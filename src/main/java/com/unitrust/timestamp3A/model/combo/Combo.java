package com.unitrust.timestamp3A.model.combo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 套餐表
 * 
 * @author tsa04
 *
 */
public class Combo implements Serializable {

	private static final long serialVersionUID = 5006876932431775755L;

	private Integer id; // 主键 套餐id


	private String bkey; // 业务模块key


	private String name;// 套餐名称

	private Integer number;// 次数

	private BigDecimal price;// 价格

	private String coType;// 类型1个人2企业

	private Integer days; // 有效天数

	private String content; // 套餐内容

	private String paidMode;// 计费模式 1次数、2天数、3次数+天数、4储存空间、5储存空间+天数

	public static final String paidMode_Num = "1";
	public static final String paidMode_time = "2";
	public static final String paidMode_NumAndTime = "3";
	public static final String paidMode_Space = "4";
	public static final String paidMode_SpaceAndTime = "5";
	private Integer status; // 状态0禁用1启用2取消
	
	private String businessName;//业务模块名
	
	private String b_Bkey;//业务模块KEY
	
	private Integer space;//储存空间
	
	
	
	public Integer getSpace() {
		return space;
	}

	public void setSpace(Integer space) {
		this.space = space;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}




	public String getBkey() {
		return bkey;
	}

	public void setBkey(String bkey) {
		this.bkey = bkey;
	}

	public String getB_Bkey() {
		return b_Bkey;
	}

	public void setB_Bkey(String b_Bkey) {
		this.b_Bkey = b_Bkey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCoType() {
		return coType;
	}

	public void setCoType(String coType) {
		this.coType = coType;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPaidMode() {
		return paidMode;
	}

	public void setPaidMode(String paidMode) {
		this.paidMode = paidMode;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Combo [id=" + id + ", bkey=" + bkey + ", name=" + name + ", number=" + number + ", price=" + price
				+ ", coType=" + coType + ", days=" + days + ", content=" + content + ", paidMode=" + paidMode
				+ ", status=" + status + ", businessName=" + businessName + ", b_Bkey=" + b_Bkey + "]";
	}


}
