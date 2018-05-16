package com.unitrust.timestamp3A.controller.comboFix;

import com.unitrust.timestamp3A.authorization.SystemLog;
import com.unitrust.timestamp3A.common.util.Common;
import com.unitrust.timestamp3A.common.util.ResultBean;
import com.unitrust.timestamp3A.service.consume.ConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hongwei on 2017/12/11.
 */
@Controller
public class ComboFixController {

    @Autowired
    private ConsumeService consumeService;

    @RequestMapping(value = "/comboFixSyns", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(module = "套餐修复", methods = "同步套餐用户和企业数据库和redis数据")
    public ResultBean comboFixSyns(String type, String operateType, String cusId
            , String bkey) {
        ResultBean resultBean = null;
        if (!Common.isEmpty(operateType) && "all".equals(operateType)) {
            resultBean = consumeService.comboFixSynsAll(bkey,type);
        } else if (!Common.isEmpty(operateType) && "one".equals(operateType) && !Common.isEmpty(cusId)) {
            resultBean = consumeService.comboFixSynsOne(bkey,type ,cusId );
        }else{
            resultBean = new ResultBean();
            resultBean.setMsg("传递参数不正确,请确认参数!!!!!");
        }

        return resultBean;
    }
}
