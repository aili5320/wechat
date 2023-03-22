package com.aili.wechatdome.controller;

import cn.hutool.json.JSONObject;
import com.aili.wechatdome.model.User;
import com.aili.wechatdome.service.FriendService;
import com.aili.wechatdome.service.UserService;
import com.aili.wechatdome.utils.VerifyCode;
import com.aili.wechatdome.utils.result.R;
import com.aili.wechatdome.utils.result.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController {

    /*@Value("${wechat.name}")
    private String name;
    @Value("${wechat.version}")
    private String version;*/


    @Autowired
    private UserService userService;
    @Autowired
    private VerifyCode verifyCode;

    @Autowired
    private FriendService friendService;



    @RequestMapping("/login")
    public R<Object> login(@RequestBody JSONObject jsonObject) {
        return  userService.login(jsonObject);

    }

    /**
     * 验证码
     *
     *
     */
    @RequestMapping("/getVerify")
    public R<Object> getVerify() {
        HashMap map = new HashMap();
        map.put("verifyImg", verifyCode.getImage());
        map.put("verifyText", verifyCode.getText());
        return R.buildR(Status.OK, map);
    }

    /**
     * 验证用户名是否被占用
     * @param jsonObject
     * @return
     */
    @RequestMapping("/verifyUsername")
    public R<Object> verifyUsername(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getStr("username");
        int count = userService.VerifyUsername(username);
        return R.buildR(Status.OK, count);
    }

    /**
     * 注册用户
     *
     * @param jsonObject
     * @param request
     * @return
     */
    @RequestMapping("/register")
    public R<Object> register(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        int flag = userService.register(jsonObject, request);
        if (flag == 1) {
            return R.buildR(Status.OK, 0);
        } else {
            return R.buildR(Status.SYSTEM_ERROR, 0);
        }
    }


    /**
     * 根据手机号或者用户名查询用户
     * @param jsonObject
     * @return
     */
    @RequestMapping("/queryUser")
    public R<Object> queryUser(@RequestBody JSONObject jsonObject){
       User user = userService.queryUser(jsonObject);
        if (user == null) {
            return R.buildR(Status.OK, 0);
        } else {
            return R.buildR(Status.OK, user);
        }

    }

    /**
     * 添加申请好友
     */
    @RequestMapping("/addFriend")
   public R<Object> addFriend(@RequestBody JSONObject jsonObject){
         int count = friendService.addFriend(jsonObject);
         if (count==1){
             return R.buildR(Status.OK, 1);
         }
         else if(count==2) {
             return R.buildR(Status.OK, 2);
         }
        return R.buildR(Status.SYSTEM_ERROR, 3);
    }

    /**
     * 查看好友申请列表
     */
    @RequestMapping("/queryFriendList")
    public R<Object> queryFriendList(@RequestBody JSONObject jsonObject){
        return friendService.queryFriendList(jsonObject);
    }

    /**
     * 修改头像
     * @param jsonObject
     * @return
     */
    @RequestMapping("/updatePicUrl")
    public R<Object> updatePicUrl(@RequestBody JSONObject jsonObject){
        return userService.updatePicUrl(jsonObject);
    }

    /**
     * 好友申请认证通过
     * @param jsonObject
     * @return
     */
    @RequestMapping("/passFriend")
    public R<Object> passFriend(@RequestBody JSONObject jsonObject){
        return userService.passFriend(jsonObject);
    }

    /**
     * 好友申请认证失败
     * @param jsonObject
     * @return
     */
    @RequestMapping("/friendList")
    public R<Object> friendList(@RequestBody JSONObject jsonObject){
        return userService.friendList(jsonObject);
    }







}