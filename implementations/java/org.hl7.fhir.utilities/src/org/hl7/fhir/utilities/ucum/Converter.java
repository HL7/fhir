/*******************************************************************************
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Kestral Computing P/L - initial implementation
 *******************************************************************************/

package org.hl7.fhir.utilities.ucum;

import java.math.BigDecimal;

import org.hl7.fhir.utilities.ucum.special.Registry;

/*
= [mu_0]
= 4.[pi].10*-7.N/A2
= (g.m/s2)/(C/s)2? 
= g.m/s2/(C2/s2)
= g.m/s2/C2.s2
= g.m/C2.s2/s2
= g.m/C2
= m.g.C-2 

 */
public class Converter {

	private UcumModel model;
	private Registry handlers;
	
	private Factor one = new Factor(1);
	
	/**
	 * @param model
	 */
	public Converter(UcumModel model, Registry handlers) {
		super();
		this.model = model;
		this.handlers = handlers;
	}

	
	public Canonical convert(Term term) throws Exception {
		return convertTerm(term);
	}
	
	private Canonical convertTerm(Term term) throws Exception {
		Canonical res = new Canonical(new BigDecimal(1), new Term());
		if (term.hasComp()) 
			res.getUnit().setComp(convertComp(res, term.getComp()));
		if (term.hasOp())
			res.getUnit().setOp(term.getOp());
		if (term.hasTerm()) {
			Canonical t = convertTerm(term.getTerm());
			res.setValue(res.getValue().multiply(t.getValue()));
			if (t.hasUnit())
				res.getUnit().setTermCheckOp(t.getUnit());
			else
				res.getUnit().setOp(null);
		}
		
		// normalise	
		debug("normalise", res.getUnit());
		if (res.getUnit().hasOp() && res.getUnit().getOp() == Operator.DIVISION) {
			res.getUnit().setOp(Operator.MULTIPLICATION);
			flipExponents(res.getUnit().getTerm());
			debug("flipped", res.getUnit());
		}

		if (!res.getUnit().hasComp() || res.getUnit().getComp() == one) {
			res.setUnit(res.getUnit().getTerm());
			debug("trimmed", res.getUnit());
		}

		// everything in scope is a multiplication operation. If comp is a term, then 
		// we are going to tack our term on the end of that term as a multiplication, and 
		// make comp our term
		
		if (res.hasUnit() && res.getUnit().hasComp() && res.getUnit().getComp() instanceof Term) {
			Term end = getEndTerm((Term) res.getUnit().getComp());
			assert end.getOp() == null;
			end.setOp(Operator.MULTIPLICATION);
			end.setTermCheckOp(res.getUnit().getTerm());
			res.setUnit((Term) res.getUnit().getComp());
			debug("reorged", res.getUnit());
		}

		if (res.hasUnit() && (!res.getUnit().hasComp() || res.getUnit().getComp() == one)){
			res.setUnit(res.getUnit().getTerm());
			debug("trimmed", res.getUnit());
		}		
		// now we have a linear list of terms, each with one component. 
		// we scan through the list looking for common components to factor out
		// we have to scan into the list because we may have deep duplicates 
		// from the previous flattening operation. we also remove anything that's
		// ended up with an exponent of 0 during this operation
		if (res.hasUnit())
			res.setUnit(removeDuplicateComponents(res.getUnit()));
		
		if (res.hasUnit() && !res.getUnit().hasTerm())
			res.getUnit().setOp(null);
		debug("norm finished", res.getUnit());
//		System.out.println("value: "+res.getValue().toPlainString()+"; units: "+new ExpressionComposer().compose(res.getUnit()));
		return res;
	}


	private void debug(String state, Term unit) {
//		System.out.println(state+": "+new ExpressionComposer().compose(unit));
	}


	private Term getEndTerm(Term term) {
		if (term.hasTerm())
			return getEndTerm(term.getTerm());
		else
			return term;
	}


	private Term removeDuplicateComponents(Term unit) {
		if (unit == null)
			return null;
		assert unit.getComp() instanceof Symbol; // because that should be all that's left
		Symbol symO = (Symbol) unit.getComp();
		Term inner = findDuplicateCompOwner(unit.getTerm(), symO);
		if (inner != null) {
			Symbol symI = (Symbol) inner.getComp();
			symI.setExponent(symI.getExponent()+symO.getExponent());
			return removeDuplicateComponents(unit.getTerm());
		}
		if (symO.getExponent() == 0)
			return removeDuplicateComponents(unit.getTerm());
	    unit.setTermCheckOp(removeDuplicateComponents(unit.getTerm()));
		return unit;
	}


