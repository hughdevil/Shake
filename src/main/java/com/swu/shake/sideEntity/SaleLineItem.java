package com.swu.shake.sideEntity;
public class SaleLineItem {
	
	private String iid;
	private Integer iNumber;
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public Integer getiNumber() {
		return iNumber;
	}
	public void setiNumber(Integer iNumber) {
		this.iNumber = iNumber;
	}

}

/*
 * 如何保证突然断电时用户的购物车记录得以保存。
 * 1、在数据库中创建购物车的表
 * 2、分间隔时间将其写入到数据库中。
 * 
 * 
 * */
