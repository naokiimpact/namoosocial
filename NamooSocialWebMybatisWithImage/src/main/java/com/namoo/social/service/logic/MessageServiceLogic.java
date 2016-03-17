package com.namoo.social.service.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namoo.social.dao.MessageDao;
import com.namoo.social.dao.UserDao;
import com.namoo.social.domain.Message;
import com.namoo.social.domain.User;
import com.namoo.social.service.facade.MessageService;
import com.namoo.social.web.press.Page;
import com.namoo.social.web.press.PageCondition;

@Service
public class MessageServiceLogic implements MessageService {

	@Autowired
	private MessageDao messageDao;
	@Autowired
	private UserDao userDao;
	
	@Override
	public Page<Message> findAllMessagesByUserId(String userId, PageCondition pageCondition) {
		//
		List<Message> list = messageDao.readAllMessagesByUserId(userId, pageCondition);
		int total = messageDao.readTimelineCount(userId);
		
		List<Message> messageList = new ArrayList<Message>();
		for (Message message : list) {
			User user = userDao.readUser(message.getWriter().getUserId());
			message.setWriter(user);
			messageList.add(message);
		}
		
		Page<Message> page = new Page<Message>();
		page.setPageCondition(pageCondition);
		page.setTotal(total);
		
		page.setResults(messageList);
		
		return page;
	}
	
	@Override
	public int findAllMessagesByUserIdCount(String userId) {
		//
		return messageDao.readAllMessagesByUserIdCount(userId);
	}

	@Override
	public Page<Message> findTimeline(String userId, PageCondition pageCondition) {
		//
//		List<Message> list = messageDao.readTimeline(userId, pageCondition);
//		List<Message> messageList = new ArrayList<Message>();
//		for (Message message : list) {
//			User user = userDao.readUser(message.getWriter().getUserId());
//			message.setWriter(user);
//			messageList.add(message);
//		}
//		
//		return messageList;
		
		List<Message> list = messageDao.readTimeline(userId, pageCondition);
		int total = messageDao.readTimelineCount(userId);
		
		List<Message> messageList = new ArrayList<Message>();
		for (Message message : list) {
			User user = userDao.readUser(message.getWriter().getUserId());
			message.setWriter(user);
			messageList.add(message);
		}
		
		Page<Message> page = new Page<Message>();
		page.setPageCondition(pageCondition);
		page.setTotal(total);
		
		page.setResults(messageList);
		
		return page;
	}
	
	@Override
	public int findTimelineCount(String userId) {
		//
		return messageDao.readTimelineCount(userId);
	}

	@Override
	public Page<Message> findAllMessagesFromFollowings(String userId, PageCondition pageCondition) {
		//
		List<Message> list = messageDao.readAllMessagesFromFollowings(userId, pageCondition);
		int total = messageDao.readAllMessagesFromFollowingsCount(userId);
		
		List<Message> messageList = new ArrayList<Message>();
		for (Message message : list) {
			User user = userDao.readUser(message.getWriter().getUserId());
			message.setWriter(user);
			messageList.add(message);
		}
		
		Page<Message> page = new Page<Message>();
		page.setPageCondition(pageCondition);
		page.setTotal(total);
		
		page.setResults(messageList);
		
		return page;
	}
	
	@Override
	public int findAllMessagesFromFollowingsCount(String userId) {
		//
		return messageDao.readAllMessagesFromFollowingsCount(userId);
	}

	@Override
	public Page<Message> findAllMessagesFromFollowers(String userId, PageCondition pageCondition) {
		//
		List<Message> list = messageDao.readAllMessagesFromFollowers(userId, pageCondition);
		int total = messageDao.readAllMessagesFromFollowersCount(userId);
		
		List<Message> messageList = new ArrayList<Message>();
		for (Message message : list) {
			User user = userDao.readUser(message.getWriter().getUserId());
			message.setWriter(user);
			messageList.add(message);
		}
		
		Page<Message> page = new Page<Message>();
		page.setPageCondition(pageCondition);
		page.setTotal(total);
		
		page.setResults(messageList);
		
		return page;
	}
	
	@Override
	public int findAllMessagesFromFollowersCount(String userId) {
		//
		return messageDao.readAllMessagesFromFollowersCount(userId);
	}

	@Override
	public Message findMessage(int msgNo) {
		//
		Message message = messageDao.readMessage(msgNo);
		User user = userDao.readUser(message.getWriter().getUserId());
		message.setWriter(user);
		
		return message;
	}

	@Override
	public int writeMessage(Message message) {
		//
		return messageDao.createMessage(message);
	}

	@Override
	public void modifyMessage(Message message) {
		//
		messageDao.updateMessage(message);
	}

	@Override
	public void removeMessage(int msgNo) {
		//
		messageDao.deleteMessage(msgNo);
	}

}
