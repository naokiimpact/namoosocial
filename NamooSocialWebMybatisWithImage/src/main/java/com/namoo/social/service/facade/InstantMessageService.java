package com.namoo.social.service.facade;

import java.util.Date;
import java.util.List;

import com.namoo.social.domain.InstantMessage;

public interface InstantMessageService {

	List<InstantMessage> findAllInstantMessagesByUserId(String userId);
	
	int sendInstantMessage(InstantMessage instant);
	
	void removeInstantMessagesByUserId(String userId);
	void removeInstantMessagesByUserIdAndDate(String userId, Date lastRead);
}
