package com.telchina.wx;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
        super(context, attrs, defStyleAttr );

        setBackgroundColor(Color.LTGRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint bluePaint=new Paint();
        bluePaint.setColor(Color.BLUE);
        bluePaint.setStyle(Paint.Style.STROKE);
        bluePaint.setStrokeWidth(15);
        canvas.drawCircle(110, 150, 60, bluePaint);

        Paint yellowPaint=new Paint();
        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setStyle(Paint.Style.STROKE);
        yellowPaint.setStrokeWidth(15);
        canvas.drawCircle((float) 175.5, 210, 60, yellowPaint);

        Paint blackPaint=new Paint();
        blackPaint.setColor(Color.BLACK);
        blackPaint.setStyle(Paint.Style.STROKE);
        blackPaint.setStrokeWidth(15);
        canvas.drawCircle(245, 150, 60, blackPaint);

        Paint greenPaint=new Paint();
        greenPaint.setColor(Color.GREEN);
        greenPaint.setStyle(Paint.Style.STROKE);
        greenPaint.setStrokeWidth(15);
        canvas.drawCircle(311, 210, 60, greenPaint);

        Paint redPaint=new Paint();
        redPaint.setColor(Color.RED);
        redPaint.setStyle(Paint.Style.STROKE);
        redPaint.setStrokeWidth(15);
        canvas.drawCircle(380, 150, 60, redPaint);


        Paint linePaint=new Paint();
        linePaint.setColor(Color.BLUE);
        canvas.drawLine(240, 310, 425, 310, linePaint);

        Paint textPaint=new Paint();
        textPaint.setColor(Color.BLUE);
        linePaint.setTextSize(30);
        linePaint.setAlpha(255);
        canvas.drawText("北京欢迎您", 275, 330, linePaint);

        //canvas.drawBitmap(Bitmap);

    }
}
