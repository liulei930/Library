package com.dao;

import com.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 刘磊
 * @version 1.0
 */
@Repository
public interface IBookDao {
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
     * 查询所有图书
     * @return
     */
    List<Book> findAll();

    /**
     * 修改书籍
     * @param book
     */
   void updateBook(Book book);

    /**
     * 根据id查询
     * @param id
     * @return
     */
   Book findById(Integer id);

    /**
     * 根据名字查询
     * @param name
     * @return
     */
  List<Book> findByName(String name);

    /**
     * 根据名字模糊查询
     * @param name
     * @return
     */
   List<Book> findMoHu(String name) throws Exception;

}
