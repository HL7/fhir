package org.hl7.fhir.instance.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hl7.fhir.instance.formats.XmlComposer;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Code;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.DataElement;
import org.hl7.fhir.instance.model.DataElement.ResourceObservationDefStatus;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.String_;
import org.hl7.fhir.instance.model.Contact;
import org.hl7.fhir.instance.model.Contact.ContactSystem;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * This class converts the LOINC XML representation that the FHIR build tool uses internally to a set of DataElements in an atom feed
 * 
 * @author Grahame
 *
 */
public class LoincToDEConvertor {

	// C:\temp\LOINC.xml
	public static void main(String[] args) throws Exception {
		String output = null;
		if (args.length == 0) {
			System.out.println("FHIR LOINC to CDE convertor. ");
			System.out.println("");
			System.out.println("This tool converts from LOINC to A set of CommonDataElement definitions.");
			System.out.println("");
			System.out.println("Usage: [jar(path?)] [dest] (-defn [definitions]) where: ");
			System.out.println("* [dest] is a file name of the bundle to produce");
			System.out.println("* [definitions] is the file name of a file produced by exporting the main LOINC table from the mdb to XML");
			System.out.println("");
		} else {
			LoincToDEConvertor exe = new LoincToDEConvertor();
			exe.setDest(args[0]);
			for (int i = 1; i < args.length; i++) {
				if (args[i].equals("-defn"))
					exe.setDefinitions(args[i+1]);
			}
			exe.process();
		}

	}

	private String dest;
	private String definitions;
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
	public String getDefinitions() {
		return definitions;
	}
	public void setDefinitions(String definitions) {
		this.definitions = definitions;
	}

	private Document xml;
	private AtomFeed feed;
	private DateAndTime now;

	public void process() throws Exception {
		log("Begin. Produce Loinc CDEs in "+dest+" from "+definitions);
		loadLoinc();
		log("LOINC loaded");

		now = DateAndTime.now();

		feed = new AtomFeed();
		feed.setId("http://hl7.org/fhir/commondataelement/loinc");
		feed.setUpdated(now);
		feed.setAuthorName("FHIR Core Team");

		processLoincCodes();
		if (dest != null) {
			log("Saving...");
			saveBundle();
		}
		log("Done");

	}

	private void log(String string) {
		System.out.println(string);

	}
	private void loadLoinc() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();

