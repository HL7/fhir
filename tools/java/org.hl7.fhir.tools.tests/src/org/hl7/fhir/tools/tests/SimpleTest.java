package org.hl7.fhir.tools.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.hl7.fhir.definitions.generators.specification.DictHTMLGenerator;
import org.hl7.fhir.definitions.generators.specification.XmlSpecGenerator;
import org.hl7.fhir.definitions.generators.xsd.SchemaGenerator;

public class SimpleTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Root root;
		
		//exec("Person");
		exec("organization");
		//exec("Animal");
//		exec("Narrative");
//		exec("Resource");
//		exec("Attachment");
//		exec("Concept");
	}

	private static void exec(String s) throws FileNotFoundException, Exception {
		Root root;
		XmlSpecParser parser = new XmlSpecParser(new FileInputStream(new File("C:\\workspace\\projects\\hl7v4\\java\\testcases\\"+s+".in.txt")));
		root = parser.parse();
		
		File f = new File("C:\\workspace\\projects\\hl7v4\\java\\testcases\\"+s+".in.csv");
		if (f.exists()) {
			CSVParser cparser = new CSVParser(new FileInputStream(f));
			cparser.parse(root);
		}
		
		XmlSpecGenerator gen = new XmlSpecGenerator(new FileOutputStream(new File("C:\\workspace\\projects\\hl7v4\\java\\testcases\\"+s+".out.htm")));
		gen.generate(root);

		CSVGenerator cgen = new CSVGenerator(new FileOutputStream(new File("C:\\workspace\\projects\\hl7v4\\java\\testcases\\"+s+".out.csv")));
		cgen.generate(root);

		DictHTMLGenerator dgen = new DictHTMLGenerator(new FileOutputStream(new File("C:\\workspace\\projects\\hl7v4\\java\\testcases\\"+s+".out.dict.htm")));
		dgen.generate(root);
		
		parser = new XmlSpecParser(new FileInputStream(new File("C:\\workspace\\projects\\hl7v4\\java\\testcases\\"+s+".out.htm")));
		root = parser.parse();
		if (f.exists()) {
			CSVParser cparser = new CSVParser(new FileInputStream(new File("C:\\workspace\\projects\\hl7v4\\java\\testcases\\"+s+".out.csv")));
			cparser.parse(root);
		}

		gen = new XmlSpecGenerator(new FileOutputStream(new File("C:\\workspace\\projects\\hl7v4\\java\\testcases\\"+s+".out2.htm")));
		gen.generate(root);
		
		cgen = new CSVGenerator(new FileOutputStream(new File("C:\\workspace\\projects\\hl7v4\\java\\testcases\\"+s+".out2.csv")));
		cgen.generate(root);

		Utilities.compareFiles("C:\\workspace\\projects\\hl7v4\\java\\testcases\\"+s+".out.htm",
				  "C:\\workspace\\projects\\hl7v4\\java\\testcases\\"+s+".out2.htm");

		Utilities.compareFiles("C:\\workspace\\projects\\hl7v4\\java\\testcases\\"+s+".out.csv",
				  "C:\\workspace\\projects\\hl7v4\\java\\testcases\\"+s+".out2.csv");
		
		SchemaGenerator sgen = new SchemaGenerator(new FileOutputStream(new File("C:\\workspace\\projects\\hl7v4\\java\\testcases\\"+s+".out.xsd")));
		sgen.generate(root);

	}

}
