package com.superhonor.rpc.test.service;

import com.superhonor.rpc.test.model.User;

/**
 * 用户服务
 *
 * @author liuweidong
 */
public interface UserService {

    /**
     * 根据id查询User
     * @param id
     * @return
     */
    User getUserById(Long id);
}
