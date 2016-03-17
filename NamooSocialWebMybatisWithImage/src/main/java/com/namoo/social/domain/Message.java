package com.namoo.social.domain;

import java.util.Date;

public class Message {

	private int msgNo;
	private String contents;
	private User writer;
	private Date regDate;
	
	//--------------------------------------------------------------------------
	
	public Message() {}
	
	public Message(int msgNo, String contents, User writer, Date regDate) {
		//
		this.msgNo = msgNo;
		this.contents = contents;
		this.writer = writer;
		this.regDate = regDate;
	}
	
	public Message(String contents, User writer) {
		//
		this.contents = contents;
		this.writer = writer;
	}
	
	public Message(int msgNo, String contents) {
		//
		this.msgNo = msgNo;
		this.contents = contents;
	}
	
	//--------------------------------------------------------------------------
	
	public int getMsgNo() {
		return msgNo;
	}
	public void setMsgNo(int msgNo) {
		this.msgNo = msgNo;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public User getWriter() {
		return writer;
	}
	public void setWriter(User writer) {
		this.writer = writer;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
}
