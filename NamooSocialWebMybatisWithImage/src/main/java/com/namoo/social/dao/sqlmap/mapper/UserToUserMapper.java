package com.namoo.social.dao.sqlmap.mapper;

import com.namoo.social.dto.UserToUser;

public interface UserToUserMapper {

	UserToUser selectUserToUser(UserToUser userToUser);

	void insertUserToUser(UserToUser userToUser);
	
	void deleteAllUserToUserByUserId(String userId);
	
	void deleteUserToUser(UserToUser userToUser);
	
}
