package com.jqubian.wechat.bean.shopcar;


import com.jqubian.wechat.bean.Goods;

import java.io.Serializable;

/**
 * @Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @DateTime 2018/5/12
 *
 * <p>
 * A novice on the road, please give me a suggestion.
 * 众里寻他千百度，蓦然回首，那人却在，灯火阑珊处。
 * Find a way for success and not make excuses for failure.
 * </p>
 */
public class CartItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Goods goods;
	private Integer number;
	private double money;
	public CartItem(Goods goods) {
		this.goods=goods;
	}
	
	
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public double getMoney() {
		return goods.getPrice()*number;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public Goods getGoods() {
		return goods;
	}

	
	
}
