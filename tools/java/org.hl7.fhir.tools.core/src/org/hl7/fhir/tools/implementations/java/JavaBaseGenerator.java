package org.hl7.fhir.tools.implementations.java;
/*
Copyright (c) 2011+, HL7, Inc
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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingMethod;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.r5.model.CodeSystem;
import org.hl7.fhir.r5.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.r5.model.Enumerations.BindingStrength;
import org.hl7.fhir.r5.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.tools.implementations.GeneratorUtils;
import org.hl7.fhir.utilities.Utilities;

public class JavaBaseGenerator extends OutputStreamWriter {

  protected Definitions definitions;

  public JavaBaseGenerator(OutputStream out) throws UnsupportedEncodingException {
		super(out, "UTF-8");
	}

  protected boolean isJavaPrimitive(ElementDefn e) {
    return e.getTypes().size() == 1 && (isPrimitive(e.typeCode()) || e.typeCode().equals("xml:lang"));
  }

  protected boolean isPrimitive(String name) {
    return definitions.hasPrimitiveType(name) || (name.endsWith("Type") && definitions.getPrimitives().containsKey(name.substring(0, name.length()-4)));
  }

	protected String getElementName(String name, boolean alone) {
	  if (name.equals("[type]"))
	    return "value";
	  else if ((alone && GeneratorUtils.isJavaReservedWord(name)) || (!alone && name.equals("class")))
	    return name+"_";
	  else if (name.equals("[x]"))
      return "value";
	  else
	    return name.replace("[x]", "");
	}

	protected String getTypeName(ElementDefn e) throws Exception {
		if (e.getTypes().size() > 1) {
			return "Type";
		} else if (e.getTypes().size() == 0) {
			throw new Exception("not supported");
		} else {
			return getTypename(e.getTypes().get(0));
		}
	}

	protected String getTypename(TypeRef type) throws Exception {
		if (type.getParams().size() == 1) {
			if (type.isResourceReference())
				return "Reference";
			else if (type.isCanonical())
        return "CanonicalType";
			else
				throw new Exception("not supported");
		} else if (type.getParams().size() > 1) {
			if (type.isResourceReference())
				return "Reference";
			else if (type.isCanonical())
        return "CanonicalType";
      else
				throw new Exception("not supported");
		} else {
			return getTypeName(type.getName());
		}
	}

	protected String getTypeName(String tn) {
		if (tn.equals("string")) {
			return "StringType";
		} else if (tn.equals("Any")) {
			return "Reference";
    } else if (tn.equals("SimpleQuantity")) {
      return "Quantity";
    } else if (definitions.hasPrimitiveType(tn)) {
      return getTitle(tn)+"Type";
		} else {
			return getTitle(tn);
		}
	}

	protected String getTitle(String name) {
		return Utilities.noString(name) ? "Value" : name.substring(0, 1).toUpperCase()+ name.substring(1);
	}


  protected List<ConceptDefinitionComponent> listAllCodes(CodeSystem cs) {
    List<ConceptDefinitionComponent> result = new ArrayList<ConceptDefinitionComponent>();
    addAllCodes(result, cs.getConcept());
    return result;
  }

  private void addAllCodes(List<ConceptDefinitionComponent> result, List<ConceptDefinitionComponent> concept) {
    for (ConceptDefinitionComponent c : concept) {
      result.add(c);
      addAllCodes(result, c.getConcept());
    }
  }

  protected String makeConst(String cc) {
    if (cc.equals("*"))
      cc = "ASTERISK";
    if (Utilities.isOid(cc))
      cc = "OID_"+cc;
    if (cc.equals("%"))
      cc = "pct";
    else if (cc.equals("<"))
      cc = "less_Than";
    else if (cc.equals("<="))
      cc = "less_Or_Equal";
    else if (cc.equals(">"))
      cc = "greater_Than";
    else if (cc.equals(">="))
      cc = "greater_Or_Equal";
    else if (cc.equals("="))
      cc = "equal";
    else if (cc.equals("!="))
      cc = "not_equal";
    else if (allPlusMinus(cc))
      cc = cc.replace("-", "Minus").replace("+", "Plus");
    else
      cc = cc.replace("-", "").replace("+", "");
    cc = cc.replace("(", "_").replace(")", "_");
    cc = cc.replace("{", "_").replace("}", "_");
    cc = cc.replace("<", "_").replace(">", "_");
    cc = cc.replace(".", "_").replace("/", "_");
    cc = cc.replace(":", "_");
    cc = cc.replace("%", "pct");
    if (Utilities.isInteger(cc.substring(0, 1)))
      cc = "_"+cc;
    cc = cc.toUpperCase();
    if (GeneratorUtils.isJavaReservedWord(cc))
      cc = cc + "_";
    return cc;
  }

  private boolean allPlusMinus(String cc) {
    for (char c : cc.toCharArray())
      if (!(c == '-' || c == '+'))
        return false;
    return true;
  }

  protected boolean isEnum(BindingSpecification cd) {
    boolean ok = cd.getBinding() == (BindingSpecification.BindingMethod.CodeList) || (cd.getStrength() == BindingStrength.REQUIRED && cd.getBinding() == BindingMethod.ValueSet);
    if (ok) {
      if (cd.getValueSet() != null && cd.getValueSet().hasCompose() && cd.getValueSet().getCompose().getInclude().size() == 1) {
        ConceptSetComponent inc = cd.getValueSet().getCompose().getIncludeFirstRep();
        if (inc.hasSystem() && !inc.hasFilter() && !inc.hasConcept() && !(inc.getSystem().startsWith("http://hl7.org/fhir") || inc.getSystem().startsWith("http://terminology.hl7.org")))
          ok = false;
      }
    }
    return ok;
  }


}
