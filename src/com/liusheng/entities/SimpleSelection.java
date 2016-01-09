package com.liusheng.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="SIMPLE_SELECTION")
public class SimpleSelection {
	
	private int id;
	private String number;
	private String problem;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String answer;
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
	
	@Column(name="OPTION_A")
	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	@Column(name="OPTION_B")
	public String getOptionB() {
		return optionB;
	}
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	
	@Column(name="OPTION_C")
	public String getOptionC() {
		return optionC;
	}
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	
	@Column(name="OPTION_D")
	public String getOptionD() {
		return optionD;
	}
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	
	@Column(name="ANSWER")
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
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
	
	
	public SimpleSelection(String number,String problem, String optionA, String optionB,
			String optionC, String optionD, int checkStatus) {
		super();
		this.number = number;
		this.problem = problem;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.checkStatus = checkStatus;
	}
	public SimpleSelection() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SimpleSelection [id=" + id + ", problem=" + problem
				+ ", optionA=" + optionA + ", optionB=" + optionB
				+ ", optionC=" + optionC + ", optionD=" + optionD + ", answer="
				+ answer + ", keypointId=" + keypointId + ", checkStatus="
				+ checkStatus + "]";
	}
	
	
}
