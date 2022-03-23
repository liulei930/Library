package com.dao;

import com.domain.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 刘磊
 * @version 1.0
 */
@Repository
public interface IStudentDao {
    /**
     * 根据账号密码查找
     * @param account
     * @param password
     * @return
     */
    Student login(@Param("account") String account, @Param("password") String password) throws Exception;

    List<Student> findAll();

    /**
     * 保存学生
     * @param student
     * @throws Exception
     */
    void save(Student student) throws Exception;

    /**
     * 指定id删除学生
     * @param id
     * @throws Exception
     */
    void delete(Integer id) throws Exception;

    /**
     * 根据学生id查询
     * @param id
     * @return
     * @throws Exception
     */
    Student findById(Integer id) throws Exception;

    /**
     * 更新指定id的学生
     * @param student
     * @throws Exception
     */
    void update(Student student) throws Exception;

    /**
     * 更新密码
     * @param student
     * @throws Exception
     */
    void updatePassword(Student student) throws Exception;

    /**
     * 根据名字查找学生
     * @param name
     * @return
     * @throws Exception
     */
    List<Student> findByName(String name) throws Exception;
}
