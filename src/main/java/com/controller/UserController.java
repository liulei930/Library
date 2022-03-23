package com.controller;


import com.dao.IAdminDao;
import com.domain.Admin;
import com.domain.Student;
import com.domain.UserInfo;
import com.github.pagehelper.PageInfo;
import com.service.IAdminService;
import com.service.IManagerService;
import com.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 刘磊
 * @version 1.0
 */
@SessionAttributes("user")
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IManagerService managerService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    IAdminService adminService;

    @RequestMapping("/login")
    public String login(@RequestParam(name = "account", required = true) String account, @RequestParam(name = "password", required = true) String password,
                        @RequestParam(name = "userType", required = true) String userType, Model model
    ) throws Exception {
        UserInfo userInfo = new UserInfo();
        Admin manager = null;
        Student student = null;
        String page = "";
        if ("管理员".equals(userType)) {
            manager = managerService.login(Integer.parseInt(account), password);
            if (manager != null) {
                userInfo.setUserId(manager.getId());
                userInfo.setUserName(manager.getName());
                userInfo.setUserType("管理员");
                model.addAttribute("user", userInfo);
                return "main";
            }
        }
        if ("学生".equals(userType)) {
            student = studentService.login(account, password);
            if (student != null) {
                userInfo.setUserId(student.getSid());
                userInfo.setUserName(student.getName());
                userInfo.setUserType("学生");
                model.addAttribute("user", userInfo);
                return "main";
            }
        }
        if (userInfo.getUserId() == null) {
            page = "error";
        }
        return page;
    }

    @RequestMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response, HttpServletRequest request) throws Exception {
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        session.removeAttribute("user");
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }


}
