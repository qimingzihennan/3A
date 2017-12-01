package com.unitrust.timestamp3A.service.enterprise.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.unitrust.timestamp3A.common.interceptor.page.Page;

import com.unitrust.timestamp3A.dao.enterprise.EnterpriseDao;
import com.unitrust.timestamp3A.model.enterprise.Enterprise;
import com.unitrust.timestamp3A.model.enterprise.PIN_SD;
import com.unitrust.timestamp3A.model.personal.Personal;
import com.unitrust.timestamp3A.service.enterprise.EnterpriseService;


@Service("enterpriseService")
public class EnterpriseServiceImpl implements EnterpriseService {

	@Resource
	private EnterpriseDao enterpriseDao;

	@Override
	public List<Enterprise> query(Page<Enterprise> page) {
		// TODO Auto-generated method stub
		List<Enterprise> enterpriseList = enterpriseDao.query(page);
		return enterpriseList;
	}

	@Override
	public int save(Enterprise enterprise) throws IOException {
		// TODO Auto-generated method stub
		int result = 0;
		result = enterpriseDao.save(enterprise);
		//PIN码
		Date date1 = new Date();
		String aaa = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date1);
		String PIN = DigestUtils.md5DigestAsHex(aaa.getBytes());
		//SD码
		Date date2 = new Date();
		String bbb = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd").format(date2);
		String SD = DigestUtils.md5DigestAsHex(bbb.getBytes());
		PIN_SD PS = new PIN_SD();
		PS.setPIN(PIN);
		PS.setSD(SD);
		PS.setEnterpriseId(enterprise.getEnterpriseId());
		//生成一组新的PIN/SD码
		savePIN_SD(PS,enterprise.getEnterpriseId());
		return result;
	}
	
	private void savePIN_SD(PIN_SD PS,Integer enterpriseId) {
		// TODO Auto-generated method stub
		//先改原有PIN/SD码状态为不可用（0）
		enterpriseDao.modifyPIN_SDStatus(enterpriseId);
		//再保存新的PIN/SD码
		enterpriseDao.savePIN_SD(PS);
		
	}

	@Override
	public int remove(Integer enterpriseId) {
		// TODO Auto-generated method stub
		return enterpriseDao.delete(enterpriseId);
	}

	@Override
	public Enterprise getEnterpriseById(Integer enterpriseId) {
		// TODO Auto-generated method stub
		return enterpriseDao.getEnterpriseById(enterpriseId);
	}

	@Override
	public int modifyEnterprise(Enterprise enterprise) {
		// TODO Auto-generated method stub
		return enterpriseDao.modifyEnterprise(enterprise);
	}

	@Override
	public Enterprise findEnterpriseByName(String enterpriseName) {
		// TODO Auto-generated method stub
		return enterpriseDao.findEnterpriseByName(enterpriseName);
	}
	
	@Override
	public List<Personal> queryPerson(Page<Enterprise> page) {
		// TODO Auto-generated method stub
		return enterpriseDao.queryPerson(page);
	}
	
	@Override
	public int approveEnterprise(Integer enterpriseId) {
		// TODO Auto-generated method stub
		return enterpriseDao.modifyEnterpriseToApprove(enterpriseId);
	}
	
	@Override
	public int rejectEnterprise(Integer enterpriseId) {
		// TODO Auto-generated method stub
		return enterpriseDao.modifyEnterpriseToReject(enterpriseId);
	}

	@Override
	public void savePIN_SD(Integer enterpriseId) {
		// TODO Auto-generated method stub
		
		//PIN码
		Date date1 = new Date();
		String aaa = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date1);
		String PIN = DigestUtils.md5DigestAsHex(aaa.getBytes());
		//SD码
		Date date2 = new Date();
		String bbb = new SimpleDateFormat("hh:mm:ss yyyy-MM-dd").format(date2);
		String SD = DigestUtils.md5DigestAsHex(bbb.getBytes());
		PIN_SD PS = new PIN_SD();
		PS.setPIN(PIN);
		PS.setSD(SD);
		
		PS.setEnterpriseId(enterpriseId);
		int sum = enterpriseDao.getPIN_SDByEnterpriseId(enterpriseId);
		if(sum != 0){
			enterpriseDao.modifyPIN_SDStatus(enterpriseId);
		}
			
		enterpriseDao.savePIN_SD(PS);
		
	}

	@Override
	public PIN_SD findPSByPIN(String PIN) {
		// TODO Auto-generated method stub
		return enterpriseDao.findPSByPIN(PIN);
	}

	@Override
	public PIN_SD getPSByEnterpriseId(Integer enterpriseId) {
		// TODO Auto-generated method stub
		return enterpriseDao.getPSByEnterpriseId(enterpriseId);
	}
	
	

	public Enterprise findEnterpriseByBusinessNumber(String businessNumber) {
		// TODO Auto-generated method stub
		return enterpriseDao.findEnterpriseByBusinessNumber(businessNumber);
	}

	@Override
	public Enterprise findEnterpriseByAdminIdCard(String adminIdCard,String enterpriseId) {
		// TODO Auto-generated method stub
		return enterpriseDao.findEnterpriseByAdminIdCard(adminIdCard,enterpriseId);
	}

	@Override
	public Enterprise findEnterpriseBycondition(Enterprise en) {
		// TODO Auto-generated method stub
		return enterpriseDao.findEnterpriseBycondition(en);
	}

	

}