		xml = builder.parse(new FileInputStream(definitions)); 
	}

	private void saveBundle() throws Exception {
		XmlComposer xml = new XmlComposer();
		xml.compose(new FileOutputStream(dest), feed, true);
	}

	private String col(Element row, String name) {
		Element e = XMLUtil.getNamedChild(row, name);
		if (e == null)
			return null;
		String text = e.getTextContent();
		return text;
	}

	private boolean hasCol(Element row, String name) {
		return Utilities.noString(col(row, name));
	}

	private void processLoincCodes() {
		Element row = XMLUtil.getFirstChild(xml.getDocumentElement());
		int i = 0;
		while (row != null) {
			i++;
			if (i % 1000 == 0)
				System.out.print(".");
				String code = col(row, "LOINC_NUM");
				String comp = col(row, "COMPONENT");
				AtomEntry<DataElement> ae = new AtomEntry<DataElement>();
				ae.setId("http://hl7.org/fhir/commondataelement/loinc-"+code);
				ae.setTitle("LOINC Code "+code+": "+comp);
				ae.setPublished(now);
				ae.setUpdated(now);
				feed.getEntryList().add(ae);
				DataElement cde = new DataElement();
				Identifier id = new Identifier();
				id.setSystemSimple("http://hl7.org/fhir/commondataelement/loinc");
				id.setValueSimple(code);
				cde.setIdentifier(id);
				cde.setPublisherSimple("HL7 FHIR Project Team / LOINC");
				cde.getTelecom().add(new Contact().setSystemSimple(ContactSystem.url).setValueSimple("http://hl7.org/fhir"));
				cde.getTelecom().add(new Contact().setSystemSimple(ContactSystem.url).setValueSimple("http://loinc.org"));
				if (!col(row, "STATUS").equals("ACTIVE"))
	 				cde.setStatusSimple(ResourceObservationDefStatus.draft); // till we get good at this
				else
					cde.setStatusSimple(ResourceObservationDefStatus.retired); // till we get good at this
				cde.setDateSimple(now);
				cde.setNameSimple(comp);
				ae.setResource(cde);

				// PROPERTY	ignore
				// TIME_ASPCT	
				// SYSTEM	
				// SCALE_TYP	
				// METHOD_TYP	
				cde.getCategory().add(new CodeableConcept().setTextSimple(col(row, "CLASS")));
				// SOURCE	
				// DATE_LAST_CHANGED - should be in ?	
				// CHNG_TYPE	
				cde.setCommentsSimple(col(row , "COMMENTS"));
				if (hasCol(row, "CONSUMER_NAME"))
					cde.getSynonym().add(new String_().setValue(col(row, "CONSUMER_NAME")));	
				// MOLAR_MASS	
				// CLASSTYPE	
				// FORMULA	
				// SPECIES	
				// EXMPL_ANSWERS	
				// ACSSYM	
				// BASE_NAME - ? this is a relationship	
				// NAACCR_ID	
				// ---------- CODE_TABLE todo	
				// SURVEY_QUEST_TEXT	
				// SURVEY_QUEST_SRC	
				if (hasCol(row, "RELATEDNAMES2")) {
	        String n = col(row, "RELATEDNAMES2");
	        for (String s : n.split(";")) {
						if (!Utilities.noString(s))
							cde.getSynonym().add(new String_().setValue(s));	
					}
        }
				cde.getSynonym().add(new String_().setValue(col(row, "SHORTNAME")));	
				// ORDER_OBS	
				// CDISC Code	
				// HL7_FIELD_SUBFIELD_ID	
				//  ------------------ EXTERNAL_COPYRIGHT_NOTICE todo	
				cde.setDefinitionSimple(col(row, "LONG_COMMON_NAME"));	
				// HL7_V2_DATATYPE	
				cde.setTypeSimple(makeType(col(row, "HL7_V3_DATATYPE"), code));	
				// todo... CURATED_RANGE_AND_UNITS	
				// todo: DOCUMENT_SECTION	
				// STATUS_REASON	
				// STATUS_TEXT	
				// CHANGE_REASON_PUBLIC	
				// COMMON_TEST_RANK	
				// COMMON_ORDER_RANK	
				// COMMON_SI_TEST_RANK	
				// HL7_ATTACHMENT_STRUCTURE

				// units:
				// UNITSREQUIRED	
				// SUBMITTED_UNITS
				cde.setUnits(makeUnits(col(row, "EXAMPLE_UNITS"), col(row, "EXAMPLE_UCUM_UNITS")));
				// EXAMPLE_SI_UCUM_UNITS	
//			}
			row = XMLUtil.getNextSibling(row);
		}
		System.out.println("done");
	}

	private String makeType(String type, String id) {
		if (Utilities.noString(type))
			return null;
		if (type.equals("PQ"))
			return "Quantity";
		else if (type.equals("ED"))
			return "Attachment";
		else if (type.equals("TS"))
			return "dateTime";
		else if (type.equals("ST"))
			return "string";
		else if (type.equals("II"))
			return "Identifier";
		else if (type.equals("CWE"))
			return "CodeableConcept";
		else if (type.equals("CD") || type.equals("CO"))
			return "CodeableConcept";
		else if (type.equals("PN"))
			return "HumanName";
		else if (type.equals("EN"))
			return "HumanName";
		else if (type.equals("AD"))
			return "Address";
		else if (type.equals("BL"))
			return "boolean";
		else if (type.equals("GTS"))
			return "Schedule";
		else if (type.equals("INT"))
			return "integer";
		else if (type.equals("CS"))
			return "code";
		else if (type.equals("IVL_TS"))
			return "Period";
		else if (type.equals("MMAT") || type.equals("PRF") || type.equals("TX") || type.equals("DT") || type.equals("FT"))
			return null;
		else
			throw new Error("unmapped type "+type+" for LOINC code "+id);
	} // 18606-4: MMAT.  18665-0: PRF. 18671-8: TX. 55400-6: DT; 8251-1: FT 

	private CodeableConcept makeUnits(String text, String ucum) {
		if (Utilities.noString(text) && Utilities.noString(ucum))
			return null;
		CodeableConcept cc = new CodeableConcept();
		cc.setTextSimple(text);
		cc.getCoding().add(new Coding().setCodeSimple(ucum).setSystemSimple("http://unitsofmeasure.org"));
		return cc;
	}
  public AtomFeed getFeed() {
    return feed;
  }
}
