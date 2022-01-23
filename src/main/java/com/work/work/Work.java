package com.work.work;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Work {

	private int id;
	private String userId;
	private LocalDateTime start;
	private LocalDateTime end;

	public Work(String userId) {
		this.userId = userId;
		this.start = LocalDateTime.now();
	}
}
