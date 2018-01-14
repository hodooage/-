package com.z.service;

import java.util.List;

import com.z.bean.Xia;

public interface XiaService {
	List<Xia> getAllEnableXia();
	
	Xia selectByPrimaryKey(Integer id);
	
	int updateByPrimaryKeySelective(Xia xia);
}
