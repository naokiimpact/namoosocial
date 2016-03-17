package com.namoo.social.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.namoo.social.domain.Message;
import com.namoo.social.domain.User;
import com.namoo.social.web.press.PageCondition;

public class MessageDaoTest extends DbCommonTest {

	private static final String DATASET_XML = "MessageDaoTest_dataset.xml";
	
	@Autowired
	private MessageDao messageDao;

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadAllMessagesByUserId() {
		//
		PageCondition pageCondition = new PageCondition(0, 10);
		List<Message> messages = messageDao.readAllMessagesByUserId("user3", pageCondition);
		
		assertEquals(3, messages.size());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadTimeline() {
		//
		PageCondition pageCondition = new PageCondition(0, 10);
		List<Message> messages = messageDao.readTimeline("user1", pageCondition);
		
		assertEquals(6, messages.size());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadAllMessagesFromFollowings() {
		//
		PageCondition pageCondition = new PageCondition(0, 10);
		List<Message> messages = messageDao.readAllMessagesFromFollowings("user1", pageCondition);
		
		assertEquals(5, messages.size());	
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadAllMessagesFromFollowers() {
		//
		PageCondition pageCondition = new PageCondition(0, 10);
		List<Message> messages = messageDao.readAllMessagesFromFollowers("user3", pageCondition);
		
		assertEquals(3, messages.size());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadMessage() {
		//
		Message message = messageDao.readMessage(5);
		
		assertEquals(5, message.getMsgNo());
		assertEquals("메시지5", message.getContents());
		assertEquals("user3", message.getWriter().getUserId());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testCreateMessage() {
		//
		Message message = new Message("메시지11", new User("user1"));
		int msgNo = messageDao.createMessage(message);
		
		System.out.println(msgNo);
		
		message = messageDao.readMessage(msgNo);
		
		assertEquals(11, message.getMsgNo());
		assertEquals("메시지11", message.getContents());
		assertEquals("user1", message.getWriter().getUserId());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testUpdateMessage() {
		//
		Message message = new Message(1, "메시지1");
		
		messageDao.updateMessage(message);
		
		message = messageDao.readMessage(1);
		
		assertEquals(1, message.getMsgNo());
		assertEquals("메시지1", message.getContents());
		assertEquals("user1", message.getWriter().getUserId());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testDeleteMessage() {
		//
		messageDao.deleteMessage(1);
		
		Message message = messageDao.readMessage(1);
		
		assertNull(message);
	}

}
