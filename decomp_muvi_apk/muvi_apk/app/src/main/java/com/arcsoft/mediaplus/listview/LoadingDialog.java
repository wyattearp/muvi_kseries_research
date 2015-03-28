// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.listview;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import com.arcsoft.workshop.ui.UISaveDialog;

public class LoadingDialog extends UISaveDialog
{

    public static final int LOADINGDIALOG_ID = 12289;
    protected int mToastResId;

    public LoadingDialog(Context context)
    {
        super(context);
        mToastResId = 0x7f0b0012;
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    public LoadingDialog(Context context, int i)
    {
        super(context, i);
        mToastResId = 0x7f0b0012;
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    public LoadingDialog(Context context, boolean flag, android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        super(context, flag, oncancellistener);
        mToastResId = 0x7f0b0012;
    }

    protected boolean needFilterSomeKey(int i)
    {
        return false;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setText();
    }

    protected void setText()
    {
        if (mTextView != null)
        {
            mTextView.setText(mContext.getString(mToastResId));
        }
    }

    public void setText(int i)
    {
        mToastResId = i;
    }

    public void setText(String s)
    {
        if (mTextView != null)
        {
            mTextView.setText(s);
        }
    }
}
