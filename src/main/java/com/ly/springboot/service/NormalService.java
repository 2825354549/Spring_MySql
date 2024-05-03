package com.ly.springboot.service;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Data
//@Component
@PropertySource("classpath:data.properties")
public class NormalService{
    @Value("${normalizations.MD.std}")
    private float mdStd;

    @Value("${normalizations.MD.mean}")
    private  float mdMean;

    @Value("${normalizations.WOB.std}")
    private  float wobStd;

    @Value("${normalizations.WOB.mean}")
    private  float wobMean;

    @Value("${normalizations.ROP.std}")
    private  float ropStd;

    @Value("${normalizations.ROP.mean}")
    private double ropMean;

    @Value("${normalizations.SP.std}")
    private  float spStd;

    @Value("${normalizations.SP.mean}")
    private  float spMean;

    @Value("${normalizations.TQ.std}")
    private  float tqStd;

    @Value("${normalizations.TQ.mean}")
    private  float tqMean;

    @Value("${normalizations.RS.std}")
    private double rsStd;

    @Value("${normalizations.RS.mean}")
    private  float rsMean;

    @Value("${normalizations.MF.std}")
    private  float mfStd;

    @Value("${normalizations.MF.mean}")
    private  float mfMean;

    @Value("${normalizations.HL.std}")
    private  float hlStd;

    @Value("${normalizations.HL.mean}")
    private  float hlMean;

    @Value("${normalizations.TVD.std}")
    private  float tvdStd;

    @Value("${normalizations.TVD.mean}")
    private  float tvdMean;

    public double normalize(String field, float value) {
        switch (field) {
            case "MD":
                return (value - mdMean) / mdStd;
            case "WOB":
                return (value - wobMean) / wobStd;
            case "SP":
                return (value - spMean) / spStd;
            case "TQ":
                return (value - tqMean) / tqStd;
            case "RS":
                return (value - rsMean) / rsStd;
            case "MF":
                return (value - mfMean) / mfStd;
            case "HL":
                return (value - hlMean) / hlStd;
            case "TVD":
                return (value - tvdMean) / tvdStd;
            case "ROP":
                return (value - ropMean) / ropStd;
            default:
                throw new IllegalArgumentException("Invalid field: " + field);
        }
    }
    public float denormalize(String field, double normalizedValue) {
        switch (field) {
            case "MD":
                return (float) (normalizedValue * mdStd + mdMean);
            case "WOB":
                return (float) (normalizedValue * wobStd + wobMean);
            case "SP":
                return (float) (normalizedValue * spStd + spMean);
            case "TQ":
                return (float) (normalizedValue * tqStd + tqMean);
            case "RS":
                return (float) (normalizedValue * rsStd + rsMean);
            case "MF":
                return (float) (normalizedValue * mfStd + mfMean);
            case "HL":
                return (float) (normalizedValue * hlStd + hlMean);
            case "TVD":
                return (float) (normalizedValue * tvdStd + tvdMean);
            case "ROP":
                return (float) (normalizedValue * ropStd + ropMean);
            default:
                throw new IllegalArgumentException("Invalid field: " + field);
        }
    }






}

