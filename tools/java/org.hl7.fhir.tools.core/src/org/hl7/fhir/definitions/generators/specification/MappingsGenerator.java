/*
Copyright (c) 2011+, HL7, Inc
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
package org.hl7.fhir.definitions.generators.specification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.ResourceDefn.StringPair;
import org.hl7.fhir.igtools.spreadsheets.MappingSpace;
import org.hl7.fhir.r5.model.ElementDefinition;
import org.hl7.fhir.r5.model.ElementDefinition.ElementDefinitionMappingComponent;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.r5.model.StructureDefinition.StructureDefinitionMappingComponent;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

public class MappingsGenerator {

	public class Sorter implements Comparator<String> {

	  private int v(String s){
	    return definitions.getMapTypes().get(s).getSortOrder();
	  }

	  @Override
    public int compare(String o1, String o2) {
      return v(o1) - v(o2);
    }

  }

  private static final int ROOT_ONLY = 1;
  private static final int ALL = 2;
  private static final int CHILDREN_ONLY = 3;

  String mappings;
	String mappingsList;
	private Definitions definitions;
	
	
	public MappingsGenerator(Definitions definitions) {
    super();
    this.definitions = definitions;
  }


  public void generate(StructureDefinition profile) throws IOException {
    if (profile.getMapping().isEmpty())
      mappings = "<p>No Mappings</p>";
    else {
      StringBuilder s = new StringBuilder();
      for (StructureDefinitionMappingComponent map : profile.getMapping()) {

        s.append("<a name=\""+map.getIdentity() +"\"> </a><h3>"+map.getName()+" ("+map.getUri()+")</h3>");
        if (map.hasComment())
          s.append("<p>"+Utilities.escapeXml(map.getComment())+"</p>");
        else if (definitions.getMapTypes().containsKey(map.getUri())) {   
          XhtmlNode pre = definitions.getMapTypes().get(map.getUri()).getPreamble();
          if (pre != null)
            s.append(new XhtmlComposer(XhtmlComposer.HTML).compose(pre));
        }

        s.append("<table class=\"grid\">\r\n");
        
        s.append(" <tr><td colspan=\"3\"><b>"+Utilities.escapeXml(profile.getName())+"</b></td></tr>\r\n");
        String path = null;
        for (ElementDefinition e : profile.getSnapshot().getElement()) {
          if (path == null || !e.getPath().startsWith(path)) {
            path = null;
            if (e.hasMax() && e.getMax().equals("0")) {
              path = e.getPath()+".";
            } else
              genElement(s, e, map.getIdentity());
          }
        }
        s.append("</table>\r\n");
      }
      mappings = s.toString();
    }
  }
  
  public void generateExtension(StructureDefinition ed) throws IOException {
    if (ed.getMapping().isEmpty())
      mappings = "<p>No Mappings</p>";
    else {
      StringBuilder s = new StringBuilder();
      for (StructureDefinitionMappingComponent map : ed.getMapping()) {

        s.append("<a name=\""+map.getIdentity() +"\"> </a><h3>"+map.getName()+" ("+map.getUri()+")</h3>");
        if (map.hasComment())
          s.append("<p>"+Utilities.escapeXml(map.getComment())+"</p>");
        else if (definitions.getMapTypes().containsKey(map.getUri())) {  
          XhtmlNode pre = definitions.getMapTypes().get(map.getUri()).getPreamble();
          if (pre != null)
            s.append(new XhtmlComposer(XhtmlComposer.HTML).compose(pre));
        }

        s.append("<table class=\"grid\">\r\n");

        s.append(" <tr><td colspan=\"3\"><b>"+Utilities.escapeXml(ed.getName())+"</b></td></tr>\r\n");
        String path = null;
        for (ElementDefinition e : ed.getSnapshot().getElement()) {
          if (path == null || !e.getPath().startsWith(path)) {
            path = null;
            if (e.hasMax() && e.getMax().equals("0")) {
              path = e.getPath()+".";
            } else
              genElement(s, e, map.getIdentity());
          }
        }
        s.append("</table>\r\n");
      }
      mappings = s.toString();
    }
  }
  
  
  private void genElement(StringBuilder s, ElementDefinition e, String id) {
      s.append(" <tr><td>");
      boolean root = true;
      for (char c : e.getPath().toCharArray()) 
        if (c == '.') {
          s.append("&nbsp;");
          s.append("&nbsp;");
          s.append("&nbsp;");
          root = false;
        }
      if (root)
        s.append(e.getPath());
      else
        s.append(tail(e.getPath()));
      s.append("</td><td>"+Utilities.escapeXml(e.getSliceName())+"</td>");
      ElementDefinitionMappingComponent m = getMap(e, id);
      if (m == null)
        s.append("<td></td>");
      else
        s.append("<td>"+Utilities.escapeXml(m.getMap())+"</td>");
      s.append(" </tr>\r\n");
  }


  private ElementDefinitionMappingComponent getMap(ElementDefinition e, String id) {
    for (ElementDefinitionMappingComponent m : e.getMapping()) {
      if (m.getIdentity().equals(id))
        return m;
    }
    return null;
  }


  private String tail(String path) {
    return path.substring(path.lastIndexOf('.')+1);
  }


  public void generate(ResourceDefn resource) throws IOException {
		StringBuilder s = new StringBuilder();
		List<String> maps = new ArrayList<String>();
		listKnownMappings(resource.getRoot(), maps);
		Collections.sort(maps, new Sorter());
		StringBuilder list = new StringBuilder();
		for (String m : maps) {
			MappingSpace ms = definitions.getMapTypes().get(m);
      list.append("|"+ms.getTitle() + "#"+ms.getId());

			s.append("<a name=\""+m+"\"> </a><a name=\""+ms.getId()+"\"> </a>");

			if (!Utilities.noString(ms.getLink()))
		    s.append("<h3>"+ms.getTitle()+" (<a href=\""+ms.getLink()+"\">"+m+"</a>)</h3>");
			else
			  s.append("<h3>"+ms.getTitle()+" ("+m+")</h3>");
			
			XhtmlNode pre = ms.getPreamble();
			if (pre != null)
			  s.append(new XhtmlComposer(XhtmlComposer.HTML).compose(pre));
			s.append("<table class=\"grid\">\r\n");
      genElement(s, 0, resource.getRoot(), m, ROOT_ONLY, true, ms.isSparse());
			genInherited(s, resource, m);
			genElement(s, 0, resource.getRoot(), m, CHILDREN_ONLY, true, ms.isSparse());
			s.append("</table>\r\n");
		}
	  mappings = s.toString();
	  mappingsList = list.length() == 0 ? "" : list.toString().substring(1);
	}

  private void genInherited(StringBuilder s, ResourceDefn resource, String m) {
    for (StringPair t : resource.getMappings(m)) {
      s.append(" <tr><td><i>");
      s.append("&nbsp;");
      s.append("&nbsp;(");
      s.append(t.name.contains(".") ? t.name.substring(t.name.indexOf(".")+1) : t.name);
      s.append(")</i></td><td>"+Utilities.escapeXml(t.value).replace("\n", "<br/>\n")+"</td></tr>\r\n");
    }
  }

  public void generate(List<ElementDefn> elements) {
		StringBuilder s = new StringBuilder();
		List<String> maps = new ArrayList<String>();
		for (ElementDefn e : elements)
			listKnownMappings(e, maps);
		Collections.sort(maps);

		StringBuilder list = new StringBuilder();
		for (String m : maps) {
			list.append("|"+definitions.getMapTypes().get(m).getTitle() + "#"+m);
      s.append("<a name=\""+m+"\"> </a>\r\n");
      s.append("<a name=\""+definitions.getMapTypes().get(m).getId()+"\"> </a>\r\n");
			s.append("<h3>"+definitions.getMapTypes().get(m).getTitle()+" ("+m+")</h3>\r\n");
			s.append("<table class=\"grid\">\r\n");
			for (ElementDefn e : elements) 
				if (elementHasMapping(e, m)) {
				  genElement(s, 0, e, m, ALL, true, definitions.getMapTypes().get(m).isSparse());
				}
			s.append("</table>\r\n");
		}
	  mappings = s.toString();
	  if (list.toString().length() > 1)
	    mappingsList = list.toString().substring(1);
	  else
      mappingsList = list.toString();
	}
	


	private boolean elementHasMapping(ElementDefn e, String m) {
		if (e.getMappings().containsKey(m))
			return true;
		for (ElementDefn child : e.getElements())
			if (elementHasMapping(child, m))
				return true;
			
		return false;
	}

	private void genElement(StringBuilder s, int indent, ElementDefn elem, String m, int children, boolean isRoot, boolean sparse) {
	  if ((children == ROOT_ONLY || children == ALL)) {
	    if (isRoot || !sparse || hasMapping(elem, m)) {
	      s.append(" <tr><td>");
	      if (indent == 0) {
	        s.append("<a name=\""+elem.getName()+"\"> </a>");
	        s.append("<a name=\""+elem.getName().toLowerCase()+"\"> </a>");
	      }
	      for (int i = 0; i < indent; i++) {
	        s.append("&nbsp;");
	        s.append("&nbsp;");
	        s.append("&nbsp;");
	        s.append("&nbsp;");
	      }
	      if (indent == 0) 
	        s.append("<b>"+elem.getName()+"</b>");
	      else
	        s.append(elem.getName());
	      s.append("</td><td>"+Utilities.escapeXml(elem.getMappings().get(m)).replace("\n", "<br/>\n")+"</td></tr>\r\n");
	    }
	  }
	  if (!isRoot || !"N/A".equalsIgnoreCase(elem.getMappings().get(m))) {	    
	    if (children == CHILDREN_ONLY || children == ALL) {
	      for (ElementDefn child : elem.getElements()) {
	        genElement(s, indent+1, child, m, ALL, false, sparse);
	      }
	    }
	  }
	}

	private boolean hasMapping(ElementDefn elem, String m) {
    return !Utilities.noString(elem.getMappings().get(m));
  }


  private void listKnownMappings(ElementDefn e, List<String> maps) {
		for (String s : e.getMappings().keySet())
			if (!maps.contains(s) && definitions.getMapTypes().get(s).isPublish())
				maps.add(s);
		for (ElementDefn c : e.getElements())
			listKnownMappings(c,  maps);		
	}

  private void listKnownMappings(StructureDefinition profile, List<String> maps) {
    for (StructureDefinitionMappingComponent map : profile.getMapping())
      if (!maps.contains(map.getIdentity()) && definitions.getMapTypes().get(map.getIdentity()).isPublish())
        maps.add(map.getIdentity());
  }

	public String getMappings() {
		return mappings;
	}

	public String getMappingsList() {
		return mappingsList;
	}

}
