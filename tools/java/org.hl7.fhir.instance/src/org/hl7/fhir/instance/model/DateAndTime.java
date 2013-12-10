package org.hl7.fhir.instance.model;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.hl7.fhir.utilities.Utilities;

// java 1.7 can parse xml date/times, but going java 1.7 is too hard for implementers
// javax.xml.bind.DatatypeConverter can parse xml date/times, but is not available on android. (and it's error message sucks)
// anyway, the underlying date/time concept has variable precision, and timezone, and neither Date nor Calendar real with
// that nicely. So we parse the date directly

public class DateAndTime {

	private int year;
	private int month;
	private int day;
	private boolean time;
	private int hour;
	private int minute;
	private boolean seconds;
	private int second;
	private int fractions;
	private int fraction;
	private java.lang.Boolean timezone;
	private int tzHour;
	private int tzMin;

	public DateAndTime(String xDate) throws ParseException {

		String s;
		String t = null;
		if (xDate.endsWith("Z")) {
			s = xDate.substring(0, xDate.length()-1);
			timezone = false;
		} else if (xDate.lastIndexOf("-") > 8) {
			s = xDate.substring(0, xDate.lastIndexOf("-"));
			t = xDate.substring(xDate.lastIndexOf("-"));
		} else if (xDate.lastIndexOf("+") > 8) {
			s = xDate.substring(0, xDate.lastIndexOf("+"));
			t = xDate.substring(xDate.lastIndexOf("+"));
		} else { // no timezone
			s = xDate;
			t = null;
			timezone = null;
		}

		int offset = 0;
		try {
			int yearlength = s.startsWith("-") ? s.substring(1).indexOf("-") + 1 : s.indexOf("-");
			setYear(readField(s, 0, yearlength));
			offset = yearlength;
			if (s.length() >= yearlength + 3)
				setMonth(readField(s, yearlength + 1, 2));
			offset = yearlength + 4;
			if (s.length() >= yearlength + 6)
				setDay(readField(s, yearlength + 4, 2));
			offset = yearlength + 7;
			if (s.length() >= yearlength + 9)
				setHour(readField(s, yearlength + 7, 2));
			offset = yearlength + 10;
			if (s.length() >= yearlength + 12)
				setMinute(readField(s, yearlength + 10, 2));
			offset = yearlength + 13;
			if (s.length() >= yearlength + 15)
				setSecond(readField(s, yearlength + 13, 2));
			offset = yearlength + 16;
			if (s.length() >= yearlength + 17) {
				setFractions(s.length() - (yearlength + 16));
				setFraction(readField(s, yearlength + 16, fractions));
			}
			if (t != null) {
				setTzHour(readField(t, 0, 3));
				setTzMin(readField(t, 4, 2));
			}
		} catch (Exception e) {
			throw new ParseException("The date '"+xDate+"' is not a valid Date Time Format at character "+java.lang.Integer.toString(offset), offset);
		}
	}

	private static int readField(String date, int i, int j) {
		String s = date.substring(i, i+j);
		return java.lang.Integer.parseInt(s);
	}


	public DateAndTime(Calendar date) {
    setCalendar(date);
	}

	private void setCalendar(Calendar date) {
	  setYear(date.get(Calendar.YEAR));
    setMonth(date.get(Calendar.MONTH)+1);
    setDay(date.get(Calendar.DAY_OF_MONTH));
    setHour(date.get(Calendar.HOUR_OF_DAY));
    setMinute(date.get(Calendar.MINUTE));
    setSecond(date.get(Calendar.SECOND));
    if (date.get(Calendar.MILLISECOND) > 0) {
      setFractions(3);
      try {
	      setFraction(date.get(Calendar.MILLISECOND));
      } catch (Exception e) {
      	// can't happen
      }
    }
    if (date.getTimeZone() != null) {
    	int offset = date.getTimeZone().getOffset(date.getTime().getTime());
    	setTzHour(offset / (60 * 60 * 1000));
    	offset = offset - tzHour * 60 * 60 * 1000;
    	setTzMin(offset / (60 * 1000));
    }
  }

	public DateAndTime(java.util.Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		setCalendar(cal);
	}

