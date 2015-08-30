package org.hl7.fhir.tools.publisher;

import org.hl7.fhir.definitions.model.ImplementationGuideDefn;
import org.hl7.fhir.tools.publisher.Publisher.DocumentHolder;

public interface SectionNumberer {
  String addSectionNumbers(String file, String logicalName, String src, String id, int level, DocumentHolder doch, ImplementationGuideDefn ig) throws Exception; 

}
