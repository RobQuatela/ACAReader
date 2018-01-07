package com.accountomation.acareader.model;

public class ACAStatus {

	private int id;
	private Employee emp;
	private DateMap date;
	private String series1;
	private String empShare;
	private String series2;
	private String enrolledInSelf;
	private String entity;
	
	public ACAStatus() {
		
	}
	
	public ACAStatus(Employee emp, DateMap date, String series1, 
			String empShare, String series2, String enrolledInSelf, String entity) {
		this.emp = emp;
		this.date = date;
		this.series1 = series1;
		this.series2 = series2;
		this.empShare = empShare;
		this.enrolledInSelf = enrolledInSelf;
		this.entity = entity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public DateMap getDate() {
		return date;
	}

	public void setDate(DateMap date) {
		this.date = date;
	}

	public String getSeries1() {
		return series1;
	}

	public void setSeries1(String series1) {
		this.series1 = series1;
	}

	public String getEmpShare() {
		return empShare;
	}

	public void setEmpShare(String empShare) {
		this.empShare = empShare;
	}

	public String getSeries2() {
		return series2;
	}

	public void setSeries2(String series2) {
		this.series2 = series2;
	}

	public String getEnrolledInSelf() {
		return enrolledInSelf;
	}

	public void setEnrolledInSelf(String enrolledInSelf) {
		this.enrolledInSelf = enrolledInSelf;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}
}
