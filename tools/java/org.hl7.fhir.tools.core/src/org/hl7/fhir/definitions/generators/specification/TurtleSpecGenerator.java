package org.hl7.fhir.definitions.generators.specification;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.igtools.spreadsheets.TypeParser;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.Utilities;

public class TurtleSpecGenerator extends OutputStreamWriter {

	private String defPage;
	private String dtRoot;
	private Definitions definitions;
  private PageProcessor page;
  private String prefix; 

	public TurtleSpecGenerator(OutputStream out, String defPage, String dtRoot, PageProcessor page, String prefix) throws UnsupportedEncodingException {
		super(out, "UTF-8");
		this.defPage = defPage;
		this.dtRoot = dtRoot == null ? "" : dtRoot;
		this.definitions = page.getDefinitions();
		this.page = page;
		this.prefix = prefix;
	}

  protected String getBindingLink(ElementDefn e) throws Exception {
    BindingSpecification bs = e.getBinding();
    if (bs == null)
      return "terminologies.html#unbound";
    if (bs.getValueSet() != null) 
      return bs.getValueSet().getUserString("path");
    else if (!Utilities.noString(bs.getReference()))
      return bs.getReference();      
    else 
      return "terminologies.html#unbound";
  }

	public void generate(ElementDefn root, boolean isAbstract) throws Exception {
		write("<pre class=\"spec\">\r\n");

		generateInner(root, definitions.hasResource(root.getName()), isAbstract);

		write("</pre>\r\n");
		flush();
		close();
	}

  public void generateExtension(StructureDefinition ed) throws Exception {
    write("<pre class=\"spec\">\r\n");

    write("  Not done yet\r\n");

    write("</pre>\r\n");
    flush();
    close();
  }

  private void generateInner(ElementDefn root, boolean resource, boolean isAbstract) throws IOException, Exception {
		String rn;
		if (root.getName().equals("Extension")) 
		  rn = "extension|modifierExtension";
		else if (root.getName().equals("Meta")) 
      rn = "meta";
		else if (root.getTypes().size() > 0 && (root.getTypes().get(0).getName().equals("Type")
				|| (root.getTypes().get(0).getName().equals("Structure"))) || isAbstract)
			rn = "[name]";
		else 
			rn = root.getName();

    write("@prefix fhir: &lt;http://hl7.org/fhir/&gt; .");
    if (resource) 
      write("<span style=\"float: right\"><a title=\"Documentation for this format\" href=\""+prefix+"rdf.html\"><img src=\""+prefix+"help.png\" alt=\"doco\"/></a></span>\r\n");
    write("\r\n");
    write("\r\n");
    if (resource) {
      write("[ a fhir:");
      if (defPage == null)
        write("<span title=\"" + Utilities.escapeXml(root.getDefinition())
        + "\"><b>");
      else
        write("<a href=\"" + (defPage + "#" + root.getName()) + "\" title=\""
            + Utilities.escapeXml(root.getDefinition())
            + "\" class=\"dict\"><b>");
      write(rn);
      if ((defPage == null))
        write("</b></span>;");
      else
        write("</b></a>;");
      write("\r\n  fhir:nodeRole fhir:treeRoot; # if this is the parser root\r\n");
    } else
      write("[");
		write("\r\n");
    if (rn.equals(root.getName()) && resource) {
      if (!Utilities.noString(root.typeCode())) {
        write("  # from <a href=\""+prefix+"resource.html\">Resource</a>: <a href=\""+prefix+"resource.html#id\">.id</a>, <a href=\""+prefix+"resource.html#meta\">.meta</a>, <a href=\""+prefix+"resource.html#implicitRules\">.implicitRules</a>, and <a href=\""+prefix+"resource.html#language\">.language</a>\r\n");
        if (root.typeCode().equals("DomainResource"))
          write("  # from <a href=\""+prefix+"domainresource.html\">DomainResource</a>: <a href=\""+prefix+"narrative.html#Narrative\">.text</a>, <a href=\""+prefix+"references.html#contained\">.contained</a>, <a href=\""+prefix+"extensibility.html\">.extension</a>, and <a href=\""+prefix+"extensibility.html#modifierExtension\">.modifierExtension</a>\r\n");
      }
    } else {
      write(" # from Element: <a href=\""+prefix+"extensibility.html\">Element.extension</a>\r\n");
    }
		for (ElementDefn elem : root.getElements()) {
		  generateCoreElem(elem, 1, root.getName(), rn.equals(root.getName()) && resource);
		}

    write("]\r\n");
	}


