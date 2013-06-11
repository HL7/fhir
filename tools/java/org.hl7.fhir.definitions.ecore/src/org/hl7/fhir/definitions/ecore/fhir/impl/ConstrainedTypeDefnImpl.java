/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.hl7.fhir.definitions.ecore.fhir.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.hl7.fhir.definitions.ecore.fhir.ConstrainedTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.FhirPackage;
import org.hl7.fhir.definitions.ecore.fhir.Invariant;
import org.hl7.fhir.definitions.ecore.fhir.TypeRef;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constrained Type Defn</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ConstrainedTypeDefnImpl#getConstrainedBaseType <em>Constrained Base Type</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ConstrainedTypeDefnImpl#getDetail <em>Detail</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConstrainedTypeDefnImpl extends TypeDefnImpl implements ConstrainedTypeDefn {
	/**
	 * The cached value of the '{@link #getConstrainedBaseType() <em>Constrained Base Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstrainedBaseType()
	 * @generated
	 * @ordered
	 */
	protected TypeRef constrainedBaseType;

	/**
	 * The cached value of the '{@link #getDetail() <em>Detail</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDetail()
	 * @generated
	 * @ordered
	 */
	protected EList<Invariant> detail;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConstrainedTypeDefnImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FhirPackage.Literals.CONSTRAINED_TYPE_DEFN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeRef getConstrainedBaseType() {
		return constrainedBaseType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConstrainedBaseType(TypeRef newConstrainedBaseType, NotificationChain msgs) {
		TypeRef oldConstrainedBaseType = constrainedBaseType;
		constrainedBaseType = newConstrainedBaseType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FhirPackage.CONSTRAINED_TYPE_DEFN__CONSTRAINED_BASE_TYPE, oldConstrainedBaseType, newConstrainedBaseType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstrainedBaseType(TypeRef newConstrainedBaseType) {
		if (newConstrainedBaseType != constrainedBaseType) {
			NotificationChain msgs = null;
			if (constrainedBaseType != null)
				msgs = ((InternalEObject)constrainedBaseType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FhirPackage.CONSTRAINED_TYPE_DEFN__CONSTRAINED_BASE_TYPE, null, msgs);
			if (newConstrainedBaseType != null)
				msgs = ((InternalEObject)newConstrainedBaseType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FhirPackage.CONSTRAINED_TYPE_DEFN__CONSTRAINED_BASE_TYPE, null, msgs);
			msgs = basicSetConstrainedBaseType(newConstrainedBaseType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.CONSTRAINED_TYPE_DEFN__CONSTRAINED_BASE_TYPE, newConstrainedBaseType, newConstrainedBaseType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Invariant> getDetail() {
		if (detail == null) {
			detail = new EObjectContainmentEList<Invariant>(Invariant.class, this, FhirPackage.CONSTRAINED_TYPE_DEFN__DETAIL);
		}
		return detail;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FhirPackage.CONSTRAINED_TYPE_DEFN__CONSTRAINED_BASE_TYPE:
				return basicSetConstrainedBaseType(null, msgs);
			case FhirPackage.CONSTRAINED_TYPE_DEFN__DETAIL:
				return ((InternalEList<?>)getDetail()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FhirPackage.CONSTRAINED_TYPE_DEFN__CONSTRAINED_BASE_TYPE:
				return getConstrainedBaseType();
			case FhirPackage.CONSTRAINED_TYPE_DEFN__DETAIL:
				return getDetail();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case FhirPackage.CONSTRAINED_TYPE_DEFN__CONSTRAINED_BASE_TYPE:
				setConstrainedBaseType((TypeRef)newValue);
				return;
			case FhirPackage.CONSTRAINED_TYPE_DEFN__DETAIL:
				getDetail().clear();
				getDetail().addAll((Collection<? extends Invariant>)newValue);
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
			case FhirPackage.CONSTRAINED_TYPE_DEFN__CONSTRAINED_BASE_TYPE:
				setConstrainedBaseType((TypeRef)null);
				return;
			case FhirPackage.CONSTRAINED_TYPE_DEFN__DETAIL:
				getDetail().clear();
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
			case FhirPackage.CONSTRAINED_TYPE_DEFN__CONSTRAINED_BASE_TYPE:
				return constrainedBaseType != null;
			case FhirPackage.CONSTRAINED_TYPE_DEFN__DETAIL:
				return detail != null && !detail.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ConstrainedTypeDefnImpl
