package com.jck.scan.base;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.mlkit.vision.barcode.common.Barcode;
import com.jck.scan.R;
import com.jck.scan.analyze.BarcodeScanningAnalyzer;
import com.jck.scan.view.ViewfinderView;
import com.king.camera.scan.BaseCameraScanActivity;
import com.king.camera.scan.analyze.Analyzer;

import java.util.List;

/**
 * 条形码/二维码扫描 - 相机扫描基类
 * 通过继承 BarcodeCameraScanActivity 可快速实现条形码/二维码扫描
 *
 */
public abstract class BarcodeCameraScanActivity extends BaseCameraScanActivity<List<Barcode>> {

    protected ViewfinderView viewfinderView;

    protected TextView cancelBtn;
    protected ImageView backBtn;

    @Override
    public void initUI() {
//        getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // 隐藏导航栏
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN // 隐藏状态栏
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        int viewfinderViewId = getViewfinderViewId();
        if (viewfinderViewId != View.NO_ID && viewfinderViewId != 0) {
            viewfinderView = findViewById(viewfinderViewId);
        }
        cancelBtn = findViewById(R.id.cancelBtn);
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> {
            finish();
        });
        super.initUI();
    }

    /**
     * 创建分析器，默认分析所有条码格式
     *
     */
    @Nullable
    @Override
    public Analyzer<List<Barcode>> createAnalyzer() {
        return new BarcodeScanningAnalyzer(Barcode.FORMAT_ALL_FORMATS);
    }

    /**
     * 布局ID；通过覆写此方法可以自定义布局
     *
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
