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
 *  保存用户的openId 和sessionKey
 */
public class SessionKeyAndOpenId {

    private String sessionKey;
    private String openId;

    public SessionKeyAndOpenId() {
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "SessionKeyAndOpenId{" +
                "sessionKey='" + sessionKey + '\'' +
                ", openId='" + openId + '\'' +
                '}';
    }
}
