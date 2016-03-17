package com.namoo.social.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.namoo.social.domain.InstantMessage;
import com.namoo.social.domain.User;
import com.namoo.social.domain.UserStatus;
import com.namoo.social.service.facade.InstantMessageService;
import com.namoo.social.service.facade.UserService;
import com.namoo.social.web.session.LoginRequired;
import com.namoo.social.web.session.SessionManager;

@Controller
@LoginRequired
public class InstantMessageController {

	@Autowired
	private UserService userService;
	@Autowired
	private InstantMessageService instantMessageService;
	
	@RequestMapping("/instant/send")
	@ResponseBody
	public boolean send(HttpServletRequest req, @RequestParam(value = "instant[]") String[] instant) {
		//
		InstantMessage instantMessage = new InstantMessage();
		
		System.out.println(instant[0]);
		System.out.println(instant[1]);
		System.out.println(instant[2]);
		
		instantMessage.setMessage(instant[0]);
		instantMessage.setSender(new User(instant[1]));
		instantMessage.setRecipient(new User(instant[2]));
		
		instantMessageService.sendInstantMessage(instantMessage);
		System.out.println("보냄 : " + instantMessage.getMessage());
		User receivingUser = userService.findUser(instant[2]);
		
		if(receivingUser.getUserStatus().isUserStatus()){
			long renewDate = receivingUser.getUserStatus().getRenewDate().getTime()/1000;
			long now = new Date().getTime()/1000;
			System.out.println("지금 : " + now + "저장 : " + renewDate + "차이 : " + (now - renewDate));
			if ((now - renewDate) > 5) {
				receivingUser.setUserStatus(new UserStatus(false));
				userService.modifyUserStatus(receivingUser);
				
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping("/instant/load")
	@ResponseBody
	public List<InstantMessage> load(HttpServletRequest req) {
		//
		String loginId = SessionManager.getInstance(req).getLogin().getUserId();
		
		List<InstantMessage> loadingMessages = instantMessageService.findAllInstantMessagesByUserId(loginId);
		List<InstantMessage> messages = new ArrayList<InstantMessage>();
		for (InstantMessage instantMessage : loadingMessages) {
			String senderId = instantMessage.getSender().getUserId();
			String senderName = userService.findUser(senderId).getName();
			User sender = new User(senderId);
			sender.setName(senderName);
			
			instantMessage.setSender(sender);
			messages.add(instantMessage);
			System.out.println("로드 : " + instantMessage.getMessage());
		}
		
		instantMessageService.removeInstantMessagesByUserId(loginId);
		
		return messages;
		
	}
}
