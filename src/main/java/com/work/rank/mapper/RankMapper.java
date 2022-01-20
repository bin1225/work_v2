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

	void deleteUser(@Param("userId") String userId, @Param("departmentId") Long departmentId);

	List<User> selectUsers(@Param("departmentId") Long departmentId);
}
