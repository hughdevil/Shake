package com.swu.shake.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_ItemImage")
public class ItemImage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// “upload/pic/" + new Date().getTime(）+
	// this.getUploadFileName().HashCode();
	private int iiid;
	@Column(nullable = false)
	private String iiname;
	@Column(nullable = false)
	private Date uploadDate;

	public int getIiid() {
		return iiid;
	}

	public void setIiid(int iiid) {
		this.iiid = iiid;
	}

	public String getIiname() {
		return iiname;
	}

	public void setIiname(String iiname) {
		this.iiname = iiname;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
}
