package com.aa12501.community.service;

import com.aa12501.community.mapper.UserMapper;
import com.aa12501.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User oldUser = userMapper.finfByAccountId(user.getAccountId());
        if (oldUser == null) {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else{
            oldUser.setGmtModified(System.currentTimeMillis());
            System.out.println(oldUser.getGmtModified());
            oldUser.setAvatarUrl(user.getAvatarUrl());
            oldUser.setToken(user.getToken());
            oldUser.setName(user.getName());
            userMapper.update(oldUser);
        }
    }
}
