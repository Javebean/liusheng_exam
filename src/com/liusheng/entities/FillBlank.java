package com.liusheng.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="FILL_BLANK")
public class FillBlank {
	
	private int id;
	private String number;
	private String problem;
	private String keypointId;
	private int checkStatus;
	
	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@GeneratedValue(generator = "generator")
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="PROBLEM")
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	
	@Column(name="KEYPOINT_ID")
	public String getKeypointId() {
		return keypointId;
	}
	public void setKeypointId(String keypointId) {
		this.keypointId = keypointId;
	}
	
	@Column(name="CHECK_STATUS")
	public int getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(int checkStatus) {
		this.checkStatus = checkStatus;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
	public FillBlank(String number,String problem, int checkStatus) {
		super();
		this.number = number;
		this.problem = problem;
		this.checkStatus = checkStatus;
	}
	public FillBlank() {
		super();
	}
	

	
}
