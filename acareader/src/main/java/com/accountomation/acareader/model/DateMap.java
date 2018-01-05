package com.accountomation.acareader.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "datemap")
public class DateMap {

	private int id;
	List<ACAStatus> statuses = new ArrayList<>();
	
	public DateMap() {
		
	}
	
	public DateMap(int id) {
		this.id = id;
	}

	@Id
	@Column(name = "date_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "date", cascade = CascadeType.ALL)
	public List<ACAStatus> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<ACAStatus> statuses) {
		this.statuses = statuses;
	}
}
