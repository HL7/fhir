/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.hl7.fhir.definitions.ecore.fhir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Composite Type Defn</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn#getElements <em>Elements</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn#getInvariants <em>Invariants</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn#isUnnamedElementGroup <em>Unnamed Element Group</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn#getBaseType <em>Base Type</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn#isPrimitiveContents <em>Primitive Contents</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getCompositeTypeDefn()
 * @model
 * @generated
 */
public interface CompositeTypeDefn extends TypeDefn, NameScope {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn}.
	 * It is bidirectional and its opposite is '{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getParentType <em>Parent Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getCompositeTypeDefn_Elements()
	 * @see org.hl7.fhir.definitions.ecore.fhir.ElementDefn#getParentType
	 * @model opposite="parentType" containment="true"
	 *        extendedMetaData="name='element'"
	 * @generated
	 */
	EList<ElementDefn> getElements();

	/**
	 * Returns the value of the '<em><b>Invariants</b></em>' containment reference list.
	 * The list contents are of type {@link org.hl7.fhir.definitions.ecore.fhir.Invariant}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Invariants</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Invariants</em>' containment reference list.
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getCompositeTypeDefn_Invariants()
	 * @model containment="true"
	 *        extendedMetaData="name='invariant'"
	 * @generated
	 */
	EList<Invariant> getInvariants();

	/**
	 * Returns the value of the '<em><b>Unnamed Element Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unnamed Element Group</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unnamed Element Group</em>' attribute.
	 * @see #setUnnamedElementGroup(boolean)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getCompositeTypeDefn_UnnamedElementGroup()
	 * @model
	 * @generated
	 */
	boolean isUnnamedElementGroup();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn#isUnnamedElementGroup <em>Unnamed Element Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unnamed Element Group</em>' attribute.
	 * @see #isUnnamedElementGroup()
	 * @generated
	 */
	void setUnnamedElementGroup(boolean value);

	/**
	 * Returns the value of the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Abstract</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Abstract</em>' attribute.
	 * @see #setAbstract(boolean)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getCompositeTypeDefn_Abstract()
	 * @model
	 * @generated
	 */
	boolean isAbstract();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn#isAbstract <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Abstract</em>' attribute.
	 * @see #isAbstract()
	 * @generated
	 */
	void setAbstract(boolean value);

	/**
	 * Returns the value of the '<em><b>Base Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Type</em>' containment reference.
	 * @see #setBaseType(TypeRef)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getCompositeTypeDefn_BaseType()
	 * @model containment="true"
	 * @generated
	 */
	TypeRef getBaseType();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn#getBaseType <em>Base Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Type</em>' containment reference.
	 * @see #getBaseType()
	 * @generated
	 */
	void setBaseType(TypeRef value);

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
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getCompositeTypeDefn_PrimitiveContents()
	 * @model
	 * @generated
	 */
	boolean isPrimitiveContents();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn#isPrimitiveContents <em>Primitive Contents</em>}' attribute.
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
	EList<ElementDefn> getAllElements();

} // CompositeTypeDefn
