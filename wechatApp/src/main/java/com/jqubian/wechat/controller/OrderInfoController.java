package com.jqubian.wechat.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jqubian.wechat.bean.*;
import com.jqubian.wechat.common.Constant;
import com.jqubian.wechat.common.Msg;
import com.jqubian.wechat.common.wechat.SessionKeyAndOpenId;
import com.jqubian.wechat.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
 *  用户的订单信息
 */
@RestController
@RequestMapping("/api")
public class OrderInfoController {

    @Autowired
    private OrderService orderService;

    /**
     *  获取用户的所有订单信息
     * @param token
     * @return
     */
    @GetMapping("orderInfo")  //success
    public Msg getOrderInfo(@RequestParam("token") String token){
        if (StringUtils.isEmpty(token)){
            return Msg.fail().put("err","请先登录账号..");
        }
        SessionKeyAndOpenId sessionKeyAndOpenId =UserController.getTokens().get(token);
        String openId= sessionKeyAndOpenId.getOpenId();
        if (StringUtils.isEmpty(openId)){
            return Msg.fail().put("err","会话超时..");
        }
        User user=new User();
        user.setId(openId);
        Order order=new Order();
        order.setUser(user);
        List<Order> orders =orderService.selectByService(order);
        if (orders.size()>0){
            return Msg.success().put(Constant.USER_ORDER_INFO,orders);
        }
        return Msg.fail();
    }

   /* @PostMapping("orderInfo") //success
    public Msg saveOrderInfo(@RequestBody Order order){
       // System.out.println("controller 进入"+order.toString());
       if (order!=null){
           if (orderService.insert(order)>0){
               return Msg.success();
           }
       }
        return Msg.fail();
    }*/

    /**
     * 创建订单
     * @param items  订单项 list json串
     * @param address  收货信息 json串
     * @return
     */
    @PostMapping("orderInfo") //success
    public Msg saveOrderInfo(@RequestParam("token") String token,@RequestParam("items") String items,@RequestParam("address") String address){
        if (StringUtils.isEmpty(token)){
            return Msg.fail().put("err","请先登录账号..");
        }
        SessionKeyAndOpenId sessionKeyAndOpenId =UserController.getTokens().get(token);
        String openId= sessionKeyAndOpenId.getOpenId();
        if (StringUtils.isEmpty(openId)){
            return Msg.fail().put("err","会话超时..");
        }
        ObjectMapper mapper = new ObjectMapper();
        List<OrderItem> orderItems= null;
        Address address1=null;
        try {
            orderItems = mapper.readValue(items, new TypeReference<List<OrderItem>>(){});
            address1=mapper.readValue(address,Address.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
       User user= new User();
        user.setId(openId);
        user.setName(address1.getName());
        user.setPhone(address1.getPhone());
        user.setAddress(address1.getDetail());
        System.out.println("user-->"+user);
        Order order=new Order();
        order.setUser(user);  //所属用户放进去
         order.setItems(orderItems);   //订单项加进去
        if (order!=null){
            if (orderService.insert(order)>0){
                return Msg.success();
            }
        }
        return Msg.fail();
    }
   /* @PostMapping("orderInfo") //success
    public Msg saveOrderInfo(HttpSession session){
        //从session中取出购物车信息
        Cart cart= (Cart) session.getAttribute(Constant.SHOP_CAR);
        User user= (User) session.getAttribute(Constant.USER_ISLOGIN); //TODO 待修改
        Order order=new Order();
        order.setUser(user);  //所属用户放进去
        List<OrderItem> items =new ArrayList<OrderItem>();
        //遍历购物车中的购物项
        for (Map.Entry<Integer,CartItem> em:cart.getItems().entrySet()){
            CartItem cItem=em.getValue();  //得到购物项
            OrderItem item = new OrderItem();
            item.setNumber(cItem.getNumber()); //购买数量
            item.setGoods(cItem.getGoods());  //商品
            items.add(item);  //添加进购物项
        }
        order.setItems(items);   //订单项加进去
        if (order!=null){
            if (orderService.insert(order)>0){
                return Msg.success();
            }
        }
        return Msg.fail();
    }*/

    /**
     * 根据订单id删除订单
     * @param id 订单id
     * @return
     */
    @DeleteMapping("orderInfo") //success
    public Msg deleteOrderInfo(@RequestParam("id") Integer id){
        if (orderService.deleteById(id) > 0) {
            return Msg.success();
        }
        return Msg.fail();
    }


    /**
     *  根据订单id修改更新订单信息
     *  把id跟要更新的信息封装成order对象
     * @param order
     * @return
     */
    @PutMapping("orderInfo") //success
    public Msg updateOrderInfo(@RequestBody Order order){
     //   System.out.println("更新-->"+order);
        if (orderService.updateByservice(order) > 0) {
            return Msg.success();
        }
        return Msg.fail();
    }
}
