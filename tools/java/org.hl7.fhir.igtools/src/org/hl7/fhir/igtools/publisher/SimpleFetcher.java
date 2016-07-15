package org.hl7.fhir.igtools.publisher;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.hl7.fhir.dstu3.formats.FormatUtilities;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.ResourceType;
import org.hl7.fhir.dstu3.model.Type;
import org.hl7.fhir.dstu3.model.UriType;
import org.hl7.fhir.utilities.Utilities;

public class SimpleFetcher implements IFetchFile {

  private IGKnowledgeProvider pkp;
  
  @Override
  public void setPkp(IGKnowledgeProvider pkp) {
    this.pkp = pkp;
  }

  @Override
  public FetchedFile fetch(String path) throws Exception {
    File f = new File(path);
    if (!f.exists())
      throw new Exception("Unable to find file "+path);
    FetchedFile ff = new FetchedFile();
    ff.setPath(path);
    ff.setName(Utilities.fileTitle(path));
    ff.setTime(f.lastModified());
    if (f.isDirectory()) {
      ff.setContentType("application/directory");
      ff.setFolder(true);   
      for (File fl : f.listFiles())
        ff.getFiles().add(fl.getAbsolutePath());
    } else {
      ff.setFolder(false);   
      if (path.endsWith("json"))
        ff.setContentType("application/json+fhir");
      else if (path.endsWith("xml"))
        ff.setContentType("application/xml+fhir");

      InputStream ss = new FileInputStream(f);
      byte[] b = new byte[ss.available()];
      ss.read(b, 0, ss.available());
      ff.setSource(b);
      ss.close();
    }
    return ff;
  }

  @Override
  public FetchedFile fetchFlexible(String path) throws Exception {
    File f = new File(path+".xml");
    if (!f.exists())
      f = new File(path+".json");
    if (!f.exists())
      throw new Exception("Unable to find file "+path+".xml/json");
    FetchedFile ff = new FetchedFile();
    ff.setPath(path);
    ff.setName(Utilities.fileTitle(path));
    ff.setTime(f.lastModified());
    if (f.getName().endsWith("json"))
      ff.setContentType("application/json+fhir");
    else if (f.getName().endsWith("xml"))
      ff.setContentType("application/xml+fhir");
    
    InputStream ss = new FileInputStream(f);
    byte[] b = new byte[ss.available()];
    ss.read(b, 0, ss.available());
    ff.setSource(b);
    ss.close();
    return ff;
  }

  @Override
  public boolean canFetchFlexible(String path) throws Exception {
    File f = new File(path+".xml");
    if (!f.exists())
      f = new File(path+".json");
    return f.exists();
  }

  @Override
  public FetchedFile fetch(Type source, FetchedFile src) throws Exception {
    if (source instanceof Reference) {
      String s = ((Reference)source).getReference();
      if (!s.contains("/"))
        throw new Exception("Bad Source Reference '"+s+"' - should have the format [Type]/[id]");
      String type = s.substring(0,  s.indexOf("/"));
      String id = s.substring(s.indexOf("/")+1); 
      try {
        ResourceType rt = ResourceType.fromCode(type);
      } catch (Exception e) {
        throw new Exception("Bad Source Reference '"+s+"' - should have the format [Type]/[id] where Type is a valid resource type");
      }
      if (!id.matches(FormatUtilities.ID_REGEX))
        throw new Exception("Bad Source Reference '"+s+"' - should have the format [Type]/[id] where id is a valid FHIR id type");
      String fn = pkp.getSourceFor(type+"/"+id);
      if (Utilities.noString(fn)) {
        // no source in the json file.
        fn = Utilities.path(Utilities.getDirectoryForFile(src.getPath()), type.toLowerCase()+"-"+id+".xml");
        if (!exists(fn))
          fn = Utilities.path(Utilities.getDirectoryForFile(src.getPath()), type.toLowerCase()+"-"+id+".json");
        if (!exists(fn))
          fn = Utilities.path(Utilities.getDirectoryForFile(src.getPath()), id+".xml");
        if (!exists(fn))
          fn = Utilities.path(Utilities.getDirectoryForFile(src.getPath()), id+".json");
        if (!exists(fn))
          throw new Exception("Unable to find the source file for "+type+"/"+id+": not specified, so tried "+type.toLowerCase()+"-"+id+".xml, "+type.toLowerCase()+"-"+id+".xml, "+id+".xml, and "+id+".json");
      } else {
        fn = Utilities.path(Utilities.getDirectoryForFile(src.getPath()), fn);
        if (!exists(fn))
          throw new Exception("Unable to find the source file for "+type+"/"+id+" at "+fn);
      }
      return fetch(fn); 
    } else if (source instanceof UriType) {
      UriType s = (UriType) source;
      String fn = Utilities.path(Utilities.getDirectoryForFile(src.getPath()), s.getValueAsString());
      return fetch(fn); 
    } else {
      throw new Exception("Unknown source reference type for implementation guide");
    }
  }

  private boolean exists(String fn) {
    return new File(fn).exists();
  }

}
