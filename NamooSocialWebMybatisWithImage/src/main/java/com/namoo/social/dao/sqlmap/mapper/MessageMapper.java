package com.namoo.social.dao.sqlmap.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.namoo.social.domain.Message;
import com.namoo.social.web.press.PageCondition;

public interface MessageMapper {

	List<Message> selectAllMessagesByUserId(@Param("userId") String userId, @Param("pageCondition") PageCondition pageCondition);
	int selectAllMessagesByUserIdCount(String userId);
	List<Message> selectTimeline(@Param("userId")String userId, @Param("pageCondition") PageCondition pageCondition);
	int selectTimelineCount(String userId);
	List<Message> selectAllMessagesFromFollowings(@Param("userId")String userId, @Param("pageCondition") PageCondition pageCondition);
	int selectAllMessagesFromFollowingsCount(String userId);
	List<Message> selectAllMessagesFromFollowers(@Param("userId")String userId, @Param("pageCondition") PageCondition pageCondition);
	int selectAllMessagesFromFollowersCount(String userId);
	Message selectMessage(int msgNo);
	
	int insertMessage(Message message);
	
	void updateMessage(Message message);
	
	void deleteMessage(int msgNo);
}
