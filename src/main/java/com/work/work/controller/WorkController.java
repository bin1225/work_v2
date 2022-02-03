package com.work.work.controller;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.work.common.argumentResolver.UserInfo;

import com.work.common.dto.Result;
import com.work.common.dto.SearchStatisticsCondition;
import com.work.user.User;
import com.work.work.Work;

import com.work.work.service.WorkService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class WorkController {

	@Autowired
	private WorkService workService;



	@PostMapping("work/start")
	public void start(@UserInfo User user, HttpServletRequest request, HttpServletResponse response) {
		if (isWorking(request)) {
			throw new IllegalStateException("already start working" + "isWorking : " + isWorking(request));
		}
		Work work = workService.startWork(user.getId());
		Cookie cookie = new Cookie("working", String.valueOf(work.getId()));
		cookie.setPath("/work");
		response.addCookie(cookie);
	}

	@PostMapping("work/finish")
	public void finish(HttpServletRequest request, HttpServletResponse response) {
		if (!isWorking(request)) {
			throw new IllegalStateException(
				"not working now, start work should be precede" + "isWorking : " + isWorking(request));
		}
		Cookie[] cookies = request.getCookies();
		int workingId = 0;
		for (Cookie c : cookies) {
			if (c.getName().equals("working")) {
				workingId = Integer.valueOf(c.getValue());
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}

		workService.finishWork(workingId);
	}

	// @GetMapping("work/count/latecomer")
	// public Result<Integer> getLatecomerCount(@RequestBody @Valid SearchStatisticsCondition searchStatisticsCondition){
	// 	int count = workService.countLatecomers(searchStatisticsCondition);
	// 	return Result.<Integer>builder()
	// 		.success(true)
	// 		.data(count)
	// 		.build();
	// }
	//
	// @GetMapping("work/count/overtime")
	// public Result<Integer> getOvertimeWorkerCount(@RequestBody @Valid DateAndDepartmentId dateAndDepartmentId){
	// 	int count = workService.countOvertimeWorkers(dateAndDepartmentId);
	// 	return Result.<Integer>builder()
	// 		.success(true)
	// 		.data(count)
	// 		.build();
	// }
	//
	// @GetMapping("work/count/night")
	// public Result<Integer> getNightShiftsCount(@RequestBody @Valid DateAndDepartmentId dateAndDepartmentId){
	// 	int count = workService.countNightShifts(dateAndDepartmentId);
	// 	return Result.<Integer>builder()
	// 		.success(true)
	// 		.data(count)
	// 		.build();
	// }


	boolean isWorking(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("working")) {
				return true;
			}
		}
		return false;
	}
}
