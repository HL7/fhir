package org.hl7.fhir.instance.test;

import java.text.ParseException;

import org.hl7.fhir.instance.model.DateAndTime;

// probably should be DUnit

public class BaseParserTests {

	public static class XMLParserTests {

		public void test() {
			try {
	      System.out.println(DateAndTime.now().toString());
	      System.out.println(new DateAndTime("1951-06").toString());
	      System.out.println(new DateAndTime("1951-06-04").toString());
	      System.out.println(new DateAndTime("1951-06-01Z").toString());
	      System.out.println(new DateAndTime("1951-06-01-09:30").toString());
	      System.out.println(new DateAndTime("1951-06-01+09:30").toString());
	      System.out.println(new DateAndTime("2013-06-08T10:57:34+01:00").toString());
	      System.out.println(new DateAndTime("2013-06-08T10:57:34-01:00").toString());
	      System.out.println(new DateAndTime("2013-06-08T09:57:34.2112Z").toString());
	      System.out.println(new DateAndTime("2013-06-08T09:57:34.2112425234Z").toString());

	      System.out.println(DateAndTime.parseV3("195106").toString());
	      System.out.println(DateAndTime.parseV3("19510604").toString());
	      System.out.println(DateAndTime.parseV3("19510601Z").toString());
	      System.out.println(DateAndTime.parseV3("19510601+0930").toString());
	      System.out.println(DateAndTime.parseV3("19510601-0930").toString());
	      System.out.println(DateAndTime.parseV3("20130608105734+0100").toString());
	      System.out.println(DateAndTime.parseV3("20130608105734-0100").toString());
	      System.out.println(DateAndTime.parseV3("20130608095734.2112Z").toString());
} catch (ParseException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
      }
    }
  }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new XMLParserTests().test();

	}

}
