package com.work.rank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.work.common.argumentResolver.UserInfo;
import com.work.common.dto.Result;
import com.work.rank.Rank;
import com.work.rank.service.RankService;
import com.work.user.User;
import com.work.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RankController {



	@Autowired
	private RankService rankService;

	@Autowired
	private UserService userService;

	@PostMapping("/department/user")
	public Result<Rank> addUser(@Valid @RequestBody Rank rank, @UserInfo User user) {
		log.info("parameter = rank : " +rank.toString());
		userService.checkIsManager(user.getId());
		rank.setCreateId(user.getId());
		rank.setUpdateId(user.getId());
		rankService.addUser(rank);


		return Result.<Rank>builder()
			.success(true)
			.data(rank)
			.build();
	}

	@PutMapping("/department/user")
	public Result<Rank> updateUser(@Valid @RequestBody Rank rank, @UserInfo User user) {
		log.info("parameter = rank : " +rank.toString());
		userService.checkIsManager(user.getId());
		rank.setUpdateId(user.getId());
		rankService.updateUser(rank);

		return Result.<Rank>builder()
			.success(true)
			.data(rank)
			.build();
	}

	@DeleteMapping("/department/user")
	public Result<Rank> deleteUser(@Valid @RequestBody Rank rank, @UserInfo User user) {
		log.info("parameter = rank : " +rank.toString());
		userService.checkIsManager(user.getId());
		rank.setUpdateId(user.getId());
		rankService.deleteUser(rank);


		return Result.<Rank>builder()
			.success(true)
			.data(rank)
			.build();
	}

	@GetMapping("/department/users")
	public Result<List<User>> getUsersInDepartment(@RequestParam("departmentId") Long departmentId) {
		log.info("parameter = departmentId : " +departmentId.toString());
		List<User> usersInDepartment = rankService.getUsersInDepartment(departmentId);
		return Result.<List<User>>builder()
			.success(true)
			.data(usersInDepartment)
			.build();
	}


}
