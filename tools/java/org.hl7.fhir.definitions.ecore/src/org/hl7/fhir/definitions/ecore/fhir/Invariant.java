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
 * A representation of the model object '<em><b>Invariant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.Invariant#getName <em>Name</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.Invariant#getDescription <em>Description</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.Invariant#getHuman <em>Human</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.Invariant#getOcl <em>Ocl</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.Invariant#getXpath <em>Xpath</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getInvariant()
 * @model
 * @generated
 */
public interface Invariant extends EObject {
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
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getInvariant_Name()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.Invariant#getName <em>Name</em>}' attribute.
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
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getInvariant_Description()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.Invariant#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Human</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Human</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Human</em>' attribute.
	 * @see #setHuman(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getInvariant_Human()
	 * @model required="true"
	 *        extendedMetaData="kind='element'"
	 * @generated
	 */
	String getHuman();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.Invariant#getHuman <em>Human</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Human</em>' attribute.
	 * @see #getHuman()
	 * @generated
	 */
	void setHuman(String value);

	/**
	 * Returns the value of the '<em><b>Ocl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ocl</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ocl</em>' attribute.
	 * @see #setOcl(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getInvariant_Ocl()
	 * @model extendedMetaData="kind='element'"
	 * @generated
	 */
	String getOcl();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.Invariant#getOcl <em>Ocl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ocl</em>' attribute.
	 * @see #getOcl()
	 * @generated
	 */
	void setOcl(String value);

	/**
	 * Returns the value of the '<em><b>Xpath</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Xpath</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Xpath</em>' attribute.
	 * @see #setXpath(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getInvariant_Xpath()
	 * @model required="true"
	 *        extendedMetaData="kind='element'"
	 * @generated
	 */
	String getXpath();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.Invariant#getXpath <em>Xpath</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xpath</em>' attribute.
	 * @see #getXpath()
	 * @generated
	 */
	void setXpath(String value);

} // Invariant
