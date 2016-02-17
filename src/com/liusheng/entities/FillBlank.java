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
	private int fillNums;//该题目有多少个空
	
	private String keypoint;
	
	public String getKeypoint() {
		return keypoint;
	}
	
	public void setKeypoint(String keypoint) {
		this.keypoint = keypoint;
	}
	
	
	
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
	
	
	public int getFillNums() {
		return fillNums;
	}
	
	public void setFillNums(int fillNums) {
		this.fillNums = fillNums;
	}
	

	
	
	public FillBlank(String number, String problem, String keypointId,
			int checkStatus, int fillNums) {
		super();
		this.number = number;
		this.problem = problem;
		this.keypointId = keypointId;
		this.checkStatus = checkStatus;
		this.fillNums = fillNums;
	}
	public FillBlank() {
		super();
	}
	

	
}
