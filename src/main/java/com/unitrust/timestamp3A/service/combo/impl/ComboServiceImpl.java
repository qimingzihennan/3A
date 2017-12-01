package com.unitrust.timestamp3A.service.combo.impl;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.dao.combo.ComboDao;
import com.unitrust.timestamp3A.model.combo.Combo;
import com.unitrust.timestamp3A.service.combo.ComboService;

@Service("comboService")
public class ComboServiceImpl implements ComboService{
	
	@Resource
	private ComboDao comboDao;

	@Override
	public List<Combo> queryList(Page<Combo> page) {
		// TODO Auto-generated method stub
		return comboDao.queryList(page);
	}

	@Override
	public int save(Combo combo) {
		// TODO Auto-generated method stub
		combo.setStatus(1);
		return comboDao.save(combo);
	}

	@Override
	public int modify(Combo combo) {
		// TODO Auto-generated method stub
		return comboDao.modify(combo);
	}

	@Override
	public int deleStatus(Integer id,Integer status) {
		// TODO Auto-generated method stub
		return comboDao.deleStatus(id,2);
	
	}

	@Override
	public int changeStatus(Integer id, Integer status) {
		// TODO Auto-generated method stub
		if(status == 0){
			return comboDao.changeStatus(id, 1);
		}else{
			return comboDao.changeStatus(id, 0);
		}
		
	}

	@Override
	public int checkout(Integer id) {
		// TODO Auto-generated method stub
		Combo combo = comboDao.findById(id);
		if(combo == null||combo.getId() == null ){
			return 0;
		}
		return 1;
	}

	@Override
	public Combo getComboById(Integer id) {
		// TODO Auto-generated method stub
		return comboDao.getComboById(id);
	}

	@Override
	public List<Combo> query() {
		// TODO Auto-generated method stub
		return comboDao.query();
	}
	@Override
	public List<Combo> querys() {
		// TODO Auto-generated method stub
		return comboDao.querys();
	}
	@Override
	public List<Combo> querye() {
		// TODO Auto-generated method stub
		return comboDao.querye();
	}

}
