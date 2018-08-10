package com.jqubian.wechat.controller;

import com.jqubian.wechat.common.Constant;
import com.jqubian.wechat.common.Msg;
import com.jqubian.wechat.common.wechat.GeneralToken;
import com.jqubian.wechat.common.wechat.SessionKeyAndOpenId;
import com.jqubian.wechat.common.wechat.WechatUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @DateTime 2018/5/25
 *
 * <p>
 * A novice on the road, please give me a suggestion.
 * 众里寻他千百度，蓦然回首，那人却在，灯火阑珊处。
 * Find a way for success and not make excuses for failure.
 * </p>
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Value("${appId}")
    private  String appId ; //= "wx6105a569cd9d7154";
    @Value("${secret}")
    private String secret ; //= "cf6ef6056a062aa00703197fc5eee6b7";
    /**
     * 保存所有用户的token 和信息
     */
    private static Map<String,SessionKeyAndOpenId> tokens=new HashMap<String,SessionKeyAndOpenId>();

    @GetMapping("/login")
    public Msg login(@RequestParam("code") String code){
        SessionKeyAndOpenId S_O=WechatUtil.getSessionKey_OpenId(appId,secret,code);
        if (S_O!=null){
            GeneralToken generalToken=WechatUtil.getToken(appId,secret);
            String token=generalToken.getAccess_token();
            if (!StringUtils.isEmpty(token)){
                tokens.put(token,S_O);
              //  System.out.println("登录成功"+" "+getTokens().toString());
                return Msg.success().put(Constant.TOKEN,token);
            }
        }
        return Msg.fail();
    }

    @GetMapping("checkToken")
    public Msg checkToken(@RequestParam("token") String token){
        SessionKeyAndOpenId so=getTokens().get(token);
        if (so!=null){
            System.out.println("openid"+so.getOpenId());
            return Msg.success();
        }
        return Msg.fail();
    }
    public static Map<String, SessionKeyAndOpenId> getTokens() {
        return tokens;
    }
}
