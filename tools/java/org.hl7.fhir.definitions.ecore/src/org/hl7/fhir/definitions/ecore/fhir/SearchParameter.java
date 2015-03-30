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
 * A representation of the model object '<em><b>Search Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.SearchParameter#getName <em>Name</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.SearchParameter#getDescription <em>Description</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.SearchParameter#getType <em>Type</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.SearchParameter#getPath <em>Path</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.SearchParameter#getComposite <em>Composite</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.SearchParameter#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getSearchParameter()
 * @model
 * @generated
 */
public interface SearchParameter extends EObject {
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
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getSearchParameter_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.SearchParameter#getName <em>Name</em>}' attribute.
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
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getSearchParameter_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.SearchParameter#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.hl7.fhir.definitions.ecore.fhir.SearchType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.hl7.fhir.definitions.ecore.fhir.SearchType
	 * @see #setType(SearchType)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getSearchParameter_Type()
	 * @model required="true"
	 * @generated
	 */
	SearchType getType();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.SearchParameter#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.hl7.fhir.definitions.ecore.fhir.SearchType
	 * @see #getType()
	 * @generated
	 */
	void setType(SearchType value);

	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' attribute list.
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getSearchParameter_Path()
	 * @model
	 * @generated
	 */
	EList<String> getPath();

	/**
	 * Returns the value of the '<em><b>Composite</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Composite</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Composite</em>' attribute list.
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getSearchParameter_Composite()
	 * @model
	 * @generated
	 */
	EList<String> getComposite();

	/**
	 * Returns the value of the '<em><b>Target</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' attribute list.
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getSearchParameter_Target()
	 * @model
	 * @generated
	 */
	EList<String> getTarget();
} // SearchParameter
