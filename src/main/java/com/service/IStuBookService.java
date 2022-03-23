package com.service;

import com.domain.StudentBook;

import java.util.List;

/**
 * @author 刘磊
 * @version 1.0
 */
public interface IStuBookService {

    void save(StudentBook studentBook) throws Exception;

    List<StudentBook> findBookByStuId(Integer studentId) throws Exception;

    void update(StudentBook studentBook) throws Exception;

    List<StudentBook> findHistoryByStuId(Integer studentId) throws Exception;

    void updateXuJie(StudentBook studentBook) throws Exception;

    StudentBook findById(Integer stuBookId) throws Exception;

    List<StudentBook> lateBook(Integer studentId) throws Exception;

    List<StudentBook> findAll(Integer page, Integer size);

    void delete(Integer id);
}
