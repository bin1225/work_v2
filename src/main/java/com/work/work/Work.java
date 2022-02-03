package com.work.work;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;

import lombok.Data;

@Data
public class Work {

	private int id;
	private String userId;

	private String dateStart;
	private String dateFinish;
	private Time timeStart;
	private Time timeFinish;


	public Work(String userId) {
		this.userId = userId;
	}
}
