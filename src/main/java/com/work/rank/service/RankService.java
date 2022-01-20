package com.work.rank.service;

import java.util.List;

import com.work.user.User;
import com.work.rank.Rank;

public interface RankService {


	void addUser(Rank rank);

	void updateUser(Rank rank);

	void deleteUser(String userId, Long departmentId);

	List<User> getUsersInDepartment(Long departmentId);
}
