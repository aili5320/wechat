package com.aili.wechatdome.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import com.aili.wechatdome.mapper.FriendListMapper;
import com.aili.wechatdome.model.FriendList;
import com.aili.wechatdome.service.FriendService;
import com.aili.wechatdome.utils.result.R;
import com.aili.wechatdome.utils.result.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendListMapper friendListMapper;


    @Override
    @Transactional
    public int addFriend(JSONObject jsonObject) {
        JSONObject userInfo = (JSONObject) jsonObject.get("userInfo");
        JSONObject searchUser = (JSONObject) jsonObject.get("searchUser");
        String remark = jsonObject.getStr("remark");
        String apply = jsonObject.getStr("apply");
        String userId =userInfo.getStr("userId");
        String searchId =searchUser.getStr("userId");
        int count = friendListMapper.selectSoleFriend(userId, searchId);
        if (count != 0) {
            log.debug("该好友已在好友列表！");
            return 2;
        }
        FriendList friendList = new FriendList();
        friendList.setUuid(IdUtil.simpleUUID());
        friendList.setUserId(userId);
        friendList.setFirId(searchId);
        friendList.setFriRemarkName(remark);
        friendList.setCreateTime(DateUtil.date());
        friendList.setApply(apply);
        friendList.setFriPic(userInfo.getStr("userPic"));
        friendList.setAsking("0");
        friendList.setFriRealname(userInfo.getStr("realName"));
        friendList.setFriUserName(userInfo.getStr("userName"));
        friendList.setAddress(userInfo.getStr("address"));

        friendList.setUuid(IdUtil.simpleUUID());
        friendList.setUserId(searchId);
        friendList.setFirId(userId);
        friendList.setFriRemarkName(remark);
        friendList.setCreateTime(DateUtil.date());
        friendList.setApply(apply);
        friendList.setFriPic(searchUser.getStr("userPic"));
        friendList.setAsking("0");
        friendList.setFriRealname(searchUser.getStr("realName"));
        friendList.setFriUserName(searchUser.getStr("userName"));
        friendList.setAddress(searchUser.getStr("address"));
        return 1;


    }

    @Override
    public R<Object> queryFriendList(JSONObject jsonObject) {
        List<FriendList> list = friendListMapper.selectFirId(jsonObject.getStr("userId"));
        return R.buildR(Status.OK, list);
    }
}
