package com.model;

import com.service.util.ValidateUtil;

public class PerformanceInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private static ValidateUtil vUtil = new ValidateUtil();

	private java.lang.Double cpu;
	private java.lang.Double memory;
	private java.lang.String dateTime;

	public PerformanceInfo(Double cpu, Double memory, String dateTime) {

		this.cpu = cpu;
		this.memory = memory;
		this.dateTime = dateTime;
	}

	public PerformanceInfo() {

	}

	public java.lang.Double getCpu() {
		return cpu;
	}

	public void setCpu(java.lang.Double cpu) {
		this.cpu = vUtil.validatePercentRange(cpu);
	}

	public java.lang.Double getMemory() {
		return memory;
	}

	public void setMemory(java.lang.Double memory) {
		this.memory = vUtil.validatePercentRange(memory);
	}

	public java.lang.String getDateTime() {
		return dateTime;
	}

	public void setDateTime(java.lang.String dateTime) {
		this.dateTime = vUtil.parseDateToGetHourAndTime(dateTime);
	}

}
