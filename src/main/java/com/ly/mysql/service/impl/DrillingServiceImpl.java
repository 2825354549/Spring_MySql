package com.ly.mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.mysql.domain.Drilling;
import com.ly.mysql.service.DrillingService;
import com.ly.mysql.mapper.DrillingMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liaoyu
* @description 针对表【drilling4】的数据库操作Service实现
* @createDate 2024-04-23 15:44:26
*/
@Service
public class DrillingServiceImpl extends ServiceImpl<DrillingMapper, Drilling>
    implements DrillingService{

    @Override
    public List<Drilling> selectList() {
        return baseMapper.selectList();
    }

}




