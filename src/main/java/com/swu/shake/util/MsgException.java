package com.swu.shake.util;

public class MsgException extends Exception {
	private static final long serialVersionUID = -6040519534274104282L;
	private String resultMsg;

	public MsgException(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	@Override
	public String toString() {
		return resultMsg;
	}

}
