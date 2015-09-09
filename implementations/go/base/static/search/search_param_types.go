package search

import (
	"fmt"
	"math/big"
	"net/url"
	"regexp"
	"strconv"
	"strings"
	"time"

	//"github.com/davecgh/go-spew/spew"
)

// Query describes a string-based FHIR query and the resource it is associated
// with.  For example, the URL http://acme.com/Condition?patient=123&onset=2012
// should be represented as:
// 	Query { Resource: "Condition", Query: "patient=123&onset=2012" }
type Query struct {
	Resource string
	Query    string
}

// Params parses the query string and returns a slice containing the
// appropriate SearchParam instances.  For example, a Query on the "Condition"
// resource with the query string "patient=123&onset=2012" should return a
// slice containing a ReferenceParam (for patient) and a DateParam (for onset).
func (q *Query) Params() []SearchParam {
	var results []SearchParam
	queryMap, _ := url.ParseQuery(q.Query)
	for param, values := range queryMap {
		pSplit := strings.Split(param, ":")
		info, ok := SearchParameterDictionary[q.Resource][pSplit[0]]
		if ok {
			if len(pSplit) > 1 && info.Type != "reference" {
				panic(UnsupportedError(fmt.Sprintf("search modifier: :%s", pSplit[1])))
			}
			for _, value := range values {
				results = append(results, info.CreateSearchParam(value))
			}
		} else {
			if strings.HasPrefix(param, "_") {
				panic(UnsupportedError(fmt.Sprintf("special search parameter: %s", param)))
			} else if strings.Contains(param, ".") {
				panic(UnsupportedError("chained search parameters"))
			} else if strings.Contains(param, ":") {
				panic(UnsupportedError(fmt.Sprintf("Unimplemented: search modifier: %s", param[strings.Index(param, ":"):])))
			} else {
				panic(InvalidSearchError(fmt.Sprintf("%s does not support search parameter: %s", q.Resource, param)))
			}
		}
	}
	return results
}

// SearchParam is an interface for all search parameter classes that exposes
// the SearchParamInfo.
type SearchParam interface {
	getInfo() SearchParamInfo
}

// SearchParamInfo contains information about a FHIR search parameter,
// including its name, type, and paths or composites.
type SearchParamInfo struct {
	Name       string
	Type       string
	Paths      []SearchParamPath
	Composites []string
}

// CreateSearchParam converts a singular string query value (e.g. "2012") into
// a SearchParam object corresponding to the SearchParamInfo.
func (s SearchParamInfo) CreateSearchParam(paramStr string) SearchParam {
	if ors := escapeFriendlySplit(paramStr, ','); len(ors) > 1 {
		return ParseOrParam(ors, s)
	}

	switch s.Type {
	case "composite":
		return ParseCompositeParam(paramStr, s)
	case "date":
		return ParseDateParam(paramStr, s)
	case "number":
		return ParseNumberParam(paramStr, s)
	case "quantity":
		return ParseQuantityParam(paramStr, s)
	case "reference":
		return ParseReferenceParam(paramStr, s)
	case "string":
		return ParseStringParam(paramStr, s)
	case "token":
		return ParseTokenParam(paramStr, s)
	case "uri":
		return ParseURIParam(paramStr, s)
	}
	return nil
}

// SearchParamPath indicates a dot-separated path to the property that should
// be searched, as well as the FHIR type of that property (e.g., "dateTime").
// The path indicates elements that are arrays by prefixing the element name
// with "[]" (e.g., "order.[]item.name").
type SearchParamPath struct {
	Path string
	Type string
}

// CompositeParam represents a composite-flavored search parameter.  The
// following description is from the FHIR DSTU2 specification:
//
// A resource may also specify composite parameters that take
// sequences of single values that match other defined parameters as an
// argument. The matching parameter of each component in such a sequence is
// documented in the definition of the parameter. These sequences are formed by
// joining the single values with a "$". Note that this sequence is a single
// value and itself can be composed into a set of values, so that, for example,
// multiple matching state-on-date parameters can be specified as
// state-on-date=new$2013-05-04,active$2013-05-05.
type CompositeParam struct {
	SearchParamInfo
	CompositeValues []string
}

func (c *CompositeParam) getInfo() SearchParamInfo {
	return c.SearchParamInfo
}

// ParseCompositeParam parses a composite query string and returns a pointer to
// a CompositeParam based on the query and the parameter definition.
func ParseCompositeParam(paramString string, info SearchParamInfo) *CompositeParam {
	return &CompositeParam{info, escapeFriendlySplit(paramString, '$')}
}

