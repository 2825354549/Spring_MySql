package com.ly.mysql.service;

import com.ly.mysql.domain.Drilling;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author liaoyu
* @description 针对表【drilling4】的数据库操作Service
* @createDate 2024-04-23 15:44:26
*/
public interface DrillingService extends IService<Drilling> {
    List<Drilling> selectList();
//    List<Drilling> selectList();
}
