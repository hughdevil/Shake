package com.swu.shake.util;

/**
 * 
 * Ajax请求用
 * 
 * @author Shuai
 */
public class Status {

	/** 成功状态 */
	private boolean status;

	/** 状态描述 */
	private String desc;

	public Status() {

	}

	public Status(boolean status, String desc) {
		super();
		this.status = status;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
