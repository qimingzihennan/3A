package com.unitrust.timestamp3A.redis.model;

import java.io.Serializable;
import java.util.List;

public class CCIVO implements Serializable {

	private static final long serialVersionUID = -1688325659298684957L;

	private String content; // 订单选用的套餐内容

	private String status; // 状态0可用、1不可用

	private String paidMode;// 计费模式 1次数、2天数、3次数+天数

	private String startTime; // 开始时间 yyyy-MM-dd

	private String endTime;// 结束时间 yyyy-MM-dd

	private String id; // 用户消费清单表id

	private List<OrderExtVO> ext;

	private String key; // 业务key

	private String cusIdOrPIN; // 客户id或者企业PIN码

	private String sdCode;// 企业PIN码对应的sd码

	private String type; // 个人or企业 person/enterprise

	public static final String CCIVO_type_person = "person";
	public static final String CCIVO_type_enterprise = "enterprise";

	private Double num; // 次数

	private Double plusOrMinus; // 相加 正数 10 相减 -10

	public double getPlusOrMinus() {
		if (plusOrMinus == null) {
			return 0.0;
		}
		return plusOrMinus;
	}

	public void setPlusOrMinus(double plusOrMinus) {
		this.plusOrMinus = plusOrMinus;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	public Double getNum() {
		if (num == null) {
			return 0.0;
		}
		return num;
	}

	public String getSdCode() {
		return sdCode;
	}

	public void setSdCode(String sdCode) {
		this.sdCode = sdCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCusIdOrPIN() {
		return cusIdOrPIN;
	}

	public void setCusIdOrPIN(String cusIdOrPIN) {
		this.cusIdOrPIN = cusIdOrPIN;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaidMode() {
		return paidMode;
	}

	public void setPaidMode(String paidMode) {
		this.paidMode = paidMode;
	}

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<OrderExtVO> getExt() {
		return ext;
	}

	public void setExt(List<OrderExtVO> ext) {
		this.ext = ext;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
