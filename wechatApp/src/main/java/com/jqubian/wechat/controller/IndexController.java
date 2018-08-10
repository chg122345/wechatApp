package com.jqubian.wechat.controller;

import com.jqubian.wechat.bean.Admin;
import com.jqubian.wechat.common.Constant;
import com.jqubian.wechat.common.Msg;
import com.jqubian.wechat.dao.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @DateTime 2018/4/26
 *
 * <p>
 * A novice on the road, please give me a suggestion.
 * 众里寻他千百度，蓦然回首，那人却在，灯火阑珊处。
 * Find a way for success and not make excuses for failure.
 * </p>
 */
@Controller
public class IndexController {

    @Autowired
    private AdminMapper adminMapper;

    @RequestMapping({"/index", "/"})
    public String index() {
        return "index";
    }

    @RequestMapping("/add")
    public String add() {
        return "add";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/check")
    @ResponseBody
    public Msg check(HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password) {
        Admin admin = adminMapper.select(username, password);
        if (admin != null) {
            session.setAttribute(Constant.ADMIN_ISLOGIN, admin);
            return Msg.success();
        }
        return Msg.fail();
    }
}
