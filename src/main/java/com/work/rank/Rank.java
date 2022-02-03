package com.work.rank;

import javax.validation.constraints.NotNull;

import com.work.common.validate.department.ExistDepartmentId;
import com.work.common.validate.user.ExistUserId;

import lombok.Data;

@Data
public class Rank {

	private Long id;

	@NotNull
	@ExistUserId
	private String userId;

	@NotNull
	@ExistDepartmentId
	private Long departmentId;

	private String userRank;
	private String available;

	private String createId;
	private String updateId;

	public Rank(){}

}
