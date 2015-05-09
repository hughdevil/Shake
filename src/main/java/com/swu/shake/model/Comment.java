package com.swu.shake.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_Comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// UUID
	private int cid;
	@Column(nullable = false)
	private String content;
	@Column(nullable = false)
	private Date markDate;

	@ManyToOne()
	@JoinColumn(name = "iid")
	private Item item;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getMarkDate() {
		return markDate;
	}

	public void setMarkDate(Date markDate) {
		this.markDate = markDate;
	}
}
