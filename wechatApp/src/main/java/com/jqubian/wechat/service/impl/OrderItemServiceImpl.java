package com.jqubian.wechat.service.impl;

import com.jqubian.wechat.bean.OrderItem;
import com.jqubian.wechat.dao.OrderItemMapper;
import com.jqubian.wechat.service.OrderItemService;
import org.springframework.stereotype.Service;

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
public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemMapper orderItemMapper;
    @Override
    public int insert(OrderItem orderItem) {
        return orderItemMapper.insert(orderItem);
    }

    @Override
    public int deleteById(Integer id) {
        return orderItemMapper.deleteById(id);
    }

    @Override
    public int deleteByService(OrderItem orderItem) {
        return orderItemMapper.deleteByService(orderItem);
    }

    @Override
    public int deleteByOrderId(String orderId) {
        return orderItemMapper.deleteByOrderId(orderId);
    }

    @Override
    public int updateByService(OrderItem orderItem) {
        return orderItemMapper.updateById(orderItem);
    }

    @Override
    public OrderItem selectById(Integer id) {
        return orderItemMapper.selectById(id);
    }

    @Override
    public List<OrderItem> selectAll() {
        return orderItemMapper.selectAll();
    }

    @Override
    public List<OrderItem> selectByService(OrderItem orderItem) {
        return orderItemMapper.selectByService(orderItem);
    }
}
