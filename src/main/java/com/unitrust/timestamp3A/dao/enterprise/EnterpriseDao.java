package com.unitrust.timestamp3A.dao.enterprise;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.model.enterprise.Enterprise;
import com.unitrust.timestamp3A.model.enterprise.PIN_SD;
import com.unitrust.timestamp3A.model.personal.Personal;


/**
 * 企业dao
 * 
 * @author hongyu
 *
 */
@Repository("enterpriseDao")
public interface EnterpriseDao {
	public List<Enterprise> query(Page<Enterprise> page);
	
	public int save(Enterprise enterprise);
	
	public int delete(Integer enterpriseId);

	public Enterprise getEnterpriseById(Integer enterpriseId);

	public int modifyEnterprise(Enterprise enterprise);
	public Enterprise findEnterpriseByName(String enterpriseName);

	public List<Personal> queryPerson(Page<Enterprise> page);
	
	public int modifyEnterpriseToApprove(Integer enterpriseId);
	
	public int modifyEnterpriseToReject(Integer enterpriseId);


	public void savePIN_SD(PIN_SD PS);

	public void modifyPIN_SDStatus(Integer enterpriseId);

	public PIN_SD findPSByPIN(String PIN);

	public PIN_SD getPSByEnterpriseId(Integer enterpriseId);
	
	public int getPIN_SDByEnterpriseId(Integer enterpriseId);

	public Enterprise findEnterpriseByBusinessNumber(String businessNumber);

	public Enterprise findEnterpriseByAdminIdCard(@Param("adminIdCard")String adminIdCard,@Param("enterpriseId")String enterpriseId);

	public Enterprise findEnterpriseBycondition(Enterprise en);




}
