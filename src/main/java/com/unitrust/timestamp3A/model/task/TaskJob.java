package com.unitrust.timestamp3A.model.task;

import java.io.Serializable;

public class TaskJob implements Serializable{
	private static final long serialVersionUID = -7970660230717422002L;
	
	private Integer jobId;
	
	private String jobName;
	
	private String jobStatus;
	
	private String jobDescribe;

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getJobDescribe() {
		return jobDescribe;
	}

	public void setJobDescribe(String jobDescribe) {
		this.jobDescribe = jobDescribe;
	}
	
	
}
