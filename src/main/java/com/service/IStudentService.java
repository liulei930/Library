package com.service;

import com.domain.Book;
import com.domain.Student;
import com.domain.StudentBook;

import java.util.List;

/**
 * @author 刘磊
 * @version 1.0
 */
public interface IStudentService {
    /**
     * 根据账号密码查找
     * @param account
     * @param password
     * @return
     */
    Student login(String account, String password) throws Exception;

    List<Student> findAll(int page,int size) throws Exception;

    void save(Student student) throws Exception;

    void delete(Integer id) throws Exception;

    Student findById(Integer id) throws Exception;

    void update(Student student) throws Exception;

    void updatePassword(Student student) throws Exception;

    List<Student> findByName(String name) throws Exception;
}