	private void generateCoreElem(ElementDefn elem, int indent,	String path, boolean backbone) throws Exception {
	  String left = Utilities.padLeft("", ' ', indent*2);
		
		if (isChoice(elem)) {
      write(left+"# ");
      String en = elem.getName();
      writeElementName(elem, path, en);
      write(": ");
      write(elem.describeCardinality());
      write(" <span style=\"color: navy\">");
      write(Utilities.escapeXml(elem.getShortDefn()));
      write("</span>");
      List<TypeRef> tl = getTypes(elem);
      write(". One of these ");
      write(Integer.toString(tl.size()));
      write("\r\n");
      for (TypeRef t : tl) {
        generateElementType(elem, path, left+"  ", t, elem.getName().replace("[x]", Utilities.capitalize(t.getName())));
        write("\r\n");
      }
		} else if (elem.getTypes().size() == 1){
		  TypeRef t = elem.getTypes().get(0);
      String en = elem.getName();
		  generateElementType(elem, path, left, t, en);
	    if (elem.getMaxCardinality() > 1)
	      write(", ... ; # ");
	    else
	      write("; # ");
	    write(elem.describeCardinality());
      write(" <span style=\"color: navy\">");
      write(Utilities.escapeXml(elem.getShortDefn()));
      write("</span>\r\n");
		} else { // children elements
      write(left+"fhir:");
      String en = elem.getName();
      writeElementName(elem, path, en);

      write("[ # ");
      write(elem.describeCardinality());
      write(" <span style=\"color: navy\">");
      write(Utilities.escapeXml(elem.getShortDefn()));
      write("</span>\r\n");
      for (ElementDefn child : elem.getElements()) {
        generateCoreElem(child, indent+1, path+"."+en, backbone);
      }
      
      if (elem.getMaxCardinality() > 1)
        write(left+"], ...;\r\n");
      else
        write(left+"];\r\n"); 
		}
	}

  private List<TypeRef> getTypes(ElementDefn elem) {
    if (elem.getTypes().size() == 1 && elem.getTypes().get(0).isWildcardType()) {
      List<TypeRef> res = new ArrayList<TypeRef>();
      for (String t : TypeParser.wildcardTypes()) {
        TypeRef tr = new TypeRef();
        tr.setName(t);
        res.add(tr);
      }
      return res;
    } else
      return elem.getTypes();
  }

  private void writeElementName(ElementDefn elem, String path, String en) throws IOException {
    if (defPage == null) {
      if (elem.isModifier() || elem.getMustSupport())
        write("<span style=\"text-decoration: underline\" title=\"" + Utilities.escapeXml(elem.getEnhancedDefinition()) + "\">");
      else
        write("<span title=\"" + Utilities.escapeXml(elem.getDefinition()) + "\">");
    } else if (elem.isModifier() || elem.getMustSupport()) 
      write("<a href=\"" + (defPage + "#" + path + "." + en)+ "\" title=\"" + Utilities .escapeXml(elem.getEnhancedDefinition()) 
      + "\" class=\"dict\"><span style=\"text-decoration: underline\">");
    else
      write("<a href=\"" + (defPage + "#" + path + "." + en) + "\" title=\"" + Utilities.escapeXml(elem.getDefinition()) + "\" class=\"dict\">");
    write(path+"."+en);
    if (defPage == null) 
      write("</span>");
    else if (elem.isModifier() || elem.getMustSupport())
      write("</span></a>");
    else
      write("</a>");
    write("<a name=\"ttl-"+en+"\"> </a>");
  }

  private void generateElementType(ElementDefn elem, String path, String left, TypeRef t, String en) throws IOException {
    write(left+"fhir:");
    writeElementName(elem, path, en);
    write("[ ");
    renderType(0, 0, t);
    write(" ]");
  }

  private boolean isChoice(ElementDefn elem) {
    return elem.getTypes().size() > 1 || elem.getName().endsWith("[x]") || elem.typeCode().equals("*");
  }


  private int renderType(int indent, int w, TypeRef t) throws IOException {
    if (t.isXhtml())
      write("fhir:value \"[escaped xhtml]\"^^xsd:string");
    else if (t.getName().startsWith("@"))
      write("<a href=\"#ttl-"+t.getName().substring(1)+"\"><span style=\"color: DarkViolet\">See "+t.getName().substring(1)+"</span></a>");     
    else if (definitions.getConstraints().containsKey(t.getName())) {
      ProfiledType pt = definitions.getConstraints().get(t.getName());
      write("<a href=\"" + (dtRoot + definitions.getSrcFile(pt.getBaseType())
      + ".html#" + pt.getBaseType() + "\">" + pt.getBaseType())+"</a>");
      w = w + pt.getBaseType().length()+2; 
      write("(<a style=\"color:navy\" href=\"" + (dtRoot + definitions.getSrcFile(t.getName())
      + ".html#" + t.getName() + "\">" + t.getName())
      + "</a>)");
    } else
      write("<a href=\"" + (dtRoot + definitions.getSrcFile(t.getName())
          + ".html#" + t.getName() + "\">" + t.getName())
          + "</a>");
    if (t.hasParams()) {
      write("(");
      boolean firstp = true;
      for (String p : t.getParams()) {
        if (!firstp) {
          write("|");
          w++;
        }

        // again, p.length() could be wrong if this is an extension, but then it won't wrap
        if (w + p.length() > 80) {
          write("\r\n  ");
          for (int j = 0; j < indent; j++)
            write(" ");
          w = indent+2;
        }
        w = w + p.length(); 
        
        // TODO: Display action and/or profile information
    		if (p.equals("Any")) {
          write("<a href=\"" +prefix+"resourcelist.html" + "\">" + p + "</a>");								
        }
        else if (t.getName().equals("Reference") && t.getParams().size() == 1 && !Utilities.noString(t.getProfile()))
          write("<a href=\""+prefix+t.getProfile()+"\"><span style=\"color: DarkViolet\">@"+t.getProfile().substring(1)+"</span></a>");     
        else
          write("<a href=\"" + (dtRoot + definitions.getSrcFile(p)
              + ".html#" + p) + "\">" + p + "</a>");

        firstp = false;
      }
      write(")");
      w++;
    }
    return w;
  }


}
