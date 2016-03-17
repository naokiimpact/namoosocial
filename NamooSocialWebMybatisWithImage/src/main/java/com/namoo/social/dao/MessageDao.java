package com.namoo.social.dao;

import java.util.List;

import com.namoo.social.domain.Message;
import com.namoo.social.web.press.PageCondition;

public interface MessageDao {

	List<Message> readAllMessagesByUserId(String userId, PageCondition pageCondition);
	int readAllMessagesByUserIdCount(String userId);
	List<Message> readTimeline(String userId, PageCondition pageCondition);
	int readTimelineCount(String userId);
	List<Message> readAllMessagesFromFollowings(String userId, PageCondition pageCondition);
	int readAllMessagesFromFollowingsCount(String userId);
	List<Message> readAllMessagesFromFollowers(String userId, PageCondition pageCondition);
	int readAllMessagesFromFollowersCount(String userId);
	Message readMessage(int msgNo);
	
	int createMessage(Message message);
	
	void updateMessage(Message message);
	
	void deleteMessage(int msgNo);
}
