package org.hl7.fhir.definitions.parsers.converters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hl7.fhir.definitions.ecore.fhir.Annotations;
import org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.ElementDefn;
import org.hl7.fhir.definitions.ecore.fhir.FhirFactory;
import org.hl7.fhir.definitions.ecore.fhir.Invariant;
import org.hl7.fhir.definitions.ecore.fhir.InvariantRef;
import org.hl7.fhir.definitions.ecore.fhir.ResourceDefn;
import org.hl7.fhir.definitions.ecore.fhir.TypeRef;
import org.hl7.fhir.definitions.ecore.fhir.XmlFormatHint;
import org.hl7.fhir.tools.implementations.GeneratorUtils;
import org.hl7.fhir.utilities.Utilities;

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

public class CompositeTypeConverter {
	public static List<CompositeTypeDefn> buildCompositeTypesFromFhirModel(
			Collection<? extends org.hl7.fhir.definitions.model.ElementDefn> types,
			CompositeTypeDefn scope) throws Exception {
		List<CompositeTypeDefn> result = new ArrayList<CompositeTypeDefn>();

		for (org.hl7.fhir.definitions.model.ElementDefn type : types) 
		{
			TypeRef elementBase = FhirFactory.eINSTANCE.createTypeRef();
			elementBase.setName(TypeRef.ELEMENT_TYPE_NAME);

			result.add(buildCompositeTypeFromFhirModel(type, false, scope, elementBase));
		}

		return result;
	}

	public static List<ResourceDefn> buildResourcesFromFhirModel(
			Collection<org.hl7.fhir.definitions.model.ResourceDefn> resources)
			throws Exception {
		List<ResourceDefn> result = new ArrayList<ResourceDefn>();
		
		for (org.hl7.fhir.definitions.model.ResourceDefn resource : resources) 
		{
			try 
			{
		    TypeRef resourceBase = FhirFactory.eINSTANCE.createTypeRef();
		    resourceBase.setName(TypeRef.RESOURCE_TYPE_NAME);

			  result.add(buildResourceFromFhirModel(resource, resourceBase));
			} 
			catch (Exception e) 
			{
				throw new Exception(e.getMessage() + " on resource "
						+ resource.getName());
			}
		}

		return result;
	}

	public static ResourceDefn buildResourceFromFhirModel(
			org.hl7.fhir.definitions.model.ResourceDefn resource, TypeRef base)
			throws Exception {
		
		ResourceDefn newResource = null;
		
		if (resource.isForFutureUse()) {
			// Build a shallow, empty ResourceDefn
			newResource = FhirFactory.eINSTANCE
					.createResourceDefn();
			newResource.setName(resource.getName());
			newResource.setFullName(resource.getName());
			Annotations ann = FhirFactory.eINSTANCE.createAnnotations();
			newResource.setAnnotations(ann);
			newResource.setFuture(true);
			newResource.setBaseType(base);
		}
		else 
		{

			newResource = (ResourceDefn) buildCompositeTypeFromFhirModel(
					resource.getRoot(), true, null, base);
			newResource.setAbstract(resource.isAbstract());
			newResource.setSandbox(resource.isSandbox());
			newResource.setBaseType(base);
			newResource.getExample().addAll(
					ExampleConverter.buildExamplesFromFhirModel(resource
							.getExamples()));
			newResource.getSearch().addAll(
					SearchParameterConverter
							.buildSearchParametersFromFhirModel(resource
									.getSearchParams().values()));
		}
		
		return newResource;
	}

	public static CompositeTypeDefn buildCompositeTypeFromFhirModel( 
			org.hl7.fhir.definitions.model.ElementDefn type, boolean isResource, CompositeTypeDefn scope,
			TypeRef base) throws Exception
	{

		CompositeTypeDefn result = isResource ? FhirFactory.eINSTANCE.createResourceDefn() : 
			FhirFactory.eINSTANCE.createCompositeTypeDefn();

		result.setName( type.getName() );

		if( base != null ) result.setBaseType(base);
		
		if( scope == null )
		{
			// If there's no containing scope, we deduce that we are building the
			// "root" type, so we are the scope.
			scope = result;
			result.setFullName( type.getName() );
		}
		else
		{
			result.setFullName( scope.getName() + "." + type.getName() );
		}
		
		Annotations ann = buildAnnotationsFromFhirElement(type);		
		result.setAnnotations( ann );

		// Add bindings defined in this type to the nearest NameScope,
		// which is a resource and could even be us.
		scope.getBinding().addAll( 
				BindingConverter.buildBindingsFromFhirModel( type.getNestedBindings().values(), scope ));

		// Invariants are local to the type, so add them here.
		result.getInvariant().addAll( 
				buildInvariantsFromFhirModel( type.getInvariants().values() ) );
		
//		for( String typeName : type.getAcceptableGenericTypes() )
//			result.getAllowedGenericTypes().addAll( 
//				TypeRefConverter.buildTypeRefsFromFhirTypeName(typeName) );
		
		// Build my properties and add.
		result.getElement().addAll( buildElementDefnsFromFhirModel( type.getElements(), isResource ) );
		
		// Recursively add nested types for explicitly declared nested types ('=<typename>')
		// to the nearest NameScope (a Resource)
		if( type.getNestedTypes() != null )
			scope.getType().addAll( CompositeTypeConverter.buildCompositeTypesFromFhirModel(
					type.getNestedTypes().values(), scope));
		
		result.setUnnamedElementGroup( type.isAnonymousTypedGroup() );
		
		return result;
	}

