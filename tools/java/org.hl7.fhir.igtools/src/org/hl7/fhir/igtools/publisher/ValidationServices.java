package org.hl7.fhir.igtools.publisher;

import java.io.IOException;
import java.util.List;

import org.hl7.fhir.dstu3.context.IWorkerContext;
import org.hl7.fhir.dstu3.elementmodel.Element;
import org.hl7.fhir.dstu3.elementmodel.ObjectConverter;
import org.hl7.fhir.dstu3.model.CodeSystem;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.model.ValueSet;
import org.hl7.fhir.dstu3.utils.IResourceValidator.IValidatorResourceFetcher;
import org.hl7.fhir.dstu3.utils.IResourceValidator.ReferenceValidationPolicy;
import org.hl7.fhir.exceptions.DefinitionException;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.exceptions.FHIRFormatError;
import org.hl7.fhir.utilities.Utilities;

public class ValidationServices implements IValidatorResourceFetcher {

  private IWorkerContext context;
  private IGKnowledgeProvider ipg;
  private List<FetchedFile> files;
  
  
  public ValidationServices(IWorkerContext context, IGKnowledgeProvider ipg, List<FetchedFile> files) {
    super();
    this.context = context;
    this.ipg = ipg;
    this.files = files;
  }


  @Override
  public Element fetch(Object appContext, String url) throws FHIRFormatError, DefinitionException, IOException {
    String turl = (!Utilities.isAbsoluteUrl(url)) ? Utilities.pathReverse(ipg.getCanonical(), url) : url;
    Resource res = context.fetchResource(getResourceType(turl), turl);
    if (res != null) {
      Element e = (Element)res.getUserData("element");
      if (e!=null)
        return e;
      else
        return new ObjectConverter(context).convert(res);
    }
   
    String[] parts = url.split("\\/");
    
    if (appContext != null) {
      Element bnd = (Element) appContext;
      for (Element be : bnd.getChildren("entry")) {
        Element ber = be.getNamedChild("resource");
        if (ber != null) {
          if (be.getChildByName("fullUrl").equals(url))
            return ber;
          if (parts.length == 2 && ber.fhirType().equals(parts[0]) && ber.getChildValue("id").equals(parts[1])) 
            return ber;
        }        
      }
    }
    
    if (!Utilities.isAbsoluteUrl(url)  || url.startsWith(ipg.getCanonical())) {
      if (parts.length == 2) {
        for (FetchedFile f : files) {
          for (FetchedResource r : f.getResources()) {
            if (r.getElement().fhirType().equals(parts[parts.length-2]) && r.getId().equals(parts[parts.length-1]))
              return r.getElement();
          }
        }
      }
    }
    return null;
  }


  private Class getResourceType(String url) {
    if (url.contains("/ValueSet/"))
      return ValueSet.class;
    if (url.contains("/StructureDefinition/"))
      return StructureDefinition.class;
    if (url.contains("/CodeSystem/"))
      return CodeSystem.class;
    return null;
  }


  @Override
  public ReferenceValidationPolicy validationPolicy(Object appContext, String path, String url) {
    return ReferenceValidationPolicy.CHECK_EXISTS_AND_TYPE;
  }


  @Override
  public boolean resolveURL(Object appContext, String path, String url) throws IOException, FHIRException {
    // todo: what to do here?
    return true;
  }

}
