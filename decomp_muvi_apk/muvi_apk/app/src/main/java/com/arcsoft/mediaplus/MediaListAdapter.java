// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MediaListAdapter extends BaseAdapter
{

    protected Context mContext;
    protected LayoutInflater mInflater;

    public MediaListAdapter(Context context)
    {
        mContext = null;
        mInflater = null;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount()
    {
        return 0;
    }

    public Object getItem(int i)
    {
        return null;
    }

    public long getItemId(int i)
    {
        return 0L;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        return null;
    }
}
