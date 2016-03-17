/*package com.namoo.social.service.facade;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.namoo.social.dao.DbCommonTest;
import com.namoo.social.domain.Message;
import com.namoo.social.domain.User;
import com.namoo.social.web.press.PageCondition;

public class MessageServiceTest extends DbCommonTest {

	private static final String DATASET_XML = "MessageServiceTest_dataset.xml";
	
	@Autowired
	private MessageService messageService;
	
	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindAllMessagesByUserId() {
		//
		List<Message> messages = messageService.findAllMessagesByUserId("user3");
		
		assertEquals(3, messages.size());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindTimeline() {
		//
		PageCondition pageCondition = new PageCondition(0, 10);
		List<Message> messages = messageService.findTimeline("user1", pageCondition);
		
		assertEquals(6, messages.size());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindAllMessagesFromFollowings() {
		//
		List<Message> messages = messageService.findAllMessagesFromFollowings("user1");
		
		assertEquals(5, messages.size());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindAllMessagesFromFollowers() {
		//
		List<Message> messages = messageService.findAllMessagesFromFollowers("user3");
		
		assertEquals(3, messages.size());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testFindMessage() {
		//
		Message message = messageService.findMessage(5);
		
		assertEquals(5, message.getMsgNo());
		assertEquals("메시지5", message.getContents());
		assertEquals("user3", message.getWriter().getUserId());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testWriteMessage() {
		//
		Message message = new Message("메시지11", new User("user1"));
		int msgNo = messageService.writeMessage(message);
		
		message = messageService.findMessage(msgNo);
		
		assertEquals(11, message.getMsgNo());
		assertEquals("메시지11", message.getContents());
		assertEquals("user1", message.getWriter().getUserId());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testModifyMessage() {
		//
		Message message = new Message(1, "메시지1");
		
		messageService.modifyMessage(message);
		
		message = messageService.findMessage(1);
		
		assertEquals(1, message.getMsgNo());
		assertEquals("메시지1", message.getContents());
		assertEquals("user1", message.getWriter().getUserId());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testRemoveMessage() {
		//
		messageService.removeMessage(1);
		
		Message message = messageService.findMessage(1);
		
		assertNull(message);
	}

}
*/