package com.aili.wechatdome.service;

import cn.hutool.json.JSONObject;
import com.aili.wechatdome.model.User;
import com.aili.wechatdome.utils.result.R;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
//    User queryUserById(String id);

//    int put(String username, String password);

    R<Object> login(JSONObject jsonObject);

    int register(JSONObject jsonObject, HttpServletRequest request);

    int VerifyUsername( String username);

    User queryUser(JSONObject jsonObject);

    R<Object> updatePicUrl(JSONObject jsonObject);

    R<Object> passFriend(JSONObject jsonObject);

    R<Object> friendList(JSONObject jsonObject);
}
