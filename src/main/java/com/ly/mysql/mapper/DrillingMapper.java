package com.ly.mysql.mapper;

import com.ly.mysql.domain.Drilling;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import org.springframework.context.annotation.Bean;

import java.util.List;

/**
* @author liaoyu
* @description 针对表【drilling4】的数据库操作Mapper
* @createDate 2024-04-23 15:44:26
* @Entity com.ly.mysql.domain.Drilling4
*/
public interface DrillingMapper extends BaseMapper<Drilling> {
    List<Drilling> selectList();
}




