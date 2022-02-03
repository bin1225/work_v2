package com.work.common.argumentResolver;


import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.work.user.User;
import com.work.user.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserResolver implements HandlerMethodArgumentResolver {

	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterAnnotation(UserInfo.class) != null
			&& parameter.getParameterType().equals(User.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {


		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();

		Cookie[] cookies = request.getCookies();

		Optional<Cookie> userIdCookie= Arrays.stream(cookies)
			.filter(cookie -> cookie.getName().equals("userId"))
			.findAny();


		User user = userMapper.selectOne(userIdCookie.get().getValue());

		log.info("userArgumentResolver : userId = "+ user.getId());

		return user;
	}
}
