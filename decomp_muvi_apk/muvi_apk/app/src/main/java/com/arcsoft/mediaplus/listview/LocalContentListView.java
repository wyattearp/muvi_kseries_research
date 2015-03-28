// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.listview;

import android.content.Context;
import com.arcsoft.mediaplus.datasource.DataSourceFactory;
import com.arcsoft.mediaplus.datasource.IDataSource;

// Referenced classes of package com.arcsoft.mediaplus.listview:
//            MediaSeeListView

public class LocalContentListView extends MediaSeeListView
{

    public LocalContentListView(Context context)
    {
        super(context);
    }

    public com.arcsoft.mediaplus.datasource.DataSourceFactory.DataSourceFilter getPlayDataSourceFilter(int i, IDataSource idatasource)
    {
        return DataSourceFactory.instance().queryFilter(idatasource);
    }

    public int getPlayDataSourceIndex(int i, IDataSource idatasource)
    {
        return i;
    }

    public IDataSource initDataSource(int i)
    {
        com.arcsoft.mediaplus.datasource.DataSourceFactory.DataSourceFilter datasourcefilter = new com.arcsoft.mediaplus.datasource.DataSourceFactory.DataSourceFilter();
        datasourcefilter.isLocal = true;
        datasourcefilter.mediatype = i;
        return DataSourceFactory.instance().getDataSource(datasourcefilter);
    }

    public void releaseDataSource(int i, IDataSource idatasource)
    {
        DataSourceFactory.instance().releaseDataSource(idatasource);
    }
}
