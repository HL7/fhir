package org.hl7.fhir.igtools.publisher;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.hl7.fhir.r5.context.IWorkerContext;
import org.hl7.fhir.r5.context.IWorkerContext.ILoggingService;
import org.hl7.fhir.r5.formats.FormatUtilities;
import org.hl7.fhir.r5.model.CanonicalType;
import org.hl7.fhir.r5.model.Reference;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.r5.model.Type;
import org.hl7.fhir.r5.model.UriType;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

public class ZipFetcher implements IFetchFile {

  private static final org.slf4j.Logger ourLog = org.slf4j.LoggerFactory.getLogger(ZipFetcher.class);
  private Map<String, FetchedFile> myFiles = new HashMap<>();
  private ILoggingService myLogger;
  private IGKnowledgeProvider myPkp;
  private List<String> myResourceDirs;

  public ZipFetcher(byte[] theZipFile) {
    ourLog.info("Beginning parsing ZIP file ({} bytes)", theZipFile.length);

    try (ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(theZipFile))) {

      Map<String, FetchedFile> dirs = new HashMap<String, FetchedFile>();
      while (true) {
        ZipEntry entry = zis.getNextEntry();
        if (entry == null) {
          ourLog.info("No more entries");
          break;
        }

        String entryName = entry.getName();
		  ourLog.info("Found entry: {}", entryName);

        FetchedFile ff = new FetchedFile();
        ff.setPath(entryName);
        ff.setName(SimpleFetcher.fileTitle(entryName));
        ff.setTime(entry.getTime());
        if (entry.isDirectory()) {
          ff.setContentType("application/directory");
          ff.setFolder(true);
          if (entryName.endsWith("/")) {
            entryName = entryName.substring(0, entryName.length() - 1);
            ff.setPath(entryName);
          }
          dirs.put(entryName, ff);
          
          // TODO: work this in
          // for (File fl : f.listFiles())
          // ff.getFiles().add(fl.getCanonicalPath());
        } else {
          ff.setFolder(false);
          if (entryName.endsWith("json")) {
            ff.setContentType("application/fhir+json");
          } else if (entryName.endsWith("xml")) {
            ff.setContentType("application/fhir+xml");
          }
          byte[] bytes = IOUtils.toByteArray(zis);
          ff.setSource(bytes);
        }
        if (entryName.contains("/"))
          dirs.get(entryName.substring(0, entryName.lastIndexOf("/"))).getFiles().add(entryName);

        myFiles.put(normalisePath(entryName), ff);
      }

    } catch (IOException e) {
      // should not happen
      throw new Error(e);
    }
  }

  @Override
  public FetchedFile fetch(String thePath) throws Exception {
    FetchedFile retVal = myFiles.get(normalisePath(thePath));
    if (retVal == null) {
      throw new Exception("Unknown resource: " + thePath);
    }
    return retVal;
  }

  public FetchedFile findDefinitionFile() throws Exception {
    for (Entry<String, FetchedFile> next : myFiles.entrySet()) {
      if (next.getKey().indexOf('/') == -1) {
        if (next.getKey().endsWith("json")) {
          return next.getValue();
        }
      }
    }
    
    throw new Exception("Can not find IG file in ZIP - Have files: " + myFiles);
  }
  
  @Override
  public FetchedFile fetchFlexible(String thePath) throws Exception {
    FetchedFile retVal = findResourceFileOrNull(thePath);
    if (retVal == null) {
      retVal = findResourceFileOrNull(thePath + ".xml");
    }
    if (retVal == null) {
      retVal = findResourceFileOrNull(thePath + ".json");
    }
    if (retVal == null) {
      throw new Exception("Unknown resource: " + thePath);
    }
    return retVal;
  }

  @Override
  public boolean canFetchFlexible(String thePath) throws Exception {
    try {
      fetchFlexible(thePath);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public FetchedFile fetch(Type source, FetchedFile src) throws Exception {
    if (source instanceof Reference || source instanceof CanonicalType) {
      String s = source instanceof CanonicalType ? source.primitiveValue() : ((Reference)source).getReference();
      if (!s.contains("/"))
        throw new Exception("Bad Source Reference '"+s+"' - should have the format [Type]/[id]");
      String type = s.substring(0,  s.indexOf("/"));
      String id = s.substring(s.indexOf("/")+1); 
      if (!myPkp.getContext().hasResource(StructureDefinition.class , "http://hl7.org/fhir/StructureDefinition/"+type))
        throw new Exception("Bad Resource Identity - should have the format [Type]/[id] where Type is a valid resource type:" + s);
      if (!id.matches(FormatUtilities.ID_REGEX))
        throw new Exception("Bad Source Reference '"+s+"' - should have the format [Type]/[id] where id is a valid FHIR id type");
      String fn = myPkp.getSourceFor(type+"/"+id);
      List<String> dirs = new ArrayList<>();
      dirs.add(src.getPath());
      dirs.addAll(myResourceDirs);
      
      if (Utilities.noString(fn)) {
        // no source in the json file.
        fn = findFile(dirs, type.toLowerCase()+"-"+id+".xml");
        if (fn == null)
          fn = findFile(dirs, type.toLowerCase()+"-"+id+".json");
        if (fn == null)
          fn = findFile(dirs, type.toLowerCase()+"/"+id+".xml");
        if (fn == null)
          fn = findFile(dirs, type.toLowerCase()+"/"+id+".json");
        if (fn == null)
          fn = findFile(dirs, id+".xml");
        if (fn == null)
          fn = findFile(dirs, id+".json");
        if (fn == null)
          throw new Exception("Unable to find the source file for "+type+"/"+id+": not specified, so tried "+type.toLowerCase()+"-"+id+".xml, "+type.toLowerCase()+"-"+id+".json, "+type.toLowerCase()+"/"+id+".xml, "+type.toLowerCase()+"/"+id+".json, "+id+".xml, and "+id+".json in dirs "+dirs.toString());
      } else {
        fn = findFile(dirs, fn);
        if (!myFiles.containsKey(normalisePath(fn)))
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

  String findFile(List<String> dirs, String name) throws IOException {
    for (String dir : dirs) {
      String fn = Utilities.path(dir, name);
      if (myFiles.containsKey(normalisePath(fn)))
        return fn;
    }
    return null;
  }

  @Override
  public FetchedFile fetchResourceFile(String theName) throws Exception {
    FetchedFile retVal = findResourceFileOrNull(theName);
    if (retVal != null) {
      return retVal;
    }
    throw new Exception("Can't find resource: " + theName);
  }

  private FetchedFile findResourceFileOrNull(String theName) {
    FetchedFile retVal = null;
    for (String next : myResourceDirs) {
      FetchedFile file = myFiles.get(normalisePath(next + "/" + theName));
      if (file != null) {
        retVal = file;
      }
    }
    return retVal;
  }

  @Override
  public void setPkp(IGKnowledgeProvider thePkp) {
    myPkp = thePkp;
  }

  @Override
  public List<FetchedFile> scan(String theSourceDir, IWorkerContext theContext, boolean autoScan) throws IOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public ILoggingService getLogger() {
    return myLogger;
  }

  @Override
  public void setLogger(ILoggingService theLog) {
    myLogger = theLog;
  }

  @Override
  public void setResourceDirs(List<String> theResourceDirs) {
    myResourceDirs = theResourceDirs;
  }

  @Override
  public FetchState check(String path) {
    FetchedFile ff = myFiles.get(normalisePath(path));
    if (ff == null)
      return FetchState.NOT_FOUND;
	 else if (ff.getContentType() == null) {
		ourLog.warn("No content type on path {}", path);
      return FetchState.FILE;
	 } else if (ff.getContentType().equals("application/directory"))
      return FetchState.DIR;
    else
      return FetchState.FILE;
  }

  @Override
  public String pathForFile(String path) {
    return path == null ? null : Utilities.getDirectoryForFile(path);
  }

  @Override
  public InputStream openAsStream(String filename) throws FileNotFoundException {
    FetchedFile ff = myFiles.get(normalisePath(filename));
    if (ff == null)
      throw new FileNotFoundException("Unable to find file "+filename+" in the uploaded content");
    return new ByteArrayInputStream(ff.getSource());
  }

  @Override
  public String openAsString(String filename) throws IOException {
    FetchedFile ff = myFiles.get(normalisePath(filename));
    if (ff == null)
      throw new FileNotFoundException("Unable to find file "+filename+" in the uploaded content");
    return TextFile.bytesToString(ff.getSource());
  }

  private String normalisePath(String filename) {
    return filename.replace("\\", "/");
  }

}
