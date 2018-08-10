package com.jqubian.wechat.service.impl;

import com.jqubian.wechat.bean.User;
import com.jqubian.wechat.dao.UserMapper;
import com.jqubian.wechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userDao;
    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public int deleteById(Integer id) {
        return userDao.deleteById(id);
    }

    @Override
    public int updateById(User user) {
        return userDao.updateById(user);
    }

    @Override
    public User select() {
        return userDao.selectAll().get(0);
    }

    @Override
    public User check(String username, String password) {
        return userDao.check(username,password);
    }

    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }
}
