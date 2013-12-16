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
import org.hl7.fhir.definitions.ecore.fhir.Annotations;
import org.hl7.fhir.definitions.ecore.fhir.FhirPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Annotations</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.AnnotationsImpl#getShortDefinition <em>Short Definition</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.AnnotationsImpl#getDefinition <em>Definition</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.AnnotationsImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.AnnotationsImpl#getRequirements <em>Requirements</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.AnnotationsImpl#getRimMapping <em>Rim Mapping</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.AnnotationsImpl#getV2Mapping <em>V2 Mapping</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.AnnotationsImpl#getTodo <em>Todo</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.AnnotationsImpl#getCommitteeNotes <em>Committee Notes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AnnotationsImpl extends EObjectImpl implements Annotations {
	/**
	 * The default value of the '{@link #getShortDefinition() <em>Short Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortDefinition()
	 * @generated
	 * @ordered
	 */
	protected static final String SHORT_DEFINITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getShortDefinition() <em>Short Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortDefinition()
	 * @generated
	 * @ordered
	 */
	protected String shortDefinition = SHORT_DEFINITION_EDEFAULT;

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
	 * The default value of the '{@link #getRequirements() <em>Requirements</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequirements()
	 * @generated
	 * @ordered
	 */
	protected static final String REQUIREMENTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRequirements() <em>Requirements</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequirements()
	 * @generated
	 * @ordered
	 */
	protected String requirements = REQUIREMENTS_EDEFAULT;

	/**
	 * The default value of the '{@link #getRimMapping() <em>Rim Mapping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRimMapping()
	 * @generated
	 * @ordered
	 */
	protected static final String RIM_MAPPING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRimMapping() <em>Rim Mapping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRimMapping()
	 * @generated
	 * @ordered
	 */
	protected String rimMapping = RIM_MAPPING_EDEFAULT;

	/**
	 * The default value of the '{@link #getV2Mapping() <em>V2 Mapping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getV2Mapping()
	 * @generated
	 * @ordered
	 */
	protected static final String V2_MAPPING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getV2Mapping() <em>V2 Mapping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getV2Mapping()
	 * @generated
	 * @ordered
	 */
	protected String v2Mapping = V2_MAPPING_EDEFAULT;

	/**
	 * The default value of the '{@link #getTodo() <em>Todo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTodo()
	 * @generated
	 * @ordered
	 */
	protected static final String TODO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTodo() <em>Todo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTodo()
	 * @generated
	 * @ordered
	 */
	protected String todo = TODO_EDEFAULT;

	/**
	 * The default value of the '{@link #getCommitteeNotes() <em>Committee Notes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommitteeNotes()
	 * @generated
	 * @ordered
	 */
	protected static final String COMMITTEE_NOTES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCommitteeNotes() <em>Committee Notes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommitteeNotes()
	 * @generated
	 * @ordered
	 */
	protected String committeeNotes = COMMITTEE_NOTES_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AnnotationsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FhirPackage.Literals.ANNOTATIONS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getShortDefinition() {
		return shortDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setShortDefinition(String newShortDefinition) {
		String oldShortDefinition = shortDefinition;
		shortDefinition = newShortDefinition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.ANNOTATIONS__SHORT_DEFINITION, oldShortDefinition, shortDefinition));
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
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.ANNOTATIONS__DEFINITION, oldDefinition, definition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getComment() {
		return comment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setComment(String newComment) {
		String oldComment = comment;
		comment = newComment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.ANNOTATIONS__COMMENT, oldComment, comment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getRequirements() {
		return requirements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRequirements(String newRequirements) {
		String oldRequirements = requirements;
		requirements = newRequirements;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.ANNOTATIONS__REQUIREMENTS, oldRequirements, requirements));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getRimMapping() {
		return rimMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRimMapping(String newRimMapping) {
		String oldRimMapping = rimMapping;
		rimMapping = newRimMapping;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.ANNOTATIONS__RIM_MAPPING, oldRimMapping, rimMapping));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getV2Mapping() {
		return v2Mapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setV2Mapping(String newV2Mapping) {
		String oldV2Mapping = v2Mapping;
		v2Mapping = newV2Mapping;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.ANNOTATIONS__V2_MAPPING, oldV2Mapping, v2Mapping));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTodo() {
		return todo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTodo(String newTodo) {
		String oldTodo = todo;
		todo = newTodo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.ANNOTATIONS__TODO, oldTodo, todo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCommitteeNotes() {
		return committeeNotes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCommitteeNotes(String newCommitteeNotes) {
		String oldCommitteeNotes = committeeNotes;
		committeeNotes = newCommitteeNotes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.ANNOTATIONS__COMMITTEE_NOTES, oldCommitteeNotes, committeeNotes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FhirPackage.ANNOTATIONS__SHORT_DEFINITION:
				return getShortDefinition();
			case FhirPackage.ANNOTATIONS__DEFINITION:
				return getDefinition();
			case FhirPackage.ANNOTATIONS__COMMENT:
				return getComment();
			case FhirPackage.ANNOTATIONS__REQUIREMENTS:
				return getRequirements();
			case FhirPackage.ANNOTATIONS__RIM_MAPPING:
				return getRimMapping();
			case FhirPackage.ANNOTATIONS__V2_MAPPING:
				return getV2Mapping();
			case FhirPackage.ANNOTATIONS__TODO:
				return getTodo();
			case FhirPackage.ANNOTATIONS__COMMITTEE_NOTES:
				return getCommitteeNotes();
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
			case FhirPackage.ANNOTATIONS__SHORT_DEFINITION:
				setShortDefinition((String)newValue);
				return;
			case FhirPackage.ANNOTATIONS__DEFINITION:
				setDefinition((String)newValue);
				return;
			case FhirPackage.ANNOTATIONS__COMMENT:
				setComment((String)newValue);
				return;
			case FhirPackage.ANNOTATIONS__REQUIREMENTS:
				setRequirements((String)newValue);
				return;
			case FhirPackage.ANNOTATIONS__RIM_MAPPING:
				setRimMapping((String)newValue);
				return;
			case FhirPackage.ANNOTATIONS__V2_MAPPING:
				setV2Mapping((String)newValue);
				return;
			case FhirPackage.ANNOTATIONS__TODO:
				setTodo((String)newValue);
				return;
			case FhirPackage.ANNOTATIONS__COMMITTEE_NOTES:
				setCommitteeNotes((String)newValue);
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
			case FhirPackage.ANNOTATIONS__SHORT_DEFINITION:
				setShortDefinition(SHORT_DEFINITION_EDEFAULT);
				return;
			case FhirPackage.ANNOTATIONS__DEFINITION:
				setDefinition(DEFINITION_EDEFAULT);
				return;
			case FhirPackage.ANNOTATIONS__COMMENT:
				setComment(COMMENT_EDEFAULT);
				return;
			case FhirPackage.ANNOTATIONS__REQUIREMENTS:
				setRequirements(REQUIREMENTS_EDEFAULT);
				return;
			case FhirPackage.ANNOTATIONS__RIM_MAPPING:
				setRimMapping(RIM_MAPPING_EDEFAULT);
				return;
			case FhirPackage.ANNOTATIONS__V2_MAPPING:
				setV2Mapping(V2_MAPPING_EDEFAULT);
				return;
			case FhirPackage.ANNOTATIONS__TODO:
				setTodo(TODO_EDEFAULT);
				return;
			case FhirPackage.ANNOTATIONS__COMMITTEE_NOTES:
				setCommitteeNotes(COMMITTEE_NOTES_EDEFAULT);
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
			case FhirPackage.ANNOTATIONS__SHORT_DEFINITION:
				return SHORT_DEFINITION_EDEFAULT == null ? shortDefinition != null : !SHORT_DEFINITION_EDEFAULT.equals(shortDefinition);
			case FhirPackage.ANNOTATIONS__DEFINITION:
				return DEFINITION_EDEFAULT == null ? definition != null : !DEFINITION_EDEFAULT.equals(definition);
			case FhirPackage.ANNOTATIONS__COMMENT:
				return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
			case FhirPackage.ANNOTATIONS__REQUIREMENTS:
				return REQUIREMENTS_EDEFAULT == null ? requirements != null : !REQUIREMENTS_EDEFAULT.equals(requirements);
			case FhirPackage.ANNOTATIONS__RIM_MAPPING:
				return RIM_MAPPING_EDEFAULT == null ? rimMapping != null : !RIM_MAPPING_EDEFAULT.equals(rimMapping);
			case FhirPackage.ANNOTATIONS__V2_MAPPING:
				return V2_MAPPING_EDEFAULT == null ? v2Mapping != null : !V2_MAPPING_EDEFAULT.equals(v2Mapping);
			case FhirPackage.ANNOTATIONS__TODO:
				return TODO_EDEFAULT == null ? todo != null : !TODO_EDEFAULT.equals(todo);
			case FhirPackage.ANNOTATIONS__COMMITTEE_NOTES:
				return COMMITTEE_NOTES_EDEFAULT == null ? committeeNotes != null : !COMMITTEE_NOTES_EDEFAULT.equals(committeeNotes);
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
		result.append(" (shortDefinition: ");
		result.append(shortDefinition);
		result.append(", definition: ");
		result.append(definition);
		result.append(", comment: ");
		result.append(comment);
		result.append(", requirements: ");
		result.append(requirements);
		result.append(", rimMapping: ");
		result.append(rimMapping);
		result.append(", v2Mapping: ");
		result.append(v2Mapping);
		result.append(", todo: ");
		result.append(todo);
		result.append(", committeeNotes: ");
		result.append(committeeNotes);
		result.append(')');
		return result.toString();
	}

} //AnnotationsImpl
