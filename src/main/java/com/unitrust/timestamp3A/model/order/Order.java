package com.unitrust.timestamp3A.model.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户订单表
 * 
 * @author tsa04
 *
 */
public class Order implements Serializable {

	private static final long serialVersionUID = -7556164813884542517L;

	private Integer id; // 主键id

	private String orderNO; // 订单编号

	private String businessName; // 业务模块名称

	private Integer comboId; // 套餐ID

	private String comboName; // 套餐名称

	private BigDecimal price; // 实际价格

	private Integer number; // 次数

	private Integer days; // 天数

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startTime; // 开始时间

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endTime; // 结束时间

	private String content; // 套餐内容

	private Integer cusId; // 客户ID（个人/企业）

	private Date orderTime; // 下单时间

	private Date payTime; // 支付时间

	private String paidMode; // 计费模式 1次数、2天数、3次数+天数、4储存空间、5储存空间+天数

	private String status;// 状态0待缴费1已交费2取消

	private String orderType; // 类型1个人2企业
	
	private String Bkey; //业务key

	private Integer space;//储存空间
	
	public Integer getSpace() {
		return space;
	}

	public void setSpace(Integer space) {
		this.space = space;
	}

	public String getBkey() {
		return Bkey;
	}

	public void setBkey(String bkey) {
		Bkey = bkey;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNO() {
		return orderNO;
	}

	public void setOrderNO(String orderNO) {
		this.orderNO = orderNO;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public Integer getComboId() {
		return comboId;
	}

	public void setComboId(Integer comboId) {
		this.comboId = comboId;
	}

	public String getComboName() {
		return comboName;
	}

	public void setComboName(String comboName) {
		this.comboName = comboName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
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

	private String cName;

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

}
