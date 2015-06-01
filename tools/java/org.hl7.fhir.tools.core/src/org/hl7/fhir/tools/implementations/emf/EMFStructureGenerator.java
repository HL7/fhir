package org.hl7.fhir.tools.implementations.emf;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.tools.implementations.GeneratorUtils;
import org.hl7.fhir.utilities.Utilities;

public class EMFStructureGenerator extends EMFBase {

	public enum OOGenClass { Structure, Type, Resource, BackboneElement, Constraint }
	
	public EMFStructureGenerator(Writer w, Definitions definitions) throws UnsupportedEncodingException {
		super();
		init(w);
		this.definitions = definitions;
	}

	private Map<ElementDefn, String> typeNames = new HashMap<ElementDefn, String>();
	private List<String> typeNameStrings = new ArrayList<String>();

	private List<ElementDefn> enums = new ArrayList<ElementDefn>();
	private List<String> enumNames = new ArrayList<String>();
	private List<ElementDefn> strucs  = new ArrayList<ElementDefn>();

  private String classname;


	public Map<ElementDefn, String> getTypeNames() {
		return typeNames;
	}

	public void generate(ElementDefn root, String name, OOGenClass clss, ProfiledType cd) throws Exception {
		typeNames.clear();
		typeNameStrings.clear();
		enums.clear();
		strucs.clear();
    classname = upFirst(name);

    
		if (clss == OOGenClass.Resource)
		  write("  class "+classname+" extends Resource {", root.getDefinition());
    else if (clss == OOGenClass.Structure)
      write("  class "+classname+" extends Element {", root.getDefinition());
    else if (clss == OOGenClass.BackboneElement)
      write("  class "+classname+" extends BackboneElement {", root.getDefinition());
		else if (clss == OOGenClass.Constraint)
		  write("  class "+classname+" extends "+upFirst(root.getName())+" {", root.getDefinition());
	  else if (root.getName().equals("Quantity"))
	    write("  class "+classname+" extends Type {", root.getDefinition());
		else
		  write("  class "+classname+" extends Type {", root.getDefinition());

		if (clss != OOGenClass.Constraint) {
			for (ElementDefn e : root.getElements()) {
				if (clss != OOGenClass.Resource || (!e.getName().equals("extension") && !e.getName().equals("text")))
					scanNestedTypes(root, root.getName(), e);
			}
			for (ElementDefn e : enums) {
				generateEnum(e);
			}
			for (ElementDefn e : strucs) {
				generateType(e, clss == OOGenClass.Resource ? OOGenClass.BackboneElement : OOGenClass.Structure);
			}

			for (ElementDefn e : root.getElements()) {
				if (clss != OOGenClass.Resource || (!e.getName().equals("extension") && !e.getName().equals("text")))
					generateField(root, e, "  ");
			}
		}
    write("  }");
    write("");

	}

  private String upFirst(String name) {
		return name.substring(0,1).toUpperCase()+name.substring(1);
	}

	private void generateEnum(ElementDefn e) throws Exception {
		String tn = typeNames.get(e);
		String tns = tn.substring(tn.indexOf("<")+1);
		BindingSpecification cd = e.getBinding();

		write("    enum "+tns+" {", cd.getDefinition());
    write("      0 : Null = \"Missing in instance\";");

		int i = 0;
		for (DefinedCode c : cd.getAllCodes()) {
			i++;
			String cc = Utilities.camelCase(c.getCode());
			if (GeneratorUtils.isJavaReservedWord(cc))
				cc = cc + "_";
			if (Utilities.isInteger(cc))
			  cc = "_"+cc;
			if (cc.equals("<"))
				cc = "lessThan";
			else if (cc.equals("<="))
				cc = "lessOrEqual";
			else if (cc.equals(">"))
				cc = "greaterThan";
			else if (cc.equals(">="))
				cc = "greaterOrEqual";
      else if (cc.equals("="))
        cc = "equal";
			else if (allPlusMinus(cc))
			  cc = cc.replace("-", "Minus").replace("+", "Plus");
			else
			  cc = cc.replace("-", "").replace("+", "");
	    write("      "+Integer.toString(i)+" : "+cc+" = \""+c.getDefinition()+"\";");
		}
    write("    }");
    write("");

	}

	private boolean allPlusMinus(String cc) {
	  for (char c : cc.toCharArray())
	    if (!(c == '-' || c == '+'))
	      return false;
    return true;
  }

  private void generateType(ElementDefn e, OOGenClass clss) throws Exception {
		String tn = typeNames.get(e);

		if (clss == OOGenClass.BackboneElement)
		  write("    class "+tn+" extends BackboneElement {");
		else
		  write("    class "+tn+" extends Element {");

		for (ElementDefn c : e.getElements()) {
			generateField(e, c, "    ");
		}
    write("    }");
    write("");
	}

