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
 * A representation of the model object '<em><b>Event Usage</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.EventUsage#getNotes <em>Notes</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.EventUsage#getRequestResources <em>Request Resources</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.EventUsage#getRequestAggregations <em>Request Aggregations</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.EventUsage#getResponseResources <em>Response Resources</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.EventUsage#getResponseAggregations <em>Response Aggregations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getEventUsage()
 * @model
 * @generated
 */
public interface EventUsage extends EObject {
	/**
	 * Returns the value of the '<em><b>Notes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Notes</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Notes</em>' attribute.
	 * @see #setNotes(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getEventUsage_Notes()
	 * @model
	 * @generated
	 */
	String getNotes();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.EventUsage#getNotes <em>Notes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Notes</em>' attribute.
	 * @see #getNotes()
	 * @generated
	 */
	void setNotes(String value);

	/**
	 * Returns the value of the '<em><b>Request Resources</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Request Resources</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Request Resources</em>' attribute list.
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getEventUsage_RequestResources()
	 * @model
	 * @generated
	 */
	EList<String> getRequestResources();

	/**
	 * Returns the value of the '<em><b>Request Aggregations</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Request Aggregations</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Request Aggregations</em>' attribute list.
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getEventUsage_RequestAggregations()
	 * @model
	 * @generated
	 */
	EList<String> getRequestAggregations();

	/**
	 * Returns the value of the '<em><b>Response Resources</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Response Resources</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Response Resources</em>' attribute list.
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getEventUsage_ResponseResources()
	 * @model
	 * @generated
	 */
	EList<String> getResponseResources();

	/**
	 * Returns the value of the '<em><b>Response Aggregations</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Response Aggregations</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Response Aggregations</em>' attribute list.
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getEventUsage_ResponseAggregations()
	 * @model
	 * @generated
	 */
	EList<String> getResponseAggregations();

} // EventUsage
