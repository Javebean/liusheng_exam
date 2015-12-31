package com.liusheng.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="KEYWORDS")
public class Keypoints {
	private int id;
	private String keypoint;
	
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
	
	@Column(name="KEYPOINT")
	public String getKeypoint() {
		return keypoint;
	}
	public void setKeypoint(String keypoint) {
		this.keypoint = keypoint;
	}
	public Keypoints(String keypoint) {
		super();
		this.keypoint = keypoint;
	}
	public Keypoints() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
