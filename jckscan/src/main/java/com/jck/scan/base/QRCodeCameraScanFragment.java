package com.jck.scan.base;

import androidx.annotation.Nullable;

import com.google.mlkit.vision.barcode.common.Barcode;
import com.jck.scan.analyze.BarcodeScanningAnalyzer;
import com.king.camera.scan.analyze.Analyzer;

import java.util.List;

/**
 * 二维码扫描 - 相机扫描基类
 * 通过继承 BarcodeCameraScanActivity 或 BarcodeCameraScanFragment 可快速实现二维码扫描
 *
 */
public abstract class QRCodeCameraScanFragment extends BarcodeCameraScanFragment {

    /**
     * 创建分析器，默认分析所有条码格式
     *
     */
    @Nullable
    @Override
    public Analyzer<List<Barcode>> createAnalyzer() {
        return new BarcodeScanningAnalyzer(Barcode.FORMAT_QR_CODE);
    }

}
