package org.hl7.fhir.igtools.publisher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.validation.ValidationMessage;

public class FetchedFile {
  private String path;
  private String name;
  
  private byte[] source;
  private long hash;
  private long time;
  private String contentType;
  private List<FetchedFile> dependencies;
  private List<FetchedResource> resources = new ArrayList<FetchedResource>();
  private List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
  private Bundle bundle;
  private List<String> valuesetsToLoad = new ArrayList<String>();
  private boolean folder;
  private List<String> files; // if it's a folder
  private boolean noProcess;
  private Set<String> outputNames = new HashSet<String>();

  public String getPath() {
    return path;
  }
  public void setPath(String path) {
    this.path = path;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public long getTime() {
    return time;
  }
  public void setTime(long time) {
    this.time = time;
  }
  public String getContentType() {
    return contentType;
  }
  public void setContentType(String contentType) {
    this.contentType = contentType;
  }
 
  public List<FetchedFile> getDependencies() {
    return dependencies;
  }
  public void setDependencies(List<FetchedFile> dependencies) {
    this.dependencies = dependencies;
  }
  public long getHash() {
    return hash;
  }
  public void setHash(long hash) {
    this.hash = hash;
  }
  public byte[] getSource() {
    if (source == null)
      throw new Error("Source has been dropped");
    return source;
  }
  public void setSource(byte[] source) {
    this.source = source;
    this.hash =Arrays.hashCode(source);
  }
  
  public void dropSource() {
    source = null;  
  }
  public List<FetchedResource> getResources() {
    return resources;
  }
  public FetchedResource addResource() {
    FetchedResource r = new FetchedResource();
    resources.add(r);
    return r;
  }
  public List<ValidationMessage> getErrors() {
    return errors;
  }
  public Bundle getBundle() {
    return bundle;
  }
  public void setBundle(Bundle bundle) {
    this.bundle = bundle;
  }
  public List<String> getValuesetsToLoad() {
    return valuesetsToLoad;
  }
  public boolean isFolder() {
    return folder;
  }
  public void setFolder(boolean folder) {
    this.folder = folder;
  }
  public List<String> getFiles() {
    if (files == null)
      files = new ArrayList<String>();
    return files;
  }
  public boolean isNoProcess() {
    return noProcess;
  }
  public void setNoProcess(boolean noProcess) {
    this.noProcess = noProcess;
  }
  
  public Set<String> getOutputNames() {
    return outputNames;
  }
 
}