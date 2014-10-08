package org.hl7.fhir.instance.validation;
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

import java.util.List;

import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.validation.ValidationMessage.Source;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ValidationErrorHandler implements ErrorHandler {

  private List<ValidationMessage> outputs;

  public ValidationErrorHandler(List<ValidationMessage> outputs) {
    this.outputs = outputs;
  }

  @Override
public void error(SAXParseException arg0) throws SAXException {
    ValidationMessage o = new ValidationMessage();
    o.setType("invalid");
    o.setLevel(IssueSeverity.ERROR);
    o.setLocation("line "+Integer.toString(arg0.getLineNumber())+", column "+Integer.toString(arg0.getColumnNumber()));
    o.setMessage(arg0.getMessage());
    o.setSource(Source.Schema);
    outputs.add(o);
  }

  @Override
public void fatalError(SAXParseException arg0) throws SAXException {
    ValidationMessage o = new ValidationMessage();
    o.setType("invalid");
    o.setLevel(IssueSeverity.FATAL);
    o.setLocation("line "+Integer.toString(arg0.getLineNumber())+", column "+Integer.toString(arg0.getColumnNumber()));
    o.setMessage(arg0.getMessage());
    o.setSource(Source.Schema);
    outputs.add(o);
  }

  @Override
public void warning(SAXParseException arg0) throws SAXException {
    ValidationMessage o = new ValidationMessage();
    o.setType("invalid");
    o.setLevel(IssueSeverity.WARNING);
    o.setLocation("line "+Integer.toString(arg0.getLineNumber())+", column "+Integer.toString(arg0.getColumnNumber()));
    o.setMessage(arg0.getMessage());
    o.setSource(Source.Schema);
    outputs.add(o);
  }

}
