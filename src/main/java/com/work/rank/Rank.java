package com.work.rank;

import lombok.Data;

@Data
public class Rank {
	private String userId;
	private Long departmentId;
	private String userRank;

	public Rank(){}

	public Rank add(String userId, Long departmentId, String userRank) {
		this.userId = userId;
		this.departmentId = departmentId;
		this.userRank = userRank;

		return this;
	}
}
