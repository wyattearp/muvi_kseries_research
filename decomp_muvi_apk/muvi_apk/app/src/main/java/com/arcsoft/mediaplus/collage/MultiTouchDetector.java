// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.collage;

import android.content.Context;
import android.graphics.Point;
import android.util.FloatMath;
import android.view.MotionEvent;
import com.arcsoft.util.debug.Log;

public class MultiTouchDetector
{
    public static interface OnMultiTouchListener
    {

        public abstract boolean OnMultiTouch(MultiTouchDetector multitouchdetector);

        public abstract boolean OnMultiTouchBegin(MultiTouchDetector multitouchdetector);

        public abstract void OnMultiTouchEnd(MultiTouchDetector multitouchdetector);
    }

    public static final class TouchMode extends Enum
    {

        private static final TouchMode $VALUES[];
        public static final TouchMode None;
        public static final TouchMode Rotate;
        public static final TouchMode Zoom;

        public static TouchMode valueOf(String s)
        {
            return (TouchMode)Enum.valueOf(com/arcsoft/mediaplus/collage/MultiTouchDetector$TouchMode, s);
        }

        public static TouchMode[] values()
        {
            return (TouchMode[])$VALUES.clone();
        }

        static 
        {
            None = new TouchMode("None", 0);
            Zoom = new TouchMode("Zoom", 1);
            Rotate = new TouchMode("Rotate", 2);
            TouchMode atouchmode[] = new TouchMode[3];
            atouchmode[0] = None;
            atouchmode[1] = Zoom;
            atouchmode[2] = Rotate;
            $VALUES = atouchmode;
        }

        private TouchMode(String s, int i)
        {
            super(s, i);
        }
    }


    private static final float PRESSURE_THRESHOLD = 0.4F;
    private int mAngle;
    private int mAngleLastZeroCount;
    private int mCenterX;
    private int mCenterY;
    private MotionEvent mCurEvent;
    private float mCurPressure;
    private float mCurrFingerDiffX;
    private float mCurrFingerDiffY;
    private float mCurrLen;
    private final OnMultiTouchListener mListener;
    private MotionEvent mPreEvent;
    private float mPrePressure;
    private float mPrevFingerDiffX;
    private float mPrevFingerDiffY;
    private float mPrevLen;
    private final Point mPt1Cur = new Point();
    private final Point mPt1Pre = new Point();
    private final Point mPt2Cur = new Point();
    private final Point mPt2Pre = new Point();
    private float mScaleFactor;
    private TouchMode mTouchMode;
    private final Point mZoomFirstPointerPos = new Point(-1, -1);
    private boolean mbInProgress;
    private int miActionDownPointer;
    private int miActionDownX;
    private int miActionDownY;

    public MultiTouchDetector(Context context, OnMultiTouchListener onmultitouchlistener)
    {
        mTouchMode = TouchMode.None;
        mAngleLastZeroCount = 0;
        mAngle = 0;
        mListener = onmultitouchlistener;
    }

    private float distance(float f, float f1, float f2, float f3)
    {
        float f4 = f1 - f;
        float f5 = f3 - f2;
        return FloatMath.sqrt(f4 * f4 + f5 * f5);
    }

    private int distance(Point point, Point point1)
    {
        float f = point.x - point1.x;
        float f1 = point.y - point1.y;
        return (int)FloatMath.sqrt(f * f + f1 * f1);
    }

