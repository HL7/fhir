package org.hl7.fhir.dstu3.utils;

import java.util.Map;

import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.StructureMap;
import org.hl7.fhir.dstu3.utils.StructureMapTransformer.ITransformerServices;

public class StructureMapTransformer {

  public interface ITransformerServices {
//    public boolean validateByValueSet(Coding code, String valuesetId);
//    public Coding translate(Coding code)
//    ValueSet validation operation
//    Translation operation
//    Lookup another tree of data
//    Create an instance tree
//    Return the correct string format to refer to a tree (input or output)

  }
  private Map<String, StructureMap> library;
  private ITransformerServices services;
  
}
