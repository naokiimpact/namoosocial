package com.namoo.social.web.press;

public class Count {
	
	private int msgCount;
	private int followingCount;
	private int followerCount;
	
	public Count() {}

	public Count(int msgCount, int followingCount, int followerCount) {
		//
		this.msgCount = msgCount;
		this.followingCount = followingCount;
		this.followerCount = followerCount;
	}

	public int getMsgCount() {
		return msgCount;
	}

	public void setMsgCount(int msgCount) {
		this.msgCount = msgCount;
	}

	public int getFollowingCount() {
		return followingCount;
	}

	public void setFollowingCount(int followingCount) {
		this.followingCount = followingCount;
	}

	public int getFollowerCount() {
		return followerCount;
	}

	public void setFollowerCount(int followerCount) {
		this.followerCount = followerCount;
	}
	
}
