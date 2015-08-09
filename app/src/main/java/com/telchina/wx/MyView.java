package com.telchina.wx;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;


/**
 * 自定义view在这里绘制图形
 */
public class MyView extends View {


    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint bluePaint = new Paint();
        bluePaint.setColor(Color.BLUE);
        bluePaint.setStyle(Paint.Style.STROKE);
        bluePaint.setStrokeWidth(15);
        canvas.drawCircle(110, 150, 60, bluePaint);

        Paint yellowPaint = new Paint();
        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setStyle(Paint.Style.STROKE);
        yellowPaint.setStrokeWidth(15);
        canvas.drawCircle((float) 175.5, 210, 60, yellowPaint);

        Paint blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        blackPaint.setStyle(Paint.Style.STROKE);
        blackPaint.setStrokeWidth(15);
        canvas.drawCircle(245, 150, 60, blackPaint);

        Paint greenPaint = new Paint();
        greenPaint.setColor(Color.GREEN);
        greenPaint.setStyle(Paint.Style.STROKE);
        greenPaint.setStrokeWidth(15);
        canvas.drawCircle(311, 210, 60, greenPaint);

        Paint redPaint = new Paint();
        redPaint.setColor(Color.RED);
        redPaint.setStyle(Paint.Style.STROKE);
        redPaint.setStrokeWidth(15);
        canvas.drawCircle(380, 150, 60, redPaint);


        Paint linePaint = new Paint();
        linePaint.setColor(Color.BLUE);
        canvas.drawLine(240, 310, 425, 310, linePaint);

        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLUE);
        linePaint.setTextSize(30);
        linePaint.setAlpha(255);
        canvas.drawText("北京欢迎您", 275, 330, linePaint);

        //canvas.drawBitmap(Bitmap);


        Paint pa = new Paint();
        pa.setColor(Color.BLACK);

        pa.setStyle(Paint.Style.STROKE);
        Path path1 = new Path();
        path1.moveTo(180, 200);
        path1.lineTo(200, 200);
        path1.lineTo(210, 210);
        path1.lineTo(200, 220);
        path1.lineTo(180, 220);
        path1.lineTo(170, 210);
        path1.close();

        canvas.drawPath(path1, pa);

        canvas.drawText("画贝塞尔曲线:", 10, 310, pa);
        pa.reset();
        pa.setStyle(Paint.Style.STROKE);
        pa.setColor(Color.GREEN);
        pa.setStrokeWidth(8);
        Path path2 = new Path();
        path2.moveTo(100, 320);//设置Path的起点
        path2.quadTo(150, 310, 170, 400); //设置贝塞尔曲线的控制点坐标和终点坐标
        canvas.drawPath(path2, pa);
    }
}
