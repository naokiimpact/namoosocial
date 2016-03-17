package com.namoo.social.dao.sqlmap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.namoo.social.dao.UserToUserDao;
import com.namoo.social.dao.sqlmap.mapper.UserToUserMapper;
import com.namoo.social.dto.UserToUser;

@Repository
public class UserToUserDaoSqlMap implements UserToUserDao {

	@Autowired
	private UserToUserMapper userToUserMapper;
	
	@Override
	public boolean findUserToUser(UserToUser userToUser) {
		// 
		UserToUser result = userToUserMapper.selectUserToUser(userToUser);
		if (result == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void createUserToUser(UserToUser userToUser) {
		//
		userToUserMapper.insertUserToUser(userToUser);
	}

	@Override
	public void deleteAllUserToUserByUserId(String userId) {
		//
		userToUserMapper.deleteAllUserToUserByUserId(userId);
	}

	@Override
	public void deleteUserToUser(UserToUser userToUser) {
		//
		userToUserMapper.deleteUserToUser(userToUser);
	}

}
