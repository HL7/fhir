package org.hl7.fhir.dstu3.utils;

/*
 open issues:
   compatibility of inputs on extension...
*/
import java.util.List;

import org.hl7.fhir.dstu3.exceptions.FHIRException;
import org.hl7.fhir.dstu3.model.Enumeration;
import org.hl7.fhir.dstu3.model.ExpressionNode;
import org.hl7.fhir.dstu3.model.IdType;
import org.hl7.fhir.dstu3.model.StringType;
import org.hl7.fhir.dstu3.model.StructureMap;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapGroupComponent;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapGroupInputComponent;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapGroupRuleComponent;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapGroupRuleDependentComponent;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapGroupRuleSourceComponent;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapGroupRuleTargetComponent;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapGroupRuleTargetParameterComponent;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapInputMode;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapListMode;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapModelMode;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapStructureComponent;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapTransform;
import org.hl7.fhir.dstu3.model.UriType;
import org.hl7.fhir.dstu3.utils.FHIRLexer.FHIRLexerException;
import org.hl7.fhir.utilities.Utilities;

public class StructureMapCompiler {

  private IWorkerContext worker;
  private FHIRPathEngine fluent;
  
  public StructureMapCompiler(IWorkerContext worker) {
    super();
    this.worker = worker;
    fluent = new FHIRPathEngine(worker);
  }

  public String render(StructureMap map) throws FHIRException {
  	StringBuilder b = new StringBuilder();
  	b.append("map \"");
  	b.append(map.getUrl());
  	b.append("\" = \"");
  	b.append(Utilities.escapeJava(map.getName()));
  	b.append("\"\r\n\r\n");
  	
  	renderUses(b, map);
  	renderImports(b, map);
  	for (StructureMapGroupComponent g : map.getGroup())
  		renderGroup(b, g);
  	return b.toString();
  }
  
	private void renderUses(StringBuilder b, StructureMap map) {
  	for (StructureMapStructureComponent s : map.getStructure()) {
  		b.append("uses \"");
  		b.append(s.getUrl());
    	b.append("\" as ");
    	b.append(s.getMode().toCode());
    	b.append("\r\n");
    	renderDoco(b, s.getDocumentation());
  	}
  	if (map.hasStructure())
  		b.append("\r\n");
	}

  private void renderImports(StringBuilder b, StructureMap map) {
  	for (UriType s : map.getImport()) {
  		b.append("imports \"");
  		b.append(s.getValue());
    	b.append("\"\r\n");
  	}
  	if (map.hasImport())
  		b.append("\r\n");
	}

	private void renderGroup(StringBuilder b, StructureMapGroupComponent g) throws FHIRException {
    b.append("group ");
    b.append(g.getName());
    if (g.hasExtends()) {
      b.append(" extends ");
      b.append(g.getExtends());
    }
    if (g.hasDocumentation()) 
    	renderDoco(b, g.getDocumentation());
    b.append("\r\n");
    for (StructureMapGroupInputComponent gi : g.getInput()) {
    	b.append("  input ");
    	b.append(gi.getName());
    	if (gi.hasType()) {
    	  b.append(" : ");
    	  b.append(gi.getType());
    	}
    	b.append(" as ");
    	b.append(gi.getMode().toCode());
    	b.append(";\r\n");
    }
    if (g.hasInput())
    	b.append("\r\n");
    for (StructureMapGroupRuleComponent r : g.getRule()) {
    	renderRule(b, r, 2);
    }
		b.append("\r\nendgroup\r\n");
	}