// DateParam represents a date-flavored search parameter.  The following
// description is from the FHIR DSTU2 specification:
//
// A date parameter searches on a date/time or period. As is usual for
// date/time related functionality, while the concepts are relatively
// straight-forward, there are a number of subtleties involved in ensuring
// consistent behavior.
type DateParam struct {
	SearchParamInfo
	Prefix Prefix
	Date   *Date
}

func (d *DateParam) getInfo() SearchParamInfo {
	return d.SearchParamInfo
}

// ParseDateParam parses a date-based query string and returns a pointer to a
// DateParam based on the query and the parameter definition.
func ParseDateParam(paramStr string, info SearchParamInfo) *DateParam {
	date := &DateParam{SearchParamInfo: info}

	var value string
	date.Prefix, value = ExtractPrefixAndValue(paramStr)
	date.Date = ParseDate(value)

	return date
}

// Date represents a date in a search query.  FHIR search params may define
// dates to varying levels of precision, and the amount of precision affects
// the behavior of the query.  Date's value should only be interpreted in the
// context of the Precision supplied.
type Date struct {
	Value     time.Time
	Precision DatePrecision
}

// String returns a string representation of the date, honoring the supplied
// precision.
func (d *Date) String() string {
	return d.Value.Format(d.Precision.layout())
}

// RangeLowIncl represents the low end of a date range to match against.  As
// the name suggests, the low end of the range is inclusive.
func (d *Date) RangeLowIncl() time.Time {
	return d.Value
}

// RangeHighExcl represents the high end of a date range to match against.  As
// the name suggests, the high end of the range is exclusive.
func (d *Date) RangeHighExcl() time.Time {
	switch d.Precision {
	case Year:
		return d.Value.AddDate(1, 0, 0)
	case Month:
		return d.Value.AddDate(0, 1, 0)
	case Day:
		return d.Value.AddDate(0, 0, 1)
	case Minute:
		return d.Value.Add(time.Minute)
	case Second:
		return d.Value.Add(time.Second)
	case Millisecond:
		return d.Value.Add(time.Millisecond)
	default:
		return d.Value.Add(time.Millisecond)
	}
}

// ParseDate parses a FHIR date string (roughly ISO 8601) into a Date object,
// maintaining the value and the precision supplied.
func ParseDate(dateStr string) *Date {
	dt := &Date{}

	dateStr = strings.TrimSpace(dateStr)
	dtRegex := regexp.MustCompile("([0-9]{4})(-(0[1-9]|1[0-2])(-(0[0-9]|[1-2][0-9]|3[0-1])(T([01][0-9]|2[0-3]):([0-5][0-9])(:([0-5][0-9])(\\.([0-9]+))?)?((Z)|(\\+|-)((0[0-9]|1[0-3]):([0-5][0-9])|(14):(00)))?)?)?)?")
	if m := dtRegex.FindStringSubmatch(dateStr); m != nil {
		y, mo, d, h, mi, s, ms, tzZu, tzOp, tzh, tzm := m[1], m[3], m[5], m[7], m[8], m[10], m[12], m[14], m[15], m[17], m[18]

		switch {
		case ms != "":
			dt.Precision = Millisecond

			// Fix milliseconds (.9 -> .900, .99 -> .990, .999999 -> .999 )
			switch len(ms) {
			case 1:
				ms += "00"
			case 2:
				ms += "0"
			case 3:
				// do nothing
			default:
				ms = ms[:3]
			}
		case s != "":
			dt.Precision = Second
		case mi != "":
			dt.Precision = Minute
		// NOTE: Skip hour precision since FHIR specification disallows it
		case d != "":
			dt.Precision = Day
		case mo != "":
			dt.Precision = Month
		case y != "":
			dt.Precision = Year
		default:
			dt.Precision = Millisecond
		}

		// Get the location (if no time components or no location, use local)
		loc := time.Local
		if h != "" {
			if tzZu == "Z" {
				loc, _ = time.LoadLocation("UTC")
			} else if tzOp != "" && tzh != "" && tzm != "" {
				tzhi, _ := strconv.Atoi(tzh)
				tzmi, _ := strconv.Atoi(tzm)
				offset := tzhi*60*60 + tzmi*60
				if tzOp == "-" {
					offset *= -1
				}
				loc = time.FixedZone(tzOp+tzh+tzm, offset)
			}
		}

		// Convert to a time.Time
		yInt, _ := strconv.Atoi(y)
		moInt, err := strconv.Atoi(mo)
		if err != nil {
			moInt = 1
		}
		dInt, err := strconv.Atoi(d)
		if err != nil {
			dInt = 1
		}
		hInt, _ := strconv.Atoi(h)
		miInt, _ := strconv.Atoi(mi)
		sInt, _ := strconv.Atoi(s)
		msInt, _ := strconv.Atoi(ms)

		dt.Value = time.Date(yInt, time.Month(moInt), dInt, hInt, miInt, sInt, msInt*1000*1000, loc)
	} else {
		// TODO: What should we do if the time format is wrong?  Right now, we default to NOW
		dt.Precision = Millisecond
		dt.Value = time.Now()
	}

	return dt
}

