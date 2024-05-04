package com.ly.springboot.service;

import ai.onnxruntime.*;
import com.ly.mysql.domain.Drilling;
import com.ly.mysql.mapper.DrillingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
//@PropertySource("classpath:application.yaml")
public class PredictService{
//    private final DrillingMapper drillingMapper;
    @Value("${model.path}")
    private String modelPath;


    @Autowired
    private DrillingMapper drillingMapper;
    @Autowired
    private OnnxTensorConverter onnxTensorConverter;

    public OrtSession.Result predict2() throws Exception {
        // 从数据库中获取钻井数据列表
        List<Drilling> drillingList = drillingMapper.selectList();
        drillingList.forEach(System.out::println);
        // 使用OnnxTensorConverter将钻井数据列表转换为Tensor数据

        float[][][] tensorData = onnxTensorConverter.convertList(drillingList);
        // ... 在这里添加使用tensorData进行预测的代码 ...
//        List<Drilling> drillingList =drillingMapper.selectList();
//        list = onnxTensorConverter.convertList(drillingList)

        String model_path = "src\\main\\resources\\iInformer.onnx";
//        int[] inputDims = new int[]{1,32,9};

        OrtEnvironment environment = OrtEnvironment.getEnvironment();
        OrtSession.SessionOptions sessionOptions = new OrtSession.SessionOptions();
        OrtSession session = environment.createSession(model_path, sessionOptions);
        OnnxTensor inputTensor= OnnxTensor.createTensor(environment,tensorData);


//        OnnxTensor tensor = OnnxTensor.createTensor(environment,tensorData);
//
//        HashMap<String, OnnxTensor> stringOnnxTensorHashMap = new HashMap<>();
//        stringOnnxTensorHashMap.put(session.getInputInfo().keySet().iterator().next(),input);
//        OnnxTensor[] output = session.run(input);
        // 创建一个HashMap来存储模型的输入
        HashMap<String, OnnxTensor> inputs = new HashMap<>();
        String inputName = session.getInputInfo().keySet().iterator().next(); // 获取模型的输入节点名称
        inputs.put(inputName, inputTensor);
        // 运行模型
        OrtSession.Result output = session.run(inputs);

        return output;
    }
    public float[][][] predict4() throws Exception {
        // 从数据库中获取钻井数据列表
        List<Drilling> drillingList = drillingMapper.selectList();
        drillingList.forEach(System.out::println);
        // 使用OnnxTensorConverter将钻井数据列表转换为Tensor数据

        float[][][] tensorData = onnxTensorConverter.convertList(drillingList);
        // ... 在这里添加使用tensorData进行预测的代码 ...
//        List<Drilling> drillingList =drillingMapper.selectList();
//        list = onnxTensorConverter.convertList(drillingList)

        String model_path = "src\\main\\resources\\iInformer.onnx";
//        int[] inputDims = new int[]{1,32,9};

        OrtEnvironment environment = OrtEnvironment.getEnvironment();
        OrtSession.SessionOptions sessionOptions = new OrtSession.SessionOptions();
        OrtSession session = environment.createSession(model_path, sessionOptions);
        OnnxTensor inputTensor = OnnxTensor.createTensor(environment, tensorData);


//        OnnxTensor tensor = OnnxTensor.createTensor(environment,tensorData);
//
//        HashMap<String, OnnxTensor> stringOnnxTensorHashMap = new HashMap<>();
//        stringOnnxTensorHashMap.put(session.getInputInfo().keySet().iterator().next(),input);
//        OnnxTensor[] output = session.run(input);
        // 创建一个HashMap来存储模型的输入
        HashMap<String, OnnxTensor> inputs = new HashMap<>();
        String inputName = session.getInputInfo().keySet().iterator().next(); // 获取模型的输入节点名称
        inputs.put(inputName, inputTensor);
        // 运行模型
        OrtSession.Result output = session.run(inputs);

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
                    float[][][] predTensorData = (float[][][]) tensorValue;
                    float[][][] outputData = onnxTensorConverter.denormalizeTensor(predTensorData);

                    return outputData;
                }

