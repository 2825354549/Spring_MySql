package com.ly.utils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.Map;


@Component
@ConfigurationProperties(prefix ="datas")
public class NormalizationProperties {

    private Map<String, Normalization> normalizations;

    public Map<String, Normalization> getNormalizations() {
        return normalizations;
    }

    // 添加一个方法来根据参数名称获取std和mean
    public Normalization getNormalization(String parameterName) {
        return normalizations.get(parameterName);
    }
    @Data
    public class Normalization {
        @Value("${datas.std}")
        private double std;
        @Value("${datas.std}")
        private double mean;
    }
}

/**
 * MD.std=862.88
 MD.mean=1892.28
 WOB.std=3.53
 WOB.mean=5.39
 SP.std=4564.3
 SP.mean=16750.58
 TQ.std=4.78
 TQ.mean=10.82
 RS.std=35.55
 RS.mean=136.51
 MF.std=1038.33
 MF.mean=3267.38
 HL.std=17.74
 HL.mean=123.59
 TVD.std=791.45
 TVD.mean=1810.23
 ROP.std=15.52
 ROP.mean=26.22*/

//    public double normalizeSP(double value) {
//        return (value - spMean) / spStd;
//    }
//
//    public double normalizeTQ(double value) {
//        return (value - tqMean) / tqStd;
//    }
//
//    public double normalizeRS(double value) {
//        return (value - rsMean) / rsStd;
//    }
//
//    public double normalizeMF(double value) {
//        return (value - mfMean) / mfStd;
//    }
//