// DatePrecision is an enum representing the precision of a date.
type DatePrecision int

// Constant values for the DatePrecision enum.
const (
	Year DatePrecision = iota
	Month
	Day
	Minute
	Second
	Millisecond
)

func (p DatePrecision) layout() string {
	switch p {
	case Year:
		return "2006"
	case Month:
		return "2006-01"
	case Day:
		return "2006-01-02"
	case Minute:
		return "2006-01-02T15:04-07:00"
	case Second:
		return "2006-01-02T15:04:05-07:00"
	case Millisecond:
		return "2006-01-02T15:04:05.000-07:00"
	default:
		return "2006-01-02T15:04:05.000-07:00"
	}
}

// NumberParam represents a number-flavored search parameter.  The following
// description is from the FHIR DSTU2 specification:
//
// Searching on a simple numerical value in a resource.
type NumberParam struct {
	SearchParamInfo
	Prefix Prefix
	Number *Number
}

func (n *NumberParam) getInfo() SearchParamInfo {
	return n.SearchParamInfo
}

// ParseNumberParam parses a number-based query string and returns a pointer to
// a NumberParam based on the query and the parameter definition.
func ParseNumberParam(paramStr string, info SearchParamInfo) *NumberParam {
	n := &NumberParam{SearchParamInfo: info}

	var value string
	n.Prefix, value = ExtractPrefixAndValue(paramStr)
	n.Number = ParseNumber(value)

	return n
}

// Number represents a number in a search query.  FHIR search params may define
// numbers to varying levels of precision, and the amount of precision affects
// the behavior of the query.  Number's value should only be interpreted in the
// context of the Precision supplied.  The Precision indicates the number of
// decimal places in the precision.
type Number struct {
	Value     *big.Rat
	Precision int
}

// String returns a string representation of the number, honoring the supplied
// precision.
func (n *Number) String() string {
	return n.Value.FloatString(n.Precision)
}

// RangeLowIncl represents the low end of a range to match against.  As
// the name suggests, the low end of the range is inclusive.
func (n *Number) RangeLowIncl() *big.Rat {
	return new(big.Rat).Sub(n.Value, n.rangeDelta())
}

// RangeHighExcl represents the high end of a range to match against.  As
// the name suggests, the high end of the range is exclusive.
func (n *Number) RangeHighExcl() *big.Rat {
	return new(big.Rat).Add(n.Value, n.rangeDelta())
}

// The FHIR spec defines equality for 100 to be the range [99.5, 100.5) so we
// must support min/max using rounding semantics. The basic algorithm for
// determining low/high is:
//   low  (inclusive) = n - 5 / 10^p
//   high (exclusive) = n + 5 / 10^p
// where n is the number and p is the count of the number's decimal places + 1.
//
// This function returns the delta ( 5 / 10^p )
func (n *Number) rangeDelta() *big.Rat {
	p := n.Precision + 1
	denomInt := new(big.Int).Exp(big.NewInt(int64(10)), big.NewInt(int64(p)), nil)
	denomRat, _ := new(big.Rat).SetString(denomInt.String())
	return new(big.Rat).Quo(new(big.Rat).SetInt64(5), denomRat)
}

// ParseNumber parses a numeric string into a Number object, maintaining the
// value and precision supplied.
func ParseNumber(numStr string) *Number {
	n := &Number{}

	numStr = strings.TrimSpace(numStr)
	n.Value, _ = new(big.Rat).SetString(numStr)
	i := strings.Index(numStr, ".")
	if i != -1 {
		n.Precision = len(numStr) - i - 1
	} else {
		n.Precision = 0
	}

	return n
}

// QuantityParam represents a quantity-flavored search parameter.  The
// following description is from the FHIR DSTU2 specification:
//
// A quantity parameter searches on the Quantity data type.
type QuantityParam struct {
	SearchParamInfo
	Prefix Prefix
	Number *Number
	System string
	Code   string
}

func (q *QuantityParam) getInfo() SearchParamInfo {
	return q.SearchParamInfo
}

