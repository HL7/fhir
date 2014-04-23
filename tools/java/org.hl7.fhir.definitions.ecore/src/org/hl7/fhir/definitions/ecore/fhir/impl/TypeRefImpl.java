/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.hl7.fhir.definitions.ecore.fhir.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.hl7.fhir.definitions.ecore.fhir.FhirPackage;
import org.hl7.fhir.definitions.ecore.fhir.TypeRef;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.TypeRefImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.TypeRefImpl#getFullName <em>Full Name</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.TypeRefImpl#getResourceParams <em>Resource Params</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.TypeRefImpl#getBindingRef <em>Binding Ref</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.TypeRefImpl#getFullBindingRef <em>Full Binding Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeRefImpl extends EObjectImpl implements TypeRef {
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
	 * The default value of the '{@link #getFullName() <em>Full Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFullName()
	 * @generated
	 * @ordered
	 */
	protected static final String FULL_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFullName() <em>Full Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFullName()
	 * @generated
	 * @ordered
	 */
	protected String fullName = FULL_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getResourceParams() <em>Resource Params</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceParams()
	 * @generated
	 * @ordered
	 */
	protected EList<String> resourceParams;

	/**
	 * The default value of the '{@link #getBindingRef() <em>Binding Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBindingRef()
	 * @generated
	 * @ordered
	 */
	protected static final String BINDING_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBindingRef() <em>Binding Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBindingRef()
	 * @generated
	 * @ordered
	 */
	protected String bindingRef = BINDING_REF_EDEFAULT;

	/**
	 * The default value of the '{@link #getFullBindingRef() <em>Full Binding Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFullBindingRef()
	 * @generated
	 * @ordered
	 */
	protected static final String FULL_BINDING_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFullBindingRef() <em>Full Binding Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFullBindingRef()
	 * @generated
	 * @ordered
	 */
	protected String fullBindingRef = FULL_BINDING_REF_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FhirPackage.Literals.TYPE_REF;
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
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.TYPE_REF__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFullName() {
		return fullName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFullName(String newFullName) {
		String oldFullName = fullName;
		fullName = newFullName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.TYPE_REF__FULL_NAME, oldFullName, fullName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getResourceParams() {
		if (resourceParams == null) {
			resourceParams = new EDataTypeUniqueEList<String>(String.class, this, FhirPackage.TYPE_REF__RESOURCE_PARAMS);
		}
		return resourceParams;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBindingRef() {
		return bindingRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBindingRef(String newBindingRef) {
		String oldBindingRef = bindingRef;
		bindingRef = newBindingRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.TYPE_REF__BINDING_REF, oldBindingRef, bindingRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFullBindingRef() {
		return fullBindingRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFullBindingRef(String newFullBindingRef) {
		String oldFullBindingRef = fullBindingRef;
		fullBindingRef = newFullBindingRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.TYPE_REF__FULL_BINDING_REF, oldFullBindingRef, fullBindingRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public boolean isBindable() {
		return this.getName().equals("code") ||
		this.getName().equals("CodeableConcept") ||
		this.getName().equals("Coding");
	}

	//	/**
//	 * <!-- begin-user-doc -->
//	 * <!-- end-user-doc -->
//	 * @generated NOT
//	 */
//	public boolean isGenericTypeRef() {
//		return getBoundParam() != null && 
//				!getName().equals(TypeRef.RESOURCEREF_TYPE_NAME);
//	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FhirPackage.TYPE_REF__NAME:
				return getName();
			case FhirPackage.TYPE_REF__FULL_NAME:
				return getFullName();
			case FhirPackage.TYPE_REF__RESOURCE_PARAMS:
				return getResourceParams();
			case FhirPackage.TYPE_REF__BINDING_REF:
				return getBindingRef();
			case FhirPackage.TYPE_REF__FULL_BINDING_REF:
				return getFullBindingRef();
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
			case FhirPackage.TYPE_REF__NAME:
				setName((String)newValue);
				return;
			case FhirPackage.TYPE_REF__FULL_NAME:
				setFullName((String)newValue);
				return;
			case FhirPackage.TYPE_REF__RESOURCE_PARAMS:
				getResourceParams().clear();
				getResourceParams().addAll((Collection<? extends String>)newValue);
				return;
			case FhirPackage.TYPE_REF__BINDING_REF:
				setBindingRef((String)newValue);
				return;
			case FhirPackage.TYPE_REF__FULL_BINDING_REF:
				setFullBindingRef((String)newValue);
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
			case FhirPackage.TYPE_REF__NAME:
				setName(NAME_EDEFAULT);
				return;
			case FhirPackage.TYPE_REF__FULL_NAME:
				setFullName(FULL_NAME_EDEFAULT);
				return;
			case FhirPackage.TYPE_REF__RESOURCE_PARAMS:
				getResourceParams().clear();
				return;
			case FhirPackage.TYPE_REF__BINDING_REF:
				setBindingRef(BINDING_REF_EDEFAULT);
				return;
			case FhirPackage.TYPE_REF__FULL_BINDING_REF:
				setFullBindingRef(FULL_BINDING_REF_EDEFAULT);
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
			case FhirPackage.TYPE_REF__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case FhirPackage.TYPE_REF__FULL_NAME:
				return FULL_NAME_EDEFAULT == null ? fullName != null : !FULL_NAME_EDEFAULT.equals(fullName);
			case FhirPackage.TYPE_REF__RESOURCE_PARAMS:
				return resourceParams != null && !resourceParams.isEmpty();
			case FhirPackage.TYPE_REF__BINDING_REF:
				return BINDING_REF_EDEFAULT == null ? bindingRef != null : !BINDING_REF_EDEFAULT.equals(bindingRef);
			case FhirPackage.TYPE_REF__FULL_BINDING_REF:
				return FULL_BINDING_REF_EDEFAULT == null ? fullBindingRef != null : !FULL_BINDING_REF_EDEFAULT.equals(fullBindingRef);
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
		result.append(", fullName: ");
		result.append(fullName);
		result.append(", resourceParams: ");
		result.append(resourceParams);
		result.append(", bindingRef: ");
		result.append(bindingRef);
		result.append(", fullBindingRef: ");
		result.append(fullBindingRef);
		result.append(')');
		return result.toString();
	}

	

} //TypeRefImpl
