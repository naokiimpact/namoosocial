package com.namoo.social.service.facade;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.namoo.social.dao.DbCommonTest;
import com.namoo.social.domain.User;
import com.namoo.social.dto.UserToUser;

public class UserServiceTest extends DbCommonTest {

	private static final String DATASET_XML = "UserServiceTest_dataset.xml";
	
	@Autowired
	private UserService userService;
	
	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindAllUsers() {
		//
		List<User> userList = userService.findAllUsers();
		
		assertEquals(4, userList.size());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindUser() {
		//
		User user = userService.findUser("user1");
		
		assertEquals("user1", user.getUserId());
		assertEquals("유저1", user.getName());
		assertEquals("user1@msg.com", user.getEmail());
		assertEquals("1234", user.getPassword());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindAllFollowings() {
		//
		List<User> followingList = userService.findAllFollowings("user1");
		
		assertEquals(2, followingList.size());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindAllFollowers() {
		//
		List<User> followerList = userService.findAllFollowers("user1");
		
		assertEquals(1, followerList.size());
	}
	
	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindAllFollowingsNotYet() {
		//
		List<User> followingNotYetList = userService.findAllFollowingsNotYet("user1");
		
		assertEquals(1, followingNotYetList.size());
	}
	
	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindRecommandableFollowings() {
		//
		List<User> reLi = userService.findRecommandableFollowings("user1");
		
		for (User user : reLi) {
			System.out.println("아이디" + user.getUserId());
		}
	}
	
	@Test
	@DatabaseSetup(DATASET_XML)
	public void testJoinAsMember() {
		//
		User user = new User("user5", "유저5", "user5@msg.com", "1234");
		userService.joinAsMember(user);
		
		user = userService.findUser("user5");
		
		assertEquals("user5", user.getUserId());
		assertEquals("유저5", user.getName());
		assertEquals("user5@msg.com", user.getEmail());
		assertEquals("1234", user.getPassword());
	}
	
	@Test
	@DatabaseSetup(DATASET_XML)
	public void testLoginAsMember() {
		//
		User user = new User("user1", "1234");
		boolean login = userService.loginAsMember(user);
		
		assertEquals(true , login);
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testAddFollowing() {
		//
		UserToUser userToUser = new UserToUser();
		userToUser.setUserId("user4");
		userToUser.setTargetId("user2");
		userService.addFollowing(userToUser);
		
		List<User> user4Followings = userService.findAllFollowings("user4");
		
		assertEquals("user2", user4Followings.get(0).getUserId());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testModifyUser() {
		//
		User user = new User("user1", "유저5", "user5@msg.com", "1234");
		userService.modifyUser(user);
		
		user = userService.findUser("user1");
		
		assertEquals("user1", user.getUserId());
		assertEquals("유저5", user.getName());
		assertEquals("user5@msg.com", user.getEmail());
		assertEquals("1234", user.getPassword());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testWithdrawalFromSocial() {
		//
		userService.withdrawalFromSocial("user1");
		
		User user = userService.findUser("user1");
		List<User> followers = userService.findAllFollowers("user1");
		List<User> followings = userService.findAllFollowings("user1");
		
		assertNull(user);
		assertEquals(0, followers.size());
		assertEquals(0, followings.size());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testRemoveFollowing() {
		//
		UserToUser userToUser = new UserToUser();
		userToUser.setUserId("user2");
		userToUser.setTargetId("user3");
		userService.removeFollowing(userToUser);
		
		List<User> users = userService.findAllFollowings("user2");
		
		assertEquals(0, users.size());
	}

}
