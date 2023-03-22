package com.aili.wechatdome.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import com.aili.wechatdome.mapper.FriendListMapper;
import com.aili.wechatdome.mapper.UserMapper;
import com.aili.wechatdome.model.FriendList;
import com.aili.wechatdome.model.User;
import com.aili.wechatdome.service.FriendService;
import com.aili.wechatdome.service.UploadService;
import com.aili.wechatdome.service.UserService;
import com.aili.wechatdome.utils.IpAddressUtil;
import com.aili.wechatdome.utils.PasswordUtil;
import com.aili.wechatdome.utils.result.R;
import com.aili.wechatdome.utils.result.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IpAddressUtil ipAddressUtil;

    @Autowired
    private PasswordUtil passwordUtil;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private FriendListMapper friendListMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
//    @Override
//    public User queryUserById(String id) {
//        return userMapper.selectByPrimaryKey(id);
//    }

//    @Override
//    @Transactional
//    public int put(String username, String password) {
//        stringRedisTemplate.opsForValue().set(username,password,2400, TimeUnit.SECONDS);
//        User user = new User();
//        user.setUserId(IdUtil.simpleUUID());
//        user.setUserName(username);
//        user.setPassword(password);
//        int count = userMapper.insert(user);
//        return count;
//    }

    @Override
    public R<Object> login(JSONObject jsonObject) {
        String username = jsonObject.getStr("username");
        String password = jsonObject.getStr("password");
        User user = userMapper.selectByUsernameKey(username);
        if (user==null) return  R.buildR(Status.OK,1);
        String  md5Password  = passwordUtil.md5(user.getSalt()+password);
        if (username.equals(user.getUserName()) && md5Password.equals(user.getPassword())){
         return R.buildR(Status.OK,user);
        }
         return R.buildR(Status.OK,2);
    }

    @Override
    public int register(JSONObject jsonObject , HttpServletRequest request) {
        String salt =passwordUtil.salt();
        String password = jsonObject.getStr("password");
        String  md5Password= passwordUtil.md5(salt+password);
        User user = new User();
        user.setUserId(IdUtil.simpleUUID());
        user.setUserName(jsonObject.getStr("username"));
        user.setRealName(jsonObject.getStr("realNam"));
        user.setUserPic(jsonObject.getStr("userPic"));
        user.setCreateTime(DateUtil.date());
        user.setSalt(salt);
        user.setPassword(md5Password);
        String ip = ipAddressUtil.getIpAddress(request);
        user.setIpAddress(ip);
        int insert = userMapper.insert(user);
        return insert;
    }

    @Override
    public int VerifyUsername(String username) {
        return userMapper.VerifyUsername(username);
    }

    @Override
    public User queryUser(JSONObject jsonObject) {
        String userName = jsonObject.getStr("userName");
        return  userMapper.queryUser(userName);
    }

    @Override
    public R<Object> updatePicUrl(JSONObject jsonObject) {
        String picUrl =jsonObject.getStr("picUrl");
        String userId =jsonObject.getStr("userId");
        User user = new User();
        user.setUserId(userId);
        user.setUserPic(picUrl);
        int i = userMapper.updateByPrimaryKeySelective(user);
        if (i!=1){
            return R.buildR(Status.OK,'1');
        }else if (i==1){
             uploadService.deleteImg(picUrl);
            FriendList friendList = new FriendList();
            friendListMapper.updateFriPic(userId,picUrl);
            return R.buildR(Status.OK,'0');
        }
        return R.buildR(Status.SYSTEM_ERROR,'2');
    }

    @Override
    public R<Object> passFriend(JSONObject jsonObject) {
        String searchId =jsonObject.getStr("searchId");
        String userId =jsonObject.getStr("userId");
        String asking ="1";
         int count = friendListMapper.passFriend(searchId,userId,asking);
        if (count == 2){
            return R.buildR(Status.OK,'1');
        } else if (count != 2){
            return R.buildR(Status.OK,2);
        }
        return R.buildR(Status.SYSTEM_ERROR);
    }

    @Override
    public R<Object> friendList(JSONObject jsonObject) {
        String userId =jsonObject.getStr("userId");
        List<FriendList> list = friendListMapper.friendList(userId);
        return R.buildR(Status.OK,list);
    }
}
