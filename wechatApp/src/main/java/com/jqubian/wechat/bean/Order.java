package com.jqubian.wechat.bean;

import com.jqubian.wechat.common.DateUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
 *  订单信息
 */
public class Order implements Serializable {

    private Integer id;
    private String orderId; //订单编号 随机生成
    private Integer totalNumber; //总数量
    private double totalMoney;  //总价
    private Integer status;//订单状态
    //1-m
    private User user;  //订单所属用户
    //m-1
    private List<OrderItem> items = new ArrayList<OrderItem>(); //订单详细项信息

    private Date orderTime; // 下单时间


    public Order() {
    }

    public String getOrderTime() {
        if (orderTime!=null){
            return DateUtil.formatDatetime(orderTime);
        }
        return null;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getTotalNumber() {
        if (totalNumber!=null) {
            return totalNumber;
        } else {
            int temp=0;
            for (OrderItem orderItem : items) {
                temp += orderItem.getNumber();
            }
            return temp;
        }
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public double getTotalMoney() {
        if (totalMoney > 0.0) {
            return totalMoney;
        } else {
            for (OrderItem orderItem : items) {
                totalMoney += orderItem.getMoney();
            }
            return totalMoney;
        }

    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", totalNumber=" + totalNumber +
                ", totalMoney=" + totalMoney +
                ", status=" + status +
                ", user=" + user +
                ", items=" + items +
                '}';
    }
}
