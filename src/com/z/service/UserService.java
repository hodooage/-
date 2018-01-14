package com.z.service;

import com.z.bean.User;

public interface UserService {
	User getUser(int id);

	User checkUserInfo(User user);
	
	int updateByPrimaryKeySelective(User record);
	
	float retrieveUserBalance(int id);
}
