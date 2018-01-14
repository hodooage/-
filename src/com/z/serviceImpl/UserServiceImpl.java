package com.z.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.z.bean.User;
import com.z.dao.mapper.UserMapper;
import com.z.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	
	
	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}


	@Override
	public User checkUserInfo(User user) {
		
		return userMapper.checkUserInfo(user);
	}


	@Override
	public int updateByPrimaryKeySelective(User record) {
		
		return userMapper.updateByPrimaryKeySelective(record);
	}


	@Override
	public float retrieveUserBalance(int id) {
		
		return userMapper.retrieveUserBalance(id);
	}

}