// ParseQuantityParam parses a quantity-based query string and returns a
// pointer to a QuantityParam based on the query and the parameter definition.
func ParseQuantityParam(paramStr string, info SearchParamInfo) *QuantityParam {
	q := &QuantityParam{SearchParamInfo: info}

	var value string
	q.Prefix, value = ExtractPrefixAndValue(paramStr)

	split := escapeFriendlySplit(value, '|')
	q.Number = ParseNumber(split[0])
	if len(split) == 3 {
		q.System = unescape(split[1])
		q.Code = unescape(split[2])
	}

	return q
}

// ReferenceParam represents a reference-flavored search parameter.  The
// following description is from the FHIR DSTU2 specification:
//
// A reference parameter refers to references between resources,
// e.g. find all Conditions where the subject reference is a particular patient,
// where the patient is selected by name or identifier.
type ReferenceParam struct {
	SearchParamInfo
	Reference string
}

func (r *ReferenceParam) getInfo() SearchParamInfo {
	return r.SearchParamInfo
}

// GetID returns the ID that is referred to.  If the reference isn't to an ID,
// the second return value (ok) will be false.
func (r *ReferenceParam) GetID() (id string, ok bool) {
	if r.Reference == "" {
		ok = false
	} else if !r.isURL() {
		i := strings.LastIndex(r.Reference, "/")
		id = r.Reference[i+1:]
		ok = true
	} else {
		ok = false
	}
	return
}

// GetType returns the type of reference being searched, if supplied.  If the
// type isn't available, the second return value (ok) will be false.
func (r *ReferenceParam) GetType() (typ string, ok bool) {
	if !r.isURL() {
		i := strings.LastIndex(r.Reference, "/")
		if i == -1 {
			ok = false
		} else {
			typ = r.Reference[:i]
			ok = true
		}
	} else {
		ok = false
	}
	return
}

// GetURL returns the URL that is referred to.  If the reference isn't to an URL,
// the second return value (ok) will be false.
func (r *ReferenceParam) GetURL() (url string, ok bool) {
	if r.isURL() {
		url = r.Reference
		ok = true
	} else {
		ok = false
	}
	return
}

// isURL returns true if the reference is an URL, false otherwise.
func (r *ReferenceParam) isURL() bool {
	u, e := url.Parse(r.Reference)
	if e == nil {
		return u.IsAbs()
	}
	return false
}

// ParseReferenceParam parses a reference-based query string and returns a
// pointer to a ReferenceParam based on the query and the parameter definition.
func ParseReferenceParam(paramStr string, info SearchParamInfo) *ReferenceParam {
	return &ReferenceParam{info, unescape(paramStr)}
}

// StringParam represents a string-flavored search parameter.  The
// following description is from the FHIR DSTU2 specification:
//
// The string parameter refers to simple string searches against
// sequences of characters. Matches are case- and accent- insensitive. By
// default, a field matches a string query if the value of the field equals or
// starts with the supplied parameter value, after both have been normalized by
// case and accent.
type StringParam struct {
	SearchParamInfo
	String string
}

func (s *StringParam) getInfo() SearchParamInfo {
	return s.SearchParamInfo
}

// ParseStringParam parses a string-based query string and returns a pointer to
// a StringParam based on the query and the parameter definition.
func ParseStringParam(paramString string, info SearchParamInfo) *StringParam {
	return &StringParam{info, unescape(paramString)}
}

// TokenParam represents a token-flavored search parameter.  The
// following description is from the FHIR DSTU2 specification:
//
// A token type is a parameter that searches on a pair, a URI and a
// value. It is used against code or identifier value where the value may have
// a URI that scopes its meaning. The search is performed against the pair from
// a Coding or an Identifier.
type TokenParam struct {
	SearchParamInfo
	System    string
	Code      string
	AnySystem bool
}

func (t *TokenParam) getInfo() SearchParamInfo {
	return t.SearchParamInfo
}

// ParseTokenParam parses a token-based query string and returns a pointer to
// a TokenParam based on the query and the parameter definition.
func ParseTokenParam(paramString string, info SearchParamInfo) *TokenParam {
	t := &TokenParam{SearchParamInfo: info}
	splitCode := escapeFriendlySplit(paramString, '|')
	if len(splitCode) > 1 {
		t.System = unescape(splitCode[0])
		t.Code = unescape(splitCode[1])
	} else {
		t.AnySystem = true
		t.Code = unescape(splitCode[0])
	}
	return t
}

