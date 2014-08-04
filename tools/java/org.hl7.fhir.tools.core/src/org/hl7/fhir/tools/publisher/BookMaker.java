package org.hl7.fhir.tools.publisher;
/*
Copyright (c) 2011-2014, HL7, Inc
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, 
are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this 
   list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
   this list of conditions and the following disclaimer in the documentation 
   and/or other materials provided with the distribution.
 * Neither the name of HL7 nor the names of its contributors may be used to 
   endorse or promote products derived from this software without specific 
   prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.

*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.Binding;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Logger.LogMessageType;
import org.hl7.fhir.utilities.xhtml.NodeType;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlDocument;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;

public class BookMaker {

  private PageProcessor page;
  private String target;
  
  private Map<String, XhtmlDocument> pages = new HashMap<String, XhtmlDocument>();

  private class RefTarget {
    XhtmlNode parent;
    int index;
  }

  
  public BookMaker(PageProcessor page) {
    super();
    this.page = page;
  }

  private void produceBookForm() throws FileNotFoundException, Exception {
    target = page.getFolders().dstDir;
    target = target + File.separator;
    
    checkCrossLinks();
    
    String src = TextFile.fileToString(page.getFolders().srcDir+"book.html");
    src = page.processPageIncludes(page.getFolders().srcDir+"book.html", src, "book", null, null);
    XhtmlDocument doc = new XhtmlParser().parse(src, "html");
    XhtmlNode body = doc.getElement("html").getElement("body");
    addTOC(body);   
    addContent(body);
    addReferenceIds(body);
    new XhtmlComposer().compose(new FileOutputStream(target+"fhir-book.html"), doc); 
  }

  private void checkCrossLinks() {
	  for (String name : pages.keySet()) {
		  if (!"toc.html".equals(name)) {
			  XhtmlDocument d = pages.get(name);
			  checkCrossLinks(name, d);
		  }
	}
  }
  
  private boolean findTarget(XhtmlNode node, String name) {
	if (node == null)
	  return false;
	if (node.getNodeType() == NodeType.Element && node.getName().equals("a") && name.equals(node.getAttribute("name"))) 
	  return true;
	boolean r = false;
	for (XhtmlNode c : node.getChildNodes()) 
	  r = r || findTarget(c, name);
	return r;
  }
  
  private void checkCrossLinks(String name, XhtmlNode node) {
	  if (node.getNodeType() == NodeType.Element && node.getName().equals("a")) {
		  String href = node.getAttribute("href");
		  if (href != null) {
			  if (!pages.containsKey(href)) {
				  boolean found = false;
				  if (href.endsWith(".xsd") || href.endsWith(".xml") || href.endsWith(".xml.html") || href.endsWith(".json") || href.endsWith(".zip"))
					  found = true;
				  else if (pages.containsKey(href))
					  found = true;
				  else if (href.startsWith("http:") || href.startsWith("https:") || href.startsWith("mailto:") || href.startsWith("ftp:"))
					  found = true;
				  else if (href.startsWith("v2/") || href.startsWith("v3/") || href.startsWith("../")) {
				    found = true;
				    node.setAttribute("href", "http://hl7.org/fhir"+href);
				  }
				  if (!found && href.contains("#")) {
					  String parts[] = href.split("#");
					  if (parts == null || parts.length == 0) {
					    parts = new String[] { name };
					  } else if (parts[0].equals(""))
						  parts[0] = name;
					  found = pages.containsKey(parts[0]);
					  if (found && parts[1] != null && !parts[1].equals(""))
					  {
						  found = findTarget(pages.get(parts[0]), parts[1]);
						  if (!found)
							try {
								if (new File("c:\\temp\\source.html").exists())
								  new XhtmlComposer().compose(new FileOutputStream("c:\\temp\\source.html"), pages.get(parts[0]));
							} catch (Exception e) {
								e.printStackTrace();
							}
					  }
				  }
				  if (!found && !new File(page.getFolders().dstDir+href).exists() && !href.equals("qa.html")) {
				    page.getQa().brokenlink("broken link in "+name+": <a href=\""+href+"\">"+node.allText()+"</a>");
					  page.log("broken link in "+name+": <a href=\""+href+"\">"+node.allText()+"</a>", LogMessageType.Warning);
				  }
			  }
		  }
	  } else {
		  if (!(node.getNodeType() == NodeType.Element && "div".equals(node.getName()) && "index-only-no-book".equals(node.getAttribute("class"))))
  		    for (XhtmlNode c : node.getChildNodes()) 
	 		  checkCrossLinks(name, c);
	  }
  }

  private void addReferenceIds(XhtmlNode body) {
    Map<String, RefTarget> tgts = new HashMap<String, RefTarget>();
    List<XhtmlNode> refs = new ArrayList<XhtmlNode>();
    List<XhtmlNode> norefs = new ArrayList<XhtmlNode>();
    buildIndex(refs, norefs, tgts, body, false, false);
    for (XhtmlNode a : norefs) {
    //      updateRef(tgts, a, false);
    }
    for (XhtmlNode a : refs) {
      updateRef(tgts, a, true);
    }    
  }

  private void updateRef(Map<String, RefTarget> tgts, XhtmlNode a, boolean update) {
    if (a.getAttributes().get("href").startsWith("#")) {
      RefTarget r = tgts.get(a.getAttributes().get("href").substring(1));
      if (r != null) {
        if (update) {
          int n = r.index + 1;
          while (n < r.parent.getChildNodes().size() && r.parent.getChildNodes().get(n).getNodeType() != NodeType.Element)
            n++;
          if (n < r.parent.getChildNodes().size()) {
            XhtmlNode h = r.parent.getChildNodes().get(n);
            if (h.getName().startsWith("h")) {
              String s = h.allText();
              if (s.contains(":"))
                a.addText(" (§"+s.substring(0, s.indexOf(':'))+")");
            } 
          }
        }
      }
      else if (page.getDefinitions().getFutureResources().containsKey(a.allText())) {
        a.addText(" (Broken Link: not done yet)");
      } else {
       // page.log("unable to resolve reference to "+a.getAttributes().get("href").substring(1)+" on \""+a.allText()+"\"");
        a.addText(" (Known Broken Link - needs to be resolved)");
      }
    }
  }

  private void buildIndex(List<XhtmlNode> refs, List<XhtmlNode> norefs, Map<String, RefTarget> tgts, XhtmlNode focus, boolean started, boolean noUpdate) {
    int i = 0;
    for (XhtmlNode child : focus.getChildNodes()) {
      if (started) {
        if ("a".equals(child.getName()) && child.getAttributes().containsKey("name")) {
          RefTarget r = new RefTarget();
          r.parent = focus;
          r.index = i;
          String n = child.getAttributes().get("name");
//          System.out.println("        ref: "+n);
          tgts.put(n, r);
        }
        if ("a".equals(child.getName()) && child.getAttributes().containsKey("href")) {
          //System.out.println("found "+child.getAttributes().get("href"));
          if (noUpdate)
            norefs.add(child);
          else
            refs.add(child);
        }

        if (child.getNodeType() == NodeType.Element)
          buildIndex(refs, norefs, tgts, child, true, noUpdate || child.getName().equals("pre"));
      } else if ("hr".equals(child.getName()))
        started = true;
      i++;
    }
    
  }

  private class LevelCounter {
    int l1;
    int l2;
    int l3;
    int l4;
  }
  private void addContent(XhtmlNode body) throws Exception {
    List<String> list = new ArrayList<String>();
    loadResources(list, page.getDefinitions().getResources().keySet());

    XhtmlNode e = body.getElement("contents");
    XhtmlNode div = body.addTag(body.getChildNodes().indexOf(e), "div");
    body.getChildNodes().remove(e);

    List<String> links = new ArrayList<String>();

    LevelCounter lvl = new LevelCounter();
    lvl.l1 = 0;
    for (Navigation.Category s : page.getNavigation().getCategories()) {
      lvl.l1++;
      
//      div.addTag("div").setAttribute("class", "page-break");
      XhtmlNode divS = div.addTag("div");
      divS.attribute("class", "section");
      XhtmlNode h1 = divS.addTag("h1");
      h1.addText(Integer.toString(lvl.l1)+": "+s.getName());
      addPageContent(lvl, divS, s.getLink(), s.getName());
      links.add(s.getLink());

      lvl.l2 = 0;
      for (Navigation.Entry n : s.getEntries()) {
        lvl.l2++;
        lvl.l3 = 0;
        if (n.getLink() != null) {
          if (n.getLink().equals("[codes]")) {
            lvl.l2--;
            List<String> names = new ArrayList<String>();
            for (BindingSpecification bs : page.getDefinitions().getBindings().values()) {
              if (bs.getBinding() == Binding.CodeList) 
                names.add(bs.getReference());
            }
            Collections.sort(names);
            for (String l : names) { 
              addPageContent(lvl, divS, l.substring(1), page.getDefinitions().getBindingByReference(l).getName());
//              links.add(l.substring(1));
            }
          }
          else {
            addPageContent(lvl, divS, n.getLink(), n.getName());
            links.add(n.getLink());
          }
        }
        for (Navigation.Entry g : n.getEntries()) {
          if (g.getLink() != null) {
            addPageContent(lvl, divS, g.getLink(), g.getName());
            links.add(g.getLink());
          }
        }
      }
      if (s.getEntries().size() == 0 && s.getLink().equals("resourcelist")) {
       
        for (String rn : list) {
          if (!links.contains(rn.toLowerCase())) {
            lvl.l2++;
            lvl.l3 = 0;
            ResourceDefn r = page.getDefinitions().getResourceByName(rn);
            addPageContent(lvl, divS, rn.toLowerCase(), r.getName());
          }
        }
      }
      if (s.getLink().equals("page") && s.getName().equals("Examples")) {
       
        for (String rn : list) {
          lvl.l2++;
          lvl.l3 = 0;
            ResourceDefn r = page.getDefinitions().getResourceByName(rn);
            addPageContent(lvl, divS, rn.toLowerCase()+"Ex", r.getName());
        }
      }
      if (s.getLink().equals("page") && s.getName().equals("Formal Definitions")) {
       
        for (String rn : list) {
          lvl.l2++;
          lvl.l3 = 0;
            ResourceDefn r = page.getDefinitions().getResourceByName(rn);
            addPageContent(lvl, divS, rn.toLowerCase()+"Defn", r.getName());
        }
      }
    }
  }

  private void loadResources(List<String> list, Set<String> keySet) throws Exception {
    list.addAll(page.getDefinitions().getResources().keySet());
    Collections.sort(list);
    //    list.add("Profile");
    //    list.add("MessageHeader");
    //    list.add("DocumentHeader");
    //    list.add("ValueSet");
    //    list.add("Conformance");
    //    list.add("Agent");
    //    list.add("Animal");
    //    list.add("AssessmentScale");
    //    list.add("LabReport"); 
    //    list.add("Organization");
    //    list.add("Patient");
    //    list.add("Person");
    //    list.add("Prescription");
    //    if (list.size() != keySet.size())
    //      throw new Exception("Please consult Grahame");    
  }

  private void addPageContent(LevelCounter lvl, XhtmlNode divS, String link, String name) throws Exception, Error {
    XhtmlNode divT = divS.addTag("div");
    XhtmlNode a = divT.addTag("a");
    a.attribute("name", link);
    a.addText(" "); // work around for a browser bug

    boolean first = true;
    XhtmlDocument page = pages.get(link+".html");
    if (page == null)
      throw new Exception("No content found for "+link+".html");
    if (page.getElement("html") == null)
      throw new Exception("No 'html' tag found in "+link+".html");
    if (page.getElement("html").getElement("body") == null)
      throw new Exception("No 'body' tag found in "+link+".html");
    XhtmlNode pageBody = page.getElement("html").getElement("body");
    
    List<XhtmlNode> wantDelete = new ArrayList<XhtmlNode>();
    for (XhtmlNode child : pageBody.getChildNodes()) {
      if ("index-only-no-book".equals(child.getAttribute("class"))) 
        wantDelete.add(child);
    }    
    for (XhtmlNode c : wantDelete) 
      pageBody.getChildNodes().remove(c);
    
    for (XhtmlNode child : pageBody.getChildNodes()) {
      if (child.getNodeType() == NodeType.Element) {
        fixReferences(pageBody, child, link);
      }
      
      if ("h1".equals(child.getName())) {
        if (!first)
          throw new Error("?? ("+link+".h1 repeated) ");
        first = false;
        child.setName("h2");
        child.addText(0, Integer.toString(lvl.l1)+"."+Integer.toString(lvl.l2)+": ");

      } else if ("h2".equals(child.getName())) {
        lvl.l3++;
        lvl.l4 = 0;
        child.setName("h3");
        child.addText(0, Integer.toString(lvl.l1)+"."+Integer.toString(lvl.l2)+"."+Integer.toString(lvl.l3)+": ");
      } else if ("h3".equals(child.getName())) {
        lvl.l4++;
        child.setName("h4");
        child.addText(0, Integer.toString(lvl.l1)+"."+Integer.toString(lvl.l2)+"."+Integer.toString(lvl.l3)+"."+Integer.toString(lvl.l4)+": ");
      } else if ("h4".equals(child.getName())) {
        child.setName("h5");
      }
    }
//        if (i2 != 1)
//          divT.addTag("div").setAttribute("class", "page-break");
    divT.getChildNodes().addAll(pageBody.getChildNodes());
  }

  
  private void fixReferences(XhtmlNode parent, XhtmlNode node, String name) throws Exception {

    List<XhtmlNode> wantDelete = new ArrayList<XhtmlNode>();
    
    for (XhtmlNode child : node.getChildNodes()) {
      if (child.getNodeType() == NodeType.Element) {
        if ("index-only-no-book".equals(child.getAttribute("class"))) 
          wantDelete.add(child);
        else
          fixReferences(node, child, name);
      }    
    }
    for (XhtmlNode c : wantDelete) 
      node.getChildNodes().remove(c);
    
    if (node.getName().equals("a")) {      
      if (node.getAttributes().containsKey("name")) {
        String lname = node.getAttributes().get("name");
        node.getAttributes().put("name", name+"."+lname);
        //System.out.println("found anchor "+name+"."+lname);
      } else if (node.getAttribute("href") != null || node.getAttributes().get("xlink:href") != null) { 
        String s = node.getAttributes().get("href");
        if (s == null || s.length() == 0)
          s = node.getAttributes().get("xlink:href");
        if (s == null || s.length() == 0)
          throw new Error("empty \"href\" element in \"a\" tag around "+parent.allText());
        if (s.startsWith("#")) {
          s = "#"+name+"."+s.substring(1);
        } else if (s.startsWith("http:") || s.startsWith("https:") || s.startsWith("ftp:") || s.startsWith("mailto:")) {
          //s = s;
        } else {
          int i = s.indexOf('.');
          if (i == -1)
            throw new Error("unable to understand ref: '"+s+"' on '"+node.allText()+"'");

          if (s.contains("#")) {
            int j = s.indexOf('#');
            s = "#"+s.substring(0, i)+"."+s.substring(j+1);
          } else if (s.endsWith(".html")) {
            s = "#"+s.substring(0, i);
          } else {
            if (!s.endsWith(".zip") && !s.endsWith(".xsd") && !s.endsWith(".xml") && !s.endsWith(".json") && !s.endsWith(".png") && !s.endsWith(".xml") && !s.endsWith(".eap") && !s.endsWith(".xmi")) {
              System.out.println("odd ref: "+s+" in "+node.allText());
              //s = s;
            } else {
              // actually, what we want to do is do what?
//              System.out.println("ref to remove: "+s+" in "+node.allText());
//              Utilities.copyFile(new File(page.getFolders().dstDir+s), new File(targetBin+File.separatorChar+s));
//              s = "http://hl7.org/documentcenter/public/standards/FHIR"+DSTU_PATH_PORTION+"/v"+page.getVersion()+"/"+s;
            }
          }

        }
        node.getAttributes().put("href", s);
        if (s.startsWith("http") && parent != null && !node.allText().equals(s)) {
          node.addText(" ("+s+") ");
        }
        //System.out.println("reference to "+s); 
      }
    }
  }

  private void addTOC(XhtmlNode body) throws Exception {
    XhtmlNode e = body.getElement("index");
    XhtmlNode div = body.addTag(body.getChildNodes().indexOf(e), "div");
    body.getChildNodes().remove(e);

    div.attribute("class", "toc");
    div.addText("\r\n  ");
    XhtmlNode p = div.addTag("p");

    page.getBreadCrumbManager().makeToc(p);
  }
  /*
  private void addTOCOld(XhtmlNode body) throws Exception {
    List<String> list = new ArrayList<String>();
    
    loadResources(list, page.getDefinitions().getResources().keySet());

    List<String> links = new ArrayList<String>();
    XhtmlNode e = body.getElement("index");
    XhtmlNode div = body.addTag(body.getChildNodes().indexOf(e), "div");
    body.getChildNodes().remove(e);
    div.attribute("class", "toc");
    div.addText("\r\n  ");
    int i1 = 0;
    XhtmlNode p = div.addTag("p");
    p.addText("\r\n    ");
    for (Navigation.Category c : page.getNavigation().getCategories()) {
      i1++;
      p.addText(Integer.toString(i1)+": ");
      XhtmlNode a = p.addTag("a");
      a.attribute("href", "#"+c.getLink());
      a.addText(c.getName());
      p.addTag("br");
      p.addText("\r\n      ");
      int i2 = 0;
      for (Navigation.Entry n : c.getEntries()) {
        if (n.getLink() != null) {
          if (!n.getLink().equals("[codes]")) {
            i2++;
            p.addText(XhtmlNode.NBSP+XhtmlNode.NBSP+Integer.toString(i1)+"."+Integer.toString(i2)+": ");
            links.add(n.getLink());

            a = p.addTag("a");
            a.attribute("href", "#"+n.getLink());
            a.addText(n.getName());
            p.addTag("br");
            p.addText("\r\n     ");
          }
        }
      }
      if (c.getEntries().size() ==0 && c.getLink().equals("resources")) {
        
        for (String rn : list) {
          if (!links.contains(rn.toLowerCase())) {
            i2++;
            ResourceDefn r = page.getDefinitions().getResourceByName(rn);
            p.addText(XhtmlNode.NBSP+XhtmlNode.NBSP+Integer.toString(i1)+"."+Integer.toString(i2)+": ");
            a = p.addTag("a");
            a.attribute("href", "#"+rn.toLowerCase());
            a.addText(r.getName());
            p.addTag("br");
            p.addText("\r\n     ");
          }
        }
      }
      if (c.getLink().equals("page") && c.getName().equals("Examples")) {
        
        for (String rn : list) {
            i2++;
            ResourceDefn r = page.getDefinitions().getResourceByName(rn);
            p.addText(XhtmlNode.NBSP+XhtmlNode.NBSP+Integer.toString(i1)+"."+Integer.toString(i2)+": ");
            a = p.addTag("a");
            a.attribute("href", "#"+rn.toLowerCase()+"Ex");
            a.addText(r.getName());
            p.addTag("br");
            p.addText("\r\n     ");
        }
      }
      if (c.getLink().equals("page") && c.getName().equals("Formal Definitions")) {
        
        for (String rn : list) {
          i2++;
            ResourceDefn r = page.getDefinitions().getResourceByName(rn);
            p.addText(XhtmlNode.NBSP+XhtmlNode.NBSP+Integer.toString(i1)+"."+Integer.toString(i2)+": ");
            a = p.addTag("a");
            a.attribute("href", "#"+rn.toLowerCase()+"Defn");
            a.addText(r.getName());
            p.addTag("br");
            p.addText("\r\n     ");
        }
      }
      
    }
    div.addText("\r\n  ");
  }
*/
  
  public void produce() throws FileNotFoundException, Exception {
    produceBookForm(); 
    
  }

  public Map<String, XhtmlDocument> getPages() {
    return pages;
  }


  
}
