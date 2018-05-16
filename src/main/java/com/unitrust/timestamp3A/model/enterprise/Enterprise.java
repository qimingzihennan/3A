package com.unitrust.timestamp3A.model.enterprise;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 企业实体类
 */

public class Enterprise implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4785293330370487745L;

	/**
	 * 企业id
	 */
	private Integer enterpriseId;

	/**
	 * 企业名称
	 */
	private String enterpriseName;

	/**
	 * 企业营业执照登记号
	 */
	private String businessNumber;

	/**
	 * 企业地址
	 */
	private String enterpriseAddress;

	/**
	 * 法人姓名
	 */
	private String adminName;

	/**
	 * 法人身份证号
	 */
	private String adminIdCard;

	/**
	 * 企业认证状态( 1认证成功 2认证驳回 3待审核)
	 */
	private String status;

	/**
	 * 企业PIN码
	 */
	private String PIN;

	/**
	 * 企业SD码
	 */
	private String SD;

	/**
	 * 删除标志位 0代表未删除 1代表已删除
	 */
	private String delFlag;

	/**
	 * 企业固定电话
	 */
	private String telephone;

	/**
	 * 企业邮箱
	 */
	private String email;

	/**
	 * 邮编
	 */
	private String postCode;

	/**
	 * 统一社会信用代码
	 */
	private String uscCode;

	private List relation;// 使用一个List存放多个个人-企业关系

	private Date createTime;

	private String orgCertificate; // 组织机构代码证

	private String trCertificate;// 税务登记证

	private String agentName;// 代理人姓名
	private String agentIdCard;// 代理人身份证号
	private String agentMobile;// 代理人手机号
	private Date approvalTime;//审批时间
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

	private String Bkey;

	public String getBkey() {
		return Bkey;
	}

	public void setBkey(String bkey) {
		Bkey = bkey;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOrgCertificate() {
		return orgCertificate;
	}

	public void setOrgCertificate(String orgCertificate) {
		this.orgCertificate = orgCertificate;
	}

	public String getTrCertificate() {
		return trCertificate;
	}

	public void setTrCertificate(String trCertificate) {
		this.trCertificate = trCertificate;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentIdCard() {
		return agentIdCard;
	}

	public void setAgentIdCard(String agentIdCard) {
		this.agentIdCard = agentIdCard;
	}

	public String getAgentMobile() {
		return agentMobile;
	}

	public void setAgentMobile(String agentMobile) {
		this.agentMobile = agentMobile;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	public String getEnterpriseAddress() {
		return enterpriseAddress;
	}

	public void setEnterpriseAddress(String enterpriseAddress) {
		this.enterpriseAddress = enterpriseAddress;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminIdCard() {
		return adminIdCard;
	}

	public void setAdminIdCard(String adminIdCard) {
		this.adminIdCard = adminIdCard;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPIN() {
		return PIN;
	}

	public void setPIN(String pIN) {
		PIN = pIN;
	}

	public String getSD() {
		return SD;
	}

	public void setSD(String sD) {
		SD = sD;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getUscCode() {
		return uscCode;
	}

	public void setUscCode(String uscCode) {
		this.uscCode = uscCode;
	}

	public List getRelation() {
		return relation;
	}

	public void setRelation(List relation) {
		this.relation = relation;
	}

}
