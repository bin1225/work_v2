package com.work.department.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.work.common.dto.Result;
import com.work.department.Department;
import com.work.department.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@PostMapping("/department")
	public Result<Department> createDepartment(@ModelAttribute Department department, HttpSession httpSession) {
		System.out.println("name:"+ department.getName());
		Result.ResultBuilder<Department> result = Result.<Department>builder().success(true);
		System.out.println("create Controller");
		if (httpSession.getAttribute("Admin") != "true") {
			result.success(false).message("관리자 권한 필요");
		}
		System.out.println("==========check manager-------------");
		try {
			System.out.println("parentId = " + department.getParentId());
			departmentService.createDepartment(department);
		} catch (IllegalArgumentException e) {
			result.success(false)
				.message("상위부서의 depth가 4 이상입니다.");
		}

		return result.data(department)
			.build();
	}

	@DeleteMapping("/department")
	public void deleteDepartment(@PathVariable("id") Long id, HttpSession httpSession, HttpServletResponse response) {
		if (httpSession.getAttribute("Admin") != "true") {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		departmentService.removeDepartment(id);
	}

	@GetMapping("/department/tree")
	public Result<Department> getDepartmentTree() {
		Department department = departmentService.getAllDepartmentsTree();
		return Result.<Department>builder()
			.success(true)
			.data(department)
			.build();
	}

}
