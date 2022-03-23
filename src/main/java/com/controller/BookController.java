package com.controller;

import com.domain.Book;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 刘磊
 * @version 1.0
 */
@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private IBookService bookService;

    /**
     * 查询所有图书
     *
     * @param
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                          @RequestParam(name = "size", defaultValue = "4",required = false) int size) throws Exception {
        ModelAndView mv = new ModelAndView();
        if (size == 0) {
            size = 4;

        }
        List<Book> bookList = bookService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(bookList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("/book/book-list");
        return mv;
    }

    /**
     * 保存图书
     *
     * @param book
     * @return
     */
    @RequestMapping("/saveBook")
    public String saveBook(Book book) {
        System.out.println("执行了保存");
        bookService.saveBook(book);
        System.out.println(book);
        return "redirect:/books/findAll";
    }

    /**
     * 跳转到保存页面
     *
     * @return
     */
    @RequestMapping("/toSaveBook")
    public String toSaveBook() {
        return "book/book-add";
    }

    /**
     * 删除
     *
     * @return
     */
    @RequestMapping("/delete")
    public ModelAndView delete(@RequestParam(name = "id") Integer id,
                               @RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {
        bookService.deleteBook(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/books/findAll?page=" + page + "&size=" + size);
        return mv;

    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model) {
        Book book = bookService.findById(id);
        System.out.println("跳到修改页面...");
        System.out.println(book);
        model.addAttribute("book", book);
        return "book/book-update";
    }

    @RequestMapping("/update")
    public ModelAndView update(Book book) throws Exception {
        ModelAndView mv = new ModelAndView();
        String name = book.getName();
        System.out.println("执行更新...");
        bookService.updateBook(book);
        mv.setViewName("redirect:/books/findByName?name=" + URLEncoder.encode(name, "UTF-8"));
        return mv;
    }

    @RequestMapping("/findByName")
    public String findByName(@RequestParam(name = "name") String name, Model model) throws Exception {
        String message;
        List<Book> bookList = bookService.findByName(name);
        if (bookList.size() == 0) {
            message = "未找到";
            model.addAttribute("message", message);
        }
        PageHelper.startPage(1, 4);
        PageInfo pageInfo = new PageInfo(bookList);
        model.addAttribute("pageInfo", pageInfo);
        return "book/book-list";
    }

}