	private void renderRule(StringBuilder b, StructureMapGroupRuleComponent r, int indent) throws FHIRException {
		for (int i = 0; i < indent; i++)
			b.append(' ');
		b.append(r.getName());
		b.append(": for ");
		boolean first = true;
		for (StructureMapGroupRuleSourceComponent rs : r.getSource()) {
			if (first)
				first = false;
			else
				b.append(", ");
			renderSource(b, rs);
		}
		if (r.getTarget().size() > 1) {
			b.append(" make ");
			first = true;
			for (StructureMapGroupRuleTargetComponent rt : r.getTarget()) {
				if (first)
					first = false;
				else
					b.append(", ");
				b.append("\r\n");
				for (int i = 0; i < indent+4; i++)
					b.append(' ');
				renderTarget(b, rt);
			}
		} else if (r.hasTarget()) { 
			b.append(" make ");
			renderTarget(b, r.getTarget().get(0));
		}
		if (r.hasRule()) {
			b.append(" then {");
    	renderDoco(b, r.getDocumentation());
			for (int i = 0; i < indent; i++)
				b.append(' ');
			b.append("}\r\n");
		} else {
			if (r.hasDependent()) {
				first = true;
				for (StructureMapGroupRuleDependentComponent rd : r.getDependent()) {
					if (first)
						first = false;
					else
						b.append(", ");
					b.append(rd.getName());
					b.append("(");
					boolean ifirst = true;
					for (StringType rdp : rd.getVariable()) {
						if (ifirst)
							ifirst = false;
						else
							b.append(", ");
						b.append(rd.getVariable());
					}
				}
			}
    	renderDoco(b, r.getDocumentation());
			b.append("\r\n");
		}
	
	}

	private void renderSource(StringBuilder b, StructureMapGroupRuleSourceComponent rs) {
		if (!rs.getRequired())
			b.append("optional ");
		b.append(rs.getContext());
		if (rs.hasElement()) {
			b.append('.');
			b.append(rs.getElement());
		}
		if (rs.hasListMode()) {
			b.append(" ");
			if (rs.getListMode() == StructureMapListMode.SHARE)
				b.append("only_one");
			else
				b.append(rs.getListMode().toCode());
		}
		if (rs.hasVariable()) {
			b.append(" as ");
			b.append(rs.getVariable());
		}
		if (rs.hasCondition())  {
			b.append(" where ");
			b.append(rs.getCondition());
		}
		if (rs.hasCheck())  {
			b.append(" check ");
			b.append(rs.getCheck());
		}
	}

	private void renderTarget(StringBuilder b, StructureMapGroupRuleTargetComponent rt) throws FHIRException {
		b.append(rt.getContext());
		if (rt.hasElement())  {
			b.append('.');
			b.append(rt.getElement());
		}
		if (rt.hasTransform()) {
			b.append(" = ");
			if (rt.getTransform() == StructureMapTransform.COPY && rt.getParameter().size() == 1) {
				renderTransformParam(b, rt.getParameter().get(0));
			} else {
				b.append(rt.getTransform().toCode());
				b.append("(");
				boolean first = true;
				for (StructureMapGroupRuleTargetParameterComponent rtp : rt.getParameter()) {
					if (first)
						first = false;
					else
						b.append(", ");
					renderTransformParam(b, rtp);
				}
				b.append(")");
			}
		}
		if (rt.hasVariable()) {
			b.append(" as ");
			b.append(rt.getVariable());
		}
		for (Enumeration<StructureMapListMode> lm : rt.getListMode()) {
			b.append(" ");
			b.append(lm.getValue().toCode());
			if (lm.getValue() == StructureMapListMode.SHARE) {
				b.append(" ");
				b.append(rt.getListRuleId());
			}
		}
	}

	private void renderTransformParam(StringBuilder b, StructureMapGroupRuleTargetParameterComponent rtp) throws FHIRException {
		if (rtp.hasValueBooleanType())
			b.append(rtp.getValueBooleanType().asStringValue());
		else if (rtp.hasValueDecimalType())
			b.append(rtp.getValueDecimalType().asStringValue());
		else if (rtp.hasValueIdType())
			b.append(rtp.getValueIdType().asStringValue());
		else if (rtp.hasValueDecimalType())
			b.append(rtp.getValueDecimalType().asStringValue());
		else if (rtp.hasValueIntegerType())
			b.append(rtp.getValueIntegerType().asStringValue());
		else 
			b.append(Utilities.escapeJava(rtp.getValueStringType().asStringValue()));
	}

