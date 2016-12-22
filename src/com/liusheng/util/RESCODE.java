package com.liusheng.util;

public enum RESCODE {

	SUCCESS(0, "成功"), 
	WRONG_PARAM(1, "参数错误"), 
	NOT_FOUND(2, "无该条记录"),
	PSW_ERROR(3, "密码错误"), 
	UPDATE_ERROR(4, "更新数据错误"), 
	CREATE_ERROR(5, "存储数据错误"), 
	DATE_FORMAT_ERROR(6, "日期格式错误"),
	DELETE_ERROR(7, "删除错误"), 
	DUPLICATED_ERROR(8,"重复数据"),
	UPLOAD_FILE_TYPE_ERROR(9, "上传文件类型错误"),
	EMPTY_EXCEL(10, "EXCEL表格为空"),
	EXCEL_COLUMN_ERROR(11,"请按格式填写excel中的每一列"),
	STATE_ERROR(12, "对象状态错误"),
	JSON_ERROR(13, "Json解析出错"),
	NO_PARAM(14, "参数为空"),
	FILE_NOT_FOUND(16, "文件不存在"),
	EMAIL_SEND_ERROR(17, "邮件发送错误"),
	USERNAME_ERROR(18,"用户名错误"),
	UNKNOW_ERROR(19, "系统发生未知错误");

	// 定义私有变量
	private int nCode;

	private String nMsg;

	// 构造函数，枚举类型只能为私有
	private RESCODE(int _nCode, String _nMsg) {

		this.nCode = _nCode;
		this.nMsg = _nMsg;
	}

	public String getMsg() {
		return nMsg;
	}
	public int getCode() {
		return nCode;
	}

	@Override
	public String toString() {
		return String.valueOf(this.nCode);
	}
}
