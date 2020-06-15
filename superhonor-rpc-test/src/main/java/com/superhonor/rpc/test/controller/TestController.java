package com.superhonor.rpc.test.controller;

import com.superhonor.rpc.annotation.RpcReference;
import com.superhonor.rpc.test.model.User;
import com.superhonor.rpc.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuweidong
 */
@RestController
public class TestController {

    @RpcReference
    private UserService userService;

    @GetMapping("/test/users/{id:[\\d]+}")
    public User getUserById(@PathVariable("id") Long userId) {
        System.out.println(userService);
        return userService.getUserById(userId);
    }
}
