// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.listview;

import android.content.Context;
import com.arcsoft.mediaplus.datasource.DataSourceFactory;
import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.mediaplus.datasource.MatrixDataSource;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;

// Referenced classes of package com.arcsoft.mediaplus.listview:
//            MediaSeeListView

public class OnlineContentListView extends MediaSeeListView
{

    public OnlineContentListView(Context context)
    {
        super(context);
    }

    public com.arcsoft.mediaplus.datasource.DataSourceFactory.DataSourceFilter getPlayDataSourceFilter(int i, IDataSource idatasource)
    {
        IDataSource aidatasource[] = ((MatrixDataSource)idatasource).getDataSources();
        for (int j = 0; j < aidatasource.length; j++)
        {
            com.arcsoft.mediaplus.datasource.DataSourceFactory.DataSourceFilter datasourcefilter = DataSourceFactory.instance().queryFilter(aidatasource[j]);
            if (datasourcefilter.mediatype == i)
            {
                return datasourcefilter;
            }
        }

        return null;
    }

    public int getPlayDataSourceIndex(int i, IDataSource idatasource)
    {
        IDataSource aidatasource[] = ((MatrixDataSource)idatasource).getDataSources();
        int j = 0;
        for (int k = 0; k < -1 + aidatasource.length; k++)
        {
            j += aidatasource[k].getCount();
        }

        return i - j;
    }

    public IDataSource initDataSource(int i)
    {
        com.arcsoft.mediaplus.datasource.DataSourceFactory.DataSourceFilter datasourcefilter = new com.arcsoft.mediaplus.datasource.DataSourceFactory.DataSourceFilter();
        datasourcefilter.isLocal = false;
        datasourcefilter.serverUDN = RemoteDBMgr.instance().getCurrentServer();
        if (datasourcefilter.serverUDN == null)
        {
            datasourcefilter.serverUDN = "";
        }
        datasourcefilter.mediatype = i;
        datasourcefilter.protocal = 0;
        MatrixDataSource matrixdatasource = new MatrixDataSource(new IDataSource[] {
            DataSourceFactory.instance().getDataSource(datasourcefilter)
        });
        matrixdatasource.init();
        return matrixdatasource;
    }

    public void releaseDataSource(int i, IDataSource idatasource)
    {
        MatrixDataSource matrixdatasource = (MatrixDataSource)idatasource;
        IDataSource aidatasource[] = matrixdatasource.getDataSources();
        matrixdatasource.unInit();
        int j = aidatasource.length;
        for (int k = 0; k < j; k++)
        {
            IDataSource idatasource1 = aidatasource[k];
            DataSourceFactory.instance().releaseDataSource(idatasource1);
        }

    }
}
