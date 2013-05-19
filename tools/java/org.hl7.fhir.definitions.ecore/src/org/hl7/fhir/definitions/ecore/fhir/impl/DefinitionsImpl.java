/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.hl7.fhir.definitions.ecore.fhir.impl;

import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.hl7.fhir.definitions.ecore.fhir.BindingDefn;
import org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.ConstrainedTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.Definitions;
import org.hl7.fhir.definitions.ecore.fhir.EventDefn;
import org.hl7.fhir.definitions.ecore.fhir.FhirFactory;
import org.hl7.fhir.definitions.ecore.fhir.FhirPackage;
import org.hl7.fhir.definitions.ecore.fhir.NameScope;
import org.hl7.fhir.definitions.ecore.fhir.PrimitiveDefn;
import org.hl7.fhir.definitions.ecore.fhir.ProfileDefn;
import org.hl7.fhir.definitions.ecore.fhir.ResourceDefn;
import org.hl7.fhir.definitions.ecore.fhir.TypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.TypeRef;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Definitions</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.DefinitionsImpl#getTypes <em>Types</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.DefinitionsImpl#getBindings <em>Bindings</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.DefinitionsImpl#getDate <em>Date</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.DefinitionsImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.DefinitionsImpl#getProfiles <em>Profiles</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.DefinitionsImpl#getEvents <em>Events</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.DefinitionsImpl#isInternal <em>Internal</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.DefinitionsImpl#getPrimitives <em>Primitives</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DefinitionsImpl extends EObjectImpl implements Definitions {
	/**
	 * The cached value of the '{@link #getTypes() <em>Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<TypeDefn> types;

	/**
	 * The cached value of the '{@link #getBindings() <em>Bindings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBindings()
	 * @generated
	 * @ordered
	 */
	protected EList<BindingDefn> bindings;

	/**
	 * The default value of the '{@link #getDate() <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDate() <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDate()
	 * @generated
	 * @ordered
	 */
	protected Date date = DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProfiles() <em>Profiles</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProfiles()
	 * @generated
	 * @ordered
	 */
	protected EList<ProfileDefn> profiles;

	/**
	 * The cached value of the '{@link #getEvents() <em>Events</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEvents()
	 * @generated
	 * @ordered
	 */
	protected EList<EventDefn> events;

	/**
	 * The default value of the '{@link #isInternal() <em>Internal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInternal()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INTERNAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInternal() <em>Internal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInternal()
	 * @generated
	 * @ordered
	 */
	protected boolean internal = INTERNAL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPrimitives() <em>Primitives</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrimitives()
	 * @generated
	 * @ordered
	 */
	protected EList<PrimitiveDefn> primitives;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DefinitionsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FhirPackage.Literals.DEFINITIONS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<BindingDefn> getBindings() {
		if (bindings == null) {
			bindings = new EObjectContainmentWithInverseEList<BindingDefn>(BindingDefn.class, this, FhirPackage.DEFINITIONS__BINDINGS, FhirPackage.BINDING_DEFN__PARENT);
		}
		return bindings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getDate() {
		return date;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDate(Date newDate) {
		Date oldDate = date;
		date = newDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.DEFINITIONS__DATE, oldDate, date));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.DEFINITIONS__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<TypeDefn> getTypes() {
		if (types == null) {
			types = new EObjectContainmentWithInverseEList<TypeDefn>(TypeDefn.class, this, FhirPackage.DEFINITIONS__TYPES, FhirPackage.TYPE_DEFN__SCOPE);
		}
		return types;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<EventDefn> getEvents() {
		if (events == null) {
			events = new EObjectContainmentEList<EventDefn>(EventDefn.class, this, FhirPackage.DEFINITIONS__EVENTS);
		}
		return events;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isInternal() {
		return internal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInternal(boolean newInternal) {
		boolean oldInternal = internal;
		internal = newInternal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.DEFINITIONS__INTERNAL, oldInternal, internal));
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PrimitiveDefn> getPrimitives() {
		if (primitives == null) {
			primitives = new EObjectContainmentEList<PrimitiveDefn>(PrimitiveDefn.class, this, FhirPackage.DEFINITIONS__PRIMITIVES);
		}
		return primitives;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public TypeDefn findType(String fullName) {
		String[] nameParts = fullName.split("\\.");
		
		NameScope current = this;
		String name = nameParts[0];
		
		if( nameParts.length == 2)
		{
			for( TypeDefn type : current.getTypes() )
				if(type.getName().equals(nameParts[0]))
					current = (CompositeTypeDefn)type;
			name = nameParts[1];
		}
		
		for( TypeDefn defn : current.getTypes() )
			if( defn.getName().equals(name) )
				return defn;
		
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public BindingDefn findBinding(String fullName) {
		String[] nameParts = fullName.split("\\.");
	
		NameScope current = this;
		String name = nameParts[0];
		
		if( nameParts.length == 2)
		{
			for( TypeDefn type : current.getTypes() )
				if(type.getName().equals(nameParts[0]))
					current = (CompositeTypeDefn)type;
			name = nameParts[1];
		}
		
		for( BindingDefn defn : current.getBindings() )
			if( defn.getName().equals(name) )
				return defn;
		
		return null;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public PrimitiveDefn findPrimitive(String name) {
		for( PrimitiveDefn primitive : primitives )
			if( primitive.getName().equals(name))
				return primitive;
		
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<ResourceDefn> getResources() {
		EList<ResourceDefn> result = new BasicEList<ResourceDefn>();
		
		for( TypeDefn t : this.getTypes() )
		{		
			if( t.isResource() )
				result.add((ResourceDefn)t);
		}
		
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ProfileDefn> getProfiles() {
		if (profiles == null) {
			profiles = new EObjectContainmentEList<ProfileDefn>(ProfileDefn.class, this, FhirPackage.DEFINITIONS__PROFILES);
		}
		return profiles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * List all CompositeTypes that are defined in this scope. This excludes the types
	 * inherited from parent scopes.
	 * <!-- end-user-doc -->
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
		return null;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public TypeDefn resolve(TypeRef ref) {
		return findType(ref.getFullName());
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
			case FhirPackage.DEFINITIONS__TYPES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTypes()).basicAdd(otherEnd, msgs);
			case FhirPackage.DEFINITIONS__BINDINGS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getBindings()).basicAdd(otherEnd, msgs);
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
			case FhirPackage.DEFINITIONS__TYPES:
				return ((InternalEList<?>)getTypes()).basicRemove(otherEnd, msgs);
			case FhirPackage.DEFINITIONS__BINDINGS:
				return ((InternalEList<?>)getBindings()).basicRemove(otherEnd, msgs);
			case FhirPackage.DEFINITIONS__PROFILES:
				return ((InternalEList<?>)getProfiles()).basicRemove(otherEnd, msgs);
			case FhirPackage.DEFINITIONS__EVENTS:
				return ((InternalEList<?>)getEvents()).basicRemove(otherEnd, msgs);
			case FhirPackage.DEFINITIONS__PRIMITIVES:
				return ((InternalEList<?>)getPrimitives()).basicRemove(otherEnd, msgs);
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
			case FhirPackage.DEFINITIONS__TYPES:
				return getTypes();
			case FhirPackage.DEFINITIONS__BINDINGS:
				return getBindings();
			case FhirPackage.DEFINITIONS__DATE:
				return getDate();
			case FhirPackage.DEFINITIONS__VERSION:
				return getVersion();
			case FhirPackage.DEFINITIONS__PROFILES:
				return getProfiles();
			case FhirPackage.DEFINITIONS__EVENTS:
				return getEvents();
			case FhirPackage.DEFINITIONS__INTERNAL:
				return isInternal();
			case FhirPackage.DEFINITIONS__PRIMITIVES:
				return getPrimitives();
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
			case FhirPackage.DEFINITIONS__TYPES:
				getTypes().clear();
				getTypes().addAll((Collection<? extends TypeDefn>)newValue);
				return;
			case FhirPackage.DEFINITIONS__BINDINGS:
				getBindings().clear();
				getBindings().addAll((Collection<? extends BindingDefn>)newValue);
				return;
			case FhirPackage.DEFINITIONS__DATE:
				setDate((Date)newValue);
				return;
			case FhirPackage.DEFINITIONS__VERSION:
				setVersion((String)newValue);
				return;
			case FhirPackage.DEFINITIONS__PROFILES:
				getProfiles().clear();
				getProfiles().addAll((Collection<? extends ProfileDefn>)newValue);
				return;
			case FhirPackage.DEFINITIONS__EVENTS:
				getEvents().clear();
				getEvents().addAll((Collection<? extends EventDefn>)newValue);
				return;
			case FhirPackage.DEFINITIONS__INTERNAL:
				setInternal((Boolean)newValue);
				return;
			case FhirPackage.DEFINITIONS__PRIMITIVES:
				getPrimitives().clear();
				getPrimitives().addAll((Collection<? extends PrimitiveDefn>)newValue);
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
			case FhirPackage.DEFINITIONS__TYPES:
				getTypes().clear();
				return;
			case FhirPackage.DEFINITIONS__BINDINGS:
				getBindings().clear();
				return;
			case FhirPackage.DEFINITIONS__DATE:
				setDate(DATE_EDEFAULT);
				return;
			case FhirPackage.DEFINITIONS__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case FhirPackage.DEFINITIONS__PROFILES:
				getProfiles().clear();
				return;
			case FhirPackage.DEFINITIONS__EVENTS:
				getEvents().clear();
				return;
			case FhirPackage.DEFINITIONS__INTERNAL:
				setInternal(INTERNAL_EDEFAULT);
				return;
			case FhirPackage.DEFINITIONS__PRIMITIVES:
				getPrimitives().clear();
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
			case FhirPackage.DEFINITIONS__TYPES:
				return types != null && !types.isEmpty();
			case FhirPackage.DEFINITIONS__BINDINGS:
				return bindings != null && !bindings.isEmpty();
			case FhirPackage.DEFINITIONS__DATE:
				return DATE_EDEFAULT == null ? date != null : !DATE_EDEFAULT.equals(date);
			case FhirPackage.DEFINITIONS__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case FhirPackage.DEFINITIONS__PROFILES:
				return profiles != null && !profiles.isEmpty();
			case FhirPackage.DEFINITIONS__EVENTS:
				return events != null && !events.isEmpty();
			case FhirPackage.DEFINITIONS__INTERNAL:
				return internal != INTERNAL_EDEFAULT;
			case FhirPackage.DEFINITIONS__PRIMITIVES:
				return primitives != null && !primitives.isEmpty();
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
		result.append(" (date: ");
		result.append(date);
		result.append(", version: ");
		result.append(version);
		result.append(", internal: ");
		result.append(internal);
		result.append(')');
		return result.toString();
	}
	
	
	public static Definitions build( Date date, String version )
	{
		Definitions result = FhirFactory.eINSTANCE.createDefinitions();
		
		result.setDate(date);
		result.setVersion(version);
		
		return result;
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
	
} //DefinitionsImpl
