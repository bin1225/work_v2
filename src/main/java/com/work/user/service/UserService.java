package com.work.user.service;

import com.work.common.dto.LoginInfo;

public interface UserService {
	void login(LoginInfo loginInfo);

	boolean checkIsManager(String userId);
}
