package org.hl7.fhir.tools.implementations.objectivec;

import java.io.File;

public class OCFile extends File {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4304489836983357294L;

	public OCFile(String pathname) {
		super(pathname);
		
		if (exists() && getParent() != null) {
		      String n = getName();
		      File f = new File(getParent());
		      String[] l = f.list();
		      boolean ok = false;
		      for (String n1 : l) {
		        if (n1.equals(n))
		          ok = true;
		      }
		      if (!ok)
		        throw new Error("Case mismatch of file "+ pathname);
		    }
	
	}
}
