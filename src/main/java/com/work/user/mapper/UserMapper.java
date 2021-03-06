package com.work.user.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.work.common.dto.LoginInfo;
import com.work.user.User;

@Mapper
public interface UserMapper {

	boolean selectLoginInformation(LoginInfo loginInfo);

	boolean checkIsManager(@Param("userId") String userId);

	User selectOne (@Param("userId") String userId);
}


