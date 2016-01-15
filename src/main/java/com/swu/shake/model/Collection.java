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
@Table(name = "T_Collection")
public class Collection {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int colid;
	@Column(nullable = false)
	private Date markDate;

	@ManyToOne
	@JoinColumn(name = "iid")
	private Item item;

	@ManyToOne
	@JoinColumn(name = "uid")
	private User user;
	
	public int getColid() {
		return colid;
	}

	public void setColid(int colid) {
		this.colid = colid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Date getMarkDate() {
		return markDate;
	}

	public void setMarkDate(Date markDate) {
		this.markDate = markDate;
	}
}
