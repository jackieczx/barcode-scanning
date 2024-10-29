package com.jck.scan.base;

import android.view.View;

import androidx.annotation.Nullable;

import com.google.mlkit.vision.barcode.common.Barcode;
import com.jck.scan.R;
import com.jck.scan.analyze.BarcodeScanningAnalyzer;
import com.jck.scan.view.ViewfinderView;
import com.king.camera.scan.BaseCameraScanFragment;
import com.king.camera.scan.analyze.Analyzer;

import java.util.List;

/**
 * 条形码扫描 - 相机扫描基类
 * 通过继承 BarcodeCameraScanFragment 可快速实现条形码（二维码是条形码的子集）
 *
 */
public abstract class BarcodeCameraScanFragment extends BaseCameraScanFragment<List<Barcode>> {

    protected ViewfinderView viewfinderView;

    @Override
    public void initUI() {
        int viewfinderViewId = getViewfinderViewId();
        if (viewfinderViewId != View.NO_ID && viewfinderViewId != 0) {
            viewfinderView = getRootView().findViewById(viewfinderViewId);
        }
        super.initUI();
    }

    /**
     * 创建分析器，默认分析所有条码格式
     *
     * @return {@link Analyzer}
     */
    @Nullable
    @Override
    public Analyzer<List<Barcode>> createAnalyzer() {
        return new BarcodeScanningAnalyzer(Barcode.FORMAT_ALL_FORMATS);
    }

    /**
     * 布局ID；通过覆写此方法可以自定义布局
     *
     * @return 布局ID
     */
    @Override
    public int getLayoutId() {
        return R.layout.ml_barcode_camera_scan;
    }

    /**
     * {@link #viewfinderView} 的 ID
     *
     * @return 默认返回{@code R.id.viewfinderView}, 如果不需要扫码框可以返回{@link View#NO_ID}
     */

    public int getViewfinderViewId() {
        return R.id.viewfinderView;
    }

}
