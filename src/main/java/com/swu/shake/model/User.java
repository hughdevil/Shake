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

import org.hibernate.annotations.GeneratorType;

//实体类，注解
@Entity
@Table(name = "T_User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "uid", nullable = false)
	// uuid
	private int uid;
	/** 姓名 */
	@Column(name = "name", nullable = false)
	private String name;
	/** 密码 */
	@Column(name = "password", nullable = false)
	private String password;
	/** 性别 */
	@Column(name = "sex", nullable = false)
	private boolean sex;
	/** 电话 */
	@Column(name = "phone", nullable = true)
	private Integer phone;
	/** OICQ */
	@Column(name = "QQ", nullable = true)
	private Integer QQ;
	/** 联系地址 */
	@Column(name = "addr", nullable = true)
	private String addr;
	/** 邮箱 */
	@Column(name = "email", nullable = true)
	private String email;
	@Column(name = "regDate", nullable = false)
	private Date regDate;
	@Column(name = "IP", nullable = false)
	private String IP;

	@ManyToOne
	@JoinColumn(name = "rid")
	private Role role;

	// 设置唯一主键，自增长

	public String getName() {
		return name;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public Integer getQQ() {
		return QQ;
	}

	public void setQQ(Integer qQ) {
		QQ = qQ;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

}
