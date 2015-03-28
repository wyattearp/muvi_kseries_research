// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.content.Context;
import android.os.Handler;

public class VolumeController
{
    public static final class Builder
    {

        private int mCurrentVolume;
        private long mDelayMillis;
        private boolean mEnable;
        private Handler mHandler;
        private onVolumeControllerListener mListener;
        private int mMaxVolume;
        private int mStepVolume;

        public VolumeController build()
        {
            return new VolumeController(this);
        }

        public Builder enable(boolean flag)
        {
            mEnable = flag;
            return this;
        }

        public Builder onListener(onVolumeControllerListener onvolumecontrollerlistener, long l)
        {
            mListener = onvolumecontrollerlistener;
            mDelayMillis = l;
            return this;
        }

        public Builder volume(int i, int j, int k)
        {
            mMaxVolume = i;
            mCurrentVolume = j;
            mStepVolume = k;
            return this;
        }








        public Builder(Handler handler)
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

    public static interface onVolumeControllerListener
    {

        public abstract void onVolumeChanged(int i, int j);

        public abstract void onVolumeHidden();
    }


    private final String TAG;
    private int mCurrentVolume;
    private long mDelayMillis;
    private boolean mEnable;
    private Handler mHandler;
    private Runnable mHideRunnable = new Runnable() {

        final VolumeController this$0;

        public void run()
        {
            if (mListener != null)
            {
                mListener.onVolumeHidden();
            }
        }

            
            {
                this$0 = VolumeController.this;
                super();
            }
    };
    private onVolumeControllerListener mListener;
    private int mMaxVolume;
    private int mStepVolume;

    private VolumeController(Builder builder)
    {
        TAG = "VolumeController";
        mHandler = null;
        mMaxVolume = 0;
        mCurrentVolume = 0;
        mStepVolume = 1;
        mListener = null;
        mDelayMillis = 1000L;
        mEnable = true;
        mHandler = builder.mHandler;
        mMaxVolume = builder.mMaxVolume;
        mCurrentVolume = builder.mCurrentVolume;
        mStepVolume = builder.mStepVolume;
        mListener = builder.mListener;
        mDelayMillis = builder.mDelayMillis;
        mEnable = builder.mEnable;
    }


    private void addRunnable()
    {
        if (mHandler != null)
        {
            mHandler.postDelayed(mHideRunnable, mDelayMillis);
        }
    }

    private void removeRunnable()
    {
        if (mHandler != null)
        {
            mHandler.removeCallbacks(mHideRunnable);
        }
    }

    public void enable(boolean flag)
    {
        if (mEnable == flag)
        {
            return;
        } else
        {
            removeRunnable();
            mEnable = flag;
            return;
        }
    }

    public int getVolume()
    {
        return mCurrentVolume;
    }

    public boolean goDown()
    {
        removeRunnable();
        int i = mCurrentVolume - mStepVolume;
        if (i < 0)
        {
            i = 0;
        }
        mCurrentVolume = ((-1 + (i + mStepVolume)) / mStepVolume) * mStepVolume;
        if (mListener != null)
        {
            mListener.onVolumeChanged(mCurrentVolume, mMaxVolume);
        }
        addRunnable();
        return true;
    }

    public boolean goUp()
    {
        removeRunnable();
        int i = mCurrentVolume + mStepVolume;
        if (i > mMaxVolume)
        {
            i = mMaxVolume;
        }
        mCurrentVolume = ((-1 + (i + mStepVolume)) / mStepVolume) * mStepVolume;
        if (mListener != null)
        {
            mListener.onVolumeChanged(mCurrentVolume, mMaxVolume);
        }
        addRunnable();
        return true;
    }

    public void init(Context context)
    {
    }

    public void initVolumeListener(onVolumeControllerListener onvolumecontrollerlistener)
    {
        mListener = onvolumecontrollerlistener;
    }

    public boolean setVolume(int i)
    {
        mCurrentVolume = ((-1 + (i + mStepVolume)) / mStepVolume) * mStepVolume;
        return true;
    }

    public void unInit()
    {
        removeRunnable();
    }

}
