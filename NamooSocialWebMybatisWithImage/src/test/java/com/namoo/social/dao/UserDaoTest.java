package com.namoo.social.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.namoo.social.domain.User;

public class UserDaoTest extends DbCommonTest {

	private static final String DATASET_XML = "UserDaoTest_dataset.xml";
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserToUserDao userToUserDao;
	
	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadAllUsers() {
		//
		List<User> userList = userDao.readAllUsers();
		
		assertEquals(4, userList.size());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadUser() {
		//
		User user = userDao.readUser("user1");
		
		assertEquals("user1", user.getUserId());
		assertEquals("유저1", user.getName());
		assertEquals("user1@msg.com", user.getEmail());
		assertEquals("1234", user.getPassword());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadAllFollowings() {
		//
		List<User> followingList = userDao.readAllFollowings("user1");
		
		assertEquals(2, followingList.size());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadAllFollowers() {
		//
		List<User> followerList = userDao.readAllFollowers("user1");
		
		assertEquals(1, followerList.size());
	}
	
	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadAllFollowingsNotYet() {
		//
		List<User> notYetList = userDao.readAllFollowingsNotYet("user1");
		
		for (User user : notYetList) {
			System.out.println(user.getUserId());
		}
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testCreateUser() {
		//
		User user = new User("user5", "유저5", "user5@msg.com", "1234");
		userDao.createUser(user);
		
		user = userDao.readUser("user5");
		
		assertEquals("user5", user.getUserId());
		assertEquals("유저5", user.getName());
		assertEquals("user5@msg.com", user.getEmail());
		assertEquals("1234", user.getPassword());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testUpdateUser() {
		//
		User user = new User("user1", "유저5", "user5@msg.com", "1234");
		userDao.updateUser(user);
		
		user = userDao.readUser("user1");
		
		assertEquals("user1", user.getUserId());
		assertEquals("유저5", user.getName());
		assertEquals("user5@msg.com", user.getEmail());
		assertEquals("1234", user.getPassword());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testDeleteUser() {
		//
		userDao.deleteUser("user1");
		User user = userDao.readUser("user1");
		
		assertNull(user);
	}

}
