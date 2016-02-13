package search

import (
	"fmt"
	"net/url"
	"strings"
)

// ParseQuery provides an alternative to url.ParseQuery when the order of parameters must be retained.  ParseQuery
// parses the URL-encoded query string and returns a URLQueryParameters object that can be used to get the ordered list
// of parameters, a map of the parameters, or parameters by name.  ParseQuery always returns a non-nil
// URLQueryParameters object containing all the valid query parameters found; err describes the first decoding error
// encountered, if any.
func ParseQuery(query string) (u URLQueryParameters, err error) {
	// Replace ";" with "&" so we can split on a single character
	query = strings.Replace(query, ";", "&", -1)
	// Split it into parts (e.g., "foo=bar" is a part)
	parts := strings.Split(query, "&")
	// iterate the parts and add them to the URLQueryParameters
	for _, part := range parts {
		if i := strings.Index(part, "="); i >= 0 {
			key, value := part[:i], part[i+1:]
			key, keyErr := url.QueryUnescape(key)
			if keyErr != nil {
				if err == nil {
					err = keyErr
				}
				continue
			}
			value, valueErr := url.QueryUnescape(value)
			if valueErr != nil {
				if err == nil {
					err = valueErr
				}
				continue
			}
			u.Add(key, value)
		}
	}
	return
}

// URLQueryParameter represents a query parameter as a key/value pair.
type URLQueryParameter struct {
	Key   string
	Value string
}

// URLQueryParameters represents an ordered list of query parameters that can be manipulated in several different ways.
type URLQueryParameters struct {
	params []URLQueryParameter
}

// Add adds a key/value pair to the end of the list of URLQueryParameters.  If the key already exists, the key/value is
// still added to the end of the list, as URLQueryParameters permite duplicate keys.  To replace existing values, use
// Set instead.
func (u *URLQueryParameters) Add(Key string, value string) {
	u.params = append(u.params, URLQueryParameter{Key: Key, Value: value})
}

// Set sets the value for the query parameter with the specified key.  If a query parameter with the specified key
// already exists, it overwrites the existing value.  If multiple query parameters with the specified key exist, it
// overwrites the value of the first matching query parameter and removes the remaining query parameters from the
// list.  If no query parameters exist with the given key, the key/value pair are added as a new query parameter at
// the end of the list.
func (u *URLQueryParameters) Set(key string, value string) {
	var dups []int
	var found bool
	for i := range u.params {
		if u.params[i].Key == key {
			if !found {
				u.params[i].Value = value
				found = true
			} else {
				dups = append(dups, i)
			}
		}
	}
	if !found {
		u.Add(key, value)
	} else {
		for i := range dups {
			j := dups[i] - i
			u.params = append(u.params[:j], u.params[j+1:]...)
		}
	}
}

// Get returns the value of the first query parameter with the specified key.  If no query parameters have the specified
// key, an empty string is returned.
func (u *URLQueryParameters) Get(key string) string {
	for i := range u.params {
		if u.params[i].Key == key {
			return u.params[i].Value
		}
	}
	return ""
}

// GetMulti returns a slice containing the values of all the query parameters with the specified key, in the order in which
// they were originally specified.  If no query parameters have the specified key, an empty slice is returned.
func (u *URLQueryParameters) GetMulti(key string) []string {
	var multi []string
	for i := range u.params {
		if u.params[i].Key == key {
			multi = append(multi, u.params[i].Value)
		}
	}
	return multi
}

// All returns a copy of the slice containing all of the URLQueryParameters in the original order.
func (u *URLQueryParameters) All() []URLQueryParameter {
	all := make([]URLQueryParameter, len(u.params))
	copy(all, u.params)
	return all
}

// Values returns a map similar to the map that would be returned by url.ParseQuery().  The url.Values object does not
// guarantee that order is preserved.  If order must be preserved, use on of the other functions.
func (u *URLQueryParameters) Values() url.Values {
	values := url.Values{}
	for _, param := range u.params {
		values.Add(param.Key, param.Value)
	}
	return values
}

// Encode returns a URL-encoded string representing the query parameters in the original order.
func (u *URLQueryParameters) Encode() string {
	if len(u.params) == 0 {
		return ""
	}

	parts := make([]string, len(u.params))
	for i, param := range u.params {
		parts[i] = fmt.Sprintf("%s=%s", url.QueryEscape(param.Key), url.QueryEscape(param.Value))
	}

	return strings.Join(parts, "&")
}
