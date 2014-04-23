/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.hl7.fhir.definitions.ecore.fhir.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.hl7.fhir.definitions.ecore.fhir.*;
import org.hl7.fhir.definitions.ecore.fhir.Annotations;
import org.hl7.fhir.definitions.ecore.fhir.BindingDefn;
import org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.ConstrainedTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.DefinedCode;
import org.hl7.fhir.definitions.ecore.fhir.Definitions;
import org.hl7.fhir.definitions.ecore.fhir.ElementDefn;
import org.hl7.fhir.definitions.ecore.fhir.EventDefn;
import org.hl7.fhir.definitions.ecore.fhir.EventUsage;
import org.hl7.fhir.definitions.ecore.fhir.Example;
import org.hl7.fhir.definitions.ecore.fhir.FhirPackage;
import org.hl7.fhir.definitions.ecore.fhir.Invariant;
import org.hl7.fhir.definitions.ecore.fhir.InvariantRef;
import org.hl7.fhir.definitions.ecore.fhir.Mapping;
import org.hl7.fhir.definitions.ecore.fhir.MetaDataItem;
import org.hl7.fhir.definitions.ecore.fhir.NameScope;
import org.hl7.fhir.definitions.ecore.fhir.PrimitiveDefn;
import org.hl7.fhir.definitions.ecore.fhir.ProfileDefn;
import org.hl7.fhir.definitions.ecore.fhir.ProfiledElementDefn;
import org.hl7.fhir.definitions.ecore.fhir.ResourceDefn;
import org.hl7.fhir.definitions.ecore.fhir.SearchParameter;
import org.hl7.fhir.definitions.ecore.fhir.TypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.TypeRef;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage
 * @generated
 */
