package com.namoo.social.dao;

import java.util.List;

import com.namoo.social.domain.User;

public interface UserDao {

	List<User> readAllUsers();
	List<User> readUsersByKeyword(String keyword);
	User readUser(String userId);
	List<User> readAllFollowings(String userId);
	List<User> readAllFollowers(String userId);
	List<User> readAllFollowingsNotYet(String userId);
	List<User> readAllFriends(String userId);
	
	void createUser(User user);
	
	void updateUser(User user);
	void updateUserProfile(User user);
	void updateUserStatus(User user);
	
	void deleteUser(String userId);
	
}
