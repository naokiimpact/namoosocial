package com.namoo.social.domain;

import java.util.List;

public class User {
	//
	private String userId;
	private String name;
	private String email;
	private String password;

	// 회원 프로필
	private Profile profile;

	// 메시지
	private List<Message> messages;

	// 관계
	private List<User> followings;
	private List<User> followers;
	
	// 상태
	
	private UserStatus userStatus;

	// --------------------------------------------------------------------------

	// 생성자
	//
	public User() {
	}
	
	public User(String userId, String name, String email, String password,
			Profile profile, UserStatus userStatus) {
		//
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.profile = profile;
		this.userStatus = userStatus;
	}

	public User(String userId, String name, String email, String password,
			Profile profile) {
		//
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.profile = profile;
	}

	public User(String userId, String name, String email, String password) {
		//
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public User(String userId, String name, String email, Profile profile) {
		//
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.profile = profile;
	}

	public User(String userId, String name, String email) {
		//
		this.userId = userId;
		this.name = name;
		this.email = email;
	}

	public User(String userId, String password) {
		//
		this.userId = userId;
		this.password = password;
	}

	public User(String userId) {
		//
		this.userId = userId;
	}

	// --------------------------------------------------------------------------
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<User> getFollowings() {
		return followings;
	}

	public void setFollowings(List<User> followings) {
		this.followings = followings;
	}

	public List<User> getFollowers() {
		return followers;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

}
