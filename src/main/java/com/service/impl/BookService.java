package com.service.impl;

import com.dao.IBookDao;
import com.domain.Book;
import com.github.pagehelper.PageHelper;
import com.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘磊
 * @version 1.0
 */
@Service
public class BookService implements IBookService {
    @Autowired
    private IBookDao bookDao;
    @Override
    public void saveBook(Book book) {
        bookDao.saveBook(book);

    }

    @Override
    public List<Book> findByType(String type) {

        return bookDao.findByType(type);
    }

    @Override
    public void deleteBook(Integer id) {
       bookDao.deleteBook(id);
    }

    @Override
    public List<Book> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return bookDao.findAll();
    }

    @Override
    public Book findById(Integer id) {
        return bookDao.findById(id);
    }

    @Override
    public void updateBook(Book book) {
           bookDao.updateBook(book);
    }

    @Override
    public List<Book> findByName(String name) {
        return bookDao.findByName(name);
    }

    @Override
    public List<Book> findMoHu(String bookName) throws Exception {
        return bookDao.findMoHu(bookName);
    }
}
