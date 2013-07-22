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


using Hl7.Fhir.Model;
using Hl7.Fhir.Support;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;

namespace Hl7.Fhir.Support.Search
{
    public abstract class SearchParamValue
    {
        internal abstract string QueryValue { get; }

        public DateParamValue AsDateParam()
        {
            if (this is DateParamValue)
                return (DateParamValue)this;
            else if (this is UntypedParamValue)
                return DateParamValue.FromQueryValue(((UntypedParamValue)this).Value);
            else
                throw new InvalidCastException("Cannot convert to Date Param as this is a" + this.GetType().Name);                
        }

        public IntegerParamValue AsIntegerParam()
        {
            if (this is IntegerParamValue)
                return (IntegerParamValue)this;
            else if (this is UntypedParamValue)
                return IntegerParamValue.FromQueryValue(((UntypedParamValue)this).Value);
            else
                throw new InvalidCastException("Cannot convert to Integer Param as this is a" + this.GetType().Name); 
        }

        public ReferenceParamValue AsReferenceParam()
        {
            if (this is ReferenceParamValue)
                return (ReferenceParamValue)this;
            else if (this is UntypedParamValue)
                return ReferenceParamValue.FromQueryValue(((UntypedParamValue)this).Value);
            else
                throw new InvalidCastException("Cannot convert to Reference Param as this is a" + this.GetType().Name); 
        }

        public StringParamValue AsStringParam()
        {
            if (this is StringParamValue)
                return (StringParamValue)this;
            else if (this is UntypedParamValue)
                return StringParamValue.FromQueryValue(((UntypedParamValue)this).Value);
            else
                throw new InvalidCastException("Cannot convert to String Param as this is a" + this.GetType().Name); 
        }

        public TokenParamValue AsTokenParam()
        {
            if (this is TokenParamValue)
                return (TokenParamValue)this;
            else if (this is UntypedParamValue)
                return TokenParamValue.FromQueryValue(((UntypedParamValue)this).Value);
            else
                throw new InvalidCastException("Cannot convert to Token Param as this is a" + this.GetType().Name); 
        }

        public CombinedParamValue AsCombinedParam()
        {
            if (this is CombinedParamValue)
                return (CombinedParamValue)this;
            else if (this is UntypedParamValue)
                return CombinedParamValue.FromQueryValue(((UntypedParamValue)this).Value);
            else
                throw new InvalidCastException("Cannot convert to Combined Param as this is a" + this.GetType().Name); 
        }
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


    internal class UntypedParamValue : SearchParamValue
    {
        internal string Value { get; set; }

        internal UntypedParamValue(string value)
        {
            Value = value;
        }

        internal override string QueryValue
        {
            get
            {
                return Value;
            }
        }
    }
}