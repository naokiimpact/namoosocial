package com.namoo.social.domain;

import java.util.Date;

public class UserStatus {
	
	private boolean userStatus;
	private Date renewDate;
	
	//--------------------------------------------------------------------------
	
	public UserStatus() {}
	
	public UserStatus(boolean userSatus) {
		//
		this.userStatus = userSatus;
	}
	
	public UserStatus(boolean userStatus, Date renewDate) {
		//
		this.userStatus = userStatus;
		this.renewDate = renewDate;
	}
	
	//--------------------------------------------------------------------------	

	public boolean isUserStatus() {
		return userStatus;
	}

	public void setUserStatus(boolean userStatus) {
		this.userStatus = userStatus;
	}

	public Date getRenewDate() {
		return renewDate;
	}

	public void setRenewDate(Date renewDate) {
		this.renewDate = renewDate;
	}
}
