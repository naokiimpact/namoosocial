package com.namoo.social.dao.sqlmap.mapper;

import java.util.Date;
import java.util.List;

import com.namoo.social.domain.InstantMessage;

public interface InstantMessageMapper {
	//
	List<InstantMessage> selectAllInstantMessagesByUserId(String userId);
	
	int insertInstantMessage(InstantMessage instant);
	
	void deleteInstantMessagesByUserId(String userId);
	void deleteInstantMessagesByUserIdAndDate(String userId, Date lastRead);
}
