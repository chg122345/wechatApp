package com.jqubian.wechat.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jqubian.wechat.bean.Goods;
import com.jqubian.wechat.bean.Order;
import com.jqubian.wechat.bean.User;
import com.jqubian.wechat.bean.WebInfo;
import com.jqubian.wechat.common.Msg;
import com.jqubian.wechat.common.util.ImgPathUtils;
import com.jqubian.wechat.service.GoodsService;
import com.jqubian.wechat.service.OrderService;
import com.jqubian.wechat.service.UserService;
import com.jqubian.wechat.service.WebInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @DateTime 2018/4/26
 *
 * <p>
 * A novice on the road, please give me a suggestion.
 * 众里寻他千百度，蓦然回首，那人却在，灯火阑珊处。
 * Find a way for success and not make excuses for failure.
 * </p>
 *
 * 管理员后台操作
 */
@Controller
public class AdminController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private WebInfoService webInfoService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Value("${webUrl}")
    private String webUrl ;
    @PostMapping("/goodsInfo")
    @ResponseBody
    public Msg submitGoods(Goods goods, MultipartFile file) {
        if (!file.isEmpty()) {
            String path = ImgPathUtils.getUploadImgBasePath();
            String filename = file.getOriginalFilename();
            File filepath = new File(path);
            if (!filepath.exists()) {
                filepath.mkdirs();
            }
            try {
                file.transferTo(new File(path, filename));
            } catch (IOException e) {
                e.printStackTrace();

            }
            goods.setImg(webUrl + "/icon/" + filename);
        }
        if (goodsService.insert(goods) > 0) {
            return Msg.success();
        } else {
            return Msg.fail();
        }
    }


    @RequestMapping("/list")
    public String list() {
        return "list";
    }

    @RequestMapping("/userList")
    public String userList() {
        return "userlist";
    }

    @RequestMapping("/orderList")
    public String orderList() {
        return "orderlist";
    }

    @GetMapping("/goodsList")
    @ResponseBody
    public Msg getGoodsList(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Goods> goods = goodsService.selectAll();
        PageInfo pageInfo = new PageInfo(goods, pageSize);
        return Msg.success().put("pageInfo", pageInfo);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Msg delete(@PathVariable("id") String ids) {
        ids = ids.substring(0, ids.length() - 1);
        int temp = 0;
        String[] str_ids = ids.split(":");
        for (String string : str_ids) {
            goodsService.deleteById(Integer.parseInt(string));
            temp++;
        }
        if (temp > 0) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @GetMapping("/webInfo")
    public String webInfo(Model model) {
        WebInfo webInfo = webInfoService.select();
        if(webInfo!=null)
        model.addAttribute("webinfo", webInfo);
        return "webinfo";
    }

    @PutMapping("/webInfo")
    @ResponseBody
    public Msg updateWebInfo(WebInfo webInfo) {
        System.out.println("进入controller"+webInfo.getCopyright());
        if (webInfoService.updateById(webInfo) == 1) {
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     *  查询所有的订单
     *   分页显示
     * @param page
     * @param pageSize
     * @return
     */

    @GetMapping("/orderInfo")
    @ResponseBody
    public Msg orderList(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Order> orders = orderService.selectAll();
        PageInfo pageInfo = new PageInfo(orders, pageSize);
        return Msg.success().put("pageInfo", pageInfo);
    }

    /**
     *  批量删除订单信息
     *   例： 1-2-3-4  删除id为1 2 3 4的四个订单信息
     * @param orderIds
     * @return
     */
    @DeleteMapping("orderInfo/{id}") //success
    @ResponseBody
    public Msg deleteOrderInfo(@PathVariable("id") String orderIds){

        String[] ids=orderIds.split("-");

        int temp=0;
        for (String id:ids) {
            temp+=orderService.deleteById(Integer.parseInt(id));
        }

        if (temp> 0) {
            return Msg.success().put("num",temp);
        }
        return Msg.fail();
    }

    /**
     *  根据订单id修改更新订单信息
     *  把id跟要更新的信息封装成order对象
     * @param orderIds
     * @return
     */
    @PutMapping("orderInfo") //success
    @ResponseBody
    public Msg updateOrderInfo(@RequestParam("ids") String orderIds){
        orderIds = orderIds.substring(0, orderIds.length() - 1);
        String[] ids=orderIds.split(":");
        int temp=0;
        for (String id:ids) {
            Order order=new Order();
            order.setId(Integer.parseInt(id));
            order.setStatus(3); //已发货状态
            temp+=orderService.updateByservice(order);
        }

        if (temp> 0) {
            return Msg.success().put("num",temp);
        }
        return Msg.fail();
    }

    /**
     *  查询所有的用户信息
     *   分页显示
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/userInfo")
    @ResponseBody
    public Msg userList(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<User> users = userService.selectAll();
        PageInfo pageInfo = new PageInfo(users, pageSize);
        return Msg.success().put("pageInfo", pageInfo);
    }

    /**
     * 修改用户的信息根据用户id
     *  id封装到user 对象中
     * @param user
     * @return
     */
    @PutMapping("userInfo") //success
    @ResponseBody
    public Msg updateUserInfo(User user){

        if (userService.updateById(user) > 0) {
            return Msg.success();
        }
        return Msg.fail();
    }

}
