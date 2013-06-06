package org.hl7.fhir.utilities.xml;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
 
/**
 * The Class DateHelper is used to handle ISO date.
 */

public class DateHelper
{
	/** The date formatters */
	
	public static String FORMAT_DATE_ISO[] = { "yyyy-MM-dd'T'HH:mm:ssZ",
											   "yyyy-MM-dd'T'HH:mmZ",
											   "yyyy-MM-dd",
											   "yyyy-MM",
											   "yyyy" };
	/**
	 * Converts an ISO date to a Date object.
	 *
	 * @param isoDateString the iso date string
	 * @return the date or null in case of error.
	 */
	public static Date fromISODateString(String isoDateString)
	{
		if (isoDateString != null)
			for (int i = 0; i < FORMAT_DATE_ISO.length; ++i)
			{
				try
				{
					return new SimpleDateFormat(FORMAT_DATE_ISO[i]).parse(isoDateString);
				}
				catch (ParseException e)
				{
					// Try with next one...
				}
			}
		return null;
	}

	/**
	 * Converts a date to an ISO date.
	 *
	 * @param date the date object
	 * @param format - if not specified, will use 'yyyy-MM-ddTHH:mm:ssZ'
	 * @param tz - tz to set to, if not specified uses local timezone
	 * @return the iso-formatted date string or null in case of error.
	 */
	public static String toISODateString(Date date, String format, TimeZone tz)
	{
		if (date != null)
		{
			if (format == null)
				format = FORMAT_DATE_ISO[0];
			if (tz == null)
				tz = TimeZone.getDefault();
			DateFormat f = new SimpleDateFormat(format);
			f.setTimeZone(tz);
			return f.format(date);
		}
		else
			return null;
	}
 
	/**
	 * This is equivalent to toISODateString(date, null, null).
	 * @param date the date object
	 * @return the iso-formatted date string
	 */
	public static String toISODateString(Date date)
	{
		return toISODateString(date, null, null);
	}
}