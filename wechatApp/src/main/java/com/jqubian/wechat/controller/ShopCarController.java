package com.jqubian.wechat.controller;

import com.jqubian.wechat.bean.Goods;
import com.jqubian.wechat.bean.shopcar.Cart;
import com.jqubian.wechat.common.Constant;
import com.jqubian.wechat.common.Msg;
import com.jqubian.wechat.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @DateTime 2018/4/26
 *
 * <p>
 * A novice on the road, please give me a suggestion.
 * 众里寻他千百度，蓦然回首，那人却在，灯火阑珊处。
 * Find a way for success and not make excuses for failure.
 * </p>
 *
 *  购物车session实现
 */
@RestController
@RequestMapping("/api")
public class ShopCarController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/shopCar") //success
    public Msg addCar(HttpSession session, @RequestParam("id") Integer goodsId,
                      @RequestParam("number") Integer number){

        Goods goods=goodsService.selectById(goodsId);
        Cart cart= (Cart) session.getAttribute(Constant.SHOP_CAR);
        if (cart==null){
            cart =new Cart();
            session.setAttribute(Constant.SHOP_CAR,cart);
        }
        cart.addGoods(goods,number);   //添加进购物车
        return Msg.success().put(Constant.SHOP_CAR,cart);
    }

   /* @RequestMapping("/shopCar")
    public Msg addCar(HttpServletResponse response,HttpServletRequest request, @RequestParam("id") Integer goodsId,
                      @RequestParam("number") Integer number){

        Goods goods=goodsService.selectById(goodsId);
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies){
            if (Constant.SHOP_CAR.equals(cookie.getName())){
                Cart cart=cookie.getValue();
            }
        }

        if (cart==null){
            cart =new Cart();
            session.setAttribute(Constant.SHOP_CAR,cart);
        }
        cart.addGoods(goods,number);   //添加进购物车
        return Msg.success().put(Constant.SHOP_CAR,cart);
    }
*/
    @DeleteMapping("/shopCar/{id}") //success
    public Msg addCar(HttpSession session,@PathVariable("id") Integer goodsId){

       Cart cart= (Cart) session.getAttribute(Constant.SHOP_CAR);
       cart.getItems().remove(goodsId); //移除指定的商品
        return Msg.success().put(Constant.SHOP_CAR,cart);
    }

    @DeleteMapping("/shopCar")
    public Msg addCar(HttpSession session){

        Cart cart= (Cart) session.getAttribute(Constant.SHOP_CAR);
        cart.getItems().clear(); //移除全部的商品
        return Msg.success().put(Constant.SHOP_CAR,cart);
    }

}
