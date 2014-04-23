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
 * A representation of the model object '<em><b>Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.Binding#getId <em>Id</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.Binding#getName <em>Name</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.Binding#getBinding <em>Binding</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.Binding#getStrength <em>Strength</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.Binding#getReference <em>Reference</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.Binding#getDescription <em>Description</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.Binding#getSource <em>Source</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.Binding#getCodes <em>Codes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBinding()
 * @model
 * @generated
 */
public interface Binding extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(Integer)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBinding_Id()
	 * @model required="true"
	 * @generated
	 */
	Integer getId();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.Binding#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(Integer value);

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
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBinding_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.Binding#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Binding</b></em>' attribute.
	 * The literals are from the enumeration {@link org.hl7.fhir.definitions.ecore.fhir.BindingType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' attribute.
	 * @see org.hl7.fhir.definitions.ecore.fhir.BindingType
	 * @see #setBinding(BindingType)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBinding_Binding()
	 * @model required="true"
	 * @generated
	 */
	BindingType getBinding();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.Binding#getBinding <em>Binding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' attribute.
	 * @see org.hl7.fhir.definitions.ecore.fhir.BindingType
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(BindingType value);

	/**
	 * Returns the value of the '<em><b>Strength</b></em>' attribute.
	 * The literals are from the enumeration {@link org.hl7.fhir.definitions.ecore.fhir.BindingStrength}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Strength</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Strength</em>' attribute.
	 * @see org.hl7.fhir.definitions.ecore.fhir.BindingStrength
	 * @see #setStrength(BindingStrength)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBinding_Strength()
	 * @model required="true"
	 * @generated
	 */
	BindingStrength getStrength();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.Binding#getStrength <em>Strength</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Strength</em>' attribute.
	 * @see org.hl7.fhir.definitions.ecore.fhir.BindingStrength
	 * @see #getStrength()
	 * @generated
	 */
	void setStrength(BindingStrength value);

	/**
	 * Returns the value of the '<em><b>Reference</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference</em>' attribute.
	 * @see #setReference(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBinding_Reference()
	 * @model
	 * @generated
	 */
	String getReference();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.Binding#getReference <em>Reference</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference</em>' attribute.
	 * @see #getReference()
	 * @generated
	 */
	void setReference(String value);

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
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBinding_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.Binding#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * for useful error messages during the build process
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source</em>' attribute.
	 * @see #setSource(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBinding_Source()
	 * @model
	 * @generated
	 */
	String getSource();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.Binding#getSource <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' attribute.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(String value);

	/**
	 * Returns the value of the '<em><b>Codes</b></em>' containment reference list.
	 * The list contents are of type {@link org.hl7.fhir.definitions.ecore.fhir.DefinedCode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Codes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Codes</em>' containment reference list.
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBinding_Codes()
	 * @model containment="true"
	 * @generated
	 */
	EList<DefinedCode> getCodes();

} // Binding
