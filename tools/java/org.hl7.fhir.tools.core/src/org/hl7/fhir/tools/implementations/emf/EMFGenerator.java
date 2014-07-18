package org.hl7.fhir.tools.implementations.emf;
import java.util.Date;
import java.util.List;
import org.hl7.fhir.definitions.Config;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.instance.utils.Version;
import org.hl7.fhir.tools.implementations.emf.EMFStructureGenerator.OOGenClass;
import org.hl7.fhir.tools.publisher.PlatformGenerator;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;


public class EMFGenerator extends EMFBase implements PlatformGenerator {

  public EMFGenerator() {
    super();
  }

  @Override
  public String getName() {
    return "emf";
  }

  @Override
  public String getDescription() {
    return "ECore (e.g. UML derivative) - in ECore text format";
  }

  @Override
  public void generate(Definitions definitions, String destDir, String implDir, String version, Date genDate, Logger logger, String svnRevision) throws Exception {

    init(null);
    
    write("package fhir fhir \"http://hl7.org/fhir/emf\" {");
    write("// version "+version+"."+svnRevision+", generated "+ Config.DATE_FORMAT().format(genDate));
    write("");
    
    writeDirect(TextFile.fileToString(Utilities.path(implDir, "rm.txt")));
    
    for (String n : definitions.getInfrastructure().keySet()) {
      ElementDefn root = definitions.getInfrastructure().get(n); 
      EMFStructureGenerator jgen = new EMFStructureGenerator(w, definitions);
      jgen.generate(root, root.getName(), definitions.getBindings(), OOGenClass.Structure, null);
    }
    
    for (String n : definitions.getTypes().keySet()) {
      ElementDefn root = definitions.getTypes().get(n); 
      EMFStructureGenerator jgen = new EMFStructureGenerator(w, definitions);
      jgen.generate(root, root.getName(), definitions.getBindings(), OOGenClass.Type, null);
    }
    
    for (ProfiledType cd : definitions.getConstraints().values()) {
      ElementDefn root = definitions.getTypes().get(cd.getBaseType()); 
      EMFStructureGenerator jgen = new EMFStructureGenerator(w, definitions);
      jgen.generate(root, cd.getName(), definitions.getBindings(), OOGenClass.Constraint, cd);
    }
    
    for (String n : definitions.getStructures().keySet()) {
      ElementDefn root = definitions.getStructures().get(n); 
      EMFStructureGenerator jgen = new EMFStructureGenerator(w, definitions);
      jgen.generate(root, root.getName(), definitions.getBindings(), OOGenClass.Type, null);
    }
    
    
    for (String n : definitions.sortedResourceNames()) {
      ResourceDefn root = definitions.getResourceByName(n); 
      EMFStructureGenerator jrg = new EMFStructureGenerator(w, definitions);
      jrg.generate(root.getRoot(), root.getName(), definitions.getBindings(), OOGenClass.Resource, null);
    }
    w.write("}");
    w.close();
    
    TextFile.stringToFile(b.toString(), Utilities.path(destDir, "emf.txt"));
  }

  @Override
  public String getTitle() {
    return "ECore";
  }

  @Override
  public boolean isECoreGenerator() {
    return false;
  }

  @Override
  public void generate(org.hl7.fhir.definitions.ecore.fhir.Definitions definitions, String destDir, String implDir, String version, Date genDate, Logger logger, String svnRevision) throws Exception {    
    throw new UnsupportedOperationException("EMF generator uses ElementDefn-style definitions.");	
  }

  @Override
  public boolean doesCompile() {
    return false; // ToolProvider.getSystemJavaCompiler() != null;
  }

   
  @Override
  public boolean compile(String rootDir, List<String> errors, Logger logger) throws Exception {
    throw new UnsupportedOperationException("not implemented in EMF Generator");  
  }

  
 
  @Override
  public boolean doesTest() {
    return false;
  }

  @Override
  public void loadAndSave(String rootDir, String sourceFile, String destFile) throws Exception {
    throw new UnsupportedOperationException("not implemented in EMF Generator");  
  }

  public String convertToJson(String rootDir, String sourceFile, String destFile) throws Exception {
    throw new UnsupportedOperationException("not implemented in EMF Generator");  
  }

  @Override
  public String checkFragments(String rootDir, String fragments, boolean inProcess) throws Exception {
    throw new UnsupportedOperationException("not implemented in EMF Generator");  
  }

  @Override
  public String getVersion() {
    return Version.VERSION; // this has to be hard coded, but we'll fetch if later from the client and check that it's correct
  }

  @Override
  public String getReference(String version) {
    return "emf.txt";
  }
  
  @Override
  public boolean wantListAsDownload() {
    return false;
  }


}
