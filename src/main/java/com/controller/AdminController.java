package com.controller;

import com.domain.Admin;
import com.domain.Student;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;
import java.util.List;

/**
 * @author 刘磊
 * @version 1.0
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @RequestMapping("/save")
    public ModelAndView save(Admin admin) throws Exception {
        ModelAndView mv = new ModelAndView();
        adminService.save(admin);
        String name = admin.getName();
        mv.setViewName("redirect:/admin/findByName?name=" + URLEncoder.encode(name, "UTF-8"));
        return mv;
    }

    //查找所有管理员
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page", defaultValue = "1" , required = false) int page,
                                @RequestParam(name = "size", defaultValue = "4" , required = false) int size) throws Exception{
        ModelAndView mv = new ModelAndView();
        if (size == 0){
            size = 4;
        }
        List<Admin> adminList = adminService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(adminList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("/student/addAdmin");
        return mv;
    }
    //利用关键词查找管理员
    @RequestMapping("/findByName")
    public ModelAndView findeByName(@RequestParam(name = "name") String name)throws Exception{
        ModelAndView mv = new ModelAndView();
        String message;
        List<Admin> adminList = adminService.findByName(name);
        if(adminList.size() == 0){
            //未找到
            message = "未找到管理员，请添加";
            mv.addObject("message",message);
        }
        PageHelper.startPage(1,4);
        PageInfo pageInfo = new PageInfo(adminList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("/student/addAdmin");
        return mv;
    }
    /**
     * 删除学生
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public ModelAndView delete(@RequestParam(name = "id") Integer id, @RequestParam(name = "page") Integer page,
                               @RequestParam(name = "size") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        adminService.delete(id);
        mv.setViewName("redirect:/admin/findAll?page=" + page + "&size=" + size);
        return mv;
    }

    /**
     * 跳转到更新页面
     *
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model) throws Exception {
        Admin admin = adminService.findById(id);
        model.addAttribute("admin", admin);
        return "/student/admin-update";
    }

    /**
     * 更新页面
     *
     * @param admin
     * @return
     * @throws Exception
     */
    @RequestMapping("/update")
    public String update(Admin admin) throws Exception {
        adminService.update(admin);
        return "redirect:/admin/findAll";
    }
}
