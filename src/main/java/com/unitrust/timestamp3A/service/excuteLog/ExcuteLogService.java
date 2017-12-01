package com.unitrust.timestamp3A.service.excuteLog;

import java.util.List;

import com.unitrust.timestamp3A.model.excuteLog.ExcuteLog;

public interface ExcuteLogService {
	public void add(ExcuteLog log);
	public List<ExcuteLog> getTaskById(Integer taskId);
}
