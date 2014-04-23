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
 * A representation of the model object '<em><b>Name Scope</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.NameScope#getType <em>Type</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.NameScope#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getNameScope()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface NameScope extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' containment reference list.
	 * The list contents are of type {@link org.hl7.fhir.definitions.ecore.fhir.TypeDefn}.
	 * It is bidirectional and its opposite is '{@link org.hl7.fhir.definitions.ecore.fhir.TypeDefn#getScope <em>Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' containment reference list.
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getNameScope_Type()
	 * @see org.hl7.fhir.definitions.ecore.fhir.TypeDefn#getScope
	 * @model opposite="scope" containment="true"
	 *        extendedMetaData="name='type' namespace=''"
	 * @generated
	 */
	EList<TypeDefn> getType();

	/**
	 * Returns the value of the '<em><b>Binding</b></em>' containment reference list.
	 * The list contents are of type {@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn}.
	 * It is bidirectional and its opposite is '{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' containment reference list.
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getNameScope_Binding()
	 * @see org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getParent
	 * @model opposite="parent" containment="true"
	 *        extendedMetaData="name='binding'"
	 * @generated
	 */
	EList<BindingDefn> getBinding();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<CompositeTypeDefn> getLocalCompositeTypes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<ConstrainedTypeDefn> getLocalConstrainedTypes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	NameScope getContainingScope();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	TypeDefn resolve(TypeRef ref);

} // NameScope
