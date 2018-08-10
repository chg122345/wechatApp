package com.jqubian.wechat;

import com.jqubian.wechat.bean.Goods;
import com.jqubian.wechat.bean.Order;
import com.jqubian.wechat.bean.OrderItem;
import com.jqubian.wechat.bean.User;
import com.jqubian.wechat.dao.OrderItemMapper;
import com.jqubian.wechat.dao.OrderMapper;
import com.jqubian.wechat.service.GoodsService;
import com.jqubian.wechat.service.OrderService;
import com.jqubian.wechat.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatApplicationTests {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    @Transactional
    public void GoodsServiceTest() {

        Goods g = new Goods();
        g.setId(3);
        // g.setName("商品1修改");
        //  g.setabstracts("这是商品3号");
        g.setPrice(6.5);
        g.setImg("/static/goods/img");
        System.out.println(goodsService.deleteByService(g));
    }

    @Test
    @Transactional
    public void UserServiceTest() {

        User user = new User();
        user.setName("GXF");
        user.setPassword("123456");
        user.setPhone("15770549440");
        userService.insert(user);

    }

   /* @Test
    @Transactional
    public void orderTest() {
        User user=new User();
        user.setId(2);

        Goods goods=new Goods();
        goods.setId(1);
        goods.setPrice(10.9);

        List<OrderItem> items=new ArrayList<>();
        OrderItem orderItem=new OrderItem();
        orderItem.setGoods(goods);
        orderItem.setNumber(2);
        items.add(orderItem);

        Goods goods1=new Goods();
        goods1.setId(2);
        goods1.setPrice(8.5);

        OrderItem orderItem1=new OrderItem();
        orderItem1.setGoods(goods1);
        orderItem1.setNumber(4);
        items.add(orderItem1);

        Order order=new Order();
        order.setUser(user);
        order.setItems(items);
        System.out.println(orderService.insert(order));

    }*/

   /* @Test
    @Transactional
    public void orderItemTest() {
        Goods goods=new Goods();
        goods.setPrice(3.5);
        goods.setId(2);
        Order order=new Order();
        order.setId(1);
        OrderItem orderItem=new OrderItem();
        orderItem.setId(1);
        orderItem.setNumber(6);
        orderItem.setGoods(goods);
      //  orderItem.setOrder(order);

        System.out.println(orderItemMapper.selectAll());

    }*/

}
