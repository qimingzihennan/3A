package com.unitrust.timestamp3A.service.combo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.unitrust.timestamp3A.dao.combo.ComboExtendFieldDao;
import com.unitrust.timestamp3A.model.combo.ComboExtendField;
import com.unitrust.timestamp3A.service.combo.ComboExtendFieldService;

@Service("comboExtendFieldService")
public class ComboExtendFieldServiceImpl implements ComboExtendFieldService{
	@Resource
	private ComboExtendFieldDao comboExtendFieldDao;
	@Override
//	public int save(List<ComboExtendField> list) {
//		// TODO Auto-generated method stub
//		return comboExtendFieldDao.save(list);
//	}
	public int save(ComboExtendField cef){
		return comboExtendFieldDao.save(cef);
	}

	@Override
	public int modify(ComboExtendField comboExtendField) {
		// TODO Auto-generated method stub
		return comboExtendFieldDao.modify(comboExtendField);
	}

	@Override
	public List<ComboExtendField> findById(Integer id) {
		// TODO Auto-generated method stub
		return comboExtendFieldDao.findById(id);
	}
	
}
