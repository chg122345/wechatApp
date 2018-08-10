package com.jqubian.wechat.service.impl;

import com.jqubian.wechat.bean.WebInfo;
import com.jqubian.wechat.dao.WebInfoMapper;
import com.jqubian.wechat.service.WebInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class WebInfoServiceImpl implements WebInfoService {

    @Autowired
    private WebInfoMapper webInfoDao;

    @Override
    public int insert(WebInfo webInfo) {
        return webInfoDao.insert(webInfo);
    }

    @Override
    public int deleteById(Integer id) {
        return webInfoDao.deleteById(id);
    }

    @Override
    public int updateById(WebInfo webInfo) {
        return webInfoDao.updateById(webInfo);
    }

    @Override
    public WebInfo select() {
        return webInfoDao.selectAll();
    }
}