	private void renderDoco(StringBuilder b, String doco) {
		if (Utilities.noString(doco))
			return;
	 b.append(" // ");
	 b.append(doco.replace("\r\n", " ").replace("\r", " ").replace("\n", " "));
	}

	public StructureMap parse(String text) throws FHIRException {
    FHIRLexer lexer = new FHIRLexer(text);
    if (lexer.done())
      throw lexer.error("Map Input cannot be empty");
    lexer.skipComments();
    lexer.token("map");
    StructureMap result = new StructureMap();
    result.setUrl(lexer.readConstant("url"));
    lexer.token("=");
    result.setName(lexer.readConstant("name"));
    lexer.skipComments();

    while (lexer.hasToken("uses"))
      parseUses(result, lexer);
    while (lexer.hasToken("imports"))
      parseImports(result, lexer);

    parseGroup(result, lexer);

    while (!lexer.done()) {
      parseGroup(result, lexer);    
    }

    return result;
  }

  private void parseUses(StructureMap result, FHIRLexer lexer) throws FHIRException {
    lexer.token("uses");
    StructureMapStructureComponent st = result.addStructure();
    st.setUrl(lexer.readConstant("url"));
    lexer.token("as");
    st.setMode(StructureMapModelMode.fromCode(lexer.take()));
    lexer.skipToken(";");
    if (lexer.hasComment()) {
      st.setDocumentation(lexer.take().substring(2).trim());
    }
    lexer.skipComments();
  }

  private void parseImports(StructureMap result, FHIRLexer lexer) throws FHIRException {
    lexer.token("imports");
    result.addImport(lexer.readConstant("url"));
    lexer.skipToken(";");
    if (lexer.hasComment()) {
      lexer.next();
    }
    lexer.skipComments();
  }

  private void parseGroup(StructureMap result, FHIRLexer lexer) throws FHIRException {
    lexer.token("group");
    StructureMapGroupComponent group = result.addGroup();
    group.setName(lexer.take());
    if (lexer.hasToken("extends")) {
      lexer.next();
      group.setExtends(lexer.take());
    }
    lexer.skipComments();
    while (lexer.hasToken("input")) 
      parseInput(group, lexer);
    while (!lexer.hasToken("endgroup")) {
      if (lexer.done())
        throw lexer.error("premature termination expecting 'endgroup'");
      parseRule(group.getRule(), lexer);
    }
    lexer.next();
    lexer.skipComments();
  }

  private void parseInput(StructureMapGroupComponent group, FHIRLexer lexer) throws FHIRException {
    lexer.token("input");
    StructureMapGroupInputComponent input = group.addInput();
    input.setName(lexer.take());
    if (lexer.hasToken(":")) {
      lexer.token(":");
      input.setType(lexer.take());
    }
    lexer.token("as");
    input.setMode(StructureMapInputMode.fromCode(lexer.take()));
    if (lexer.hasComment()) {
      input.setDocumentation(lexer.take().substring(2).trim());
    }
    lexer.skipToken(";");
    lexer.skipComments();
  }

  private void parseRule(List<StructureMapGroupRuleComponent> list, FHIRLexer lexer) throws FHIRException {
    StructureMapGroupRuleComponent rule = new StructureMapGroupRuleComponent(); 
    list.add(rule);
    rule.setName(lexer.takeDottedToken());
    lexer.token(":");
    lexer.token("for");
    boolean done = false;
    while (!done) {
      parseSource(rule, lexer);
      done = !lexer.hasToken(",");
      if (!done)
        lexer.next();
    }
    if (lexer.hasToken("make")) {
      lexer.token("make");
      done = false;
      while (!done) {
        parseTarget(rule, lexer);
        done = !lexer.hasToken(",");
        if (!done)
          lexer.next();
      }
    }
    if (lexer.hasToken("then")) {
      lexer.token("then");
      if (lexer.hasToken("{")) {
        lexer.token("{");
        while (!lexer.hasToken("}")) {
          if (lexer.done())
            throw lexer.error("premature termination expecting '}' in nested group");
          parseRule(rule.getRule(), lexer);
        }      
      } else {
        done = false;
        while (!done) {
          parseRuleReference(rule, lexer);
          done = !lexer.hasToken(",");
          if (!done)
            lexer.next();
        }
      }
    }
    if (lexer.hasComment()) {
      rule.setDocumentation(lexer.take().substring(2).trim());
    }
    lexer.skipComments();
  }

