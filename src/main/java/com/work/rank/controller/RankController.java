package com.work.rank.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.work.common.dto.Result;
import com.work.rank.Rank;
import com.work.rank.service.RankService;
import com.work.user.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RankController {

	private static final String TRUE = "true";
	@Autowired
	private RankService rankService;

	private final static Result.ResultBuilder<Rank> RESULT_BUILDER = Result.<Rank>builder()
		.success(true);

	@PostMapping("/department/user")
	public Result<Rank> addUser(@ModelAttribute Rank rank, HttpSession httpSession) {
		isManager(httpSession);

		rankService.addUser(rank);
		return RESULT_BUILDER.build();
	}

	@PutMapping("/department/user")
	public Result<Rank> updateUser(Rank rank, HttpSession httpSession) {
		isManager(httpSession);

		rankService.updateUser(rank);
		return RESULT_BUILDER.build();
	}

	@DeleteMapping("/department/user")
	public Result<Rank> deleteUser(Rank rank, HttpSession httpSession) {
		Result.ResultBuilder<Rank> result = Result.<Rank>builder();
		isManager(httpSession);

		rankService.deleteUser(rank.getUserId(), rank.getDepartmentId());

		return RESULT_BUILDER.build();
	}

	@GetMapping("/department/users")
	public Result<List<User>> getUsersInDepartment(@RequestParam("departmentId") Long departmentId) {
		List<User> usersInDepartment = rankService.getUsersInDepartment(departmentId);
		return Result.<List<User>>builder()
			.success(true)
			.data(usersInDepartment)
			.build();
	}


	void isManager(HttpSession httpSession) {
		if (httpSession.getAttribute("Admin") != "true") {
			throw new IllegalStateException();
		}
	}
}
