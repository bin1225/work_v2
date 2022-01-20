package com.work.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.work.rank.Rank;

import lombok.Data;

@Data
@Alias("user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

	private String name;
	private String id;
	private String password;
	private String available;

	private List<Rank> ranks;

	public void addRelation(Rank relation) {
		if (Objects.isNull(ranks)) {
			ranks = new ArrayList<>();
		}
		ranks.add(relation);
	}

	public User() {
	}
}
