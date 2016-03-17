package com.namoo.social.dao;

import com.namoo.social.dto.UserToUser;

public interface UserToUserDao {
	
	boolean findUserToUser(UserToUser userToUser);

	void createUserToUser(UserToUser userToUser);
	
	void deleteAllUserToUserByUserId(String userId);
	
	void deleteUserToUser(UserToUser userToUser);
}
