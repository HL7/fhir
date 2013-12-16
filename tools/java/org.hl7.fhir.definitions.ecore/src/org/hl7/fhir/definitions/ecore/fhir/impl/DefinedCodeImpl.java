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
import org.hl7.fhir.definitions.ecore.fhir.DefinedCode;
import org.hl7.fhir.definitions.ecore.fhir.FhirPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Defined Code</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.DefinedCodeImpl#getCode <em>Code</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.DefinedCodeImpl#getDefinition <em>Definition</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.DefinedCodeImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.DefinedCodeImpl#getDisplay <em>Display</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.DefinedCodeImpl#getSystem <em>System</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.DefinedCodeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.DefinedCodeImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.DefinedCodeImpl#getV2Map <em>V2 Map</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.DefinedCodeImpl#getV3Map <em>V3 Map</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DefinedCodeImpl extends EObjectImpl implements DefinedCode {
	/**
	 * The default value of the '{@link #getCode() <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCode()
	 * @generated
	 * @ordered
	 */
	protected static final String CODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCode() <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCode()
	 * @generated
	 * @ordered
	 */
	protected String code = CODE_EDEFAULT;

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
	 * The default value of the '{@link #getComment() <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComment()
	 * @generated
	 * @ordered
	 */
	protected static final String COMMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getComment() <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComment()
	 * @generated
	 * @ordered
	 */
	protected String comment = COMMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getDisplay() <em>Display</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplay()
	 * @generated
	 * @ordered
	 */
	protected static final String DISPLAY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDisplay() <em>Display</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplay()
	 * @generated
	 * @ordered
	 */
	protected String display = DISPLAY_EDEFAULT;

	/**
	 * The default value of the '{@link #getSystem() <em>System</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystem()
	 * @generated
	 * @ordered
	 */
	protected static final String SYSTEM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSystem() <em>System</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystem()
	 * @generated
	 * @ordered
	 */
	protected String system = SYSTEM_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getParent() <em>Parent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParent()
	 * @generated
	 * @ordered
	 */
	protected static final String PARENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getParent() <em>Parent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParent()
	 * @generated
	 * @ordered
	 */
	protected String parent = PARENT_EDEFAULT;

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
	protected DefinedCodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FhirPackage.Literals.DEFINED_CODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCode() {
		return code;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCode(String newCode) {
		String oldCode = code;
		code = newCode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.DEFINED_CODE__CODE, oldCode, code));
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
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.DEFINED_CODE__DEFINITION, oldDefinition, definition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComment(String newComment) {
		String oldComment = comment;
		comment = newComment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.DEFINED_CODE__COMMENT, oldComment, comment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDisplay() {
		return display;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDisplay(String newDisplay) {
		String oldDisplay = display;
		display = newDisplay;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.DEFINED_CODE__DISPLAY, oldDisplay, display));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSystem() {
		return system;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSystem(String newSystem) {
		String oldSystem = system;
		system = newSystem;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.DEFINED_CODE__SYSTEM, oldSystem, system));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.DEFINED_CODE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getParent() {
		return parent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(String newParent) {
		String oldParent = parent;
		parent = newParent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.DEFINED_CODE__PARENT, oldParent, parent));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getV2Map() {
		return v2Map;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setV2Map(String newV2Map) {
		String oldV2Map = v2Map;
		v2Map = newV2Map;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.DEFINED_CODE__V2_MAP, oldV2Map, v2Map));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getV3Map() {
		return v3Map;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setV3Map(String newV3Map) {
		String oldV3Map = v3Map;
		v3Map = newV3Map;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.DEFINED_CODE__V3_MAP, oldV3Map, v3Map));
	}

  /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FhirPackage.DEFINED_CODE__CODE:
				return getCode();
			case FhirPackage.DEFINED_CODE__DEFINITION:
				return getDefinition();
			case FhirPackage.DEFINED_CODE__COMMENT:
				return getComment();
			case FhirPackage.DEFINED_CODE__DISPLAY:
				return getDisplay();
			case FhirPackage.DEFINED_CODE__SYSTEM:
				return getSystem();
			case FhirPackage.DEFINED_CODE__ID:
				return getId();
			case FhirPackage.DEFINED_CODE__PARENT:
				return getParent();
			case FhirPackage.DEFINED_CODE__V2_MAP:
				return getV2Map();
			case FhirPackage.DEFINED_CODE__V3_MAP:
				return getV3Map();
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
			case FhirPackage.DEFINED_CODE__CODE:
				setCode((String)newValue);
				return;
			case FhirPackage.DEFINED_CODE__DEFINITION:
				setDefinition((String)newValue);
				return;
			case FhirPackage.DEFINED_CODE__COMMENT:
				setComment((String)newValue);
				return;
			case FhirPackage.DEFINED_CODE__DISPLAY:
				setDisplay((String)newValue);
				return;
			case FhirPackage.DEFINED_CODE__SYSTEM:
				setSystem((String)newValue);
				return;
			case FhirPackage.DEFINED_CODE__ID:
				setId((String)newValue);
				return;
			case FhirPackage.DEFINED_CODE__PARENT:
				setParent((String)newValue);
				return;
			case FhirPackage.DEFINED_CODE__V2_MAP:
				setV2Map((String)newValue);
				return;
			case FhirPackage.DEFINED_CODE__V3_MAP:
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
			case FhirPackage.DEFINED_CODE__CODE:
				setCode(CODE_EDEFAULT);
				return;
			case FhirPackage.DEFINED_CODE__DEFINITION:
				setDefinition(DEFINITION_EDEFAULT);
				return;
			case FhirPackage.DEFINED_CODE__COMMENT:
				setComment(COMMENT_EDEFAULT);
				return;
			case FhirPackage.DEFINED_CODE__DISPLAY:
				setDisplay(DISPLAY_EDEFAULT);
				return;
			case FhirPackage.DEFINED_CODE__SYSTEM:
				setSystem(SYSTEM_EDEFAULT);
				return;
			case FhirPackage.DEFINED_CODE__ID:
				setId(ID_EDEFAULT);
				return;
			case FhirPackage.DEFINED_CODE__PARENT:
				setParent(PARENT_EDEFAULT);
				return;
			case FhirPackage.DEFINED_CODE__V2_MAP:
				setV2Map(V2_MAP_EDEFAULT);
				return;
			case FhirPackage.DEFINED_CODE__V3_MAP:
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
			case FhirPackage.DEFINED_CODE__CODE:
				return CODE_EDEFAULT == null ? code != null : !CODE_EDEFAULT.equals(code);
			case FhirPackage.DEFINED_CODE__DEFINITION:
				return DEFINITION_EDEFAULT == null ? definition != null : !DEFINITION_EDEFAULT.equals(definition);
			case FhirPackage.DEFINED_CODE__COMMENT:
				return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
			case FhirPackage.DEFINED_CODE__DISPLAY:
				return DISPLAY_EDEFAULT == null ? display != null : !DISPLAY_EDEFAULT.equals(display);
			case FhirPackage.DEFINED_CODE__SYSTEM:
				return SYSTEM_EDEFAULT == null ? system != null : !SYSTEM_EDEFAULT.equals(system);
			case FhirPackage.DEFINED_CODE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case FhirPackage.DEFINED_CODE__PARENT:
				return PARENT_EDEFAULT == null ? parent != null : !PARENT_EDEFAULT.equals(parent);
			case FhirPackage.DEFINED_CODE__V2_MAP:
				return V2_MAP_EDEFAULT == null ? v2Map != null : !V2_MAP_EDEFAULT.equals(v2Map);
			case FhirPackage.DEFINED_CODE__V3_MAP:
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
		result.append(" (code: ");
		result.append(code);
		result.append(", definition: ");
		result.append(definition);
		result.append(", comment: ");
		result.append(comment);
		result.append(", display: ");
		result.append(display);
		result.append(", system: ");
		result.append(system);
		result.append(", id: ");
		result.append(id);
		result.append(", parent: ");
		result.append(parent);
		result.append(", v2Map: ");
		result.append(v2Map);
		result.append(", v3Map: ");
		result.append(v3Map);
		result.append(')');
		return result.toString();
	}

} //DefinedCodeImpl
