package com.work.rank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.work.rank.Rank;
import com.work.rank.mapper.RankMapper;
import com.work.user.User;

@Service
public class RankServiceImpl implements RankService {

	@Autowired
	private RankMapper rankMapper;

	@Transactional
	public void addUser(Rank rank) {
		rankMapper.insertUser(rank);
		rankMapper.insertHistory(rank);
	}

	@Transactional
	public void updateUser(Rank rank) {
		rankMapper.updateUser(rank);
		rankMapper.insertHistory(rank);
	}

	@Transactional
	public void deleteUser(Rank rank) {
		rankMapper.deleteUser(rank);
		rankMapper.insertHistory(rank);
	}

	public List<User> getUsersInDepartment(Long departmentId) {
		return rankMapper.selectUsers(departmentId);
	}
}
