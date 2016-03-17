package com.namoo.social.service.facade;

import java.util.List;

import com.namoo.social.domain.User;
import com.namoo.social.dto.UserToUser;

public interface UserService {
	//
	List<User> findAllUsers();
	List<User> findUsersByKeyword(String keyword);
	User findUser(String userId);
	List<User> findAllFollowings(String userId);
	List<User> findAllFollowers(String userId);
	List<User> findRecommandableFollowings(String userId);
	List<User> findAllFollowingsNotYet(String userId);
	List<User> findAllFriends(String userId);
//	
//	List<UserStatus> findAllFriendsStatus(String userId);
//	UserStatus findUserStatus(String userId);
//	
	void joinAsMember(User user);
	
	boolean loginAsMember(User user);
	
	void addFollowing(UserToUser userToUser);
	
	void modifyUser(User user);
	void modifyUserProfile(User user);
	void modifyUserStatus(User user);
	
	void withdrawalFromSocial(String userId);
	
	void removeFollowing(UserToUser userToUser);
}
