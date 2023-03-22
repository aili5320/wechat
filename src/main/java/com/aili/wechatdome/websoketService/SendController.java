package com.aili.wechatdome.websoketService;

import com.aili.wechatdome.utils.result.R;
import com.aili.wechatdome.utils.result.Status;
import com.aili.wechatdome.websoketService.WebSocket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

public class SendController {

    //创建业务消息信息
   /* JSONObject obj = new JSONObject();
obj.put("cmd", "topic");//业务类型
obj.put("msgId", sysAnnouncement.getId());//消息id
obj.put("msgTxt", sysAnnouncement.getTitile());//消息内容
//全体发送
webSocket.sendAllMessage(obj.toJSONString());
//单个用户发送 (userId为用户id)
webSocket.sendOneMessage(userId, obj.toJSONString());
//多个用户发送 (userIds为多个用户id，逗号‘,’分隔)
webSocket.sendMoreMessage(userIds, obj.toJSONString());*/





    @Resource
    private WebSocket webSocket;

    @GetMapping(value = "/send")
    public R<Object> send(@RequestParam String userId, String message){
        webSocket.sendOneMessage(userId, message);
        return R.buildR(Status.OK,"发送成功");
    }
}
