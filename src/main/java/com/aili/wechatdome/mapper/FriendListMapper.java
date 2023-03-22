package com.aili.wechatdome.mapper;

import com.aili.wechatdome.model.FriendList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendListMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(FriendList record);

    int insertSelective(FriendList record);

    FriendList selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(FriendList record);

    int updateByPrimaryKey(FriendList record);

    int selectSoleFriend(String userId, String firId);

    List<FriendList> selectFirId(String userId);

    int updateFriPic(String userId, String picUrl);

    int passFriend(String searchId, String userId ,String asking);

    List<FriendList> friendList(String userId);
}