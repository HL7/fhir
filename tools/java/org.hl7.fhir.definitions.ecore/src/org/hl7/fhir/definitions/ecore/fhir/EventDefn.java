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
 * A representation of the model object '<em><b>Event Defn</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.EventDefn#getCode <em>Code</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.EventDefn#getDefinition <em>Definition</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.EventDefn#getFollowUps <em>Follow Ups</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.EventDefn#getUsage <em>Usage</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getEventDefn()
 * @model
 * @generated
 */
public interface EventDefn extends EObject {
	/**
	 * Returns the value of the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Code</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Code</em>' attribute.
	 * @see #setCode(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getEventDefn_Code()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getCode();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.EventDefn#getCode <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Code</em>' attribute.
	 * @see #getCode()
	 * @generated
	 */
	void setCode(String value);

	/**
	 * Returns the value of the '<em><b>Definition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Definition</em>' attribute.
	 * @see #setDefinition(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getEventDefn_Definition()
	 * @model required="true"
	 *        extendedMetaData="kind='element'"
	 * @generated
	 */
	String getDefinition();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.EventDefn#getDefinition <em>Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Definition</em>' attribute.
	 * @see #getDefinition()
	 * @generated
	 */
	void setDefinition(String value);

	/**
	 * Returns the value of the '<em><b>Follow Ups</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Follow Ups</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Follow Ups</em>' attribute list.
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getEventDefn_FollowUps()
	 * @model extendedMetaData="kind='element'"
	 * @generated
	 */
	EList<String> getFollowUps();

	/**
	 * Returns the value of the '<em><b>Usage</b></em>' containment reference list.
	 * The list contents are of type {@link org.hl7.fhir.definitions.ecore.fhir.EventUsage}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Usage</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Usage</em>' containment reference list.
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getEventDefn_Usage()
	 * @model containment="true" required="true"
	 *        extendedMetaData="name='usage'"
	 * @generated
	 */
	EList<EventUsage> getUsage();

} // EventDefn
