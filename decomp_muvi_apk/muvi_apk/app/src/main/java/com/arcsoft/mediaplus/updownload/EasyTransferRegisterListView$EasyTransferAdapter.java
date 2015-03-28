// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine;

// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            EasyTransferRegisterListView

private class <init> extends BaseAdapter
{

    final EasyTransferRegisterListView this$0;

    public int getCount()
    {
        if (mEasyTransferEngine != null)
        {
            return mEasyTransferEngine.getCount();
        } else
        {
            return 0;
        }
    }

    public Object getItem(int i)
    {
        return null;
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        if (view == null)
        {
            view = LayoutInflater.from(EasyTransferRegisterListView.access$100(EasyTransferRegisterListView.this)).inflate(0x7f030013, null);
        }
        EasyTransferRegisterListView.access$200(EasyTransferRegisterListView.this, view, i);
        return view;
    }

    private ()
    {
        this$0 = EasyTransferRegisterListView.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
