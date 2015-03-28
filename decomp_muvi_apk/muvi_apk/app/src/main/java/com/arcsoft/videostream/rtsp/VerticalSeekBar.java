// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.rtsp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.SeekBar;

public class VerticalSeekBar extends SeekBar
{
    public static interface OnSeekBarChangeListenerVer
    {

        public abstract void onProgressChanged(VerticalSeekBar verticalseekbar, int i, boolean flag);

        public abstract void onStartTrackingTouch(VerticalSeekBar verticalseekbar);

        public abstract void onStopTrackingTouch(VerticalSeekBar verticalseekbar);
    }


    private int height;
    private OnSeekBarChangeListenerVer mOnSeekBarChangeListener;
    private Drawable mThumb;
    private int width;

    public VerticalSeekBar(Context context)
    {
        this(context, null);
    }

    public VerticalSeekBar(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101007b);
    }

    public VerticalSeekBar(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    private void attemptClaimDrag()
    {
        if (getParent() != null)
        {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }

    private void setThumbPos(int i, Drawable drawable, float f, int j)
    {
        int k = (i + getPaddingLeft()) - getPaddingRight();
        int l = drawable.getIntrinsicWidth();
        int i1 = drawable.getIntrinsicHeight();
        int j1 = (int)(f * (float)((k - l) + 2 * getThumbOffset()));
        int k1;
        int l1;
        if (j == 0x80000000)
        {
            Rect rect = drawable.getBounds();
            k1 = rect.top;
            l1 = rect.bottom;
        } else
        {
            k1 = j;
            l1 = j + i1;
        }
        drawable.setBounds(j1, k1, j1 + l, l1);
    }

    private void trackTouchEvent(MotionEvent motionevent)
    {
        int i = getHeight();
        int j = i - getPaddingBottom() - getPaddingTop();
        int k = (int)((float)i - motionevent.getY());
        float f;
        if (k < getPaddingBottom())
        {
            f = 0.0F;
        } else
        if (k > i - getPaddingTop())
        {
            f = 1.0F;
        } else
        {
            f = (float)k / (float)j;
        }
        setProgress((int)(f * (float)getMax()));
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        int i;
        boolean flag;
        i = keyevent.getAction();
        flag = false;
        if (i != 0) goto _L2; else goto _L1
_L1:
        keyevent.getKeyCode();
        JVM INSTR tableswitch 19 22: default 44
    //                   19 67
    //                   20 82
    //                   21 97
    //                   22 112;
           goto _L3 _L4 _L5 _L6 _L7
_L3:
        KeyEvent keyevent1 = new KeyEvent(0, keyevent.getKeyCode());
_L9:
        flag = keyevent1.dispatch(this);
_L2:
        return flag;
_L4:
        keyevent1 = new KeyEvent(0, 22);
        continue; /* Loop/switch isn't completed */
_L5:
        keyevent1 = new KeyEvent(0, 21);
        continue; /* Loop/switch isn't completed */
_L6:
        keyevent1 = new KeyEvent(0, 20);
        continue; /* Loop/switch isn't completed */
_L7:
        keyevent1 = new KeyEvent(0, 19);
        if (true) goto _L9; else goto _L8
_L8:
    }

    protected void onDraw(Canvas canvas)
    {
        canvas.rotate(-90F);
        canvas.translate(-height, 0.0F);
        super.onDraw(canvas);
    }

    protected void onMeasure(int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        height = android.view.View.MeasureSpec.getSize(j);
        width = android.view.View.MeasureSpec.getSize(i);
        setMeasuredDimension(width, height);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void onProgressRefresh(float f, boolean flag)
    {
        Drawable drawable = mThumb;
        if (drawable != null)
        {
            setThumbPos(getHeight(), drawable, f, 0x80000000);
            invalidate();
        }
        if (mOnSeekBarChangeListener != null)
        {
            mOnSeekBarChangeListener.onProgressChanged(this, getProgress(), flag);
        }
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(j, i, k, l);
    }

    void onStartTrackingTouch()
    {
        if (mOnSeekBarChangeListener != null)
        {
            mOnSeekBarChangeListener.onStartTrackingTouch(this);
        }
    }

    void onStopTrackingTouch()
    {
        if (mOnSeekBarChangeListener != null)
        {
            mOnSeekBarChangeListener.onStopTrackingTouch(this);
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if (!isEnabled())
        {
            return false;
        }
        motionevent.getAction();
        JVM INSTR tableswitch 0 3: default 44
    //                   0 46
    //                   1 75
    //                   2 63
    //                   3 92;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return true;
_L2:
        setPressed(true);
        onStartTrackingTouch();
        trackTouchEvent(motionevent);
        continue; /* Loop/switch isn't completed */
_L4:
        trackTouchEvent(motionevent);
        attemptClaimDrag();
        continue; /* Loop/switch isn't completed */
_L3:
        trackTouchEvent(motionevent);
        onStopTrackingTouch();
        setPressed(false);
        continue; /* Loop/switch isn't completed */
_L5:
        onStopTrackingTouch();
        setPressed(false);
        if (true) goto _L1; else goto _L6
_L6:
    }

    public void setOnSeekBarChangeListener(OnSeekBarChangeListenerVer onseekbarchangelistenerver)
    {
        mOnSeekBarChangeListener = onseekbarchangelistenerver;
    }

    public void setThumb(Drawable drawable)
    {
        mThumb = drawable;
        super.setThumb(drawable);
    }
}
