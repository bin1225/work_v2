package com.work.user;

import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Alias("user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

	@NotNull
	private String name;
	@NotNull
	private String id;
	@NotNull
	private String password;
	private String available;

	public User() {
	}
}
