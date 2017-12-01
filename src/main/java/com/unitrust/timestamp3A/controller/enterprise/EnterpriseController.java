package com.unitrust.timestamp3A.controller.enterprise;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.unitrust.timestamp3A.authorization.SystemLog;
//import com.unitrust.timestamp3A.authorization.SystemLog;
import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.common.util.Common;
import com.unitrust.timestamp3A.common.util.ResultBean;
import com.unitrust.timestamp3A.model.combo.Combo;
import com.unitrust.timestamp3A.model.combo.ComboExtendField;
import com.unitrust.timestamp3A.model.enterprise.Enterprise;
import com.unitrust.timestamp3A.model.enterprise.PIN_SD;
import com.unitrust.timestamp3A.model.file.Files;
import com.unitrust.timestamp3A.model.file.testFile;
import com.unitrust.timestamp3A.model.personal.Personal;
import com.unitrust.timestamp3A.service.combo.ComboExtendFieldService;
import com.unitrust.timestamp3A.service.combo.ComboService;
import com.unitrust.timestamp3A.service.enterprise.EnterpriseService;
import com.unitrust.timestamp3A.service.file.FileService;




@Controller
@RequestMapping("/enterprise")
public class EnterpriseController {

	@Resource
	private EnterpriseService enterpriseService;
	
	@Resource
	private FileService fileService;
	
	@Resource
	private ComboService comboService;
	
	@Resource 
	private ComboExtendFieldService comboExtendFieldService;
	

