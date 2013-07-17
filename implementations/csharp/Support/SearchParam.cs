/*
  Copyright (c) 2011-2013, HL7, Inc.
  All rights reserved.
  
  Redistribution and use in source and binary forms, with or without modification, 
  are permitted provided that the following conditions are met:
  
   * Redistributions of source code must retain the above copyright notice, this 
     list of conditions and the following disclaimer.
   * Redistributions in binary form must reproduce the above copyright notice, 
     this list of conditions and the following disclaimer in the documentation 
     and/or other materials provided with the distribution.
   * Neither the name of HL7 nor the names of its contributors may be used to 
     endorse or promote products derived from this software without specific 
     prior written permission.
  
  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
  IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
  INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
  POSSIBILITY OF SUCH DAMAGE.
  

*/


using Hl7.Fhir.Support;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;

namespace Hl7.Fhir.Support
{
    public abstract class ParamValue
    {
    }

    public class SingleParamValue : ParamValue
    {
        private string _formatted;

        public enum NamespaceHandling
        {
            MatchAnyNamespace,
            MatchWithoutNamespace
        }

        protected SingleParamValue(string formatted)
        {
            _formatted = formatted;
        }

        public static SingleParamValue MakeString(string value)
        {
            return new SingleParamValue("\"" + value + "\"");
        }


        public static SingleParamValue MakeInteger(int value, ComparisonOperator comparison = ComparisonOperator.EQ)
        {
            var formatted = addComparison(value.ToString(),comparison);

            return new SingleParamValue(formatted);
        }

        private static string addComparison(string value, ComparisonOperator comparison)
        {
            string result = value;

            if (comparison != ComparisonOperator.EQ)
            {
                switch (comparison)
                {
                    case ComparisonOperator.GT: result = ">" + result; break;
                    case ComparisonOperator.GTE: result = ">=" + result; break;
                    case ComparisonOperator.LT: result = "<" + result; break;
                    case ComparisonOperator.LTE: result = "<=" + result; break;
                    default: throw new InvalidOperationException();
                }
            }

            return result;
        }


        public static SingleParamValue MakeDate(DateTimeOffset value, ComparisonOperator comparison = ComparisonOperator.EQ)
        {
            return MakeDate(Util.FormatIsoDateTime(value), comparison);
        }

        public static SingleParamValue MakeDate(string partial, ComparisonOperator comparison = ComparisonOperator.EQ)
        {
            var formatted = addComparison(partial, comparison);

            return new SingleParamValue(formatted);
        }

        public static SingleParamValue MakeToken(string value, string namespc)
        {
            return new SingleParamValue(namespc + "!" + value);
        }


        public static SingleParamValue MakeToken(string value, NamespaceHandling nsHandling = NamespaceHandling.MatchAnyNamespace)
        {
            if(nsHandling == NamespaceHandling.MatchAnyNamespace)
                return new SingleParamValue(value);
            else if(nsHandling == NamespaceHandling.MatchWithoutNamespace)
                return new SingleParamValue("!" + value);
            else
                throw new ArgumentException("Unrecognized NamespaceHandling detected", "nsHandling");
        }

        public static SingleParamValue MakeReference(string id, string resourceType = null)
        {
            var formatted = id;

            if(resourceType != null) formatted = resourceType + "/" + id;
 
            return new SingleParamValue(formatted);
        }


        public override string ToString()
        {
 	        return _formatted;
        }
    }


    public class CombinedParamValue : ParamValue
    {
        public CombinedParamValue( params SingleParamValue[] parameter)
        {
        }

        public CombinedParamValue( IEnumerable<SingleParamValue> parameters)
        {
        }

        public string QueryValue { get; protected set; }
    }


    public class SearchParam
    {
        public SearchParam(string name, ParamValue value)
        {
            Name = name;
        }

        public SearchParam(string name, string modifier, ParamValue value)
        {
            Name = name;
        }

        public SearchParam(string name, IEnumerable<ParamValue> values)
        {
            Name = name;
        }

        public SearchParam(string name, string modifier, IEnumerable<ParamValue> values)
        {
            Name = name;
        }

        public SearchParam(string name, params ParamValue[] values)
        {
            Name = name;
        }

        public SearchParam(string name, string modifier, params ParamValue[] values)
        {
            Name = name;
        }


        /// <summary>
        /// Name of the parameter as found in the definition of the Resource
        /// </summary>
        public string Name { get; protected set; }

