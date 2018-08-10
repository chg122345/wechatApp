package com.jqubian.wechat.service.impl;

import com.jqubian.wechat.bean.Goods;
import com.jqubian.wechat.dao.GoodsMapper;
import com.jqubian.wechat.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsdao;

    @Override
    public int insert(Goods goods) {
        return goodsdao.insert(goods);
    }

    @Override
    public int deleteById(Integer id) {
        return goodsdao.deleteById(id);
    }

    @Override
    public int deleteByService(Goods goods) {
        return goodsdao.deleteByService(goods);
    }

    @Override
    public int updateById(Goods goods) {
        return goodsdao.updateById(goods);
    }

    @Override
    public Goods selectById(Integer id) {
        return goodsdao.selectById(id);
    }

    @Override
    public List<Goods> selectAll() {
        return goodsdao.selectAll();
    }

    @Override
    public List<Goods> selectByType(int type) {
        return goodsdao.selectByType(type);
    }

    @Override
    public List<Goods> selectByService(Goods goods) {
        return goodsdao.selectByService(goods);
    }
}
