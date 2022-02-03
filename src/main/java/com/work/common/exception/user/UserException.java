package com.work.common.exception.user;

import org.springframework.http.HttpStatus;

public class UserException extends RuntimeException {
	private final Integer failCode;
	private final Object body;

	public UserException(Integer failCode, Object body) {
		this.failCode = failCode;
		this.body = body;
	}

	public Integer getFailCode() {
		return failCode;
	}

	public Object getBody() {
		return body;
	}
}