    private float getRotateAngle()
    {
        float f = distance(mPt1Pre, mPt1Cur);
        float f1 = distance(mPt2Pre, mPt2Cur);
        Point point = new Point();
        float f2;
        byte byte0;
        int i;
        float f3;
        if (f < f1)
        {
            point.x = (mPt1Pre.x + mPt1Cur.x) / 2;
            point.y = (mPt1Pre.y + mPt1Cur.y) / 2;
            float f7 = distance(point.x, mPt2Pre.x, point.y, mPt2Pre.y);
            float f8 = distance(point.x, mPt2Cur.x, point.y, mPt2Cur.y);
            float f9 = distance(mPt2Pre.x, mPt2Cur.x, mPt2Pre.y, mPt2Cur.y);
            f2 = (float)((180D * Math.acos(((f7 * f7 + f8 * f8) - f9 * f9) / (f8 * (2.0F * f7)))) / 3.1400000000000001D);
        } else
        if (f > f1)
        {
            point.x = (mPt2Pre.x + mPt2Cur.x) / 2;
            point.y = (mPt2Pre.y + mPt2Cur.y) / 2;
            float f4 = distance(mPt1Pre.x, point.x, mPt1Pre.y, point.y);
            float f5 = distance(mPt1Cur.x, point.x, mPt1Cur.y, point.y);
            float f6 = distance(mPt1Pre.x, mPt1Cur.x, mPt1Pre.y, mPt1Cur.y);
            f2 = (float)((180D * Math.acos(((f4 * f4 + f5 * f5) - f6 * f6) / (f5 * (2.0F * f4)))) / 3.1400000000000001D);
        } else
        {
            f2 = 0.0F;
        }
        byte0 = 1;
        i = Float.compare(f2, 0.0F);
        f3 = 0.0F;
        if (i != 0)
        {
            if (f < f1)
            {
                f3 = (float)((point.x * (mPt2Pre.y - mPt2Cur.y) - point.y * (mPt2Pre.x - mPt2Cur.x)) + (mPt2Pre.x * mPt2Cur.y - mPt2Pre.y * mPt2Cur.x)) / 2.0F;
            } else
            {
                f3 = (float)((point.x * (mPt1Pre.y - mPt1Cur.y) - point.y * (mPt1Pre.x - mPt1Cur.x)) + (mPt1Pre.x * mPt1Cur.y - mPt1Pre.y * mPt1Cur.x)) / 2.0F;
            }
        }
        if (f3 < 0.0F)
        {
            byte0 = -1;
        }
        Log.v("MiniatureAngle", (new StringBuilder()).append("angle111 : ").append(f2).toString());
        Log.v("MiniatureAngle", (new StringBuilder()).append("angle111 direction : ").append(byte0).toString());
        if (byte0 == 1)
        {
            f2 = -f2;
        }
        return f2;
    }

