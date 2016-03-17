package com.namoo.social.dao.sqlmap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.namoo.social.dao.MessageDao;
import com.namoo.social.dao.sqlmap.mapper.MessageMapper;
import com.namoo.social.domain.Message;
import com.namoo.social.web.press.PageCondition;

@Repository
public class MessageDaoSqlMap implements MessageDao {

	@Autowired
	private MessageMapper messageMapper;
	
	@Override
	public List<Message> readAllMessagesByUserId(String userId,
			PageCondition pageCondition) {
		//
		return messageMapper.selectAllMessagesByUserId(userId, pageCondition);
	}

	@Override
	public int readAllMessagesByUserIdCount(String userId) {
		// 
		return messageMapper.selectAllMessagesByUserIdCount(userId);
	}

	@Override
	public List<Message> readTimeline(String userId, PageCondition pageCondition) {
		//
		return messageMapper.selectTimeline(userId, pageCondition);
	}

	@Override
	public int readTimelineCount(String userId) {
		// 
		return messageMapper.selectTimelineCount(userId);
	}

	@Override
	public List<Message> readAllMessagesFromFollowings(String userId,
			PageCondition pageCondition) {
		// 
		return messageMapper.selectAllMessagesFromFollowings(userId, pageCondition);
	}

	@Override
	public int readAllMessagesFromFollowingsCount(String userId) {
		//
		return messageMapper.selectAllMessagesFromFollowingsCount(userId);
	}

	@Override
	public List<Message> readAllMessagesFromFollowers(String userId,
			PageCondition pageCondition) {
		//
		return messageMapper.selectAllMessagesFromFollowers(userId, pageCondition);
	}

	@Override
	public int readAllMessagesFromFollowersCount(String userId) {
		//
		return messageMapper.selectAllMessagesFromFollowersCount(userId);
	}

	@Override
	public Message readMessage(int msgNo) {
		//
		return messageMapper.selectMessage(msgNo);
	}

	@Override
	public int createMessage(Message message) {
		//
		messageMapper.insertMessage(message);
		return message.getMsgNo();
	}

	@Override
	public void updateMessage(Message message) {
		//
		messageMapper.updateMessage(message);
	}

	@Override
	public void deleteMessage(int msgNo) {
		//
		messageMapper.deleteMessage(msgNo);
	}

}
