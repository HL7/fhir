package org.hl7.fhir.tools.implementations.csharp;
/*
Copyright (c) 2011-2013, HL7, Inc
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

import org.hl7.fhir.definitions.ecore.fhir.BindingDefn;
import org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.ConstrainedTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.NameScope;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.tools.implementations.BaseGenerator;
import org.hl7.fhir.tools.implementations.GeneratorUtils;
import org.hl7.fhir.tools.publisher.DotNetFramework;
import org.hl7.fhir.tools.publisher.DotNetFramework.DotNetCompileResult;
import org.hl7.fhir.tools.publisher.PlatformGenerator;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.Logger;
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
		return "Resource definitions, XML & Json parsers, validation and a FHIR Client API. The reference implementation uses the standard .NET framework and only Json.NET as a third-party assembly.";
	}

	@Override
	public String getTitle() {
		return "C#";
	}

	@Override
	public boolean isECoreGenerator() {
		return true;
	}

  private Logger logger = null;
  
	@Override
	public void generate(org.hl7.fhir.definitions.ecore.fhir.Definitions definitions, String destDir,
			String implDir, Logger logger) throws Exception {

	  this.logger = logger;

		char sl = File.separatorChar;
		String modelDir = "Model" + sl;
		String parsersDir = "Parsers" + sl;
		String serializersDir = "Serializers" + sl;
		
		File f = new CSFile(implDir + modelDir);	if( !f.exists() ) f.mkdir();
		File p = new CSFile(implDir + parsersDir);	if( !p.exists() ) p.mkdir();
		File s = new CSFile(implDir + serializersDir);	if( !s.exists() ) s.mkdir();
	
		
		List<String> generatedFilenames = new ArrayList<String>();

		{
			String enumsFilename = modelDir + "Bindings.cs";
		
			new CSharpModelGenerator(definitions)
				.generateGlobalEnums(definitions.getBinding()).toFile(implDir+enumsFilename);						 
			generatedFilenames.add(enumsFilename);
		}

		{
			String filename = modelDir + "ModelInfo.cs";
			 new CSharpModelInformationGenerator(definitions).generateInformation().toFile(implDir+filename);						 
			generatedFilenames.add(filename);
		}
			
		List<CompositeTypeDefn> allComplexTypes = new ArrayList<CompositeTypeDefn>();
		allComplexTypes.addAll(definitions.getLocalCompositeTypes());
		allComplexTypes.addAll(definitions.getResources());
		
		for( CompositeTypeDefn composite : allComplexTypes )
		{		  
		  // Generate model for all other classes
			String compositeFilename = modelDir + GeneratorUtils.generateCSharpTypeName(composite.getName()) + ".cs";	
			new CSharpModelGenerator(definitions)
				.generateComposite(composite).toFile(implDir + compositeFilename);		
			generatedFilenames.add(compositeFilename);
		}

		for( CompositeTypeDefn composite : allComplexTypes )
		{		
			// Don't generate parsers/serializers for abstract stuff (for now)
			if( composite.isAbstract() ) continue;
      
      // Generate parsers/serializers for all other classes
			String xmlParserFilename = parsersDir + GeneratorUtils.generateCSharpTypeName(composite.getName()) + "Parser.cs";			
			new CSharpParserGenerator(definitions)
					.generateCompositeParser(composite, definitions).toFile(implDir+xmlParserFilename);			
			generatedFilenames.add(xmlParserFilename);
	
			String serializerFilename = serializersDir + GeneratorUtils.generateCSharpTypeName(composite.getName()) + "Serializer.cs";			
			new CSharpSerializerGenerator(definitions)
				.generateCompositeSerializer(composite).toFile(implDir+serializerFilename);			
			generatedFilenames.add(serializerFilename);
		}
		
		for( ConstrainedTypeDefn constrained : definitions.getLocalConstrainedTypes() )
		{
			// Build C# class for constrained type
			String constrainedFilename = modelDir + constrained.getName() + ".cs";
			new CSharpModelGenerator(definitions)
				.generateConstrained(constrained).toFile(implDir+constrainedFilename);						 
			generatedFilenames.add(constrainedFilename);
			
			// Build Xml parser for constrained type
			String parserFilename = parsersDir + constrained.getName() + "Parser.cs";
			new CSharpParserGenerator(definitions)
				.generateConstrainedParser(constrained).toFile(implDir+parserFilename);						 
			generatedFilenames.add(parserFilename);
		}

		// Collect all bindings to generate the EnumHelper class
		List<BindingDefn> allBindings = new ArrayList<BindingDefn>();
		allBindings.addAll(definitions.getBinding());
		for( NameScope ns : definitions.getLocalCompositeTypes() )
			allBindings.addAll(ns.getBinding());
		for( NameScope ns : definitions.getResources() )
			allBindings.addAll(ns.getBinding());
		{
			String enumHelperFilename = modelDir + "EnumHelper.cs";
			
			new CSharpEnumHelperGenerator(definitions)
				.generateEnumHelper(definitions, allBindings).toFile(implDir+enumHelperFilename);						 
			generatedFilenames.add(enumHelperFilename);			
		}
		
		// Generate resource parser entrypoint
		{
			String filename = parsersDir + "FhirParser.cs";
			
			new CSharpFhirParserGenerator(definitions)
				.generateResourceParser(definitions).toFile(implDir+filename);						 
			generatedFilenames.add(filename);			
		}
		
		// Generate resource serializer entrypoint
		{
			String filename = serializersDir + "FhirSerializer.cs";
			
			new CSharpSerializerGenerator(definitions)
				.generateResourceSerializer().toFile(implDir+filename);						 
			generatedFilenames.add(filename);			
		}
		
	    // Generate C# project file
	    CSharpProjectGenerator projGen = new CSharpProjectGenerator();
	    projGen.build(implDir, generatedFilenames);
	    
		String modelSupportDir = "Model.Support" + sl;
		String parsersSupportDir = "Parsers.Support" + sl;
		String serializersSupportDir = "Serializers.Support" + sl;
		String supportDir = "Support" + sl;
	//	String supportSearchDir = supportDir + "Search" + sl;
		
		ZipGenerator zip = new ZipGenerator(destDir + CSHARP_FILENAME);
		zip.addFiles(implDir+modelDir, modelDir, ".cs", null);
		zip.addFiles(implDir+parsersDir, parsersDir, ".cs", null);
		zip.addFiles(implDir+serializersDir, serializersDir, ".cs", null);
		zip.addFolder(implDir+modelSupportDir, modelSupportDir);
		zip.addFolder(implDir+parsersSupportDir, parsersSupportDir);
		zip.addFolder(implDir+serializersSupportDir, serializersSupportDir);
		zip.addFolder(implDir+supportDir, supportDir);
		//zip.addFiles(implDir+supportSearchDir,supportSearchDir, ".cs", null);
		zip.addFolder(implDir+"Client"+sl, "Client"+sl);
		zip.addFiles(implDir+"Properties" + sl, "Properties"+sl, ".cs", null);
		zip.addFiles(implDir, "", ".csproj", null);
		zip.addFiles(implDir, "", ".sln", null);
		zip.addFiles(implDir, "", "Local.testsettings", null);
		zip.addFiles(implDir, "", "Hl7.Fhir.vsmdi", null);
		// Include supporting libraries
		String librariesDir = "Libraries" + sl;
		String winRTLibrariesDir = librariesDir + "WinRT" + sl;
		zip.addFiles(implDir+librariesDir, librariesDir, ".dll", null);
		zip.addFiles(implDir+winRTLibrariesDir, winRTLibrariesDir , ".dll", null);
		
		// Include test project
		String testProjectDir = "Hl7.Fhir.Tests" + sl;
		zip.addFiles(implDir+testProjectDir, testProjectDir, ".cs", null);
		zip.addFiles(implDir+testProjectDir + "Properties" + sl, testProjectDir+"Properties"+sl, ".cs", null);
		zip.addFiles(implDir+testProjectDir, testProjectDir, ".csproj", null);
		
		zip.close();		
	}

  @Override
public boolean doesCompile() {
    return true;
  }

  
  private static final String CSHARP_FILENAME = "CSharp.zip";
  
  @Override
  public boolean compile(String rootDir, List<String> errors) 
  {  
    String solutionDirectory = Utilities.path(rootDir, "implementations", "csharp");
    String solutionFile = Utilities.path(solutionDirectory, "Hl7.Fhir.csproj");
    DotNetCompileResult result = DotNetFramework.compile(solutionFile, this.logger);

    // If result == null, the compile function will have logged the reason
    if( result == null )
      return false;    

    // If there was an error, print the message
    else if(result.exitValue != 0)
    {
      logger.log(result.message);
      return false;
    }

    return addCompiledAssemblyToCsharpZip(rootDir, solutionDirectory);    
  }

  private boolean addCompiledAssemblyToCsharpZip(String rootDir, String solutionDirectory) {
    // Try to add the newly compiled assembly to the distribution zip
    String csharpZip = Utilities.path(rootDir, "publish", CSHARP_FILENAME );
    String assemblyDirectory = Utilities.path(solutionDirectory,"bin","Release");
    String tempZip = csharpZip + "_temp";
    
    File origZipFile = new File(csharpZip);
    File tempZipFile = new File(tempZip);
    
    try
    {     
      if( origZipFile.renameTo(tempZipFile) == false )
      {
        logger.log("Failed to rename CSharp.zip to a temporary file. Is it locked?");
        return false;
      }
      
      char sl = File.separatorChar;
      ZipGenerator zip = new ZipGenerator(csharpZip);
      zip.addFromZip(tempZip);
      zip.addFolder( assemblyDirectory + sl, "bin" + sl);
      zip.close();
    }
    catch( Exception e)
    {
      logger.log("Failed to add compiled assembly to csharp distribution zip");
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
public String checkFragments(String rootDir, String fragments) throws Exception {
    return "Not supported by C# implementation";
  }
}
