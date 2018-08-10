package com.jqubian.wechat.dao;

import com.jqubian.wechat.bean.Goods;
import org.apache.ibatis.annotations.Mapper;

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
@Mapper
public interface GoodsMapper {

    int insert(Goods goods);

    int deleteById(Integer id);

    int deleteByService(Goods goods);

    int updateById(Goods goods);

    Goods selectById(Integer id);

    List<Goods> selectAll();

    List<Goods> selectByType(int type);

    List<Goods> selectByService(Goods goods);

}
