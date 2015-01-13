package com.data.main.dao;

import java.sql.Timestamp;

public class CPUStatsDao {

	private String vm_name;
	private double cpu_percentage_usage;
	private Timestamp time_stamp;
	
	public String getVm_name() {
		return vm_name;
	}
	public void setVm_name(String vm_name) {
		this.vm_name = vm_name;
	}
	public double getCpu_percentage_usage() {
		return cpu_percentage_usage;
	}
	public void setCpu_percentage_usage(double cpu_percentage_usage) {
		this.cpu_percentage_usage = cpu_percentage_usage;
	}
	public Timestamp getTime_stamp() {
		return time_stamp;
	}
	public void setTime_stamp(Timestamp time_stamp) {
		this.time_stamp = time_stamp;
	}
	
	
	
	
	
}
