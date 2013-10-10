package org.hl7.fhir.instance.model;

public class AtomCategory {

  private String scheme;
  private String term;
  private String label;
  
  
	public AtomCategory(String scheme, String term, String label) {
	  super();
	  this.scheme = scheme;
	  this.term = term;
	  this.label = label;
  }
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
  
}
