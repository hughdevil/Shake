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
import javax.persistence.Transient;

@Entity
@Table(name = "T_Comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cid;
	@Column(nullable = false)
	private String content;
	@Column(nullable = false)
	private Date markDate;
	@Column(nullable = false)
	private int likes;

	// 该字段仅方便前台展示只用，不用持久化
	@Transient
	private boolean isMyLike;

	@ManyToOne
	@JoinColumn(name = "iid")
	private Item item;

	@ManyToOne
	@JoinColumn(name = "uid")
	private User user;

	public boolean getIsMyLike() {
		return isMyLike;
	}

	public void setIsMyLike(boolean isMyLike) {
		this.isMyLike = isMyLike;
	}

	public User getUser() {
		return user;
	}

	// 新创建的评论点赞数默认是0
	public Comment() {
		this.likes = 0;
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

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}
}
