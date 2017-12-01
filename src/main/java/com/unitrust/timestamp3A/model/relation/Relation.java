package com.unitrust.timestamp3A.model.relation;

import java.io.Serializable;
import java.util.List;

import com.unitrust.timestamp3A.model.personal.Personal;
import com.unitrust.timestamp3A.model.enterprise.Enterprise;

public class Relation implements Serializable {

	/**
	 * 个人企业关系
	 */
	private static final long serialVersionUID = 5192046717545718685L;
	
	/**
	 * 表主键id
	 */
	private Integer id;
	
	/**
	 * 个人id
	 */
	private Integer personId;
	
	/**
	 * 企业id
	 */
	private Integer enterpriseId;
	
	/**
	 * 管理员标志位（0为普通员工，1为管理员）
	 */
	private String isAdmin;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}


	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	
	
	
}
