package com.unitrust.timestamp3A.dao.task;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.model.enterprise.Enterprise;
import com.unitrust.timestamp3A.model.excuteLog.ExcuteLog;
import com.unitrust.timestamp3A.model.personal.Personal;
import com.unitrust.timestamp3A.model.task.TaskJob;

@Repository("taskjobDao")
public interface TaskJobDao {
	
	public List<TaskJob> query(Page<TaskJob> page);
	
	public int modifyStatus(TaskJob job);
	
	public List<ExcuteLog> queryExcute(Page<TaskJob> page);
	
	public TaskJob findTaskJobById(Integer taskId);
	
}
