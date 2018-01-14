package com.z.service;

import com.z.bean.UseRecord;
import com.z.bean.User;

public interface UseRecordService {
	int insertSelective(UseRecord useRecord);
	
	int selectByStartTime(UseRecord useRecord);
	
	int updateByPrimaryKeySelective(UseRecord useRecord);
	
	UseRecord getUseRecord(UseRecord useRecord);
	
}
