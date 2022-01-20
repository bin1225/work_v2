package com.work.department.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.work.department.Department;

@Mapper
public interface DepartmentMapper {
	void insert(Department department);

	Department selectOne(Long id);

	boolean selectExistLower(Long id);

	void delete(Long id);

	List<Department> selectAll();

	Integer selectDepth(Long departmentId);



}