  private void parseRuleReference(StructureMapGroupRuleComponent rule, FHIRLexer lexer) throws FHIRLexerException {
    StructureMapGroupRuleDependentComponent ref = rule.addDependent();
    ref.setName(lexer.take());
    lexer.token("(");
    boolean done = false;
    while (!done) {
      ref.addVariable(lexer.take());
      done = !lexer.hasToken(",");
      if (!done)
        lexer.next();
    }
    lexer.token(")");
  }

  private void parseSource(StructureMapGroupRuleComponent rule, FHIRLexer lexer) throws FHIRException {
    StructureMapGroupRuleSourceComponent source = rule.addSource();
    if (lexer.hasToken("optional")) 
      lexer.next();
    else
      source.setRequired(true);
    source.setContext(lexer.take());
    if (lexer.hasToken(".")) {
      lexer.token(".");
      source.setElement(lexer.take());
    }
    if (Utilities.existsInList(lexer.getCurrent(), "first", "last", "only_one"))
      if (lexer.getCurrent().equals("only_one")) { 
        source.setListMode(StructureMapListMode.SHARE);
        lexer.take();
      } else 
        source.setListMode(StructureMapListMode.fromCode(lexer.take()));
    
    if (lexer.hasToken("as")) {
      lexer.take();
      source.setVariable(lexer.take());
    }
    if (lexer.hasToken("where")) {
      lexer.take();
      ExpressionNode node = fluent.parse(lexer);
      source.setUserData("map.where.expression", node);
      source.setCondition(node.toString());
    }
    if (lexer.hasToken("check")) {
      lexer.take();
      ExpressionNode node = fluent.parse(lexer);
      source.setUserData("map.where.check", node);
      source.setCheck(node.toString());
    }
  }

  private void parseTarget(StructureMapGroupRuleComponent rule, FHIRLexer lexer) throws FHIRException {
    StructureMapGroupRuleTargetComponent target = rule.addTarget();
    target.setContext(lexer.take());
    if (lexer.hasToken(".")) {
      lexer.token(".");
      target.setElement(lexer.take());
    }
    if (lexer.hasToken("=")) {
      lexer.token("=");
      String name = lexer.take();
      if (lexer.hasToken("(")) {
        target.setTransform(StructureMapTransform.fromCode(name));
        lexer.token("(");
        while (!lexer.hasToken(")")) {
          parseParameter(target, lexer);
          if (!lexer.hasToken(")"))
          	lexer.token(",");
        }        
        lexer.token(")");
      } else {
        target.setTransform(StructureMapTransform.COPY);
        target.addParameter().setValue(new IdType(name));
      }
    }
    if (lexer.hasToken("as")) {
      lexer.take();
      target.setVariable(lexer.take());
    }
    while (Utilities.existsInList(lexer.getCurrent(), "first", "last", "share", "only_one")) {
      if (lexer.getCurrent().equals("share")) {
        target.addListMode(StructureMapListMode.SHARE);
        lexer.next();
        target.setListRuleId(lexer.take());
      } else if (lexer.getCurrent().equals("first")) 
        target.addListMode(StructureMapListMode.FIRST);
      else
        target.addListMode(StructureMapListMode.LAST);
      lexer.next();
    }
  }

  private void parseParameter(StructureMapGroupRuleTargetComponent target, FHIRLexer lexer) throws FHIRLexerException {
    target.addParameter().setValue(new StringType(lexer.take()));    
  }
  
}
