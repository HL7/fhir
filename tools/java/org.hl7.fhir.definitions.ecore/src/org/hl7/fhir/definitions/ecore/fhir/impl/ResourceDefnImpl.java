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
import org.hl7.fhir.definitions.ecore.fhir.Example;
import org.hl7.fhir.definitions.ecore.fhir.FhirPackage;
import org.hl7.fhir.definitions.ecore.fhir.ResourceDefn;
import org.hl7.fhir.definitions.ecore.fhir.SearchParameter;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Defn</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ResourceDefnImpl#isSandbox <em>Sandbox</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ResourceDefnImpl#getExample <em>Example</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ResourceDefnImpl#getSearch <em>Search</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ResourceDefnImpl#isFuture <em>Future</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceDefnImpl extends CompositeTypeDefnImpl implements ResourceDefn {
	/**
	 * The default value of the '{@link #isSandbox() <em>Sandbox</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSandbox()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SANDBOX_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSandbox() <em>Sandbox</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSandbox()
	 * @generated
	 * @ordered
	 */
	protected boolean sandbox = SANDBOX_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExample() <em>Example</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExample()
	 * @generated
	 * @ordered
	 */
	protected EList<Example> example;

	/**
	 * The cached value of the '{@link #getSearch() <em>Search</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSearch()
	 * @generated
	 * @ordered
	 */
	protected EList<SearchParameter> search;

	/**
	 * The default value of the '{@link #isFuture() <em>Future</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFuture()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FUTURE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFuture() <em>Future</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFuture()
	 * @generated
	 * @ordered
	 */
	protected boolean future = FUTURE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceDefnImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FhirPackage.Literals.RESOURCE_DEFN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Example> getExample() {
		if (example == null) {
			example = new EObjectContainmentEList<Example>(Example.class, this, FhirPackage.RESOURCE_DEFN__EXAMPLE);
		}
		return example;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SearchParameter> getSearch() {
		if (search == null) {
			search = new EObjectContainmentEList<SearchParameter>(SearchParameter.class, this, FhirPackage.RESOURCE_DEFN__SEARCH);
		}
		return search;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSandbox() {
		return sandbox;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSandbox(boolean newSandbox) {
		boolean oldSandbox = sandbox;
		sandbox = newSandbox;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.RESOURCE_DEFN__SANDBOX, oldSandbox, sandbox));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFuture() {
		return future;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFuture(boolean newFuture) {
		boolean oldFuture = future;
		future = newFuture;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.RESOURCE_DEFN__FUTURE, oldFuture, future));
	}

	

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FhirPackage.RESOURCE_DEFN__EXAMPLE:
				return ((InternalEList<?>)getExample()).basicRemove(otherEnd, msgs);
			case FhirPackage.RESOURCE_DEFN__SEARCH:
				return ((InternalEList<?>)getSearch()).basicRemove(otherEnd, msgs);
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
			case FhirPackage.RESOURCE_DEFN__SANDBOX:
				return isSandbox();
			case FhirPackage.RESOURCE_DEFN__EXAMPLE:
				return getExample();
			case FhirPackage.RESOURCE_DEFN__SEARCH:
				return getSearch();
			case FhirPackage.RESOURCE_DEFN__FUTURE:
				return isFuture();
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
			case FhirPackage.RESOURCE_DEFN__SANDBOX:
				setSandbox((Boolean)newValue);
				return;
			case FhirPackage.RESOURCE_DEFN__EXAMPLE:
				getExample().clear();
				getExample().addAll((Collection<? extends Example>)newValue);
				return;
			case FhirPackage.RESOURCE_DEFN__SEARCH:
				getSearch().clear();
				getSearch().addAll((Collection<? extends SearchParameter>)newValue);
				return;
			case FhirPackage.RESOURCE_DEFN__FUTURE:
				setFuture((Boolean)newValue);
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
			case FhirPackage.RESOURCE_DEFN__SANDBOX:
				setSandbox(SANDBOX_EDEFAULT);
				return;
			case FhirPackage.RESOURCE_DEFN__EXAMPLE:
				getExample().clear();
				return;
			case FhirPackage.RESOURCE_DEFN__SEARCH:
				getSearch().clear();
				return;
			case FhirPackage.RESOURCE_DEFN__FUTURE:
				setFuture(FUTURE_EDEFAULT);
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
			case FhirPackage.RESOURCE_DEFN__SANDBOX:
				return sandbox != SANDBOX_EDEFAULT;
			case FhirPackage.RESOURCE_DEFN__EXAMPLE:
				return example != null && !example.isEmpty();
			case FhirPackage.RESOURCE_DEFN__SEARCH:
				return search != null && !search.isEmpty();
			case FhirPackage.RESOURCE_DEFN__FUTURE:
				return future != FUTURE_EDEFAULT;
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
		result.append(" (sandbox: ");
		result.append(sandbox);
		result.append(", future: ");
		result.append(future);
		result.append(')');
		return result.toString();
	}

	

	
} //ResourceDefnImpl
