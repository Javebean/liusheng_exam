package com.liusheng.entities;

public class CommonResult {
	private Result result;
	private Object data;
	
	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}



	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}


	public CommonResult(Result result, Object data) {
		super();
		this.result = result;
		this.data = data;
	}

	public CommonResult() {
		super();
		// TODO Auto-generated constructor stub

	}
}
