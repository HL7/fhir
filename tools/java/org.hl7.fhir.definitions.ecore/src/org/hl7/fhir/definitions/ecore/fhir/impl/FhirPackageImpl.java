/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.hl7.fhir.definitions.ecore.fhir.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.hl7.fhir.definitions.ecore.fhir.Annotations;
import org.hl7.fhir.definitions.ecore.fhir.BindingDefn;
import org.hl7.fhir.definitions.ecore.fhir.BindingExtensibility;
import org.hl7.fhir.definitions.ecore.fhir.BindingStrength;
import org.hl7.fhir.definitions.ecore.fhir.BindingType;
import org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.ConstrainedTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.DefinedCode;
import org.hl7.fhir.definitions.ecore.fhir.Definitions;
import org.hl7.fhir.definitions.ecore.fhir.ElementDefn;
import org.hl7.fhir.definitions.ecore.fhir.EventDefn;
import org.hl7.fhir.definitions.ecore.fhir.EventUsage;
import org.hl7.fhir.definitions.ecore.fhir.Example;
import org.hl7.fhir.definitions.ecore.fhir.ExampleType;
import org.hl7.fhir.definitions.ecore.fhir.FhirFactory;
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
import org.hl7.fhir.definitions.ecore.fhir.SearchRepeatMode;
import org.hl7.fhir.definitions.ecore.fhir.SearchType;
import org.hl7.fhir.definitions.ecore.fhir.TypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.TypeRef;
import org.hl7.fhir.definitions.ecore.fhir.XmlFormatHint;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FhirPackageImpl extends EPackageImpl implements FhirPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass definitionsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementDefnEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass invariantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bindingDefnEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceDefnEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exampleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass definedCodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveDefnEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constrainedTypeDefnEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeDefnEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeTypeDefnEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nameScopeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass annotationsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass profiledElementDefnEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass invariantRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventDefnEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventUsageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass profileDefnEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass metaDataItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass searchParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum bindingTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum bindingStrengthEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum searchTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum bindingExtensibilityEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum searchRepeatModeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum exampleTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum xmlFormatHintEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.hl7.fhir.definitions.ecore.fhir.FhirPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private FhirPackageImpl() {
		super(eNS_URI, FhirFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link FhirPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static FhirPackage init() {
		if (isInited) return (FhirPackage)EPackage.Registry.INSTANCE.getEPackage(FhirPackage.eNS_URI);

		// Obtain or create and register package
		FhirPackageImpl theFhirPackage = (FhirPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof FhirPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new FhirPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theFhirPackage.createPackageContents();

		// Initialize created meta-data
		theFhirPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theFhirPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(FhirPackage.eNS_URI, theFhirPackage);
		return theFhirPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDefinitions() {
		return definitionsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDefinitions_Date() {
		return (EAttribute)definitionsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDefinitions_Version() {
		return (EAttribute)definitionsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDefinitions_Internal() {
		return (EAttribute)definitionsEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EReference getDefinitions_Primitive() {
		return (EReference)definitionsEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDefinitions_Profiles() {
		return (EReference)definitionsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EReference getDefinitions_Event() {
		return (EReference)definitionsEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getElementDefn() {
		return elementDefnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getElementDefn_MaxCardinality() {
		return (EAttribute)elementDefnEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EAttribute getElementDefn_IsModifier() {
		return (EAttribute)elementDefnEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getElementDefn_MinCardinality() {
		return (EAttribute)elementDefnEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getElementDefn_ParentType() {
		return (EReference)elementDefnEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getElementDefn_ParentElement() {
		return (EReference)elementDefnEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EAttribute getElementDefn_PrimitiveContents() {
		return (EAttribute)elementDefnEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EAttribute getElementDefn_XmlFormatHint() {
		return (EAttribute)elementDefnEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EAttribute getElementDefn_SummaryItem() {
		return (EAttribute)elementDefnEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EReference getElementDefn_Type() {
		return (EReference)elementDefnEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getElementDefn_Annotation() {
		return (EReference)elementDefnEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EReference getElementDefn_Invariant() {
		return (EReference)elementDefnEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getElementDefn_Name() {
		return (EAttribute)elementDefnEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getElementDefn_Mappings() {
		return (EReference)elementDefnEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getElementDefn_ExampleValue() {
		return (EAttribute)elementDefnEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getElementDefn_Content() {
		return (EReference)elementDefnEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInvariant() {
		return invariantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInvariant_Name() {
		return (EAttribute)invariantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInvariant_Description() {
		return (EAttribute)invariantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInvariant_Human() {
		return (EAttribute)invariantEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInvariant_Ocl() {
		return (EAttribute)invariantEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInvariant_Xpath() {
		return (EAttribute)invariantEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTypeRef() {
		return typeRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTypeRef_Name() {
		return (EAttribute)typeRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTypeRef_FullName() {
		return (EAttribute)typeRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTypeRef_ResourceParams() {
		return (EAttribute)typeRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTypeRef_BindingRef() {
		return (EAttribute)typeRefEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTypeRef_FullBindingRef() {
		return (EAttribute)typeRefEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBindingDefn() {
		return bindingDefnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBindingDefn_Id() {
		return (EAttribute)bindingDefnEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBindingDefn_Name() {
		return (EAttribute)bindingDefnEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EAttribute getBindingDefn_FullName() {
		return (EAttribute)bindingDefnEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBindingDefn_Binding() {
		return (EAttribute)bindingDefnEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EAttribute getBindingDefn_Reference() {
		return (EAttribute)bindingDefnEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBindingDefn_Source() {
		return (EAttribute)bindingDefnEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EReference getBindingDefn_Code() {
		return (EReference)bindingDefnEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBindingDefn_Parent() {
		return (EReference)bindingDefnEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EAttribute getBindingDefn_Description() {
		return (EAttribute)bindingDefnEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EAttribute getBindingDefn_Definition() {
		return (EAttribute)bindingDefnEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EAttribute getBindingDefn_Example() {
		return (EAttribute)bindingDefnEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public EAttribute getBindingDefn_V2Map() {
		return (EAttribute)bindingDefnEClass.getEStructuralFeatures().get(11);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public EAttribute getBindingDefn_V3Map() {
		return (EAttribute)bindingDefnEClass.getEStructuralFeatures().get(12);
	}

  /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMapping() {
		return mappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMapping_Source() {
		return (EAttribute)mappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMapping_Details() {
		return (EAttribute)mappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getResourceDefn() {
		return resourceDefnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResourceDefn_Example() {
		return (EReference)resourceDefnEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EReference getResourceDefn_Search() {
		return (EReference)resourceDefnEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getResourceDefn_Sandbox() {
		return (EAttribute)resourceDefnEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getResourceDefn_Future() {
		return (EAttribute)resourceDefnEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExample() {
		return exampleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExample_Name() {
		return (EAttribute)exampleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExample_Description() {
		return (EAttribute)exampleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExample_Path() {
		return (EAttribute)exampleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExample_InBook() {
		return (EAttribute)exampleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EAttribute getExample_Identity() {
		return (EAttribute)exampleEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EAttribute getExample_Type() {
		return (EAttribute)exampleEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDefinedCode() {
		return definedCodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDefinedCode_Code() {
		return (EAttribute)definedCodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDefinedCode_Definition() {
		return (EAttribute)definedCodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EAttribute getDefinedCode_Comment() {
		return (EAttribute)definedCodeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EAttribute getDefinedCode_Display() {
		return (EAttribute)definedCodeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EAttribute getDefinedCode_System() {
		return (EAttribute)definedCodeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EAttribute getDefinedCode_Id() {
		return (EAttribute)definedCodeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EAttribute getDefinedCode_Parent() {
		return (EAttribute)definedCodeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public EAttribute getDefinedCode_V2Map() {
		return (EAttribute)definedCodeEClass.getEStructuralFeatures().get(7);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public EAttribute getDefinedCode_V3Map() {
		return (EAttribute)definedCodeEClass.getEStructuralFeatures().get(8);
	}

  /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EClass getPrimitiveDefn() {
		return primitiveDefnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EAttribute getPrimitiveDefn_Pattern() {
		return (EAttribute)primitiveDefnEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EAttribute getPrimitiveDefn_Xsdtype() {
		return (EAttribute)primitiveDefnEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EAttribute getPrimitiveDefn_Name() {
		return (EAttribute)primitiveDefnEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EReference getPrimitiveDefn_Annotations() {
		return (EReference)primitiveDefnEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConstrainedTypeDefn() {
		return constrainedTypeDefnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EReference getConstrainedTypeDefn_ConstrainedBaseType() {
		return (EReference)constrainedTypeDefnEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EReference getConstrainedTypeDefn_Detail() {
		return (EReference)constrainedTypeDefnEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTypeDefn() {
		return typeDefnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTypeDefn_Name() {
		return (EAttribute)typeDefnEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTypeDefn_Annotations() {
		return (EReference)typeDefnEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTypeDefn_Scope() {
		return (EReference)typeDefnEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTypeDefn_FullName() {
		return (EAttribute)typeDefnEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTypeDefn_Infrastructure() {
		return (EAttribute)typeDefnEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCompositeTypeDefn() {
		return compositeTypeDefnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EReference getCompositeTypeDefn_Element() {
		return (EReference)compositeTypeDefnEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EReference getCompositeTypeDefn_Invariant() {
		return (EReference)compositeTypeDefnEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCompositeTypeDefn_UnnamedElementGroup() {
		return (EAttribute)compositeTypeDefnEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EAttribute getCompositeTypeDefn_Abstract() {
		return (EAttribute)compositeTypeDefnEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EReference getCompositeTypeDefn_BaseType() {
		return (EReference)compositeTypeDefnEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNameScope() {
		return nameScopeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EReference getNameScope_Type() {
		return (EReference)nameScopeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EReference getNameScope_Binding() {
		return (EReference)nameScopeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAnnotations() {
		return annotationsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAnnotations_ShortDefinition() {
		return (EAttribute)annotationsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAnnotations_Definition() {
		return (EAttribute)annotationsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAnnotations_Comment() {
		return (EAttribute)annotationsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAnnotations_Requirements() {
		return (EAttribute)annotationsEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAnnotations_RimMapping() {
		return (EAttribute)annotationsEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAnnotations_V2Mapping() {
		return (EAttribute)annotationsEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAnnotations_Todo() {
		return (EAttribute)annotationsEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAnnotations_CommitteeNotes() {
		return (EAttribute)annotationsEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProfiledElementDefn() {
		return profiledElementDefnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProfiledElementDefn_Inherited() {
		return (EAttribute)profiledElementDefnEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProfiledElementDefn_Aggregation() {
		return (EAttribute)profiledElementDefnEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProfiledElementDefn_FixedValue() {
		return (EAttribute)profiledElementDefnEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProfiledElementDefn_TargetUri() {
		return (EAttribute)profiledElementDefnEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProfiledElementDefn_ProfileName() {
		return (EAttribute)profiledElementDefnEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInvariantRef() {
		return invariantRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInvariantRef_Name() {
		return (EAttribute)invariantRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEventDefn() {
		return eventDefnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEventDefn_Code() {
		return (EAttribute)eventDefnEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEventDefn_Definition() {
		return (EAttribute)eventDefnEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEventDefn_FollowUps() {
		return (EAttribute)eventDefnEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EReference getEventDefn_Usage() {
		return (EReference)eventDefnEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEventUsage() {
		return eventUsageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEventUsage_Notes() {
		return (EAttribute)eventUsageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEventUsage_RequestResources() {
		return (EAttribute)eventUsageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEventUsage_RequestAggregations() {
		return (EAttribute)eventUsageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEventUsage_ResponseResources() {
		return (EAttribute)eventUsageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEventUsage_ResponseAggregations() {
		return (EAttribute)eventUsageEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProfileDefn() {
		return profileDefnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProfileDefn_Resources() {
		return (EReference)profileDefnEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProfileDefn_Metadata() {
		return (EReference)profileDefnEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMetaDataItem() {
		return metaDataItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMetaDataItem_Name() {
		return (EAttribute)metaDataItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMetaDataItem_Value() {
		return (EAttribute)metaDataItemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSearchParameter() {
		return searchParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSearchParameter_Name() {
		return (EAttribute)searchParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSearchParameter_Description() {
		return (EAttribute)searchParameterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSearchParameter_Type() {
		return (EAttribute)searchParameterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EAttribute getSearchParameter_Path() {
		return (EAttribute)searchParameterEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EAttribute getSearchParameter_Composite() {
		return (EAttribute)searchParameterEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSearchParameter_Target() {
		return (EAttribute)searchParameterEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getBindingType() {
		return bindingTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getBindingStrength() {
		return bindingStrengthEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getSearchType() {
		return searchTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EEnum getBindingExtensibility() {
		return bindingExtensibilityEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EEnum getSearchRepeatMode() {
		return searchRepeatModeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EEnum getExampleType() {
		return exampleTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
  public EEnum getXmlFormatHint() {
		return xmlFormatHintEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FhirFactory getFhirFactory() {
		return (FhirFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		definitionsEClass = createEClass(DEFINITIONS);
		createEAttribute(definitionsEClass, DEFINITIONS__DATE);
		createEAttribute(definitionsEClass, DEFINITIONS__VERSION);
		createEReference(definitionsEClass, DEFINITIONS__PROFILES);
		createEReference(definitionsEClass, DEFINITIONS__EVENT);
		createEAttribute(definitionsEClass, DEFINITIONS__INTERNAL);
		createEReference(definitionsEClass, DEFINITIONS__PRIMITIVE);

		elementDefnEClass = createEClass(ELEMENT_DEFN);
		createEAttribute(elementDefnEClass, ELEMENT_DEFN__NAME);
		createEAttribute(elementDefnEClass, ELEMENT_DEFN__MIN_CARDINALITY);
		createEAttribute(elementDefnEClass, ELEMENT_DEFN__MAX_CARDINALITY);
		createEAttribute(elementDefnEClass, ELEMENT_DEFN__IS_MODIFIER);
		createEReference(elementDefnEClass, ELEMENT_DEFN__TYPE);
		createEReference(elementDefnEClass, ELEMENT_DEFN__MAPPINGS);
		createEAttribute(elementDefnEClass, ELEMENT_DEFN__EXAMPLE_VALUE);
		createEReference(elementDefnEClass, ELEMENT_DEFN__CONTENT);
		createEReference(elementDefnEClass, ELEMENT_DEFN__ANNOTATION);
		createEReference(elementDefnEClass, ELEMENT_DEFN__INVARIANT);
		createEReference(elementDefnEClass, ELEMENT_DEFN__PARENT_TYPE);
		createEReference(elementDefnEClass, ELEMENT_DEFN__PARENT_ELEMENT);
		createEAttribute(elementDefnEClass, ELEMENT_DEFN__PRIMITIVE_CONTENTS);
		createEAttribute(elementDefnEClass, ELEMENT_DEFN__XML_FORMAT_HINT);
		createEAttribute(elementDefnEClass, ELEMENT_DEFN__SUMMARY_ITEM);

		invariantEClass = createEClass(INVARIANT);
		createEAttribute(invariantEClass, INVARIANT__NAME);
		createEAttribute(invariantEClass, INVARIANT__DESCRIPTION);
		createEAttribute(invariantEClass, INVARIANT__HUMAN);
		createEAttribute(invariantEClass, INVARIANT__OCL);
		createEAttribute(invariantEClass, INVARIANT__XPATH);

		typeRefEClass = createEClass(TYPE_REF);
		createEAttribute(typeRefEClass, TYPE_REF__NAME);
		createEAttribute(typeRefEClass, TYPE_REF__FULL_NAME);
		createEAttribute(typeRefEClass, TYPE_REF__RESOURCE_PARAMS);
		createEAttribute(typeRefEClass, TYPE_REF__BINDING_REF);
		createEAttribute(typeRefEClass, TYPE_REF__FULL_BINDING_REF);

		bindingDefnEClass = createEClass(BINDING_DEFN);
		createEAttribute(bindingDefnEClass, BINDING_DEFN__ID);
		createEAttribute(bindingDefnEClass, BINDING_DEFN__NAME);
		createEAttribute(bindingDefnEClass, BINDING_DEFN__FULL_NAME);
		createEAttribute(bindingDefnEClass, BINDING_DEFN__BINDING);
		createEAttribute(bindingDefnEClass, BINDING_DEFN__REFERENCE);
		createEAttribute(bindingDefnEClass, BINDING_DEFN__SOURCE);
		createEReference(bindingDefnEClass, BINDING_DEFN__CODE);
		createEReference(bindingDefnEClass, BINDING_DEFN__PARENT);
		createEAttribute(bindingDefnEClass, BINDING_DEFN__DESCRIPTION);
		createEAttribute(bindingDefnEClass, BINDING_DEFN__DEFINITION);
		createEAttribute(bindingDefnEClass, BINDING_DEFN__EXAMPLE);
		createEAttribute(bindingDefnEClass, BINDING_DEFN__V2_MAP);
		createEAttribute(bindingDefnEClass, BINDING_DEFN__V3_MAP);

		mappingEClass = createEClass(MAPPING);
		createEAttribute(mappingEClass, MAPPING__SOURCE);
		createEAttribute(mappingEClass, MAPPING__DETAILS);

		resourceDefnEClass = createEClass(RESOURCE_DEFN);
		createEAttribute(resourceDefnEClass, RESOURCE_DEFN__SANDBOX);
		createEReference(resourceDefnEClass, RESOURCE_DEFN__EXAMPLE);
		createEReference(resourceDefnEClass, RESOURCE_DEFN__SEARCH);
		createEAttribute(resourceDefnEClass, RESOURCE_DEFN__FUTURE);

		exampleEClass = createEClass(EXAMPLE);
		createEAttribute(exampleEClass, EXAMPLE__NAME);
		createEAttribute(exampleEClass, EXAMPLE__DESCRIPTION);
		createEAttribute(exampleEClass, EXAMPLE__PATH);
		createEAttribute(exampleEClass, EXAMPLE__IN_BOOK);
		createEAttribute(exampleEClass, EXAMPLE__IDENTITY);
		createEAttribute(exampleEClass, EXAMPLE__TYPE);

		definedCodeEClass = createEClass(DEFINED_CODE);
		createEAttribute(definedCodeEClass, DEFINED_CODE__CODE);
		createEAttribute(definedCodeEClass, DEFINED_CODE__DEFINITION);
		createEAttribute(definedCodeEClass, DEFINED_CODE__COMMENT);
		createEAttribute(definedCodeEClass, DEFINED_CODE__DISPLAY);
		createEAttribute(definedCodeEClass, DEFINED_CODE__SYSTEM);
		createEAttribute(definedCodeEClass, DEFINED_CODE__ID);
		createEAttribute(definedCodeEClass, DEFINED_CODE__PARENT);
		createEAttribute(definedCodeEClass, DEFINED_CODE__V2_MAP);
		createEAttribute(definedCodeEClass, DEFINED_CODE__V3_MAP);

		primitiveDefnEClass = createEClass(PRIMITIVE_DEFN);
		createEAttribute(primitiveDefnEClass, PRIMITIVE_DEFN__PATTERN);
		createEAttribute(primitiveDefnEClass, PRIMITIVE_DEFN__XSDTYPE);
		createEAttribute(primitiveDefnEClass, PRIMITIVE_DEFN__NAME);
		createEReference(primitiveDefnEClass, PRIMITIVE_DEFN__ANNOTATIONS);

		constrainedTypeDefnEClass = createEClass(CONSTRAINED_TYPE_DEFN);
		createEReference(constrainedTypeDefnEClass, CONSTRAINED_TYPE_DEFN__CONSTRAINED_BASE_TYPE);
		createEReference(constrainedTypeDefnEClass, CONSTRAINED_TYPE_DEFN__DETAIL);

		eventDefnEClass = createEClass(EVENT_DEFN);
		createEAttribute(eventDefnEClass, EVENT_DEFN__CODE);
		createEAttribute(eventDefnEClass, EVENT_DEFN__DEFINITION);
		createEAttribute(eventDefnEClass, EVENT_DEFN__FOLLOW_UPS);
		createEReference(eventDefnEClass, EVENT_DEFN__USAGE);

		eventUsageEClass = createEClass(EVENT_USAGE);
		createEAttribute(eventUsageEClass, EVENT_USAGE__NOTES);
		createEAttribute(eventUsageEClass, EVENT_USAGE__REQUEST_RESOURCES);
		createEAttribute(eventUsageEClass, EVENT_USAGE__REQUEST_AGGREGATIONS);
		createEAttribute(eventUsageEClass, EVENT_USAGE__RESPONSE_RESOURCES);
		createEAttribute(eventUsageEClass, EVENT_USAGE__RESPONSE_AGGREGATIONS);

		profileDefnEClass = createEClass(PROFILE_DEFN);
		createEReference(profileDefnEClass, PROFILE_DEFN__RESOURCES);
		createEReference(profileDefnEClass, PROFILE_DEFN__METADATA);

		metaDataItemEClass = createEClass(META_DATA_ITEM);
		createEAttribute(metaDataItemEClass, META_DATA_ITEM__NAME);
		createEAttribute(metaDataItemEClass, META_DATA_ITEM__VALUE);

		searchParameterEClass = createEClass(SEARCH_PARAMETER);
		createEAttribute(searchParameterEClass, SEARCH_PARAMETER__NAME);
		createEAttribute(searchParameterEClass, SEARCH_PARAMETER__DESCRIPTION);
		createEAttribute(searchParameterEClass, SEARCH_PARAMETER__TYPE);
		createEAttribute(searchParameterEClass, SEARCH_PARAMETER__PATH);
		createEAttribute(searchParameterEClass, SEARCH_PARAMETER__COMPOSITE);
		createEAttribute(searchParameterEClass, SEARCH_PARAMETER__TARGET);

		typeDefnEClass = createEClass(TYPE_DEFN);
		createEAttribute(typeDefnEClass, TYPE_DEFN__NAME);
		createEReference(typeDefnEClass, TYPE_DEFN__ANNOTATIONS);
		createEReference(typeDefnEClass, TYPE_DEFN__SCOPE);
		createEAttribute(typeDefnEClass, TYPE_DEFN__FULL_NAME);
		createEAttribute(typeDefnEClass, TYPE_DEFN__INFRASTRUCTURE);

		compositeTypeDefnEClass = createEClass(COMPOSITE_TYPE_DEFN);
		createEReference(compositeTypeDefnEClass, COMPOSITE_TYPE_DEFN__ELEMENT);
		createEReference(compositeTypeDefnEClass, COMPOSITE_TYPE_DEFN__INVARIANT);
		createEAttribute(compositeTypeDefnEClass, COMPOSITE_TYPE_DEFN__UNNAMED_ELEMENT_GROUP);
		createEAttribute(compositeTypeDefnEClass, COMPOSITE_TYPE_DEFN__ABSTRACT);
		createEReference(compositeTypeDefnEClass, COMPOSITE_TYPE_DEFN__BASE_TYPE);

		nameScopeEClass = createEClass(NAME_SCOPE);
		createEReference(nameScopeEClass, NAME_SCOPE__TYPE);
		createEReference(nameScopeEClass, NAME_SCOPE__BINDING);

		annotationsEClass = createEClass(ANNOTATIONS);
		createEAttribute(annotationsEClass, ANNOTATIONS__SHORT_DEFINITION);
		createEAttribute(annotationsEClass, ANNOTATIONS__DEFINITION);
		createEAttribute(annotationsEClass, ANNOTATIONS__COMMENT);
		createEAttribute(annotationsEClass, ANNOTATIONS__REQUIREMENTS);
		createEAttribute(annotationsEClass, ANNOTATIONS__RIM_MAPPING);
		createEAttribute(annotationsEClass, ANNOTATIONS__V2_MAPPING);
		createEAttribute(annotationsEClass, ANNOTATIONS__TODO);
		createEAttribute(annotationsEClass, ANNOTATIONS__COMMITTEE_NOTES);

		profiledElementDefnEClass = createEClass(PROFILED_ELEMENT_DEFN);
		createEAttribute(profiledElementDefnEClass, PROFILED_ELEMENT_DEFN__INHERITED);
		createEAttribute(profiledElementDefnEClass, PROFILED_ELEMENT_DEFN__AGGREGATION);
		createEAttribute(profiledElementDefnEClass, PROFILED_ELEMENT_DEFN__FIXED_VALUE);
		createEAttribute(profiledElementDefnEClass, PROFILED_ELEMENT_DEFN__TARGET_URI);
		createEAttribute(profiledElementDefnEClass, PROFILED_ELEMENT_DEFN__PROFILE_NAME);

		invariantRefEClass = createEClass(INVARIANT_REF);
		createEAttribute(invariantRefEClass, INVARIANT_REF__NAME);

		// Create enums
		bindingTypeEEnum = createEEnum(BINDING_TYPE);
		bindingStrengthEEnum = createEEnum(BINDING_STRENGTH);
		searchTypeEEnum = createEEnum(SEARCH_TYPE);
		bindingExtensibilityEEnum = createEEnum(BINDING_EXTENSIBILITY);
		searchRepeatModeEEnum = createEEnum(SEARCH_REPEAT_MODE);
		exampleTypeEEnum = createEEnum(EXAMPLE_TYPE);
		xmlFormatHintEEnum = createEEnum(XML_FORMAT_HINT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		definitionsEClass.getESuperTypes().add(this.getNameScope());
		resourceDefnEClass.getESuperTypes().add(this.getCompositeTypeDefn());
		constrainedTypeDefnEClass.getESuperTypes().add(this.getTypeDefn());
		compositeTypeDefnEClass.getESuperTypes().add(this.getTypeDefn());
		compositeTypeDefnEClass.getESuperTypes().add(this.getNameScope());
		profiledElementDefnEClass.getESuperTypes().add(this.getElementDefn());

		// Initialize classes and features; add operations and parameters
		initEClass(definitionsEClass, Definitions.class, "Definitions", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDefinitions_Date(), ecorePackage.getEDate(), "date", null, 1, 1, Definitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDefinitions_Version(), ecorePackage.getEString(), "version", null, 1, 1, Definitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDefinitions_Profiles(), this.getProfileDefn(), null, "profiles", null, 0, -1, Definitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDefinitions_Event(), this.getEventDefn(), null, "event", null, 0, -1, Definitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDefinitions_Internal(), ecorePackage.getEBoolean(), "internal", null, 0, 1, Definitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDefinitions_Primitive(), this.getPrimitiveDefn(), null, "primitive", null, 0, -1, Definitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(definitionsEClass, this.getTypeDefn(), "findType", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "fullName", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(definitionsEClass, this.getBindingDefn(), "findBinding", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "fullName", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(definitionsEClass, this.getPrimitiveDefn(), "findPrimitive", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(definitionsEClass, this.getResourceDefn(), "getResources", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(elementDefnEClass, ElementDefn.class, "ElementDefn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getElementDefn_Name(), ecorePackage.getEString(), "name", null, 1, 1, ElementDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getElementDefn_MinCardinality(), ecorePackage.getEInt(), "minCardinality", null, 1, 1, ElementDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getElementDefn_MaxCardinality(), ecorePackage.getEInt(), "maxCardinality", null, 1, 1, ElementDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getElementDefn_IsModifier(), ecorePackage.getEBoolean(), "isModifier", null, 0, 1, ElementDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElementDefn_Type(), this.getTypeRef(), null, "type", null, 0, -1, ElementDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElementDefn_Mappings(), this.getMapping(), null, "mappings", null, 0, -1, ElementDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getElementDefn_ExampleValue(), ecorePackage.getEString(), "exampleValue", null, 0, 1, ElementDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElementDefn_Content(), this.getElementDefn(), null, "content", null, 0, 1, ElementDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElementDefn_Annotation(), this.getAnnotations(), null, "annotation", null, 1, 1, ElementDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElementDefn_Invariant(), this.getInvariantRef(), null, "invariant", null, 0, -1, ElementDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElementDefn_ParentType(), this.getCompositeTypeDefn(), this.getCompositeTypeDefn_Element(), "parentType", null, 0, 1, ElementDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElementDefn_ParentElement(), this.getElementDefn(), null, "parentElement", null, 0, 1, ElementDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getElementDefn_PrimitiveContents(), ecorePackage.getEBoolean(), "primitiveContents", null, 0, 1, ElementDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getElementDefn_XmlFormatHint(), this.getXmlFormatHint(), "xmlFormatHint", null, 0, 1, ElementDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getElementDefn_SummaryItem(), ecorePackage.getEBoolean(), "summaryItem", null, 0, 1, ElementDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(elementDefnEClass, ecorePackage.getEBoolean(), "isRepeating", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(elementDefnEClass, ecorePackage.getEBoolean(), "isPolymorph", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(elementDefnEClass, ecorePackage.getEBoolean(), "containsReference", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(elementDefnEClass, ecorePackage.getEBoolean(), "isPrimitiveValueElement", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(invariantEClass, Invariant.class, "Invariant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInvariant_Name(), ecorePackage.getEString(), "name", null, 1, 1, Invariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInvariant_Description(), ecorePackage.getEString(), "description", null, 0, 1, Invariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInvariant_Human(), ecorePackage.getEString(), "human", null, 1, 1, Invariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInvariant_Ocl(), ecorePackage.getEString(), "ocl", null, 0, 1, Invariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInvariant_Xpath(), ecorePackage.getEString(), "xpath", null, 1, 1, Invariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(typeRefEClass, TypeRef.class, "TypeRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTypeRef_Name(), ecorePackage.getEString(), "name", null, 0, 1, TypeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTypeRef_FullName(), ecorePackage.getEString(), "fullName", null, 0, 1, TypeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTypeRef_ResourceParams(), ecorePackage.getEString(), "resourceParams", null, 0, -1, TypeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTypeRef_BindingRef(), ecorePackage.getEString(), "bindingRef", null, 0, 1, TypeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTypeRef_FullBindingRef(), ecorePackage.getEString(), "fullBindingRef", null, 0, 1, TypeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(typeRefEClass, ecorePackage.getEBoolean(), "isBindable", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(bindingDefnEClass, BindingDefn.class, "BindingDefn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBindingDefn_Id(), ecorePackage.getEInt(), "id", null, 1, 1, BindingDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBindingDefn_Name(), ecorePackage.getEString(), "name", null, 1, 1, BindingDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBindingDefn_FullName(), ecorePackage.getEString(), "fullName", null, 0, 1, BindingDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBindingDefn_Binding(), this.getBindingType(), "binding", null, 1, 1, BindingDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBindingDefn_Reference(), ecorePackage.getEString(), "reference", null, 1, 1, BindingDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBindingDefn_Source(), ecorePackage.getEString(), "source", null, 1, 1, BindingDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBindingDefn_Code(), this.getDefinedCode(), null, "code", null, 0, -1, BindingDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBindingDefn_Parent(), this.getNameScope(), this.getNameScope_Binding(), "parent", null, 1, 1, BindingDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBindingDefn_Description(), ecorePackage.getEString(), "description", null, 0, 1, BindingDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBindingDefn_Definition(), ecorePackage.getEString(), "definition", null, 0, 1, BindingDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBindingDefn_Example(), ecorePackage.getEBoolean(), "example", null, 0, 1, BindingDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBindingDefn_V2Map(), ecorePackage.getEString(), "v2Map", null, 0, 1, BindingDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBindingDefn_V3Map(), ecorePackage.getEString(), "v3Map", null, 0, 1, BindingDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(bindingDefnEClass, ecorePackage.getEBoolean(), "isGloballyDefined", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(mappingEClass, Mapping.class, "Mapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMapping_Source(), ecorePackage.getEString(), "source", null, 0, 1, Mapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMapping_Details(), ecorePackage.getEString(), "details", null, 0, 1, Mapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceDefnEClass, ResourceDefn.class, "ResourceDefn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getResourceDefn_Sandbox(), ecorePackage.getEBoolean(), "sandbox", null, 1, 1, ResourceDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceDefn_Example(), this.getExample(), null, "example", null, 1, -1, ResourceDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceDefn_Search(), this.getSearchParameter(), null, "search", null, 0, -1, ResourceDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceDefn_Future(), ecorePackage.getEBoolean(), "future", null, 0, 1, ResourceDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(exampleEClass, Example.class, "Example", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExample_Name(), ecorePackage.getEString(), "name", null, 1, 1, Example.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExample_Description(), ecorePackage.getEString(), "description", null, 0, 1, Example.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExample_Path(), ecorePackage.getEString(), "path", null, 1, 1, Example.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExample_InBook(), ecorePackage.getEBoolean(), "inBook", null, 0, 1, Example.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExample_Identity(), ecorePackage.getEString(), "identity", null, 0, 1, Example.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExample_Type(), this.getExampleType(), "type", null, 1, 1, Example.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(definedCodeEClass, DefinedCode.class, "DefinedCode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDefinedCode_Code(), ecorePackage.getEString(), "code", null, 1, 1, DefinedCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDefinedCode_Definition(), ecorePackage.getEString(), "definition", null, 1, 1, DefinedCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDefinedCode_Comment(), ecorePackage.getEString(), "comment", null, 0, 1, DefinedCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDefinedCode_Display(), ecorePackage.getEString(), "display", null, 0, 1, DefinedCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDefinedCode_System(), ecorePackage.getEString(), "system", null, 0, 1, DefinedCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDefinedCode_Id(), ecorePackage.getEString(), "id", null, 1, 1, DefinedCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDefinedCode_Parent(), ecorePackage.getEString(), "parent", null, 0, 1, DefinedCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDefinedCode_V2Map(), ecorePackage.getEString(), "v2Map", null, 0, 1, DefinedCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDefinedCode_V3Map(), ecorePackage.getEString(), "v3Map", null, 0, 1, DefinedCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(primitiveDefnEClass, PrimitiveDefn.class, "PrimitiveDefn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPrimitiveDefn_Pattern(), ecorePackage.getEString(), "pattern", null, 0, 1, PrimitiveDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrimitiveDefn_Xsdtype(), ecorePackage.getEString(), "xsdtype", null, 1, 1, PrimitiveDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrimitiveDefn_Name(), ecorePackage.getEString(), "name", null, 0, 1, PrimitiveDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPrimitiveDefn_Annotations(), this.getAnnotations(), null, "annotations", null, 1, 1, PrimitiveDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(constrainedTypeDefnEClass, ConstrainedTypeDefn.class, "ConstrainedTypeDefn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConstrainedTypeDefn_ConstrainedBaseType(), this.getTypeRef(), null, "constrainedBaseType", null, 1, 1, ConstrainedTypeDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConstrainedTypeDefn_Detail(), this.getInvariant(), null, "detail", null, 1, -1, ConstrainedTypeDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventDefnEClass, EventDefn.class, "EventDefn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEventDefn_Code(), ecorePackage.getEString(), "code", null, 1, 1, EventDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventDefn_Definition(), ecorePackage.getEString(), "definition", null, 1, 1, EventDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventDefn_FollowUps(), ecorePackage.getEString(), "followUps", null, 0, -1, EventDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEventDefn_Usage(), this.getEventUsage(), null, "usage", null, 1, -1, EventDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventUsageEClass, EventUsage.class, "EventUsage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEventUsage_Notes(), ecorePackage.getEString(), "notes", null, 0, 1, EventUsage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventUsage_RequestResources(), ecorePackage.getEString(), "requestResources", null, 0, -1, EventUsage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventUsage_RequestAggregations(), ecorePackage.getEString(), "requestAggregations", null, 0, -1, EventUsage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventUsage_ResponseResources(), ecorePackage.getEString(), "responseResources", null, 0, -1, EventUsage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventUsage_ResponseAggregations(), ecorePackage.getEString(), "responseAggregations", null, 0, -1, EventUsage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(profileDefnEClass, ProfileDefn.class, "ProfileDefn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProfileDefn_Resources(), this.getResourceDefn(), null, "resources", null, 1, -1, ProfileDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProfileDefn_Metadata(), this.getMetaDataItem(), null, "metadata", null, 0, -1, ProfileDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(metaDataItemEClass, MetaDataItem.class, "MetaDataItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMetaDataItem_Name(), ecorePackage.getEString(), "name", null, 1, 1, MetaDataItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaDataItem_Value(), ecorePackage.getEString(), "value", null, 0, -1, MetaDataItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(searchParameterEClass, SearchParameter.class, "SearchParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSearchParameter_Name(), ecorePackage.getEString(), "name", null, 0, 1, SearchParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSearchParameter_Description(), ecorePackage.getEString(), "description", null, 0, 1, SearchParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSearchParameter_Type(), this.getSearchType(), "type", null, 1, 1, SearchParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSearchParameter_Path(), ecorePackage.getEString(), "path", null, 0, -1, SearchParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSearchParameter_Composite(), ecorePackage.getEString(), "composite", null, 0, -1, SearchParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSearchParameter_Target(), ecorePackage.getEString(), "target", null, 0, -1, SearchParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(typeDefnEClass, TypeDefn.class, "TypeDefn", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTypeDefn_Name(), ecorePackage.getEString(), "name", null, 1, 1, TypeDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTypeDefn_Annotations(), this.getAnnotations(), null, "annotations", null, 1, 1, TypeDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTypeDefn_Scope(), this.getNameScope(), this.getNameScope_Type(), "scope", null, 0, 1, TypeDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTypeDefn_FullName(), ecorePackage.getEString(), "fullName", null, 0, 1, TypeDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTypeDefn_Infrastructure(), ecorePackage.getEBoolean(), "infrastructure", null, 0, 1, TypeDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(typeDefnEClass, ecorePackage.getEBoolean(), "isComposite", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(typeDefnEClass, ecorePackage.getEBoolean(), "isConstrained", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(typeDefnEClass, ecorePackage.getEBoolean(), "isReference", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(typeDefnEClass, ecorePackage.getEBoolean(), "isGloballyDefined", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(compositeTypeDefnEClass, CompositeTypeDefn.class, "CompositeTypeDefn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompositeTypeDefn_Element(), this.getElementDefn(), this.getElementDefn_ParentType(), "element", null, 0, -1, CompositeTypeDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeTypeDefn_Invariant(), this.getInvariant(), null, "invariant", null, 0, -1, CompositeTypeDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompositeTypeDefn_UnnamedElementGroup(), ecorePackage.getEBoolean(), "unnamedElementGroup", null, 0, 1, CompositeTypeDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompositeTypeDefn_Abstract(), ecorePackage.getEBoolean(), "abstract", null, 0, 1, CompositeTypeDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeTypeDefn_BaseType(), this.getTypeRef(), null, "baseType", null, 0, 1, CompositeTypeDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(compositeTypeDefnEClass, this.getElementDefn(), "getAllElements", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(nameScopeEClass, NameScope.class, "NameScope", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNameScope_Type(), this.getTypeDefn(), this.getTypeDefn_Scope(), "type", null, 0, -1, NameScope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNameScope_Binding(), this.getBindingDefn(), this.getBindingDefn_Parent(), "binding", null, 0, -1, NameScope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(nameScopeEClass, this.getCompositeTypeDefn(), "getLocalCompositeTypes", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(nameScopeEClass, this.getConstrainedTypeDefn(), "getLocalConstrainedTypes", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(nameScopeEClass, this.getNameScope(), "getContainingScope", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(nameScopeEClass, this.getTypeDefn(), "resolve", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getTypeRef(), "ref", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(annotationsEClass, Annotations.class, "Annotations", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAnnotations_ShortDefinition(), ecorePackage.getEString(), "shortDefinition", null, 0, 1, Annotations.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAnnotations_Definition(), ecorePackage.getEString(), "definition", null, 0, 1, Annotations.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAnnotations_Comment(), ecorePackage.getEString(), "comment", null, 0, 1, Annotations.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAnnotations_Requirements(), ecorePackage.getEString(), "requirements", null, 0, 1, Annotations.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAnnotations_RimMapping(), ecorePackage.getEString(), "rimMapping", null, 0, 1, Annotations.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAnnotations_V2Mapping(), ecorePackage.getEString(), "v2Mapping", null, 0, 1, Annotations.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAnnotations_Todo(), ecorePackage.getEString(), "todo", null, 0, 1, Annotations.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAnnotations_CommitteeNotes(), ecorePackage.getEString(), "committeeNotes", null, 0, 1, Annotations.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(profiledElementDefnEClass, ProfiledElementDefn.class, "ProfiledElementDefn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProfiledElementDefn_Inherited(), ecorePackage.getEBoolean(), "inherited", null, 0, 1, ProfiledElementDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProfiledElementDefn_Aggregation(), ecorePackage.getEString(), "aggregation", null, 0, 1, ProfiledElementDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProfiledElementDefn_FixedValue(), ecorePackage.getEString(), "fixedValue", null, 0, 1, ProfiledElementDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProfiledElementDefn_TargetUri(), ecorePackage.getEString(), "targetUri", null, 0, 1, ProfiledElementDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProfiledElementDefn_ProfileName(), ecorePackage.getEString(), "profileName", null, 0, 1, ProfiledElementDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(invariantRefEClass, InvariantRef.class, "InvariantRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInvariantRef_Name(), ecorePackage.getEString(), "name", null, 0, 1, InvariantRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(bindingTypeEEnum, BindingType.class, "BindingType");
		addEEnumLiteral(bindingTypeEEnum, BindingType.UNBOUND);
		addEEnumLiteral(bindingTypeEEnum, BindingType.CODE_LIST);
		addEEnumLiteral(bindingTypeEEnum, BindingType.VALUE_SET);
		addEEnumLiteral(bindingTypeEEnum, BindingType.REFERENCE);
		addEEnumLiteral(bindingTypeEEnum, BindingType.SPECIAL);

		initEEnum(bindingStrengthEEnum, BindingStrength.class, "BindingStrength");
		addEEnumLiteral(bindingStrengthEEnum, BindingStrength.UNSTATED);
		addEEnumLiteral(bindingStrengthEEnum, BindingStrength.REQUIRED);
		addEEnumLiteral(bindingStrengthEEnum, BindingStrength.PREFERRED);
		addEEnumLiteral(bindingStrengthEEnum, BindingStrength.SUGGESTED);

		initEEnum(searchTypeEEnum, SearchType.class, "SearchType");
		addEEnumLiteral(searchTypeEEnum, SearchType.COMPOSITE);
		addEEnumLiteral(searchTypeEEnum, SearchType.NUMBER);
		addEEnumLiteral(searchTypeEEnum, SearchType.STRING);
		addEEnumLiteral(searchTypeEEnum, SearchType.DATE);
		addEEnumLiteral(searchTypeEEnum, SearchType.REFERENCE);
		addEEnumLiteral(searchTypeEEnum, SearchType.TOKEN);
		addEEnumLiteral(searchTypeEEnum, SearchType.QUANTITY);
		addEEnumLiteral(searchTypeEEnum, SearchType.URI);

		initEEnum(bindingExtensibilityEEnum, BindingExtensibility.class, "BindingExtensibility");
		addEEnumLiteral(bindingExtensibilityEEnum, BindingExtensibility.COMPLETE);
		addEEnumLiteral(bindingExtensibilityEEnum, BindingExtensibility.EXTENSIBLE);

		initEEnum(searchRepeatModeEEnum, SearchRepeatMode.class, "SearchRepeatMode");
		addEEnumLiteral(searchRepeatModeEEnum, SearchRepeatMode.SINGLE);
		addEEnumLiteral(searchRepeatModeEEnum, SearchRepeatMode.UNION);
		addEEnumLiteral(searchRepeatModeEEnum, SearchRepeatMode.INTERSECTION);

		initEEnum(exampleTypeEEnum, ExampleType.class, "ExampleType");
		addEEnumLiteral(exampleTypeEEnum, ExampleType.XML);
		addEEnumLiteral(exampleTypeEEnum, ExampleType.CSV);
		addEEnumLiteral(exampleTypeEEnum, ExampleType.TOOL);

		initEEnum(xmlFormatHintEEnum, XmlFormatHint.class, "XmlFormatHint");
		addEEnumLiteral(xmlFormatHintEEnum, XmlFormatHint.ELEMENT);
		addEEnumLiteral(xmlFormatHintEEnum, XmlFormatHint.ATTRIBUTE);
		addEEnumLiteral(xmlFormatHintEEnum, XmlFormatHint.TEXT_NODE);
		addEEnumLiteral(xmlFormatHintEEnum, XmlFormatHint.XHTML_ELEMENT);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
		addAnnotation
		  (getDefinitions_Event(), 
		   source, 
		   new String[] {
			 "name", "event"
		   });		
		addAnnotation
		  (getElementDefn_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getElementDefn_MinCardinality(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getElementDefn_MaxCardinality(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getElementDefn_IsModifier(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getElementDefn_Type(), 
		   source, 
		   new String[] {
			 "name", "type"
		   });		
		addAnnotation
		  (getElementDefn_Mappings(), 
		   source, 
		   new String[] {
			 "name", "mapping"
		   });		
		addAnnotation
		  (getElementDefn_ExampleValue(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getInvariant_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getInvariant_Description(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getInvariant_Human(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getInvariant_Ocl(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getInvariant_Xpath(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });			
		addAnnotation
		  (getBindingDefn_Source(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getBindingDefn_Code(), 
		   source, 
		   new String[] {
			 "name", "code"
		   });		
		addAnnotation
		  (getResourceDefn_Search(), 
		   source, 
		   new String[] {
			 "name", "search"
		   });		
		addAnnotation
		  (getExample_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getExample_Description(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getExample_Path(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getExample_InBook(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getDefinedCode_Code(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getDefinedCode_Definition(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getPrimitiveDefn_Pattern(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getConstrainedTypeDefn_Detail(), 
		   source, 
		   new String[] {
			 "name", "detail"
		   });		
		addAnnotation
		  (getEventDefn_Code(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getEventDefn_Definition(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getEventDefn_FollowUps(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getEventDefn_Usage(), 
		   source, 
		   new String[] {
			 "name", "usage"
		   });		
		addAnnotation
		  (getProfileDefn_Resources(), 
		   source, 
		   new String[] {
			 "name", "resource"
		   });		
		addAnnotation
		  (getCompositeTypeDefn_Element(), 
		   source, 
		   new String[] {
			 "name", "element"
		   });		
		addAnnotation
		  (getCompositeTypeDefn_Invariant(), 
		   source, 
		   new String[] {
			 "name", "invariant"
		   });		
		addAnnotation
		  (getNameScope_Type(), 
		   source, 
		   new String[] {
			 "name", "type",
			 "namespace", ""
		   });		
		addAnnotation
		  (getNameScope_Binding(), 
		   source, 
		   new String[] {
			 "name", "binding"
		   });		
		addAnnotation
		  (getAnnotations_ShortDefinition(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getAnnotations_Definition(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getAnnotations_Comment(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getAnnotations_Requirements(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getAnnotations_RimMapping(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getAnnotations_V2Mapping(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getAnnotations_Todo(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getAnnotations_CommitteeNotes(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });			
		addAnnotation
		  (getInvariantRef_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });
	}

} //FhirPackageImpl
