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
import javax.persistence.OneToOne;
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
	private int iNumber;
	@Column(nullable = false)
	private String idesc;
	@Column(nullable = false)
	private Date onshelfdate;
	@Column(nullable = false)
	private boolean isValid;
	
	//如果用ItemImage的话，每查一个都会查询一下itemimage表(每次查询User这个没办法，因为以后可能扩展)，用string直接解决,
	@Column(nullable = false)
	private String postImage;

	@Column(nullable = false)
	private User user;
	@Column(nullable = false)
	private ItemType itemtype;

	private Set<ItemImage> itemImages = new HashSet<ItemImage>();
	private Set<Comment> comments = new HashSet<Comment>();

	public Item() {

	}

	public Item(int iid, String iname, double iprice, boolean isvalid,
			Date onshelfdate,String postImage, int uid,String name ) {
		this.iid = iid;
		this.iname = iname;
		this.iprice = iprice;
		this.isValid = isvalid;
		this.onshelfdate = onshelfdate;
		this.user = new User();
		user.setUid(uid);
		user.setName(name);
		this.postImage = postImage;
	}

	@OneToMany(mappedBy = "item", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	public Set<Comment> getComments() {
		return comments;
	}

	public String getIdesc() {
		return idesc;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "iid", nullable = false)
	public int getIid() {
		return iid;
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

	
	public String getPostImage() {
		return postImage;
	}

	@ManyToOne
	@JoinColumn(name = "uid")
	public User getUser() {
		return user;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public void setIdesc(String idesc) {
		this.idesc = idesc;
	}

	public void setIid(int iid) {
		this.iid = iid;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}

	public void setiNumber(int iNumber) {
		this.iNumber = iNumber;
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

	public void setPostImage(String postImage) {
		this.postImage = postImage;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
}
