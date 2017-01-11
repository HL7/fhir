package org.hl7.fhir.rdf;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.Node_Blank;
import org.apache.jena.graph.Node_Literal;
import org.apache.jena.graph.Node_URI;
import org.apache.jena.graph.Triple;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.hl7.fhir.utilities.TextFile;

public class ModelComparer {

  private Model model1;
  private Model model2;
  private String name1;
  private String name2;
  List<Triple> tl1;
  List<Triple> tl2;

  public ModelComparer setModel1(Model model, String name) throws IOException {
    model1 = model;
    name1 = name;
    tl1 = listAllTriples(model1);
//    log(tl1, "c:\\temp\\triples-"+name+".txt");
    return this;
  }

  private void log(List<Triple> list, String filename) throws IOException {
    StringBuilder b = new StringBuilder();
    for (Triple t : list) {
      b.append(t.getSubject().toString());
      b.append("\t");
      b.append(t.getPredicate().toString());
      b.append("\t");
      b.append(t.getObject().toString());
      b.append("\r\n");
    }
    TextFile.stringToFile(b.toString(), filename);
  }

  public ModelComparer setModel2(Model model, String name) throws IOException {
    model2 = model;
    name2 = name;
    tl2 = listAllTriples(model2);
//    log(tl2, "c:\\temp\\triples-"+name+".txt");
    return this;
  }

  public List<String> compare() {
    Set<String> ep1 = listEntryPoints(tl1);
    Set<String> ep2 = listEntryPoints(tl2);
    List<String> diffs = new ArrayList<String>();
    if (ep1.size() != ep2.size())
      diffs.add("Entry point counts differ");
    if (ep1.size() != 1)
      diffs.add("Entry point count != 1");
    String ep = ep1.iterator().next();
    compare(diffs, ep, ep, ep);
    return diffs;
  }

  
  private void compare(List<String> diffs, String url1, String url2, String statedPath) {
    List<Triple> pl1 = listAllProperties(tl1, url1);
    List<Triple> pl2 = listAllProperties(tl2, url2);
    Set<String> handled = new HashSet<String>();
    for (Triple t : pl1) {
      String pred = t.getPredicate().toString();
      if (!handled.contains(pred)) {
        comparePredicate(diffs, statedPath, pred, pl1, pl2);
      }
    }
  }

  private void comparePredicate(List<String> diffs, String statedPath, String pred, List<Triple> pl1, List<Triple> pl2) {
    List<Triple> ml1 = listMatchingProperties(pl1, pred);
    List<Triple> ml2 = listMatchingProperties(pl2, pred);
    if (ml1.size() != ml2.size()) {
      if (!isExpectedDifference(statedPath, pred, ml1.size(), ml2.size()))
        diffs.add("Difference at "+statedPath+" for "+pred+": "+name1+" has "+Integer.toString(ml1.size())+" values, but "+name2+" has "+Integer.toString(ml2.size())+" values");
    } else if (ml1.size() == 1) {
      Node o1 = ml1.get(0).getObject();
      Node o2 = ml2.get(0).getObject();
      compareObjects(diffs, statedPath, pred, o1, o2);
    } else for (int i = 0; i < ml1.size(); i++) {
      String id = pred+"["+Integer.toString(i)+"]";
      Node o1 = getByIndex(ml1, tl1, i, statedPath, id);
      if (o1 == null)
        diffs.add("Unable to find "+statedPath+" / "+id+" in "+name1);
      else {
        Node o2 = getByIndex(ml2, tl2, i, statedPath, id);
        if (o2 == null)
          diffs.add("Unable to find "+statedPath+" / "+id+" in "+name2);        
        else
          compareObjects(diffs, statedPath, id, o1, o2);
      }
    }
  }

