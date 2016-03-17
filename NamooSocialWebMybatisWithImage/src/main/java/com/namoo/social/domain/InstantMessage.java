package com.namoo.social.domain;

import java.util.Date;

public class InstantMessage {

	private int instantNo;
	private User sender;
	private User recipient;
	private String message;
	private Date regDate;
	
	//--------------------------------------------------------------------------
	
	public InstantMessage() {}
	
	public InstantMessage(int instantNo, User sender, User recipient, String message) {
		//
		this.instantNo = instantNo;
		this.sender = sender;
		this.recipient = recipient;
		this.message = message;
	}
	
	public InstantMessage(int instantNo, User sender, User recipient, String message, Date regDate) {
		//
		this.instantNo = instantNo;
		this.sender = sender;
		this.recipient = recipient;
		this.message = message;
		this.regDate = regDate;
	}

	//--------------------------------------------------------------------------
	
	public int getInstantNo() {
		return instantNo;
	}
	
	public void setInstantNo(int instantNo) {
		this.instantNo = instantNo;
	}
	
	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

}
