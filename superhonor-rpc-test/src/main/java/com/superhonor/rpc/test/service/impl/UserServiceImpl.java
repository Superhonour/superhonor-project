package com.superhonor.rpc.test.service.impl;

import com.superhonor.rpc.annotation.RpcService;
import com.superhonor.rpc.test.model.User;
import com.superhonor.rpc.test.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author liuweidong
 */
@Service
@RpcService(interfaces = {UserService.class})
public class UserServiceImpl implements UserService {

    @Value("${zrpc.servicePort}")
    private String post;

    @Override
    public User getUserById(Long id) {
        User user = new User();
        user.setId(1L);
        user.setName("你好！");
        user.setSex(1);
        user.setCreateTime(new Date());

        return user;
    }

}
