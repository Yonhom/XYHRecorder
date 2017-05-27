package com.xuyonghong.xyhrecorder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xuyonghong.xyhrecorder.util.CommonUtils;

import java.util.Timer;

/**
 * Created by xuyonghong on 2017/5/27.
 */

public class TimerRing extends View {
    private Paint paint;

    private Paint textPaint;

    private int radius;

    private Timer timer = new Timer();

    private int secondCount;

    private int strokeWidth;

    public void setTotolSeconds(int seconds) {
        secondCount = seconds;
        invalidate();
    }

    public TimerRing(Context context) {
        super(context);
        init();
    }

    public TimerRing(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TimerRing(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TimerRing(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // set the paint default color
        paint.setColor(Color.RED);
        // set the ring width
        paint.setStyle(Paint.Style.STROKE);
        strokeWidth = (int) CommonUtils.dp2px(getContext(), 2);
        paint.setStrokeWidth(strokeWidth);

        radius = (int) CommonUtils.dp2px(getContext(), 100);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(180);
        textPaint.setColor(Color.BLACK);
    }

    public void setRingColor(int color) {
        paint.setColor(color);
    }

    public void setTextColor(int color) {
        textPaint.setColor(color);
    }

    /**
     * convert a mount of seconds to a string format: 00:00:00
     * @param second
     * @return
     */
    public String secondCountToString(int second) {
        String hourStr;
        String minuteStr;
        String secondStr;

        int hours;
        int minutes;
        int seconds;

        hours = second / 3600;
        minutes = (second - hours * 3600) / 60;
        seconds = second - hours * 3600 - minutes * 60;

        if (minutes < 10) {
            minuteStr = "0" + minutes;
        } else minuteStr = "" + minutes;

        if (second < 10) {
            secondStr = "0" + seconds;
        } else secondStr = "" + seconds;

        if (hours == 0) {
            return minuteStr + ":" + secondStr;
        } else {
            if (hours < 10) {
                hourStr = "0" + hours;
            } else hourStr = "" + hours;

            return hourStr + ":" + minuteStr + ":" + secondStr;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float centerX = getWidth() / 2f;
        float centerY = getHeight() / 2f;
        canvas.drawCircle(centerX, centerY, radius - strokeWidth, paint);

        canvas.drawText(secondCountToString(secondCount), radius, radius + 50, textPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(radius * 2, radius * 2);
    }
}
