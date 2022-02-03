package com.work.department;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.work.common.validate.department.ExistDepartmentId;
import com.work.rank.Rank;

import lombok.Data;

@Data
@Alias("department")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Department {

	private Long id;

	@NotNull(message = "name should not be null")
	private String name;
	@NotNull(message = "parent should not be null")
	private Long parentId;

	private String available;
	private int depth;

	private String createId;
	private String updateId;


	private List<Department> children;

	public Department() {
	}

	public Department(String name, Long parentId) {
		this.name = name;
		this.parentId = parentId;

	}


	public void addChild(Department department) {
		if(Objects.isNull(children)){
			children = new ArrayList<>();
		}
		this.children.add(department);
	}

}
