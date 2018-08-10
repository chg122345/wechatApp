package com.jqubian.wechat.bean.shopcar;


import com.jqubian.wechat.bean.Goods;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class Cart implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<CartItem> items=new ArrayList<CartItem>();
	private Integer totalNumber;
	private double totalMoney;


	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	public Integer gettotalNumber() {
		   totalNumber=0;
		for(CartItem g:items) {
		    totalNumber +=g.getNumber();
			}
		return totalNumber;
	}
	
	
	public void settotalNumber(Integer totalNumber) {
		
		this.totalNumber = totalNumber;
	}
	
	
	public double gettotalMoney() {
		totalMoney=0;
		for(CartItem g:items) {
	    totalMoney +=g.getMoney();
		}
		return totalMoney;
	}
	
	
	public void settotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	
	public List <CartItem> getItems() {
		return items;
	}
	
	

	//添加购物车
	public void addGoods(Goods goods,Integer number){

		for (CartItem item:items) {
			if (item.getGoods().getId()==(goods.getId())) {
				//购物车有该商品
				item.setNumber(item.getNumber() + number);
			} else {
				CartItem item1 = new CartItem(goods);
				item1.setNumber(number);
				items.add(item1);
			}
		}
	}


	@Override
	public String toString() {
		return "Cart{" +
				"items=" + items +
				", totalNumber=" + totalNumber +
				", totalMoney=" + totalMoney +
				'}';
	}
}