	private Term findDuplicateCompOwner(Term term, Symbol comp) {
		if (term == null)
			return null;
		if (term.getComp() instanceof Symbol) {
			Symbol sym = (Symbol) term.getComp();
			if (sym.getPrefix() == comp.getPrefix() && // i.e. null
					sym.getUnit() == comp.getUnit())
				return term;
		}
		return findDuplicateCompOwner(term.getTerm(), comp);
	}


	private void flipExponents(Term term) {
		if (term.getComp() instanceof Symbol) {
			((Symbol)term.getComp()).invertExponent();
		}
		if (term.hasTerm()) {
			flipExponents(term.getTerm());
		}		
	}


	private Component convertComp(Canonical ctxt, Component comp) throws Exception {
		if (comp instanceof Term) {
			Canonical t = convertTerm((Term) comp);
			ctxt.multiplyValue(t.getValue());
			return t.getUnit();
		} else if (comp instanceof Factor) {
			ctxt.multiplyValue(((Factor) comp).getValue());
			return one; // nothing to convert
		} else if (comp instanceof Symbol)
			return convertSymbol(ctxt, (Symbol) comp);
		else 
			throw new Exception("unknown component type "+comp.getClass().toString());
	}


	private Component convertSymbol(Canonical ctxt, Symbol comp) throws Exception {
		if (comp.hasPrefix()) {
			ctxt.multiplyValue(comp.getPrefix().getValue());
		}
			
			
		if (comp.getUnit().getKind() == ConceptKind.BASEUNIT) {
			Symbol res = new Symbol();
			res.setUnit(comp.getUnit());
			res.setExponent(comp.getExponent());
			return res;			
		} else {
			DefinedUnit unit = (DefinedUnit) comp.getUnit();
			String u = unit.getValue().getUnit();
			if (unit.isSpecial()) {
				if (!handlers.exists(unit.getCode())) 
					throw new Exception("Not handled yet (special unit)");
				else {
					u = handlers.get(unit.getCode()).getUnits();
					ctxt.multiplyValue(handlers.get(unit.getCode()).getValue());
				}
			} else
				ctxt.multiplyValue(unit.getValue().getValue());
			Term canonical = new ExpressionParser(model).parse(u);
			if (canonical.hasComp() && !canonical.hasOp() && !canonical.hasTerm()) {
				Component ret = convertComp(ctxt, canonical.getComp());
				if (comp.getExponent() == 1)
					return ret;
				else if (ret instanceof Factor) {
					((Factor) ret).setValue(comp.getExponent() + ((Factor) ret).getValue());
					return ret;
				} else if (ret instanceof Symbol) {
					((Symbol) ret).setExponent(comp.getExponent() * ((Symbol) ret).getExponent());
					return ret;
				} else if (ret instanceof Term) {
					applyExponent((Term) ret, comp.getExponent());
					return ret;
				} else 
					throw new Exception("unknown component type "+comp.getClass().toString());
			} else { 
				Canonical t1 = convertTerm(canonical);
				Term ret = t1.getUnit();
				if (comp.getExponent() == -1 && ret.hasComp() && ret.hasOp() && ret.hasTerm() && ret.getTerm().hasComp() && !ret.getTerm().hasOp() && !ret.getTerm().hasTerm()) {
					Component t = ret.getTerm().getComp();
					ret.getTerm().setComp(ret.getComp());
					ret.setComp(t);
					ctxt.divideValue(t1.getValue());
					return ret;
				} else if (comp.getExponent() != 1) {
					ctxt.multiplyValue(t1.getValue());
					// what we have to do is push the exponent into the all the symbols contained herein
					applyExponent(ret, comp.getExponent());
					return ret;
				} else {
					ctxt.multiplyValue(t1.getValue());
					return ret;
				}
			}
		}
	}


	private void applyExponent(Term term, int exponent) {
		if (term == null)
			return;
		if (term.hasComp()) {
			if (term.getComp() instanceof Term) {
				applyExponent((Term) term.getComp(), exponent);
			} else if (term.getComp() instanceof Symbol) {
				Symbol sym = (Symbol) term.getComp();
				sym.setExponent(sym.getExponent() * exponent);
			}
			
		}
		applyExponent(term.getTerm(), exponent);		
	}

}
