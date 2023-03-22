package com.aili.wechatdome;

import cn.hutool.core.util.IdUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Random;

@SpringBootTest
class WechatDomeApplicationTests {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;





}
