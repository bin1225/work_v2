package com.work.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(IllegalArgumentException.class)
	public String ExceptionCatch(Exception e) {

		return "Illegal Exception 발생 !!";
	}

	@ExceptionHandler(IllegalStateException.class)
	public String isManager(Exception e) {
		return "관리자 권한이 필요한 기능입니다.";
	}
}
