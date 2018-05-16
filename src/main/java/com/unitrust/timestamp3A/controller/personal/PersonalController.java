package com.unitrust.timestamp3A.controller.personal;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitrust.timestamp3A.authorization.SystemLog;
import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.common.util.Common;
import com.unitrust.timestamp3A.common.util.ResultBean;
import com.unitrust.timestamp3A.model.combo.Combo;
import com.unitrust.timestamp3A.model.combo.ComboExtendField;
import com.unitrust.timestamp3A.model.file.Files;
import com.unitrust.timestamp3A.model.personal.Personal;
import com.unitrust.timestamp3A.model.register.Register;
import com.unitrust.timestamp3A.service.combo.ComboExtendFieldService;
import com.unitrust.timestamp3A.service.combo.ComboService;
import com.unitrust.timestamp3A.service.file.FileService;
import com.unitrust.timestamp3A.service.personal.PersonalService;
import com.unitrust.timestamp3A.service.register.RegisterService;
import org.springframework.web.multipart.MultipartFile;




@Controller
@RequestMapping("/personal")
public class PersonalController {

	@Resource
	private PersonalService personalService;
	
	@Resource
	private ComboService comboService;
	
	@Resource
	private ComboExtendFieldService comboExtendFieldService;
	
	@Resource
	private RegisterService registerService;
	
	@Resource
	private FileService fileService;
	
	
	
