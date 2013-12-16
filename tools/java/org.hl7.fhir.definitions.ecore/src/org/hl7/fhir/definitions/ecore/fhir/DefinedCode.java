/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.hl7.fhir.definitions.ecore.fhir;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Defined Code</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.DefinedCode#getCode <em>Code</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.DefinedCode#getDefinition <em>Definition</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.DefinedCode#getComment <em>Comment</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.DefinedCode#getDisplay <em>Display</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.DefinedCode#getSystem <em>System</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.DefinedCode#getId <em>Id</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.DefinedCode#getParent <em>Parent</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.DefinedCode#getV2Map <em>V2 Map</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.DefinedCode#getV3Map <em>V3 Map</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getDefinedCode()
 * @model
 * @generated
 */
public interface DefinedCode extends EObject {
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
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getDefinedCode_Code()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getCode();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.DefinedCode#getCode <em>Code</em>}' attribute.
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
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getDefinedCode_Definition()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getDefinition();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.DefinedCode#getDefinition <em>Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Definition</em>' attribute.
	 * @see #getDefinition()
	 * @generated
	 */
	void setDefinition(String value);

	/**
	 * Returns the value of the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comment</em>' attribute.
	 * @see #setComment(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getDefinedCode_Comment()
	 * @model
	 * @generated
	 */
	String getComment();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.DefinedCode#getComment <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Comment</em>' attribute.
	 * @see #getComment()
	 * @generated
	 */
	void setComment(String value);

	/**
	 * Returns the value of the '<em><b>Display</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Display</em>' attribute.
	 * @see #setDisplay(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getDefinedCode_Display()
	 * @model
	 * @generated
	 */
	String getDisplay();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.DefinedCode#getDisplay <em>Display</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Display</em>' attribute.
	 * @see #getDisplay()
	 * @generated
	 */
	void setDisplay(String value);

	/**
	 * Returns the value of the '<em><b>System</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System</em>' attribute.
	 * @see #setSystem(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getDefinedCode_System()
	 * @model
	 * @generated
	 */
	String getSystem();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.DefinedCode#getSystem <em>System</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>System</em>' attribute.
	 * @see #getSystem()
	 * @generated
	 */
	void setSystem(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getDefinedCode_Id()
	 * @model required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.DefinedCode#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' attribute.
	 * @see #setParent(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getDefinedCode_Parent()
	 * @model
	 * @generated
	 */
	String getParent();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.DefinedCode#getParent <em>Parent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' attribute.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(String value);

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
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getDefinedCode_V2Map()
	 * @model
	 * @generated
	 */
  String getV2Map();

  /**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.DefinedCode#getV2Map <em>V2 Map</em>}' attribute.
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
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getDefinedCode_V3Map()
	 * @model
	 * @generated
	 */
  String getV3Map();

  /**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.DefinedCode#getV3Map <em>V3 Map</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>V3 Map</em>' attribute.
	 * @see #getV3Map()
	 * @generated
	 */
  void setV3Map(String value);

} // DefinedCode
