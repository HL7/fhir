/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.hl7.fhir.definitions.ecore.fhir;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Defn</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getName <em>Name</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getMinCardinality <em>Min Cardinality</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getMaxCardinality <em>Max Cardinality</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#isMustUnderstand <em>Must Understand</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#isMustSupport <em>Must Support</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getTypes <em>Types</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getMappings <em>Mappings</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getExampleValue <em>Example Value</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getContent <em>Content</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getAnnotation <em>Annotation</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getInvariants <em>Invariants</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getParentType <em>Parent Type</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getParentElement <em>Parent Element</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#isInternalId <em>Internal Id</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#isPrimitiveContents <em>Primitive Contents</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getElementDefn()
 * @model
 * @generated
 */
public interface ElementDefn extends EObject {
	/**
	 * Returns the value of the '<em><b>Max Cardinality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Cardinality</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Cardinality</em>' attribute.
	 * @see #isSetMaxCardinality()
	 * @see #unsetMaxCardinality()
	 * @see #setMaxCardinality(int)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getElementDefn_MaxCardinality()
	 * @model unsettable="true" required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	int getMaxCardinality();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getMaxCardinality <em>Max Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Cardinality</em>' attribute.
	 * @see #isSetMaxCardinality()
	 * @see #unsetMaxCardinality()
	 * @see #getMaxCardinality()
	 * @generated
	 */
	void setMaxCardinality(int value);

	/**
	 * Unsets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getMaxCardinality <em>Max Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMaxCardinality()
	 * @see #getMaxCardinality()
	 * @see #setMaxCardinality(int)
	 * @generated
	 */
	void unsetMaxCardinality();

	/**
	 * Returns whether the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getMaxCardinality <em>Max Cardinality</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Max Cardinality</em>' attribute is set.
	 * @see #unsetMaxCardinality()
	 * @see #getMaxCardinality()
	 * @see #setMaxCardinality(int)
	 * @generated
	 */
	boolean isSetMaxCardinality();

	/**
	 * Returns the value of the '<em><b>Min Cardinality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Cardinality</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min Cardinality</em>' attribute.
	 * @see #isSetMinCardinality()
	 * @see #unsetMinCardinality()
	 * @see #setMinCardinality(int)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getElementDefn_MinCardinality()
	 * @model unsettable="true" required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	int getMinCardinality();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getMinCardinality <em>Min Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Cardinality</em>' attribute.
	 * @see #isSetMinCardinality()
	 * @see #unsetMinCardinality()
	 * @see #getMinCardinality()
	 * @generated
	 */
	void setMinCardinality(int value);

	/**
	 * Unsets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getMinCardinality <em>Min Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMinCardinality()
	 * @see #getMinCardinality()
	 * @see #setMinCardinality(int)
	 * @generated
	 */
	void unsetMinCardinality();

	/**
	 * Returns whether the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getMinCardinality <em>Min Cardinality</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Min Cardinality</em>' attribute is set.
	 * @see #unsetMinCardinality()
	 * @see #getMinCardinality()
	 * @see #setMinCardinality(int)
	 * @generated
	 */
	boolean isSetMinCardinality();

	/**
	 * Returns the value of the '<em><b>Must Understand</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Must Understand</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Must Understand</em>' attribute.
	 * @see #setMustUnderstand(boolean)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getElementDefn_MustUnderstand()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	boolean isMustUnderstand();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#isMustUnderstand <em>Must Understand</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Must Understand</em>' attribute.
	 * @see #isMustUnderstand()
	 * @generated
	 */
	void setMustUnderstand(boolean value);

	/**
	 * Returns the value of the '<em><b>Parent Type</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Type</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Type</em>' container reference.
	 * @see #setParentType(CompositeTypeDefn)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getElementDefn_ParentType()
	 * @see org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn#getElements
	 * @model opposite="elements" transient="false"
	 * @generated
	 */
	CompositeTypeDefn getParentType();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getParentType <em>Parent Type</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Type</em>' container reference.
	 * @see #getParentType()
	 * @generated
	 */
	void setParentType(CompositeTypeDefn value);

