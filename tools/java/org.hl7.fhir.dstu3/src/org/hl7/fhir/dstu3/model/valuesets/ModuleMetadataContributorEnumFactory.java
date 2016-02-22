package org.hl7.fhir.dstu3.model.valuesets;

import org.hl7.fhir.dstu3.model.EnumFactory;

public class ModuleMetadataContributorEnumFactory implements EnumFactory<ModuleMetadataContributor> {

  public ModuleMetadataContributor fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("author".equals(codeString))
      return ModuleMetadataContributor.AUTHOR;
    if ("editor".equals(codeString))
      return ModuleMetadataContributor.EDITOR;
    if ("reviewer".equals(codeString))
      return ModuleMetadataContributor.REVIEWER;
    if ("endorser".equals(codeString))
      return ModuleMetadataContributor.ENDORSER;
    throw new IllegalArgumentException("Unknown ModuleMetadataContributor code '"+codeString+"'");
  }

  public String toCode(ModuleMetadataContributor code) {
    if (code == ModuleMetadataContributor.AUTHOR)
      return "author";
    if (code == ModuleMetadataContributor.EDITOR)
      return "editor";
    if (code == ModuleMetadataContributor.REVIEWER)
      return "reviewer";
    if (code == ModuleMetadataContributor.ENDORSER)
      return "endorser";
    return "?";
  }

    public String toSystem(ModuleMetadataContributor code) {
      return code.getSystem();
      }

}

