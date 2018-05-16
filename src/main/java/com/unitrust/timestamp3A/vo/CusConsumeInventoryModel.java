package com.unitrust.timestamp3A.vo;

/**
 * Created by hongwei on 2017/12/11.
 */
public class CusConsumeInventoryModel {
    private String paidMode; // 计费模式 1次数、2天数、3次数+天数、4储存空间、5储存空间+天数
    private String sdCode;// 企业PIN码对应的sd码
    private Integer id; // 主键
    private String status; // 状态0正在使用、1暂停、2待使用、3完成消费
    private String startTime;
    private String endTime;
    private String num;
    private String content;
    private Integer cusId; // 客户ID(个人/用户)
    private String Bkey; // 业务模块key
    private Integer orderId; // 订单id
    private String orderType; // 类型1个人2企业
    private Integer residueNumber; // 剩余次数

    public Integer getResidueNumber() {
        return residueNumber;
    }

    public void setResidueNumber(Integer residueNumber) {
        this.residueNumber = residueNumber;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getBkey() {
        return Bkey;
    }

    public void setBkey(String bkey) {
        Bkey = bkey;
    }

    public String getPaidMode() {
        return paidMode;
    }

    public void setPaidMode(String paidMode) {
        this.paidMode = paidMode;
    }

    public String getSdCode() {
        return sdCode;
    }

    public void setSdCode(String sdCode) {
        this.sdCode = sdCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
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
}
