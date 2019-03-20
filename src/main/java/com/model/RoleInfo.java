package com.model;

import java.util.ArrayList;

public class RoleInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private java.lang.String role;
	//private ArrayList<com.model.PerformanceInfo> performanceInfo;
	com.model.PerformanceInfo performanceInfo;
	private java.lang.Integer roleTargetCapacity;
	
	public RoleInfo(String role, PerformanceInfo performanceInfo, 
			Integer intendedTargetCapacity) {//ArrayList<PerformanceInfo> performanceInfo) {
		this.role = role;
		this.performanceInfo = performanceInfo;
		this.roleTargetCapacity = intendedTargetCapacity;
	}
	
	public RoleInfo() {
		//performanceInfo = new ArrayList<PerformanceInfo>();
		performanceInfo = new PerformanceInfo();
	}
	
	public java.lang.String getRole() {
		return role;
	}
	public void setRole(java.lang.String role) {
		this.role = role;
	}

	public com.model.PerformanceInfo getPerformanceInfo() {
		return performanceInfo;
	}

	public void setPerformanceInfo(com.model.PerformanceInfo performanceInfo) {
		this.performanceInfo = performanceInfo;
	}

	public java.lang.Integer getRoleTargetCapacity() {
		return roleTargetCapacity;
	}

	public void setRoleTargetCapacity(java.lang.Integer roleTargetCapacity) {
		this.roleTargetCapacity = roleTargetCapacity;
	}
	
//	public ArrayList<com.model.PerformanceInfo> getPerformanceInfo() {
//		return performanceInfo;
//	}
//	public void setPerformanceInfo(ArrayList<com.model.PerformanceInfo> performanceInfo) {
//		this.performanceInfo = performanceInfo;
//	}
//	public void addPerformanceInfo(com.model.PerformanceInfo performanceInfo) {
//		this.performanceInfo.add(performanceInfo);
//	}
	
}
