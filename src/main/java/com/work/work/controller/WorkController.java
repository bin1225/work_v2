package com.work.work.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.work.common.argumentResolver.UserInfo;
import com.work.user.User;
import com.work.work.Work;
import com.work.work.service.WorkService;

@RestController
public class WorkController {

	@Autowired
	private WorkService workService;

	@PostMapping("work/start")
	public void start(@UserInfo User user, HttpServletResponse response) {
		Work work = workService.startWork(user.getId());
		Cookie cookie = new Cookie("working",String.valueOf(work.getId()));
		cookie.setPath("/work");
		response.addCookie(cookie);
	}

	@PostMapping("work/finish")
	public void end(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Cookie[] cookies = request.getCookies();
		int workingId = 0;
		for(Cookie c : cookies){
			if(c.getName().equals("working")){
				workingId = Integer.valueOf(c.getValue());
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}
		if(workingId ==0){
			response.sendError(400,"현재 일을 진행중인 상태가 아닙니다.");
		}

		workService.finishWork(workingId);
	}
}
