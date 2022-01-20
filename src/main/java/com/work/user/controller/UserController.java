package com.work.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.work.common.dto.Result;
import com.work.common.dto.LoginInfo;
import com.work.user.User;
import com.work.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user/login")
	public Result<User> login(@ModelAttribute LoginInfo loginInfo, HttpSession httpSession) {

		try {
			userService.login(loginInfo);
			httpSession.setAttribute("Login","true");
			httpSession.setAttribute("id",loginInfo.getId());
			httpSession.setAttribute("Admin",userService.checkIsManager(loginInfo.getId()));

		} catch (IllegalArgumentException e) {
			return Result.<User>builder()
				.success(false)
				.message("로그인 실패, 회원정보가 존재하지 않습니다." + loginInfo.getId())
				.build();
		}

		return Result.<User>builder()
			.success(true)
			.build();
	}
}
