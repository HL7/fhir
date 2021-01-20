package org.hl7.fhir.tools.converters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.definitions.generators.specification.ToolResourceUtilities;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingMethod;
import org.hl7.fhir.definitions.parsers.CodeListToValueSetParser;
import org.hl7.fhir.definitions.parsers.ValueSetGenerator;
import org.hl7.fhir.definitions.parsers.spreadsheets.BindingsParser;
import org.hl7.fhir.definitions.parsers.spreadsheets.SpreadSheetBase.ExtensionSorter;
import org.hl7.fhir.r5.model.ElementDefinition;
import org.hl7.fhir.r5.model.Extension;
import org.hl7.fhir.r5.model.ImplementationGuide;
import org.hl7.fhir.r5.model.ImplementationGuide.ImplementationGuideDefinitionResourceComponent;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r5.formats.IParser.OutputStyle;
import org.hl7.fhir.r5.formats.XmlParser;
import org.hl7.fhir.r5.model.Bundle;
import org.hl7.fhir.r5.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r5.model.CodeSystem;
import org.hl7.fhir.r5.model.CodeType;
import org.hl7.fhir.r5.model.Constants;
import org.hl7.fhir.r5.model.DomainResource;
import org.hl7.fhir.r5.model.Element;
import org.hl7.fhir.r5.model.ValueSet;
import org.hl7.fhir.r5.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.r5.model.Enumerations.BindingStrength;
import org.hl7.fhir.r5.model.Enumerations.PublicationStatus;
import org.hl7.fhir.r5.model.SearchParameter;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.r5.terminologies.CodeSystemUtilities;
import org.hl7.fhir.r5.terminologies.ValueSetUtilities;
import org.hl7.fhir.r5.utils.ToolingExtensions;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xls.XLSXmlParser;
import org.hl7.fhir.utilities.xls.XLSXmlParser.Sheet;

public class ValueSetFinder {
  
  public static void main(String[] args) throws Exception {
    visitSearchParams(new File(args[0]));    
  }

  private static void visitSearchParams(File fd) throws Exception {
    for (File f : fd.listFiles()) {
      if (f.isDirectory()) {
        visitSearchParams(f);
      } else if (f.getName().endsWith("-search-params.xml")) {
        checkSearchParams(f);
      }
    }
  }

  private static void checkSearchParams(File f) throws Exception {
    String folder = Utilities.getDirectoryForFile(f.getAbsolutePath());
    System.out.println(f.getName());
    try {
      Set<String> s = new HashSet<>();
      for (File fsp : new File(folder).listFiles()) {
        if (fsp.getName().startsWith("searchparameter-")) {
          SearchParameter sp = (SearchParameter) new XmlParser().parseAndClose(new FileInputStream(fsp));
          s.add(sp.getId());
        }
      }

      for (File fig : new File(folder).listFiles()) {
        if (fig.getName().startsWith("implementationguide-")) {
          ImplementationGuide ig = (ImplementationGuide) new XmlParser().parseAndClose(new FileInputStream(fig));
          ig.getDefinition().getResource().removeIf(res -> res.getReference().getReference().startsWith("SearchParameter/") && !s.contains(res.getReference().getReference().substring(res.getReference().getReference().indexOf("/")+1)));
          if (ig.getDefinition().getResource().isEmpty()) {
            fig.delete();
          } else {
            new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(fig), ig);            
          }
        }
      }
    } catch (Exception e) { 
      System.out.println("  "+e.getMessage());
    }
  }
//  
//  
//
//  public static class ExtensionSorter implements Comparator<Extension> {
//
//    @Override
//    public int compare(Extension o1, Extension o2) {
//      if (!o1.getUrl().equals(o2.getUrl())) {
//        return o1.getUrl().compareTo(o2.getUrl());
//      } else if (o1.hasExtension() || o2.hasExtension()) {
//        return urls(o1).compareTo(urls(o2));
//      } else if (!o1.getValue().fhirType().equals(o2.getValue().fhirType())) {
//        return o1.getUrl().compareTo(o2.getUrl());
//      } else if (o1.getValue().isPrimitive()) {
//        return o1.getValue().primitiveValue().compareTo(o2.getValue().primitiveValue());
//      } else {
//        return 0;
//      }
//    }
//
//    private String urls(Extension src) {
//      List<String> urls = new ArrayList<>();
//      for (Extension ext : src.getExtension()) {
//        urls.add(ext.getUrl());
//      }
//      Collections.sort(urls);
//      return String.join("|", urls);
//    }
//  }
//
//  protected static void sortExtensions(Element e) {
//    e.getExtension().removeIf(ext -> !ext.hasValue() && !e.hasExtension());
//    Collections.sort(e.getExtension(), new ExtensionSorter());
//  }
//
//  protected static void sortExtensions(DomainResource e) {
//    e.getExtension().removeIf(ext -> !ext.hasValue() && !e.hasExtension());
//    Collections.sort(e.getExtension(), new ExtensionSorter());
//  }
  
}
