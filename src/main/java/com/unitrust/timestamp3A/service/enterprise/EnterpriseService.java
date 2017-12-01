package com.unitrust.timestamp3A.service.enterprise;

import java.io.IOException;
import java.util.List;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.model.enterprise.Enterprise;
import com.unitrust.timestamp3A.model.enterprise.PIN_SD;
import com.unitrust.timestamp3A.model.personal.Personal;




public interface EnterpriseService {
	/**
	 * 查询企业信息
	 * 
	 * @param page
	 * @return
	 */
	public List<Enterprise> query(Page<Enterprise> page);
	/**
	 * 企业信息添加
	 * @param Enterprise
	 * @return
	 * @throws IOException
	 */
	public int save(Enterprise enterprise) throws IOException;
	/**
	 * 删除企业信息
	 * 
	 * @param enterpriseId
	 * @return
	 */
	public int remove(Integer enterpriseId);
	/**
	 * 通过企业id获取企业信息
	 * 
	 * @param enterpriseId
	 * @return
	 */
	public Enterprise getEnterpriseById(Integer enterpriseId);
	/**
	 * 修改企业信息
	 * 
	 * @param enterprise
	 * @return
	 */
	public int modifyEnterprise(Enterprise enterprise);
	public Enterprise findEnterpriseByName(String enterpriseName);
	

	
	public int approveEnterprise(Integer enterpriseId);
	
	public int rejectEnterprise(Integer enterpriseId);

	public List<Personal> queryPerson(Page<Enterprise> page);
	

	public void savePIN_SD(Integer enterpriseId);
	
	public PIN_SD findPSByPIN(String PIN);
	
	public PIN_SD getPSByEnterpriseId(Integer enterpriseId);

	public Enterprise findEnterpriseByBusinessNumber(String businessNumber);
	
	public Enterprise findEnterpriseByAdminIdCard(String adminIdCard,String enterpriseId);
	
	public Enterprise findEnterpriseBycondition(Enterprise en);


}
