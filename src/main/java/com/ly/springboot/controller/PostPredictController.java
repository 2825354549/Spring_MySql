package com.ly.springboot.controller;


import com.ly.mysql.domain.Drilling;
import com.ly.springboot.service.PredictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/predict")
public class PostPredictController {

    @Autowired
    private PredictService predictService;

    @GetMapping("/data")
    public List<Drilling> getDrillingData() {
        return predictService.getList();
    }

}
