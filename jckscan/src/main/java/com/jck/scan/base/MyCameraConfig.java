package com.jck.scan.base;

import android.content.Context;
import android.graphics.Point;
import android.util.Size;
import android.view.Display;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;

import com.king.camera.scan.config.CameraConfig;

public class MyCameraConfig extends CameraConfig {

    /**
     * 目标尺寸
     */
    private Size mTargetSize;

    /**
     * 构造
     *
     * @param context 上下文
     */
    public MyCameraConfig(Context context) {
        super();
        initTargetResolutionSize(context);
    }

    /**
     * 初始化 {@link #mTargetSize}
     *
     * @param context      上下文
     */
    private void initTargetResolutionSize(Context context) {
        // 屏幕实际分辨路
        Display display = ((AppCompatActivity)context).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getRealSize(size);
        int width = size.x; // 屏幕宽度
        int height = size.y; // 屏幕高度

        // 1080:2400 9:20
        // 1080:1920 9:16
        // 1080:2160 9:18
        // 720:1600 9:20

        int x = width / 9;
//        Log.d("x======", "width / 9:" + x);

        if(height / x == 20) {
            // 屏幕比例 9:20
            mTargetSize = new Size(width, height - (4 * x));
        }else if(height / x == 18) {
            // 屏幕比例 9:18
            mTargetSize = new Size(width, height - (2 * x));
        }else {
            mTargetSize = new Size(width, height - (2 * x));
        }
    }

    @NonNull
    @Override
    public CameraSelector options(@NonNull CameraSelector.Builder builder) {
        return super.options(builder);
    }

    @NonNull
    @Override
    public Preview options(@NonNull Preview.Builder builder) {
        return super.options(builder);
    }

    @NonNull
    @Override
    public ImageAnalysis options(@NonNull ImageAnalysis.Builder builder) {
        builder.setTargetResolution(mTargetSize);
        return super.options(builder);
    }

}
