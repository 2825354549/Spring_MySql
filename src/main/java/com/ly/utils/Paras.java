package com.ly.utils;

import lombok.Data;

/**
 * Paras类型 存储标准差（std）和均值（mean）
 * */
//@Data
public class Paras {

    private float std;
    private float mean;

    // getters and setters
    public float getStd() {
        return std;
    }

    public void setStd(float std) {
        this.std = std;
    }

    public float getMean() {
        return mean;
    }

    public void setMean(float mean) {
        this.mean = mean;
    }
}