	private static Annotations buildAnnotationsFromFhirElement(
			org.hl7.fhir.definitions.model.ElementDefn type) {
		Annotations ann = FhirFactory.eINSTANCE.createAnnotations();
		ann.setShortDefinition(Utilities.cleanupTextString(type.getShortDefn()));
		ann.setDefinition(Utilities.cleanupTextString(type.getDefinition()));
		ann.setComment(Utilities.cleanupTextString(type.getComments()));
		ann.setRequirements(Utilities.cleanupTextString(type.getRequirements()));
		ann.setV2Mapping(Utilities.cleanupTextString(type.getMapping(org.hl7.fhir.definitions.model.Definitions.v2_MAPPING)));
    ann.setRimMapping(Utilities.cleanupTextString(type.getMapping(org.hl7.fhir.definitions.model.Definitions.RIM_MAPPING)));
		ann.setTodo(Utilities.cleanupTextString(type.getTodo()));
		ann.setCommitteeNotes(Utilities.cleanupTextString(type
				.getCommitteeNotes()));
		return ann;
	}

	public static List<ElementDefn> buildElementDefnsFromFhirModel(
			List<org.hl7.fhir.definitions.model.ElementDefn> elements,
			boolean isResource) throws Exception {

		List<ElementDefn> result = new ArrayList<ElementDefn>();

		for (org.hl7.fhir.definitions.model.ElementDefn element : elements) {
			result.add(buildElementDefnFromFhirModel(element, isResource));
		}

		return result;
	}

	public static ElementDefn buildElementDefnFromFhirModel(
			org.hl7.fhir.definitions.model.ElementDefn element,
			boolean isResource) throws Exception {

		ElementDefn result = FhirFactory.eINSTANCE.createElementDefn();

		String name = element.getName();
		if (name.endsWith("[x]")) {
			name = name.replace("[x]", "");
			if (Utilities.noString(name))
			  name = "value";
		}

		result.setName(name);
		Annotations ann = buildAnnotationsFromFhirElement(element);

		if(element.isXmlAttribute())
		  result.setXmlFormatHint(XmlFormatHint.ATTRIBUTE);
		if(element.isXhtmlElement())
		  result.setXmlFormatHint(XmlFormatHint.XHTML_ELEMENT);
		
		result.setAnnotation(ann);		
		result.setIsModifier(element.isModifier());
		
		if( isResource )
		  result.setSummaryItem(element.isSummaryItem());
		else
		  result.setSummaryItem(true);
		
		result.setMinCardinality(element.getMinCardinality());

		if (element.getMaxCardinality() != null)
			result.setMaxCardinality(element.getMaxCardinality());
		else
			result.setMaxCardinality(-1); // Adapt eCore convention for '*'

		if (element.getTypes() != null) {
			result.getType().addAll(
					TypeRefConverter.buildTypeRefsFromFhirModel(element
							.getTypes()));
		
			// Special case: all element using the 'xhtml' type contain only the primitive value
	    // (which, in Xml, is rendered as a xhtml <div> node), there is no id, nor extensions
			 if( result.getType().size() == 1 && result.getType().get(0).getName().equals("xhtml") )
			 {
		      result.setPrimitiveContents(true);
		      result.setXmlFormatHint(XmlFormatHint.XHTML_ELEMENT);	      
			 }
		}
  
		// If this element is actually a nested type definition, these nested
		// elements
		// will have been put into a separately defined type, so we'll just
		// refer to this newly defined type here. Note that by now, to not
		// confuse
		// old code, the typename will have been cleared, and only the fact
		// that getDeclaredTypeName() is set, reminds us of this explicit
		// type declaration that was there before.
		if (element.getDeclaredTypeName() != null)
			result.getType().add(
					TypeRefConverter.buildTypeRefsFromFhirTypeName(element
							.getDeclaredTypeName()));

		if (element.getBindingName() != null
				&& !element.getBindingName().equals("")
				&& !element.getBindingName().equals("*unbound*")) {
			if (result.getType().size() >= 1) {
				for (TypeRef tr : result.getType()) {
					if (tr.isBindable())
						tr.setBindingRef(element.getBindingName());
				}
			}
			// else
			// {
			// throw new
			// Exception("Cannot specify binding for a polymorphic element");
			// }
		}

		for (org.hl7.fhir.definitions.model.Invariant i : element
				.getStatedInvariants()) {
			InvariantRef inv = FhirFactory.eINSTANCE.createInvariantRef();
			inv.setName(i.getId());
			result.getInvariant().add(inv);
		}

		return result;
	}

