package com.unitrust.timestamp3A.controller.consume;

import com.unitrust.timestamp3A.authorization.SystemLog;
import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.common.util.Common;
import com.unitrust.timestamp3A.model.consume.CusConsumeInventory;
import com.unitrust.timestamp3A.model.consume.CusConsumeInventoryVO;
import com.unitrust.timestamp3A.service.consume.ConsumeService;
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
 * Created by hongwei on 2017/12/11.
 */
@Controller
@RequestMapping(value = "/consume/cci/enterprise")
public class CusConsumeInventoryEnterpriseController {
    @Autowired
    private ConsumeService consumeService;


    @RequestMapping(value = "list")
    @SystemLog(module = "用户订单消费管理", methods = "前往用户订单消费管理查看页面")
    public String listAll(String id, Model model) {
        model.addAttribute("iframeId", id);
        return "cci/enterprise/list";
    }

    @ResponseBody
    @RequestMapping("/query")
    @SystemLog(module = "用户订单消费管理", methods = "分页查看用户订单消费信息")
    public Map<String, Object> query(HttpServletRequest request, CusConsumeInventory cci) {
        Page<CusConsumeInventory> page = new Page<CusConsumeInventory>();
        Integer pageNum = request.getParameter("page") != null ? Integer.valueOf(request.getParameter("page")) : 1;
        Integer rows = request.getParameter("rows") != null ? Integer.valueOf(request.getParameter("rows")) : 10;
        page.setPageNum(pageNum);
        page.setPageSize(rows);
        Map paramMap = Common.ObjectToMap(cci);
        page.setSearchCondition(paramMap);
        List<CusConsumeInventoryVO> list = consumeService.queryEnterpriseCusConsumeInventory(page);
        Map<String, Object> rb = new HashMap<String, Object>();
        rb.put("total", page.getTotalRecords());
        rb.put("rows", list);
        return rb;
    }
}


