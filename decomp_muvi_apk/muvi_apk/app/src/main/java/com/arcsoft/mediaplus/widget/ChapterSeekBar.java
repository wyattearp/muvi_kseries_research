// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class ChapterSeekBar extends SeekBar
{

    private final String TAG;
    private Drawable mChapterDrawable;
    private long mChapterProgress[];
    private int mDrawableOffset;

    public ChapterSeekBar(Context context)
    {
        super(context);
        TAG = "ChapterSeekBar";
        mChapterDrawable = null;
        mDrawableOffset = 0;
        mChapterProgress = null;
    }

    public ChapterSeekBar(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        TAG = "ChapterSeekBar";
        mChapterDrawable = null;
        mDrawableOffset = 0;
        mChapterProgress = null;
    }

    public ChapterSeekBar(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        TAG = "ChapterSeekBar";
        mChapterDrawable = null;
        mDrawableOffset = 0;
        mChapterProgress = null;
    }

    private void setChapterPos(int i, float f, int j)
    {
        int k = i - getPaddingLeft() - getPaddingRight();
        int l = mChapterDrawable.getIntrinsicWidth();
        int i1 = mChapterDrawable.getIntrinsicHeight();
        int j1 = (int)(f * (float)((k - l) + mDrawableOffset));
        int k1;
        int l1;
        if (j == 0x80000000)
        {
            Rect rect = mChapterDrawable.getBounds();
            k1 = rect.top;
            l1 = rect.bottom;
        } else
        {
            k1 = j;
            l1 = j + i1;
        }
        mChapterDrawable.setBounds(j1, k1, j1 + l, l1);
    }

    protected void onDraw(Canvas canvas)
    {
        this;
        JVM INSTR monitorenter ;
        Drawable drawable;
        super.onDraw(canvas);
        drawable = mChapterDrawable;
        if (drawable == null)
        {
            break MISSING_BLOCK_LABEL_109;
        }
        int i;
        if (mChapterProgress == null || mChapterProgress.length <= 0)
        {
            break MISSING_BLOCK_LABEL_109;
        }
        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        i = mChapterProgress.length;
        int j = 0;
_L2:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        float f = (float)mChapterProgress[j] / (float)getMax();
        setChapterPos(getWidth(), f, 0);
        drawable.draw(canvas);
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        canvas.restore();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setChapterDrawable(Drawable drawable)
    {
        mChapterDrawable = drawable;
        if (drawable != null)
        {
            mChapterDrawable.setCallback(this);
            mDrawableOffset = mChapterDrawable.getIntrinsicWidth() / 2;
        }
        invalidate();
    }

    public void setChapterProgress(long al[])
    {
        mChapterProgress = al;
        invalidate();
    }

    public void setDrawableOffset(int i)
    {
        mDrawableOffset = i;
        invalidate();
    }
}
