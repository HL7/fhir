package org.hl7.fhir.definitions.generators.specification;
/*
Copyright (c) 2011-2013, HL7, Inc
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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.Binding;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ExtensionDefn;
import org.hl7.fhir.definitions.model.ProfileDefn;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.Utilities;

public class TerminologyNotesGenerator extends OutputStreamWriter {

  private PageProcessor page;
  
	public class CDUsage {
		public CDUsage(String path, ElementDefn element) {
			this.path = path;
			this.element = element;
		}
		private String path;
		private ElementDefn element;
	}

	public class MyCompare implements Comparator<BindingSpecification> {

		@Override
		public int compare(BindingSpecification arg0, BindingSpecification arg1) {
			return txusages.get(arg0).get(0).path.compareTo(txusages.get(arg1).get(0).path);
		}

	}

	char c = 'A';
	private Map<BindingSpecification, List<CDUsage>> txusages = new HashMap<BindingSpecification, List<CDUsage>>(); 
	
	public TerminologyNotesGenerator(OutputStream out, PageProcessor page) throws UnsupportedEncodingException {
		super(out, "UTF-8");
		this.page = page;
	}

	public void generate(ElementDefn root, Map<String, BindingSpecification> tx) throws Exception
	{
		scan(root, root.getName(), tx);
		gen(txusages);
		flush();
		close();
	}

  public void generate(ProfileDefn profile, Map<String, BindingSpecification> tx) throws Exception
  {
//    write("<p>\r\nDefined Bindings\r\n</p>\r\n<ul>\r\n");
//    for (BindingSpecification b : profile.getBindings()) {
//      genBinding(b, "", false);
//    }
//    write("</ul>\r\n");
    scan(profile, tx);
    gen(txusages);
    flush();
    close();
  }

	
	private void scan(ProfileDefn profile, Map<String, BindingSpecification> tx) throws Exception {
    for (ElementDefn d : profile.getElements()) {
      scan(d, profile.getMetadata().get("id")+"."+d.getName(), tx);
    }
    for (ExtensionDefn ex : profile.getExtensions()) {
      if (ex.getDefinition().hasBinding()) {
        BindingSpecification cd = getConceptDomainByName(tx, ex.getDefinition().getBindingName());
        if (!txusages.containsKey(cd)) {
          txusages.put(cd, new ArrayList<CDUsage>());
          c++;
          txusages.get(cd).add(new CDUsage(String.valueOf(c), null));           
        }
        txusages.get(cd).add(new CDUsage(profile.getMetadata().get("id")+".extensions."+ex.getCode(), ex.getDefinition()));     
      }
    }
    
  }

  private void gen(Map<BindingSpecification, List<CDUsage>> txusages2) throws Exception {
		List<BindingSpecification> cds = new ArrayList<BindingSpecification>();
		cds.addAll(txusages.keySet());
		if (cds.size() == 0)
			return;
		
		Collections.sort(cds, new MyCompare());
		write("<h3>\r\nTerminology Bindings\r\n</h3>\r\n");
		// 1. new form
    write("<table class=\"grid\">\r\n");
    write(" <tr><th>Path</th><th>Definition</th><th>Type</th><th>Reference</th></tr>\r\n");
    for (BindingSpecification cd : cds) {
      String path;
      List<CDUsage> list = txusages.get(cd);
      for (int i = 2; i < list.size(); i++) {
        if (!list.get(i).element.typeCode().equals(list.get(1).element.typeCode()))
          throw new Exception("Mixed types on one concept domain in one type - not yet supported by the build process for binding "+cd.getName());
      }
      write(" <tr><td valign=\"top\" title=\""+cd.getName()+"\">");
      boolean first = true;
      for (int i = 1; i < list.size(); i++) {
        if (!first)
          write("<br/>");
        first = false;
        write(list.get(i).path);          
      }
      write(" </td>");
      write("<td valign=\"top\">"+Utilities.escapeXml(cd.getDefinition())+"</td>");
      if (cd.getBinding() == Binding.Unbound)
        write("<td>Unknown</td><td valign=\"top\">No details provided yet</td>");
      else {
        if (cd.isExample())
          write("<td><a href=\"terminologies.htm#example\">Example</a></td>");
        else if (cd.getBinding() == Binding.CodeList)
          write("<td><a href=\"terminologies.htm#code\">Fixed</a></td>");
        else
          write("<td><a href=\"terminologies.htm#codeable\">Incomplete</a></td>");
        write("<td valign=\"top\">");
        if (cd.getBinding() == BindingSpecification.Binding.Special) {
          if (cd.getName().equals("MessageEvent"))
            write("<a href=\"message-events.htm\">http://hl7.org/fhir/message-events</a>");
          else if (cd.getName().equals("ResourceType"))
            write("<a href=\"resource-types.htm\">http://hl7.org/fhir/resource-types</a>");
          else if (cd.getName().equals("DataType"))
            write("<a href=\"data-types.htm\">http://hl7.org/fhir/data-types</a>");
          else if (cd.getName().equals("FHIRDefinedType"))
            write("<a href=\"defined-types.htm\">http://hl7.org/fhir/defined-types</a>");
          else 
            throw new Exception("Unknown special type "+cd.getName());
        } 
        if (cd.getBinding() == BindingSpecification.Binding.ValueSet) {
          if (Utilities.noString(cd.getReference())) 
            write("??");
          else if (cd.getReference().startsWith("valueset-"))
            write("<a href=\""+cd.getReference()+".htm\">http://hl7.org/fhir/vs/"+cd.getReference().substring(9)+"</a>");            
          else if (cd.getReference().startsWith("http://hl7.org/fhir")) {
            if (cd.getReference().startsWith("http://hl7.org/fhir/v3/vs/"))
              write("<a href=\"v3/"+cd.getReference().substring(26)+"/index.htm\">"+cd.getReference()+"</a>");
            else if (cd.getReference().startsWith("http://hl7.org/fhir/vs/"))
              write("<a href=\""+cd.getReference().substring(23)+".htm\">"+cd.getReference()+"</a>");
            else
              throw new Exception("Internal reference "+cd.getReference()+" not handled yet");
          } else
            write("<a href=\""+cd.getReference()+".htm\">http://hl7.org/fhir/"+cd.getReference()+"</a>");            
        }
        if (cd.getBinding() == BindingSpecification.Binding.CodeList) {
          write("<a href=\""+cd.getReference().substring(1)+".htm\">http://hl7.org/fhir/"+cd.getReference().substring(1)+"</a>");            
        }
        if (cd.getBinding() == BindingSpecification.Binding.Reference) {
          write("<a href=\""+cd.getReference()+"\">"+cd.getDescription()+"</a>");
        }

        write("</td>");
      }
      write(" </tr>\r\n");
    }
    write("</table>\r\n<p> </p>\r\n");		
	}

  public static String describeBinding(BindingSpecification cd) throws Exception {
    if (cd.getBinding() == BindingSpecification.Binding.Unbound) 
      return cd.getDefinition();
    if (cd.getBinding() == BindingSpecification.Binding.Special) {
      if (cd.getName().equals("MessageEvent"))
        return "the <a href=\"message-events.htm\">Event List in the messaging framework</a>";
      else if (cd.getName().equals("ResourceType"))
        return "<a href=\"resource-types.htm\">Any defined Resource Type name</a>";
      else if (cd.getName().equals("DataType"))
        return "<a href=\"data-types.htm\">Any defined Data Type name</a>";
      else if (cd.getName().equals("FHIRDefinedType"))
        return "<a href=\"defined-types.htm\">Any defined Resource or Data Type name</a>";
      else 
        throw new Exception("Unknown special type "+cd.getName());
    } 
    if (cd.getBinding() == BindingSpecification.Binding.ValueSet) {
      if (Utilities.noString(cd.getReference())) 
        return cd.getDescription();
      else if (cd.getReference().startsWith("http://hl7.org/fhir/v3/vs/"))
        return cd.getDefinition()+" (<a href=\"v3/"+cd.getReference().substring(26)+"/index.htm\">Value Set Definition</a>)";
      else
        return cd.getDescription()+" (<a href=\""+cd.getReference()+".htm\">Value Set Definition</a>)";
    }
    if (cd.getBinding() == BindingSpecification.Binding.CodeList) {
      if (Utilities.noString(cd.getReference())) 
        return cd.getDefinition()+" ("+cd.getDescription()+")";
      else
        return cd.getDefinition()+" (see <a href=\""+cd.getReference().substring(1)+".htm\">http://hl7.org/fhir/"+cd.getReference().substring(1)+"</a> for values)";
    }
    if (cd.getBinding() == BindingSpecification.Binding.Reference) {
      return "see <a href=\""+cd.getReference()+"\">"+cd.getDescription()+"</a>";
    }
    return "??";
  }

  private void genBinding(BindingSpecification cd, String path, boolean isCode) throws Exception {
    if (cd.getName().equals("*unbound*")) {
    	write("  <li>"+path+" (Error!!!)</li>\r\n");
    } else if (cd.getBinding() == BindingSpecification.Binding.Unbound) {
      write("  <li>"+path+" <i>"+Utilities.escapeXml(cd.getName())+"</i>: \""+Utilities.escapeXml(cd.getDefinition())+"\". (not bound to any codes)</li>\r\n");
    } else if (cd.getBinding() == BindingSpecification.Binding.CodeList) {
      String sid = "";
      if (!isCode) {
        sid = "\"<a href=\""+cd.getReference().substring(1)+".htm\">http://hl7.org/fhir/"+cd.getReference().substring(1)+"\"</a>";
        //					if (!sids.contains(sid))
        //						sids.put(sid, new DefinedCode())
        sid = " system "+sid+"";
        if (cd.getBindingStrength().equals(BindingSpecification.BindingStrength.Example))
          write("  <li>"+path+" <i>"+Utilities.escapeXml(cd.getName())+"</i>: \""+Utilities.escapeXml(cd.getDefinition())+"\". Example values are in the "+sid+".\r\n");
        else if (cd.getBindingStrength().equals(BindingSpecification.BindingStrength.Preferred))
          write("  <li>"+path+" <i>"+Utilities.escapeXml(cd.getName())+"</i>: \""+Utilities.escapeXml(cd.getDefinition())+"\". Defined values are in the "+sid+". Other codes can be used when those codes are not suitable\r\n");
        else // if (cd.getBindingStrength().equals(BindingSpecification.BindingStrength.Required))
          write("  <li>"+path+" <i>"+Utilities.escapeXml(cd.getName())+"</i>: \""+Utilities.escapeXml(cd.getDefinition())+"\". Possible values are in the "+sid+".\r\n");
      } else {

        if (cd.getBindingStrength().equals(BindingSpecification.BindingStrength.Example))
          write("  <li>"+path+" <i>"+Utilities.escapeXml(cd.getName())+"</i>: \""+Utilities.escapeXml(cd.getDefinition())+"\""+sid+". Example values:\r\n");
        else if (cd.getBindingStrength().equals(BindingSpecification.BindingStrength.Preferred))
          write("  <li>"+path+" <i>"+Utilities.escapeXml(cd.getName())+"</i>: \""+Utilities.escapeXml(cd.getDefinition())+"\""+sid+". Defined values (extend this with other codes):\r\n");
        else // if (cd.getBindingStrength().equals(BindingSpecification.BindingStrength.Required))
          write("  <li>"+path+" <i>"+Utilities.escapeXml(cd.getName())+"</i>: \""+Utilities.escapeXml(cd.getDefinition())+"\""+sid+". Possible values:\r\n");
        write("    <table class=\"codes\">\r\n");
        boolean hasComment = false;
        boolean hasDefinition = false;
        for (DefinedCode c : cd.getCodes()) {
          hasComment = hasComment || c.hasComment();
          hasDefinition = hasDefinition || c.hasDefinition();
        }
        //				if (hasComment)
        //					write("    <tr><td><b>Code</b></td><td><b>Title</b></td><td><b>Comment</b></td></tr>");
        //				else if (hasDefinition)
        //					write("    <tr><td><b>Code</b></td><td colspan=\"2\"><b>Title</b></td></tr>");


        for (DefinedCode c : cd.getCodes()) {
          if (hasComment)
            write("    <tr><td>"+Utilities.escapeXml(c.getCode())+"</td><td>"+Utilities.escapeXml(c.getDefinition())+"</td><td>"+Utilities.escapeXml(c.getComment())+"</td></tr>");
          else if (hasDefinition)
            write("    <tr><td>"+Utilities.escapeXml(c.getCode())+"</td><td colspan=\"2\">"+Utilities.escapeXml(c.getDefinition())+"</td></tr>");
          else
            write("    <tr><td colspan=\"3\">"+Utilities.escapeXml(c.getCode())+"</td></tr>");
        }
        write("    </table>\r\n");
      }
    	write("  </li>\r\n");
    	
    } else if (cd.getBinding() == BindingSpecification.Binding.Special) {
      if (cd.getName().equals("MessageEvent"))
        write("<li>"+path+" of the <a href=\"message.htm#Events\"> Event List in the messaging framework</a></li>\r\n");
      else if (cd.getName().equals("ResourceType"))
        write("  <li>"+path+" of <a href=\"terminologies.htm#ResourceType\"> any defined Resource Type name</a></li>\r\n");
      else if (cd.getName().equals("FHIRContentType"))
        write("  <li>"+path+" of <a href=\"terminologies.htm#fhircontenttypes\"> any defined Resource or Data Type name</a></li>\r\n");
      else 
        write("  <li>"+path+" of <a href=\"datatypes.htm\"> any defined data Type name</a> (including <a href=\"resources.htm#Resource\">Resource</a>)</li>\r\n");
      
    } else {
      if (cd.getBindingStrength() == BindingSpecification.BindingStrength.Required)
        write("  <li>"+path+" <i>"+Utilities.escapeXml(cd.getName())+"</i>: \""+Utilities.escapeXml(cd.getDefinition())+"\". For example values, see "+ref(cd)+"</li>\r\n");
      else if (cd.getBindingStrength() == BindingSpecification.BindingStrength.Preferred)
        write("  <li>"+path+" <i>"+Utilities.escapeXml(cd.getName())+"</i>: \""+Utilities.escapeXml(cd.getDefinition())+"\". If an appropriate code exists in "+ref(cd)+" then it should be used</li>\r\n");
      else // if (cd.getBindingStrength() = ConceptDomain.BindingStrength.Suggested)
        write("  <li>"+path+" <i>"+Utilities.escapeXml(cd.getName())+"</i>: \""+Utilities.escapeXml(cd.getDefinition())+"\". Example Codes: "+ref(cd)+"</li>\r\n");
    }
  }

	

  private String ref(BindingSpecification cd) {
    if (!cd.hasReference())
      return Utilities.escapeXml(cd.getDescription());
    else if (cd.getReferredValueSet() != null)
      return "<a href=\""+cd.getReference()+".htm\">"+Utilities.escapeXml(cd.getReferredValueSet().getNameSimple())+"</a>";      
    else
      return "<a href=\""+cd.getReference()+"\">"+Utilities.escapeXml(cd.getDescription())+"</a>";
  }


	private void scan(ElementDefn e, String path, Map<String, BindingSpecification> tx) throws Exception {
		if (e.hasBinding()) {
			BindingSpecification cd = getConceptDomainByName(tx, e.getBindingName());
			if (!txusages.containsKey(cd)) {
				txusages.put(cd, new ArrayList<CDUsage>());
				c++;
				txusages.get(cd).add(new CDUsage(String.valueOf(c), null));						
			}
			txusages.get(cd).add(new CDUsage(path, e));			
		}
		for (ElementDefn c : e.getElements()) {
			scan(c, path+"."+c.getName(), tx);
		}		
	}

	private BindingSpecification getConceptDomainByName(Map<String, BindingSpecification> tx, String conceptDomain) throws Exception {		
		for (BindingSpecification cd : tx.values()) {
			if (cd.getName().equals(conceptDomain))
				return cd; 
		}
		throw new Exception("Unable to find Concept Domain "+conceptDomain);
	}
	
	
}
