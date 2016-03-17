package com.namoo.social.service.logic;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namoo.social.dao.InstantMessageDao;
import com.namoo.social.dao.UserDao;
import com.namoo.social.domain.InstantMessage;
import com.namoo.social.service.facade.InstantMessageService;

@Service
public class InstatntMessageServiceLogic implements InstantMessageService {

	@Autowired
	private InstantMessageDao instantDao;
	@Autowired
	private UserDao userDao;
	
	@Override
	public List<InstantMessage> findAllInstantMessagesByUserId(String userId) {
		//
		return instantDao.readAllInstantMessagesByUserId(userId);
	}
	@Override
	public int sendInstantMessage(InstantMessage instant) {
		//
		return instantDao.createInstantMessage(instant);
	}
	@Override
	public void removeInstantMessagesByUserId(String userId) {
		//
		instantDao.deleteInstantMessagesByUserId(userId);
	}
	@Override
	public void removeInstantMessagesByUserIdAndDate(String userId,
			Date lastRead) {
		//
		instantDao.deleteInstantMessagesByUserIdAndDate(userId, lastRead);
	}
}
