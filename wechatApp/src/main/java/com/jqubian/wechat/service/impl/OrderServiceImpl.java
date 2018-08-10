package com.jqubian.wechat.service.impl;

import com.jqubian.wechat.bean.Order;
import com.jqubian.wechat.bean.OrderItem;
import com.jqubian.wechat.common.OrderIdHelper;
import com.jqubian.wechat.dao.OrderItemMapper;
import com.jqubian.wechat.dao.OrderMapper;
import com.jqubian.wechat.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public int insert(Order order) {
        String temp = OrderIdHelper.getInstance().getOrderId(String.valueOf(order.getUser().getPhone()));
        order.setOrderId(temp);
        int result = orderMapper.insert(order);
        List<OrderItem> orderItems = order.getItems();
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderId(temp);
            result+=orderItemMapper.insert(orderItem);
        }

        return result;
    }

    @Override
    public int deleteById(Integer id) {
        return orderMapper.deleteById(id);
    }

    @Override
    public int deleteByService(Order order) {
        return orderMapper.deleteByService(order);
    }

    @Override
    public int updateByservice(Order order) {
        return orderMapper.updateByservice(order);
    }

    @Override
    public Order selectById(Integer id) {
        return orderMapper.selectById(id);
    }

    @Override
    public List<Order> selectAll() {
        return orderMapper.selectAll();
    }

    @Override
    public List<Order> selectByService(Order order) {
        return orderMapper.selectByService(order);
    }
}
