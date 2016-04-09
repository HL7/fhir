package org.hl7.fhir.dstu3.utils;

/*
 open issues:
   compatibility of inputs on extension...
*/
import java.util.List;

import org.hl7.fhir.dstu3.exceptions.FHIRException;
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
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapInputMode;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapListMode;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapModelMode;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapStructureComponent;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapTransform;
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
