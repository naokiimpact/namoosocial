package com.namoo.social.dao.sqlmap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.namoo.social.dao.UserDao;
import com.namoo.social.dao.sqlmap.mapper.UserMapper;
import com.namoo.social.domain.User;

@Repository
public class UserDaoSqlMap implements UserDao {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> readAllUsers() {
		// 
		return userMapper.selectAllUsers();
	}
	
	@Override
	public List<User> readUsersByKeyword(String keyword) {
		//
		return userMapper.selectUsersByKeyword(keyword);
	}

	@Override
	public User readUser(String userId) {
		// 
		return userMapper.selectUser(userId);
	}

	@Override
	public List<User> readAllFollowings(String userId) {
		// 
		return userMapper.selectAllFollowings(userId);
	}

	@Override
	public List<User> readAllFollowers(String userId) {
		// 
		return userMapper.selectAllFollowers(userId);
	}

	@Override
	public List<User> readAllFollowingsNotYet(String userId) {
		// 
		return userMapper.selectAllFollowingsNotYet(userId);
	}
	
	@Override
	public List<User> readAllFriends(String userId) {
		//
		return userMapper.selectAllFriends(userId);
	}

	@Override
	public void createUser(User user) {
		// 
		userMapper.insertUser(user);
	}

	@Override
	public void updateUser(User user) {
		// 
		userMapper.updateUser(user);
	}
	
	@Override
	public void updateUserProfile(User user) {
		//
		userMapper.updateUserProfile(user);
	}
	
	@Override
	public void updateUserStatus(User user) {
		//
		userMapper.updateUserStatus(user);
	}

	@Override
	public void deleteUser(String userId) {
		// 
		userMapper.deleteUser(userId);
	}

}
