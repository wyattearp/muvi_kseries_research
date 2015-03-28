// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import android.content.Context;
import android.view.MotionEvent;

public class MultiTouchDetector
{
    public static interface OnMultiTouchListener
    {

        public abstract boolean OnMultiTouch(MultiTouchDetector multitouchdetector);

        public abstract boolean OnMultiTouchBegin(MultiTouchDetector multitouchdetector);

        public abstract void OnMultiTouchEnd(MultiTouchDetector multitouchdetector);
    }


    private static final float PRESSURE_THRESHOLD = 0.4F;
    private int mCenterX;
    private int mCenterY;
    private MotionEvent mCurEvent;
    private float mCurPressure;
    private float mCurrFingerDiffX;
    private float mCurrFingerDiffY;
    private float mCurrLen;
    private OnMultiTouchListener mListener;
    private MotionEvent mPreEvent;
    private float mPrePressure;
    private float mPrevFingerDiffX;
    private float mPrevFingerDiffY;
    private float mPrevLen;
    private float mScaleFactor;
    private boolean mbInProgress;
    private int miActionDownPointer;
    private int miActionDownX;
    private int miActionDownY;

    public MultiTouchDetector(Context context, OnMultiTouchListener onmultitouchlistener)
    {
        mListener = onmultitouchlistener;
    }

    private void reset()
    {
        if (mPreEvent != null)
        {
            mPreEvent.recycle();
            mPreEvent = null;
        }
        if (mCurEvent != null)
        {
            mCurEvent.recycle();
            mCurEvent = null;
        }
    }

    private void setContext(MotionEvent motionevent)
    {
        if (mCurEvent != null)
        {
            mCurEvent.recycle();
        }
        mCurEvent = MotionEvent.obtain(motionevent);
        mCurrLen = -1F;
        mPrevLen = -1F;
        mScaleFactor = -1F;
        mPrevFingerDiffX = mPreEvent.getX(1) - mPreEvent.getX(0);
        mPrevFingerDiffY = mPreEvent.getY(1) - mPreEvent.getY(0);
        mCurrFingerDiffX = motionevent.getX(1) - motionevent.getX(0);
        mCurrFingerDiffY = motionevent.getY(1) - motionevent.getY(0);
        float f = Math.abs(mCurrFingerDiffX - mPrevFingerDiffX);
        float f1 = Math.abs(mCurrFingerDiffY - mPrevFingerDiffY);
        if (f < 1.0F && f1 < 1.0F)
        {
            mCurrFingerDiffX = mPrevFingerDiffX;
            mCurrFingerDiffY = mPrevFingerDiffY;
        }
        mCurPressure = motionevent.getPressure(0) + motionevent.getPressure(1);
        mPrePressure = mPreEvent.getPressure(0) + mPreEvent.getPressure(1);
    }

    public int getActionDownPointer()
    {
        return miActionDownPointer;
    }

    public int getActionDownX()
    {
        return miActionDownX;
    }

    public int getActionDownY()
    {
        return miActionDownY;
    }

    public int getCenterX()
    {
        return mCenterX;
    }

    public int getCenterY()
    {
        return mCenterY;
    }

    public MotionEvent getCurEvent()
    {
        return mCurEvent;
    }

    public float getCurrentSpan()
    {
        if (Float.compare(mCurrLen, -1F) == 0)
        {
            float f = mCurrFingerDiffX;
            float f1 = mCurrFingerDiffY;
            mCurrLen = (float)Math.sqrt(f * f + f1 * f1);
        }
        return mCurrLen;
    }

    public float getPreviousSpan()
    {
        if (Float.compare(mPrevLen, -1F) == 0)
        {
            float f = mPrevFingerDiffX;
            float f1 = mPrevFingerDiffY;
            mPrevLen = (float)Math.sqrt(f * f + f1 * f1);
        }
        return mPrevLen;
    }

    public float getZoomScale()
    {
        if (Float.compare(mScaleFactor, -1F) != 0) goto _L2; else goto _L1
_L1:
        mScaleFactor = getCurrentSpan() / getPreviousSpan();
        if (getPreviousSpan() < 50F)
        {
            mScaleFactor = 1.0F;
        }
        if (mScaleFactor <= 1.2F) goto _L4; else goto _L3
_L3:
        mScaleFactor = 1.2F;
_L2:
        return mScaleFactor;
_L4:
        if (mScaleFactor < 0.8F)
        {
            mScaleFactor = 0.8F;
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i = motionevent.getAction();
        if (mbInProgress) goto _L2; else goto _L1
_L1:
        if (i != 5 && i != 261 || motionevent.getPointerCount() < 2) goto _L4; else goto _L3
_L3:
        reset();
        mPreEvent = MotionEvent.obtain(motionevent);
        mCenterX = (int)((motionevent.getX(0) + motionevent.getX(1)) / 2.0F);
        mCenterY = (int)((motionevent.getY(0) + motionevent.getY(1)) / 2.0F);
        setContext(motionevent);
        mbInProgress = mListener.OnMultiTouchBegin(this);
        miActionDownPointer = i;
        if (miActionDownPointer != 5) goto _L6; else goto _L5
_L5:
        miActionDownX = (int)motionevent.getX(0);
        miActionDownY = (int)motionevent.getY(0);
_L4:
        return true;
_L6:
        if (miActionDownPointer != 261) goto _L4; else goto _L7
_L7:
        miActionDownX = (int)motionevent.getX(1);
        miActionDownY = (int)motionevent.getY(1);
        return true;
_L2:
        switch (i)
        {
        default:
            return true;

        case 2: // '\002'
            setContext(motionevent);
            if (mCurPressure / mPrePressure > 0.4F && mListener.OnMultiTouch(this))
            {
                mPreEvent.recycle();
                mPreEvent = MotionEvent.obtain(motionevent);
                return true;
            }
            break;

        case 3: // '\003'
        case 6: // '\006'
        case 262: 
            setContext(motionevent);
            mListener.OnMultiTouchEnd(this);
            mbInProgress = false;
            reset();
            return true;
        }
        if (true) goto _L4; else goto _L8
_L8:
    }

    public void resetState()
    {
        mbInProgress = false;
    }
}
