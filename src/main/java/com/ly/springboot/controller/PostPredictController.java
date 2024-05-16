package com.ly.springboot.controller;

import com.ly.mysql.domain.Drilling;
import com.ly.springboot.service.PredictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/predict")
public class PostPredictController {


    @Autowired
    private PredictService predictService;

    @GetMapping("/data")
//    public List<Drilling> getDrillingData() {
//        return predictService.getList();
//    }
    public float[][] getPredictedData() {
        try {
            // 调用predict3方法并返回预测数据
            return predictService.predict3();
        } catch (Exception e) {
            // 异常处理逻辑，根据需要进行修改
            e.printStackTrace();
            return null; // 或者返回一个错误信息
        }
    }
}

