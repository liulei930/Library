package com.controller;

import com.domain.StudentBook;
import com.github.pagehelper.PageInfo;
import com.service.IStuBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author 刘磊
 * @version 1.0
 */
@Controller
@RequestMapping("/stuAndBook")
public class StuAndBookController {
    @Autowired
    private IStuBookService stuBookService;

    /**
     * 查找所有的借阅信息
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size){
        ModelAndView mv=new ModelAndView();
        List<StudentBook> studentBooks=stuBookService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(studentBooks);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("/book/stu-book");
        return mv;
    }

    /**
     * 删除一条记录
     * @return
     */
    @RequestMapping("/delete")
    public ModelAndView delete(@RequestParam(name = "id") Integer id,@RequestParam(name = "page")Integer page,
                               @RequestParam(name ="size") Integer size){
        ModelAndView mv=new ModelAndView();
        stuBookService.delete(id);
        mv.setViewName("redirect:/stuAndBook/findAll?page="+page+"&size="+size);
        return mv;
    }
}