	@RequestMapping(value = "list")
	@SystemLog(module = "企业管理", methods = "前往企业管理页面")
	public String listAll(String id,Model model){
		model.addAttribute("iframeId", id);
		return "enterprise/enterprise";
	}
	@RequestMapping("/addEnterprise")
	@SystemLog(module = "企业管理", methods = "前往企业信息添加页面")
	public String addEnterprise(String id,Model model) {
		model.addAttribute("iframeId", id);
		model.addAttribute("enterprise",new Enterprise());
		return "enterprise/addEnterprise";
	}
	@ResponseBody
	@RequestMapping("/query")
	@SystemLog(module = "企业管理", methods = "分页查询企业信息")
	public Map<String, Object> query(HttpServletRequest request,Enterprise enterprise){
		Page<Enterprise> page = new Page<Enterprise>();
		Integer pageNum = request.getParameter("page") != null ? Integer.valueOf(request.getParameter("page")) : 1;
		Integer rows = request.getParameter("rows") != null ? Integer.valueOf(request.getParameter("rows")) : 1;
		page.setPageNum(pageNum);
		page.setPageSize(rows);
		Map paramMap = Common.ObjectToMap(enterprise);
		page.setSearchCondition(paramMap);
		List<Enterprise> list = enterpriseService.query(page);
		Map<String, Object> rb = new HashMap<String, Object>();
		rb.put("total", page.getTotalRecords());
		rb.put("rows", list);
		return rb;
	} 
	@ResponseBody
	@RequestMapping(value = "checkEnterprise", method = RequestMethod.POST)
	public Map<String, Object> validateEnterpriseName(String enterpriseName) {
		Map<String, Object> result = new HashMap<String, Object>();
		String errorCode = "1000";
		try {
			Enterprise enterprise = enterpriseService.findEnterpriseByName(enterpriseName);
			if (enterprise != null) {
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
	@RequestMapping(value = "checkPIN", method = RequestMethod.POST)
	public Map<String, Object> validatePIN(String PIN) {
		Map<String, Object> result = new HashMap<String, Object>();
		String errorCode = "1000";
		try {
			PIN_SD PS = enterpriseService.findPSByPIN(PIN);
			if (PS != null) {
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
	@RequestMapping(value = "checkBusinessNumber", method = RequestMethod.POST)
	public Map<String, Object> validateBusinessNumber(String businessNumber) {
		Map<String, Object> result = new HashMap<String, Object>();
		String errorCode = "1000";
		try {
			Enterprise enterprise = enterpriseService.findEnterpriseByBusinessNumber(businessNumber);
			if (enterprise != null) {
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
	@RequestMapping(value = "checkAdminIdCard", method = RequestMethod.POST)
	public Map<String, Object> validateAdminIdCard(String adminIdCard,String enterpriseId) {
		Map<String, Object> result = new HashMap<String, Object>();
		String errorCode = "1000";
		try {
			Enterprise enterprise = enterpriseService.findEnterpriseByAdminIdCard(adminIdCard,enterpriseId);
			if (enterprise != null) {
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
	@RequestMapping(value = "checkuscCode", method = RequestMethod.POST)
	public Map<String, Object> validateuscCode(String uscCode) {
		Map<String, Object> result = new HashMap<String, Object>();
		Enterprise en = new Enterprise();
		en.setUscCode(uscCode);
		String errorCode = "1000";
		try {
			Enterprise enterprise = enterpriseService.findEnterpriseBycondition(en);
			if (enterprise != null) {
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
	@RequestMapping(value = "checktelephone", method = RequestMethod.POST)
	public Map<String, Object> validatetelephone(String telephone,Integer enterpriseId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Enterprise en = new Enterprise();
		en.setTelephone(telephone);
		String errorCode = "1000";
		try {
			Enterprise enterprise = enterpriseService.findEnterpriseBycondition(en);
			if (enterprise != null) {
				Integer eId = enterprise.getEnterpriseId();
				if(eId != enterpriseId){
					errorCode = "1001";
				}
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
	@RequestMapping(value = "checkemail", method = RequestMethod.POST)
	public Map<String, Object> validateemail(String email,Integer enterpriseId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Enterprise en = new Enterprise();
		en.setEmail(email);
		String errorCode = "1000";
		try {
			Enterprise enterprise = enterpriseService.findEnterpriseBycondition(en);
			if (enterprise != null) {
				Integer eId = enterprise.getEnterpriseId();
				if(eId != enterpriseId){
					errorCode = "1001";
				}
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
	@SystemLog(module = "企业管理", methods = "保存企业信息")
	public Map<String, String> save(Enterprise enterprise) {
		Map<String, String> map = new HashMap<>();
		int num = 0;
		try {
			num = enterpriseService.save(enterprise);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (num == 1) {
			map.put("msg", "添加成功");
		} else {
			map.put("msg", "添加失败");
		}
		return map;
	}
	@ResponseBody
	@RequestMapping("/delete")
	@SystemLog(module = "企业管理", methods = "删除企业信息")
	public ResultBean remove(Integer enterpriseId) {
		ResultBean result = new ResultBean();
		try {
			enterpriseService.remove(enterpriseId);
			result.putData("msg", "删除成功");
		} catch (Exception e) {
			result.putData("msg", "删除失败");
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/approve")
	@SystemLog(module = "企业管理", methods = "企业认证")
	public Map<String, Object> approveEnterprise(Integer enterpriseId) {
		Map<String, Object> result = new HashMap<String, Object>();
		int num = enterpriseService.approveEnterprise(enterpriseId);
		if (num == 1) {
			result.put("msg", "已通过认证");
		} else {
			result.put("msg", "认证失败");
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/reject")
	@SystemLog(module = "企业管理", methods = "企业认证")
	public Map<String, Object> rejectEnterprise(Integer enterpriseId) {
		Map<String, Object> result = new HashMap<String, Object>();
		int num = enterpriseService.rejectEnterprise(enterpriseId);
		if (num == 1) {
			result.put("msg", "已驳回认证");
		} else {
			result.put("msg", "驳回失败");
		}
		return result;
	}
	
	@RequestMapping("/toModify")
	@SystemLog(module = "企业管理", methods = "前往企业信息修改页面")
	public String getEnterpriseList(Integer enterpriseId, String id, Model model) {
		Enterprise enterprise = enterpriseService.getEnterpriseById(enterpriseId);
		model.addAttribute("iframeId", id);
		model.addAttribute("enterprise", enterprise);
		return "enterprise/addEnterprise";
	}
	
	@RequestMapping("/toSearchPerson")
	@SystemLog(module = "企业管理", methods = "前往查看企业员工页面")
	public String getEnterprise(Integer enterpriseId, String id, Model model) {
		Enterprise enterprise = enterpriseService.getEnterpriseById(enterpriseId);
		model.addAttribute("iframeId", id);
		model.addAttribute("enterprise", enterprise);
		return "enterprise/searchPerson";
		
	}
	
	@RequestMapping("/toApprove")
	@SystemLog(module = "企业管理", methods = "前往查看/审核企业页面")
	public String getEnterpriseInfo(Integer enterpriseId, String id, Model model) {
		Enterprise enterprise = enterpriseService.getEnterpriseById(enterpriseId);
		Files f3 = fileService.findPhotoFile(enterpriseId, 3);
		Files f4 = fileService.findPhotoFile(enterpriseId, 4);
		Files f5 = fileService.findPhotoFile(enterpriseId, 5);
		Files f6 = fileService.findPhotoFile(enterpriseId, 6);
		Files f7 = fileService.findPhotoFile(enterpriseId, 7);
		Files f8 = fileService.findPhotoFile(enterpriseId, 8);
		PIN_SD PS = enterpriseService.getPSByEnterpriseId(enterpriseId);
		model.addAttribute("PS",PS);
		model.addAttribute("iframeId", id);
		model.addAttribute("enterprise", enterprise);
		model.addAttribute("file3", f3);
		model.addAttribute("file4", f4);
		model.addAttribute("file5", f5);
		model.addAttribute("file6", f6);
		model.addAttribute("file7", f7);
		model.addAttribute("file8", f8);

		
		return "enterprise/approve";
		
	}
	
	
	@ResponseBody
	@RequestMapping("/searchPerson")
	@SystemLog(module = "企业管理", methods = "分页查询企业员工信息")
	public Map<String, Object> queryEnterprisePerson(HttpServletRequest request,Integer enterpriseId){
		Page<Enterprise> page = new Page<Enterprise>();
		Integer pageNum = request.getParameter("page") != null ? Integer.valueOf(request.getParameter("page")) : 1;
		Integer rows = request.getParameter("rows") != null ? Integer.valueOf(request.getParameter("rows")) : 1;
		page.setPageNum(pageNum);
		page.setPageSize(rows);
		Enterprise enterprise = enterpriseService.getEnterpriseById(enterpriseId);
		Map paramMap = Common.ObjectToMap(enterprise);
		page.setSearchCondition(paramMap);
		List<Personal> list = enterpriseService.queryPerson(page);
		Map<String, Object> rb = new HashMap<String, Object>();
		rb.put("total", page.getTotalRecords());
		rb.put("rows", list);
		return rb;
	} 
	
	
	@ResponseBody
	@RequestMapping("/modify")
	@SystemLog(module = "企业管理", methods = "修改企业信息")
	public Map<String, Object> modify(Enterprise enterprise) {
		Map<String, Object> result = new HashMap<String, Object>();
		int num = enterpriseService.modifyEnterprise(enterprise);
		if (num == 1) {
			result.put("msg", "修改成功");
		} else {
			result.put("msg", "修改失败");
		}
		return result;
	}
	
	@RequestMapping("/toAddOrder")
	@SystemLog(module = "订单管理", methods = "前往订单添加页面")
	public String toAdd(String id, Model model,Integer enterpriseId) {
		model.addAttribute("iframeId", id);
		Enterprise enterprise = enterpriseService.getEnterpriseById(enterpriseId);
		List<Combo> list = comboService.querye();
		model.addAttribute("enterprise", enterprise);
		model.addAttribute("combo",list);
		return "enterprise/addOrder";
	}
	
	@RequestMapping("/toAddPIN_SD")
	@SystemLog(module = "企业管理", methods = "前往添加企业PIN/SD码页面")
	public String toAddPIN_SD(String id, Model model,Integer enterpriseId) {
		model.addAttribute("iframeId", id);
		Enterprise enterprise = enterpriseService.getEnterpriseById(enterpriseId);
		model.addAttribute("enterprise", enterprise);
		return "enterprise/addPIN_SD";
	}
	
	@ResponseBody
	@RequestMapping("/addPIN_SD")
	@SystemLog(module = "企业管理", methods = "添加企业PIN/SD码信息")
	public ResultBean addPIN_SD(Integer enterpriseId) {
		ResultBean result = new ResultBean();
		try {
			enterpriseService.savePIN_SD(enterpriseId);
			result.setMsg("添加成功");
		} catch (Exception e) {
			result.setMsg("添加失败");
		}
		return result;
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
	@RequestMapping("/upload")
	@SystemLog(module = "企业管理", methods = "上传图片")
	public Map<String, Object> upload(Model model,@RequestParam MultipartFile photo) throws IOException{
		Map<String, Object> result = new HashMap<String, Object>();
		InputStream isPictureFile = photo.getInputStream();
		byte[] pictureData = new byte[(int) photo.getSize()];
		isPictureFile.read(pictureData);
		
		Files file = new Files();
		file.setFile(pictureData);
		file.setFileName("haha");
		file.setFileType(3);
		file.setId(3);
		file.setRegisterType(2);
		file.setRelationId(5);
		
		int num = fileService.uploadFiles(file);
		if (num == 1) {
			result.put("msg", "上传成功");
		} else {
			result.put("msg", "上传失败");
		}
		return result;
		
	}
	
}

