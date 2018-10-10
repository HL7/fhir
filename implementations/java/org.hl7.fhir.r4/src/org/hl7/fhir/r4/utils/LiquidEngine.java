package org.hl7.fhir.r4.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.exceptions.PathEngineException;
import org.hl7.fhir.r4.context.IWorkerContext;
import org.hl7.fhir.r4.model.Base;
import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.ExpressionNode;
import org.hl7.fhir.r4.model.TypeDetails;
import org.hl7.fhir.r4.utils.FHIRPathEngine.IEvaluationContext;
import org.hl7.fhir.utilities.Utilities;

public class LiquidEngine implements IEvaluationContext {

  private IEvaluationContext externalHostServices;
  private FHIRPathEngine engine;

  private static class LiquidEngineContext {
    private Object externalContext;
    private Map<String, Base> vars = new HashMap<>();

    public LiquidEngineContext(Object externalContext) {
      super();
      this.externalContext = externalContext;
    }

    public LiquidEngineContext(LiquidEngineContext existing) {
      super();
      externalContext = existing.externalContext;
      vars.putAll(existing.vars);
    }
  }

  public LiquidEngine(IWorkerContext context, IEvaluationContext hostServices) {
    super();
    this.externalHostServices = hostServices;
    engine = new FHIRPathEngine(context);
    engine.setHostServices(this);
  }

  public LiquidDocument parse(String source) throws Exception {
    return new LiquidParser(source).parse();
  }

  public String evaluate(LiquidDocument document, DomainResource resource, Object appContext) throws FHIRException {
    StringBuilder b = new StringBuilder();
    LiquidEngineContext ctxt = new LiquidEngineContext(appContext);
    for (LiquidNode n : document.body) {
      n.evaluate(b, resource, ctxt);
    }
    return b.toString();
  }

  private abstract class LiquidNode {
    protected void closeUp() {}

    public abstract void evaluate(StringBuilder b, DomainResource resource, LiquidEngineContext ctxt) throws FHIRException;
  }

  private class LiquidConstant extends LiquidNode {
    private String constant;
    private StringBuilder b = new StringBuilder();

    @Override
    protected void closeUp() {
      constant = b.toString();
      b = null;
    }

    public void addChar(char ch) {
      b.append(ch);
    }

    @Override
    public void evaluate(StringBuilder b, DomainResource resource, LiquidEngineContext ctxt) {
      b.append(constant);
    }
  }

  private class LiquidStatement extends LiquidNode {
    private String statement;
    private ExpressionNode compiled;

    @Override
    public void evaluate(StringBuilder b, DomainResource resource, LiquidEngineContext ctxt) throws FHIRException {
      if (compiled == null)
        compiled = engine.parse(statement);
      b.append(engine.evaluateToString(ctxt, resource, resource, compiled));
    }
  }

  private class LiquidIf extends LiquidNode {
    private String condition;
    private ExpressionNode compiled;
    private List<LiquidNode> thenBody = new ArrayList<>();
    private List<LiquidNode> elseBody = new ArrayList<>();

    @Override
    public void evaluate(StringBuilder b, DomainResource resource, LiquidEngineContext ctxt) throws FHIRException {
      if (compiled == null)
        compiled = engine.parse(condition);
      boolean ok = engine.evaluateToBoolean(ctxt, resource, resource, compiled); 
      List<LiquidNode> list = ok ? thenBody : elseBody;
      for (LiquidNode n : list) {
        n.evaluate(b, resource, ctxt);
      }
    }
  }

  private class LiquidLoop extends LiquidNode {
    private String varName;
    private String condition;
    private ExpressionNode compiled;
    private List<LiquidNode> body = new ArrayList<>();
    @Override
    public void evaluate(StringBuilder b, DomainResource resource, LiquidEngineContext ctxt) throws FHIRException {
      if (compiled == null)
        compiled = engine.parse(condition);
      List<Base> list = engine.evaluate(ctxt, resource, resource, compiled);
      LiquidEngineContext lctxt = new LiquidEngineContext(ctxt);
      for (Base o : list) {
        lctxt.vars.put(varName, o);
        for (LiquidNode n : body) {
          n.evaluate(b, resource, lctxt);
        }
      }
    }
  }

  public static class LiquidDocument  {
    private List<LiquidNode> body = new ArrayList<>();

  }

  private class LiquidParser {

    private String source;
    private int cursor;

    public LiquidParser(String source) {
      this.source = source;
      cursor = 0;
    }

    private char next1() {
      if (cursor >= source.length())
        return 0;
      else
        return source.charAt(cursor);
    }

    private char next2() {
      if (cursor >= source.length()-1)
        return 0;
      else
        return source.charAt(cursor+1);
    }

    private char grab() {
      cursor++;
      return source.charAt(cursor-1);
    }

    public LiquidDocument parse() throws Exception {
      LiquidDocument doc = new LiquidDocument();
      parseList(doc.body, new String[0]);
      return doc;
    }

