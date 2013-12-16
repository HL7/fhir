/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.hl7.fhir.definitions.ecore.fhir;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Profiled Element Defn</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.ProfiledElementDefn#isInherited <em>Inherited</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.ProfiledElementDefn#getAggregation <em>Aggregation</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.ProfiledElementDefn#getFixedValue <em>Fixed Value</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.ProfiledElementDefn#getTargetUri <em>Target Uri</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.ProfiledElementDefn#getProfileName <em>Profile Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getProfiledElementDefn()
 * @model
 * @generated
 */
public interface ProfiledElementDefn extends ElementDefn {
	/**
	 * Returns the value of the '<em><b>Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inherited</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inherited</em>' attribute.
	 * @see #setInherited(boolean)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getProfiledElementDefn_Inherited()
	 * @model
	 * @generated
	 */
	boolean isInherited();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.ProfiledElementDefn#isInherited <em>Inherited</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inherited</em>' attribute.
	 * @see #isInherited()
	 * @generated
	 */
	void setInherited(boolean value);

	/**
	 * Returns the value of the '<em><b>Aggregation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aggregation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aggregation</em>' attribute.
	 * @see #setAggregation(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getProfiledElementDefn_Aggregation()
	 * @model
	 * @generated
	 */
	String getAggregation();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.ProfiledElementDefn#getAggregation <em>Aggregation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Aggregation</em>' attribute.
	 * @see #getAggregation()
	 * @generated
	 */
	void setAggregation(String value);

	/**
	 * Returns the value of the '<em><b>Fixed Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fixed Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fixed Value</em>' attribute.
	 * @see #setFixedValue(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getProfiledElementDefn_FixedValue()
	 * @model
	 * @generated
	 */
	String getFixedValue();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.ProfiledElementDefn#getFixedValue <em>Fixed Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fixed Value</em>' attribute.
	 * @see #getFixedValue()
	 * @generated
	 */
	void setFixedValue(String value);

	/**
	 * Returns the value of the '<em><b>Target Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Uri</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Uri</em>' attribute.
	 * @see #setTargetUri(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getProfiledElementDefn_TargetUri()
	 * @model
	 * @generated
	 */
	String getTargetUri();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.ProfiledElementDefn#getTargetUri <em>Target Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Uri</em>' attribute.
	 * @see #getTargetUri()
	 * @generated
	 */
	void setTargetUri(String value);

	/**
	 * Returns the value of the '<em><b>Profile Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * only in a profile, for unpicking
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Profile Name</em>' attribute.
	 * @see #setProfileName(String)
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#getProfiledElementDefn_ProfileName()
	 * @model
	 * @generated
	 */
	String getProfileName();

	/**
	 * Sets the value of the '{@link org.hl7.fhir.definitions.ecore.fhir.ProfiledElementDefn#getProfileName <em>Profile Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Profile Name</em>' attribute.
	 * @see #getProfileName()
	 * @generated
	 */
	void setProfileName(String value);

} // ProfiledElementDefn
