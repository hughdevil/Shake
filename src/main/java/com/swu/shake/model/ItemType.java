package com.swu.shake.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "T_ItemType")
public class ItemType {
	private int tid;
	@Column(nullable = false)
	private String tname;
	@Column(nullable = false)
	private String tdesc;

	private Set<Item> set = new HashSet<Item>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	@OneToMany(mappedBy = "itemtype")
	@Cascade(CascadeType.ALL)
	public Set<Item> getSet() {
		return set;
	}

	public void setSet(Set<Item> set) {
		this.set = set;
	}

	public String getTdesc() {
		return tdesc;
	}

	public void setTdesc(String tdesc) {
		this.tdesc = tdesc;
	}

}
