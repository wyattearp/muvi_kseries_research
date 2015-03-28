// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class UISaveDialog extends Dialog
{

    protected Context mContext;
    protected TextView mTextView;

    public UISaveDialog(Context context)
    {
        super(context);
        mTextView = null;
        mContext = null;
        mContext = context;
    }

    public UISaveDialog(Context context, int i)
    {
        super(context, i);
        mTextView = null;
        mContext = null;
        mContext = context;
    }

    public UISaveDialog(Context context, boolean flag, android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        super(context, flag, oncancellistener);
        mTextView = null;
        mContext = null;
        mContext = context;
    }

    protected boolean needFilterSomeKey(int i)
    {
        return 4 == i;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030029);
        mTextView = (TextView)findViewById(0x7f09004b);
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if (needFilterSomeKey(i))
        {
            return true;
        } else
        {
            return super.onKeyDown(i, keyevent);
        }
    }

    protected void onStart()
    {
        super.onStart();
    }

    protected void onStop()
    {
        super.onStop();
    }

    protected void setText()
    {
    }
}
