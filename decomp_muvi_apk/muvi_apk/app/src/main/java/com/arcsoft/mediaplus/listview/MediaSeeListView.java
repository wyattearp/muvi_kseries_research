// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.listview;

import android.content.Context;
import com.arcsoft.mediaplus.datasource.IDataSource;

public abstract class MediaSeeListView
{

    protected Context mContext;

    public MediaSeeListView(Context context)
    {
        mContext = context;
    }

    public abstract com.arcsoft.mediaplus.datasource.DataSourceFactory.DataSourceFilter getPlayDataSourceFilter(int i, IDataSource idatasource);

    public abstract int getPlayDataSourceIndex(int i, IDataSource idatasource);

    public abstract IDataSource initDataSource(int i);

    public abstract void releaseDataSource(int i, IDataSource idatasource);
}
