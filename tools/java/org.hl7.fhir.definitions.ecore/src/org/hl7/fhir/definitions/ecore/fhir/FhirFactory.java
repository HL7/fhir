/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.hl7.fhir.definitions.ecore.fhir;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage
 * @generated
 */
public interface FhirFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	FhirFactory eINSTANCE = org.hl7.fhir.definitions.ecore.fhir.impl.FhirFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Definitions</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Definitions</em>'.
	 * @generated
	 */
	Definitions createDefinitions();

	/**
	 * Returns a new object of class '<em>Element Defn</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Element Defn</em>'.
	 * @generated
	 */
	ElementDefn createElementDefn();

	/**
	 * Returns a new object of class '<em>Invariant</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Invariant</em>'.
	 * @generated
	 */
	Invariant createInvariant();

	/**
	 * Returns a new object of class '<em>Type Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Type Ref</em>'.
	 * @generated
	 */
	TypeRef createTypeRef();

	/**
	 * Returns a new object of class '<em>Binding Defn</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Binding Defn</em>'.
	 * @generated
	 */
	BindingDefn createBindingDefn();

	/**
	 * Returns a new object of class '<em>Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mapping</em>'.
	 * @generated
	 */
	Mapping createMapping();

	/**
	 * Returns a new object of class '<em>Resource Defn</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Defn</em>'.
	 * @generated
	 */
	ResourceDefn createResourceDefn();

	/**
	 * Returns a new object of class '<em>Example</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Example</em>'.
	 * @generated
	 */
	Example createExample();

	/**
	 * Returns a new object of class '<em>Defined Code</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Defined Code</em>'.
	 * @generated
	 */
	DefinedCode createDefinedCode();

	/**
	 * Returns a new object of class '<em>Primitive Defn</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Primitive Defn</em>'.
	 * @generated
	 */
	PrimitiveDefn createPrimitiveDefn();

	/**
	 * Returns a new object of class '<em>Constrained Type Defn</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Constrained Type Defn</em>'.
	 * @generated
	 */
	ConstrainedTypeDefn createConstrainedTypeDefn();

	/**
	 * Returns a new object of class '<em>Event Defn</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Event Defn</em>'.
	 * @generated
	 */
	EventDefn createEventDefn();

	/**
	 * Returns a new object of class '<em>Event Usage</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Event Usage</em>'.
	 * @generated
	 */
	EventUsage createEventUsage();

	/**
	 * Returns a new object of class '<em>Profile Defn</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Profile Defn</em>'.
	 * @generated
	 */
	ProfileDefn createProfileDefn();

	/**
	 * Returns a new object of class '<em>Meta Data Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Meta Data Item</em>'.
	 * @generated
	 */
	MetaDataItem createMetaDataItem();

	/**
	 * Returns a new object of class '<em>Search Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Search Parameter</em>'.
	 * @generated
	 */
	SearchParameter createSearchParameter();

	/**
	 * Returns a new object of class '<em>Composite Type Defn</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Composite Type Defn</em>'.
	 * @generated
	 */
	CompositeTypeDefn createCompositeTypeDefn();

	/**
	 * Returns a new object of class '<em>Annotations</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Annotations</em>'.
	 * @generated
	 */
	Annotations createAnnotations();

	/**
	 * Returns a new object of class '<em>Profiled Element Defn</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Profiled Element Defn</em>'.
	 * @generated
	 */
	ProfiledElementDefn createProfiledElementDefn();

	/**
	 * Returns a new object of class '<em>Invariant Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Invariant Ref</em>'.
	 * @generated
	 */
	InvariantRef createInvariantRef();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	FhirPackage getFhirPackage();

} //FhirFactory
