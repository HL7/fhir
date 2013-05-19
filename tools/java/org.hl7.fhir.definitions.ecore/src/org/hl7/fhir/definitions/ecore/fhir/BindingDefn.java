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
 * A representation of the model object '<em><b>Binding Defn</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getId <em>Id</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getName <em>Name</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getFullName <em>Full Name</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getBinding <em>Binding</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getExtensibility <em>Extensibility</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getStrength <em>Strength</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getReference <em>Reference</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getSource <em>Source</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getCodes <em>Codes</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBindingDefn()
 * @model
 * @generated
 */
public interface BindingDefn extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(int)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBindingDefn_Id()
	 * @model required="true"
	 * @generated
	 */
	int getId();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(int value);

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
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBindingDefn_Name()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Full Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Full Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Full Name</em>' attribute.
	 * @see #setFullName(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBindingDefn_FullName()
	 * @model
	 * @generated
	 */
	String getFullName();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getFullName <em>Full Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Full Name</em>' attribute.
	 * @see #getFullName()
	 * @generated
	 */
	void setFullName(String value);

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
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBindingDefn_Binding()
	 * @model required="true"
	 * @generated
	 */
	BindingType getBinding();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getBinding <em>Binding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' attribute.
	 * @see org.hl7.fhir.definitions.ecore.fhir.BindingType
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(BindingType value);

	/**
	 * Returns the value of the '<em><b>Extensibility</b></em>' attribute.
	 * The literals are from the enumeration {@link org.hl7.fhir.definitions.ecore.fhir.BindingExtensibility}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extensibility</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extensibility</em>' attribute.
	 * @see org.hl7.fhir.definitions.ecore.fhir.BindingExtensibility
	 * @see #setExtensibility(BindingExtensibility)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBindingDefn_Extensibility()
	 * @model required="true"
	 * @generated
	 */
	BindingExtensibility getExtensibility();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getExtensibility <em>Extensibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extensibility</em>' attribute.
	 * @see org.hl7.fhir.definitions.ecore.fhir.BindingExtensibility
	 * @see #getExtensibility()
	 * @generated
	 */
	void setExtensibility(BindingExtensibility value);

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
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBindingDefn_Strength()
	 * @model required="true"
	 * @generated
	 */
	BindingStrength getStrength();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getStrength <em>Strength</em>}' attribute.
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
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBindingDefn_Reference()
	 * @model required="true"
	 * @generated
	 */
	String getReference();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getReference <em>Reference</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference</em>' attribute.
	 * @see #getReference()
	 * @generated
	 */
	void setReference(String value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * for useful error messages during the build process
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source</em>' attribute.
	 * @see #setSource(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBindingDefn_Source()
	 * @model required="true"
	 *        extendedMetaData="kind='element'"
	 * @generated
	 */
	String getSource();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getSource <em>Source</em>}' attribute.
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
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBindingDefn_Codes()
	 * @model containment="true"
	 *        extendedMetaData="name='code'"
	 * @generated
	 */
	EList<DefinedCode> getCodes();

	/**
	 * Returns the value of the '<em><b>Annotations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Annotations</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Annotations</em>' containment reference.
	 * @see #setAnnotations(Annotations)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBindingDefn_Annotations()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Annotations getAnnotations();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getAnnotations <em>Annotations</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Annotations</em>' containment reference.
	 * @see #getAnnotations()
	 * @generated
	 */
	void setAnnotations(Annotations value);

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.hl7.fhir.definitions.ecore.fhir.NameScope#getBindings <em>Bindings</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(NameScope)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBindingDefn_Parent()
	 * @see org.hl7.fhir.definitions.ecore.fhir.NameScope#getBindings
	 * @model opposite="bindings" required="true" transient="false"
	 * @generated
	 */
	NameScope getParent();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(NameScope value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isGloballyDefined();

} // BindingDefn
