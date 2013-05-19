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
import org.hl7.fhir.definitions.ecore.fhir.EventUsage;
import org.hl7.fhir.definitions.ecore.fhir.FhirPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event Usage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.EventUsageImpl#getNotes <em>Notes</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.EventUsageImpl#getRequestResources <em>Request Resources</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.EventUsageImpl#getRequestAggregations <em>Request Aggregations</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.EventUsageImpl#getResponseResources <em>Response Resources</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.EventUsageImpl#getResponseAggregations <em>Response Aggregations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EventUsageImpl extends EObjectImpl implements EventUsage {
	/**
	 * The default value of the '{@link #getNotes() <em>Notes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotes()
	 * @generated
	 * @ordered
	 */
	protected static final String NOTES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNotes() <em>Notes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotes()
	 * @generated
	 * @ordered
	 */
	protected String notes = NOTES_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRequestResources() <em>Request Resources</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequestResources()
	 * @generated
	 * @ordered
	 */
	protected EList<String> requestResources;

	/**
	 * The cached value of the '{@link #getRequestAggregations() <em>Request Aggregations</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequestAggregations()
	 * @generated
	 * @ordered
	 */
	protected EList<String> requestAggregations;

	/**
	 * The cached value of the '{@link #getResponseResources() <em>Response Resources</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResponseResources()
	 * @generated
	 * @ordered
	 */
	protected EList<String> responseResources;

	/**
	 * The cached value of the '{@link #getResponseAggregations() <em>Response Aggregations</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResponseAggregations()
	 * @generated
	 * @ordered
	 */
	protected EList<String> responseAggregations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EventUsageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FhirPackage.Literals.EVENT_USAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getNotes() {
		return notes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNotes(String newNotes) {
		String oldNotes = notes;
		notes = newNotes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.EVENT_USAGE__NOTES, oldNotes, notes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getRequestResources() {
		if (requestResources == null) {
			requestResources = new EDataTypeUniqueEList<String>(String.class, this, FhirPackage.EVENT_USAGE__REQUEST_RESOURCES);
		}
		return requestResources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getRequestAggregations() {
		if (requestAggregations == null) {
			requestAggregations = new EDataTypeUniqueEList<String>(String.class, this, FhirPackage.EVENT_USAGE__REQUEST_AGGREGATIONS);
		}
		return requestAggregations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getResponseResources() {
		if (responseResources == null) {
			responseResources = new EDataTypeUniqueEList<String>(String.class, this, FhirPackage.EVENT_USAGE__RESPONSE_RESOURCES);
		}
		return responseResources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getResponseAggregations() {
		if (responseAggregations == null) {
			responseAggregations = new EDataTypeUniqueEList<String>(String.class, this, FhirPackage.EVENT_USAGE__RESPONSE_AGGREGATIONS);
		}
		return responseAggregations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FhirPackage.EVENT_USAGE__NOTES:
				return getNotes();
			case FhirPackage.EVENT_USAGE__REQUEST_RESOURCES:
				return getRequestResources();
			case FhirPackage.EVENT_USAGE__REQUEST_AGGREGATIONS:
				return getRequestAggregations();
			case FhirPackage.EVENT_USAGE__RESPONSE_RESOURCES:
				return getResponseResources();
			case FhirPackage.EVENT_USAGE__RESPONSE_AGGREGATIONS:
				return getResponseAggregations();
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
			case FhirPackage.EVENT_USAGE__NOTES:
				setNotes((String)newValue);
				return;
			case FhirPackage.EVENT_USAGE__REQUEST_RESOURCES:
				getRequestResources().clear();
				getRequestResources().addAll((Collection<? extends String>)newValue);
				return;
			case FhirPackage.EVENT_USAGE__REQUEST_AGGREGATIONS:
				getRequestAggregations().clear();
				getRequestAggregations().addAll((Collection<? extends String>)newValue);
				return;
			case FhirPackage.EVENT_USAGE__RESPONSE_RESOURCES:
				getResponseResources().clear();
				getResponseResources().addAll((Collection<? extends String>)newValue);
				return;
			case FhirPackage.EVENT_USAGE__RESPONSE_AGGREGATIONS:
				getResponseAggregations().clear();
				getResponseAggregations().addAll((Collection<? extends String>)newValue);
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
			case FhirPackage.EVENT_USAGE__NOTES:
				setNotes(NOTES_EDEFAULT);
				return;
			case FhirPackage.EVENT_USAGE__REQUEST_RESOURCES:
				getRequestResources().clear();
				return;
			case FhirPackage.EVENT_USAGE__REQUEST_AGGREGATIONS:
				getRequestAggregations().clear();
				return;
			case FhirPackage.EVENT_USAGE__RESPONSE_RESOURCES:
				getResponseResources().clear();
				return;
			case FhirPackage.EVENT_USAGE__RESPONSE_AGGREGATIONS:
				getResponseAggregations().clear();
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
			case FhirPackage.EVENT_USAGE__NOTES:
				return NOTES_EDEFAULT == null ? notes != null : !NOTES_EDEFAULT.equals(notes);
			case FhirPackage.EVENT_USAGE__REQUEST_RESOURCES:
				return requestResources != null && !requestResources.isEmpty();
			case FhirPackage.EVENT_USAGE__REQUEST_AGGREGATIONS:
				return requestAggregations != null && !requestAggregations.isEmpty();
			case FhirPackage.EVENT_USAGE__RESPONSE_RESOURCES:
				return responseResources != null && !responseResources.isEmpty();
			case FhirPackage.EVENT_USAGE__RESPONSE_AGGREGATIONS:
				return responseAggregations != null && !responseAggregations.isEmpty();
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
		result.append(" (notes: ");
		result.append(notes);
		result.append(", requestResources: ");
		result.append(requestResources);
		result.append(", requestAggregations: ");
		result.append(requestAggregations);
		result.append(", responseResources: ");
		result.append(responseResources);
		result.append(", responseAggregations: ");
		result.append(responseAggregations);
		result.append(')');
		return result.toString();
	}

} //EventUsageImpl
