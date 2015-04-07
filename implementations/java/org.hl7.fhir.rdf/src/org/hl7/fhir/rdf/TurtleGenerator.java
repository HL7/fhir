package org.hl7.fhir.rdf;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.utilities.Utilities;

public class TurtleGenerator {

  private abstract class TripleObject implements Comparable<TripleObject> {
    
  }
  
  private class StringObject extends TripleObject implements Comparable<TripleObject> {
    private String value;

    public StringObject(String value) {
      super();
      this.value = value;
    }

    @Override
    public int compareTo(TripleObject o) {
      if (o instanceof StringObject)
        return value.compareTo(((StringObject) o).value);
      else
        return -1;
    }
  }
  
  private class ComplexObject extends TripleObject implements Comparable<TripleObject> {
    private List<PredicateObject> statements = new ArrayList<PredicateObject>();
    @Override
    public int compareTo(TripleObject o) {
      if (o instanceof ComplexObject) {
        ComplexObject other = ((ComplexObject) o);
        int r = Integer.compare(statements.size(), other.statements.size());
        int i = 0;
        while (r == 0 && i < statements.size()) {
          r = statements.get(i).getPredicate().compareTo(other.statements.get(i).getPredicate());
          if (r == 0)
            r = statements.get(i).getObject().compareTo(other.statements.get(i).getObject());
          i++;
        }
        return r;
      } else
        return 1;
    }
    
    public boolean write(LineOutputStreamWriter writer, int indent) throws Exception {
      if (statements.isEmpty()) 
        return false;
      if (statements.size() == 1 && statements.get(0).object instanceof StringObject && Utilities.noString(statements.get(0).comment)) {
        writer.write(" "+statements.get(0).predicate+" "+((StringObject) statements.get(0).object).value);
        return true;
      }
      String left = Utilities.padLeft("", ' ', indent);
      int i = 0;
      for (PredicateObject po : statements) {
        writer.write("\r\n");
        if (po.getObject() instanceof StringObject)
          writer.write(left+" "+po.getPredicate()+" "+((StringObject) po.getObject()).value);
        else {
          writer.write(left+" "+po.getPredicate()+" [");
          if (((ComplexObject) po.getObject()).write(writer, indent+2))
            writer.write(left+" ]");
          else
            writer.write("]");
        }
        i++;
        if (i < statements.size())
          writer.write(";");
        if (!Utilities.noString(po.comment)) 
          writer.write(" # "+escape(po.comment, false));
      }
      return true;      
    }
  }
  
  private class PredicateObject {
    protected String predicate;
    protected TripleObject object;
    protected String comment;
    
    public String getPredicate() {
      return predicate;
    }
    public TripleObject getObject() {
      return object;
    }
    public String getComment() {
      return comment;
    }
    
  }
  
  private class Triple extends PredicateObject implements Comparable<Triple> {
    private String section;
    private String subject;

    public Triple(String section, String subject, String predicate, TripleObject object, String comment) {
      super();
      this.section = section;
      this.subject = subject;
      this.predicate = predicate;
      this.object = object;
      this.comment = comment;
    }
    public String getSection() {
      return section;
    }
    public String getSubject() {
      return subject;
    }
    @Override
    public int compareTo(Triple o) {
      Triple other = (Triple) o;
      
      int i = section.compareTo(other.section);
      if (i == 0)
        i = subject.compareTo(other.subject);
      if (i == 0)
        i = Boolean.compare(!isDefiningPredicate(predicate), !isDefiningPredicate(other.predicate));
      if (i == 0)
        i = predicate.compareTo(other.predicate);
      if (i == 0)
        i = object.compareTo(other.object);
      return i;
    }
    private boolean isDefiningPredicate(String s) {
      return s.equals("a") || s.equals("rdfs:subClassOf") || s.equals("rdfs:subPropertyOf"); 
    }
    
  }

  private class Section {
    private String name;
    private Triple primary;
  }
  
  private List<Triple> triples = new ArrayList<Triple>();
  private List<Section> sections = new ArrayList<Section>();
  private Set<String> predicates = new HashSet<String>();
  
  
  private OutputStream destination;
  private Map<String, String> prefixes = new HashMap<String, String>();

  public TurtleGenerator(OutputStream destination) {
    super();
    this.destination = destination;
  }
  
  protected String pctEncode(String s) {
    if (s == null)
      return "";

    StringBuilder b = new StringBuilder();
    for (char c : s.toCharArray()) {
      if (c >= 'A' && c <= 'Z')
        b.append(c);
      else if (c >= 'a' && c <= 'z')
        b.append(c);
      else if (c >= '0' && c <= '9')
        b.append(c);
      else 
        b.append("%"+Integer.toHexString(c));
    }   
    return b.toString();
  }

