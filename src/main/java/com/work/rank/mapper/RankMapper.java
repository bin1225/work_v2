package com.work.rank.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.work.user.User;
import com.work.rank.Rank;

@Mapper
public interface RankMapper {
	void insertUser(Rank rank);

	void updateUser(Rank rank);

	void deleteUser(Rank rank);

	List<User> selectUsers(@Param("departmentId") Long departmentId);

	void insertHistory(Rank rank);

	Rank selectOne(Long id);
}
