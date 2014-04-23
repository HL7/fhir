package org.hl7.fhir.tools.tests;

import java.io.File;
import java.io.FileInputStream;


public class Utilities {

	// don't run third party programs
	public final static boolean SKIP_THIRD_PARTY = System.getenv("SKIP_THIRD_PARTY") != null;
	public final static String BIN_DIR_WIN = System.getenv("BIN") != null ? System.getenv("BIN") :"C:/Program Files";
	public final static String BROWSER_PATH = System.getProperty("os.name").toLowerCase().startsWith("mac os x") ?
			"open " : BIN_DIR_WIN+"/Mozilla FireFox/FireFox.exe";
	public final static String COMPARE_PATH = System.getProperty("os.name").toLowerCase().startsWith("mac os x") ?
			"/usr/bin/opendiff" : // consult FileMerge.app help doco (part of XCode) + remember to make it executable
				BIN_DIR_WIN+"/WinMerge/WinMerge.exe";
	
	public final static void compareFiles(String filename1, String filename2) throws Exception {
		FileInputStream one = new FileInputStream(new File(filename1));
		FileInputStream two = new FileInputStream(new File(filename2));
		boolean same = one.available() == two.available();
		while (same && one.available() > 0) {
			same = one.read() == two.read();
		}
		one.close();
		two.close();
		if (!same) {
			if (!SKIP_THIRD_PARTY)
				Runtime.getRuntime().exec(COMPARE_PATH+" \""+filename1+"\" \""+filename2+"\"");
			Thread.sleep(1000);
			throw new Exception("Content is not as expected @ "+Integer.toString(one.available()));
		}
	}
}
