package com.work.department.service;


import com.work.department.Department;

public interface DepartmentService {

	Long createDepartment(Department department);

	boolean checkLowerExists(Long id);

	Department findDepartment(Long id);

	void removeDepartment(Long id);


	Department getAllDepartmentsTree();

}
