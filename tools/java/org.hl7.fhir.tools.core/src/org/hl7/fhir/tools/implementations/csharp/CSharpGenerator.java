package org.hl7.fhir.tools.implementations.csharp;
/*
Copyright (c) 2011-2014, HL7, Inc
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, 
are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this 
   list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
   this list of conditions and the following disclaimer in the documentation 
   and/or other materials provided with the distribution.
 * Neither the name of HL7 nor the names of its contributors may be used to 
   endorse or promote products derived from this software without specific 
   prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.

*/
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.ConstrainedTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.ResourceDefn;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.instance.utils.Version;
import org.hl7.fhir.tools.implementations.BaseGenerator;
import org.hl7.fhir.tools.implementations.GeneratorUtils;
import org.hl7.fhir.tools.publisher.DotNetFramework;
import org.hl7.fhir.tools.publisher.DotNetFramework.DotNetCompileResult;
import org.hl7.fhir.tools.publisher.PlatformGenerator;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.Logger.LogMessageType;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ZipGenerator;

public class CSharpGenerator extends BaseGenerator implements PlatformGenerator {

	@Override
	public void generate(Definitions definitions, String destDir,
			String implDir, String version, Date genDate, Logger logger, String svnRevision)
			throws Exception {

		throw new UnsupportedOperationException("The C# generator uses eCore, not ElementDefn-style definition.");
	}

	@Override
	public String getName() {
		return "csharp";
	}

	  
	@Override
	public String getDescription() {
		return "Object models, Parsers/Serialisers, Validators, and a Client. The source code for that compiled .NET library can be found on GitHub at " +
				"[[http://github.com/ewoutkramer/fhir-net-api]]";
	}

	@Override
	public String getTitle() {
		return "csharp";
	}

	
	@Override
  public String getVersion() {
    return Version.VERSION;
  }

  @Override
  public String getReference(String version) {
    return "http://www.nuget.org/packages/Hl7.Fhir";
  }

  public String getZipFilename(String version) {
    return super.getReference(version);
  }
 
  @Override
	public boolean isECoreGenerator() {
		return true;
	}

  private Logger logger = null;
  
  
	@Override
	public void generate(org.hl7.fhir.definitions.ecore.fhir.Definitions definitions, String destDir, String implDir, String version, Date genDate, Logger logger, String svnRevision) throws Exception {

	  this.logger = logger;
  
		char sl = File.separatorChar;
		
		String modelProjectDir = "Hl7.Fhir.Model" + sl;
		String projectDir = implDir + modelProjectDir + sl;
		String generationDir = "Generated" + sl;
		String introspectionDir = "Introspection" + sl;
    String propertiesDir = "Properties" + sl;
		String validationDir = "Validation" + sl;

		File f = new CSFile(projectDir);	if( !f.exists() ) f.mkdir();
		f = new CSFile(projectDir + generationDir);  if( !f.exists() ) f.mkdir();
		
		List<String> generatedFilenames = new ArrayList<String>();

		{
			String enumsFilename = generationDir + "Bindings.cs";
		
			new CSharpModelGenerator(definitions)
				.generateGlobalEnums(definitions.getBinding()).toFile(projectDir+enumsFilename);						 
			generatedFilenames.add(enumsFilename);
		}

		{
			String filename = generationDir + "ModelInfo.cs";
			 new CSharpModelInformationGenerator(definitions).generateInformation().toFile(projectDir+filename);						 
			generatedFilenames.add(filename);
		}
			
		List<CompositeTypeDefn> allComplexTypes = new ArrayList<CompositeTypeDefn>();
		allComplexTypes.addAll(definitions.getLocalCompositeTypes());
			
		for( ResourceDefn res : definitions.getResources())
		  if(!res.isFuture()) allComplexTypes.add(res);
		
		for( CompositeTypeDefn composite : allComplexTypes )
		{		  
		  // Generate model for all other classes
			String compositeFilename = generationDir + GeneratorUtils.generateCSharpTypeName(composite.getName()) + ".cs";	
			new CSharpModelGenerator(definitions)
				.generateComposite(composite).toFile(projectDir + compositeFilename);		
			generatedFilenames.add(compositeFilename);
		}
	
		for( ConstrainedTypeDefn constrained : definitions.getLocalConstrainedTypes() )
		{
			// Build C# class for constrained type
			String constrainedFilename = generationDir + constrained.getName() + ".cs";
			new CSharpModelGenerator(definitions)
				.generateConstrained(constrained).toFile(projectDir+constrainedFilename);						 
			generatedFilenames.add(constrainedFilename);			
		}
		
    // Generate C# project file & update assembly version
    CSharpProjectGenerator.buildProjectFile(projectDir, generatedFilenames);
    CSharpProjectGenerator.setAssemblyVersionInProperties(projectDir + propertiesDir, definitions.getVersion(), svnRevision);

    ZipFilename = getZipFilename(version);
    ZipGenerator zip = new ZipGenerator(destDir+ZipFilename);
		//ZipGenerator zip = new ZipGenerator(destDir + CSHARP_FILENAME);
		
	  // Zip Hl7.Fhir.Model directory	
    zip.addFolder(projectDir+generationDir, modelProjectDir + generationDir, false);
		zip.addFolder(projectDir+introspectionDir, modelProjectDir + introspectionDir, false);
		zip.addFolder(projectDir+validationDir, modelProjectDir + validationDir, false);

		zip.addFiles(projectDir+propertiesDir, modelProjectDir + propertiesDir, ".cs", null);
		zip.addFiles(projectDir, modelProjectDir, ".csproj", null);
		zip.addFiles(projectDir, modelProjectDir, ".cs", null);
    zip.addFiles(projectDir, modelProjectDir, "packages.config", null);
    	
		// Zip Hl7.Fhir.Model.Tests directory
		String testProjectDir = "Hl7.Fhir.Model.Tests" + sl;
		zip.addFiles(implDir+testProjectDir, testProjectDir, ".cs", null);
		zip.addFiles(implDir+testProjectDir + "Properties" + sl, testProjectDir+"Properties"+sl, ".cs", null);
		zip.addFiles(implDir+testProjectDir, testProjectDir, ".csproj", null);
		zip.addFiles(implDir+testProjectDir, testProjectDir, "packages.config", null);
		
		// Add cross-project files
    zip.addFiles(implDir, "", ".sln", null);
    zip.addFiles(implDir, "", "README.txt", null);
    zip.addFiles(implDir + "packages" + sl, "packages" + sl, "repositories.config", null);
		
		zip.close();		
	}

