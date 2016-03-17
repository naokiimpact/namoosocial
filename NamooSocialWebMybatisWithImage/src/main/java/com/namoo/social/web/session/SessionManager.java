package com.namoo.social.web.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.namoo.social.domain.User;
import com.namoo.social.service.facade.UserService;

public class SessionManager {
	//
	private final static String loginInfo = "login";
	
	@Autowired
	private UserService userService;
	private HttpSession session;

	private SessionManager (HttpServletRequest req) {
		this.session = req.getSession();
	}
	
	public static SessionManager getInstance(HttpServletRequest req) {
		return new SessionManager(req);
	}

	public User getLogin() {
		//
		return (User) session.getAttribute(loginInfo);
	}

	public void setLogin(User login) {
		//
		session.setAttribute(loginInfo, login);
	}
	
}
