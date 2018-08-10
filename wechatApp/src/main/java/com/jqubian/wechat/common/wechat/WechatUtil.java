package com.jqubian.wechat.common.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
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
public class WechatUtil {

    /**
     *  获取sessionKey
     * @param appId
     * @param secret
     * @param js_code
     * @return
     */
    public static SessionKeyAndOpenId getSessionKey_OpenId(String appId, String secret, String js_code){
        SessionKeyAndOpenId sessionKeyAndOpenId =null;
        //官方接口，需要自己提供appid，secret和js_code
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?appid="+appId+"&secret="+secret+"&js_code="+js_code+"&grant_type=authorization_code";
        String oppid = null;
        try {
            oppid = new HttpRequestHelper().doGet(requestUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(oppid)){
            sessionKeyAndOpenId =JSONObject.parseObject(oppid,SessionKeyAndOpenId.class);
        }
        return sessionKeyAndOpenId;
    }

    /**
     * 获取用户信息
     * @param encryptedData
     * @param sessionKey
     * @param iv
     * @return
     */
    public static JSONObject getUserInfo(String encryptedData, String sessionKey, String iv){
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);

        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static GeneralToken getToken(String appId, String secret){
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("appid", appId);
            params.put("secret", secret);
            params.put("grant_type", "client_credential");
            String url = "https://api.weixin.qq.com/cgi-bin/token";
            return JSON.parseObject(new HttpRequestHelper().doPost(url,params),GeneralToken.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
