package com.namoo.social.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.namoo.social.domain.User;
import com.namoo.social.dto.UserToUser;

public class UserToUserDaoTest extends DbCommonTest {

	private static final String DATASET_XML = "UserToUserDaoTest_dataset.xml";

	@Autowired
	private UserToUserDao userToUserDao;
	@Autowired
	private UserDao userDao;
	
	private UserToUser userToUser = new UserToUser();
	
	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindUserToUser() {
		//
		userToUser.setUserId("user1");
		userToUser.setTargetId("user2");
		boolean existence = userToUserDao.findUserToUser(userToUser);
		
		assertEquals(true, existence);
		
		userToUser.setUserId("user2");
		userToUser.setTargetId("user1");
		existence = userToUserDao.findUserToUser(userToUser);
		
		assertEquals(false, existence);
	}
	
	@Test
	@DatabaseSetup(DATASET_XML)
	public void testCreateUserToUser() {
		//
		userToUser.setUserId("user4");
		userToUser.setTargetId("user2");
		userToUserDao.createUserToUser(userToUser);
		
		List<User> user4Followings = userDao.readAllFollowings("user4");
		
		assertEquals("user2", user4Followings.get(0).getUserId());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testDeleteAllUserToUserByUserId() {
		//
		userToUserDao.deleteAllUserToUserByUserId("user1");
		List<User> followers = userDao.readAllFollowers("user1");
		List<User> followings = userDao.readAllFollowings("user1");
		
		assertEquals(0, followers.size());
		assertEquals(0, followings.size());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testDeleteUserToUser() {
		//
		userToUser.setUserId("user2");
		userToUser.setTargetId("user3");
		userToUserDao.deleteUserToUser(userToUser);
		
		List<User> users = userDao.readAllFollowings("user2");
		
		assertEquals(0, users.size());
	}

}
