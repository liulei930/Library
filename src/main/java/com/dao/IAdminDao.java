package com.dao;

import com.domain.Admin;
import java.util.*;

import com.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 刘磊
 * @version 1.0
 */
@Repository
public interface IAdminDao {
    /**
     * 根据账号密码查找
     *
     * @param account
     * @param password
     * @return
     */
    Admin login(@Param("account") Integer account, @Param("password") String password) throws Exception;

    /**
     * 查找所有的管理员
     *
     * @return
     */
    List<Admin> findAll();

    List<Admin> findByName(String name);

    List<Admin> getAllAdmin();

    void save(Admin admin);

    void delete(Integer id);

    Admin findById(Integer id);

    void update(Admin admin);
}
