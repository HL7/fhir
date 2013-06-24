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

using Hl7.Fhir.Support;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;

namespace Hl7.Fhir.Model
{
    // value can be true or false
    public partial class FhirBoolean
    {
        public static bool TryParse(string value, out FhirBoolean result)
        {
            if (value == null)
            {
                result = new FhirBoolean(null);
                return true;
            }

            bool b;
            try
            {
                b = XmlConvert.ToBoolean(value);
                result = new FhirBoolean(b);
                return true;
            }
            catch
            {

            }

            result = null;
            return false;

            //if(value == "1" || value == "true")
            //{
            //    result = new FhirBoolean(true);
            //    return true;
            //}
            //else if(value == "0" || value == "false")
            //{
            //    result = new FhirBoolean(false);
            //    return true;
            //}
            //else if (value == null)
            //{
            //    result = new FhirBoolean(null);
            //    return true;
            //}
            //else
            //{
            //    result = null;
            //    return false;
            //}
        }

        public static FhirBoolean Parse(string value)
        {
            FhirBoolean result = null;

            if (TryParse(value, out result))
                return result;
            else
                throw new FhirFormatException("Booleans can be either 0, 1, true of false");
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
