package com.work.work.mapper;

import com.work.common.dto.DateAndDepartmentId;
import com.work.work.Work;

public interface WorkMapper {

	void insert(Work work);

	void updateEndTime(int workingId);

	Work selectOne(int id);

	int selectLateCount(DateAndDepartmentId dateAndDepartmentId);

	int selectOverTimeWorker(DateAndDepartmentId dateAndDepartmentId);

	int selectNightShift(DateAndDepartmentId dateAndDepartmentId);
}
