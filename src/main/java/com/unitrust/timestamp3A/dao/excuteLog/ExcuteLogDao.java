package com.unitrust.timestamp3A.dao.excuteLog;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.unitrust.timestamp3A.model.excuteLog.ExcuteLog;

@Repository(value = "elogDao")
public interface ExcuteLogDao {

	public void add(ExcuteLog log);
	
	public List<ExcuteLog> getTaskById(Integer taskId);
	
	
	
}
