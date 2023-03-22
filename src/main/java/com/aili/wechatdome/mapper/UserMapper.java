package com.aili.wechatdome.mapper;

import com.aili.wechatdome.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int VerifyUsername(String username);

    User queryUser(String userName);


    User selectByUsernameKey(String username);
}
