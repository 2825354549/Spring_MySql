package com.ly.springboot;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OnnxValue;
import ai.onnxruntime.OrtSession;
import com.ly.mysql.domain.Drilling;
//import io.lettuce.core.api.push.PushListener;
//import com.ly.springboot.service.NormalService;
//import com.ly.springboot.service.OnnxTensorConverter;
import com.ly.mysql.mapper.DrillingMapper;
import com.ly.springboot.service.NormalService;
import com.ly.springboot.service.OnnxTensorConverter;
import com.ly.springboot.service.PredictService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//import java.util.Arrays;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SpringMySqlApplicationTests {
    //	@Autowired
//	private DrillingMapper drillingMapper;
    @Autowired
    private PredictService predictService;
//    @Autowired
//    private OnnxTensorConverter onr;

    //	@Autowired
//	private NormalService normalService;
    public void print3DArray(float[][][] array) {
        for (int i = 0; i < array.length; i++) {
//            System.out.println("第 " + (i + 1) + " 维:");
            for (int j = 0; j < array[i].length; j++) {
                for (int k = 0; k < array[i][j].length; k++) {
                    System.out.print(array[i][j][k] + " ");
                }
                System.out.println(); // 每个内层数组打印完毕后换行
            }
            System.out.println(); // 每个中层数组打印完毕后换行
        }
    }

    @Test
    public void testPredService() throws Exception {
        // 调用PredictService中的方法并断言结果是否符合预期
        // 这里可以根据具体情况编写测试逻辑
        List<Drilling> drillingMapper = predictService.getList();
        drillingMapper.forEach(System.out::println);
//        var list2 = onr.convertList(drillingMapper);
//		print3DArray(list2);
        OrtSession.Result output = predictService.predict2();
        // 遍历模型的输出
//        for (Map.Entry<String, OnnxValue> entry : output) {
//            // 获取输出节点的名称
//            String outputName = entry.getKey();
//            // 获取输出节点的值
//            OnnxValue value = entry.getValue();
//
//            // 检查值是否为OnnxTensor类型
//            if (value instanceof OnnxTensor) {
//                OnnxTensor tensor = (OnnxTensor) value;
//                // 获取张量的形状
//                long[] shape = tensor.getInfo().getShape();
//                // 打印输出节点的名称和张量的形状
//                System.out.println("Output Name: " + outputName);
//                System.out.println("Output Shape: " + Arrays.toString(shape));
//            }}
        // 获取名为 "output" 的输出张量
        // 获取名为 "output" 的输出值的 Optional 包装
//        Optional<OnnxValue> optionalValue = output.get("output");

// 检查 Optional 是否包含值
//        if (optionalValue.isPresent()) {
//            // 获取实际的 OnnxValue
//            OnnxValue value = optionalValue.get();
//
//            // 检查值是否为 OnnxTensor 类型
//            if (value instanceof OnnxTensor) {
//                OnnxTensor tensor = (OnnxTensor) value;
//                // 现在您可以处理 tensor 了
//                // 例如，打印张量的内容
//                float[][][] tensorData = (float[][][]) tensor.getValue();
//                for (float[][] matrix : tensorData) {
//                    for (float[] row : matrix) {
//                        System.out.println(Arrays.toString(row));
//                    }
//                }
//            }
//        }

// 遍历模型的输出
//        for (Map.Entry<String, OnnxValue> entry : output) {
//            // 获取输出节点的名称
//            String outputName = entry.getKey();
//            // 获取输出节点的值
//            OnnxValue value = entry.getValue();
//
//            // 检查值是否为OnnxTensor类型
//            if (value instanceof OnnxTensor) {
//                OnnxTensor tensor = (OnnxTensor) value;
//                // 获取张量的形状
//                long[] shape = tensor.getInfo().getShape();
//                // 打印输出节点的名称和张量的形状
//                System.out.println("Output Name: " + outputName);
//                System.out.println("Output Shape: " + Arrays.toString(shape));
//
//                // 获取并打印张量的内容
//                Object tensorValue = tensor.getValue();
//                if (tensorValue instanceof float[][][]) {
//                    // 如果是三维浮点数组
//                    float[][][] tensorData = (float[][][]) tensorValue;
//                    for (float[][] matrix : tensorData) {
//                        for (float[] row : matrix) {
//                            System.out.println(Arrays.toString(row));
//                        }
//                    }
//                } else {
//                    // 处理其他可能的数据类型
//                    System.out.println("Tensor data type not supported for printing.");
//                }
//            }
//        }


// 遍历模型的所有输出
        for (Map.Entry<String, OnnxValue> entry : output) {
            // 获取输出节点的名称
            String outputName = entry.getKey();
            // 获取输出节点的值
            OnnxValue value = entry.getValue();

            // 检查值是否为OnnxTensor类型
            if (value instanceof OnnxTensor) {
                OnnxTensor tensor = (OnnxTensor) value;
                // 获取张量的形状
                long[] shape = tensor.getInfo().getShape();
                // 打印输出节点的名称和张量的形状
                System.out.println("Output Name: " + outputName);
                System.out.println("Output Shape: " + Arrays.toString(shape));

                // 获取并打印张量的内容
                Object tensorValue = tensor.getValue();
                if (tensorValue instanceof float[][][]) {
                    // 如果是三维浮点数组
                    float[][][] tensorData = (float[][][]) tensorValue;
                    for (float[][] matrix : tensorData) {
                        for (float[] row : matrix) {
                            System.out.println(Arrays.toString(row));
                        }
                    }
                } else if (tensorValue instanceof float[][]) {
                    // 如果是二维浮点数组
                    float[][] tensorData = (float[][]) tensorValue;
                    for (float[] row : tensorData) {
                        System.out.println(Arrays.toString(row));
                    }
                } else if (tensorValue instanceof float[]) {
                    // 如果是一维浮点数组
                    float[] tensorData = (float[]) tensorValue;
                    System.out.println(Arrays.toString(tensorData));
                } else {
                    // 处理其他可能的数据类型
                    System.out.println("Tensor data type not supported for printing.");
                }
            } else {
                System.out.println("Output value for '" + outputName + "' is not a tensor.");
            }
        }
    }

    @Test
    public void testPredict() {
        try {
            float[][][] outputData = predictService.predict();
            // 确保outputData不为空
            assertNotNull(outputData, "The output data should not be null");

            // 遍历并打印outputData的内容
            for (int i = 0; i < outputData.length; i++) {
                for (int j = 0; j < outputData[i].length; j++) {
                    System.out.println("Output for sequence " + j + ": " + Arrays.toString(outputData[i][j]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("An exception should not have been thrown");
        }
    }
    @Test
    public void testPredict3() {
        try {
            long startTime = System.currentTimeMillis(); // 获取开始时间
            float[][] outputData = predictService.predict3();
            long endTime = System.currentTimeMillis(); // 获取结束时间
            long elapsedTime = endTime - startTime; // 计算耗时
            System.out.println("预测时间：" + elapsedTime + "毫秒");
            // 确保outputData不为空
            assertNotNull(outputData, "The output data should not be null");

            // 遍历并打印outputData的内容
            // 遍历并打印outputData的内容
            for (int i = 0; i < outputData.length; i++) {
                System.out.println("Output for sequence " + i + ": " + Arrays.toString(outputData[i]));
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("An exception should not have been thrown");
        }
    }


}
