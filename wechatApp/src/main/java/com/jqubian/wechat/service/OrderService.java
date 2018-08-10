package com.jqubian.wechat.service;

import com.jqubian.wechat.bean.Order;

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
public interface OrderService {

    int insert(Order order);

    int deleteById(Integer id);

    int deleteByService(Order order);

    int updateByservice(Order order);

    Order selectById(Integer id);

    List<Order> selectAll();

    List<Order> selectByService(Order order);


}
