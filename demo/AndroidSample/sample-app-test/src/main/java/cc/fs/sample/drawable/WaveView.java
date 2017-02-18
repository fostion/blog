package cc.fs.sample.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import cc.fs.sample.utils.L;

/**
 * 波浪形view
 */
public class WaveView extends View {

    private final String TAG = "WaveView";

    //踩点数
    int samplePointSize = 60;
    //样点均匀映射在[-2,2]的x上
    float pointX[];


    Paint paint, linePaint;
    LinearGradient linearGradient1;
    int pointNum = 60;
    int allWidth = 300;
    Path path = new Path();

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        paint = new Paint();
        linePaint = new Paint();
        linearGradient1 = new LinearGradient(0, 0, 200, 0, Color.RED, Color.BLUE, Shader.TileMode.CLAMP);

        path.moveTo(50, 300);
        float tempWidth = allWidth / pointNum * 1.0f;
        for (int i = 0; i < pointNum; i++) {
            float x = i * tempWidth;
            double y = calFun(0.5,x,-0.5);
            L.e(TAG, " x:" + x + " - y:" + y);
        }

        //曲线笔画
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(Color.BLUE);
        linePaint.setStrokeWidth(5);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setShader(linearGradient1);
//        canvas.drawRect(0,20,600,220,paint);
        canvas.drawPath(path, linePaint);

    }

    //计算线条
    public double calFun(double maxY, double x, double offset) {
        double sinFun = Math.sin(0.75 * Math.PI * x - offset * Math.PI);
        double minFun = Math.pow(4 / (4 + Math.pow(x, 4)), 2.5);
        return maxY * sinFun * minFun;
    }
}


























