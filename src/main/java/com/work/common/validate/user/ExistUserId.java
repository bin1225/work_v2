package com.work.common.validate.user;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.work.common.validate.department.ExistDepartmentValidator;

@Target(ElementType.FIELD)
@Constraint(validatedBy = ExistUserIdValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistUserId {

	String message() default "존재하지 않는 userID 입니다.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
