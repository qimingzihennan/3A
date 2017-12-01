package com.unitrust.timestamp3A.service.task;

import java.util.List;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.model.excuteLog.ExcuteLog;
import com.unitrust.timestamp3A.model.task.TaskJob;

public interface TaskJobService {
	
	public List<TaskJob> query(Page<TaskJob> page);
	
	public int modifyStatus(TaskJob job);
	
	public List<ExcuteLog> queryExcute(Page<TaskJob> page);
	
	public TaskJob findTaskJobById(Integer taskId);
}
