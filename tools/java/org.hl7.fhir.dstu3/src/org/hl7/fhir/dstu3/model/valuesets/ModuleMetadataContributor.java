package org.hl7.fhir.dstu3.model.valuesets;

import org.hl7.fhir.dstu3.exceptions.FHIRException;

public enum ModuleMetadataContributor {

        /**
         * An author of the content of the module
         */
        AUTHOR, 
        /**
         * An editor of the content of the module
         */
        EDITOR, 
        /**
         * A reviewer of the content of the module
         */
        REVIEWER, 
        /**
         * An endorser of the content of the module
         */
        ENDORSER, 
        /**
         * added to help the parsers
         */
        NULL;
        public static ModuleMetadataContributor fromCode(String codeString) throws FHIRException {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("author".equals(codeString))
          return AUTHOR;
        if ("editor".equals(codeString))
          return EDITOR;
        if ("reviewer".equals(codeString))
          return REVIEWER;
        if ("endorser".equals(codeString))
          return ENDORSER;
        throw new FHIRException("Unknown ModuleMetadataContributor code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case AUTHOR: return "author";
            case EDITOR: return "editor";
            case REVIEWER: return "reviewer";
            case ENDORSER: return "endorser";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/module-metadata-contributor";
        }
        public String getDefinition() {
          switch (this) {
            case AUTHOR: return "An author of the content of the module";
            case EDITOR: return "An editor of the content of the module";
            case REVIEWER: return "A reviewer of the content of the module";
            case ENDORSER: return "An endorser of the content of the module";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case AUTHOR: return "Author";
            case EDITOR: return "Editor";
            case REVIEWER: return "Reviewer";
            case ENDORSER: return "Endorser";
            default: return "?";
          }
    }


}