// URIParam represents a uri-flavored search parameter.  The
// following description is from the FHIR DSTU2 specification:
//
// The uri parameter refers to an element which is URI (RFC 3986). Matches
// are precise (e.g. case, accent, and escape) sensitive, and the entire URI
// must match.
type URIParam struct {
	SearchParamInfo
	URI string
}

func (u *URIParam) getInfo() SearchParamInfo {
	return u.SearchParamInfo
}

// ParseURIParam parses an uri-based query string and returns a pointer to
// an URIParam based on the query and the parameter definition.
func ParseURIParam(paramStr string, info SearchParamInfo) *URIParam {
	return &URIParam{info, unescape(paramStr)}
}

// OrParam represents a search parameter that has multiple OR values.  The
// following description is from the FHIR DSTU2 specification:
//
// If, instead, the search is to find patients that speak either language, then
// this is a single parameter with multiple values, separated by a ','. For
// example: "/Patient?language=FR,NL". This is known as an OR search parameter,
// since the server is expected to respond with results which match either
// value.
type OrParam struct {
	SearchParamInfo
	Items []SearchParam
}

func (o *OrParam) getInfo() SearchParamInfo {
	return o.SearchParamInfo
}

// ParseOrParam parses a slice of values to be ORed and returns a pointer to
// an OrParam based on the query and the parameter definition.
func ParseOrParam(paramStr []string, info SearchParamInfo) *OrParam {
	ors := make([]SearchParam, len(paramStr))
	for i := range paramStr {
		ors[i] = info.CreateSearchParam(paramStr[i])
	}
	return &OrParam{SearchParamInfo{Name: info.Name, Type: "or"}, ors}
}

// Prefix is an enum representing FHIR parameter prefixes.  The following
// description is from the FHIR DSTU2 specification:
//
// For the ordered parameter types number, date, and quantity, a prefix
// to the parameter value may be used to control the nature of the matching.
type Prefix string

// Constant values for the Prefix enum.
const (
	EQ Prefix = "eq"
	NE Prefix = "ne"
	GT Prefix = "gt"
	LT Prefix = "lt"
	GE Prefix = "ge"
	LE Prefix = "le"
	AP Prefix = "ap"
)

// String returns the prefix as a string.
func (p Prefix) String() string {
	return string(p)
}

// ExtractPrefixAndValue parses a string parameter value into an optional
// prefix and value.
func ExtractPrefixAndValue(s string) (Prefix, string) {
	prefix := EQ
	for _, p := range []Prefix{EQ, NE, GT, LT, GE, LE, AP} {
		if strings.HasPrefix(s, p.String()) {
			prefix = p
			break
		}
	}
	return prefix, strings.TrimPrefix(s, prefix.String())
}

// escapeFriendlySplit splits a FHIR parameter value, properly handling special
// FHIR escape characters and sequences.  The following description is from the
// FHIR DSTU2 specification:
//
// In the rules above, special rules are defined for the characters "$", ",",
// and "|". As a consequence, if these characters appear in an actual parameter
// value, they must be differentiated from their use as separator characters.
// When any of these characters appear in an actual parameter value, they must
// be prepended by the character "\" (which also must be used to prepend
// itself).
func escapeFriendlySplit(s string, sep byte) []string {
	var result []string

	start := 0
	for i := range s {
		if s[i] == sep {
			// Count the preceding backslashes to see if it is escaped
			numBS := 0
			for j := i - 1; j >= 0; j-- {
				if s[j] == '\\' {
					numBS++
				} else {
					break
				}
			}
			// If number of preceding backslashes are even, it is not escaped
			if numBS%2 == 0 {
				result = append(result, s[start:i])
				start = i + 1
			}
		}
	}
	result = append(result, s[start:])

	return result
}

func unescape(s string) string {
	// A little hacky, but... otherwise there's a lot of annoying lookbacks/lookaheads
	s = strings.Replace(s, "\\\\", "```ie.bs```", -1)
	s = strings.Replace(s, "\\|", "|", -1)
	s = strings.Replace(s, "\\$", "$", -1)
	s = strings.Replace(s, "\\,", ",", -1)
	return strings.Replace(s, "```ie.bs```", "\\", -1)
}

func escape(s string) string {
	// A little hacky, but... otherwise there's a lot of annoying lookbacks/lookaheads
	s = strings.Replace(s, "\\", "```ie.bs```", -1)
	s = strings.Replace(s, "|", "\\|", -1)
	s = strings.Replace(s, "$", "\\$", -1)
	s = strings.Replace(s, ",", "\\,", -1)
	return strings.Replace(s, "```ie.bs```", "\\\\", -1)
}
