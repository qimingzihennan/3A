package com.unitrust.timestamp3A.model.logs;

import java.util.Date;

/**
 * Created by hongwei on 2017/10/17.
 */
public class APILogForm {
    private Integer id;
    private String module; // 操作模块

    private String methods; // 操作方法

    private String description; // 操作描述

    private String actionTime; // 操作用时

    private Date nowTime;// 操作时间

    private String userIP; // 用户ip

    public String getUserIP() {
        return userIP;
    }

    public void setUserIP(String userIP) {
        this.userIP = userIP;
    }

    private String server_ip; // 服务器ip地址

    private String methodName;// 代理的方法

    private String className;// AOP代理类的名字

    private String argString;// 请求参数信息

    private String exceptionMessage;// 异常信息

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServer_ip() {
        return server_ip;
    }

    public void setServer_ip(String server_ip) {
        this.server_ip = server_ip;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getArgString() {
        return argString;
    }

    public void setArgString(String argString) {
        this.argString = argString;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getMethods() {
        return methods;
    }

    public void setMethods(String methods) {
        this.methods = methods;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActionTime() {
        return actionTime;
    }

    public void setActionTime(String actionTime) {
        this.actionTime = actionTime;
    }

    public Date getNowTime() {
        return nowTime;
    }

    public void setNowTime(Date nowTime) {
        this.nowTime = nowTime;
    }
}