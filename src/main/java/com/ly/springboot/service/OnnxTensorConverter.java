package com.ly.springboot.service;
import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;
import com.ly.mysql.domain.Drilling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class  OnnxTensorConverter {
    @Autowired
    private NormalService normalServices;

    public float[][][] convertList(List<Drilling> drillingList) throws Exception {
        // 假设每个Drilling对象有9个属性，我们需要创建一个1x32x9的Tensor
        int n = drillingList.size();
        float[][][] data = new float[1][n][9];
        for (int i = 0; i < n; i++) {
            Drilling drilling = drillingList.get(i);
            data[0][i][0] = (float) normalServices.normalize("MD",drilling.getMd().floatValue());
            data[0][i][1] = (float) normalServices.normalize("WOB",drilling.getWob().floatValue());
            data[0][i][2] = (float) normalServices.normalize("SP",drilling.getSp().floatValue());
            data[0][i][3] = (float) normalServices.normalize("TQ",drilling.getTq().floatValue());
            data[0][i][4] = (float) normalServices.normalize("RS",drilling.getRs().floatValue());
            data[0][i][5] = (float) normalServices.normalize("MF",drilling.getMf().floatValue());
            data[0][i][6] = (float) normalServices.normalize("HL",drilling.getHl().floatValue());
            data[0][i][7] = (float) normalServices.normalize("TVD",drilling.getTvd().floatValue());
            data[0][i][8] = (float) normalServices.normalize("ROP",drilling.getRop().floatValue());
        }
        return data;
    }
    public float[][][] denormalizeTensor(float[][][] normalizedTensorData) {
        // 假设normalizedTensorData的形状是[1][32][9]
        int batchSize = normalizedTensorData.length;
        int sequenceLength = normalizedTensorData[0].length;
        int featureCount = normalizedTensorData[0][0].length;
        float[][][] outTensorData = new float[batchSize][sequenceLength][featureCount];

        for (int j = 0; j < sequenceLength; j++) {
            outTensorData[0][j][0] = normalServices.denormalize("MD", normalizedTensorData[0][j][0]);
            outTensorData[0][j][1] = normalServices.denormalize("WOB", normalizedTensorData[0][j][1]);
            outTensorData[0][j][2] = normalServices.denormalize("SP", normalizedTensorData[0][j][2]);
            outTensorData[0][j][3] = normalServices.denormalize("TQ", normalizedTensorData[0][j][3]);
            outTensorData[0][j][4] = normalServices.denormalize("RS", normalizedTensorData[0][j][4]);
            outTensorData[0][j][5] = normalServices.denormalize("MF", normalizedTensorData[0][j][5]);
            outTensorData[0][j][6] = normalServices.denormalize("HL", normalizedTensorData[0][j][6]);
            outTensorData[0][j][7] = normalServices.denormalize("TVD", normalizedTensorData[0][j][7]);
            outTensorData[0][j][8] = normalServices.denormalize("ROP", normalizedTensorData[0][j][8]);
        }
        return outTensorData;
    }
    public float[][] denormalizeTensor3(float[][][] normalizedTensorData) {
        // 假设normalizedTensorData的形状是[1][32][9]
        int sequenceLength = normalizedTensorData[0].length;
        int featureCount = normalizedTensorData[0][0].length;
        float[][] outTensorData = new float[sequenceLength][featureCount];

        for (int j = 0; j < sequenceLength; j++) {
            outTensorData[j][0] = normalServices.denormalize("MD", normalizedTensorData[0][j][0]);
            outTensorData[j][1] = normalServices.denormalize("WOB", normalizedTensorData[0][j][1]);
            outTensorData[j][2] = normalServices.denormalize("SP", normalizedTensorData[0][j][2]);
            outTensorData[j][3] = normalServices.denormalize("TQ", normalizedTensorData[0][j][3]);
            outTensorData[j][4] = normalServices.denormalize("RS", normalizedTensorData[0][j][4]);
            outTensorData[j][5] = normalServices.denormalize("MF", normalizedTensorData[0][j][5]);
            outTensorData[j][6] = normalServices.denormalize("HL", normalizedTensorData[0][j][6]);
            outTensorData[j][7] = normalServices.denormalize("TVD", normalizedTensorData[0][j][7]);
            outTensorData[j][8] = normalServices.denormalize("ROP", normalizedTensorData[0][j][8]);
        }
        return outTensorData;
}
}

//    @Autowired
//    public Object convertList;
// 加载ONNX模型
//    String model_path = "src\\main\\resources\\iInformer.onnx";
//
//    OrtEnvironment environment = OrtEnvironment.getEnvironment();
//    OrtSession.SessionOptions sessionOptions = new OrtSession.SessionOptions();
//    OrtSession session = environment.createSession(model_path, sessionOptions);
//
//
//    tensor = OnnxTensor.createTensor(environment, inputBuffer, new long[]{1, 3, 640, 640});
//
//    HashMap<String, OnnxTensor> stringOnnxTensorHashMap = new HashMap<>();
//                stringOnnxTensorHashMap.put(session.getInputInfo().keySet().iterator().next(), tensor);
//    OrtSession.Result output = session.run(stringOnnxTensorHashMap);



//    public OnnxTensor convertListToTensor(List<Drilling> drillingList, OrtEnvironment env) throws Exception {
//        // 假设每个Drilling对象有9个属性，我们需要创建一个1x32x9的Tensor
//        float[][][] data = new float[1][32][9];
//        for (int i = 0; i < drillingList.size(); i++) {
//            Drilling drilling = drillingList.get(i);
////            Normalizer2 normalService;
//            data[0][i][0] = (float) normalServices.normalize("MD",drilling.getMd().floatValue());
//            data[0][i][1] = (float) normalServices.normalize("WOB", drilling.getWob().floatValue());
//            data[0][i][2] = (float) normalServices.normalize("SP",drilling.getSp().floatValue());
//            data[0][i][3] = (float) normalServices.normalize("TQ", drilling.getTq().floatValue());
//            data[0][i][4] = (float) normalServices.normalize("RS",drilling.getRs().floatValue());
//            data[0][i][5] = (float) normalServices.normalize("MF",drilling.getMf().floatValue());
//            data[0][i][6] = (float) normalServices.normalize("HL",drilling.getHl().floatValue());
//            data[0][i][7] = (float) normalServices.normalize("TVD",drilling.getTvd().floatValue());
//            data[0][i][8] = (float) normalServices.normalize("ROP",drilling.getRop().floatValue());
//        }
//        // 创建Tensor
//        return OnnxTensor.createTensor(env, data);
//    }




