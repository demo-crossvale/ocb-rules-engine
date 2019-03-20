package com.model;

public class PerformanceInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private java.lang.Integer cpu;
	private java.lang.Integer memory;
	private java.lang.String dateTime;
	
	public PerformanceInfo(Integer cpu, Integer memory, String dateTime) {

		this.cpu = cpu;
		this.memory = memory;
		this.dateTime = dateTime;
	}

	public PerformanceInfo() {
		
	}

	public java.lang.Integer getCpu() {
		return cpu;
	}

	public void setCpu(java.lang.Integer cpu) {
		this.cpu = cpu;
	}

	public java.lang.Integer getMemory() {
		return memory;
	}

	public void setMemory(java.lang.Integer memory) {
		this.memory = memory;
	}

	public java.lang.String getDateTime() {
		return dateTime;
	}

	public void setDateTime(java.lang.String dateTime) {
		this.dateTime = dateTime;
	}
	
}
