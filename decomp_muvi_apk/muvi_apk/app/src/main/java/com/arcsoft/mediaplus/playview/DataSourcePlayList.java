// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.net.Uri;
import com.arcsoft.mediaplus.datasource.DMCDataSource;
import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.datasource.Property;
import com.arcsoft.mediaplus.playengine.AbsDMCPlayList;
import com.arcsoft.util.debug.DebugUtils;
import com.arcsoft.util.debug.Log;

public class DataSourcePlayList extends AbsDMCPlayList
{

    public static final String SCHEMA_FILE = "file://";
    private static final String TAG = "DataSourcePlayList";
    private final com.arcsoft.mediaplus.datasource.IDataSource.OnDataChangeListener mDataChangeListener = new com.arcsoft.mediaplus.datasource.IDataSource.OnDataChangeListener() {

        final DataSourcePlayList this$0;

        public void onCountChanged(int i, int j)
        {
            invalidCount();
            setCurrentIndexLater();
        }

        public void onDataChanged(int i, Property property)
        {
        }

            
            {
                this$0 = DataSourcePlayList.this;
                super();
            }
    };
    private DMCDataSource mDataSource;
    private int mExceptCurrentIndex;
    private boolean mIsSetIndexLater;

    public DataSourcePlayList()
    {
        mDataSource = null;
        mIsSetIndexLater = false;
        mExceptCurrentIndex = 0;
    }

    private void markSetCurrentIndexLater(int i)
    {
        mIsSetIndexLater = true;
        mExceptCurrentIndex = i;
    }

    private void setCurrentIndexLater()
    {
        if (mIsSetIndexLater)
        {
            mIsSetIndexLater = false;
            setCurrentIndex(mExceptCurrentIndex);
            notifyDelaySetCurrentIndex();
        }
    }

    public int getChannelID(int i)
    {
        if (mDataSource == null)
        {
            return 0;
        } else
        {
            return mDataSource.getIntProp(i, Property.PROP_CHANNELID, 0);
        }
    }

    public int getCount()
    {
        if (mDataSource == null)
        {
            return 0;
        } else
        {
            return mDataSource.getCount();
        }
    }

    public String getCurrentFilePath()
    {
        return getCurrentFilePathByIndex(getCurrentIndex());
    }

    public String getCurrentFilePathByIndex(int i)
    {
        String s;
        if (i < 0)
        {
            s = null;
        } else
        {
            s = getUri(i).toString();
            if (s != null && s.startsWith("file://"))
            {
                return Uri.decode(s.toString()).substring(7);
            }
        }
        return s;
    }

    public IDataSource getDataSource()
    {
        return mDataSource;
    }

    public long getId(int i)
    {
        if (mDataSource == null)
        {
            return 0L;
        } else
        {
            return mDataSource.getLongProp(i, Property.PROP_ID, 0L);
        }
    }

    public MediaItem getMediaItem(int i)
    {
        if (mDataSource == null)
        {
            return null;
        } else
        {
            return mDataSource.getItem(i);
        }
    }

    protected void getPlayURLAsync(int i, com.arcsoft.adk.atv.UPnP.MediaRenderDesc mediarenderdesc, Object obj, com.arcsoft.mediaplus.datasource.DMCDataSource.IOnGetPlayURLListener iongetplayurllistener)
    {
        com.arcsoft.mediaplus.datasource.DMCDataSource.GetURLRequest geturlrequest = new com.arcsoft.mediaplus.datasource.DMCDataSource.GetURLRequest();
        geturlrequest.index = i;
        geturlrequest.listener = iongetplayurllistener;
        geturlrequest.renderDesc = mediarenderdesc;
        geturlrequest.userdata = obj;
        mDataSource.getPlayURLAsync(geturlrequest, iongetplayurllistener);
    }

    public com.arcsoft.adk.atv.UPnP.RemoteItemDesc getRemoteItemDesc(int i)
    {
        if (mDataSource == null)
        {
            return null;
        } else
        {
            return mDataSource.getRemoteItemDesc(i);
        }
    }

    public Uri getUri(int i)
    {
        if (mDataSource == null)
        {
            return null;
        } else
        {
            return (Uri)mDataSource.getObjectProp(i, Property.PROP_URI, null);
        }
    }

    public void setCurrentIndex(int i)
    {
        if (mDataSource == null)
        {
            Log.w("DataSourcePlayList", (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append("datasource is null.").toString());
            return;
        }
        if (mDataSource.isReady())
        {
            super.setCurrentIndex(i);
            return;
        }
        if (i < mDataSource.getCount())
        {
            super.setCurrentIndex(i);
            return;
        }
        if (i >= mDataSource.getTotalCount())
        {
            Log.w("DataSourcePlayList", (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append("invalid input index").toString());
            return;
        } else
        {
            markSetCurrentIndexLater(i);
            return;
        }
    }

    public void setDataSource(DMCDataSource dmcdatasource)
    {
        if (mDataSource == dmcdatasource)
        {
            return;
        }
        if (mDataSource != null)
        {
            mDataSource.unregisterOnDataChangeListener(mDataChangeListener);
        }
        mDataSource = dmcdatasource;
        if (mDataSource != null)
        {
            mDataSource.registerOnDataChangeListener(mDataChangeListener);
        }
        invalidCount();
    }


}
