package com.jqubian.wechat.common;

import java.util.Date;
import java.util.Random;

/**
 * @Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @DateTime 2018/4/26
 *
 * <p>
 * A novice on the road, please give me a suggestion.
 * 众里寻他千百度，蓦然回首，那人却在，灯火阑珊处。
 * Find a way for success and not make excuses for failure.
 * </p>
 * <p>
 * 产生订单编号工具类
 */
public final class OrderIdHelper {


    private static OrderIdHelper Instance=new OrderIdHelper();

    public static OrderIdHelper getInstance(){
        return Instance;
    }

    private OrderIdHelper(){

    }

    public String getOrderId(String userId) {
        Random random=new Random();
        String temp="";
        for (int i=0;i<6;++i){
            temp+= String.valueOf(random.nextInt(10));
        }
        if (userId.length()<4){
            userId="B00O";
        }
        if (userId.length()>4){
            userId=userId.substring(userId.length()-4);
        }
        StringBuilder str = new StringBuilder(18);

        str.append("CG").append("18").append(temp).append(DateUtil.formatTime(new Date())).append(userId);
        return str.toString();
    }

}
