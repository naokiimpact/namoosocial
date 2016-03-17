package com.namoo.social.service.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namoo.social.dao.UserDao;
import com.namoo.social.dao.UserToUserDao;
import com.namoo.social.domain.User;
import com.namoo.social.domain.UserStatus;
import com.namoo.social.dto.UserToUser;
import com.namoo.social.service.facade.UserService;

@Service
public class UserServiceLogic implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserToUserDao userToUserDao;
	
	@Override
	public List<User> findAllUsers() {
		//
		return userDao.readAllUsers();
	}
	
	@Override
	public List<User> findUsersByKeyword(String keyword) {
		//
		return userDao.readUsersByKeyword(keyword);
	}

	@Override
	public User findUser(String userId) {
		//
		return userDao.readUser(userId);
	}

	@Override
	public List<User> findAllFollowings(String userId) {
		//
		return userDao.readAllFollowings(userId);
	}

	@Override
	public List<User> findAllFollowers(String userId) {
		//
		return userDao.readAllFollowers(userId);
	}
	
	@Override
	public List<User> findAllFollowingsNotYet(String userId) {
		//
		return userDao.readAllFollowingsNotYet(userId);
	}
	
	@Override
	public List<User> findAllFriends(String userId) {
		//
		return userDao.readAllFriends(userId);
	}
	
	@Override
	public List<User> findRecommandableFollowings(String userId) {
		//
		List<User> recoMemberList = new ArrayList<User>();
		List<User> memberList = userDao.readAllFollowingsNotYet(userId);
		Random r = new Random();
		int memberSize = memberList.size();
		if (memberSize > 3) {
			//
			while(true) {
				//
				User user = memberList.get(r.nextInt(memberSize));

				if(recoMemberList.contains(user)) 
					continue;
				
				recoMemberList.add(user);
				
				if(recoMemberList.size() == 3) break;
			}
			return recoMemberList;
			
		} else {
			return memberList;
		}
	}
	
//	@Override
//	public List<UserStatus> findAllFriendsStatus(String userId) {
//		//
//		return userStatusDao.readAllFriendsStatus(userId);
//	}
//
//	@Override
//	public UserStatus findUserStatus(String userId) {
//		//
//		return userStatusDao.readUserStatus(userId);
//	}
//	
	
	@Override
	public void joinAsMember(User user) {
		//
		User member = userDao.readUser(user.getUserId());
		if(member != null) {
			System.out.println("이미 존재하는 회원입니다.");
			// TODO: 에러 발생시키기
		} else {
			userDao.createUser(user);
			System.out.println(user.getUserId() + "님의 회원가입이 완료되었습니다.");
		}
	}
	
	@Override
	public boolean loginAsMember(User user) {
		//
		User member = userDao.readUser(user.getUserId());
		if (member != null && member.getPassword().equals(user.getPassword())) {
			UserStatus userStatus = new UserStatus(true);
			user.setUserStatus(userStatus);
			userDao.updateUserStatus(user);
			return true;
		}

		return false;
	}

	@Override
	public void addFollowing(UserToUser userToUser) {
		//
		if(userToUserDao.findUserToUser(userToUser)) {
			System.out.println("이미 팔로우하고 있습니다.");
			// TODO: 에러 발생시키기
		} else {
			userToUserDao.createUserToUser(userToUser);
			System.out.println(userToUser.getUserId() + "님이 "
					+ userToUser.getTargetId() + "님을 팔로우하였습니다.");
		}
	}

	@Override
	public void modifyUser(User user) {
		//
		User member = userDao.readUser(user.getUserId());
		if(member == null) {
			System.out.println("존재하지 않는 회원입니다.");
			// TODO: 에러 발생시키기
		} else {
			userDao.updateUser(user);
			System.out.println(user.getUserId() + "님의 정보가 수정되었습니다.");
		}
	}
	
	@Override
	public void modifyUserProfile(User user) {
		// 
		User member = userDao.readUser(user.getUserId());
		if(member == null) {
			System.out.println("존재하지 않는 회원입니다.");
			// TODO: 에러 발생시키기
		} else {
			userDao.updateUserProfile(user);
			System.out.println(user.getUserId() + "님의 프로필이 수정되었습니다.");
		}
	}
	
	@Override
	public void modifyUserStatus(User user) {
		//
		userDao.updateUserStatus(user);
	}
	

	@Override
	public void withdrawalFromSocial(String userId) {
		//
		User member = userDao.readUser(userId);
		if(member == null) {
			System.out.println("존재하지 않는 회원입니다.");
			// TODO: 에러 발생시키기
		} else {
			userToUserDao.deleteAllUserToUserByUserId(userId);
			System.out.println(userId + "님의 팔로잉, 팔로워가 전부 삭제되었습니다.");
			userDao.deleteUser(userId);
			System.out.println(userId + "님의 탈퇴신청이 처리되었습니다.");
		}
	}

	@Override
	public void removeFollowing(UserToUser userToUser) {
		//
		if(userToUserDao.findUserToUser(userToUser) == false) {
			System.out.println("팔로우하고 있지 않습니다.");
			// TODO: 에러 발생시키기
		} else {
			userToUserDao.deleteUserToUser(userToUser);
			System.out.println(userToUser.getUserId() + "님이 "
					+ userToUser.getTargetId() + "님을 더 이상 팔로우하지 않습니다.");
		}
	}

}
