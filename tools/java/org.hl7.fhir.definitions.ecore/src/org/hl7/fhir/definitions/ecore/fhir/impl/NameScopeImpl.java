package org.hl7.fhir.definitions.ecore.fhir.impl;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.ConstrainedTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.NameScope;
import org.hl7.fhir.definitions.ecore.fhir.TypeDefn;

public class NameScopeImpl {

	private NameScope scope;
	
	
	public NameScopeImpl( NameScope scope )
	{
		this.scope = scope;
	}
	
	
	/**
	 * <!-- begin-user-doc -->
	 * List all CompositeTypes that are defined in this scope. This excludes the types
	 * inherited from parent scopes.
	 * <!-- end-user-doc -->
	 */
	public EList<CompositeTypeDefn> getLocalCompositeTypes() {
		EList<CompositeTypeDefn> result = new BasicEList<CompositeTypeDefn>();
		
		for( TypeDefn t : scope.getType() )
		{		
			if( t.isComposite() )
				result.add((CompositeTypeDefn)t);
		}
		
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public EList<ConstrainedTypeDefn> getLocalConstrainedTypes() {
		EList<ConstrainedTypeDefn> result = new BasicEList<ConstrainedTypeDefn>();
		
		for( TypeDefn t : scope.getType() )
		{		
			if( t.isConstrained() )
				result.add((ConstrainedTypeDefn)t);
		}
		
		return result;
	}


	
}
