package org.hl7.fhir.tools.implementations.go;
/*
Contributed by Mitre Corporation

Copyright (c) 2011-2016, HL7, Inc & The MITRE Corporation
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

/**
 * FHIRType represents the possible FHIR types along with the Go types they are mapped to.  In addition, each type
 * indicated if it is an "open type".  I couldn't find a way to get the right list of open types programmatically, so
 * the open value is based on the spec: http://hl7.org/fhir/DSTU2/datatypes.html#open
 */
public enum FHIRType {
    ADDRESS("Address", true),
    AGE("Age", false, GoType.QUANTITY),
    ANNOTATION("Annotation", true),
    ATTACHMENT("Attachment", true),
    BACKBONEELEMENT("BackboneElement", false),
    BASE64BINARY("base64Binary", true, GoType.STRING),
    BOOLEAN("boolean", true, GoType.BOOL),
    CODE("code", true, GoType.STRING),
    CODEABLECONCEPT("CodeableConcept", true),
    CODING("Coding", true),
    CONTACTPOINT("ContactPoint", true),
    COUNT("Count", false, GoType.QUANTITY),
    DATE("date", true, GoType.FHIRDATETIME),
    DATETIME("dateTime", true, GoType.FHIRDATETIME),
    DECIMAL("decimal", true, GoType.FLOAT64),
    DISTANCE("Distance", false, GoType.QUANTITY),
    DURATION("Duration", false, GoType.QUANTITY),
    ELEMENT("Element", false),
    ELEMENTDEFINITION("ElementDefinition", false),
    EXTENSION("Extension", false),
    HUMANNAME("HumanName", true),
    ID("id", true, GoType.STRING),
    IDENTIFIER("Identifier", true),
    IDREF("idref", false, GoType.REFERENCE),
    INSTANT("instant", true, GoType.FHIRDATETIME),
    INTEGER("integer", true, GoType.INT32),
    MARKDOWN("markdown", true, GoType.STRING),
    META("Meta", true),
    MONEY("Money", false, GoType.QUANTITY),
    NARRATIVE("Narrative", false),
    OID("oid", true, GoType.STRING),
    PERIOD("Period", true),
    POSITIVEINT("positiveInt", true, GoType.UINT32),
    QUANTITY("Quantity", true, GoType.QUANTITY),
    RANGE("Range", true),
    RATIO("Ratio", true),
    REFERENCE("Reference", true, GoType.REFERENCE),
    RESOURCE("Resource", false, GoType.INTERFACE),
    SAMPLEDDATA("SampledData", true),
    SIGNATURE("Signature", true),
    SIMPLEQUANTITY("SimpleQuantity", false, GoType.QUANTITY),
    STRING("string", true, GoType.STRING),
    TIME("time", true, GoType.FHIRDATETIME),
    TIMING("Timing", true),
    UNSIGNEDINT("unsignedInt", true, GoType.UINT32),
    URI("uri", true, GoType.STRING),
    UUID("uuid", false, GoType.STRING),
    XHTML("xhtml", false, GoType.STRING)
    ;

    private final String type;
    private final boolean open;
    private final String goType;

    FHIRType(String type, boolean open, GoType goType) {
        this.type = type;
        this.open = open;
        this.goType = goType.type();
    }

    FHIRType(String type, boolean open) {
        this.type = type;
        this.open = open;
        this.goType = type;
    }

    public String type() {
        return this.type;
    }

    public String goType() {
        return this.goType;
    }

    public boolean isOpen() {
        return this.open;
    }

    public static FHIRType byType(String type) {
        if (type == null) {
            return null;
        }
        return FHIRType.valueOf(type.toUpperCase());
    }
}
