// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.os;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class HandlerTimer extends Handler
{
    public static interface IOnTimerListener
    {

        public abstract void onTimer();
    }


    private static final int MSG_RUN = 1;
    protected static final int USR_MSG = 1024;
    private boolean mContinue;
    private int mInterval;
    private IOnTimerListener mListener;
    private boolean mRepeat;

    public HandlerTimer()
    {
        mInterval = 0;
        mContinue = false;
        mRepeat = false;
        mListener = null;
    }

    public HandlerTimer(Looper looper)
    {
        super(looper);
        mInterval = 0;
        mContinue = false;
        mRepeat = false;
        mListener = null;
    }

    public void cancel()
    {
        this;
        JVM INSTR monitorenter ;
        removeMessages(1);
        mListener = null;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public IOnTimerListener geTimerListener()
    {
        return mListener;
    }

    public void handleMessage(Message message)
    {
        long l;
        if (message.what == 1)
        {
            l = System.currentTimeMillis();
            if (mListener != null)
            {
                mListener.onTimer();
            }
            if (!mRepeat)
            {
                cancel();
            }
            break MISSING_BLOCK_LABEL_39;
        }
        do
        {
            return;
        } while (mListener == null || !mContinue);
        long l1 = System.currentTimeMillis() - l;
        long l2 = (long)mInterval - l1;
        if (l2 < 0L)
        {
            l2 = 0L;
        }
        sendEmptyMessageDelayed(1, l2);
    }

    public boolean isRunning()
    {
        return hasMessages(1);
    }

    public void pause()
    {
        this;
        JVM INSTR monitorenter ;
        removeMessages(1);
        mContinue = false;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void resume()
    {
        this;
        JVM INSTR monitorenter ;
        IOnTimerListener iontimerlistener = mListener;
        if (iontimerlistener != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        mContinue = true;
        if (!hasMessages(1))
        {
            sendEmptyMessageDelayed(1, mInterval);
        }
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public void set(int i, boolean flag, IOnTimerListener iontimerlistener)
    {
        this;
        JVM INSTR monitorenter ;
        cancel();
        if (iontimerlistener != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        mRepeat = flag;
        mInterval = i;
        mContinue = true;
        mListener = iontimerlistener;
        sendEmptyMessageDelayed(1, i);
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }
}
