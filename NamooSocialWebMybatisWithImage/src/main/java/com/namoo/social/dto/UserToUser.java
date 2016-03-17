package com.namoo.social.dto;

public class UserToUser {

	private String userId;
	private String targetId;
	
	//--------------------------------------------------------------------------
	
	public UserToUser() {}
	
	public UserToUser(String userId, String targetId) {
		//
		this.userId = userId;
		this.targetId = targetId;
	}

	//--------------------------------------------------------------------------
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	
}
