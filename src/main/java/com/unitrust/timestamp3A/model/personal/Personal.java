package com.unitrust.timestamp3A.model.personal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.unitrust.timestamp3A.model.relation.Relation;

/**
 * 个人用户管理
 * 
 * @author tsa04
 *
 */
public class Personal implements Serializable {
	private static final long serialVersionUID = 5093279699864899166L;
	/**
	 * 用户id
	 */
	private Integer id;

	
	/**
	 * 用户名称
	 */
	private String customerName;


	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 用户邮箱地址
	 */
	private String email;

	/**
	 * 身份证号
	 */
	private String idCard;
	/**
	 * 地址
	 */

	private String address;
	/**
	 * 认证状态 状态(0未认证 1认证成功 2认证驳回 3待审核)
	 */
	private Integer status;
	
	private Relation relation;
	/**
	 * 删除状态0正常 1删除
	 */
	private Integer delStatus;
	/**
	 * 数据生成时间
	 */
	private Date createTime;
	/**
	 * 邮政编码
	 * @return
	 */
	private String postcode;
	/**
	 * 数据来源（key）
	 * @return
	 */
	private String bkey;


	/**
	 * 认证来源
	 * 1芝麻认证 2银行认证 3人工审核
	 */
	private String approvalFrom;

	/**
	 * 认证完成时间
	 */
	private Date appOperateTime;

	/**
	 * 认证时间
	 */
	private Date approvalTime;

	
	private String startTime;
	private String endTime;
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Date getApprovalTime() {
		return approvalTime;
	}

	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	

	public String getBkey() {
		return bkey;
	}

	public void setBkey(String bkey) {
		this.bkey = bkey;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	
	

	public Integer getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	



	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public void setStatus(Integer status) {
		this.status = status;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}


	public String getApprovalFrom() {
		return approvalFrom;
	}

	public void setApprovalFrom(String approvalFrom) {
		this.approvalFrom = approvalFrom;
	}

	public Date getAppOperateTime() {
		return appOperateTime;
	}

	public void setAppOperateTime(Date appOperateTime) {
		this.appOperateTime = appOperateTime;
	}
}
