package com.work.department.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.work.common.argumentResolver.UserInfo;
import com.work.common.dto.Result;
import com.work.department.Department;
import com.work.department.service.DepartmentService;
import com.work.user.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@PostMapping("/department")
	public Result<String> createDepartment(@RequestBody @Valid Department department,BindingResult result, @UserInfo User user) {

		if(result.hasErrors()) {
			log.info("effectiveness");
			throw new IllegalArgumentException("effectiveness fail");
		}

		log.info("parameter = department : " +department.toString());
		departmentService.checkManager(user.getId());
		department.setCreateId(user.getId());
		department.setUpdateId(user.getId());
		departmentService.createDepartment(department);

		return Result.<String>builder()
			.success(true)
			.data("departmentId : "+ department.getId())
			.build();
	}

	@DeleteMapping("/department/{id}")
	public Result deleteDepartment(@PathVariable("id") Long id, @UserInfo User user) {
		log.info("parameter = departmentId : " + id );
		departmentService.checkManager(user.getId());
		departmentService.removeDepartment(id,user.getId());

		return Result.builder()
			.success(true)
			.build();
	}

	@GetMapping("/department/tree")
	public Result<Department> getDepartmentTree() {
		Department root = departmentService.getAllDepartmentsTree();
		return Result.<Department>builder()
			.success(true)
			.data(root)
			.build();
	}

}
