package com.jqubian.wechat.common.wechat;

/**
 * @Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @DateTime 2018/5/25
 *
 * <p>
 * A novice on the road, please give me a suggestion.
 * 众里寻他千百度，蓦然回首，那人却在，灯火阑珊处。
 * Find a way for success and not make excuses for failure.
 * </p>
 * token
 */
public class Token {

    private Token() {}
    private  String token;
    private static Token instance  = new Token();
    public static Token getInstance() {
        return instance;
    }
    public  String getToken() {
        return token;
    }
    public  void setToken(String token) {
        this.token = token;
    }
}
