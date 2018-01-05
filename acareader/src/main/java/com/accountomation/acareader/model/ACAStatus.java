package com.accountomation.acareader.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "acastatus")
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(targetEntity = Employee.class)
	@JoinColumn(name = "status_emp", nullable = false,
		foreignKey = @ForeignKey(name = "fk_status_emp"))
	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	@ManyToOne(targetEntity = DateMap.class)
	@JoinColumn(name = "status_date", nullable = false,
		foreignKey = @ForeignKey(name = "fk_status_date"))
	public DateMap getDate() {
		return date;
	}

	public void setDate(DateMap date) {
		this.date = date;
	}

	@Column(name = "status_series1")
	public String getSeries1() {
		return series1;
	}

	public void setSeries1(String series1) {
		this.series1 = series1;
	}

	@Column(name = "status_empshare")
	public String getEmpShare() {
		return empShare;
	}

	public void setEmpShare(String empShare) {
		this.empShare = empShare;
	}

	@Column(name = "status_series2")
	public String getSeries2() {
		return series2;
	}

	public void setSeries2(String series2) {
		this.series2 = series2;
	}

	@Column(name = "status_enrolled")
	public String getEnrolledInSelf() {
		return enrolledInSelf;
	}

	public void setEnrolledInSelf(String enrolledInSelf) {
		this.enrolledInSelf = enrolledInSelf;
	}

	@Column(name = "status_entity")
	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}
}
