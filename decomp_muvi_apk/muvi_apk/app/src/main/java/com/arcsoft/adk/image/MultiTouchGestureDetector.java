// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.image;

import android.content.Context;
import android.graphics.PointF;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.arcsoft.util.debug.Log;

public class MultiTouchGestureDetector extends GestureDetector
{
    public static interface OnPinchListener
    {

        public abstract boolean onEndPinch(MotionEvent motionevent);

        public abstract boolean onPinch(MotionEvent motionevent, MotionEvent motionevent1, PointF pointf, float f);

        public abstract boolean onStartPinch(MotionEvent motionevent);
    }


    private OnPinchListener mPinchListener;
    private boolean mPinched;
    private MotionEvent mPreviousMultiTouchEvent;

    public MultiTouchGestureDetector(Context context, android.view.GestureDetector.OnGestureListener ongesturelistener)
    {
        super(context, ongesturelistener, null);
        mPinched = false;
        mPinchListener = null;
        mPreviousMultiTouchEvent = null;
        if (ongesturelistener instanceof OnPinchListener)
        {
            setOnPinchListener((OnPinchListener)ongesturelistener);
        }
    }

    public MultiTouchGestureDetector(Context context, android.view.GestureDetector.OnGestureListener ongesturelistener, Handler handler)
    {
        super(context, ongesturelistener, handler);
        mPinched = false;
        mPinchListener = null;
        mPreviousMultiTouchEvent = null;
        if (ongesturelistener instanceof OnPinchListener)
        {
            setOnPinchListener((OnPinchListener)ongesturelistener);
        }
    }

    private void dumpEvent(MotionEvent motionevent)
    {
        if (motionevent.getAction() == 2)
        {
            return;
        }
        String as[] = {
            "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE", "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?"
        };
        StringBuilder stringbuilder = new StringBuilder();
        int i = motionevent.getAction();
        int j = i & 0xff;
        stringbuilder.append("event ACTION_").append(as[j]);
        if (j == 5 || j == 6)
        {
            stringbuilder.append("(pid ").append(i >> 8);
            stringbuilder.append(")");
        }
        stringbuilder.append("[");
        for (int k = 0; k < motionevent.getPointerCount(); k++)
        {
            stringbuilder.append("#").append(k);
            stringbuilder.append("(pid ").append(motionevent.getPointerId(k));
            stringbuilder.append(")=").append((int)motionevent.getX(k));
            stringbuilder.append(",").append((int)motionevent.getY(k));
            if (k + 1 < motionevent.getPointerCount())
            {
                stringbuilder.append(";");
            }
        }

        stringbuilder.append("]");
        Log.d("Touch", stringbuilder.toString());
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if (motionevent.getAction() == 1)
        {
            mPinched = false;
        }
        if (motionevent.getPointerCount() > 1 && mPinchListener != null)
        {
            mPinched = true;
            if (mPreviousMultiTouchEvent == null)
            {
                mPreviousMultiTouchEvent = MotionEvent.obtain(motionevent);
                mPinchListener.onStartPinch(motionevent);
                return true;
            }
            if (motionevent.getAction() == 2)
            {
                float f = mPreviousMultiTouchEvent.getX(0);
                float f1 = mPreviousMultiTouchEvent.getX(1);
                float f2 = mPreviousMultiTouchEvent.getY(0);
                float f3 = mPreviousMultiTouchEvent.getY(1);
                float f4 = motionevent.getX(0);
                float f5 = motionevent.getX(1);
                float f6 = motionevent.getY(0);
                float f7 = motionevent.getY(1);
                float f8 = Math.abs(f - f1);
                float f9 = Math.abs(f4 - f5);
                float f10 = Math.abs(f2 - f3);
                float f11 = Math.abs(f6 - f7);
                float f12 = (float)(Math.sqrt(f9 * f9 + f11 * f11) / Math.sqrt(f8 * f8 + f10 * f10));
                mPinchListener.onPinch(mPreviousMultiTouchEvent, motionevent, new PointF((f4 + f5) / 2.0F, (f6 + f7) / 2.0F), f12);
            }
            mPreviousMultiTouchEvent = MotionEvent.obtain(motionevent);
            return true;
        }
        if (mPreviousMultiTouchEvent != null)
        {
            mPreviousMultiTouchEvent = null;
            mPinchListener.onEndPinch(motionevent);
        }
        if (mPinched)
        {
            return false;
        } else
        {
            return super.onTouchEvent(motionevent);
        }
    }

    public void setOnPinchListener(OnPinchListener onpinchlistener)
    {
        mPinchListener = onpinchlistener;
    }
}
