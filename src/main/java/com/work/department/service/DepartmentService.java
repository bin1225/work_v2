package com.work.department.service;


import com.work.department.Department;

public interface DepartmentService {

	void createDepartment(Department department);

	boolean checkLowerExists(Long id);

	Department findDepartment(Long id);

	void removeDepartment(Long id, String userId);

	void checkManager(String userId);

	Department getAllDepartmentsTree();

}