        /*
        public string Value { get; protected set; }

        public string Modifier { get; protected set; }

        /// <summary>
        /// Indicates whether the search is for instances where this parameter does or does not
        /// have any value. Returns null if no missing modifier found.
        /// </summary>
        public MissingOperator? Missing { get; protected set; }

        public const string MOD_MISSING = "missing";

        protected string renderMissingParam()
        {
            if (Missing != null)
            {
                string key, value;
                key = this.Name + ":missing";
                value = Missing.Value == MissingOperator.HasNoValue ? "true" : "false";

                return QueryParam.ToString(key, value);
            }
            else
                throw new InvalidOperationException();
        }

        protected static Tuple<string,ComparisonOperator> findComparator(string value)
        {
            ComparisonOperator comparison = ComparisonOperator.EQ;

            if (value.StartsWith(">=") && value.Length > 2)
            { comparison = ComparisonOperator.GTE; value = value.Substring(2); }
            else if (value.StartsWith(">"))
            { comparison = ComparisonOperator.GT; value = value.Substring(1); }
            else if (value.StartsWith("<=") && value.Length > 2)
            { comparison = ComparisonOperator.LTE; value = value.Substring(2); }
            else if (value.StartsWith("<"))
            { comparison = ComparisonOperator.LT; value = value.Substring(1); }

            return Tuple.Create(value, comparison);
        }

        protected static Tuple<string,MissingOperator> checkMissingParam(string key, string value)
        {
            if (key.EndsWith(":missing"))
            {
                key = key.Substring(0, key.Length - ":missing".Length);
                if (String.Equals(value,"true"))
                    return Tuple.Create(key,MissingOperator.HasNoValue);
                else if(String.Equals(value,"false"))
                    return Tuple.Create(key,MissingOperator.HasAnyValue);
                else
                    throw new ArgumentException("Search parameter uses modifier 'missing', but has no valid parameter value");
            }
            else
                return null;
        }

        public static string ToString()
        {

        } */
    }


    /// <summary>
    /// Type op missing operator applicable to any parameter, except combinations
    /// </summary>
    public enum MissingOperator
    {
        HasAnyValue,    // Has a value (missing=false)
        HasNoValue      // Does not have any value (missing=true)
    }


    /// <summary>
    /// Types of comparison operator applicable to searching on integer values
    /// </summary>
    public enum ComparisonOperator
    {
        LT,     // less than
        LTE,    // less than or equals
        EQ,     // equals (default)
        GTE,    // greater than or equals
        GT      // greater than
    }




    public class IntegerParam 
    {
        /// <summary>
        /// Integer value to compare the value in the instance with
        /// </summary>
        public int Value { get; protected set; }

        /// <summary>
        /// Type of comparison used
        /// </summary>
        public ComparisonOperator Comparison { get; protected set; }


   /*     public IntegerParam(string name, int value) : base(name)
        {
            Value = value;
            Name = name;
            Comparison = ComparisonOperator.EQ;
        }

        public IntegerParam(string name, ComparisonOperator comparison, int value)
            : this(name, value)
        {
            Comparison = comparison;
        }

        public IntegerParam(string name, MissingOperator missing) 
            : base(name, missing)
        {
        }


        protected IntegerParam(string name)
            : base(name)
        {
        }
        
        protected IntegerParam(string name, string modifier, string value)
            : base(name)
        {     

            if(String.IsNullOrEmpty(value)) throw new ArgumentException("Integer query parameter cannot have an empty value");

            var missing = checkMissingParam(key,value);
            if (missing != null) return new IntegerParam(missing.Item1, missing.Item2);

            var compVal = findComparator(value);

            value = compVal.Item1;
            var comparator = compVal.Item2;

            int intValue=0;
            if (Int32.TryParse(value, out intValue))
                return new IntegerParam(key, comparator, intValue);
            else
                throw new ArgumentException("Integer query parameter value is not a valid integer");
        }

        */

        protected string QueryNameModifier
        {
            get
            {
                return null;        // No type specific modifier
            }
        }

        protected string QueryValue
        {
            get
            {
                var value = Value.ToString();

                if (Comparison != ComparisonOperator.EQ)
                {
                    switch (Comparison)
                    {
                        case ComparisonOperator.GT: value = ">" + value; break;
                        case ComparisonOperator.GTE: value = ">=" + value; break;
                        case ComparisonOperator.LT: value = "<" + value; break;
                        case ComparisonOperator.LTE: value = "<=" + value; break;
                        default: throw new InvalidOperationException();
                    }
                }

                return value;
            }
        }
    }
}