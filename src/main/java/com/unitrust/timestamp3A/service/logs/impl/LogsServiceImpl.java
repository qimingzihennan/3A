package com.unitrust.timestamp3A.service.logs.impl;

import java.util.List;

import com.unitrust.timestamp3A.vo.APILogFormVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.dao.log.LoggerDao;
import com.unitrust.timestamp3A.model.logs.LogForm;
import com.unitrust.timestamp3A.service.logs.LogsService;

@Service(value = "logsService")
public class LogsServiceImpl implements LogsService {

	@Autowired
	private LoggerDao logger;

	@Override
	public List<LogForm> queryLogs(Page<LogForm> page) {
		// TODO Auto-generated method stub
		return logger.queryLogs(page);
	}

	@Override
	public List<APILogFormVO> queryAPILogs(Page<APILogFormVO> page) {
		return logger.queryAPILogs(page);
	}

}
