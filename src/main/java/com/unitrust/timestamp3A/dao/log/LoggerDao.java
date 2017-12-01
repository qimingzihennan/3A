package com.unitrust.timestamp3A.dao.log;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.model.logs.LogForm;

@Repository(value = "logger")
public interface LoggerDao {

	public void add(LogForm logForm);

	public List<LogForm> queryLogs(Page<LogForm> page);

}
