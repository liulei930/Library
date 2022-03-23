package com.service.impl;

import com.dao.IStudentDao;
import com.domain.Student;
import com.domain.StudentBook;
import com.github.pagehelper.PageHelper;
import com.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘磊
 * @version 1.0
 */
@Service
public class StudentService implements IStudentService {
    @Autowired
    private IStudentDao studentDao;
    @Override
    public Student login(String account, String password) throws Exception {
        return studentDao.login(account,password);
    }

    @Override
    public List<Student> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return studentDao.findAll();
    }

    @Override
    public void save(Student student) throws Exception {
         studentDao.save(student);
    }

    @Override
    public void delete(Integer id) throws Exception {
          studentDao.delete(id);
    }

    @Override
    public Student findById(Integer id) throws Exception {
        return studentDao.findById(id);
    }

    @Override
    public void update(Student student) throws Exception {
        studentDao.update(student);
    }



    @Override
    public void updatePassword(Student student) throws Exception {
        studentDao.updatePassword(student);
    }

    @Override
    public List<Student> findByName(String name) throws Exception {
        return studentDao.findByName(name);
    }

}
