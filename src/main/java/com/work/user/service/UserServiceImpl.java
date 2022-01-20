package com.work.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.common.dto.LoginInfo;
import com.work.user.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	public void login(LoginInfo loginInfo) throws IllegalArgumentException {
		if (!userMapper.selectLoginInformation(loginInfo)) {
			throw new IllegalArgumentException();
		}
	}

	public boolean checkIsManager(String userId) throws IllegalStateException {
		return userMapper.checkIsManager(userId);
	}
}
