package com.service;

import com.domain.Admin;
import com.domain.Student;

import java.util.List;

/**
 * @author 刘磊
 * @version 1.0
 */
public interface IAdminService {
    /**
     * 查找所有的管理员
     *
     * @return
     */
    List<Admin> findAll(int page, int size);

    List<Admin> findByName(String name);

    List<Admin> getAllAdmin();

    void save(Admin admin);

    void delete(Integer id);

    Admin findById(Integer id);

    void update(Admin admin);
}
