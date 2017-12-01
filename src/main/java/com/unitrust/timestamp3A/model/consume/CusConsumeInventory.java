package com.unitrust.timestamp3A.model.consume;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户消费清单表
 * 
 * @author tsa04
 *
 */
public class CusConsumeInventory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1549250761020647023L;

	private Integer id; // 主键

	private String businessName; // 业务模块名称

	private String Bkey; // 业务模块key

	private Integer cusId; // 客户ID(个人/用户)

	private Integer totalNumber; // 总次数

	private Integer residueNumber; // 剩余次数

	private Date startTime; // 开始时间

	private Date endTime; // 结束时间

	private String paidMode; // 计费模式 1次数、2天数、3次数+天数、4储存空间、5储存空间+天数

	private String status; // 状态0正在使用、1暂停、2待使用、3完成消费

	public static final String CusConsumeInventory_status_OK = "0";
	public static final String CusConsumeInventory_status_NotOK = "1";
	public static final String CusConsumeInventory_status_Ready = "2";
	public static final String CusConsumeInventory_status_Over = "3";

	private Integer orderId; // 订单id

	private String orderType; // 类型1个人2企业
	public static final String CusConsumeInventory_orderType_person = "1";
	public static final String CusConsumeInventory_orderType_enterprise = "2";

	private String PIN; // 企业PIN码

	private String sdCode;// 企业PIN码对应的sd码

	private String orderNO;

	private String enterpriseName;
	private String customerName;

	private Date createTime; // 生成时间

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getOrderNO() {
		return orderNO;
	}

	public void setOrderNO(String orderNO) {
		this.orderNO = orderNO;
	}

	public String getSdCode() {
		return sdCode;
	}

	public void setSdCode(String sdCode) {
		this.sdCode = sdCode;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getPIN() {
		return PIN;
	}

	public void setPIN(String pIN) {
		PIN = pIN;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBkey() {
		return Bkey;
	}

	public void setBkey(String bkey) {
		Bkey = bkey;
	}

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Integer getResidueNumber() {
		return residueNumber;
	}

	public void setResidueNumber(Integer residueNumber) {
		this.residueNumber = residueNumber;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getPaidMode() {
		return paidMode;
	}

	public void setPaidMode(String paidMode) {
		this.paidMode = paidMode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

}
