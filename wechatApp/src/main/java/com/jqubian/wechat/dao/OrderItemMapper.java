package com.jqubian.wechat.dao;

import com.jqubian.wechat.bean.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
@Mapper
public interface OrderItemMapper {

    int insert(OrderItem orderItem);

    int deleteById(Integer id);

    int deleteByService(OrderItem orderItem);

    int deleteByOrderId(@Param("orderId") String orderId);

    int updateById(OrderItem orderItem);

    OrderItem selectById(Integer id);

    List<OrderItem> selectAll();

    List<OrderItem> selectByService(OrderItem orderItem);
}
