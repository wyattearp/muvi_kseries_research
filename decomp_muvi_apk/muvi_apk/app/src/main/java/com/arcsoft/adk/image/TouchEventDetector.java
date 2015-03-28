// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.image;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class TouchEventDetector
{
    public static interface TouchEventCallback
    {

        public abstract boolean OnMultiTouchBegin(TouchEventDetector toucheventdetector);

        public abstract void OnMultiTouchEnd(TouchEventDetector toucheventdetector);

        public abstract boolean OnMultiTouchNext(TouchEventDetector toucheventdetector);

        public abstract void onDoubleTap(int i, int j);

        public abstract boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1);

        public abstract void onSingleKeyDown(int i, int j);

        public abstract void onSingleKeyMove(int i, int j);

        public abstract void onSingleKeyUp(int i, int j);
    }


    private static final float PRESSURE_THRESHOLD = 0.67F;
    private int mCenterX;
    private int mCenterY;
    private MotionEvent mCurEvent;
    private float mCurPressure;
    private float mCurrFingerDiffX;
    private float mCurrFingerDiffY;
    private float mCurrLen;
    private GestureDetector mGestureDetector;
    private TouchEventCallback mListener;
    private boolean mMutiTouchDown;
    private MotionEvent mPreEvent;
    private float mPrePressure;
    private float mPrevFingerDiffX;
    private float mPrevFingerDiffY;
    private float mPrevLen;
    private float mScaleFactor;

    public TouchEventDetector(TouchEventCallback toucheventcallback)
    {
        mMutiTouchDown = false;
        mGestureDetector = new GestureDetector(new android.view.GestureDetector.SimpleOnGestureListener() {

            final TouchEventDetector this$0;

            public boolean onDoubleTap(MotionEvent motionevent)
            {
                int i = (int)motionevent.getX();
                int j = (int)motionevent.getY();
                mListener.onDoubleTap(i, j);
                return true;
            }

            public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
            {
                mListener.onScroll(motionevent, motionevent1);
                return true;
            }

            
            {
                this$0 = TouchEventDetector.this;
                super();
            }
        });
        mListener = toucheventcallback;
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
        mCurPressure = motionevent.getPressure(0) + motionevent.getPressure(1);
        mPrePressure = mPreEvent.getPressure(0) + mPreEvent.getPressure(1);
    }

    public int getCenterX()
    {
        return mCenterX;
    }

    public int getCenterY()
    {
        return mCenterY;
    }

    public float getCurrentSpan()
    {
        if (mCurrLen == -1F)
        {
            float f = mCurrFingerDiffX;
            float f1 = mCurrFingerDiffY;
            mCurrLen = (float)Math.sqrt(f * f + f1 * f1);
        }
        return mCurrLen;
    }

    public float getPreviousSpan()
    {
        if (mPrevLen == -1F)
        {
            float f = mPrevFingerDiffX;
            float f1 = mPrevFingerDiffY;
            mPrevLen = (float)Math.sqrt(f * f + f1 * f1);
        }
        return mPrevLen;
    }

    public int getRotateDirection()
    {
        float f = mCurrFingerDiffX;
        float f1 = -mCurrFingerDiffY;
        if (f == 0.0F)
        {
            return f1 <= 0.0F ? 270 : 90;
        }
        if (f1 == 0.0F)
        {
            return f <= 0.0F ? 180 : 0;
        }
        int i = (int)((180D * Math.atan(f1 / f)) / 3.1415926535897931D);
        if (f > 0.0F)
        {
            return (i + 360) % 360;
        } else
        {
            return i + 180;
        }
    }

    public float getZoomScale()
    {
        if (mScaleFactor != -1F) goto _L2; else goto _L1
_L1:
        mScaleFactor = getCurrentSpan() / getPreviousSpan();
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
        if (motionevent.getPointerCount() >= 2) goto _L2; else goto _L1
_L1:
        int j;
        int k;
        mGestureDetector.onTouchEvent(motionevent);
        j = (int)motionevent.getX();
        k = (int)motionevent.getY();
        if (mMutiTouchDown) goto _L4; else goto _L3
_L3:
        if (i != 0) goto _L6; else goto _L5
_L5:
        mListener.onSingleKeyDown(j, k);
_L8:
        return true;
_L6:
        if (1 == i)
        {
            mListener.onSingleKeyUp(j, k);
            return true;
        }
        if (2 == i)
        {
            mListener.onSingleKeyMove(j, k);
            return true;
        }
        break; /* Loop/switch isn't completed */
_L4:
        if (i == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (1 == i)
        {
            mMutiTouchDown = false;
            mListener.onSingleKeyUp(j, k);
            return true;
        }
        if (2 == i) goto _L8; else goto _L7
_L7:
        return false;
_L2:
        switch (i)
        {
        default:
            return true;

        case 2: // '\002'
            if (mPreEvent == null)
            {
                return false;
            }
            break;

        case 5: // '\005'
        case 261: 
            reset();
            mPreEvent = MotionEvent.obtain(motionevent);
            setContext(motionevent);
            mListener.OnMultiTouchBegin(this);
            mMutiTouchDown = true;
            return true;

        case 3: // '\003'
        case 6: // '\006'
        case 262: 
            mListener.OnMultiTouchEnd(this);
            reset();
            return true;
        }
        mCenterX = (int)((motionevent.getX(0) + motionevent.getX(1)) / 2.0F);
        mCenterY = (int)((motionevent.getY(0) + motionevent.getY(1)) / 2.0F);
        setContext(motionevent);
        if (mCurPressure / mPrePressure > 0.67F && mListener.OnMultiTouchNext(this))
        {
            mPreEvent.recycle();
            mPreEvent = MotionEvent.obtain(motionevent);
            return true;
        }
        if (true) goto _L8; else goto _L9
_L9:
    }

}
