package com.work.rank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.rank.Rank;
import com.work.rank.mapper.RankMapper;
import com.work.user.User;

@Service
public class RankServiceImpl implements RankService {

	@Autowired
	private RankMapper rankMapper;

	public void addUser(Rank rank) {
		rankMapper.insertUser(rank);
	}

	public void updateUser(Rank rank) {
		rankMapper.updateUser(rank);
	}

	public void deleteUser(String userId, Long departmentId) {
		rankMapper.deleteUser(userId, departmentId);
	}

	public List<User> getUsersInDepartment(Long departmentId) {
		return rankMapper.selectUsers(departmentId);
	}
}
