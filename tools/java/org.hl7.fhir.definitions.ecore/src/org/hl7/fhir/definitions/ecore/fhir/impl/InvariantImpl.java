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
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.hl7.fhir.definitions.ecore.fhir.FhirPackage;
import org.hl7.fhir.definitions.ecore.fhir.Invariant;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Invariant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.InvariantImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.InvariantImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.InvariantImpl#getHuman <em>Human</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.InvariantImpl#getOcl <em>Ocl</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.InvariantImpl#getXpath <em>Xpath</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InvariantImpl extends EObjectImpl implements Invariant {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getHuman() <em>Human</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHuman()
	 * @generated
	 * @ordered
	 */
	protected static final String HUMAN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHuman() <em>Human</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHuman()
	 * @generated
	 * @ordered
	 */
	protected String human = HUMAN_EDEFAULT;

	/**
	 * The default value of the '{@link #getOcl() <em>Ocl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOcl()
	 * @generated
	 * @ordered
	 */
	protected static final String OCL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOcl() <em>Ocl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOcl()
	 * @generated
	 * @ordered
	 */
	protected String ocl = OCL_EDEFAULT;

	/**
	 * The default value of the '{@link #getXpath() <em>Xpath</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXpath()
	 * @generated
	 * @ordered
	 */
	protected static final String XPATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getXpath() <em>Xpath</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXpath()
	 * @generated
	 * @ordered
	 */
	protected String xpath = XPATH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InvariantImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FhirPackage.Literals.INVARIANT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.INVARIANT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.INVARIANT__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getHuman() {
		return human;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHuman(String newHuman) {
		String oldHuman = human;
		human = newHuman;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.INVARIANT__HUMAN, oldHuman, human));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getOcl() {
		return ocl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOcl(String newOcl) {
		String oldOcl = ocl;
		ocl = newOcl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.INVARIANT__OCL, oldOcl, ocl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getXpath() {
		return xpath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setXpath(String newXpath) {
		String oldXpath = xpath;
		xpath = newXpath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.INVARIANT__XPATH, oldXpath, xpath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FhirPackage.INVARIANT__NAME:
				return getName();
			case FhirPackage.INVARIANT__DESCRIPTION:
				return getDescription();
			case FhirPackage.INVARIANT__HUMAN:
				return getHuman();
			case FhirPackage.INVARIANT__OCL:
				return getOcl();
			case FhirPackage.INVARIANT__XPATH:
				return getXpath();
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
			case FhirPackage.INVARIANT__NAME:
				setName((String)newValue);
				return;
			case FhirPackage.INVARIANT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case FhirPackage.INVARIANT__HUMAN:
				setHuman((String)newValue);
				return;
			case FhirPackage.INVARIANT__OCL:
				setOcl((String)newValue);
				return;
			case FhirPackage.INVARIANT__XPATH:
				setXpath((String)newValue);
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
			case FhirPackage.INVARIANT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case FhirPackage.INVARIANT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case FhirPackage.INVARIANT__HUMAN:
				setHuman(HUMAN_EDEFAULT);
				return;
			case FhirPackage.INVARIANT__OCL:
				setOcl(OCL_EDEFAULT);
				return;
			case FhirPackage.INVARIANT__XPATH:
				setXpath(XPATH_EDEFAULT);
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
			case FhirPackage.INVARIANT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case FhirPackage.INVARIANT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case FhirPackage.INVARIANT__HUMAN:
				return HUMAN_EDEFAULT == null ? human != null : !HUMAN_EDEFAULT.equals(human);
			case FhirPackage.INVARIANT__OCL:
				return OCL_EDEFAULT == null ? ocl != null : !OCL_EDEFAULT.equals(ocl);
			case FhirPackage.INVARIANT__XPATH:
				return XPATH_EDEFAULT == null ? xpath != null : !XPATH_EDEFAULT.equals(xpath);
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
		result.append(" (name: ");
		result.append(name);
		result.append(", description: ");
		result.append(description);
		result.append(", human: ");
		result.append(human);
		result.append(", ocl: ");
		result.append(ocl);
		result.append(", xpath: ");
		result.append(xpath);
		result.append(')');
		return result.toString();
	}

} //InvariantImpl
