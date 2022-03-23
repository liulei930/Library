package com.dao;

import com.domain.StudentBook;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 刘磊
 * @version 1.0
 */
@Repository
public interface IStuBookDao {
    /**
     * 新增借书记录
     * @param studentBook
     * @throws Exception
     */
    void save(StudentBook studentBook) throws Exception;

    /**
     * 根据学生id查找当前借阅书籍
     * @param studentId
     * @return
     * @throws Exception
     */
    List<StudentBook> findBookByStuId(Integer studentId) throws Exception;

    /**
     * 还书更新还书时间
     * @param studentBook
     * @throws Exception
     */
    void update(StudentBook studentBook) throws Exception;

    /**
     * 查找指定id的借阅记录
     * @param studentId
     * @return
     * @throws Exception
     */
    List<StudentBook> findHistoryByStuId(Integer studentId) throws Exception;

    /**
     * 更新续借信息
     * @param studentBook
     * @throws Exception
     */
    void updateXuJie(StudentBook studentBook) throws Exception;

    /**
     * 根据学生Id查找记录
     * @param stuBookId
     * @return
     * @throws Exception
     */
    StudentBook findById(Integer stuBookId) throws Exception;

    /**
     * 查找当前超期图书
     * @param studentId
     * @return
     * @throws Exception
     */
    List<StudentBook> lateBook(Integer studentId) throws Exception;

    /**
     *查找借阅表所有记录
     * @return
     */
    List<StudentBook> findAll();

    void delete(Integer id);
}
