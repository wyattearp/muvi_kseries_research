// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.mediaplus.datasource.Property;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            DmcPlayingDataProvider

public class DmcRemotePrefetcher
{
    public static interface IPrefetchDone
    {

        public abstract void prefetchDone(int i);
    }


    private com.arcsoft.mediaplus.datasource.IDataSource.OnDataChangeListener mDataChangeListener;
    private DmcPlayingDataProvider mDataProvider;
    private IDataSource mDataSource;
    private com.arcsoft.mediaplus.datasource.IDataSource.IController mDataSourceController;
    private IPrefetchDone mPrefetchListener;

    public DmcRemotePrefetcher(DmcPlayingDataProvider dmcplayingdataprovider, IDataSource idatasource)
    {
        mDataProvider = null;
        mDataSource = null;
        mDataSourceController = null;
        mPrefetchListener = null;
        mDataChangeListener = new com.arcsoft.mediaplus.datasource.IDataSource.OnDataChangeListener() {

            final DmcRemotePrefetcher this$0;

            public void onCountChanged(int i, int j)
            {
                if (i != 0)
                {
                    prefetch(mDataProvider.getCurrentIndex());
                }
            }

            public void onDataChanged(int i, Property property)
            {
                if (property == Property.PROP_THUMBNAIL_FILEPATH)
                {
                    int j = mDataProvider.getListIndex(i);
                    Log.d("DmcDecoder", (new StringBuilder()).append("onDataChanged:  index = ").append(i).append(" listIndex = ").append(j).toString());
                    if (mPrefetchListener != null && -1 != j)
                    {
                        mPrefetchListener.prefetchDone(j);
                    }
                }
            }

            
            {
                this$0 = DmcRemotePrefetcher.this;
                super();
            }
        };
        mDataProvider = dmcplayingdataprovider;
        mDataSource = idatasource;
    }

    public void destroy()
    {
        if (mDataSource != null)
        {
            try
            {
                stop();
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
            mDataSource.unregisterOnDataChangeListener(mDataChangeListener);
            mDataChangeListener = null;
        }
        if (mPrefetchListener != null)
        {
            mPrefetchListener = null;
        }
    }

    public void prefetch(int i)
    {
        if (mDataSourceController == null)
        {
            return;
        } else
        {
            int j = mDataProvider.getOriginIndex(i);
            com.arcsoft.mediaplus.datasource.IDataSource.IController icontroller = mDataSourceController;
            int k = j - 1;
            int l = j + 1;
            Property aproperty[] = new Property[1];
            aproperty[0] = Property.PROP_THUMBNAIL_FILEPATH;
            icontroller.prefetch(k, l, aproperty);
            return;
        }
    }

    public void prefetch(int i, int j)
    {
        if (mDataSourceController == null || j < i)
        {
            return;
        }
        int k = 1 + (j - i);
        int ai[] = new int[k];
        for (int l = 0; l < k; l++)
        {
            ai[l] = mDataProvider.getOriginIndex(i + l);
        }

        com.arcsoft.mediaplus.datasource.IDataSource.IController icontroller = mDataSourceController;
        Property aproperty[] = new Property[1];
        aproperty[0] = Property.PROP_THUMBNAIL_FILEPATH;
        icontroller.prefetchEx(ai, aproperty);
    }

    public void resume()
    {
        if (mDataSource != null && mDataSourceController == null)
        {
            mDataSourceController = mDataSource.getController();
            if (mDataSourceController != null)
            {
                mDataSourceController.setEnable(true);
                mDataSourceController.resume();
                mDataSourceController.setResourceType(false);
            }
            mDataSource.registerOnDataChangeListener(mDataChangeListener);
        }
    }

    public void setListener(IPrefetchDone iprefetchdone)
    {
        mPrefetchListener = iprefetchdone;
    }

    public void stop()
    {
        if (mDataSourceController != null)
        {
            mDataSourceController.pause();
            mDataSourceController.setEnable(false);
            mDataSourceController.release();
            mDataSourceController = null;
            mDataSource.registerOnDataChangeListener(mDataChangeListener);
        }
    }


}
