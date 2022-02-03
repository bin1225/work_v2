package com.work.department.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.work.department.Department;

@Mapper
public interface DepartmentMapper {
	void insert(Department department);

	Department selectOne(Long id);

	boolean selectExistLower(Long id);

	void delete(@Param("departmentId") Long id, @Param("userId") String userId);

	List<Department> selectAll();

	Integer selectDepth(@Param("departmentId") Long departmentId);

	void insertHistory(Department department);

}


