package com.jqubian.wechat.controller;

import com.jqubian.wechat.bean.User;
import com.jqubian.wechat.bean.WebInfo;
import com.jqubian.wechat.common.Constant;
import com.jqubian.wechat.common.Msg;
import com.jqubian.wechat.service.GoodsService;
import com.jqubian.wechat.service.UserService;
import com.jqubian.wechat.service.WebInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @DateTime 2018/4/25
 *
 * <p>
 * A novice on the road, please give me a suggestion.
 * 众里寻他千百度，蓦然回首，那人却在，灯火阑珊处。
 * Find a way for success and not make excuses for failure.
 * </p>
 */
@RestController
@RequestMapping("/api")
public class InfoController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private WebInfoService webInfoService;

    @Autowired
    private UserService userService;


    @RequestMapping("/unLogin")
    public Msg unLogin() {

        return Msg.fail().put(Constant.USER_UNLOGIN, "请登录会员账号！");
    }

    @PostMapping("/register")
    public Msg register(User user) {
        if (userService.insert(user) == 1) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping("/check")
    public Msg checkLogin(HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password) {
        User user = userService.check(username, password);
        if (user != null) {
            session.setAttribute(Constant.USER_ISLOGIN, user);
            return Msg.success();
        }
        return Msg.fail();
    }

    @GetMapping("/shopInfo")
    public Msg start() {
        WebInfo webInfo = webInfoService.select();
        return Msg.success().put(Constant.SHOP_INFO, webInfo);
    }

    /**
     * 查所有商品
     * @return
     */
    @GetMapping("/goodsInfo")
    public Msg getGoodsInfo() {
        return Msg.success().put(Constant.GOODS_INFO, goodsService.selectAll());
    }

    /**
     * 根据id查商品
     * @param id
     * @return
     */
    @GetMapping("/goods")
    public Msg getGoodsById(@RequestParam("id") Integer id) {
        return Msg.success().put(Constant.GOODS_INFO, goodsService.selectById(id));
    }

    /**
     * 根据类型查商品
     * @param type
     * @return
     */
    @GetMapping("/goodsType")
    public Msg getGoodsByType(@RequestParam("type") int type) {
        return Msg.success().put(Constant.GOODS_INFO, goodsService.selectByType(type));
    }
}
