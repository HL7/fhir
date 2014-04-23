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
 * A representation of the model object '<em><b>Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.Constraint#getName <em>Name</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.Constraint#getDefintion <em>Defintion</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.Constraint#getConstrains <em>Constrains</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.Constraint#getDetails <em>Details</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getConstraint()
 * @model
 * @generated
 */
public interface Constraint extends EObject {
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
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getConstraint_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.Constraint#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Defintion</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Defintion</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Defintion</em>' attribute.
	 * @see #setDefintion(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getConstraint_Defintion()
	 * @model required="true"
	 * @generated
	 */
	String getDefintion();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.Constraint#getDefintion <em>Defintion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Defintion</em>' attribute.
	 * @see #getDefintion()
	 * @generated
	 */
	void setDefintion(String value);

	/**
	 * Returns the value of the '<em><b>Constrains</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constrains</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constrains</em>' containment reference.
	 * @see #setConstrains(TypeRef)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getConstraint_Constrains()
	 * @model containment="true" required="true"
	 * @generated
	 */
	TypeRef getConstrains();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.Constraint#getConstrains <em>Constrains</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constrains</em>' containment reference.
	 * @see #getConstrains()
	 * @generated
	 */
	void setConstrains(TypeRef value);

	/**
	 * Returns the value of the '<em><b>Details</b></em>' containment reference list.
	 * The list contents are of type {@link org.hl7.fhir.definitions.ecore.fhir.Invariant}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Details</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Details</em>' containment reference list.
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getConstraint_Details()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Invariant> getDetails();

} // Constraint
