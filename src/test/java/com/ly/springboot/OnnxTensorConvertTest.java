package com.ly.springboot;
import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtSession;
import com.ly.mysql.domain.Drilling;
import com.ly.mysql.mapper.DrillingMapper;
import com.ly.springboot.service.OnnxTensorConverter;
import com.ly.springboot.service.OnnxTensorConverter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

//import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class OnnxTensorConvertTest {

    @Autowired
    private DrillingMapper drillingMapper;

    private OnnxTensorConverter  onnxTensorConverter;

    @Test
    public void testConvertListToTensor() throws Exception {
         System.out.println("testConvertListToTensor");
    }
//
//    @Test
//    public void testConvertListToTensor2) throws Exception {
//
//
//    List<Drilling> drillings = drillingMapper.selectList(null);
//
//        // 将List转换为OnnxTensor对象
////        OnnxTensor onnxTensor = OnnxTensorConverter.convertListToTensor(drillings);
////
////        assertNotNull(onnxTensor);
////         进行进一步的测试    }
//}
}
