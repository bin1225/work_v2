package com.work.common.validate.department;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Constraint(validatedBy = ExistDepartmentValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistDepartmentId {

	String message() default "존재하지 않는 department";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