public class FhirAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static FhirPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FhirAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = FhirPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FhirSwitch<Adapter> modelSwitch =
		new FhirSwitch<Adapter>() {
			@Override
			public Adapter caseDefinitions(Definitions object) {
				return createDefinitionsAdapter();
			}
			@Override
			public Adapter caseElementDefn(ElementDefn object) {
				return createElementDefnAdapter();
			}
			@Override
			public Adapter caseInvariant(Invariant object) {
				return createInvariantAdapter();
			}
			@Override
			public Adapter caseTypeRef(TypeRef object) {
				return createTypeRefAdapter();
			}
			@Override
			public Adapter caseBindingDefn(BindingDefn object) {
				return createBindingDefnAdapter();
			}
			@Override
			public Adapter caseMapping(Mapping object) {
				return createMappingAdapter();
			}
			@Override
			public Adapter caseResourceDefn(ResourceDefn object) {
				return createResourceDefnAdapter();
			}
			@Override
			public Adapter caseExample(Example object) {
				return createExampleAdapter();
			}
			@Override
			public Adapter caseDefinedCode(DefinedCode object) {
				return createDefinedCodeAdapter();
			}
			@Override
			public Adapter casePrimitiveDefn(PrimitiveDefn object) {
				return createPrimitiveDefnAdapter();
			}
			@Override
			public Adapter caseConstrainedTypeDefn(ConstrainedTypeDefn object) {
				return createConstrainedTypeDefnAdapter();
			}
			@Override
			public Adapter caseEventDefn(EventDefn object) {
				return createEventDefnAdapter();
			}
			@Override
			public Adapter caseEventUsage(EventUsage object) {
				return createEventUsageAdapter();
			}
			@Override
			public Adapter caseProfileDefn(ProfileDefn object) {
				return createProfileDefnAdapter();
			}
			@Override
			public Adapter caseMetaDataItem(MetaDataItem object) {
				return createMetaDataItemAdapter();
			}
			@Override
			public Adapter caseSearchParameter(SearchParameter object) {
				return createSearchParameterAdapter();
			}
			@Override
			public Adapter caseTypeDefn(TypeDefn object) {
				return createTypeDefnAdapter();
			}
			@Override
			public Adapter caseCompositeTypeDefn(CompositeTypeDefn object) {
				return createCompositeTypeDefnAdapter();
			}
			@Override
			public Adapter caseNameScope(NameScope object) {
				return createNameScopeAdapter();
			}
			@Override
			public Adapter caseAnnotations(Annotations object) {
				return createAnnotationsAdapter();
			}
			@Override
			public Adapter caseProfiledElementDefn(ProfiledElementDefn object) {
				return createProfiledElementDefnAdapter();
			}
			@Override
			public Adapter caseInvariantRef(InvariantRef object) {
				return createInvariantRefAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.hl7.fhir.definitions.ecore.fhir.Definitions <em>Definitions</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.hl7.fhir.definitions.ecore.fhir.Definitions
	 * @generated
	 */
	public Adapter createDefinitionsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.hl7.fhir.definitions.ecore.fhir.ElementDefn <em>Element Defn</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.hl7.fhir.definitions.ecore.fhir.ElementDefn
	 * @generated
	 */
	public Adapter createElementDefnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.hl7.fhir.definitions.ecore.fhir.Invariant <em>Invariant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.hl7.fhir.definitions.ecore.fhir.Invariant
	 * @generated
	 */
	public Adapter createInvariantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.hl7.fhir.definitions.ecore.fhir.TypeRef <em>Type Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.hl7.fhir.definitions.ecore.fhir.TypeRef
	 * @generated
	 */
	public Adapter createTypeRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.hl7.fhir.definitions.ecore.fhir.BindingDefn <em>Binding Defn</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.hl7.fhir.definitions.ecore.fhir.BindingDefn
	 * @generated
	 */
	public Adapter createBindingDefnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.hl7.fhir.definitions.ecore.fhir.Mapping <em>Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.hl7.fhir.definitions.ecore.fhir.Mapping
	 * @generated
	 */
	public Adapter createMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.hl7.fhir.definitions.ecore.fhir.ResourceDefn <em>Resource Defn</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.hl7.fhir.definitions.ecore.fhir.ResourceDefn
	 * @generated
	 */
	public Adapter createResourceDefnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.hl7.fhir.definitions.ecore.fhir.Example <em>Example</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.hl7.fhir.definitions.ecore.fhir.Example
	 * @generated
	 */
	public Adapter createExampleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.hl7.fhir.definitions.ecore.fhir.DefinedCode <em>Defined Code</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.hl7.fhir.definitions.ecore.fhir.DefinedCode
	 * @generated
	 */
	public Adapter createDefinedCodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.hl7.fhir.definitions.ecore.fhir.PrimitiveDefn <em>Primitive Defn</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.hl7.fhir.definitions.ecore.fhir.PrimitiveDefn
	 * @generated
	 */
	public Adapter createPrimitiveDefnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.hl7.fhir.definitions.ecore.fhir.ConstrainedTypeDefn <em>Constrained Type Defn</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.hl7.fhir.definitions.ecore.fhir.ConstrainedTypeDefn
	 * @generated
	 */
	public Adapter createConstrainedTypeDefnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.hl7.fhir.definitions.ecore.fhir.TypeDefn <em>Type Defn</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.hl7.fhir.definitions.ecore.fhir.TypeDefn
	 * @generated
	 */
	public Adapter createTypeDefnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn <em>Composite Type Defn</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn
	 * @generated
	 */
	public Adapter createCompositeTypeDefnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.hl7.fhir.definitions.ecore.fhir.NameScope <em>Name Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.hl7.fhir.definitions.ecore.fhir.NameScope
	 * @generated
	 */
	public Adapter createNameScopeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.hl7.fhir.definitions.ecore.fhir.Annotations <em>Annotations</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.hl7.fhir.definitions.ecore.fhir.Annotations
	 * @generated
	 */
	public Adapter createAnnotationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.hl7.fhir.definitions.ecore.fhir.ProfiledElementDefn <em>Profiled Element Defn</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.hl7.fhir.definitions.ecore.fhir.ProfiledElementDefn
	 * @generated
	 */
	public Adapter createProfiledElementDefnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.hl7.fhir.definitions.ecore.fhir.InvariantRef <em>Invariant Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.hl7.fhir.definitions.ecore.fhir.InvariantRef
	 * @generated
	 */
	public Adapter createInvariantRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.hl7.fhir.definitions.ecore.fhir.EventDefn <em>Event Defn</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.hl7.fhir.definitions.ecore.fhir.EventDefn
	 * @generated
	 */
	public Adapter createEventDefnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.hl7.fhir.definitions.ecore.fhir.EventUsage <em>Event Usage</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.hl7.fhir.definitions.ecore.fhir.EventUsage
	 * @generated
	 */
	public Adapter createEventUsageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.hl7.fhir.definitions.ecore.fhir.ProfileDefn <em>Profile Defn</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.hl7.fhir.definitions.ecore.fhir.ProfileDefn
	 * @generated
	 */
	public Adapter createProfileDefnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.hl7.fhir.definitions.ecore.fhir.MetaDataItem <em>Meta Data Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.hl7.fhir.definitions.ecore.fhir.MetaDataItem
	 * @generated
	 */
	public Adapter createMetaDataItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.hl7.fhir.definitions.ecore.fhir.SearchParameter <em>Search Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.hl7.fhir.definitions.ecore.fhir.SearchParameter
	 * @generated
	 */
	public Adapter createSearchParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //FhirAdapterFactory
