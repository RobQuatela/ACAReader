package com.accountomation.acareader.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	private String id;
	private String name;
	private List<ACAStatus> statuses = new ArrayList<>();
	
	public Employee() {
		
	}
	
	public Employee(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Id
	@Column(name = "emp_id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@Column(name = "emp_name")
	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	public List<ACAStatus> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<ACAStatus> statuses) {
		this.statuses = statuses;
	}
}