	/**
	 * Returns the value of the '<em><b>Parent Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Element</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Element</em>' reference.
	 * @see #setParentElement(ElementDefn)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getElementDefn_ParentElement()
	 * @model
	 * @generated
	 */
	ElementDefn getParentElement();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getParentElement <em>Parent Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Element</em>' reference.
	 * @see #getParentElement()
	 * @generated
	 */
	void setParentElement(ElementDefn value);

	/**
	 * Returns the value of the '<em><b>Internal Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Internal Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Internal Id</em>' attribute.
	 * @see #setInternalId(boolean)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getElementDefn_InternalId()
	 * @model
	 * @generated
	 */
	boolean isInternalId();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#isInternalId <em>Internal Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Internal Id</em>' attribute.
	 * @see #isInternalId()
	 * @generated
	 */
	void setInternalId(boolean value);

	/**
	 * Returns the value of the '<em><b>Primitive Contents</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Primitive Contents</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Primitive Contents</em>' attribute.
	 * @see #setPrimitiveContents(boolean)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getElementDefn_PrimitiveContents()
	 * @model
	 * @generated
	 */
	boolean isPrimitiveContents();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#isPrimitiveContents <em>Primitive Contents</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Primitive Contents</em>' attribute.
	 * @see #isPrimitiveContents()
	 * @generated
	 */
	void setPrimitiveContents(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isRepeating();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isPolymorph();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean containsResource();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isXhtml();

	/**
	 * Returns the value of the '<em><b>Must Support</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Must Support</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Must Support</em>' attribute.
	 * @see #setMustSupport(boolean)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getElementDefn_MustSupport()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	boolean isMustSupport();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#isMustSupport <em>Must Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Must Support</em>' attribute.
	 * @see #isMustSupport()
	 * @generated
	 */
	void setMustSupport(boolean value);

	/**
	 * Returns the value of the '<em><b>Types</b></em>' containment reference list.
	 * The list contents are of type {@link org.hl7.fhir.definitions.ecore.fhir.TypeRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Types</em>' containment reference list.
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getElementDefn_Types()
	 * @model containment="true"
	 *        extendedMetaData="name='type'"
	 * @generated
	 */
	EList<TypeRef> getTypes();

	/**
	 * Returns the value of the '<em><b>Annotation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Annotation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Annotation</em>' containment reference.
	 * @see #setAnnotation(Annotations)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getElementDefn_Annotation()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Annotations getAnnotation();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getAnnotation <em>Annotation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Annotation</em>' containment reference.
	 * @see #getAnnotation()
	 * @generated
	 */
	void setAnnotation(Annotations value);

	/**
	 * Returns the value of the '<em><b>Invariants</b></em>' containment reference list.
	 * The list contents are of type {@link org.hl7.fhir.definitions.ecore.fhir.InvariantRef}.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Invariants</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Invariants</em>' containment reference list.
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getElementDefn_Invariants()
	 * @model containment="true"
	 * @generated
	 */
  EList<InvariantRef> getInvariants();

  /**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getElementDefn_Name()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Mappings</b></em>' containment reference list.
	 * The list contents are of type {@link org.hl7.fhir.definitions.ecore.fhir.Mapping}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mappings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mappings</em>' containment reference list.
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getElementDefn_Mappings()
	 * @model containment="true"
	 *        extendedMetaData="name='mapping'"
	 * @generated
	 */
	EList<Mapping> getMappings();

	/**
	 * Returns the value of the '<em><b>Example Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Example Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Example Value</em>' attribute.
	 * @see #setExampleValue(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getElementDefn_ExampleValue()
	 * @model extendedMetaData="kind='element'"
	 * @generated
	 */
	String getExampleValue();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getExampleValue <em>Example Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Example Value</em>' attribute.
	 * @see #getExampleValue()
	 * @generated
	 */
	void setExampleValue(String value);

	/**
	 * Returns the value of the '<em><b>Content</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Content</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Content</em>' reference.
	 * @see #setContent(ElementDefn)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getElementDefn_Content()
	 * @model
	 * @generated
	 */
	ElementDefn getContent();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getContent <em>Content</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Content</em>' reference.
	 * @see #getContent()
	 * @generated
	 */
	void setContent(ElementDefn value);

} // ElementDefn
