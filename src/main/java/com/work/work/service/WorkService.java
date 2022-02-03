package com.work.work.service;

import com.work.common.dto.DateAndDepartmentId;
import com.work.work.Work;

public interface WorkService {

	Work startWork(String userId);

	void finishWork(int workingId);

	int countLatecomers(DateAndDepartmentId dateAndDepartmentId);

	int countOvertimeWorkers(DateAndDepartmentId dateAndDepartmentId);

	int countNightShifts(DateAndDepartmentId dateAndDepartmentId);
}
