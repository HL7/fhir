﻿/*
  Copyright (c) 2011-2012, HL7, Inc
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
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace Hl7.Fhir.Model
{
    public partial class Oid
    {
        public static bool TryParseValue(string value, out string result)
        {
            if (value == null) throw new ArgumentNullException("value");

            //The PATTERN (generated from the source) is incorrect. So, temporarily, a corrected version
            //until after the ballot has updated this regexp
            if (value==null || Regex.IsMatch(value, "^" + PATTERN + "$", RegexOptions.Singleline))
            {
                result = value;
                return true;
            }
            else
            {
                result = null;
                return false;
            }
        }

        public static string ParseValue(string value)
        {
            string result = null;

            if (TryParseValue(value, out result))
                return result;
            else
                throw new FhirFormatException("Not an correctly formatted oid value");
        }

        internal override ErrorList ValidateRules()
        {
            var result = new ErrorList();

            if (Value == null)
                result.Add("Oid values cannot be empty");
            else
            {
                string dummy;

                if (!TryParseValue(Value, out dummy))
                    result.Add("Not an correctly formatted oid value");
            }

            return result; 
        }

        public override string ToString()
        {
            return Value;
        }
    }
}
