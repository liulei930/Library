package com.service.impl;

import com.dao.IStuBookDao;
import com.domain.StudentBook;
import com.github.pagehelper.PageHelper;
import com.service.IStuBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘磊
 * @version 1.0
 */
@Service
public class StuBookService implements IStuBookService {
    @Autowired
    private IStuBookDao stuBookDao;
    @Override
    public void save(StudentBook studentBook) throws Exception {
        stuBookDao.save(studentBook);
    }

    @Override
    public List<StudentBook> findBookByStuId(Integer studentId) throws Exception {
        return stuBookDao.findBookByStuId(studentId);
    }

    @Override
    public void delete(Integer id) {
        stuBookDao.delete(id);
    }

    @Override
    public void update(StudentBook studentBook) throws Exception {
                stuBookDao.update(studentBook);
    }

    @Override
    public List<StudentBook> findHistoryByStuId(Integer studentId) throws Exception {
        return stuBookDao.findHistoryByStuId(studentId);
    }

    @Override
    public void updateXuJie(StudentBook studentBook) throws Exception {
        stuBookDao.updateXuJie(studentBook);
    }

    @Override
    public StudentBook findById(Integer stuBookId) throws Exception {
        return stuBookDao.findById(stuBookId);
    }

    @Override
    public List<StudentBook> lateBook(Integer studentId) throws Exception {
        return stuBookDao.lateBook(studentId);
    }

    @Override
    public List<StudentBook> findAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return stuBookDao.findAll();
    }
}
