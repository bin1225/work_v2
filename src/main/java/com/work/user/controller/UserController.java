package com.work.user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.work.common.dto.LoginInfo;
import com.work.common.dto.Result;
import com.work.user.User;
import com.work.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user/login")
	public Result<User> login(@RequestBody LoginInfo loginInfo, HttpServletResponse response,
		HttpSession httpSession) {

		try {
			userService.login(loginInfo);
			Cookie cookie = new Cookie("userId", loginInfo.getId());
			cookie.setPath("/");
			response.addCookie(cookie);

			System.out.println("session value" + httpSession.getAttribute("Admin"));
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
