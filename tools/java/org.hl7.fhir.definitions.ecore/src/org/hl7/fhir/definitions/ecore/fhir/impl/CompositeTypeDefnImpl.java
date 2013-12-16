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
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.hl7.fhir.definitions.ecore.fhir.BindingDefn;
import org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.ConstrainedTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.ElementDefn;
import org.hl7.fhir.definitions.ecore.fhir.FhirPackage;
import org.hl7.fhir.definitions.ecore.fhir.Invariant;
import org.hl7.fhir.definitions.ecore.fhir.NameScope;
import org.hl7.fhir.definitions.ecore.fhir.TypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.TypeRef;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Composite Type Defn</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.CompositeTypeDefnImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.CompositeTypeDefnImpl#getBinding <em>Binding</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.CompositeTypeDefnImpl#getElement <em>Element</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.CompositeTypeDefnImpl#getInvariant <em>Invariant</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.CompositeTypeDefnImpl#isUnnamedElementGroup <em>Unnamed Element Group</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.CompositeTypeDefnImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.CompositeTypeDefnImpl#getBaseType <em>Base Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CompositeTypeDefnImpl extends TypeDefnImpl implements CompositeTypeDefn {
	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected EList<TypeDefn> type;
	/**
	 * The cached value of the '{@link #getBinding() <em>Binding</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBinding()
	 * @generated
	 * @ordered
	 */
	protected EList<BindingDefn> binding;
	/**
	 * The cached value of the '{@link #getElement() <em>Element</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElement()
	 * @generated
	 * @ordered
	 */
	protected EList<ElementDefn> element;
	/**
	 * The cached value of the '{@link #getInvariant() <em>Invariant</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvariant()
	 * @generated
	 * @ordered
	 */
	protected EList<Invariant> invariant;
	/**
	 * The default value of the '{@link #isUnnamedElementGroup() <em>Unnamed Element Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnnamedElementGroup()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UNNAMED_ELEMENT_GROUP_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isUnnamedElementGroup() <em>Unnamed Element Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnnamedElementGroup()
	 * @generated
	 * @ordered
	 */
	protected boolean unnamedElementGroup = UNNAMED_ELEMENT_GROUP_EDEFAULT;
	/**
	 * The default value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ABSTRACT_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected boolean abstract_ = ABSTRACT_EDEFAULT;
	/**
	 * The cached value of the '{@link #getBaseType() <em>Base Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseType()
	 * @generated
	 * @ordered
	 */
	protected TypeRef baseType;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompositeTypeDefnImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FhirPackage.Literals.COMPOSITE_TYPE_DEFN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TypeDefn> getType() {
		if (type == null) {
			type = new EObjectContainmentWithInverseEList<TypeDefn>(TypeDefn.class, this, FhirPackage.COMPOSITE_TYPE_DEFN__TYPE, FhirPackage.TYPE_DEFN__SCOPE);
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BindingDefn> getBinding() {
		if (binding == null) {
			binding = new EObjectContainmentWithInverseEList<BindingDefn>(BindingDefn.class, this, FhirPackage.COMPOSITE_TYPE_DEFN__BINDING, FhirPackage.BINDING_DEFN__PARENT);
		}
		return binding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ElementDefn> getElement() {
		if (element == null) {
			element = new EObjectContainmentWithInverseEList<ElementDefn>(ElementDefn.class, this, FhirPackage.COMPOSITE_TYPE_DEFN__ELEMENT, FhirPackage.ELEMENT_DEFN__PARENT_TYPE);
		}
		return element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Invariant> getInvariant() {
		if (invariant == null) {
			invariant = new EObjectContainmentEList<Invariant>(Invariant.class, this, FhirPackage.COMPOSITE_TYPE_DEFN__INVARIANT);
		}
		return invariant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isUnnamedElementGroup() {
		return unnamedElementGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUnnamedElementGroup(boolean newUnnamedElementGroup) {
		boolean oldUnnamedElementGroup = unnamedElementGroup;
		unnamedElementGroup = newUnnamedElementGroup;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.COMPOSITE_TYPE_DEFN__UNNAMED_ELEMENT_GROUP, oldUnnamedElementGroup, unnamedElementGroup));
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAbstract() {
		return abstract_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbstract(boolean newAbstract) {
		boolean oldAbstract = abstract_;
		abstract_ = newAbstract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.COMPOSITE_TYPE_DEFN__ABSTRACT, oldAbstract, abstract_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeRef getBaseType() {
		return baseType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBaseType(TypeRef newBaseType, NotificationChain msgs) {
		TypeRef oldBaseType = baseType;
		baseType = newBaseType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FhirPackage.COMPOSITE_TYPE_DEFN__BASE_TYPE, oldBaseType, newBaseType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBaseType(TypeRef newBaseType) {
		if (newBaseType != baseType) {
			NotificationChain msgs = null;
			if (baseType != null)
				msgs = ((InternalEObject)baseType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FhirPackage.COMPOSITE_TYPE_DEFN__BASE_TYPE, null, msgs);
			if (newBaseType != null)
				msgs = ((InternalEObject)newBaseType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FhirPackage.COMPOSITE_TYPE_DEFN__BASE_TYPE, null, msgs);
			msgs = basicSetBaseType(newBaseType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.COMPOSITE_TYPE_DEFN__BASE_TYPE, newBaseType, newBaseType));
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<ElementDefn> getAllElements() {
		EList<ElementDefn> result = new BasicEList<ElementDefn>();
		
		if( this.getBaseType() != null )
		{
			CompositeTypeDefn base = (CompositeTypeDefnImpl)resolve(this.getBaseType());
			result.addAll(base.getAllElements());
		}
		
		result.addAll(this.getElement());
		
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<CompositeTypeDefn> getLocalCompositeTypes() {
		return ns().getLocalCompositeTypes();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<ConstrainedTypeDefn> getLocalConstrainedTypes() {
		return ns().getLocalConstrainedTypes();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public NameScope getContainingScope() {
		return this.getScope();
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public TypeDefn resolve(TypeRef ref) {
		NameScope outer = this;
		
		while( outer.getContainingScope() != null )
			outer = outer.getContainingScope();
		
		return outer.resolve(ref);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FhirPackage.COMPOSITE_TYPE_DEFN__TYPE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getType()).basicAdd(otherEnd, msgs);
			case FhirPackage.COMPOSITE_TYPE_DEFN__BINDING:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getBinding()).basicAdd(otherEnd, msgs);
			case FhirPackage.COMPOSITE_TYPE_DEFN__ELEMENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getElement()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FhirPackage.COMPOSITE_TYPE_DEFN__TYPE:
				return ((InternalEList<?>)getType()).basicRemove(otherEnd, msgs);
			case FhirPackage.COMPOSITE_TYPE_DEFN__BINDING:
				return ((InternalEList<?>)getBinding()).basicRemove(otherEnd, msgs);
			case FhirPackage.COMPOSITE_TYPE_DEFN__ELEMENT:
				return ((InternalEList<?>)getElement()).basicRemove(otherEnd, msgs);
			case FhirPackage.COMPOSITE_TYPE_DEFN__INVARIANT:
				return ((InternalEList<?>)getInvariant()).basicRemove(otherEnd, msgs);
			case FhirPackage.COMPOSITE_TYPE_DEFN__BASE_TYPE:
				return basicSetBaseType(null, msgs);
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
			case FhirPackage.COMPOSITE_TYPE_DEFN__TYPE:
				return getType();
			case FhirPackage.COMPOSITE_TYPE_DEFN__BINDING:
				return getBinding();
			case FhirPackage.COMPOSITE_TYPE_DEFN__ELEMENT:
				return getElement();
			case FhirPackage.COMPOSITE_TYPE_DEFN__INVARIANT:
				return getInvariant();
			case FhirPackage.COMPOSITE_TYPE_DEFN__UNNAMED_ELEMENT_GROUP:
				return isUnnamedElementGroup();
			case FhirPackage.COMPOSITE_TYPE_DEFN__ABSTRACT:
				return isAbstract();
			case FhirPackage.COMPOSITE_TYPE_DEFN__BASE_TYPE:
				return getBaseType();
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
			case FhirPackage.COMPOSITE_TYPE_DEFN__TYPE:
				getType().clear();
				getType().addAll((Collection<? extends TypeDefn>)newValue);
				return;
			case FhirPackage.COMPOSITE_TYPE_DEFN__BINDING:
				getBinding().clear();
				getBinding().addAll((Collection<? extends BindingDefn>)newValue);
				return;
			case FhirPackage.COMPOSITE_TYPE_DEFN__ELEMENT:
				getElement().clear();
				getElement().addAll((Collection<? extends ElementDefn>)newValue);
				return;
			case FhirPackage.COMPOSITE_TYPE_DEFN__INVARIANT:
				getInvariant().clear();
				getInvariant().addAll((Collection<? extends Invariant>)newValue);
				return;
			case FhirPackage.COMPOSITE_TYPE_DEFN__UNNAMED_ELEMENT_GROUP:
				setUnnamedElementGroup((Boolean)newValue);
				return;
			case FhirPackage.COMPOSITE_TYPE_DEFN__ABSTRACT:
				setAbstract((Boolean)newValue);
				return;
			case FhirPackage.COMPOSITE_TYPE_DEFN__BASE_TYPE:
				setBaseType((TypeRef)newValue);
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
			case FhirPackage.COMPOSITE_TYPE_DEFN__TYPE:
				getType().clear();
				return;
			case FhirPackage.COMPOSITE_TYPE_DEFN__BINDING:
				getBinding().clear();
				return;
			case FhirPackage.COMPOSITE_TYPE_DEFN__ELEMENT:
				getElement().clear();
				return;
			case FhirPackage.COMPOSITE_TYPE_DEFN__INVARIANT:
				getInvariant().clear();
				return;
			case FhirPackage.COMPOSITE_TYPE_DEFN__UNNAMED_ELEMENT_GROUP:
				setUnnamedElementGroup(UNNAMED_ELEMENT_GROUP_EDEFAULT);
				return;
			case FhirPackage.COMPOSITE_TYPE_DEFN__ABSTRACT:
				setAbstract(ABSTRACT_EDEFAULT);
				return;
			case FhirPackage.COMPOSITE_TYPE_DEFN__BASE_TYPE:
				setBaseType((TypeRef)null);
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
			case FhirPackage.COMPOSITE_TYPE_DEFN__TYPE:
				return type != null && !type.isEmpty();
			case FhirPackage.COMPOSITE_TYPE_DEFN__BINDING:
				return binding != null && !binding.isEmpty();
			case FhirPackage.COMPOSITE_TYPE_DEFN__ELEMENT:
				return element != null && !element.isEmpty();
			case FhirPackage.COMPOSITE_TYPE_DEFN__INVARIANT:
				return invariant != null && !invariant.isEmpty();
			case FhirPackage.COMPOSITE_TYPE_DEFN__UNNAMED_ELEMENT_GROUP:
				return unnamedElementGroup != UNNAMED_ELEMENT_GROUP_EDEFAULT;
			case FhirPackage.COMPOSITE_TYPE_DEFN__ABSTRACT:
				return abstract_ != ABSTRACT_EDEFAULT;
			case FhirPackage.COMPOSITE_TYPE_DEFN__BASE_TYPE:
				return baseType != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == NameScope.class) {
			switch (derivedFeatureID) {
				case FhirPackage.COMPOSITE_TYPE_DEFN__TYPE: return FhirPackage.NAME_SCOPE__TYPE;
				case FhirPackage.COMPOSITE_TYPE_DEFN__BINDING: return FhirPackage.NAME_SCOPE__BINDING;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == NameScope.class) {
			switch (baseFeatureID) {
				case FhirPackage.NAME_SCOPE__TYPE: return FhirPackage.COMPOSITE_TYPE_DEFN__TYPE;
				case FhirPackage.NAME_SCOPE__BINDING: return FhirPackage.COMPOSITE_TYPE_DEFN__BINDING;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (unnamedElementGroup: ");
		result.append(unnamedElementGroup);
		result.append(", abstract: ");
		result.append(abstract_);
		result.append(')');
		return result.toString();
	}
	

	private NameScopeImpl nameScope;
	
	private NameScopeImpl ns()
	{
		if( nameScope == null )
		{
			nameScope = new NameScopeImpl(this);
		}
		
		return nameScope;
	}
} //CompositeTypeDefnImpl
