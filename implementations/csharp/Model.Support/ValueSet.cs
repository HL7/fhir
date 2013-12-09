using System;
using System.Collections.Generic;
using Hl7.Fhir.Support;
using System.Linq;

/*
  IList<Tag>
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

namespace Hl7.Fhir.Model
{
    public partial class ValueSet : Hl7.Fhir.Model.Resource
    {
        public static bool CodeEquals(string code, string value, bool caseSensitive)
        {
            return String.Equals(code,value,
                caseSensitive ? StringComparison.Ordinal : 
                        StringComparison.OrdinalIgnoreCase);
        }


        public static IEnumerable<ValueSetDefineConceptComponent> GetFlattenedDefinedConcepts(
                        IEnumerable<ValueSetDefineConceptComponent> concepts)
        {
            foreach (var concept in concepts)
            {
                yield return concept;

                if (concept.Concept != null)
                    foreach (var childConcept in GetFlattenedDefinedConcepts(concept.Concept))
                        yield return childConcept;
            }
        }


        internal static ValueSetDefineConceptComponent GetDefinedConceptForCode(
                    IEnumerable<ValueSetDefineConceptComponent> concepts, string code, bool caseSensitive = true)
        {
            if (concepts != null)
                return GetFlattenedDefinedConcepts(concepts)
                    .FirstOrDefault(c => CodeEquals(c.Code, code, caseSensitive));
            else
                return null;
        }


        /// <summary>
        /// Searches for a concept, defined in this ValueSet, using its code
        /// </summary>
        /// <param name="code"></param>
        /// <returns>The concept, or null if there was no concept found with that code</returns>
        /// <remarks>The search will search nested concepts as well. 
        /// Whether the search is case-sensitive depends on the value of Define.CaseSensitive</remarks>
        public ValueSetDefineConceptComponent GetDefinedConceptForCode(string code)
        {           
            if (this.Define != null && this.Define.Concept != null)
            {
                bool caseSensitive = Define.CaseSensitive.GetValueOrDefault();
                return GetDefinedConceptForCode(this.Define.Concept, code, caseSensitive);
            }
            else
                return null;
        }
    }
}
