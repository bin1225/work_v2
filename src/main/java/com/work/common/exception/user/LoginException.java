package com.work.common.exception.user;

import org.springframework.http.HttpStatus;

public class LoginException extends UserException {
	public LoginException(Integer failCode, Object body) {
		super(failCode, body);
	}
}
