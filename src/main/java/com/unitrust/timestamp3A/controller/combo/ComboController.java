package com.unitrust.timestamp3A.controller.combo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitrust.timestamp3A.authorization.SystemLog;
import com.unitrust.timestamp3A.common.DataGridVO;
import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.common.util.Common;
import com.unitrust.timestamp3A.model.business.Business;
import com.unitrust.timestamp3A.model.business.BusinessExtendField;
import com.unitrust.timestamp3A.model.combo.Combo;
import com.unitrust.timestamp3A.model.combo.ComboExtendField;
import com.unitrust.timestamp3A.model.logs.LogForm;
import com.unitrust.timestamp3A.model.personal.Personal;
import com.unitrust.timestamp3A.service.business.BusinessExtendFieldService;
import com.unitrust.timestamp3A.service.business.BusinessService;
import com.unitrust.timestamp3A.service.combo.ComboExtendFieldService;
import com.unitrust.timestamp3A.service.combo.ComboService;
import com.unitrust.timestamp3A.service.register.RegisterService;


@Controller
@RequestMapping("/combo")
public class ComboController {
	@Resource
	private ComboService comboService;
	
	@Resource
	private BusinessService businessService;
	
	@Resource
	private BusinessExtendFieldService businessExtendFieldService;
	
	@Resource
	private ComboExtendFieldService comboExtendFieldService;
	
	
	
	@RequestMapping("/findByBkey")
	@ResponseBody
	public Map<String, Object> findByBkey(String bkey,Model model){
		List<BusinessExtendField> list = businessExtendFieldService.findByBkey(bkey);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bef", list);
		return map;
	}
	@RequestMapping("/list")
	@SystemLog(module = "套餐管理", methods = "前往套餐管理页面")
	public String list(String id,Model model){
		model.addAttribute("iframeId", id);
		return "combo/list";
	}
	
	@RequestMapping("/toAdd")
	@SystemLog(module = "套餐管理", methods = "前往套餐添加页面")
	public String toAdd(String id,Model model) {
		model.addAttribute("iframeId", id);
		List<Business> list = businessService.query();
		model.addAttribute("business", list);
		return "combo/addCombo";
	}
	
	@RequestMapping("/toEdit")
	@SystemLog(module = "套餐管理", methods = "前往套餐修改页面")
	public String toEdit(String iframeId,Model model,Integer id) {
		model.addAttribute("iframeId", iframeId);
		List<Business> list = businessService.query();
		Combo combo = comboService.getComboById(id);
		List<ComboExtendField> lists = comboExtendFieldService.findById(id);
		model.addAttribute("combo", combo);
		model.addAttribute("business", list);
		model.addAttribute("cef",lists);
		return "combo/editCombo";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/query")
	@ResponseBody
	@SystemLog(module = "套餐管理", methods = "分页查询套餐信息")
	public Map<String, Object> query(Combo combo,HttpServletRequest request){
		Page<Combo> page = new Page<Combo>();
		Integer pageNum = request.getParameter("page") != null ? Integer.valueOf(request.getParameter("page")) : 1;
		Integer rows = request.getParameter("rows") != null ? Integer.valueOf(request.getParameter("rows")) : 10;
		page.setPageNum(pageNum);
		page.setPageSize(rows);
		Map paramMap = Common.ObjectToMap(combo);
		page.setSearchCondition(paramMap);
		List<Combo> list = comboService.queryList(page);
		Map<String, Object> rb = new HashMap<String, Object>();
		rb.put("total", page.getTotalRecords());
		rb.put("rows", list);
		return rb;
	}
	
	
	@RequestMapping("/save")
	@ResponseBody	
	@SystemLog(module = "套餐管理", methods = "添加套餐信息")
	public Map<String, String> save(Combo combo,String[] bEFValue,String[] eName,String[] bEFName){
		Map<String, String> map = new HashMap<String, String>();
		ComboExtendField cef = new ComboExtendField();
		int num = 0;
		try {
				num =  comboService.save(combo);
				for (int a = 0;a < bEFValue.length; a++) {
					cef.setbEFValue(bEFValue[a]);
					cef.setbEFName(bEFName[a]);
					cef.seteName(eName[a]);
					cef.setCoId(combo.getId());
					comboExtendFieldService.save(cef);
					
				}

					if(num == 1){
						map.put("msg", "添加成功");
					}else{
						map.put("msg", "添加失败");
					}
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "添加失败");
		}
		return map;
	}
	
	@RequestMapping("/modify")
	@ResponseBody	
	@SystemLog(module = "套餐管理", methods = "修改套餐信息")
	public Map<String, String> modify(Combo combo,String[] bEFValue,String[] eName){
		Map<String, String> map = new HashMap<>();
		int num = 0;
		ComboExtendField cef = new ComboExtendField();
		try {
			num =  comboService.modify(combo);
			for (int a = 0;a < bEFValue.length; a++) {
				cef.setbEFValue(bEFValue[a]);
				cef.seteName(eName[a]);
				cef.setCoId(combo.getId());
				comboExtendFieldService.modify(cef);
				
			}
			if(num == 1){
				map.put("msg", "修改成功");
			}else{
				map.put("msg", "修改失败");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "修改失败");
		}
		return map;
	}
	
	
	@RequestMapping("/deleStatus")
	@ResponseBody		
	@SystemLog(module = "套餐管理", methods = "删除套餐信息")
	public Map<String, String> remove(Integer id,Integer status){
		Map<String, String> map = new HashMap<>();
		try {
			comboService.deleStatus(id,status);
			map.put("msg", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "删除失败");
		}
		return map;
	}
	
	@RequestMapping("/changeStatus")
	@ResponseBody		
	@SystemLog(module = "套餐管理", methods = "修改套餐状态信息")
	public Map<String, String> changeStatus(Integer id,Integer status){
		Map<String, String> map = new HashMap<>();
		try {
			comboService.changeStatus(id, status);
			map.put("msg", "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "修改失败");
		}
		return map;
	}
}
