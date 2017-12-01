package com.unitrust.timestamp3A.service.task.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.dao.task.TaskJobDao;
import com.unitrust.timestamp3A.model.excuteLog.ExcuteLog;
import com.unitrust.timestamp3A.model.task.TaskJob;
import com.unitrust.timestamp3A.service.task.TaskJobService;

@Service("taskjobService")
public class TaskJobServiceImpl implements TaskJobService{
	
	@Resource
	private TaskJobDao taskJoDao;
	
	@Override
	public List<TaskJob> query(Page<TaskJob> page) {
		// TODO Auto-generated method stub
		List<TaskJob> list = taskJoDao.query(page);
		return list;
	}

	@Override
	public int modifyStatus(TaskJob job) {
		// TODO Auto-generated method stub
		return taskJoDao.modifyStatus(job);
	}

	@Override
	public List<ExcuteLog> queryExcute(Page<TaskJob> page) {
		// TODO Auto-generated method stub
		return taskJoDao.queryExcute(page);
	}

	@Override
	public TaskJob findTaskJobById(Integer taskId) {
		// TODO Auto-generated method stub
		return taskJoDao.findTaskJobById(taskId);
	}

}
