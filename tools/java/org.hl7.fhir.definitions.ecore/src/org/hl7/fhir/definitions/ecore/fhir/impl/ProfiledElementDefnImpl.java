/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.hl7.fhir.definitions.ecore.fhir.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.hl7.fhir.definitions.ecore.fhir.FhirPackage;
import org.hl7.fhir.definitions.ecore.fhir.ProfiledElementDefn;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Profiled Element Defn</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ProfiledElementDefnImpl#isInherited <em>Inherited</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ProfiledElementDefnImpl#getAggregation <em>Aggregation</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ProfiledElementDefnImpl#getFixedValue <em>Fixed Value</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ProfiledElementDefnImpl#getTargetUri <em>Target Uri</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ProfiledElementDefnImpl#getProfileName <em>Profile Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProfiledElementDefnImpl extends ElementDefnImpl implements ProfiledElementDefn {
	/**
	 * The default value of the '{@link #isInherited() <em>Inherited</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInherited()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INHERITED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInherited() <em>Inherited</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInherited()
	 * @generated
	 * @ordered
	 */
	protected boolean inherited = INHERITED_EDEFAULT;

	/**
	 * The default value of the '{@link #getAggregation() <em>Aggregation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAggregation()
	 * @generated
	 * @ordered
	 */
	protected static final String AGGREGATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAggregation() <em>Aggregation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAggregation()
	 * @generated
	 * @ordered
	 */
	protected String aggregation = AGGREGATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getFixedValue() <em>Fixed Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFixedValue()
	 * @generated
	 * @ordered
	 */
	protected static final String FIXED_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFixedValue() <em>Fixed Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFixedValue()
	 * @generated
	 * @ordered
	 */
	protected String fixedValue = FIXED_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetUri() <em>Target Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetUri()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetUri() <em>Target Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetUri()
	 * @generated
	 * @ordered
	 */
	protected String targetUri = TARGET_URI_EDEFAULT;

	/**
	 * The default value of the '{@link #getProfileName() <em>Profile Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProfileName()
	 * @generated
	 * @ordered
	 */
	protected static final String PROFILE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProfileName() <em>Profile Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProfileName()
	 * @generated
	 * @ordered
	 */
	protected String profileName = PROFILE_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProfiledElementDefnImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FhirPackage.Literals.PROFILED_ELEMENT_DEFN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isInherited() {
		return inherited;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInherited(boolean newInherited) {
		boolean oldInherited = inherited;
		inherited = newInherited;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.PROFILED_ELEMENT_DEFN__INHERITED, oldInherited, inherited));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getAggregation() {
		return aggregation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAggregation(String newAggregation) {
		String oldAggregation = aggregation;
		aggregation = newAggregation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.PROFILED_ELEMENT_DEFN__AGGREGATION, oldAggregation, aggregation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFixedValue() {
		return fixedValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFixedValue(String newFixedValue) {
		String oldFixedValue = fixedValue;
		fixedValue = newFixedValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.PROFILED_ELEMENT_DEFN__FIXED_VALUE, oldFixedValue, fixedValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTargetUri() {
		return targetUri;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTargetUri(String newTargetUri) {
		String oldTargetUri = targetUri;
		targetUri = newTargetUri;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.PROFILED_ELEMENT_DEFN__TARGET_URI, oldTargetUri, targetUri));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getProfileName() {
		return profileName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProfileName(String newProfileName) {
		String oldProfileName = profileName;
		profileName = newProfileName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.PROFILED_ELEMENT_DEFN__PROFILE_NAME, oldProfileName, profileName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FhirPackage.PROFILED_ELEMENT_DEFN__INHERITED:
				return isInherited();
			case FhirPackage.PROFILED_ELEMENT_DEFN__AGGREGATION:
				return getAggregation();
			case FhirPackage.PROFILED_ELEMENT_DEFN__FIXED_VALUE:
				return getFixedValue();
			case FhirPackage.PROFILED_ELEMENT_DEFN__TARGET_URI:
				return getTargetUri();
			case FhirPackage.PROFILED_ELEMENT_DEFN__PROFILE_NAME:
				return getProfileName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case FhirPackage.PROFILED_ELEMENT_DEFN__INHERITED:
				setInherited((Boolean)newValue);
				return;
			case FhirPackage.PROFILED_ELEMENT_DEFN__AGGREGATION:
				setAggregation((String)newValue);
				return;
			case FhirPackage.PROFILED_ELEMENT_DEFN__FIXED_VALUE:
				setFixedValue((String)newValue);
				return;
			case FhirPackage.PROFILED_ELEMENT_DEFN__TARGET_URI:
				setTargetUri((String)newValue);
				return;
			case FhirPackage.PROFILED_ELEMENT_DEFN__PROFILE_NAME:
				setProfileName((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case FhirPackage.PROFILED_ELEMENT_DEFN__INHERITED:
				setInherited(INHERITED_EDEFAULT);
				return;
			case FhirPackage.PROFILED_ELEMENT_DEFN__AGGREGATION:
				setAggregation(AGGREGATION_EDEFAULT);
				return;
			case FhirPackage.PROFILED_ELEMENT_DEFN__FIXED_VALUE:
				setFixedValue(FIXED_VALUE_EDEFAULT);
				return;
			case FhirPackage.PROFILED_ELEMENT_DEFN__TARGET_URI:
				setTargetUri(TARGET_URI_EDEFAULT);
				return;
			case FhirPackage.PROFILED_ELEMENT_DEFN__PROFILE_NAME:
				setProfileName(PROFILE_NAME_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case FhirPackage.PROFILED_ELEMENT_DEFN__INHERITED:
				return inherited != INHERITED_EDEFAULT;
			case FhirPackage.PROFILED_ELEMENT_DEFN__AGGREGATION:
				return AGGREGATION_EDEFAULT == null ? aggregation != null : !AGGREGATION_EDEFAULT.equals(aggregation);
			case FhirPackage.PROFILED_ELEMENT_DEFN__FIXED_VALUE:
				return FIXED_VALUE_EDEFAULT == null ? fixedValue != null : !FIXED_VALUE_EDEFAULT.equals(fixedValue);
			case FhirPackage.PROFILED_ELEMENT_DEFN__TARGET_URI:
				return TARGET_URI_EDEFAULT == null ? targetUri != null : !TARGET_URI_EDEFAULT.equals(targetUri);
			case FhirPackage.PROFILED_ELEMENT_DEFN__PROFILE_NAME:
				return PROFILE_NAME_EDEFAULT == null ? profileName != null : !PROFILE_NAME_EDEFAULT.equals(profileName);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (inherited: ");
		result.append(inherited);
		result.append(", aggregation: ");
		result.append(aggregation);
		result.append(", fixedValue: ");
		result.append(fixedValue);
		result.append(", targetUri: ");
		result.append(targetUri);
		result.append(", profileName: ");
		result.append(profileName);
		result.append(')');
		return result.toString();
	}

} //ProfiledElementDefnImpl
