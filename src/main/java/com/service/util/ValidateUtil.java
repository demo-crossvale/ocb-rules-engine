package com.service.util;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class ValidateUtil {
	/** Util for validating input to the rules engine
	 * @author ragrahari
	 **/

	static private DateTimeFormatter parser = ISODateTimeFormat.dateTimeNoMillis();

	public String parseDateToGetHourAndTime(String inputDate) {
		String parsedTime = new String();
		if(inputDate!=null) {
			try {
				double hour = parser.parseDateTime(inputDate).getHourOfDay()*100;//HH
				double minute = parser.parseDateTime(inputDate).getMinuteOfHour();//MM
				double second = parser.parseDateTime(inputDate).getSecondOfMinute();//SS

				Double hhmmss = hour+minute+(second/100);// HHMM.SS
				parsedTime = String.valueOf(hhmmss);
			}catch(IllegalArgumentException iae) {
				// This means that the input date has already been parsed. This is not an error.
				parsedTime = inputDate;
			}catch(Exception e) {
				System.out.println("Error while parsing Date to get Hour and Time");
				e.printStackTrace();
			}
		}

		return parsedTime;
	}

	public Double validatePercentRange(Double input) {
		// Check if value provided is in the range 0-100
		Double min= new Double(0);
		double max= new Double(100);
		Double output = null;
		if(input>=min && input <=max) {
			output=input;
		}else {
			System.out.println("Double input not in range for:"+input+"\nThis is an ERROR!");
			if(input<min) {
				System.out.println("Setting value to min:"+min);
				output=min;
			}
			else if(input>max) {
				System.out.println("Setting value to max:"+max);
				output=max;
			}
		}
		return output;
	}
}