    private int getRotateAngleUsingVector()
    {
        float f;
        float f1;
        float f2;
        float f3;
        int i;
        float f4;
        float f5;
        float f6;
        float f7;
        byte byte0;
        if ((float)distance(mPt1Pre, mPt1Cur) < (float)distance(mPt2Pre, mPt2Cur))
        {
            f = mPt2Pre.x - mPt1Pre.x;
            f1 = mPt2Pre.y - mPt1Pre.y;
            f2 = mPt2Cur.x - mPt1Cur.x;
            f3 = mPt2Cur.y - mPt1Cur.y;
        } else
        {
            f = mPt1Pre.x - mPt2Pre.x;
            f1 = mPt1Pre.y - mPt2Pre.y;
            f2 = mPt1Cur.x - mPt2Cur.x;
            f3 = mPt1Cur.y - mPt2Cur.y;
        }
        i = (int)((180D * Math.acos((float)((double)(f * f2 + f1 * f3) / (Math.sqrt(f * f + f1 * f1) * Math.sqrt(f2 * f2 + f3 * f3))))) / 3.1400000000000001D);
        f4 = (float)((double)(f * 1.0F + f1 * 0.0F) / (Math.sqrt(f * f + f1 * f1) * Math.sqrt(1.0F * 1.0F + 0.0F * 0.0F)));
        f5 = (float)((double)(f2 * 1.0F + f3 * 0.0F) / (Math.sqrt(f2 * f2 + f3 * f3) * Math.sqrt(1.0F * 1.0F + 0.0F * 0.0F)));
        f6 = (int)((180D * Math.acos(f4)) / 3.1400000000000001D);
        f7 = (int)((180D * Math.acos(f5)) / 3.1400000000000001D);
        byte0 = 1;
        if (f6 - f7 < 0.0F)
        {
            byte0 = -1;
        }
        if (byte0 == 1)
        {
            i = -i;
        }
        return i;
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

    public int getAngle()
    {
        return mAngle;
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

    public TouchMode getTouchMode()
    {
        return mTouchMode;
    }

    public Point getZoomFirstPointerPos()
    {
        return mZoomFirstPointerPos;
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
        mbInProgress = true;
        mZoomFirstPointerPos.x = (int)motionevent.getX(0);
        mZoomFirstPointerPos.y = (int)motionevent.getY(0);
        mTouchMode = TouchMode.None;
        mPt1Pre.x = (int)motionevent.getX(0);
        mPt2Pre.x = (int)motionevent.getX(1);
        mPt1Pre.y = (int)motionevent.getY(0);
        mPt2Pre.y = (int)motionevent.getY(1);
        mAngleLastZeroCount = 0;
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
        i;
        JVM INSTR lookupswitch 4: default 292
    //                   2: 294
    //                   3: 444
    //                   6: 444
    //                   262: 444;
           goto _L8 _L9 _L10 _L10 _L10
_L8:
        return true;
_L9:
        if (mTouchMode != TouchMode.None) goto _L12; else goto _L11
_L11:
        mPt1Cur.x = (int)motionevent.getX(0);
        mPt2Cur.x = (int)motionevent.getX(1);
        mPt1Cur.y = (int)motionevent.getY(0);
        mPt2Cur.y = (int)motionevent.getY(1);
        if (mPt1Cur.x != mPt1Pre.x || mPt1Cur.y != mPt1Pre.y || mPt2Cur.x != mPt2Pre.x || mPt2Cur.y != mPt2Pre.y)
        {
            mTouchMode = TouchMode.Zoom;
            mListener.OnMultiTouchBegin(this);
            return true;
        }
          goto _L4
_L10:
        if (mTouchMode != TouchMode.Zoom) goto _L14; else goto _L13
_L13:
        setContext(motionevent);
        reset();
_L15:
        mListener.OnMultiTouchEnd(this);
        mbInProgress = false;
        mTouchMode = TouchMode.None;
        return true;
_L14:
        if (mTouchMode == TouchMode.Rotate)
        {
            mAngleLastZeroCount = 0;
            mAngle = 0;
        }
        if (true) goto _L15; else goto _L12
_L12:
        if (mTouchMode != TouchMode.Zoom)
        {
            continue; /* Loop/switch isn't completed */
        }
        setContext(motionevent);
        if (mCurPressure / mPrePressure <= 0.4F || !mListener.OnMultiTouch(this)) goto _L4; else goto _L16
_L16:
        mPreEvent.recycle();
        mPreEvent = MotionEvent.obtain(motionevent);
        return true;
        if (mTouchMode != TouchMode.Rotate) goto _L4; else goto _L17
_L17:
        mPt1Cur.x = (int)motionevent.getX(0);
        mPt2Cur.x = (int)motionevent.getX(1);
        mPt1Cur.y = (int)motionevent.getY(0);
        mPt2Cur.y = (int)motionevent.getY(1);
        float f = getRotateAngle();
        if (Float.compare(f, 1.0F) < 0 && Float.compare(f, 0.0F) > 0)
        {
            mAngle = 1;
        } else
        if (Float.compare(f, 0.0F) < 0 && Float.compare(f, -1F) > 0)
        {
            mAngle = -1;
        } else
        {
            mAngle = (int)f;
        }
        mAngle = (360 + mAngle % 360) % 360;
        Log.e("rotate angle", (new StringBuilder()).append("mAngle=").append(mAngle).toString());
        mPt1Pre.x = mPt1Cur.x;
        mPt2Pre.x = mPt2Cur.x;
        mPt1Pre.y = mPt1Cur.y;
        mPt2Pre.y = mPt2Cur.y;
        mListener.OnMultiTouch(this);
        return true;
    }

    public void resetState()
    {
        mbInProgress = false;
    }
}
