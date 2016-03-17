package com.namoo.social.dao.sqlmap.mapper;

import java.util.List;

import com.namoo.social.domain.User;

public interface UserMapper {

	List<User> selectAllUsers();
	List<User> selectUsersByKeyword(String keyword);
	User selectUser(String userId);
	List<User> selectAllFollowings(String userId);
	List<User> selectAllFollowers(String userId);
	List<User> selectAllFollowingsNotYet(String userId);
	List<User> selectAllFriends(String userId);
	
	void insertUser(User user);
	
	void updateUser(User user);
	void updateUserProfile(User user);
	void updateUserStatus(User user);
	
	void deleteUser(String userId);
	
}
