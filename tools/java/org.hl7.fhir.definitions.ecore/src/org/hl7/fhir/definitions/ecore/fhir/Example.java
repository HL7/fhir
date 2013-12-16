/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.hl7.fhir.definitions.ecore.fhir;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Example</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.Example#getName <em>Name</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.Example#getDescription <em>Description</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.Example#getPath <em>Path</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.Example#isInBook <em>In Book</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.Example#getIdentity <em>Identity</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.Example#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getExample()
 * @model
 * @generated
 */
public interface Example extends EObject {
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
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getExample_Name()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.Example#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getExample_Description()
	 * @model extendedMetaData="kind='element'"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.Example#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getExample_Path()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.Example#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

	/**
	 * Returns the value of the '<em><b>In Book</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Book</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Book</em>' attribute.
	 * @see #setInBook(boolean)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getExample_InBook()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	boolean isInBook();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.Example#isInBook <em>In Book</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>In Book</em>' attribute.
	 * @see #isInBook()
	 * @generated
	 */
	void setInBook(boolean value);

	/**
	 * Returns the value of the '<em><b>Identity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identity</em>' attribute.
	 * @see #setIdentity(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getExample_Identity()
	 * @model
	 * @generated
	 */
	String getIdentity();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.Example#getIdentity <em>Identity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identity</em>' attribute.
	 * @see #getIdentity()
	 * @generated
	 */
	void setIdentity(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.hl7.fhir.definitions.ecore.fhir.ExampleType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.hl7.fhir.definitions.ecore.fhir.ExampleType
	 * @see #setType(ExampleType)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getExample_Type()
	 * @model required="true"
	 * @generated
	 */
	ExampleType getType();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.Example#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.hl7.fhir.definitions.ecore.fhir.ExampleType
	 * @see #getType()
	 * @generated
	 */
	void setType(ExampleType value);

	String getFileTitle();
} // Example
