package com.namoo.social.web.press;

import com.namoo.social.domain.User;

public class UserState {
	
	private User user;
	private boolean followed = false;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isFollowed() {
		return followed;
	}
	public void setFollowed(boolean followed) {
		this.followed = followed;
	}

	
	
}
