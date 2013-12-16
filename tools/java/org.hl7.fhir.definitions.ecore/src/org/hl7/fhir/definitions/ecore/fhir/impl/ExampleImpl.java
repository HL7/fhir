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
import org.hl7.fhir.definitions.ecore.fhir.Example;
import org.hl7.fhir.definitions.ecore.fhir.ExampleType;
import org.hl7.fhir.definitions.ecore.fhir.FhirPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Example</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ExampleImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ExampleImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ExampleImpl#getPath <em>Path</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ExampleImpl#isInBook <em>In Book</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ExampleImpl#getIdentity <em>Identity</em>}</li>
 *   <li>{@link org.hl7.fhir.definitions.ecore.fhir.impl.ExampleImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExampleImpl extends EObjectImpl implements Example {
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
	 * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected String path = PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #isInBook() <em>In Book</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInBook()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IN_BOOK_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInBook() <em>In Book</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInBook()
	 * @generated
	 * @ordered
	 */
	protected boolean inBook = IN_BOOK_EDEFAULT;

	/**
	 * The default value of the '{@link #getIdentity() <em>Identity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdentity()
	 * @generated
	 * @ordered
	 */
	protected static final String IDENTITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIdentity() <em>Identity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdentity()
	 * @generated
	 * @ordered
	 */
	protected String identity = IDENTITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final ExampleType TYPE_EDEFAULT = ExampleType.XML;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected ExampleType type = TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExampleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FhirPackage.Literals.EXAMPLE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.EXAMPLE__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.EXAMPLE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPath() {
		return path;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPath(String newPath) {
		String oldPath = path;
		path = newPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.EXAMPLE__PATH, oldPath, path));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isInBook() {
		return inBook;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInBook(boolean newInBook) {
		boolean oldInBook = inBook;
		inBook = newInBook;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.EXAMPLE__IN_BOOK, oldInBook, inBook));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIdentity() {
		return identity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdentity(String newIdentity) {
		String oldIdentity = identity;
		identity = newIdentity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.EXAMPLE__IDENTITY, oldIdentity, identity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExampleType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(ExampleType newType) {
		ExampleType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FhirPackage.EXAMPLE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FhirPackage.EXAMPLE__NAME:
				return getName();
			case FhirPackage.EXAMPLE__DESCRIPTION:
				return getDescription();
			case FhirPackage.EXAMPLE__PATH:
				return getPath();
			case FhirPackage.EXAMPLE__IN_BOOK:
				return isInBook();
			case FhirPackage.EXAMPLE__IDENTITY:
				return getIdentity();
			case FhirPackage.EXAMPLE__TYPE:
				return getType();
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
			case FhirPackage.EXAMPLE__NAME:
				setName((String)newValue);
				return;
			case FhirPackage.EXAMPLE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case FhirPackage.EXAMPLE__PATH:
				setPath((String)newValue);
				return;
			case FhirPackage.EXAMPLE__IN_BOOK:
				setInBook((Boolean)newValue);
				return;
			case FhirPackage.EXAMPLE__IDENTITY:
				setIdentity((String)newValue);
				return;
			case FhirPackage.EXAMPLE__TYPE:
				setType((ExampleType)newValue);
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
			case FhirPackage.EXAMPLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case FhirPackage.EXAMPLE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case FhirPackage.EXAMPLE__PATH:
				setPath(PATH_EDEFAULT);
				return;
			case FhirPackage.EXAMPLE__IN_BOOK:
				setInBook(IN_BOOK_EDEFAULT);
				return;
			case FhirPackage.EXAMPLE__IDENTITY:
				setIdentity(IDENTITY_EDEFAULT);
				return;
			case FhirPackage.EXAMPLE__TYPE:
				setType(TYPE_EDEFAULT);
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
			case FhirPackage.EXAMPLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case FhirPackage.EXAMPLE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case FhirPackage.EXAMPLE__PATH:
				return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
			case FhirPackage.EXAMPLE__IN_BOOK:
				return inBook != IN_BOOK_EDEFAULT;
			case FhirPackage.EXAMPLE__IDENTITY:
				return IDENTITY_EDEFAULT == null ? identity != null : !IDENTITY_EDEFAULT.equals(identity);
			case FhirPackage.EXAMPLE__TYPE:
				return type != TYPE_EDEFAULT;
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
		result.append(", description: ");
		result.append(description);
		result.append(", path: ");
		result.append(path);
		result.append(", inBook: ");
		result.append(inBook);
		result.append(", identity: ");
		result.append(identity);
		result.append(", type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

	
	@Override
	public String getFileTitle() 
	{
		    String s = this.getName();
		    return s.substring(0, s.indexOf("."));
	}
} //ExampleImpl
