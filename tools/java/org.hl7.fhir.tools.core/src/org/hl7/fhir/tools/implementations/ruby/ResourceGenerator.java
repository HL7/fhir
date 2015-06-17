package org.hl7.fhir.tools.implementations.ruby;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.tools.implementations.GenBlock;

public abstract class ResourceGenerator {
  protected String name;
  protected File outputFile;
  protected Definitions definitions;
  protected ElementDefn textElement;

  protected enum FieldType {
    ANY("*"),
    BINARY("base64Binary"),
    BOOLEAN("boolean"),
    NUMERIC("integer", "decimal"),
    DATE("date", "dateTime", "time"),
    INSTANT("instant"),
    STRING("string", "uri", "code", "id", "oid"),
    REFERENCE("Resource"),
    QUANTITY("Age", "Count", "Duration"),
    IGNORED("xhtml","div"),
    RESOURCE((String[])null),
    EMBEDDED((String[])null);
    
    private List<String> elementTypes;

    FieldType(String... elementTypes) {
      this.elementTypes = elementTypes != null ? Arrays.asList(elementTypes) : null;
    }

    public static FieldType getFieldType(String elementType) {
      FieldType found = RESOURCE;
      for (FieldType fieldType : FieldType.values()) {
        if (fieldType.elementTypes != null && fieldType.elementTypes.contains(elementType)) {
          return fieldType;
        }
      }
      return found;
    }
  }  
  
  public ResourceGenerator(String name, Definitions definitions, File outputFile) {
    this.name = name;
    this.definitions = definitions;
    this.outputFile = outputFile;
    
    // The <text/> element is really inherited from the root Resource...
    // probably a better way to do this...
    this.textElement = new ElementDefn();
    this.textElement.setName("text");
    this.textElement.setMinCardinality(0);
    this.textElement.setMaxCardinality(1);
    this.textElement.setDeclaredTypeName("Narrative");
    this.textElement.getTypes().add(new TypeRef("Narrative"));
  }

  public void generate() throws Exception {

    outputFile.createNewFile();
    GenBlock fileBlock = new GenBlock();

    generateMainHeader(fileBlock);
    generateResourceHeader(fileBlock);

    TypeDefn root = getRootDefinition();
    // first loop through and generate all of the embedded types from this
    // schema
    for (Iterator<ElementDefn> iterator = root.getElements().iterator(); iterator.hasNext();) {
      ElementDefn elementDefinition = iterator.next();
      generateEmbeddedType(outputFile.getParentFile(), fileBlock, elementDefinition);
    }

    generatePostEmbedded(fileBlock);

    if(!name.equalsIgnoreCase("narrative")) {
      boolean alreadyHasTextElement = false;
      for (Iterator<ElementDefn> iterator = root.getElements().iterator(); iterator.hasNext();) {
        ElementDefn elementDefinition = iterator.next();
        if(elementDefinition.getName().equalsIgnoreCase("text")) alreadyHasTextElement = true;
      }
      
      if(!alreadyHasTextElement && textElement != null) {
        generateElement(fileBlock, textElement);
      }      
    }
    
    // next loop through and generate the filed elements
    for (Iterator<ElementDefn> iterator = root.getElements().iterator(); iterator.hasNext();) {
      ElementDefn elementDefinition = iterator.next();
      generateElement(fileBlock, elementDefinition);
    }

    generateResourceFooter(fileBlock);
    generateMainFooter(fileBlock);

    Writer modelFile = new BufferedWriter(new FileWriter(outputFile));
    modelFile.write(fileBlock.toString());
    modelFile.flush();
    modelFile.close();

  }

  protected abstract void generateMainFooter(GenBlock fileBlock);
  protected abstract void generateResourceFooter(GenBlock fileBlock);
  protected abstract void generateResourceHeader(GenBlock fileBlock);
  protected abstract void generatePostEmbedded(GenBlock fileBlock);
  protected abstract void generateMainHeader(GenBlock fileBlock);
  protected abstract void generateEmbeddedType(File parentFile, GenBlock fileBlock, ElementDefn elementDefinition) throws IOException;
  protected abstract void handleField(GenBlock block, FieldType fieldType, boolean multipleCardinality, ElementDefn elementDefinition, TypeRef typeRef);

  protected void generateElement(GenBlock block, ElementDefn elementDefinition) {
    List<TypeRef> types = elementDefinition.getTypes();
    boolean multipleCardinality = elementDefinition.getMaxCardinality() == null||elementDefinition.getMaxCardinality() > 1;
    if (types.size() > 0) {
      for (TypeRef typeRef : types) {
        String elementType = typeRef.getName();
        if (elementType.startsWith("@")) {
          handleField(block, FieldType.EMBEDDED, multipleCardinality, elementDefinition, typeRef);
        } else {
          handleField(block, FieldType.getFieldType(elementType), multipleCardinality, elementDefinition, typeRef);
        }
      }
    } else if (types.size() == 0) {
      handleField(block, FieldType.EMBEDDED, multipleCardinality, elementDefinition, null);
    }

  }

  protected String getEmbeddedClassName(ElementDefn elementDefinition, TypeRef typeRef) {
    if (typeRef != null) return typeRef.getResolvedTypeName();
    String typeName = generateTypeName(elementDefinition, null);
    String cname = elementDefinition.getDeclaredTypeName();
    cname = (cname == null) ? typeName : cname;
    return Character.toUpperCase(cname.charAt(0)) + cname.substring(1);

  }

  protected TypeDefn getRootDefinition() {
    TypeDefn el = null;
    ResourceDefn resource = definitions.getResources().get(name);
    if (resource != null) {
      el = resource.getRoot();
    } else {
      el = definitions.getInfrastructure().get(name);
      el = (el == null) ? definitions.getTypes().get(name) : el;
      el = (el == null) ? definitions.getStructures().get(name) : el;
    }
    return el;
  }

  protected String generateTypeName(ElementDefn elementDefinition, TypeRef type) {
    return this.generateTypeName(elementDefinition, type, true);
  }

  protected String generateTypeName(ElementDefn elementDefinition, TypeRef type, boolean fixTypes) {
    String elementName = elementDefinition.getName().replace("[x]", "");
    if (elementDefinition.getTypes().size() > 1) {
      String typeName = type.getName();
      typeName = Character.toUpperCase(typeName.charAt(0)) + typeName.substring(1);
      elementName += typeName;
    }
    
    if (!fixTypes) { 
      return elementName;
    } else if (elementName.equals("type")) {
      elementName = "fhirType";
    } else if (elementName.equals("collection")) {
      elementName = "fhirCollection";
    } else if (elementName.equals("deleted")) {
      elementName = "fhirDeleted";
    } else if (elementName.equals("version")) {
      elementName = "fhirVersion";
    } else if (elementName.equals("class")) {
      elementName = "fhirClass";
    }

    return elementName;
  }

}