	public static List<Invariant> buildInvariantsFromFhirModel(
			Collection<org.hl7.fhir.definitions.model.Invariant> invariants) {
		List<Invariant> result = new ArrayList<Invariant>();

		for (org.hl7.fhir.definitions.model.Invariant invariant : invariants)
			result.add(buildInvariantFromFhirModel(invariant));

		return result;
	}

	public static Invariant buildInvariantFromFhirModel(
			org.hl7.fhir.definitions.model.Invariant invariant) {
		Invariant result = FhirFactory.eINSTANCE.createInvariant();

		// In the old model, Id was actually a short identifying name
		// and Name contained a short description.
		result.setName(invariant.getId());
		result.setDescription(Utilities.cleanupTextString(invariant.getName()));
		result.setHuman(Utilities.cleanupTextString(invariant.getEnglish()));
		result.setOcl(Utilities.cleanupTextString(invariant.getOcl()));
		result.setXpath(Utilities.cleanupTextString(invariant.getXpath()));

		return result;
	}

	public static void FixTypeRefs(CompositeTypeDefn composite) {
		for (ElementDefn elemt : composite.getElement()) {
			for (TypeRef ref : elemt.getType())
				TypeRefConverter.Fix(ref, composite);
		}

		for (CompositeTypeDefn comp : composite.getLocalCompositeTypes())
			CompositeTypeConverter.FixTypeRefs(comp);
		
		if( composite.getBaseType() != null )
			TypeRefConverter.Fix(composite.getBaseType(),composite);
	}
	
	public static CompositeTypeDefn buildElementBaseType()
	{
		CompositeTypeDefn result = FhirFactory.eINSTANCE.createCompositeTypeDefn();

		result.setName(TypeRef.ELEMENT_TYPE_NAME);
		result.setFullName(result.getName());
		result.setAbstract(true);
				
		Annotations baseAnn = FhirFactory.eINSTANCE.createAnnotations();
		baseAnn.setShortDefinition("Basetype for all composite-typed elements");
		result.setAnnotations(baseAnn);
		
		ElementDefn extElem = GeneratorUtils.buildSimpleElementDefn("extension", "Extension", "Additional Content defined by implementations", 0, -1);		
		result.getElement().add(extElem);

		ElementDefn modExtElem = GeneratorUtils.buildSimpleElementDefn("modifierExtension", "Extension", "Extensions that cannot be ignored", 0, -1);    
    result.getElement().add(modExtElem);
		
		result.getElement().add(buildInternalIdElement());
		
		return result;
	}
	
	public static ElementDefn buildInternalIdElement()
	{
	  ElementDefn idElem = GeneratorUtils.buildSimpleElementDefn("id", "id", "Local id for element", 0, 1);	  
		idElem.setPrimitiveContents(true);
		idElem.setXmlFormatHint(XmlFormatHint.ATTRIBUTE);
		idElem.setSummaryItem(true);		
		return idElem;
	}
	
	
	

	public static ResourceDefn buildBinaryResourceDefn()
	{
	  ResourceDefn result= FhirFactory.eINSTANCE.createResourceDefn();

	  result.setName(TypeRef.BINARY_TYPE_NAME);
	  result.setFullName(result.getName());
	  result.setAbstract(false);
	    
	  Annotations resourceAnn = FhirFactory.eINSTANCE.createAnnotations();
	  resourceAnn.setShortDefinition("Resource for capturing binary data");
	  result.setAnnotations(resourceAnn);
	  
    TypeRef resourceBase = FhirFactory.eINSTANCE.createTypeRef();
    resourceBase.setName(TypeRef.RESOURCE_TYPE_NAME);
	  result.setBaseType(resourceBase);

	  ElementDefn contentElem = GeneratorUtils.buildSimpleElementDefn("content", "base64Binary", "Binary contents", 1, 1);
	  contentElem.setPrimitiveContents(true);
	  contentElem.setXmlFormatHint(XmlFormatHint.TEXT_NODE);
	  result.getElement().add(contentElem);
	   
	   ElementDefn contentTypeElem = GeneratorUtils.buildSimpleElementDefn("contentType", "string", "Media type of contents", 1, 1);
	   contentTypeElem.setPrimitiveContents(true);
	   contentTypeElem.setXmlFormatHint(XmlFormatHint.ATTRIBUTE);
	   result.getElement().add(contentTypeElem);
    
	   return result;
	}

}
