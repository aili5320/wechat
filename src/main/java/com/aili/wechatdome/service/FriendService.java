package com.aili.wechatdome.service;

import cn.hutool.json.JSONObject;
import com.aili.wechatdome.utils.result.R;

public interface FriendService {
    int addFriend(JSONObject jsonObject);

    R<Object> queryFriendList(JSONObject jsonObject);
}