  private void compareObjects(List<String> diffs, String statedPath, String pred, Node o1, Node o2) {
    if (o1.getClass() == Node_Blank.class || o2.getClass() == Node_Blank.class ) {
      // bnodes: follow the nodes
      compare(diffs, o1.toString(), o2.toString(), statedPath+" / "+pred);
    } else if (o1.getClass() == Node_URI.class && o2.getClass() == Node_URI.class) {
      // if either is a url, just compare literal values
      String u1 = o1.toString();
      String u2 = o2.toString();
      if (u1.startsWith("\"") && u1.endsWith("\""))
        u1 = u1.substring(1, u1.length()-1);
      if (u2.startsWith("\"") && u2.endsWith("\""))
        u2 = u2.substring(1, u2.length()-1);
      if (!u1.equals(u2)) 
        diffs.add("Difference at "+statedPath+" for "+pred+": URL objects have different values: "+name1+" = "+u1+", "+name2+" = "+u2+"");
    } else if (o1.getClass() == Node_Literal.class && o2.getClass() == Node_Literal.class) {
      Node_Literal l1 = (Node_Literal) o1;
      Node_Literal l2 = (Node_Literal) o2;
      if (!l1.getLiteralDatatypeURI().equals(l2.getLiteralDatatypeURI())) {
        diffs.add("Difference at "+statedPath+" for "+pred+": Literal objects have different types: "+name1+" = "+l1.getLiteralDatatypeURI()+", "+name2+" = "+l2.getLiteralDatatypeURI()+"");
      } else if (l1.getLiteralValue() instanceof BigDecimal) {
        BigDecimal d1 = (BigDecimal) l1.getLiteralValue();
        BigDecimal d2 = new BigDecimal(l2.getLiteralValue().toString());
        if (d1.compareTo(d2) != 0) 
          diffs.add("Difference at "+statedPath+" for "+pred+": Literal objects have different values: "+name1+" = "+l1.getLiteralLexicalForm()+", "+name2+" = "+l2.getLiteralLexicalForm()+"");
      } else if (!l1.getLiteralLexicalForm().equals(l2.getLiteralLexicalForm())) {
        diffs.add("Difference at "+statedPath+" for "+pred+": Literal objects have different values: "+name1+" = "+l1.getLiteralLexicalForm()+", "+name2+" = "+l2.getLiteralLexicalForm()+"");
      }  
    } else if (o1.getClass() != o2.getClass()) {
      diffs.add("Difference at "+statedPath+" for "+pred+": objects have different types: "+name1+" = "+o1.getClass().getName()+", "+name2+" = "+o2.getClass().getName()+"");
    } else 
      diffs.add("Difference at "+statedPath+" for "+pred+": value comparison not done yet ("+o1.getClass().getName()+" / "+o2.getClass().getName()+")");
  }

  private Node getByIndex(List<Triple> matches, List<Triple> all, int index, String statedPath, String id) {
    for (Triple t : matches) {
      for (Triple s : all) {
        if (s.getSubject().toString().equals(t.getObject().toString()) && 
            s.getPredicate().toString().equals("http://hl7.org/fhir/index") && 
            s.getObject().toString().startsWith("\""+Integer.toString(index)+"\"")) {
          return t.getObject();
        }
      }
    }
    return null;
  }

  private boolean isExpectedDifference(String statedPath, String pred, int c1, int c2) {
//    if (pred.equals("http://hl7.org/fhir/nodeRole") && c1 == 1 && c2 == 0)
//      return true;
//    if (pred.equals("http://hl7.org/fhir/index") && c1 == 1 && c2 == 0)
//      return true;
    return false;
  }

  private List<Triple> listMatchingProperties(List<Triple> list, String pred) {
    List<Triple> props = new ArrayList<Triple>();
    for (Triple t : list) {
      if (t.getPredicate().toString().equals(pred))
        props.add(t);
    }
    return props;
  }

  private List<Triple> listAllProperties(List<Triple> list, String subject) {
    List<Triple> props = new ArrayList<Triple>();
    for (Triple t : list) {
      if (t.getSubject().toString().equals(subject))
        props.add(t);
    }
    return props;
  }

  private Set<String> listEntryPoints(List<Triple> list) {
    Set<String> ep1 = new HashSet<String>();
    for (Triple t : list) {
      boolean found = false;
      for (Triple s : list) {
        if (t.getSubject().toString().equals(s.getObject().toString()))
          found = true;
      }
      if (!found)
        ep1.add(t.getSubject().toString());
    };
    return ep1;
  }

  private List<Triple> listAllTriples(Model m1) {
    List<Triple> tl1 = new ArrayList<Triple>();
    for ( final StmtIterator res = m1.listStatements(); res.hasNext(); ) {
      final Statement r = res.next();
      tl1.add(r.asTriple());
    }
    return tl1;
  }

}