  protected List<String> sorted(Set<String> keys) {
    List<String> names = new ArrayList<String>();
    names.addAll(keys);
    Collections.sort(names);
    return names;
  }

  protected void codedTriple(String section, String subject, String predicate, CodeableConcept cc) {
    for (Coding c : cc.getCoding()) {
      String s = getLinkedForm(c);
      if (s != null) 
        triple(section, subject, predicate, s, c.hasDisplay() ? c.getDisplay() : cc.getText());
    }
  }
 
  protected String getLinkedForm(Coding c) {
    if (c.hasSystem()) {
      if (c.getSystem().equals("http://loinc.org")) {
        prefixes.put("loinc", "http://loinc.org/");
        return "loinc:"+c.getCode();
      }
    }
    return null;
  }

  protected void prefix(String code, String url) {
    prefixes.put(code, url);
  }


  private boolean hasSection(String sn) {
    for (Section s : sections)
      if (s.name.equals(sn))
        return true;
    return false;
    
  }
  protected void section(String sn) {
    if (hasSection(sn))
      throw new Error("Duplicate section name "+sn);
    Section s = new Section();
    s.name = sn;
    sections.add(s);
  }

  protected String matches(String url, String prefixUri, String prefix) {
    if (url.startsWith(prefixUri)) {
      prefixes.put(prefix, prefixUri);
      return prefix+":"+escape(url.substring(prefixUri.length()), false);
    }
    return null;
  }

  protected PredicateObject predicateObj(String predicate, TripleObject object) {
    PredicateObject obj = new PredicateObject();
    obj.predicate = predicate;
    predicates.add(predicate);
    obj.object = object;
    return obj;
  }

  protected PredicateObject predicate(String predicate, String object) {
    PredicateObject obj = new PredicateObject();
    obj.predicate = predicate;
    predicates.add(predicate);
    obj.object = new StringObject(object);
    return obj;
  }

  protected PredicateObject predicate(String predicate, String object, String comment) {
    PredicateObject obj = new PredicateObject();
    obj.predicate = predicate;
    predicates.add(predicate);
    obj.object = new StringObject(object);
    obj.comment = comment;
    return obj;
  }

  protected TripleObject complex(PredicateObject predicate) {
    ComplexObject obj = new ComplexObject();
    obj.statements.add(predicate);
    return obj;
  }

  protected TripleObject complex(PredicateObject predicate1, PredicateObject predicate2) {
    ComplexObject obj = new ComplexObject();
    obj.statements.add(predicate1);
    obj.statements.add(predicate2);
    return obj;
  }

  protected TripleObject complex(PredicateObject predicate1, PredicateObject predicate2, PredicateObject predicate3) {
    ComplexObject obj = new ComplexObject();
    obj.statements.add(predicate1);
    obj.statements.add(predicate2);
    obj.statements.add(predicate3);
    return obj;
  }

  protected void comment(String section, String subject, String comment) {
    triple(section, subject, "rdfs:comment", literal(comment), null);
    triple(section, subject, "dc:terms", literal(comment), null);
  }
  
  protected void label(String section, String subject, String comment) {
    triple(section, subject, "rdfs:label", literal(comment), null);
    triple(section, subject, "dc:title", literal(comment), null);
  }
  
  protected void triple(String section, String subject, String predicate, String object) {
    triple(section, subject, predicate, new StringObject(object), null);
  }
  
  protected void triple(String section, String subject, String predicate, TripleObject object) {
    triple(section, subject, predicate, object, null);
  }
  
  protected void triple(String section, String subject, String predicate, String object, String comment) {
    triple(section, subject, predicate, new StringObject(object), comment);
  }
  
  protected void primaryTriple(String section, String subject, String predicate, String object) {
    Section s = sections.get(sections.size()-1); 
    if (s.primary != null)
      throw new Error("multiple primary objects");
    s.primary = triple(section, subject, predicate, new StringObject(object), null);
  }
  
  protected Triple triple(String section, String subject, String predicate, TripleObject object, String comment) {
    if (!hasSection(section))
      throw new Error("use undefined section "+section);
    checkPrefix(subject);
    checkPrefix(predicate);
    checkPrefix(object);
    predicates.add(predicate);
    Triple t = new Triple(section, subject, predicate, object, comment == null ? "" : " # "+comment.replace("\r\n", " ").replace("\r", " ").replace("\n", " "));
    triples.add(t);
    return t;
  }
  
  private void checkPrefix(TripleObject object) {
    if (object instanceof StringObject)
      checkPrefix(((StringObject) object).value);
    else {
      ComplexObject obj = (ComplexObject) object;
      for (PredicateObject po : obj.statements) {
        checkPrefix(po.getPredicate());
        checkPrefix(po.getObject());
      }
    }
    
  }

