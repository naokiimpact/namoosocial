package com.namoo.social.dao;

import java.util.Date;
import java.util.List;

import com.namoo.social.domain.InstantMessage;

public interface InstantMessageDao {
	//
	List<InstantMessage> readAllInstantMessagesByUserId(String userId);
	
	int createInstantMessage(InstantMessage instant);
	
	void deleteInstantMessagesByUserId(String userId);
	void deleteInstantMessagesByUserIdAndDate(String userId, Date lastRead);
}
