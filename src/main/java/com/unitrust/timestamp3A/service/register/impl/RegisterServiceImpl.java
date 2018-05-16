package com.unitrust.timestamp3A.service.register.impl;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import com.unitrust.timestamp3A.dao.register.RegisterDao;
import com.unitrust.timestamp3A.model.register.Register;
import com.unitrust.timestamp3A.service.register.RegisterService;

@Service("registerService")
public class RegisterServiceImpl implements RegisterService{
	@Resource
	private RegisterDao registerDao;

	@Value("${timestamp3A.defaultPassword}")
	private String defaultPassword;
	
	@Override
	public int save(Register register) throws IOException {
		// TODO Auto-generated method stub
		String passwordMD5 = DigestUtils.md5DigestAsHex(defaultPassword.getBytes());
		register.setPassword(passwordMD5);
		return registerDao.save(register);
	}

	@Override
	public Register findUserByName(String loginCode) {
		// TODO Auto-generated method stub
		return registerDao.findUserByName(loginCode);
	}

	@Override
	public Register getRegisterById(Integer customerId) {
		// TODO Auto-generated method stub
		return registerDao.getRegisterById(customerId);
	}

	@Override
	public int modifyUser(Register register) {
		// TODO Auto-generated method stub
		return registerDao.modifyUser(register);
	}

	@Override
	public Register findEmailByName(String loginCode) {
		// TODO Auto-generated method stub
		return registerDao.findEmailByName(loginCode);
	}

	@Override
	public Register findMobileByName(String loginCode) {
		// TODO Auto-generated method stub
		return registerDao.findMobileByName(loginCode);
	}

	@Override
	public List<Register> getListById(Integer customerId) {
		// TODO Auto-generated method stub
		return registerDao.getListById(customerId);
	}

}
