/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.hl7.fhir.definitions.ecore.fhir.impl;

import java.util.Collection;
import java.util.Hashtable;

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
import org.hl7.fhir.definitions.ecore.fhir.Annotations;
import org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.ElementDefn;
import org.hl7.fhir.definitions.ecore.fhir.FhirPackage;
import org.hl7.fhir.definitions.ecore.fhir.InvariantRef;
import org.hl7.fhir.definitions.ecore.fhir.Mapping;
import org.hl7.fhir.definitions.ecore.fhir.TypeRef;
import org.hl7.fhir.definitions.ecore.fhir.XmlFormatHint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element Defn</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ElementDefnImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ElementDefnImpl#getMinCardinality <em>Min Cardinality</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ElementDefnImpl#getMaxCardinality <em>Max Cardinality</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ElementDefnImpl#isIsModifier <em>Is Modifier</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ElementDefnImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ElementDefnImpl#getMappings <em>Mappings</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ElementDefnImpl#getExampleValue <em>Example Value</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ElementDefnImpl#getContent <em>Content</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ElementDefnImpl#getAnnotation <em>Annotation</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ElementDefnImpl#getInvariant <em>Invariant</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ElementDefnImpl#getParentType <em>Parent Type</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ElementDefnImpl#getParentElement <em>Parent Element</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ElementDefnImpl#isPrimitiveContents <em>Primitive Contents</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ElementDefnImpl#getXmlFormatHint <em>Xml Format Hint</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ElementDefnImpl#isSummaryItem <em>Summary Item</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ElementDefnImpl extends EObjectImpl implements ElementDefn {
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
	 * The default value of the '{@link #getMinCardinality() <em>Min Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinCardinality()
	 * @generated
	 * @ordered
	 */
	protected static final int MIN_CARDINALITY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMinCardinality() <em>Min Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinCardinality()
	 * @generated
	 * @ordered
	 */
	protected int minCardinality = MIN_CARDINALITY_EDEFAULT;

	/**
	 * This is true if the Min Cardinality attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean minCardinalityESet;

	/**
	 * The default value of the '{@link #getMaxCardinality() <em>Max Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxCardinality()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_CARDINALITY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaxCardinality() <em>Max Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxCardinality()
	 * @generated
	 * @ordered
	 */
	protected int maxCardinality = MAX_CARDINALITY_EDEFAULT;

	/**
	 * This is true if the Max Cardinality attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean maxCardinalityESet;

	/**
	 * The default value of the '{@link #isIsModifier() <em>Is Modifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsModifier()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_MODIFIER_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsModifier() <em>Is Modifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsModifier()
	 * @generated
	 * @ordered
	 */
	protected boolean isModifier = IS_MODIFIER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected EList<TypeRef> type;

	/**
	 * The cached value of the '{@link #getMappings() <em>Mappings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappings()
	 * @generated
	 * @ordered
	 */
	protected EList<Mapping> mappings;

	/**
	 * The default value of the '{@link #getExampleValue() <em>Example Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExampleValue()
	 * @generated
	 * @ordered
	 */
	protected static final String EXAMPLE_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExampleValue() <em>Example Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExampleValue()
	 * @generated
	 * @ordered
	 */
	protected String exampleValue = EXAMPLE_VALUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getContent() <em>Content</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContent()
	 * @generated
	 * @ordered
	 */
	protected ElementDefn content;

	/**
	 * The cached value of the '{@link #getAnnotation() <em>Annotation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnnotation()
	 * @generated
	 * @ordered
	 */
	protected Annotations annotation;

	/**
	 * The cached value of the '{@link #getInvariant() <em>Invariant</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvariant()
	 * @generated
	 * @ordered
	 */
	protected EList<InvariantRef> invariant;

	/**
	 * The cached value of the '{@link #getParentElement() <em>Parent Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentElement()
	 * @generated
	 * @ordered
	 */
	protected ElementDefn parentElement;

	/**
	 * The default value of the '{@link #isPrimitiveContents() <em>Primitive Contents</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrimitiveContents()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PRIMITIVE_CONTENTS_EDEFAULT = false;

		/**
	 * The cached value of the '{@link #isPrimitiveContents() <em>Primitive Contents</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrimitiveContents()
	 * @generated
	 * @ordered
	 */
	protected boolean primitiveContents = PRIMITIVE_CONTENTS_EDEFAULT;

	/**
	 * The default value of the '{@link #getXmlFormatHint() <em>Xml Format Hint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXmlFormatHint()
	 * @generated
	 * @ordered
	 */
	protected static final XmlFormatHint XML_FORMAT_HINT_EDEFAULT = XmlFormatHint.ELEMENT;

		/**
	 * The cached value of the '{@link #getXmlFormatHint() <em>Xml Format Hint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXmlFormatHint()
	 * @generated
	 * @ordered
	 */
	protected XmlFormatHint xmlFormatHint = XML_FORMAT_HINT_EDEFAULT;

	/**
	 * The default value of the '{@link #isSummaryItem() <em>Summary Item</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSummaryItem()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SUMMARY_ITEM_EDEFAULT = false;

		/**
	 * The cached value of the '{@link #isSummaryItem() <em>Summary Item</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSummaryItem()
	 * @generated
	 * @ordered
	 */
	protected boolean summaryItem = SUMMARY_ITEM_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElementDefnImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FhirPackage.Literals.ELEMENT_DEFN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getMaxCardinality() {
		return maxCardinality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMaxCardinality(int newMaxCardinality) {
		int oldMaxCardinality = maxCardinality;
		maxCardinality = newMaxCardinality;
		boolean oldMaxCardinalityESet = maxCardinalityESet;
		maxCardinalityESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.ELEMENT_DEFN__MAX_CARDINALITY, oldMaxCardinality, maxCardinality, !oldMaxCardinalityESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetMaxCardinality() {
		int oldMaxCardinality = maxCardinality;
		boolean oldMaxCardinalityESet = maxCardinalityESet;
		maxCardinality = MAX_CARDINALITY_EDEFAULT;
		maxCardinalityESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, FhirPackage.ELEMENT_DEFN__MAX_CARDINALITY, oldMaxCardinality, MAX_CARDINALITY_EDEFAULT, oldMaxCardinalityESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetMaxCardinality() {
		return maxCardinalityESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public boolean isIsModifier() {
		return isModifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public void setIsModifier(boolean newIsModifier) {
		boolean oldIsModifier = isModifier;
		isModifier = newIsModifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.ELEMENT_DEFN__IS_MODIFIER, oldIsModifier, isModifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getMinCardinality() {
		return minCardinality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMinCardinality(int newMinCardinality) {
		int oldMinCardinality = minCardinality;
		minCardinality = newMinCardinality;
		boolean oldMinCardinalityESet = minCardinalityESet;
		minCardinalityESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.ELEMENT_DEFN__MIN_CARDINALITY, oldMinCardinality, minCardinality, !oldMinCardinalityESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetMinCardinality() {
		int oldMinCardinality = minCardinality;
		boolean oldMinCardinalityESet = minCardinalityESet;
		minCardinality = MIN_CARDINALITY_EDEFAULT;
		minCardinalityESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, FhirPackage.ELEMENT_DEFN__MIN_CARDINALITY, oldMinCardinality, MIN_CARDINALITY_EDEFAULT, oldMinCardinalityESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetMinCardinality() {
		return minCardinalityESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeTypeDefn getParentTypeGen() {
		if (eContainerFeatureID() != FhirPackage.ELEMENT_DEFN__PARENT_TYPE) return null;
		return (CompositeTypeDefn)eContainer();
	}
	
	@Override
	public CompositeTypeDefn getParentType() {
		CompositeTypeDefn parent = getParentTypeGen();
		
		if( parent != null ) return parent;
		
		if( getParentElement() != null )
			return getParentElement().getParentType();
		else
			return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParentType(CompositeTypeDefn newParentType, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParentType, FhirPackage.ELEMENT_DEFN__PARENT_TYPE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setParentType(CompositeTypeDefn newParentType) {
		if (newParentType != eInternalContainer() || (eContainerFeatureID() != FhirPackage.ELEMENT_DEFN__PARENT_TYPE && newParentType != null)) {
			if (EcoreUtil.isAncestor(this, newParentType))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParentType != null)
				msgs = ((InternalEObject)newParentType).eInverseAdd(this, FhirPackage.COMPOSITE_TYPE_DEFN__ELEMENT, CompositeTypeDefn.class, msgs);
			msgs = basicSetParentType(newParentType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.ELEMENT_DEFN__PARENT_TYPE, newParentType, newParentType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ElementDefn getParentElement() {
		if (parentElement != null && parentElement.eIsProxy()) {
			InternalEObject oldParentElement = (InternalEObject)parentElement;
			parentElement = (ElementDefn)eResolveProxy(oldParentElement);
			if (parentElement != oldParentElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FhirPackage.ELEMENT_DEFN__PARENT_ELEMENT, oldParentElement, parentElement));
			}
		}
		return parentElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElementDefn basicGetParentElement() {
		return parentElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setParentElement(ElementDefn newParentElement) {
		ElementDefn oldParentElement = parentElement;
		parentElement = newParentElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.ELEMENT_DEFN__PARENT_ELEMENT, oldParentElement, parentElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public boolean isPrimitiveContents() {
		return primitiveContents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public void setPrimitiveContents(boolean newPrimitiveContents) {
		boolean oldPrimitiveContents = primitiveContents;
		primitiveContents = newPrimitiveContents;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.ELEMENT_DEFN__PRIMITIVE_CONTENTS, oldPrimitiveContents, primitiveContents));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public XmlFormatHint getXmlFormatHint() {
		return xmlFormatHint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public void setXmlFormatHint(XmlFormatHint newXmlFormatHint) {
		XmlFormatHint oldXmlFormatHint = xmlFormatHint;
		xmlFormatHint = newXmlFormatHint == null ? XML_FORMAT_HINT_EDEFAULT : newXmlFormatHint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.ELEMENT_DEFN__XML_FORMAT_HINT, oldXmlFormatHint, xmlFormatHint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public boolean isSummaryItem() {
		return summaryItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public void setSummaryItem(boolean newSummaryItem) {
		boolean oldSummaryItem = summaryItem;
		summaryItem = newSummaryItem;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.ELEMENT_DEFN__SUMMARY_ITEM, oldSummaryItem, summaryItem));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
//	public boolean hasNestedElements() {
//		return getElements() != null && getElements().size() > 0;
//	}


//	/**
//	 * <!-- begin-user-doc -->
//	 * <!-- end-user-doc -->
//	 * @generated NOT
//	 */
//	public boolean isResourceReference() {
//		return getTypes().size() > 0 &&
//				getTypes().get(0).getName().equals( TypeRef.RESOURCEREF_TYPE_NAME );
//	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isRepeating() {
		return getMaxCardinality() == -1 || getMaxCardinality() > 1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isPolymorph() {
		return getType() != null && (getType().size() > 1 
				|| getType().get(0).getName().equals(TypeRef.ELEMENT_TYPE_NAME)
				|| getType().get(0).getName().equals(TypeRef.RESOURCE_TYPE_NAME));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
  public boolean containsReference() {
		return getType() != null && getType().size() == 1 &&
				getType().get(0).getName().equals(TypeRef.RESOURCE_TYPE_NAME);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
  public boolean isPrimitiveValueElement() {
		return isPrimitiveContents() && getName().equals("value");
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isXhtml() 
	{
		if( this.getType() == null || this.getType().size() != 1 ) return false;
		
		return getType().get(0).getName().equals(TypeRef.XHTML_PSEUDOTYPE_NAME);	
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
			case FhirPackage.ELEMENT_DEFN__PARENT_TYPE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParentType((CompositeTypeDefn)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EList<TypeRef> getType() {
		if (type == null) {
			type = new EObjectContainmentEList<TypeRef>(TypeRef.class, this, FhirPackage.ELEMENT_DEFN__TYPE);
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Annotations getAnnotation() {
		return annotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAnnotation(Annotations newAnnotation, NotificationChain msgs) {
		Annotations oldAnnotation = annotation;
		annotation = newAnnotation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FhirPackage.ELEMENT_DEFN__ANNOTATION, oldAnnotation, newAnnotation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAnnotation(Annotations newAnnotation) {
		if (newAnnotation != annotation) {
			NotificationChain msgs = null;
			if (annotation != null)
				msgs = ((InternalEObject)annotation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FhirPackage.ELEMENT_DEFN__ANNOTATION, null, msgs);
			if (newAnnotation != null)
				msgs = ((InternalEObject)newAnnotation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FhirPackage.ELEMENT_DEFN__ANNOTATION, null, msgs);
			msgs = basicSetAnnotation(newAnnotation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.ELEMENT_DEFN__ANNOTATION, newAnnotation, newAnnotation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EList<InvariantRef> getInvariant() {
		if (invariant == null) {
			invariant = new EObjectContainmentEList<InvariantRef>(InvariantRef.class, this, FhirPackage.ELEMENT_DEFN__INVARIANT);
		}
		return invariant;
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
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.ELEMENT_DEFN__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Mapping> getMappings() {
		if (mappings == null) {
			mappings = new EObjectContainmentEList<Mapping>(Mapping.class, this, FhirPackage.ELEMENT_DEFN__MAPPINGS);
		}
		return mappings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getExampleValue() {
		return exampleValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setExampleValue(String newExampleValue) {
		String oldExampleValue = exampleValue;
		exampleValue = newExampleValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.ELEMENT_DEFN__EXAMPLE_VALUE, oldExampleValue, exampleValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ElementDefn getContent() {
		if (content != null && content.eIsProxy()) {
			InternalEObject oldContent = (InternalEObject)content;
			content = (ElementDefn)eResolveProxy(oldContent);
			if (content != oldContent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FhirPackage.ELEMENT_DEFN__CONTENT, oldContent, content));
			}
		}
		return content;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElementDefn basicGetContent() {
		return content;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContent(ElementDefn newContent) {
		ElementDefn oldContent = content;
		content = newContent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.ELEMENT_DEFN__CONTENT, oldContent, content));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FhirPackage.ELEMENT_DEFN__TYPE:
				return ((InternalEList<?>)getType()).basicRemove(otherEnd, msgs);
			case FhirPackage.ELEMENT_DEFN__MAPPINGS:
				return ((InternalEList<?>)getMappings()).basicRemove(otherEnd, msgs);
			case FhirPackage.ELEMENT_DEFN__ANNOTATION:
				return basicSetAnnotation(null, msgs);
			case FhirPackage.ELEMENT_DEFN__INVARIANT:
				return ((InternalEList<?>)getInvariant()).basicRemove(otherEnd, msgs);
			case FhirPackage.ELEMENT_DEFN__PARENT_TYPE:
				return basicSetParentType(null, msgs);
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
			case FhirPackage.ELEMENT_DEFN__PARENT_TYPE:
				return eInternalContainer().eInverseRemove(this, FhirPackage.COMPOSITE_TYPE_DEFN__ELEMENT, CompositeTypeDefn.class, msgs);
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
			case FhirPackage.ELEMENT_DEFN__NAME:
				return getName();
			case FhirPackage.ELEMENT_DEFN__MIN_CARDINALITY:
				return getMinCardinality();
			case FhirPackage.ELEMENT_DEFN__MAX_CARDINALITY:
				return getMaxCardinality();
			case FhirPackage.ELEMENT_DEFN__IS_MODIFIER:
				return isIsModifier();
			case FhirPackage.ELEMENT_DEFN__TYPE:
				return getType();
			case FhirPackage.ELEMENT_DEFN__MAPPINGS:
				return getMappings();
			case FhirPackage.ELEMENT_DEFN__EXAMPLE_VALUE:
				return getExampleValue();
			case FhirPackage.ELEMENT_DEFN__CONTENT:
				if (resolve) return getContent();
				return basicGetContent();
			case FhirPackage.ELEMENT_DEFN__ANNOTATION:
				return getAnnotation();
			case FhirPackage.ELEMENT_DEFN__INVARIANT:
				return getInvariant();
			case FhirPackage.ELEMENT_DEFN__PARENT_TYPE:
				return getParentType();
			case FhirPackage.ELEMENT_DEFN__PARENT_ELEMENT:
				if (resolve) return getParentElement();
				return basicGetParentElement();
			case FhirPackage.ELEMENT_DEFN__PRIMITIVE_CONTENTS:
				return isPrimitiveContents();
			case FhirPackage.ELEMENT_DEFN__XML_FORMAT_HINT:
				return getXmlFormatHint();
			case FhirPackage.ELEMENT_DEFN__SUMMARY_ITEM:
				return isSummaryItem();
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
			case FhirPackage.ELEMENT_DEFN__NAME:
				setName((String)newValue);
				return;
			case FhirPackage.ELEMENT_DEFN__MIN_CARDINALITY:
				setMinCardinality((Integer)newValue);
				return;
			case FhirPackage.ELEMENT_DEFN__MAX_CARDINALITY:
				setMaxCardinality((Integer)newValue);
				return;
			case FhirPackage.ELEMENT_DEFN__IS_MODIFIER:
				setIsModifier((Boolean)newValue);
				return;
			case FhirPackage.ELEMENT_DEFN__TYPE:
				getType().clear();
				getType().addAll((Collection<? extends TypeRef>)newValue);
				return;
			case FhirPackage.ELEMENT_DEFN__MAPPINGS:
				getMappings().clear();
				getMappings().addAll((Collection<? extends Mapping>)newValue);
				return;
			case FhirPackage.ELEMENT_DEFN__EXAMPLE_VALUE:
				setExampleValue((String)newValue);
				return;
			case FhirPackage.ELEMENT_DEFN__CONTENT:
				setContent((ElementDefn)newValue);
				return;
			case FhirPackage.ELEMENT_DEFN__ANNOTATION:
				setAnnotation((Annotations)newValue);
				return;
			case FhirPackage.ELEMENT_DEFN__INVARIANT:
				getInvariant().clear();
				getInvariant().addAll((Collection<? extends InvariantRef>)newValue);
				return;
			case FhirPackage.ELEMENT_DEFN__PARENT_TYPE:
				setParentType((CompositeTypeDefn)newValue);
				return;
			case FhirPackage.ELEMENT_DEFN__PARENT_ELEMENT:
				setParentElement((ElementDefn)newValue);
				return;
			case FhirPackage.ELEMENT_DEFN__PRIMITIVE_CONTENTS:
				setPrimitiveContents((Boolean)newValue);
				return;
			case FhirPackage.ELEMENT_DEFN__XML_FORMAT_HINT:
				setXmlFormatHint((XmlFormatHint)newValue);
				return;
			case FhirPackage.ELEMENT_DEFN__SUMMARY_ITEM:
				setSummaryItem((Boolean)newValue);
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
			case FhirPackage.ELEMENT_DEFN__NAME:
				setName(NAME_EDEFAULT);
				return;
			case FhirPackage.ELEMENT_DEFN__MIN_CARDINALITY:
				unsetMinCardinality();
				return;
			case FhirPackage.ELEMENT_DEFN__MAX_CARDINALITY:
				unsetMaxCardinality();
				return;
			case FhirPackage.ELEMENT_DEFN__IS_MODIFIER:
				setIsModifier(IS_MODIFIER_EDEFAULT);
				return;
			case FhirPackage.ELEMENT_DEFN__TYPE:
				getType().clear();
				return;
			case FhirPackage.ELEMENT_DEFN__MAPPINGS:
				getMappings().clear();
				return;
			case FhirPackage.ELEMENT_DEFN__EXAMPLE_VALUE:
				setExampleValue(EXAMPLE_VALUE_EDEFAULT);
				return;
			case FhirPackage.ELEMENT_DEFN__CONTENT:
				setContent((ElementDefn)null);
				return;
			case FhirPackage.ELEMENT_DEFN__ANNOTATION:
				setAnnotation((Annotations)null);
				return;
			case FhirPackage.ELEMENT_DEFN__INVARIANT:
				getInvariant().clear();
				return;
			case FhirPackage.ELEMENT_DEFN__PARENT_TYPE:
				setParentType((CompositeTypeDefn)null);
				return;
			case FhirPackage.ELEMENT_DEFN__PARENT_ELEMENT:
				setParentElement((ElementDefn)null);
				return;
			case FhirPackage.ELEMENT_DEFN__PRIMITIVE_CONTENTS:
				setPrimitiveContents(PRIMITIVE_CONTENTS_EDEFAULT);
				return;
			case FhirPackage.ELEMENT_DEFN__XML_FORMAT_HINT:
				setXmlFormatHint(XML_FORMAT_HINT_EDEFAULT);
				return;
			case FhirPackage.ELEMENT_DEFN__SUMMARY_ITEM:
				setSummaryItem(SUMMARY_ITEM_EDEFAULT);
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
			case FhirPackage.ELEMENT_DEFN__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case FhirPackage.ELEMENT_DEFN__MIN_CARDINALITY:
				return isSetMinCardinality();
			case FhirPackage.ELEMENT_DEFN__MAX_CARDINALITY:
				return isSetMaxCardinality();
			case FhirPackage.ELEMENT_DEFN__IS_MODIFIER:
				return isModifier != IS_MODIFIER_EDEFAULT;
			case FhirPackage.ELEMENT_DEFN__TYPE:
				return type != null && !type.isEmpty();
			case FhirPackage.ELEMENT_DEFN__MAPPINGS:
				return mappings != null && !mappings.isEmpty();
			case FhirPackage.ELEMENT_DEFN__EXAMPLE_VALUE:
				return EXAMPLE_VALUE_EDEFAULT == null ? exampleValue != null : !EXAMPLE_VALUE_EDEFAULT.equals(exampleValue);
			case FhirPackage.ELEMENT_DEFN__CONTENT:
				return content != null;
			case FhirPackage.ELEMENT_DEFN__ANNOTATION:
				return annotation != null;
			case FhirPackage.ELEMENT_DEFN__INVARIANT:
				return invariant != null && !invariant.isEmpty();
			case FhirPackage.ELEMENT_DEFN__PARENT_TYPE:
				return getParentType() != null;
			case FhirPackage.ELEMENT_DEFN__PARENT_ELEMENT:
				return parentElement != null;
			case FhirPackage.ELEMENT_DEFN__PRIMITIVE_CONTENTS:
				return primitiveContents != PRIMITIVE_CONTENTS_EDEFAULT;
			case FhirPackage.ELEMENT_DEFN__XML_FORMAT_HINT:
				return xmlFormatHint != XML_FORMAT_HINT_EDEFAULT;
			case FhirPackage.ELEMENT_DEFN__SUMMARY_ITEM:
				return summaryItem != SUMMARY_ITEM_EDEFAULT;
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
		result.append(", minCardinality: ");
		if (minCardinalityESet) result.append(minCardinality); else result.append("<unset>");
		result.append(", maxCardinality: ");
		if (maxCardinalityESet) result.append(maxCardinality); else result.append("<unset>");
		result.append(", isModifier: ");
		result.append(isModifier);
		result.append(", exampleValue: ");
		result.append(exampleValue);
		result.append(", primitiveContents: ");
		result.append(primitiveContents);
		result.append(", xmlFormatHint: ");
		result.append(xmlFormatHint);
		result.append(", summaryItem: ");
		result.append(summaryItem);
		result.append(')');
		return result.toString();
	}

	/* @generated NOT
	 * 
	 */
	private Hashtable<String,String> _generatorAnnotations = new Hashtable<String, String>();
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
  public Hashtable<String, String> getGeneratorAnnotations()
	{
		return _generatorAnnotations;
	}
	
	
} //ElementDefnImpl
