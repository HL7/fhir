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
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.hl7.fhir.definitions.ecore.fhir.BindingDefn;
import org.hl7.fhir.definitions.ecore.fhir.BindingType;
import org.hl7.fhir.definitions.ecore.fhir.DefinedCode;
import org.hl7.fhir.definitions.ecore.fhir.FhirPackage;
import org.hl7.fhir.definitions.ecore.fhir.NameScope;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Binding Defn</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.BindingDefnImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.BindingDefnImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.BindingDefnImpl#getFullName <em>Full Name</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.BindingDefnImpl#getBinding <em>Binding</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.BindingDefnImpl#getReference <em>Reference</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.BindingDefnImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.BindingDefnImpl#getCode <em>Code</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.BindingDefnImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.BindingDefnImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.BindingDefnImpl#getDefinition <em>Definition</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.BindingDefnImpl#isExample <em>Example</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.BindingDefnImpl#getV2Map <em>V2 Map</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.BindingDefnImpl#getV3Map <em>V3 Map</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BindingDefnImpl extends EObjectImpl implements BindingDefn {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final int ID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected int id = ID_EDEFAULT;

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
	 * The default value of the '{@link #getBinding() <em>Binding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBinding()
	 * @generated
	 * @ordered
	 */
	protected static final BindingType BINDING_EDEFAULT = BindingType.UNBOUND;

	/**
	 * The cached value of the '{@link #getBinding() <em>Binding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBinding()
	 * @generated
	 * @ordered
	 */
	protected BindingType binding = BINDING_EDEFAULT;

	/**
	 * The default value of the '{@link #getReference() <em>Reference</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReference()
	 * @generated
	 * @ordered
	 */
	protected static final String REFERENCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReference() <em>Reference</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReference()
	 * @generated
	 * @ordered
	 */
	protected String reference = REFERENCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected String source = SOURCE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCode() <em>Code</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCode()
	 * @generated
	 * @ordered
	 */
	protected EList<DefinedCode> code;

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
	 * The default value of the '{@link #getDefinition() <em>Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinition()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFINITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefinition() <em>Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinition()
	 * @generated
	 * @ordered
	 */
	protected String definition = DEFINITION_EDEFAULT;

	/**
	 * The default value of the '{@link #isExample() <em>Example</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExample()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXAMPLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isExample() <em>Example</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExample()
	 * @generated
	 * @ordered
	 */
	protected boolean example = EXAMPLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getV2Map() <em>V2 Map</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getV2Map()
	 * @generated
	 * @ordered
	 */
  protected static final String V2_MAP_EDEFAULT = null;

  /**
	 * The cached value of the '{@link #getV2Map() <em>V2 Map</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getV2Map()
	 * @generated
	 * @ordered
	 */
  protected String v2Map = V2_MAP_EDEFAULT;

  /**
	 * The default value of the '{@link #getV3Map() <em>V3 Map</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getV3Map()
	 * @generated
	 * @ordered
	 */
  protected static final String V3_MAP_EDEFAULT = null;

  /**
	 * The cached value of the '{@link #getV3Map() <em>V3 Map</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getV3Map()
	 * @generated
	 * @ordered
	 */
  protected String v3Map = V3_MAP_EDEFAULT;

  /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BindingDefnImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FhirPackage.Literals.BINDING_DEFN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(int newId) {
		int oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.BINDING_DEFN__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.BINDING_DEFN__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.BINDING_DEFN__FULL_NAME, oldFullName, fullName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BindingType getBinding() {
		return binding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBinding(BindingType newBinding) {
		BindingType oldBinding = binding;
		binding = newBinding == null ? BINDING_EDEFAULT : newBinding;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.BINDING_DEFN__BINDING, oldBinding, binding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public String getReference() {
		return reference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public void setReference(String newReference) {
		String oldReference = reference;
		reference = newReference;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.BINDING_DEFN__REFERENCE, oldReference, reference));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSource(String newSource) {
		String oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.BINDING_DEFN__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EList<DefinedCode> getCode() {
		if (code == null) {
			code = new EObjectContainmentEList<DefinedCode>(DefinedCode.class, this, FhirPackage.BINDING_DEFN__CODE);
		}
		return code;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NameScope getParent() {
		if (eContainerFeatureID() != FhirPackage.BINDING_DEFN__PARENT) return null;
		return (NameScope)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(NameScope newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, FhirPackage.BINDING_DEFN__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setParent(NameScope newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != FhirPackage.BINDING_DEFN__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, FhirPackage.NAME_SCOPE__BINDING, NameScope.class, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.BINDING_DEFN__PARENT, newParent, newParent));
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
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.BINDING_DEFN__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public String getDefinition() {
		return definition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public void setDefinition(String newDefinition) {
		String oldDefinition = definition;
		definition = newDefinition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.BINDING_DEFN__DEFINITION, oldDefinition, definition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public boolean isExample() {
		return example;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public void setExample(boolean newExample) {
		boolean oldExample = example;
		example = newExample;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.BINDING_DEFN__EXAMPLE, oldExample, example));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public String getV2Map() {
		return v2Map;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public void setV2Map(String newV2Map) {
		String oldV2Map = v2Map;
		v2Map = newV2Map;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.BINDING_DEFN__V2_MAP, oldV2Map, v2Map));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public String getV3Map() {
		return v3Map;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public void setV3Map(String newV3Map) {
		String oldV3Map = v3Map;
		v3Map = newV3Map;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.BINDING_DEFN__V3_MAP, oldV3Map, v3Map));
	}

  /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isGloballyDefined() {
		return getParent().getContainingScope() == null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FhirPackage.BINDING_DEFN__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((NameScope)otherEnd, msgs);
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
			case FhirPackage.BINDING_DEFN__CODE:
				return ((InternalEList<?>)getCode()).basicRemove(otherEnd, msgs);
			case FhirPackage.BINDING_DEFN__PARENT:
				return basicSetParent(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case FhirPackage.BINDING_DEFN__PARENT:
				return eInternalContainer().eInverseRemove(this, FhirPackage.NAME_SCOPE__BINDING, NameScope.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FhirPackage.BINDING_DEFN__ID:
				return getId();
			case FhirPackage.BINDING_DEFN__NAME:
				return getName();
			case FhirPackage.BINDING_DEFN__FULL_NAME:
				return getFullName();
			case FhirPackage.BINDING_DEFN__BINDING:
				return getBinding();
			case FhirPackage.BINDING_DEFN__REFERENCE:
				return getReference();
			case FhirPackage.BINDING_DEFN__SOURCE:
				return getSource();
			case FhirPackage.BINDING_DEFN__CODE:
				return getCode();
			case FhirPackage.BINDING_DEFN__PARENT:
				return getParent();
			case FhirPackage.BINDING_DEFN__DESCRIPTION:
				return getDescription();
			case FhirPackage.BINDING_DEFN__DEFINITION:
				return getDefinition();
			case FhirPackage.BINDING_DEFN__EXAMPLE:
				return isExample();
			case FhirPackage.BINDING_DEFN__V2_MAP:
				return getV2Map();
			case FhirPackage.BINDING_DEFN__V3_MAP:
				return getV3Map();
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
			case FhirPackage.BINDING_DEFN__ID:
				setId((Integer)newValue);
				return;
			case FhirPackage.BINDING_DEFN__NAME:
				setName((String)newValue);
				return;
			case FhirPackage.BINDING_DEFN__FULL_NAME:
				setFullName((String)newValue);
				return;
			case FhirPackage.BINDING_DEFN__BINDING:
				setBinding((BindingType)newValue);
				return;
			case FhirPackage.BINDING_DEFN__REFERENCE:
				setReference((String)newValue);
				return;
			case FhirPackage.BINDING_DEFN__SOURCE:
				setSource((String)newValue);
				return;
			case FhirPackage.BINDING_DEFN__CODE:
				getCode().clear();
				getCode().addAll((Collection<? extends DefinedCode>)newValue);
				return;
			case FhirPackage.BINDING_DEFN__PARENT:
				setParent((NameScope)newValue);
				return;
			case FhirPackage.BINDING_DEFN__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case FhirPackage.BINDING_DEFN__DEFINITION:
				setDefinition((String)newValue);
				return;
			case FhirPackage.BINDING_DEFN__EXAMPLE:
				setExample((Boolean)newValue);
				return;
			case FhirPackage.BINDING_DEFN__V2_MAP:
				setV2Map((String)newValue);
				return;
			case FhirPackage.BINDING_DEFN__V3_MAP:
				setV3Map((String)newValue);
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
			case FhirPackage.BINDING_DEFN__ID:
				setId(ID_EDEFAULT);
				return;
			case FhirPackage.BINDING_DEFN__NAME:
				setName(NAME_EDEFAULT);
				return;
			case FhirPackage.BINDING_DEFN__FULL_NAME:
				setFullName(FULL_NAME_EDEFAULT);
				return;
			case FhirPackage.BINDING_DEFN__BINDING:
				setBinding(BINDING_EDEFAULT);
				return;
			case FhirPackage.BINDING_DEFN__REFERENCE:
				setReference(REFERENCE_EDEFAULT);
				return;
			case FhirPackage.BINDING_DEFN__SOURCE:
				setSource(SOURCE_EDEFAULT);
				return;
			case FhirPackage.BINDING_DEFN__CODE:
				getCode().clear();
				return;
			case FhirPackage.BINDING_DEFN__PARENT:
				setParent((NameScope)null);
				return;
			case FhirPackage.BINDING_DEFN__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case FhirPackage.BINDING_DEFN__DEFINITION:
				setDefinition(DEFINITION_EDEFAULT);
				return;
			case FhirPackage.BINDING_DEFN__EXAMPLE:
				setExample(EXAMPLE_EDEFAULT);
				return;
			case FhirPackage.BINDING_DEFN__V2_MAP:
				setV2Map(V2_MAP_EDEFAULT);
				return;
			case FhirPackage.BINDING_DEFN__V3_MAP:
				setV3Map(V3_MAP_EDEFAULT);
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
			case FhirPackage.BINDING_DEFN__ID:
				return id != ID_EDEFAULT;
			case FhirPackage.BINDING_DEFN__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case FhirPackage.BINDING_DEFN__FULL_NAME:
				return FULL_NAME_EDEFAULT == null ? fullName != null : !FULL_NAME_EDEFAULT.equals(fullName);
			case FhirPackage.BINDING_DEFN__BINDING:
				return binding != BINDING_EDEFAULT;
			case FhirPackage.BINDING_DEFN__REFERENCE:
				return REFERENCE_EDEFAULT == null ? reference != null : !REFERENCE_EDEFAULT.equals(reference);
			case FhirPackage.BINDING_DEFN__SOURCE:
				return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
			case FhirPackage.BINDING_DEFN__CODE:
				return code != null && !code.isEmpty();
			case FhirPackage.BINDING_DEFN__PARENT:
				return getParent() != null;
			case FhirPackage.BINDING_DEFN__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case FhirPackage.BINDING_DEFN__DEFINITION:
				return DEFINITION_EDEFAULT == null ? definition != null : !DEFINITION_EDEFAULT.equals(definition);
			case FhirPackage.BINDING_DEFN__EXAMPLE:
				return example != EXAMPLE_EDEFAULT;
			case FhirPackage.BINDING_DEFN__V2_MAP:
				return V2_MAP_EDEFAULT == null ? v2Map != null : !V2_MAP_EDEFAULT.equals(v2Map);
			case FhirPackage.BINDING_DEFN__V3_MAP:
				return V3_MAP_EDEFAULT == null ? v3Map != null : !V3_MAP_EDEFAULT.equals(v3Map);
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
		result.append(" (id: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(", fullName: ");
		result.append(fullName);
		result.append(", binding: ");
		result.append(binding);
		result.append(", reference: ");
		result.append(reference);
		result.append(", source: ");
		result.append(source);
		result.append(", description: ");
		result.append(description);
		result.append(", definition: ");
		result.append(definition);
		result.append(", example: ");
		result.append(example);
		result.append(", v2Map: ");
		result.append(v2Map);
		result.append(", v3Map: ");
		result.append(v3Map);
		result.append(')');
		return result.toString();
	}

} //BindingDefnImpl
