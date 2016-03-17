package com.namoo.social.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.namoo.social.domain.Message;
import com.namoo.social.domain.User;
import com.namoo.social.domain.UserStatus;
import com.namoo.social.service.facade.MessageService;
import com.namoo.social.service.facade.UserService;
import com.namoo.social.web.press.Count;
import com.namoo.social.web.press.Page;
import com.namoo.social.web.press.PageCondition;
import com.namoo.social.web.session.LoginRequired;
import com.namoo.social.web.session.SessionManager;

@Controller
@LoginRequired
public class MessageController {

	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;

	@RequestMapping("/")
	@LoginRequired(false)
	public String index(HttpServletRequest req) {
		//
		if (SessionManager.getInstance(req).getLogin() != null) {
			//
			return "redirect:/main";
		} else {
			//
			return "index";
		}
	}

	@RequestMapping("/main")
	@LoginRequired(false)
	public String main(HttpServletRequest req, Model model) {
		//
		User login = SessionManager.getInstance(req).getLogin();

		if (login == null) {
			//
			return "redirect:/";
		} else {
			//
			User user = userService.findUser(login.getUserId());
//			List<Message> timeline = messageService.findTimeline(login
//					.getUserId());
//			user.setMessages(timeline);

			List<User> recoMembers = userService
					.findRecommandableFollowings(login.getUserId());

			Count count = new Count();
			count.setMsgCount(messageService.findAllMessagesByUserIdCount(
					login.getUserId()));
			count.setFollowingCount(userService.findAllFollowings(
					login.getUserId()).size());
			count.setFollowerCount(userService.findAllFollowers(
					login.getUserId()).size());

			model.addAttribute("user", user);
			model.addAttribute("recoList", recoMembers);
			model.addAttribute("count", count);

			return "main";
		}
	}
	
	@RequestMapping("/message/timeline")
	@ResponseBody
	public Page<Message> veiwTimeline(HttpServletRequest req, PageCondition pageCondition) {
		//
		User login = SessionManager.getInstance(req).getLogin();
		
		Page<Message> timeline = messageService.findTimeline(login.getUserId(), pageCondition);
		
		return timeline;
	}
	
	@RequestMapping("/message/{userId}/{message}")
	@ResponseBody
	public Page<Message> viewMessage(@PathVariable("userId") String userId,
			@PathVariable("message") String message, 
			HttpServletRequest req, PageCondition pageCondition) {
		//
		Page<Message> messageList = null;
		
			if (message.equals("message")) {
			//
			messageList = messageService.findAllMessagesByUserId(userId, pageCondition);

		} else if (message.equals("followingMsg")) {
			//
			messageList = messageService.findAllMessagesFromFollowings(userId, pageCondition);

		} else if (message.equals("followerMsg")) {
			//
			messageList = messageService.findAllMessagesFromFollowers(userId, pageCondition);
		}
		return messageList;
	}

	@RequestMapping("/main/{userId}")
	public String main(@PathVariable("userId") String userId,
			HttpServletRequest req, Model model) {
		//
		User login = SessionManager.getInstance(req).getLogin();

		User user = userService.findUser(userId);

		List<User> recoMembers = userService.findRecommandableFollowings(login
				.getUserId());

		Count count = new Count();
		count.setMsgCount(messageService.findAllMessagesByUserIdCount(userId));
		count.setFollowingCount(userService.findAllFollowings(userId).size());
		count.setFollowerCount(userService.findAllFollowers(userId).size());

		model.addAttribute("user", user);
		model.addAttribute("recoList", recoMembers);
		model.addAttribute("count", count);

		return "usermainmsg";
	}

	@RequestMapping("/main/{userId}/{message}")
	public String main(@PathVariable("userId") String userId,
			@PathVariable("message") String message, HttpServletRequest req,
			Model model) {
		//
		User login = SessionManager.getInstance(req).getLogin();

		User user = userService.findUser(userId);

		List<User> recoMembers = userService.findRecommandableFollowings(login
				.getUserId());

		Count count = new Count();
		count.setMsgCount(messageService.findAllMessagesByUserIdCount(userId));
		count.setFollowingCount(userService.findAllFollowings(userId).size());
		count.setFollowerCount(userService.findAllFollowers(userId).size());

		if (message.equals("message") ||
				message.equals("followingMsg") ||
				message.equals("followerMsg")) {
			//
			model.addAttribute("user", user);
			model.addAttribute("recoList", recoMembers);
			model.addAttribute("count", count);

			return "usermainmsg";

		} else if (message.equals("following")) {
			//
			List<User> followingList = userService.findAllFollowings(userId);

			model.addAttribute("user", user);
			model.addAttribute("recoList", recoMembers);
			model.addAttribute("count", count);
			model.addAttribute("followingList", followingList);

			return "usermain";

		} else if (message.equals("follower")) {
			//
			List<User> followerList = userService.findAllFollowers(userId);

			model.addAttribute("user", user);
			model.addAttribute("recoList", recoMembers);
			model.addAttribute("count", count);
			model.addAttribute("followerList", followerList);

			return "usermain";
		} else if (message.equals("friend")) {
			//
			List<User> friendsList = userService.findAllFriends(userId);

			model.addAttribute("user", user);
			model.addAttribute("recoList", recoMembers);
			model.addAttribute("count", count);
			model.addAttribute("friendsList", friendsList);

			return "usermain";
		}
		return null;
	}

	/*@RequestMapping(value = "/message/write", method = RequestMethod.POST)
	public String write(HttpServletRequest req, Message message) {
		//
		String loginId = SessionManager.getInstance(req).getLogin().getUserId();

		message.setWriter(userService.findUser(loginId));

		messageService.writeMessage(message);

		String url = req.getHeader("Referer");

		return "redirect:" + url; // 이전 주소로 리다이렉트
	}*/
	
	@RequestMapping(value = "/message/write", method = RequestMethod.POST)
	@ResponseBody
	public Message write(HttpServletRequest req, Message message) {
		//
		String loginId = SessionManager.getInstance(req).getLogin().getUserId();

		message.setWriter(userService.findUser(loginId));

		int msgNo = messageService.writeMessage(message);
		
		message = messageService.findMessage(msgNo);

		return message;
	}
	
	@RequestMapping(value = "/message/renew", method = RequestMethod.GET)
	@ResponseBody
	public int renew(HttpServletRequest req, PageCondition pageCondition) {
		//
		User user = SessionManager.getInstance(req).getLogin();
		String loginId = null;
		if(user != null) {
			UserStatus userStatus = new UserStatus(true);
			user.setUserStatus(userStatus);
			userService.modifyUserStatus(user);
			loginId = user.getUserId();
		}
		int messagesCount = messageService.findTimelineCount(loginId);

		return messagesCount;
	}

}