    private String parseList(List<LiquidNode> list, String[] terminators) throws Exception {
      String close = null;
      while (cursor < source.length()) {
        if (next1() == '{' && (next2() == '%' || next2() == '{' )) {
          if (next2() == '%') { 
            String cnt = parseTag('%');
            if (Utilities.existsInList(cnt, terminators)) {
              close = cnt;
              break;
            } else if (cnt.startsWith("if "))
              list.add(parseIf(cnt));
            else if (cnt.startsWith("loop "))
              list.add(parseLoop(cnt.substring(4).trim()));
            else
              throw new Exception("Unknown flow control statement "+cnt);
          } else { // next2() == '{'
            list.add(parseStatement());
          }
        } else {
          if (list.size() == 0 || !(list.get(list.size()-1) instanceof LiquidConstant))
            list.add(new LiquidConstant());
          ((LiquidConstant) list.get(list.size()-1)).addChar(grab());
        }
      }
      for (LiquidNode n : list)
        n.closeUp();
      if (terminators.length > 0)
        if (!Utilities.existsInList(close, terminators))
          throw new Exception("Found end of script looking for "+terminators);
      return close;
    }

    private LiquidNode parseIf(String cnt) throws Exception {
      LiquidIf res = new LiquidIf();
      res.condition = cnt.substring(3).trim();
      String term = parseList(res.thenBody, new String[] { "else", "endif"} );
      if ("else".equals(term))
        term = parseList(res.elseBody, new String[] { "endif"} );
      return res;
    }

    private LiquidNode parseLoop(String cnt) throws Exception {
      int i = 0;
      while (!Character.isWhitespace(cnt.charAt(i)))
        i++;
      LiquidLoop res = new LiquidLoop();
      res.varName = cnt.substring(0, i);
      while (Character.isWhitespace(cnt.charAt(i)))
        i++;
      int j = i;
      while (!Character.isWhitespace(cnt.charAt(i)))
        i++;
      if (!"in".equals(cnt.substring(j, i)))
        throw new Exception("Error reading loop: "+cnt);
      res.condition = cnt.substring(i).trim();
      parseList(res.body, new String[] { "endloop"} );
      return res;
    }

    private String parseTag(char ch) throws Exception {
      grab(); 
      grab();
      StringBuilder b = new StringBuilder();
      while (cursor < source.length() && !(next1() == '%' && next2() == '}')) {
        b.append(grab());
      }
      if (!(next1() == '%' && next2() == '}')) 
        throw new Exception("Unterminated Liquid statement {% "+b.toString());
      grab(); 
      grab();
      return b.toString().trim();
    }

    private LiquidStatement parseStatement() throws Exception {
      grab(); 
      grab();
      StringBuilder b = new StringBuilder();
      while (cursor < source.length() && !(next1() == '}' && next2() == '}')) {
        b.append(grab());
      }
      if (!(next1() == '}' && next2() == '}')) 
        throw new Exception("Unterminated Liquid statement {{ "+b.toString());
      grab(); 
      grab();
      LiquidStatement res = new LiquidStatement();
      res.statement = b.toString().trim();
      return res;
    }

  }

  @Override
  public Base resolveConstant(Object appContext, String name, boolean beforeContext) throws PathEngineException {
    LiquidEngineContext ctxt = (LiquidEngineContext) appContext;
    if (ctxt.vars.containsKey(name))
      return ctxt.vars.get(name);
    if (externalHostServices == null)
      return null;
    return externalHostServices.resolveConstant(ctxt.externalContext, name, beforeContext);
  }

  @Override
  public TypeDetails resolveConstantType(Object appContext, String name) throws PathEngineException {
    if (externalHostServices == null)
      return null;
    LiquidEngineContext ctxt = (LiquidEngineContext) appContext;
    return externalHostServices.resolveConstantType(ctxt.externalContext, name);
  }

  @Override
  public boolean log(String argument, List<Base> focus) {
    if (externalHostServices == null)
      return false;
    return externalHostServices.log(argument, focus);
  }

  @Override
  public FunctionDetails resolveFunction(String functionName) {
    if (externalHostServices == null)
      return null;
    return externalHostServices.resolveFunction(functionName);
  }

  @Override
  public TypeDetails checkFunction(Object appContext, String functionName, List<TypeDetails> parameters) throws PathEngineException {
    if (externalHostServices == null)
      return null;
    LiquidEngineContext ctxt = (LiquidEngineContext) appContext;
    return externalHostServices.checkFunction(ctxt.externalContext, functionName, parameters);
  }

  @Override
  public List<Base> executeFunction(Object appContext, String functionName, List<List<Base>> parameters) {
    if (externalHostServices == null)
      return null;
    LiquidEngineContext ctxt = (LiquidEngineContext) appContext;
    return externalHostServices.executeFunction(ctxt.externalContext, functionName, parameters);
  }

  @Override
  public Base resolveReference(Object appContext, String url) throws FHIRException {
    if (externalHostServices == null)
      return null;
    LiquidEngineContext ctxt = (LiquidEngineContext) appContext;
    return resolveReference(ctxt.externalContext, url);
  }

  @Override
  public boolean conformsToProfile(Object appContext, Base item, String url) throws FHIRException {
    if (externalHostServices == null)
      return false;
    LiquidEngineContext ctxt = (LiquidEngineContext) appContext;
    return conformsToProfile(ctxt.externalContext, item, url);
  }

}
