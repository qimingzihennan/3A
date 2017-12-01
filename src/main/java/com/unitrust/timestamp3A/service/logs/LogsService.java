package com.unitrust.timestamp3A.service.logs;

import java.util.List;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.model.logs.LogForm;

public interface LogsService {

	public List<LogForm> queryLogs(Page<LogForm> page);

}
