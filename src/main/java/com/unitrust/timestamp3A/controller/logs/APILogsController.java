package com.unitrust.timestamp3A.controller.logs;

import com.unitrust.timestamp3A.authorization.SystemLog;
import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.common.util.Common;
import com.unitrust.timestamp3A.model.logs.APILogForm;
import com.unitrust.timestamp3A.model.logs.LogForm;
import com.unitrust.timestamp3A.service.logs.LogsService;
import com.unitrust.timestamp3A.vo.APILogFormVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hongwei on 2017/10/17.
 * 3A系统异常信息显示
 */
@Controller
@RequestMapping("/apilogs")
public class APILogsController {

    @Autowired
    private LogsService logsService;

    /**
     * 列表显示
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "list")
    public String listAll(String id, Model model) {
        model.addAttribute("iframeId", id);
        return "logs/apiList";
    }

    @ResponseBody
    @RequestMapping("/query")
    @SystemLog(module = "客户操作日志", methods = "分页查看客户操作日志信息")
    public Map<String, Object> query(HttpServletRequest request, APILogFormVO logForm) {
        Page<APILogFormVO> page = new Page<APILogFormVO>();
        Integer pageNum = request.getParameter("page") != null ? Integer.valueOf(request.getParameter("page")) : 1;
        Integer rows = request.getParameter("rows") != null ? Integer.valueOf(request.getParameter("rows")) : 10;
        page.setPageNum(pageNum);
        page.setPageSize(rows);
        Map paramMap = Common.ObjectToMap(logForm);
        page.setSearchCondition(paramMap);
        List<APILogFormVO> list = logsService.queryAPILogs(page);
        Map<String, Object> rb = new HashMap<String, Object>();
        rb.put("total", page.getTotalRecords());
        rb.put("rows", list);
        return rb;
    }
}