package org.hl7.fhir.igtools.publisher;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r5.context.IWorkerContext;
import org.hl7.fhir.r5.elementmodel.Element;
import org.hl7.fhir.r5.elementmodel.Manager;
import org.hl7.fhir.r5.elementmodel.Manager.FhirFormat;
import org.hl7.fhir.r5.elementmodel.ObjectConverter;
import org.hl7.fhir.r5.model.CodeSystem;
import org.hl7.fhir.r5.model.OperationDefinition;
import org.hl7.fhir.r5.model.Questionnaire;
import org.hl7.fhir.r5.model.Resource;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.r5.model.ValueSet;
import org.hl7.fhir.r5.terminologies.ImplicitValueSets;
import org.hl7.fhir.r5.utils.IResourceValidator.IValidatorResourceFetcher;
import org.hl7.fhir.r5.utils.IResourceValidator.ReferenceValidationPolicy;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.cache.NpmPackage;
import org.hl7.fhir.utilities.cache.PackageCacheManager;

public class ValidationServices implements IValidatorResourceFetcher {

  private IWorkerContext context;
  private IGKnowledgeProvider ipg;
  private List<FetchedFile> files;
  private List<NpmPackage> packages;
  private List<String> otherUrls = new ArrayList<>();
  
  
  public ValidationServices(IWorkerContext context, IGKnowledgeProvider ipg, List<FetchedFile> files, List<NpmPackage> packages) {
    super();
    this.context = context;
    this.ipg = ipg;
    this.files = files;
    this.packages = packages;
    initOtherUrls();
  }

  @Override
  public Element fetch(Object appContext, String url) throws FHIRException, IOException {
    if (url == null)
      return null;
    String turl = (!Utilities.isAbsoluteUrl(url)) ? Utilities.pathURL(ipg.getCanonical(), url) : url;
    Resource res = context.fetchResource(getResourceType(turl), turl);
    if (res != null) {
      Element e = (Element)res.getUserData("element");
      if (e!=null)
        return e;
      else
        return new ObjectConverter(context).convert(res);
    }

    ValueSet vs = ImplicitValueSets.build(url);
    if (vs != null)
      return new ObjectConverter(context).convert(vs);
    
    for (NpmPackage npm : packages) {
      if (npm.canonical() != null && url.startsWith(npm.canonical())) {
        String u = url.substring(npm.canonical().length());
        if (u.startsWith("/"))
          u = u.substring(1);
        String[] ul = u.split("\\/");
        InputStream s = npm.loadResource(ul[0], ul[1]);
        if (s != null)
          return Manager.makeParser(context, FhirFormat.JSON).parse(s);
      }
    }
    String[] parts = url.split("\\/");
    
    if (appContext != null) {
      Element bnd = (Element) appContext;
      int count = 0;
      for (Element be : bnd.getChildren("entry")) {
        count++;
        Element ber = be.getNamedChild("resource");
        if (ber != null) {
          if (!be.hasChild("fullUrl")) {
            String bundleId = ((Element) appContext).getChildValue("id");
            throw new FHIRException("No fullUrl on entry #" + count + " in Bundle " + bundleId);
          }
          if (be.getChildByName("fullUrl").equals(url))
            return ber;
          if (parts.length == 2 && ber.fhirType().equals(parts[0]) && ber.hasChild("id") && ber.getChildValue("id").equals(parts[1])) 
            return ber;
        }        
      }
    }
    
    if (!Utilities.isAbsoluteUrl(url) || url.startsWith(ipg.getCanonical())) {
      if (parts.length == 2) {
        for (FetchedFile f : files) {
          for (FetchedResource r : f.getResources()) {
            if (r.getElement().fhirType().equals(parts[parts.length-2]) && r.getId().equals(parts[parts.length-1]))
              return r.getElement();
          }
        }
      }
    }
    
    if (Utilities.isAbsoluteUrl(url)) {
      for (FetchedFile f : files) {
        for (FetchedResource r : f.getResources()) {
          if (r.getElement().fhirType().equals("Bundle")) {
            for (Element be : r.getElement().getChildren("entry")) {
              Element ber = be.getNamedChild("resource");
              if (ber != null) {
                if (be.getChildValue("fullUrl").equals(url))
                  return ber;
              }
            }
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
    if (url.contains("/OperationDefinition/"))
      return OperationDefinition.class;
    if (url.contains("/Questionnaire/"))
      return Questionnaire.class;
    return null;
  }


  @Override
  public ReferenceValidationPolicy validationPolicy(Object appContext, String path, String url) {
    return ReferenceValidationPolicy.CHECK_EXISTS_AND_TYPE;
  }


  @Override
  public boolean resolveURL(Object appContext, String path, String url) throws IOException {
    if (otherUrls.contains(url))
      return true;
    
    if (url.startsWith("http://hl7.org/fhir"))
      try {
        return context.fetchResourceWithException(Resource.class, url) != null;
      } catch (FHIRException e) {
        return false;
      }
    // todo: what to do here?
    return true;
  }

  public List<String> getOtherUrls() {
    return otherUrls;
  }

  public void initOtherUrls() {
    otherUrls.clear();
    otherUrls.add("http://hl7.org/fhir/sid/us-ssn");
    otherUrls.add("http://hl7.org/fhir/sid/us-npi");
    otherUrls.add("http://hl7.org/fhir/sid/icd-10-vn");
    otherUrls.add("http://hl7.org/fhir/w5");
  }

}
