package com.swu.shake.util;

public class MsgException extends Exception {
    private String resultMsg;
    public MsgException(String resultMsg){
    	this.resultMsg=resultMsg;
    }
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return resultMsg;
	}
}
