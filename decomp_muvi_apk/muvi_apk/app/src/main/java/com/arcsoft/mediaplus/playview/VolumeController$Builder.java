// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.os.Handler;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            VolumeController

public static final class mHandler
{

    private int mCurrentVolume;
    private long mDelayMillis;
    private boolean mEnable;
    private Handler mHandler;
    private ControllerListener mListener;
    private int mMaxVolume;
    private int mStepVolume;

    public VolumeController build()
    {
        return new VolumeController(this, null);
    }

    public mHandler enable(boolean flag)
    {
        mEnable = flag;
        return this;
    }

    public ControllerListener onListener(ControllerListener controllerlistener, long l)
    {
        mListener = controllerlistener;
        mDelayMillis = l;
        return this;
    }

    public mDelayMillis volume(int i, int j, int k)
    {
        mMaxVolume = i;
        mCurrentVolume = j;
        mStepVolume = k;
        return this;
    }








    public ControllerListener(Handler handler)
    {
        mHandler = null;
        mMaxVolume = 0;
        mCurrentVolume = 0;
        mStepVolume = 1;
        mListener = null;
        mDelayMillis = 1000L;
        mEnable = true;
        mHandler = handler;
    }
}
