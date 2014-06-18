package org.hl7.fhir.instance.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Calendar;

import org.hl7.fhir.instance.model.DateAndTime;
import org.junit.Test;


public class DateAndTimeTests {

	@Test
	public void testParsing() throws ParseException {
		assertEquals("2013-02-02", new DateAndTime("2013-02-02").toString());
		assertEquals("2013-02", new DateAndTime("2013-02").toString());
		assertEquals("2013", new DateAndTime("2013").toString());
		assertEquals("2013-02-02T20:13", new DateAndTime("2013-02-02T20:13").toString());
		assertEquals("2013-02-02T20:13:03", new DateAndTime("2013-02-02T20:13:03").toString());
		assertEquals("2013-02-02T20:13:03Z", new DateAndTime("2013-02-02T20:13:03Z").toString());
		assertEquals("2013-02-02T20:13:03+05:00", new DateAndTime("2013-02-02T20:13:03+05:00").toString());
		assertEquals("2013-02-02T20:13:03-05:00", new DateAndTime("2013-02-02T20:13:03-05:00").toString());
		assertEquals("2013-02-02T20:13-05:00", new DateAndTime("2013-02-02T20:13-05:00").toString());
		assertEquals("2013-02-02T20:13-00:00", new DateAndTime("2013-02-02T20:13-00:00").toString());
		assertEquals("2013-02-02-05:00", new DateAndTime("2013-02-02-05:00").toString());
	}


//	@Test
//	public void testCalnendar() throws ParseException {
//		DateAndTime dat = new DateAndTime("2013-02-02T20:13+05:00");
//		Calendar date = dat.toCalendar();
//		DateAndTime dat2 = new DateAndTime(date);
//		assertEquals(dat.toString(), dat2.toString());
//	}
	
	@Test
	public void testCalendar() throws ParseException, InterruptedException {
		DateAndTime dt = DateAndTime.now();
		Thread.sleep(1200);
		DateAndTime dt2 = DateAndTime.now();
		// assertNotEquals(dt.toString(), dt2.toString());
	}	
	
	@Test
	public void testV3() throws ParseException {
		assertEquals("2013-02-02", DateAndTime.parseV3("20130202").toString());
		assertEquals("2013-02", DateAndTime.parseV3("201302").toString());
		assertEquals("2013", DateAndTime.parseV3("2013").toString());
		assertEquals("2013-02-02T20:13", DateAndTime.parseV3("201302022013").toString());
		assertEquals("2013-02-02T20:13:03", DateAndTime.parseV3("20130202201303").toString());
		assertEquals("2013-02-02T20:13:03Z", DateAndTime.parseV3("20130202201303Z").toString());
		assertEquals("2013-02-02T20:13:03+05:00", DateAndTime.parseV3("20130202201303+0500").toString());
		assertEquals("2013-02-02T20:13:03-05:00", DateAndTime.parseV3("20130202201303-0500").toString());
		assertEquals("2013-02-02T20:13-05:00", DateAndTime.parseV3("201302022013-0500").toString());
		assertEquals("2013-02-02T20:13-00:00", DateAndTime.parseV3("201302022013-0000").toString());
		assertEquals("2013-02-02-05:00", DateAndTime.parseV3("20130202-0500").toString());
	}

	@Test
	public void testBefore() throws ParseException {
		assertFalse(new DateAndTime("2013-02-02").before(new DateAndTime("2013-02-01")));
		assertFalse(new DateAndTime("2013-02-02").before(new DateAndTime("2013-02-02")));
		assertTrue(new DateAndTime("2013-02-02").before(new DateAndTime("2013-02-03")));
		assertFalse(new DateAndTime("2013-02").before(new DateAndTime("2013-01")));
		assertFalse(new DateAndTime("2013-02").before(new DateAndTime("2012-01")));
		assertFalse(new DateAndTime("2013").before(new DateAndTime("2012")));
		assertFalse(new DateAndTime("2013-02-02T20:13").before(new DateAndTime("2013-02-02T20:12")));
		assertFalse(new DateAndTime("2013-02-02T20:13:03").before(new DateAndTime("2013-02-02T20:13:02")));
		assertFalse(new DateAndTime("2013-02-02T20:13:03").before(new DateAndTime("2013-02-02T20:13:03")));
		assertFalse(new DateAndTime("2013-02-02T20:13:03Z").before(new DateAndTime("2013-02-02T20:13:02Z")));
		assertFalse(new DateAndTime("2013-02-02T20:13:03Z").before(new DateAndTime("2013-02-01T20:13:05Z")));
		assertFalse(new DateAndTime("2013-02-02T20:13:03Z").before(new DateAndTime("2013-02-02T20:13:02+01:00")));
	}

