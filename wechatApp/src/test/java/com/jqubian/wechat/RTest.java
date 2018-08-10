package com.jqubian.wechat;

import com.jqubian.wechat.bean.Goods;
import com.jqubian.wechat.bean.shopcar.Cart;
import com.jqubian.wechat.bean.shopcar.CartItem;
import com.jqubian.wechat.common.DateUtil;
import com.jqubian.wechat.common.wechat.WechatUtil;
import org.junit.Test;

import java.util.*;


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
public class RTest {



   /* @Test
    public void TT0(){

        System.out.println(localProperties.getUrl());
    }*/

    @Test
    public void TTT() {
        Random random = new Random();
        String temp = "";
        for (int i = 0; i < 6; ++i) {
            temp += String.valueOf(random.nextInt(4));
        }
        System.out.println(random.nextInt(10));
    }

    @Test
    public void TTT2() {
        Calendar cal = Calendar.getInstance();
        String d = String.valueOf(cal.get(Calendar.DATE));
        String mi = String.valueOf(cal.get(Calendar.MINUTE));
        String s = String.valueOf(cal.get(Calendar.SECOND));
        System.out.println(DateUtil.formatTime(new Date()));
    }

    @Test
    public void TTT23() {
        Goods goods = new Goods();
        goods.setName("商品1号");
        goods.setPrice(5.5);
        CartItem cartItem = new CartItem(goods);
        cartItem.setNumber(2);
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);
        Cart cart = new Cart();
        cart.setItems(cartItems);
        System.out.println(cart);
    }

    @Test
    public void TTT234() {
        String encryptedData = "AxPySuOvpeIuVJZYquilnqf+Bq3LbF47zvQA45SGY3aBh0gf/sGLVYGH8+roNBc7/TAjhiAt2Ow0d0xi//IrXZsifTrk4JTeRR2u/B5MEPK4tW5AodmE1yI2+L26FkD6l1aX/lCNC2hbbvwxXsvUDkI/iyAdmr6wXxYJQ8Ic8VgdfX++kcc/34CyjDZ7EoSiEOTik/i7BzecIusNrJOIbaPRe0nIOzEqB2JIIe7tBpmbPb8L7h69q6vMGKh7gYwtZAreS8m/yMsyo+LnB6KeUUxstU6QK3juR1E5bYI+wAmLon93QB1OtgF9Ar07xoL59qbroT+4uGJxa5GlNketzJCEKzIqXsuqCdhqCzNEwRovzWbHe8NWqYA63u/6x57bgEuyP5NoosnSsD4ReZHI4TfeDO8+310ZvUrNsmjOtoAzIkCXXrN8fTaEsRrDd2f6DfiYlo+iNMe7VLMV13stbEkGCHGXjTcDVb2wbhT1yl0=";//"hcz1nY87415kDEafj29s0BfNG5caJ3egDAp0QQ/Du8/22i+Tp78yAQUQwE+CNTP237THWYSCnkCKmeok6RSYoaXesHLF7hm4ZxIokNd/16xAiZN5TASM9V2O2uNbQBkwTXN2g8GReYAjznegaYNq/2ITVZe2sp4XeyBoCA9I2DciBn16O7y/lnXChAFyrM0vuqX/Q11FqnoNslYMEOg+Y6O0UZQ3+qX7Pzepk7G1dpQ5rjTtCaZIpfYLJtoiB8YLsfuf7YhZhOR4s7Gz4FDwlrxASSpL4uqlNccliG3vkGmZuPksrc39ktV3dyW7WMc3x///+7yyp0OArzcfNOqYyecuURVjwO0JqTb+0smlyq39hGZwJ2YK4ykdDJTKPdm78RD18fQKXIt/3YSEDcX++i3gPTwaPzXzQPfJ8EdZKcSMSPQAcD3xIuGC0qiqo/yLQZRf/mFk2YI3CMzZ+f1xphPaVqaA4mDhmOS95rgbyNFovJkO2Vq7nImyVDcAJufW";
        String iv = "IPbLocPqYDEGMZr1pGPcSw==";
        //String sessionKey="0l4VsRotiT2i0mqbAz/YPA==";
        String appId = "wx6105a569cd9d7154";
        String secret = "cf6ef6056a062aa00703197fc5eee6b7";
      /* String sessionKey=WechatUtil.getSessionKey(appId,secret,"011HgJq71zTuXR1Gmrp710a6r71HgJq-");
       System.out.println(sessionKey);*/

        // System.out.println( "获取结果：---"+WechatUtil.getUserInfo(encryptedData,sessionKey,iv));
      //  System.out.println("获取结果：---" + WechatUtil.getToken(appId, secret));
        /*{"country":"China","watermark":{"appid":"wx6105a569cd9d7154","timestamp":1527241096}
                ,"gender":1,"province":"Jiangxi","city":"Yichun"
                , "avatarUrl":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83epmiax6QzjpTmqn4H3IbdvaTeOibJIzdlxwJco6pOGXDibtxHlSRHzTO8xrdW1zpfFkic5GPP2J4Yjvvg/132"
                ,"openId":"oNcCG5I2TbppaHnU7ZYMNA8X36Mg","nickName":"NullPointerException","language":"zh_CN"}*/

        /*{
            "access_token":
            "10_pIForTdHW55RNWSE1NPwTCGxM5iCH0zIsQt7Kp5KGK0bSOCVePHcSN6AXFS7GCi2MFZWE-f0JNlBtSj3quJuwb9yS9EM1PBLKiYqFk2yXW39SRaN_CDzLPCmMYg1IBoUOh7iexjxIzozOO4nHERcAAAPMZ",
             "expires_in":7200
        }*/
    }
}
