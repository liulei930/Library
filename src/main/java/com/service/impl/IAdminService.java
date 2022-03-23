package com.service.impl;

import com.dao.IAdminDao;
import com.domain.Admin;
import com.domain.Student;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘磊
 * @version 1.0
 */
@Service
public class IAdminService implements com.service.IAdminService {
    @Autowired
    IAdminDao adminDao;

    @Override
    public List<Admin> findAll(int page, int size) {
        PageHelper.startPage(page,size);
        return adminDao.findAll();
    }

    @Override
    public List<Admin> findByName(String name) {
        return adminDao.findByName(name);
    }

    @Override
    public List<Admin> getAllAdmin() {
        return adminDao.getAllAdmin();
    }

    @Override
    public void save(Admin admin) {
        adminDao.save(admin);
    }

    @Override
    public void delete(Integer id) {
        adminDao.delete(id);
    }

    @Override
    public Admin findById(Integer id) {
        return adminDao.findById(id);
    }

    @Override
    public void update(Admin admin) {
        adminDao.update(admin);
    }
}
