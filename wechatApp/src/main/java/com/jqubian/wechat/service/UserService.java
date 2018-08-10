package com.jqubian.wechat.service;

import com.jqubian.wechat.bean.User;

import java.util.List;

/**
 * @Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @DateTime 2018/5/4
 *
 * <p>
 * A novice on the road, please give me a suggestion.
 * 众里寻他千百度，蓦然回首，那人却在，灯火阑珊处。
 * Find a way for success and not make excuses for failure.
 * </p>
 */
public interface UserService {
    int insert(User user);

    int deleteById(Integer id);

    int updateById(User user);

    User select();

    User check(String username,String password);

    List<User> selectAll();
}