	@Test
	public void testAdd() throws Exception {
    // simple addition
		checkAdd("2013-02-02T20:13:15", Calendar.DAY_OF_YEAR, 1, "2013-02-03T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MONTH, 1, "2013-03-02T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.YEAR, 1, "2014-02-02T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.HOUR, 1, "2013-02-02T21:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MINUTE, 1, "2013-02-02T20:14:15");
		checkAdd("2013-02-02T20:13:15", Calendar.SECOND, 1, "2013-02-02T20:13:16");
		
		// boundary conditions
		checkAdd("2013-02-02T20:13:15", Calendar.DAY_OF_YEAR, -1, "2013-02-01T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MONTH, -1, "2013-01-02T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.YEAR, -1, "2012-02-02T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.HOUR, -1, "2013-02-02T19:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MINUTE, -1, "2013-02-02T20:12:15");
		checkAdd("2013-02-02T20:13:15", Calendar.SECOND, -1, "2013-02-02T20:13:14");

		checkAdd("2013-02-02T20:13:15", Calendar.SECOND, 60, "2013-02-02T20:14:15");
		checkAdd("2013-02-02T20:13:15", Calendar.SECOND, 45, "2013-02-02T20:14:00");
		checkAdd("2013-02-02T20:13:15", Calendar.SECOND, 46, "2013-02-02T20:14:01");
		checkAdd("2013-02-02T20:13:15", Calendar.SECOND, -15, "2013-02-02T20:13:00");
		checkAdd("2013-02-02T20:13:15", Calendar.SECOND, -16, "2013-02-02T20:12:59");
		checkAdd("2013-02-02T20:13:15", Calendar.SECOND, -60, "2013-02-02T20:12:15");

		checkAdd("2013-02-02T20:13:15", Calendar.MINUTE, 60, "2013-02-02T21:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MINUTE, 47, "2013-02-02T21:00:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MINUTE, 48, "2013-02-02T21:01:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MINUTE, -13, "2013-02-02T20:00:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MINUTE, -16, "2013-02-02T19:57:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MINUTE, -60, "2013-02-02T19:13:15");

		checkAdd("2013-02-02T20:13:15", Calendar.HOUR, 24, "2013-02-03T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.HOUR, 4, "2013-02-03T00:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.HOUR, 5, "2013-02-03T01:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.HOUR, -20, "2013-02-02T00:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.HOUR, -21, "2013-02-01T23:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.HOUR, -24, "2013-02-01T20:13:15");

		// testing days. This is complicated because we run into calendar issues
		checkAdd("2013-02-02T20:13:15", Calendar.DAY_OF_YEAR, 28, "2013-03-02T20:13:15");
		checkAdd("2013-03-02T20:13:15", Calendar.DAY_OF_YEAR, 31, "2013-04-02T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.DAY_OF_YEAR, 26, "2013-02-28T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.DAY_OF_YEAR, -1, "2013-02-01T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.DAY_OF_YEAR, -2, "2013-01-31T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.DAY_OF_YEAR, -31, "2013-01-02T20:13:15");

		checkAdd("2013-02-02T20:13:15", Calendar.MONTH, 12, "2014-02-02T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MONTH, 11, "2014-01-02T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MONTH, 10, "2013-12-02T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MONTH, -1, "2013-01-02T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MONTH, -2, "2012-12-02T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MONTH, -12, "2012-02-02T20:13:15");

	}


	private void checkAdd(String base, int field, int value, String outcome) throws Exception {
		DateAndTime dt = new DateAndTime(base);
		dt.add(field, value);
		assertEquals(outcome, dt.toString());
  }

	
}
