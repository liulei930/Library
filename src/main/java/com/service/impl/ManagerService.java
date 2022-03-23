package com.service.impl;

import com.dao.IAdminDao;
import com.domain.Admin;
import com.service.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 刘磊
 * @version 1.0
 */
@Service
public class ManagerService implements IManagerService {
    @Autowired
    private IAdminDao managerDao;
    @Override
    public Admin login(Integer account, String password) throws Exception {
        return managerDao.login(account,password);
    }
}
