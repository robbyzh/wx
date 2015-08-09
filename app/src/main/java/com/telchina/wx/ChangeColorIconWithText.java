package com.telchina.wx;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * 自定义view类
 */
public class ChangeColorIconWithText extends View {

    private int         mColor = 0xffe;
    private Bitmap      mIconBitMap;
    private String      mText;
    private int         mTextSize = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());

    private Canvas      mCanvas;
    private Bitmap      mBitmap;
    private Paint       mPaint;

    private float       mAlpha;

    private Rect        mIconRect;
    private Rect        mTextBound;

    private Paint       mTextPaint;

    private static final String INSTANCE_STATUS = "instance_status";
    private static final String STATUS_ALPHA = "status_alhpa";


    public ChangeColorIconWithText(Context context) {
        this(context, null);
    }

    public ChangeColorIconWithText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 获取自定义参数
     */
    public ChangeColorIconWithText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ChangeColorIconWithText);

        int n = a.getIndexCount();

        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);

            switch (attr) {
                case R.styleable.ChangeColorIconWithText_c_text:
                    mText = a.getString(attr);
                    break;

                case R.styleable.ChangeColorIconWithText_c_color:
                    mColor = a.getColor(attr, 0xffe);
                    break;

                case R.styleable.ChangeColorIconWithText_c_icon:
                    BitmapDrawable drawable = (BitmapDrawable) a.getDrawable(attr);
                    mIconBitMap = drawable.getBitmap();
                    break;
                case R.styleable.ChangeColorIconWithText_c_text_size:
                    mTextSize = (int) a.getDimension(attr, TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    break;
            }
        }

        a.recycle();

        mTextBound = new Rect();
        mTextPaint = new Paint();
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(0xffe);
        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBound);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int iconWidth = Math.min(getMeasuredWidth() - getPaddingRight() -
                getPaddingLeft(), getMeasuredHeight() - getPaddingBottom() -
                getPaddingTop() - mTextBound.height());

        int left = getMeasuredWidth() / 2 - iconWidth / 2;
        int top = (getMeasuredHeight() - mTextBound.height()) / 2 - iconWidth / 2;

        mIconRect = new Rect(left, top, left + iconWidth, top + iconWidth);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mIconBitMap, null, mIconRect, null);

        int alpha = (int) Math.ceil(255 * mAlpha);

        setupTargetBitmap(alpha);

        drawSourceText(canvas, alpha);

        drawTargetText(canvas, alpha);

        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    //绘制移动tab时的目标文本
    private void drawTargetText(Canvas canvas, int alpah) {

        mTextPaint.setColor(mColor);
        mTextPaint.setAlpha(alpah);

        int x = getMeasuredWidth() / 2 - mTextBound.width() / 2;
        int y = mIconRect.bottom + mTextBound.height();

        canvas.drawText(mText, x, y, mTextPaint);
    }

    //绘制移动时的源文本
    private void drawSourceText(Canvas canvas, int alpah) {

        mTextPaint.setColor(Color.GRAY);
        //mTextPaint.setAlpha(255 - alpah);

        int x = getMeasuredWidth() / 2 - mTextBound.width() / 2;
        int y = mIconRect.bottom + mTextBound.height();

        canvas.drawText(mText, x, y, mTextPaint);
    }

    /**
     * 绘制目标图像
     * @param alpha
     */
    private void setupTargetBitmap(int alpha) {

        mBitmap = Bitmap.createBitmap(getMeasuredWidth(),
                getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        mCanvas = new Canvas(mBitmap);
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setAlpha(alpha);

        mCanvas.drawRect(mIconRect, mPaint);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setAlpha(255);
        mCanvas.drawBitmap(mIconBitMap, null, mIconRect, mPaint);
    }

    /**
     * 外部接口，用于在滑动tab的时候设置view的透明度，来实现图像和问题变色
     * @param alpha
     */
    public void setIconAlpha(float alpha) {

        this.mAlpha = alpha;
        invalidateView();
    }

    /**
     * 重绘方法
     */
    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }

        invalidate();
    }


    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATUS, super.onSaveInstanceState());
        bundle.putFloat(STATUS_ALPHA, mAlpha);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mAlpha = bundle.getFloat(STATUS_ALPHA);
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATUS));
        } else {
            super.onRestoreInstanceState(state);
        }
    }
}
