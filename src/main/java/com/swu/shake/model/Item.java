package com.swu.shake.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_Item")
public class Item {
	private int iid;
	@Column(nullable = false)
	private String iname;
	@Column(nullable = false)
	private double iprice;
	@Column(nullable = false)
	private Integer iNumber;
	@Column(nullable = false)
	private String idesc;
	@Column(nullable = false)
	private Date onshelfdate;
	@Column(nullable = false)
	private boolean isValid;

	@Column(nullable = false)
	private User user;
	@Column(nullable = false)
	private ItemType itemtype;

	private Set<ItemImage> itemImages = new HashSet<ItemImage>();
	private Set<Comment> comments = new HashSet<Comment>();

	public String getIdesc() {
		return idesc;
	}

	@OneToMany(mappedBy = "item", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	public Set<Comment> getComments() {
		return comments;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "iid", nullable = false)
	public int getIid() {
		return iid;
	}

	public void setIid(int iid) {
		this.iid = iid;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getIname() {
		return iname;
	}

	public Integer getiNumber() {
		return iNumber;
	}

	public double getIprice() {
		return iprice;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "iid")
	public Set<ItemImage> getItemImages() {
		return itemImages;
	}

	@ManyToOne
	@JoinColumn(name = "tid")
	public ItemType getItemtype() {
		return itemtype;
	}

	public Date getOnshelfdate() {
		return onshelfdate;
	}

	@ManyToOne
	@JoinColumn(name = "uid")
	public User getUser() {
		return user;
	}

	public void setIdesc(String idesc) {
		this.idesc = idesc;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}

	public void setiNumber(Integer iNumber) {
		this.iNumber = iNumber;
	}

	public void setIprice(double iprice) {
		this.iprice = iprice;
	}

	public void setItemImages(Set<ItemImage> itemImages) {
		this.itemImages = itemImages;
	}

	public void setItemtype(ItemType itemtype) {
		this.itemtype = itemtype;
	}

	public void setOnshelfdate(Date onshelfdate) {
		this.onshelfdate = onshelfdate;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
