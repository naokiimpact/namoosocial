package com.namoo.social.dao.sqlmap;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.namoo.social.dao.InstantMessageDao;
import com.namoo.social.dao.sqlmap.mapper.InstantMessageMapper;
import com.namoo.social.domain.InstantMessage;

@Repository
public class InstantMessageSqlMap implements InstantMessageDao{

	@Autowired
	private InstantMessageMapper instantMapper;
	
	@Override
	public List<InstantMessage> readAllInstantMessagesByUserId(String userId) {
		//
		return instantMapper.selectAllInstantMessagesByUserId(userId);
	}

	@Override
	public int createInstantMessage(InstantMessage instant) {
		//
		return instantMapper.insertInstantMessage(instant);
	}

	@Override
	public void deleteInstantMessagesByUserId(String userId) {
		//
		instantMapper.deleteInstantMessagesByUserId(userId);
	}
	
	@Override
	public void deleteInstantMessagesByUserIdAndDate(String userId, Date lastRead) {
		//
		instantMapper.deleteInstantMessagesByUserIdAndDate(userId, lastRead);
	}

}
