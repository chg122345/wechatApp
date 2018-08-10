package com.jqubian.wechat.bean;

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
public class OrderItem {

    private Integer id;
    private Integer number; //购买数量
    private double money;  //小计金额
    //1-1
    private Goods goods;
    //m-1
    private String orderId;

    public OrderItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney() {
        if (money > 0.0) {
            return money;
        } else {
            return goods.getPrice() * number;
        }
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", number=" + number +
                ", money=" + money +
                ", goods=" + goods +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
