package com.z.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.z.bean.Xia;
import com.z.dao.mapper.XiaMapper;
import com.z.service.XiaService;
@Service
public class XiaServiceImpl implements XiaService{
	@Autowired
	private XiaMapper xiaMapper;
	
	@Override
	public List<Xia> getAllEnableXia() {
		// TODO Auto-generated method stub
		return xiaMapper.getAllEnableXia();
	}

	@Override
	public Xia selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return xiaMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Xia xia) {
		// TODO Auto-generated method stub
		return xiaMapper.updateByPrimaryKeySelective(xia);
	}

}