	private DateAndTime() {
  }

	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(Utilities.padLeft(java.lang.Integer.toString(year), '0', 4));
		if (month != 0) {
			b.append("-");
			b.append(Utilities.padLeft(java.lang.Integer.toString(month), '0', 2));
			if (day != 0) {
				b.append("-");
				b.append(Utilities.padLeft(java.lang.Integer.toString(day), '0', 2));
				if (time) {
					b.append("T");
					b.append(Utilities.padLeft(java.lang.Integer.toString(hour), '0', 2));
					b.append(":");
					b.append(Utilities.padLeft(java.lang.Integer.toString(minute), '0', 2));
					if (seconds) {
						b.append(":");
						b.append(Utilities.padLeft(java.lang.Integer.toString(second), '0', 2));
						if (fractions > 0) {
							b.append(".");
							b.append(Utilities.padLeft(java.lang.Integer.toString(fraction), '0', fractions));
						}
					}
				}
				if (timezone != null) {
					if (!timezone) {
						b.append("Z");
					} else {
						if (tzHour > 0) {
							b.append("+");
							b.append(Utilities.padLeft(java.lang.Integer.toString(tzHour), '0', 2));
						} else { 
							b.append("-");
							b.append(Utilities.padLeft(java.lang.Integer.toString(-tzHour), '0', 2));
						}
						b.append(":");
						b.append(Utilities.padLeft(java.lang.Integer.toString(tzMin), '0', 2));
					}
				}
			}
		}
		return b.toString();
	}

	public Calendar toCalendar() {
		return null;
	}

	public Date toDate() {
		return null;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public boolean isTime() {
		return time;
	}

	public void setTime(boolean time) {
		this.time = time;
		if (!time)
			setSeconds(false);
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.time = true;
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.time = true;
		this.minute = minute;
	}

	public boolean isSeconds() {
		return seconds;
	}

	public void setSeconds(boolean seconds) {
		this.seconds = seconds;
		if (!seconds)
			setFractions(0);
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.time = true;
		this.seconds = true;
		this.second = second;
	}

	public int getFractions() {
		return fractions;
	}

	public void setFractions(int fractions) {
		this.fractions = fractions;
	}

	public int getFraction() {
		return fraction;
	}

	public void setFraction(int fraction) throws Exception {
		this.fraction = fraction;
		if (this.fraction == 0)
			throw new Exception("set 'fractions' before setting 'fraction'");
	}

	public java.lang.Boolean getTimezone() {
		return timezone;
	}

	public void setTimezone(java.lang.Boolean timezone) {
		this.timezone = timezone;
	}

	public int getTzHour() {
		return tzHour;
	}

	public void setTzHour(int tzHour) {
		this.tzHour = tzHour;
		this.timezone = true;
	}

	public int getTzMin() {
		return tzMin;
	}

	public void setTzMin(int tzMin) {
		this.tzMin = tzMin;
		this.timezone = true;
	}

	public static DateAndTime now() {
		return new DateAndTime(Calendar.getInstance());
	}

	public static DateAndTime parseV3(String xDate) throws ParseException {

		DateAndTime res = new DateAndTime();
		String s;
		String t = null;
		if (xDate.endsWith("Z")) {
			s = xDate.substring(0, xDate.length()-1);
			res.timezone = false;
		} else if (xDate.lastIndexOf("-") > 0) {
			s = xDate.substring(0, xDate.lastIndexOf("-"));
			t = xDate.substring(xDate.lastIndexOf("-"));
		} else if (xDate.lastIndexOf("+") > 0) {
			s = xDate.substring(0, xDate.lastIndexOf("+"));
			t = xDate.substring(xDate.lastIndexOf("+"));
		} else { // no timezone
			s = xDate;
			t = null;
			res.timezone = null;
		}

		int offset = 0;
		try {
			res.setYear(readField(s, 0, 4));
			offset = 4;
			if (s.length() >= 6)
				res.setMonth(readField(s, 4, 2));
			offset = 6;
			if (s.length() >= 8)
				res.setDay(readField(s, 6, 2));
			offset = 8;
			if (s.length() >= 10)
				res.setHour(readField(s, 8, 2));
			offset = 10;
			if (s.length() >= 12)
				res.setMinute(readField(s, 10, 2));
			offset = 12;
			if (s.length() >= 14)
				res.setSecond(readField(s, 12, 2));
			offset = 15;
			if (s.length() >= 16) {
				res.setFractions(s.length() - (15));
				res.setFraction(readField(s, 15, res.fractions));
			}
			if (t != null) {
				res.setTzHour(readField(t, 0, 3));
				res.setTzMin(readField(t, 3, 2));
			}
		} catch (Exception e) {
			throw new ParseException("The date '"+xDate+"' is not a valid Date Time Format at character "+java.lang.Integer.toString(offset), offset);
		}
		return res;
  }

  public DateAndTime expandTime() {
    time = true;
    seconds = true;
    timezone = true;
    TimeZone tz = TimeZone.getDefault();
    
    int offset = tz.getOffset(new java.util.Date().getTime());
    setTzHour(offset / (60 * 60 * 1000));
    offset = offset - tzHour * 60 * 60 * 1000;
    setTzMin(offset / (60 * 1000));
    return this;
  }



}