	@RequestMapping(value = "list")
	@SystemLog(module = "用户管理", methods = "前往用户管理页面")
	public String listAll(String id,Model model){
		model.addAttribute("iframeId", id);
		return "personal/list";
	}
	@RequestMapping(value = "/user/list")
	@SystemLog(module = "用户管理", methods = "前往用户管理页面")
	public String listAlls(String id,Model model){
		model.addAttribute("iframeId", id);
		return "personal/lists";
	}
	@RequestMapping("/addCustom")
	@SystemLog(module = "用户管理", methods = "前往用户信息添加页面")
	public String addRoles(String id,Model model) {
		model.addAttribute("iframeId", id);
		//model.addAttribute("personal",new Personal());
		return "personal/addCustom";
	}
	@ResponseBody
	@RequestMapping("/query")
	@SystemLog(module = "用户管理", methods = "分页查询用户信息")
	public Map<String, Object> query(HttpServletRequest request,Personal personal){
		Page<Personal> page = new Page<Personal>();
		Integer pageNum = request.getParameter("page") != null ? Integer.valueOf(request.getParameter("page")) : 1;
		Integer rows = request.getParameter("rows") != null ? Integer.valueOf(request.getParameter("rows")) : 1;
		page.setPageNum(pageNum);
		page.setPageSize(rows);
		Map paramMap = Common.ObjectToMap(personal);
		page.setSearchCondition(paramMap);
		List<Personal> list = personalService.query(page);
		Map<String, Object> rb = new HashMap<String, Object>();
		rb.put("total", page.getTotalRecords());
		rb.put("rows", list);
		return rb;
	} 
	@ResponseBody
	@RequestMapping("/user/query")
	@SystemLog(module = "用户管理", methods = "分页查询用户信息")
	public Map<String, Object> querys(HttpServletRequest request,Personal personal){
		Page<Personal> page = new Page<Personal>();
		Integer pageNum = request.getParameter("page") != null ? Integer.valueOf(request.getParameter("page")) : 1;
		Integer rows = request.getParameter("rows") != null ? Integer.valueOf(request.getParameter("rows")) : 1;
		page.setPageNum(pageNum);
		page.setPageSize(rows);
		Map paramMap = Common.ObjectToMap(personal);
		page.setSearchCondition(paramMap);
		List<Personal> list = personalService.querys(page);
		Map<String, Object> rb = new HashMap<String, Object>();
		rb.put("total", page.getTotalRecords());
		rb.put("rows", list);
		return rb;
	} 
	@ResponseBody
	@RequestMapping(value = "checkCName", method = RequestMethod.POST)
	public Map<String, Object> validateCName(String loginCode) {
		Map<String, Object> result = new HashMap<String, Object>();
		String errorCode = "1000";
		try {
			Register register = registerService.findUserByName(loginCode);
			if (register != null) {
				errorCode = "1001";
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorCode = "1001";
		} finally {
			result.put("msg", errorCode);
		}
		return result;
	}
	@ResponseBody
	@RequestMapping(value = "checkIdCard", method = RequestMethod.POST)
	public Map<String, Object> validateIdCard(String idCard) {
		Map<String, Object> result = new HashMap<String, Object>();
		String errorCode = "1000";
		try {
			Personal personal = personalService.findUserByIdCard(idCard);
			if (personal != null) {
				errorCode = "1001";
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorCode = "1001";
		} finally {
			result.put("msg", errorCode);
		}
		return result;
	}
	@ResponseBody
	@RequestMapping(value = "checkEmail", method = RequestMethod.POST)
	public Map<String, Object> validateEmail(String email) {
		Map<String, Object> result = new HashMap<String, Object>();
		String errorCode = "1000";
		try {
			Personal personal = personalService.findUserByEmail(email);
			if (personal != null) {
				errorCode = "1001";
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorCode = "1001";
		} finally {
			result.put("msg", errorCode);
		}
		return result;
	}
	@ResponseBody
	@RequestMapping(value = "checkMobile", method = RequestMethod.POST)
	public Map<String, Object> validateMobile(String mobile) {
		Map<String, Object> result = new HashMap<String, Object>();
		String errorCode = "1000";
		try {
			Personal Custom = personalService.findUserByMobile(mobile);
			if (Custom != null) {
				errorCode = "1001";
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorCode = "1001";
		} finally {
			result.put("msg", errorCode);
		}
		return result;
	}
	@RequestMapping("/save")
	@ResponseBody
	@SystemLog(module = "用户管理", methods = "保存用户信息")
	public Map<String, String> save(Personal personal,String loginCode) {
		Map<String, String> map = new HashMap<>();
		Register register = new Register();
		int num = 0;
		try {
			personal.setDelStatus(0);
			num = personalService.save(personal);
			if(personal.getMobile() != null){
				register.setCustomerId(personal.getId());
				register.setLoginType(2);
				register.setLoginCode(personal.getMobile());
				registerService.save(register);
			}
			if(personal.getEmail() != null){
				register.setCustomerId(personal.getId());
				register.setLoginType(1);
				register.setLoginCode(personal.getEmail());
				registerService.save(register);
			}
			register.setCustomerId(personal.getId());
			register.setLoginType(0);
			register.setLoginCode(loginCode);
			registerService.save(register);
			
			if (num == 1) {
				map.put("msg", "添加成功");
			} else {
				map.put("msg", "添加失败");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return map;
	}
	@ResponseBody
	@RequestMapping("/delStatus")
	@SystemLog(module = "用户管理", methods = "冻结/解冻用户信息")
	public ResultBean remove(Integer customerId) {
		ResultBean result = new ResultBean();
		Personal p = personalService.getCustomById(customerId);
		try {
			if(p.getDelStatus() == 0){
				personalService.delStatus(customerId);
				result.putData("msg", "冻结成功");
			}else{
				personalService.delsStatus(customerId);
				result.putData("msg", "解冻成功");
			}
		} catch (Exception e) {
			result.putData("msg", "操作失败");
		}
		return result;
	}
	@RequestMapping("/toModify")
	@SystemLog(module = "用户管理", methods = "前往用户信息修改页面")
	public String getUserList(Integer customerId, String id, Model model) {
		Personal personal = personalService.getCustomById(customerId);
		Register register = registerService.getRegisterById(customerId);
		model.addAttribute("iframeId", id);
		model.addAttribute("personal", personal);
		model.addAttribute("register", register);
		return "personal/addCustom";
	}
	@RequestMapping("/toModifys")
	@SystemLog(module = "用户管理", methods = "前往用户详情页面")
	public String getUserLists(Integer customerId, String id, Model model) {
		Personal personal = personalService.getCustomById(customerId);
		Register register = registerService.getRegisterById(customerId);
		List<Files> list = fileService.getCustomById(customerId);
		Files f0 = fileService.findPhotoFile(customerId, 0);
		Files f1 = fileService.findPhotoFile(customerId, 1);
		Files f2 = fileService.findPhotoFile(customerId, 2);
		model.addAttribute("iframeId", id);
		model.addAttribute("personal", personal);
		model.addAttribute("register", register);
		model.addAttribute("list", list);
		model.addAttribute("file0", f0);
		model.addAttribute("file1", f1);
		model.addAttribute("file2", f2);
		
		for(int i = 0;i<list.size();i++){
			Files files = list.get(i);	
		}
		return "personal/listCustom";
	}
	
	@ResponseBody
	@RequestMapping("/modify")
	@SystemLog(module = "用户管理", methods = "修改用户信息")
	public Map<String, Object> modify(Personal personal,Integer registerId,String loginCode) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		Register register = new Register();
		List<Register> list = registerService.getListById(personal.getId());
		int num = personalService.modifyUser(personal);
		
		for(int i = 0;i<list.size();i++){
			Register r = list.get(i);
			if(r.getLoginType() == 1){
				register.setLoginCode(personal.getEmail());
				register.setRegisterId(r.getRegisterId());
				registerService.modifyUser(register);
			}
			if(r.getLoginType() == 2){
				register.setLoginCode(personal.getMobile());
				register.setRegisterId(r.getRegisterId());
				registerService.modifyUser(register);
			}
			if(r.getLoginType() == 0){
				register.setLoginCode(loginCode);
				register.setRegisterId(r.getRegisterId());
				registerService.modifyUser(register);
			}
		}
		
		if (num == 1) {
			map.put("msg", "修改成功");
		} else {
			map.put("msg", "修改失败");
		}
		return map;
	}
	@RequestMapping("/toAddOrder")
	@SystemLog(module = "订单管理", methods = "前往订单添加页面")
	public String toAdd(String id, Model model,Integer customerId) {
		model.addAttribute("iframeId", id);
		Personal personal = personalService.getCustomById(customerId);
		List<Combo> list = comboService.querys();
		model.addAttribute("personal", personal);
		model.addAttribute("combo",list);
		return "personal/addOrder";
	}
	@RequestMapping("/findByCid")
	@ResponseBody
	public Map<String, Object> findByBkey(Integer cid,Model model){
		Combo combo = comboService.getComboById(cid);
		List<ComboExtendField> list = comboExtendFieldService.findById(cid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("combo", combo);
		map.put("cef", list);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/changeStatus")
	@SystemLog(module = "用户管理", methods = "个人认证审核信息")
	public Map<String, Object> changeStatus(Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		int num = personalService.changeStatus(id);
		if (num == 1) {
			result.put("msg", "认证成功");
		} else {
			result.put("msg", "认证失败");
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/changesStatus")
	@SystemLog(module = "用户管理", methods = "个人认证审核信息")
	public Map<String, Object> changesStatus(Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		int num = personalService.changesStatus(id);
		if (num == 1) {
			result.put("msg", "驳回成功");
		} else {
			result.put("msg", "驳回失败");
		}
		return result;
	}
	
}

