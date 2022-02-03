package com.work.common.interceptor;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.work.common.exception.user.LoginException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		log.info("Interceptor login validate");

		if(request.getCookies()==null){
			log.info("login fail : userIdCookie = null");
			throw new LoginException(400,request.getCookies());
		}

		Optional<Cookie> userIdCookie= Arrays.stream(request.getCookies())
			.filter(cookie -> cookie.getName().equals("userId"))
			.findAny();
		if (userIdCookie.isPresent()) {
			log.info("login success : userId = " + userIdCookie.get().getValue());
			return true;
		}
		log.error("login fail : userId = " +userIdCookie.get().getValue());
		return false;
	}

}
