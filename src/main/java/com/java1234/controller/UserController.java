package com.java1234.controller;

import com.java1234.entity.User;
import com.java1234.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request) {
        User resultUser = userService.login(user);
        if (resultUser == null) {
            request.setAttribute("user", user);
            request.setAttribute("errorMsg", "用户名或者密码错误！");
            return "index";
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", resultUser);
            return "redirect:/success.jsp";
        }
    }
}
