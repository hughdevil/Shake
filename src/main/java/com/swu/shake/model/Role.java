package com.swu.shake.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_Role")
public class Role {
	@Id
	// uuid
	private String rid;
	private String rname;
	private String rdesc;
	private int rlevel;

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public int getRlevel() {
		return rlevel;
	}

	public void setRlevel(int rlevel) {
		this.rlevel = rlevel;
	}

	public String getRdesc() {
		return rdesc;
	}

	public void setRdesc(String rdesc) {
		this.rdesc = rdesc;
	}
}
