package com.liusheng.entities;

public class Result {

	private int code;//0 正常 1 异常
	private String msg;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Result(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
