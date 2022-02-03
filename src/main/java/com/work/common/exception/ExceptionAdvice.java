package com.work.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.work.common.dto.Result;
import com.work.common.exception.user.LoginException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(Exception.class)
	public String catchRemainder(Exception e) {
		return e.getMessage() + " -> server error";
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public String catchArgument(IllegalArgumentException e) {
		log.info("IllegalArgumentException : "+ e.getMessage());
		return e.getMessage();
	}

	@ExceptionHandler(IllegalStateException.class)
	public String catchState(IllegalStateException e) {
		log.info("IllegalStateException : "+ e.getMessage());
		return e.getMessage();
	}

	@ExceptionHandler(LoginException.class)
	public Result loginValidate(LoginException e) {
		log.info("LoginException : "+ e.getMessage());
		return Result.<Object>builder()
			.data(e.getBody())
			.failCode(e.getFailCode())
			.build();

	}
}
