package com.work.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Result<T> {
	private boolean success;
	T data;
	private Integer failCode;
	private String message;

}
