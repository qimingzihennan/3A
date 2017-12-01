package com.unitrust.timestamp3A.service.excuteLog.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unitrust.timestamp3A.dao.excuteLog.ExcuteLogDao;
import com.unitrust.timestamp3A.model.excuteLog.ExcuteLog;
import com.unitrust.timestamp3A.service.excuteLog.ExcuteLogService;

@Service(value = "elService")
public class ExcuteLogServiceImpl implements ExcuteLogService{
	
	@Autowired
	private ExcuteLogDao elDao;
	
	@Override
	public void add(ExcuteLog log) {
		// TODO Auto-generated method stub
		elDao.add(log);
	}

	@Override
	public List<ExcuteLog> getTaskById(Integer taskId) {
		// TODO Auto-generated method stub
		return elDao.getTaskById(taskId);
	}

}
