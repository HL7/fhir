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
 * A representation of the model object '<em><b>Type Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.TypeRef#getName <em>Name</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.TypeRef#getFullName <em>Full Name</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.TypeRef#getResourceParams <em>Resource Params</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.TypeRef#getBindingRef <em>Binding Ref</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.TypeRef#getFullBindingRef <em>Full Binding Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getTypeRef()
 * @model
 * @generated
 */
public interface TypeRef extends EObject {	
	public final static String XHTML_PSEUDOTYPE_NAME = "xhtml";	
	public final static String RESOURCE_TYPE_NAME = "Resource";
	public final static String RESOURCEREF_TYPE_NAME = "Reference";
	public static final String ELEMENT_TYPE_NAME = "Element";
	public static final String BINARY_TYPE_NAME = "Binary";
	
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
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getTypeRef_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.TypeRef#getName <em>Name</em>}' attribute.
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
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getTypeRef_FullName()
	 * @model
	 * @generated
	 */
	String getFullName();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.TypeRef#getFullName <em>Full Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Full Name</em>' attribute.
	 * @see #getFullName()
	 * @generated
	 */
	void setFullName(String value);

	/**
	 * Returns the value of the '<em><b>Resource Params</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Params</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Params</em>' attribute list.
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getTypeRef_ResourceParams()
	 * @model
	 * @generated
	 */
	EList<String> getResourceParams();

	/**
	 * Returns the value of the '<em><b>Binding Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding Ref</em>' attribute.
	 * @see #setBindingRef(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getTypeRef_BindingRef()
	 * @model
	 * @generated
	 */
	String getBindingRef();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.TypeRef#getBindingRef <em>Binding Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding Ref</em>' attribute.
	 * @see #getBindingRef()
	 * @generated
	 */
	void setBindingRef(String value);

	/**
	 * Returns the value of the '<em><b>Full Binding Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Full Binding Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Full Binding Ref</em>' attribute.
	 * @see #setFullBindingRef(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getTypeRef_FullBindingRef()
	 * @model
	 * @generated
	 */
	String getFullBindingRef();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.TypeRef#getFullBindingRef <em>Full Binding Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Full Binding Ref</em>' attribute.
	 * @see #getFullBindingRef()
	 * @generated
	 */
	void setFullBindingRef(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isBindable();

} // TypeRef
