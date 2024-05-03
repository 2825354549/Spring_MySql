package com.ly.springboot;

//import com.ly.springboot.service.NormalService;
import com.ly.springboot.service.NormalService;
import com.ly.utils.DataConfig;
//import com.ly.utils.NormalizationProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@EnableConfigurationProperties(DataConfig.class)
public class StdMeanServiceTest {


//    @Autowired
//    private NormalizationProperties normalizationProperties;


//    @Autowired
//    private DataConfig dataConfig;

    @Autowired
    private NormalService normalizationProperties;

    @Test
    public void testProperties() {
        System.out.println("MD std: " + normalizationProperties.getMdStd());
        System.out.println("MD mean: " + normalizationProperties.getMdMean());
        System.out.println("WOB std: " + normalizationProperties.getWobStd());
        System.out.println("WOB mean: " + normalizationProperties.getWobMean());
        // 其他属性的输出...
    }
}
