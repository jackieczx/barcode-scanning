package com.jck.scan.analyze;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.jck.scan.common.CommonAnalyzer;

import java.util.List;

/**
 * 条码扫描分析器：分析相机预览的帧数据，从中检测识别条形码/二维码
 *
 */
public class BarcodeScanningAnalyzer extends CommonAnalyzer<List<Barcode>> {

    private final BarcodeScanner mDetector;

    public BarcodeScanningAnalyzer() {
        mDetector = BarcodeScanning.getClient();
    }

    public BarcodeScanningAnalyzer(@Barcode.BarcodeFormat int barcodeFormat, @Barcode.BarcodeFormat int... barcodeFormats) {
        this(new BarcodeScannerOptions.Builder()
                .setBarcodeFormats(barcodeFormat, barcodeFormats)
                .build());
    }

    public BarcodeScanningAnalyzer(BarcodeScannerOptions options) {
        if (options != null) {
            mDetector = BarcodeScanning.getClient(options);
        } else {
            mDetector = BarcodeScanning.getClient();
        }
    }

    @NonNull
    @Override
    protected Task<List<Barcode>> detectInImage(@NonNull InputImage inputImage) {
        return mDetector.process(inputImage);
    }

}
