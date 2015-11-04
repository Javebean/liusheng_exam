package com.liusheng.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="FILLBLANK_ANSWER")
public class fillBlankAnswer {

	private int id;
	private String answer;
	private int fillBlackId;
	private int answerOrder;
	
	@Id
	@GenericGenerator(name="generator",strategy="native")
	@GeneratedValue(generator="generator")
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="ANSWER")
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	@Column(name="FILLBLACK_ID")
	public int getFillBlackId() {
		return fillBlackId;
	}
	public void setFillBlackId(int fillBlackId) {
		this.fillBlackId = fillBlackId;
	}
	@Column(name="ANSWER_ORDER")
	public int getAnswerOrder() {
		return answerOrder;
	}
	public void setAnswerOrder(int answerOrder) {
		this.answerOrder = answerOrder;
	}
	
	
}
