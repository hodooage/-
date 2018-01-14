package com.z.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.z.bean.UseRecord;
import com.z.bean.User;
import com.z.dao.mapper.UseRecordMapper;
import com.z.service.UseRecordService;

@Service
public class UseRecordServiceImpl implements UseRecordService {
	@Autowired
	private UseRecordMapper useRecordMapper;
	
	@Override
	public int insertSelective(UseRecord useRecord) {
		
		return useRecordMapper.insertSelective(useRecord);
	}

	@Override
	public int selectByStartTime(UseRecord useRecord) {
		// TODO Auto-generated method stub
		return useRecordMapper.selectByStartTime(useRecord);
	}

	@Override
	public int updateByPrimaryKeySelective(UseRecord useRecord) {
		// TODO Auto-generated method stub
		return useRecordMapper.updateByPrimaryKeySelective(useRecord);
	}

	@Override
	public UseRecord getUseRecord(UseRecord useRecord) {
		// TODO Auto-generated method stub
		return useRecordMapper.getUseRecord(useRecord);
	}

}
