package com.model;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class PerformanceInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private java.lang.Float cpu;
	private java.lang.Float memory;
	private java.lang.String dateTime;

	public PerformanceInfo(Float cpu, Float memory, String dateTime) {

		this.cpu = cpu;
		this.memory = memory;
		this.dateTime = dateTime;
	}

	public PerformanceInfo() {

	}

	public java.lang.Float getCpu() {
		return cpu;
	}

	public void setCpu(java.lang.Float cpu) {
		this.cpu = cpu;
	}

	public java.lang.Float getMemory() {
		return memory;
	}

	public void setMemory(java.lang.Float memory) {
		this.memory = memory;
	}

	public java.lang.String getDateTime() {
		return dateTime;
	}

	public void setDateTime(java.lang.String dateTime) {
		DateTimeFormatter parser = ISODateTimeFormat.dateTimeNoMillis();
		try {
			this.dateTime = String.valueOf(parser.parseDateTime(dateTime).getHourOfDay()*100);
		}catch(Exception e) {
			this.dateTime = dateTime;
		}
	}

}
