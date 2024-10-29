package com.jck.scan

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Point
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.mlkit.vision.barcode.common.Barcode
import com.jck.scan.base.BarcodeCameraScanActivity
import com.jck.scan.base.MyCameraConfig
import com.jck.scan.ext.drawRect
import com.jck.scan.view.ViewfinderView
import com.king.camera.scan.AnalyzeResult
import com.king.camera.scan.CameraScan

/**
 * 条形码/二维码扫描
 */
class BarcodeScanningActivity : BarcodeCameraScanActivity() {

    private val codeList : MutableList<String> = mutableListOf()

    override fun initCameraScan(cameraScan: CameraScan<MutableList<Barcode>>) {
        super.initCameraScan(cameraScan)

        cameraScan.setCameraConfig(MyCameraConfig(this))
        viewfinderView.setViewfinderStyle(ViewfinderView.ViewfinderStyle.POPULAR)

        cameraScan.setPlayBeep(true)
            .setVibrate(true)
    }

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onScanResultCallback(result: AnalyzeResult<MutableList<Barcode>>) {
        // 停止分析
        cameraScan.setAnalyzeImage(false)

        val pointList : MutableList<Point> = mutableListOf()
//        val mPaint = Paint()
//        mPaint.color = Color.RED // 设置文本颜色
//        mPaint.textSize = 30f // 设置文本大小
//        mPaint.textAlign = Paint.Align.LEFT // 设置文本对齐方式

        val bitmap = result.bitmap?.drawRect { canvas, paint ->
            for ((index, data) in result.result.withIndex()) {
                var displayValue = ""
                if(data.displayValue != null) {
                    displayValue = data.displayValue!!
                }
                data.boundingBox?.let { box ->
//                    canvas.drawRect(box, paint)

                    // 绘画原点
                    val centerPointX = box.left  + (box.right - box.left) / 2
                    val centerPointY = box.top + (box.bottom - box.top) / 2
//                    canvas.drawCircle(centerPointX.toFloat(), centerPointY.toFloat(), 60.0f, paint)

                    pointList.add(Point(centerPointX, centerPointY))

//                    val txt : String = box.left.toString() + " -- " + box.right
//                    canvas.drawText(txt , box.left.toFloat(), box.top.toFloat(), mPaint)
                }

                codeList.add(displayValue)
            }
        }

        viewfinderView.setScanningBitmap(bitmap)
        viewfinderView.setMaskColor(getColor(R.color.viewfinder_mask))
        viewfinderView.showResultPoints(pointList)
        // 结果点Item点击监听
        viewfinderView.setOnItemClickListener { p ->
            val code = codeList[p]
            Toast.makeText(this, code, Toast.LENGTH_SHORT).show()

            val returnIntent = Intent()
            returnIntent.putExtra("code", code)
            setResult(RESULT_OK, returnIntent)
            finish()
        }

        backBtn.visibility = View.GONE
        cancelBtn.visibility = View.VISIBLE
        cancelBtn.setOnClickListener {
            backBtn.visibility = View.VISIBLE
            cancelBtn.visibility = View.GONE
            viewfinderView.showScanner()

            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                cameraScan.setAnalyzeImage(true)
            }, 1000) // 延迟 1000 毫秒
        }
    }

}
