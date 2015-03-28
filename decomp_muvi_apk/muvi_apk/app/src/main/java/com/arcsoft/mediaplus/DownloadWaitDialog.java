// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.content.Context;
import android.view.KeyEvent;
import android.widget.TextView;
import com.arcsoft.mediaplus.listview.LoadingDialog;

public class DownloadWaitDialog extends LoadingDialog
{
    public static interface IBackPressListener
    {

        public abstract void onWaittingDlgBackPressed();
    }


    IBackPressListener mBackPressListener;

    public DownloadWaitDialog(Context context)
    {
        super(context);
        mBackPressListener = null;
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    public DownloadWaitDialog(Context context, int i)
    {
        super(context, i);
        mBackPressListener = null;
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    public DownloadWaitDialog(Context context, boolean flag, android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        super(context, flag, oncancellistener);
        mBackPressListener = null;
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if (i == 4)
        {
            if (mBackPressListener != null)
            {
                mBackPressListener.onWaittingDlgBackPressed();
            }
            dismiss();
        }
        return super.onKeyDown(i, keyevent);
    }

    public void setBackPressListener(IBackPressListener ibackpresslistener)
    {
        mBackPressListener = ibackpresslistener;
    }

    protected void setText()
    {
        if (mTextView != null)
        {
            mTextView.setText(mContext.getString(0x7f0b0181));
        }
    }
}
