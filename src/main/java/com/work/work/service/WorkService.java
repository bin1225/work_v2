package com.work.work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.work.work.Work;
import com.work.work.mapper.WorkMapper;

@Service
public class WorkService {

	@Autowired
	private WorkMapper workMapper;

	public Work startWork(String userId){
		Work work = new Work(userId);
		workMapper.insert(work);
		return work;
	}

	public void finishWork(int workingId){
		workMapper.updateEndTime(workingId);
	}
}
