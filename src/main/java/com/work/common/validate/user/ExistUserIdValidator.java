package com.work.common.validate.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.work.common.validate.department.ExistDepartmentId;
import com.work.user.User;
import com.work.user.mapper.UserMapper;

public class ExistUserIdValidator implements ConstraintValidator<ExistDepartmentId, String> {

	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean isValid(String userId, ConstraintValidatorContext constraintValidatorContext) {
		User user = userMapper.selectOne("userId");
		if(user ==null){
			return false;
		}
		return true;
	}
}
