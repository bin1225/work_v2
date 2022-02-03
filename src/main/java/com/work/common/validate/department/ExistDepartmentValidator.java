package com.work.common.validate.department;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.work.department.Department;
import com.work.department.mapper.DepartmentMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistDepartmentValidator implements ConstraintValidator<ExistDepartmentId, Long> {

	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	public boolean isValid(Long departmentId, ConstraintValidatorContext constraintValidatorContext) {
		log.info("validator 작동");

		Department department = departmentMapper.selectOne(departmentId);
		if (department == null) {
			return false;
		}
		return true;
	}
}
