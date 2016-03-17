package com.namoo.social.service.facade;

import com.namoo.social.domain.Message;
import com.namoo.social.web.press.Page;
import com.namoo.social.web.press.PageCondition;

public interface MessageService {
	//
	Page<Message> findAllMessagesByUserId(String userId, PageCondition pageCondition);
	int findAllMessagesByUserIdCount(String userId);
	Page<Message> findTimeline(String userId, PageCondition pageCondition);
	int findTimelineCount(String userId);
	Page<Message> findAllMessagesFromFollowings(String userId, PageCondition pageCondition);
	int findAllMessagesFromFollowingsCount(String userId);
	Page<Message> findAllMessagesFromFollowers(String userId, PageCondition pageCondition);
	int findAllMessagesFromFollowersCount(String userId);
	Message findMessage(int msgNo);
	
	int writeMessage(Message message);
	
	void modifyMessage(Message message);
	
	void removeMessage(int msgNo);
}