                else {
                    System.out.println("Output value for '" + outputName + "' is not a tensor.");
                    return null;
                }
            }

        }
        return null;

    }

    public float[][][] predict() throws Exception {
        List<Drilling> drillingList = drillingMapper.selectList();
        // 假设您已经在其他地方打印了drillingList，这里就不再重复打印

        float[][][] tensorData = onnxTensorConverter.convertList(drillingList);

        String modelPath = "src/main/resources/iInformer.onnx";
        try (OrtEnvironment environment = OrtEnvironment.getEnvironment();
             OrtSession.SessionOptions sessionOptions = new OrtSession.SessionOptions();
             OrtSession session = environment.createSession(modelPath, sessionOptions)) {

            OnnxTensor inputTensor = OnnxTensor.createTensor(environment, tensorData);
            HashMap<String, OnnxTensor> inputs = new HashMap<>();
            String inputName = session.getInputInfo().keySet().iterator().next();
            inputs.put(inputName, inputTensor);

            try (OrtSession.Result output = session.run(inputs)) {
                return processOutput(output);
            }
        }
    }

    private float[][][] processOutput(OrtSession.Result output) throws OrtException {
        Optional<OnnxValue> optionalValue = output.get("output");
        if (!optionalValue.isPresent()) {
            throw new IllegalStateException("Output not found for 'output'");
        }

        OnnxValue value = optionalValue.get();
        if (!(value instanceof OnnxTensor)) {
            throw new IllegalStateException("Output value for 'output' is not a tensor");
        }

        OnnxTensor tensor = (OnnxTensor) value;
        Object tensorValue = tensor.getValue();
        if (!(tensorValue instanceof float[][][])) {
            throw new IllegalStateException("Tensor data type is not supported for printing");
        }

        float[][][] predTensorData = (float[][][]) tensorValue;
        return onnxTensorConverter.denormalizeTensor(predTensorData);
    }
    public float[][] predict3() throws Exception {
        List<Drilling> drillingList = drillingMapper.selectList();
//        drillingMapper.forEach(System.out::println);

        float[][][] tensorData = onnxTensorConverter.convertList(drillingList);
//        System.out.println("modelPath:" + modelPath);

//        String modelPath = "src/main/resources/iInformer.onnx";
        //创建onnxruntime实例  创建配置类 （加入GPU） 创建会话实例
        try (OrtEnvironment environment = OrtEnvironment.getEnvironment();
             OrtSession.SessionOptions sessionOptions = new OrtSession.SessionOptions();
             OrtSession session = environment.createSession(modelPath, sessionOptions)) {

            OnnxTensor inputTensor = OnnxTensor.createTensor(environment, tensorData);
            HashMap<String, OnnxTensor> inputs = new HashMap<>();
            //模型输入键集合 第一个
            String inputName = session.getInputInfo().keySet().iterator().next();
            inputs.put(inputName, inputTensor);

//            try (OrtSession.Result output = session.run(inputs)) {
//                return processOutput3(output);
//            }
            long startTime = System.currentTimeMillis(); // 获取开始时间

            OrtSession.Result output = session.run(inputs);
            long endTime = System.currentTimeMillis(); // 获取结束时间
            long duration = endTime - startTime; // 计算运行时间

            System.out.println("运行时间：" + duration + "毫秒");
            return processOutput3(output);
            // 获取结束时间
//            long endTime = System.currentTimeMillis();
//            long duration =System.currentTimeMillis() - startTime; // 计算运行时间
//            System.out.println("运行时间：" + duration + "毫秒");
        }
    }

    private float[][] processOutput3(OrtSession.Result output) throws OrtException {
        Optional<OnnxValue> optionalValue = output.get("output");
        if (!optionalValue.isPresent()) {
            throw new IllegalStateException("Output not found for 'output'");
        }

        OnnxValue value = optionalValue.get();
        if (!(value instanceof OnnxTensor)) {
            throw new IllegalStateException("Output value for 'output' is not a tensor");
        }

        OnnxTensor tensor = (OnnxTensor) value;
        Object tensorValue = tensor.getValue();
        if (!(tensorValue instanceof float[][][])) {
            throw new IllegalStateException("Tensor data type is not supported for printing");
        }

        float[][][] predTensorData = (float[][][]) tensorValue;
        return onnxTensorConverter.denormalizeTensor3(predTensorData);
    }



    public List<Drilling> getList() {
        return drillingMapper.selectList();
    }
}
//                    for (float[][] matrix : outputData) {
//                        for (float[] row : matrix) {
//                            System.out.println(Arrays.toString(row));
//                        }
//                    }


//                } else if (tensorValue instanceof float[][]) {
//                    // 如果是二维浮点数组
//                    float[][] predTensorData = (float[][]) tensorValue;
//                    for (float[] row : predTensorData) {
//                        System.out.println(Arrays.toString(row));
//                    }
//                } else if (tensorValue instanceof float[]) {
//                    // 如果是一维浮点数组
//                    float[] predTensorData = (float[]) tensorValue;
//                    System.out.println(Arrays.toString(tensorData));
//                }
//                else {
//                    // 处理其他可能的数据类型
//                    System.out.println("Tensor data type not supported for printing.");
//                }




// 遍历模型的输出
//        for (Map.Entry<String, OnnxValue> entry : output) {
//            // 获取输出节点的名称
//            String outputName = entry.getKey();
//            // 获取输出节点的值
//            OnnxValue value = entry.getValue();
//            // 检查值是否为OnnxTensor类型
//            if (value instanceof OnnxTensor tensorOnnx) {
//                // 获取张量的形状
//                long[] shape = tensorOnnx.getInfo().getShape();
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
//            // 检查值是否为 OnnxTensor 类型
//            if (value instanceof OnnxTensor) {
//                OnnxTensor tensor = (OnnxTensor) value;
//                // 现在您可以处理 tensor 了
//                // 例如，打印张量的内容
//                float[][][] outTensorData = (float[][][]) tensor.getValue();
//                for (float[][] matrix :outTensorData) {
//                    for (float[] row : matrix) {
//                        System.out.println(Arrays.toString(row));
//                    }
//                }
//            }
//        }
//    }




