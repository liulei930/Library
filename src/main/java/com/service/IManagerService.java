package com.service;

import com.domain.Admin;

/**
 * @author 刘磊
 * @version 1.0
 */
public interface IManagerService {
    /**
     * 根据账号密码查找
     * @param account
     * @param password
     * @return
     */
   Admin login(Integer account, String password) throws Exception;
}
