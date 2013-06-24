/*
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

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
//using Hl7.Fhir.Support;
using System.Globalization;
using Hl7.Fhir.Support;
using System.Xml;

namespace Hl7.Fhir.Model
{
    public partial class FhirDecimal
    {
        public static bool TryParse(string value, out FhirDecimal result)
        {
            if (value == null)
            {
                result = new FhirDecimal(null);
                return true;
            }

            decimal decimalValue;

            try
            {
                decimalValue = XmlConvert.ToDecimal(value);
                result = new FhirDecimal(decimalValue);
                return true;
            }
            catch
            {
            }

            result = null;
            return false;

            //NumberStyles style = NumberStyles.AllowDecimalPoint | NumberStyles.AllowLeadingSign |
            //                    NumberStyles.AllowTrailingWhite | NumberStyles.AllowLeadingWhite;

            //if (value == null)
            //{
            //    result = new FhirDecimal(null);
            //    return true;
            //}
            //else if (Decimal.TryParse(value, style, NumberFormatInfo.InvariantInfo, out decimalValue))
            //{
            //    result = new FhirDecimal(decimalValue);
            //    return true;
            //}
            //else
            //{
            //    result = null;
            //    return false;
            //}
        }

        public static FhirDecimal Parse(string value)
        {
            FhirDecimal result = null;

            if (TryParse(value, out result))
                return result;
            else 
                throw new FhirFormatException("Not a decimal value");
        }

        public override string ToString()
        {
            if (Value.HasValue)
                return XmlConvert.ToString(Value.Value);
            else
                return null;
        }
    }
  
}
