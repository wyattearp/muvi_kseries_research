// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class NumricalSeekBar extends SeekBar
{

    private static final String TAG = "NumricalSeekBar";
    private Context mContext;
    private Paint mPaint;
    private String numStr;
    private int txtColor;

    public NumricalSeekBar(Context context)
    {
        super(context);
        mContext = null;
        numStr = null;
        txtColor = 0;
        mPaint = null;
        mContext = context;
        init();
    }

    public NumricalSeekBar(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mContext = null;
        numStr = null;
        txtColor = 0;
        mPaint = null;
        mContext = context;
        init();
    }

    public NumricalSeekBar(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mContext = null;
        numStr = null;
        txtColor = 0;
        mPaint = null;
        mContext = context;
        init();
    }

    private void init()
    {
        numStr = new String("0");
        txtColor = -1;
        mPaint = new Paint();
        mPaint.setTextAlign(android.graphics.Paint.Align.CENTER);
        mPaint.setTextSize(mContext.getResources().getDimension(0x7f0800d8));
        mPaint.setColor(txtColor);
    }

    protected void onDraw(Canvas canvas)
    {
        this;
        JVM INSTR monitorenter ;
        super.onDraw(canvas);
        canvas.save();
        numStr = String.valueOf(getProgress());
        float f = mPaint.measureText(numStr);
        float f1 = (float)(getWidth() * getProgress()) / (float)getMax();
        float f2 = mContext.getResources().getDimension(0x7f0800de);
        float f3 = f1 - f / 2.0F;
        if (f3 < (float)getPaddingLeft())
        {
            f3 = getPaddingLeft();
        }
        float f4 = mContext.getResources().getDimension(0x7f0800e0);
        if (f1 - f > (float)getWidth() - f4 / 2.0F)
        {
            f3 = (f1 - f) + f4 / 2.0F;
        }
        canvas.drawText(numStr, f3, f2, mPaint);
        canvas.restore();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setProgress(int i)
    {
        this;
        JVM INSTR monitorenter ;
        numStr = String.valueOf(i);
        super.setProgress(i);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }
}