  @Override
public boolean doesCompile() {
    return true;
  }

  
  private String ZipFilename;
  
  @Override
  public boolean compile(String rootDir, List<String> errors, Logger logger) 
  {  
    String solutionDirectory = Utilities.path(rootDir, "implementations", "csharp");
    String solutionFile = Utilities.path(solutionDirectory, "Hl7.Fhir.sln");
    DotNetCompileResult result = DotNetFramework.compile(solutionFile, this.logger);

    // If result == null, the compile function will have logged the reason
    if( result == null )
      return false;    

    // If there was an error, print the message
    else if(result.exitValue != 0)
    {
      logger.log(result.message, LogMessageType.Error);
      return false;
    }
   
    return addCompiledAssemblyToCsharpZip(rootDir, solutionDirectory, ZipFilename);    
  }

  private boolean addCompiledAssemblyToCsharpZip(String rootDir, String solutionDirectory, String zipFilename) {
    // Try to add the newly compiled assembly to the distribution zip
    String csharpZip = Utilities.path(rootDir, "publish", zipFilename );
    String assemblyDirectory = Utilities.path(solutionDirectory,"Hl7.Fhir.Model","bin","Release","Net40");
    String tempZip = csharpZip + "_temp";
    
    File origZipFile = new File(csharpZip);
    File tempZipFile = new File(tempZip);
    
    try
    {     
      if( origZipFile.renameTo(tempZipFile) == false )
      {
        logger.log("Failed to rename CSharp.zip to a temporary file. Is it locked?", LogMessageType.Error);
        return false;
      }
      
      char sl = File.separatorChar;
      ZipGenerator zip = new ZipGenerator(csharpZip);
      zip.addFromZip(tempZip);
      zip.addFolder(assemblyDirectory + sl, "bin" + sl + "Net40" + sl, false);
      zip.close();
    }
    catch(Exception e)
    {
      logger.log("Failed to add compiled assembly to csharp distribution zip: " + e.getMessage(), LogMessageType.Error);
      return false;
    }
    finally
    {
      tempZipFile.delete();
    }
    
    return true;
  }

  @Override
public boolean doesTest() {
    return false;
  }

  @Override
public void loadAndSave(String rootDir, String sourceFile, String destFile) {
  }

  @Override
public String checkFragments(String rootDir, String fragments, boolean inProcess) throws Exception {
    return "Not supported by C# implementation";
  }
}