  private void scanNestedTypes(ElementDefn root, String path, ElementDefn e) throws Exception {
		String tn = null;
		if (e.typeCode().equals("code") && e.hasBinding()) {
			BindingSpecification cd = e.getBinding();
			if (cd != null && cd.getBinding() == BindingSpecification.BindingMethod.CodeList) {
				tn = getCodeListType(cd.getReference().substring(1));
				if (!enumNames.contains(tn)) {
					enumNames.add(tn);
					enums.add(e);
				}
				typeNames.put(e, tn);
			}
		}
		if (tn == null) {
			if (e.getTypes().size() > 0 && !e.usesCompositeType()) {
				tn = getTypeName(e);
				if (e.typeCode().equals("xml:lang"))
				  tn = "code";
				if (e.isXhtmlElement()) 
					tn = "xhtml?";
				else if (e.getTypes().get(0).isWildcardType())
					tn ="Type";

				typeNames.put(e,  tn);
			} else {
				if (e.usesCompositeType()) {
					tn = typeNames.get(getElementForPath(root, e.typeCode().substring(1)));
					typeNames.put(e,  tn);
//				} else if (e.getDeclaredTypeName() != null) {
//					tn = e.getDeclaredTypeName();
//					typeNames.put(e,  tn);
//					System.out.println(tn);
				} else {
					if (e.getDeclaredTypeName() != null) 
						tn = e.getDeclaredTypeName();
					else
						tn = getTitle(e.getName());
					if (tn.equals("Element"))
						tn = "Element_";
					strucs.add(e);
					if (typeNameStrings.contains(tn)) {
						char i = 'A';
						while (typeNameStrings.contains(tn+i))
							i++;
						tn = tn + i;
					}
					typeNames.put(e,  tn);
					typeNameStrings.add(tn);
					for (ElementDefn c : e.getElements()) {
						scanNestedTypes(root, path+getTitle(e.getName()), c);
					}
				}
			}
		}
	}

	private Object getElementForPath(ElementDefn root, String pathname) throws Exception {
		String[] path = pathname.split("\\.");
		if (!path[0].equals(root.getName()))
			throw new Exception("Element Path '"+pathname+"' is not legal in this context");
		ElementDefn res = root;
		for (int i = 1; i < path.length; i++)
		{
			String en = path[i];
			if (en.length() == 0)
				throw new Exception("Improper path "+pathname);
			ElementDefn t = res.getElementByName(en);
			if (t == null) {
				throw new Exception("unable to resolve "+pathname);
			}
			res = t; 
		}
		return res;

	}

	private String getCodeListType(String binding) {
		StringBuilder b = new StringBuilder();
		boolean up = true;
		for (char ch: binding.toCharArray()) {
			if (ch == '-')
				up = true;
			else if (up) {
				b.append(Character.toUpperCase(ch));
				up = false;
			}
			else				
				b.append(ch);
		}
		return b.toString();
	}

	private void generateField(ElementDefn root, ElementDefn e, String prefix) throws Exception {
	  String tn = typeNames.get(e);
	  
	  write(prefix+"  attribute "+tn+" "+getElementName(e.getName(), true)+" ("+Integer.toString(e.getMinCardinality())+".."+(e.unbounded() ? "-1" : "1")+");", e.getDefinition());

//		if (e.unbounded()) {
//			if (tn == null && e.usesCompositeType())
//				field.addProperty("type", "List<"+root.getName()+">");
//			else {
//			  field.addProperty("type", "List<"+tn+">");
////	      if (e.getTypes().size() == 1 && e.typeCode().startsWith("Reference(")) {
////	        List<String> params = e.getTypes().get(0).getParams();
////	        String rn = params.size() == 1 ? params.get(0) : "Resource";
////	        if (rn.equals("Any"))
////	          rn = "Resource";
////	        jdoc(indent, "The actual objects that are the target of the reference ("+e.getDefinition()+")");
////	        writeWithHash(indent+"protected List<"+rn+"> "+getElementName(e.getName(), true)+"Target = new ArrayList<"+rn+">();\r\n");
////	        write("\r\n");
////	      }
//			}
//		} else {
//		  field.addProperty("type", tn);
////      if (e.getTypes().size() == 1 && e.typeCode().startsWith("Reference(")) {
////        List<String> params = e.getTypes().get(0).getParams();
////        String rn = params.size() == 1 ? params.get(0) : "Resource";
////        if (rn.equals("Any"))
////          rn = "Resource";
////        jdoc(indent, "The actual object that is the target of the reference ("+e.getDefinition()+")");
////        writeWithHash(indent+"protected "+rn+" "+getElementName(e.getName(), true)+"Target;\r\n");
////        write("\r\n");
////      }
//		}
	}





}
