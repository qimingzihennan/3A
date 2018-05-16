package com.unitrust.timestamp3A.service.logs;

import java.util.List;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.model.logs.LogForm;
import com.unitrust.timestamp3A.vo.APILogFormVO;

public interface LogsService {

	public List<LogForm> queryLogs(Page<LogForm> page);

	public List<APILogFormVO> queryAPILogs(Page<APILogFormVO> page);


}