  protected void checkPrefix(String pname) {
    if (pname.startsWith("("))
      return;
    if (pname.startsWith("\""))
      return;
    if (pname.startsWith("<"))
      return;
    
    if (pname.contains(":")) {
      String prefix = pname.substring(0, pname.indexOf(":"));
      if (!prefixes.containsKey(prefix) && !prefix.equals("http")&& !prefix.equals("urn"))
        throw new Error("undefined prefix "+prefix); 
    }
  }

  protected StringObject literal(String s) {
    return new StringObject("\""+escape(s, true)+"\"");
  }

  protected String escape(String s, boolean string) {
    if (s == null)
      return "";

    StringBuilder b = new StringBuilder();
    for (char c : s.toCharArray()) {
      if (c == '\r')
        b.append("\\r");
      else if (c == '\n')
        b.append("\\n");
      else if (c == '"')
        b.append("\\\"");
      else if (c == '\\')
        b.append("\\\\");
      else if (c == '/' && !string)
        b.append("\\/");
      else 
        b.append(c);
    }   
    return b.toString();
  }

  protected class LineOutputStreamWriter extends OutputStreamWriter {
    private LineOutputStreamWriter(OutputStream out) throws UnsupportedEncodingException {
      super(out, "UTF-8");
    }

    private void ln() throws Exception {
      write("\r\n");
    }

    private void ln(String s) throws Exception {
      write(s);
      write("\r\n");
    }

  }


  protected void commit() throws Exception {
    LineOutputStreamWriter writer = new LineOutputStreamWriter(destination);
    commitPrefixes(writer);
    for (Section s : sections) {
      commitSection(writer, s);
    }
    writer.ln("# -------------------------------------------------------------------------------------");
    writer.ln();
    writer.flush();
    writer.close();
  }
 
  private void commitPrefixes(LineOutputStreamWriter writer) throws Exception {
    writer.ln("# FHIR definitions");
    writer.write("# This is work in progress, and may change rapidly \r\n");
    writer.ln();
    writer.write("# A note about policy: the focus here is providing the knowledge from \r\n"); 
    writer.write("# the FHIR specification as a set of triples for knowledge processing. \r\n");
    writer.write("# Where appopriate, predicates defined external to FHIR are used. \"Where \r\n");
    writer.write("# appropriate\" means that the predicates are a faithful representation \r\n");
    writer.write("# of the FHIR semantics, and do not involve insane (or owful) syntax. \r\n");
    writer.ln();
    writer.write("# Where the community agrees on additional predicate statements (such \r\n");
    writer.write("# as OWL constraints) these are added in addition to the direct FHIR \r\n");
    writer.write("# predicates \r\n");
    writer.ln();
    for (String p : sorted(prefixes.keySet()))
      writer.ln("@prefix "+p+": <"+prefixes.get(p)+"> .");
    writer.ln();
    writer.ln("# Predicates used in this file:");
    for (String s : sorted(predicates)) 
      writer.ln(" # "+s);
    writer.ln();
 }

  private String lastSubject = null;
  private String lastComment = "";

  private void commitSection(LineOutputStreamWriter writer, Section section) throws Exception {
    List<Triple> sectlist = new ArrayList<Triple>();
    for (Triple t : triples)
      if (t.getSection().equals(section.name))
        sectlist.add(t);
    Collections.sort(sectlist);
    writer.ln("# - "+section.name+" "+Utilities.padLeft("", '-', 75-section.name.length()));
    writer.ln();
    
    lastSubject = null;
    lastComment = "";

    // first pass: primary
    for (Triple t : sectlist) {
      if (section.primary != null && t.getSubject().equals(section.primary.getSubject()))
        coomitTriple(writer, t);
    }
    // first pass: secondary
    for (Triple t : sectlist) {
      if (section.primary == null || !t.getSubject().equals(section.primary.getSubject()))
        coomitTriple(writer, t);
    }
    writer.ln("."+lastComment);
    writer.ln();
  }

  private void coomitTriple(LineOutputStreamWriter writer, Triple t) throws Exception, IOException {
    boolean follow = false;
    if (lastSubject != null) {
      follow = lastSubject.equals(t.getSubject());
      String c = follow ? ";" : ".";
      writer.ln(c+lastComment);
      if (!follow) 
        writer.ln();
    }
    String left = follow ? Utilities.padLeft("", ' ', 2) : t.getSubject();
    if (t.getObject() instanceof StringObject)
      writer.write(left+" "+t.getPredicate()+" "+((StringObject) t.getObject()).value);
    else {
      writer.write(left+" "+t.getPredicate()+" [");
      if (((ComplexObject) t.getObject()).write(writer, 4))
        writer.write("\r\n"+left+" ]");
      else
        writer.write("]");
    }
    lastComment = t.getComment();
    lastSubject = t.getSubject();
  }


}


   