// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import android.os.Handler;
import android.os.Message;
import android.widget.ImageButton;
import android.widget.SeekBar;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            DmcPlayback

private class <init> extends Handler
{

    private long mBufferBasedTimer;
    private long mCurrentProgress;
    private long mCurrentSysTime;
    private int mIndex;
    private boolean mIsTimerRunning;
    private long mTimerBasedTime;
    final DmcPlayback this$0;

    public void handleMessage(Message message)
    {
        mCurrentSysTime = System.currentTimeMillis();
        message.what;
        JVM INSTR lookupswitch 6: default 68
    //                   3: 69
    //                   4: 403
    //                   9: 422
    //                   10: 307
    //                   11: 348
    //                   22: 430;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L4:
        break MISSING_BLOCK_LABEL_422;
_L1:
        break; /* Loop/switch isn't completed */
_L7:
        break MISSING_BLOCK_LABEL_430;
_L8:
        return;
_L2:
        if (message.arg1 == 2)
        {
            mIndex = message.arg2;
            mCurrentProgress = 0L;
            mTimerBasedTime = mCurrentSysTime;
            refreshTotal(5000L);
            mCurrentMaxOfSeekbar = 5000;
            DmcPlayback.access$1200(DmcPlayback.this).setMax(mCurrentMaxOfSeekbar);
            DmcPlayback.access$1200(DmcPlayback.this).setEnabled(false);
            DmcPlayback.access$1300(DmcPlayback.this).setEnabled(false);
            refreshCurrent(0L);
            mIsTimerRunning = false;
            sendEmptyMessageDelayed(3, 50L);
            return;
        }
        if (mCurrentProgress >= 5000L)
        {
            removeMessages(3);
            refreshTotal(0L);
            refreshCurrent(0L);
            mIsTimerRunning = false;
            if (mListener != null)
            {
                mListener.mpleted();
                return;
            }
        } else
        {
            mIsTimerRunning = true;
            mCurrentProgress = mCurrentSysTime - mTimerBasedTime;
            Log.d("Dmc", (new StringBuilder()).append("ImageDisplaying progress: ").append(mCurrentProgress).toString());
            refreshCurrent(mCurrentProgress);
            sendEmptyMessageDelayed(3, 50L);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (mIsTimerRunning)
        {
            Log.d("Dmc", (new StringBuilder()).append("ImageDisplaying pause: ").append(mCurrentProgress).toString());
            removeMessages(3);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if (mIsTimerRunning)
        {
            Log.d("Dmc", (new StringBuilder()).append("ImageDisplaying resume: ").append(mCurrentProgress).toString());
            mTimerBasedTime = mCurrentSysTime - mCurrentProgress;
            sendEmptyMessage(3);
            return;
        }
        if (true) goto _L8; else goto _L3
_L3:
        refreshPlayingItem(message.arg1, (em)message.obj);
        return;
        finish();
        return;
        if (-1L == mBufferBasedTimer)
        {
            mBufferBasedTimer = mCurrentSysTime;
            sendEmptyMessageDelayed(22, 50L);
            Log.d("Dmc", "BufferTimer init");
            return;
        }
        if (mCurrentSysTime - mBufferBasedTimer >= 40000L)
        {
            removeMessages(22);
            mBufferBasedTimer = -1L;
            DmcPlayback.access$1400(DmcPlayback.this);
            Log.d("Dmc", "BufferTimer timeout");
            return;
        } else
        {
            Log.d("Dmc", (new StringBuilder()).append("BufferTimer progress = ").append(mCurrentSysTime - mBufferBasedTimer).toString());
            sendEmptyMessageDelayed(22, 50L);
            return;
        }
    }

    public boolean isTimingIndex(int i)
    {
        return mIndex == i && mIsTimerRunning;
    }

    public void resetBufferTimer()
    {
        mBufferBasedTimer = -1L;
    }

    private er()
    {
        this$0 = DmcPlayback.this;
        super();
        mCurrentProgress = 0L;
        mIsTimerRunning = false;
        mTimerBasedTime = 0L;
        mCurrentSysTime = 0L;
        mIndex = 0;
        mBufferBasedTimer = -1L;
    }

    mBufferBasedTimer(mBufferBasedTimer mbufferbasedtimer)
    {
        this();
    }
}
