// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.text.NumberFormat;

public class SavingProgress extends AlertDialog
{

    Context mContext;
    private final int mExitValue = -1;
    private Handler mHandler;
    private boolean mHasStarted;
    private int mIncrementBy;
    private int mIncrementSecondaryBy;
    private boolean mIndeterminate;
    private Drawable mIndeterminateDrawable;
    private int mMax;
    private CharSequence mMessage;
    private ProgressBar mProgress;
    private Drawable mProgressDrawable;
    private TextView mProgressPercent;
    private NumberFormat mProgressPercentFormat;
    private int mProgressVal;
    private int mSecondaryProgressVal;

    public SavingProgress(Context context, Handler handler)
    {
        super(context);
        mHandler = null;
        mContext = context;
        mHandler = handler;
        setCanceledOnTouchOutside(false);
    }

    private void onProgressChanged()
    {
        int i = mProgress.getProgress();
        int j = mProgress.getMax();
        double d = (double)i / (double)j;
        mProgressPercent.setText(mProgressPercentFormat.format(d));
    }

    public int getMax()
    {
        if (mProgress != null)
        {
            return mProgress.getMax();
        } else
        {
            return mMax;
        }
    }

    public int getProgress()
    {
        if (mProgress != null)
        {
            return mProgress.getProgress();
        } else
        {
            return mProgressVal;
        }
    }

    public int getSecondaryProgress()
    {
        if (mProgress != null)
        {
            return mProgress.getSecondaryProgress();
        } else
        {
            return mSecondaryProgressVal;
        }
    }

    public void incrementProgressBy(int i)
    {
        if (mProgress != null)
        {
            mProgress.incrementProgressBy(i);
            onProgressChanged();
            return;
        } else
        {
            mIncrementBy = i + mIncrementBy;
            return;
        }
    }

    public void incrementSecondaryProgressBy(int i)
    {
        if (mProgress != null)
        {
            mProgress.incrementSecondaryProgressBy(i);
            onProgressChanged();
            return;
        } else
        {
            mIncrementSecondaryBy = i + mIncrementSecondaryBy;
            return;
        }
    }

    public boolean isIndeterminate()
    {
        if (mProgress != null)
        {
            return mProgress.isIndeterminate();
        } else
        {
            return mIndeterminate;
        }
    }

    protected void onCreate(Bundle bundle)
    {
        View view = getLayoutInflater().inflate(0x7f030001, null);
        mProgress = (ProgressBar)view.findViewById(0x7f09000a);
        mProgressPercent = (TextView)view.findViewById(0x7f09000b);
        mProgressPercentFormat = NumberFormat.getPercentInstance();
        mProgressPercentFormat.setMaximumFractionDigits(0);
        setView(view);
        if (mMax > 0)
        {
            setMax(mMax);
        }
        if (mProgressVal > 0)
        {
            setProgress(mProgressVal);
        }
        if (mSecondaryProgressVal > 0)
        {
            setSecondaryProgress(mSecondaryProgressVal);
        }
        if (mIncrementBy > 0)
        {
            incrementProgressBy(mIncrementBy);
        }
        if (mIncrementSecondaryBy > 0)
        {
            incrementSecondaryProgressBy(mIncrementSecondaryBy);
        }
        if (mProgressDrawable != null)
        {
            setProgressDrawable(mProgressDrawable);
        }
        if (mIndeterminateDrawable != null)
        {
            setIndeterminateDrawable(mIndeterminateDrawable);
        }
        if (mMessage != null)
        {
            setMessage(mMessage);
        }
        setIndeterminate(mIndeterminate);
        onProgressChanged();
        super.onCreate(bundle);
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if (i == 4)
        {
            if (mHandler != null)
            {
                mHandler.sendEmptyMessage(-1);
            }
        } else
        if (i == 84)
        {
            return true;
        }
        return super.onKeyDown(i, keyevent);
    }

    public void onStart()
    {
        super.onStart();
        mHasStarted = true;
    }

    protected void onStop()
    {
        super.onStop();
        mHasStarted = false;
    }

    public void setIndeterminate(boolean flag)
    {
        if (mHasStarted && isIndeterminate() != flag)
        {
            mProgress.setIndeterminate(flag);
            return;
        } else
        {
            mIndeterminate = flag;
            return;
        }
    }

    public void setIndeterminateDrawable(Drawable drawable)
    {
        if (mProgress != null)
        {
            mProgress.setIndeterminateDrawable(drawable);
            return;
        } else
        {
            mIndeterminateDrawable = drawable;
            return;
        }
    }

    public void setMax(int i)
    {
        if (mProgress != null)
        {
            mProgress.setMax(i);
            onProgressChanged();
            return;
        } else
        {
            mMax = i;
            return;
        }
    }

    public void setMessage(CharSequence charsequence)
    {
        if (mProgress != null)
        {
            super.setMessage(charsequence);
            return;
        } else
        {
            mMessage = charsequence;
            return;
        }
    }

    public void setProgress(int i)
    {
        if (mHasStarted)
        {
            mProgress.setProgress(i);
            int j = mProgress.getMax();
            int k;
            if (100 == j)
            {
                k = i;
            } else
            {
                k = (i * 100) / j;
            }
            mProgressPercent.setText((new StringBuilder()).append(k).append("%").toString());
            return;
        } else
        {
            mProgressVal = i;
            return;
        }
    }

    public void setProgressDrawable(Drawable drawable)
    {
        if (mProgress != null)
        {
            mProgress.setProgressDrawable(drawable);
            return;
        } else
        {
            mProgressDrawable = drawable;
            return;
        }
    }

    public void setSecondaryProgress(int i)
    {
        if (mProgress != null)
        {
            mProgress.setSecondaryProgress(i);
            onProgressChanged();
            return;
        } else
        {
            mSecondaryProgressVal = i;
            return;
        }
    }
}
