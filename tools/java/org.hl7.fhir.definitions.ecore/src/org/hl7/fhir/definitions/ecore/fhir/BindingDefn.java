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
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getReference <em>Reference</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getSource <em>Source</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getCode <em>Code</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getParent <em>Parent</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getDescription <em>Description</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getDefinition <em>Definition</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#isExample <em>Example</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getV2Map <em>V2 Map</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getV3Map <em>V3 Map</em>}</li>
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
	 * Returns the value of the '<em><b>Code</b></em>' containment reference list.
	 * The list contents are of type {@link org.hl7.fhir.definitions.ecore.fhir.DefinedCode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Code</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Code</em>' containment reference list.
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBindingDefn_Code()
	 * @model containment="true"
	 *        extendedMetaData="name='code'"
	 * @generated
	 */
	EList<DefinedCode> getCode();

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.hl7.fhir.definitions.ecore.fhir.NameScope#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(NameScope)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBindingDefn_Parent()
	 * @see org.hl7.fhir.definitions.ecore.fhir.NameScope#getBinding
	 * @model opposite="binding" required="true" transient="false"
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
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBindingDefn_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

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
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBindingDefn_Definition()
	 * @model
	 * @generated
	 */
	String getDefinition();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getDefinition <em>Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Definition</em>' attribute.
	 * @see #getDefinition()
	 * @generated
	 */
	void setDefinition(String value);

	/**
	 * Returns the value of the '<em><b>Example</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Example</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Example</em>' attribute.
	 * @see #setExample(boolean)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBindingDefn_Example()
	 * @model
	 * @generated
	 */
	boolean isExample();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#isExample <em>Example</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Example</em>' attribute.
	 * @see #isExample()
	 * @generated
	 */
	void setExample(boolean value);

	/**
	 * Returns the value of the '<em><b>V2 Map</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>V2 Map</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>V2 Map</em>' attribute.
	 * @see #setV2Map(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBindingDefn_V2Map()
	 * @model
	 * @generated
	 */
  String getV2Map();

  /**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getV2Map <em>V2 Map</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>V2 Map</em>' attribute.
	 * @see #getV2Map()
	 * @generated
	 */
  void setV2Map(String value);

  /**
	 * Returns the value of the '<em><b>V3 Map</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>V3 Map</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>V3 Map</em>' attribute.
	 * @see #setV3Map(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getBindingDefn_V3Map()
	 * @model
	 * @generated
	 */
  String getV3Map();

  /**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn#getV3Map <em>V3 Map</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>V3 Map</em>' attribute.
	 * @see #getV3Map()
	 * @generated
	 */
  void setV3Map(String value);

  /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isGloballyDefined();

} // BindingDefn
