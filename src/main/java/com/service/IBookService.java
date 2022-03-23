package com.service;

import com.domain.Book;

import java.util.List;

/**
 * @author 刘磊
 * @version 1.0
 */
public interface IBookService {
    /**
     * 保存图书
     * @param book
     */
    void saveBook(Book book);

    /**
     * 根据图书类型查询
     * @param type
     * @return
     */
    List<Book> findByType(String type);


    /**
     * 根据id删除图书
     * @param id
     */
    void deleteBook(Integer id);

    /**
     * 查询所有
     * @param page
     * @param size
     * @return
     */
    List<Book> findAll(int page,int size);
    /**
     * 根据id查询
     * @param id
     * @return
     */
    Book findById(Integer id);
    /**
     * 修改书籍
     * @param book
     */
    void updateBook(Book book);
    /**
     * 根据名字查询
     * @param name
     * @return
     */
    List<Book> findByName(String name) throws Exception;

    List<Book> findMoHu(String bookName) throws Exception;
}
