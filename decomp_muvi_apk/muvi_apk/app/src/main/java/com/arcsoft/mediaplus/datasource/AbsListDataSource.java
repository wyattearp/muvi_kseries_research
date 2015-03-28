// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.net.Uri;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.util.tool.DoubleBufferList;
import java.util.List;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsDataSource, Property, MediaItem

public abstract class AbsListDataSource extends AbsDataSource
{

    protected static Property SUPPORTED_PROPERTIES[];
    private static final String TAG = "AbsListDataSource";
    private final com.arcsoft.util.tool.DoubleBufferList.ICallback mDoubleBufferCallback = new com.arcsoft.util.tool.DoubleBufferList.ICallback() {

        final AbsListDataSource this$0;

        public boolean onBuildList(List list, com.arcsoft.util.tool.DoubleBufferList.Options options)
        {
            return AbsListDataSource.this.onBuildList(list, options);
        }

        public void onDestoryList(List list)
        {
            AbsListDataSource.this.onDestoryList(list);
        }

        public void onListSwitched(int i, int j)
        {
            AbsListDataSource.this.onListSwitched(i, j);
        }

            
            {
                this$0 = AbsListDataSource.this;
                super();
            }
    };
    protected int mExpectCount;
    protected boolean mIsDesc;
    protected boolean mIsVgaResource;
    protected DoubleBufferList mList;
    protected Property mSortProperty;

    public AbsListDataSource()
    {
        mExpectCount = 0;
        mSortProperty = null;
        mIsDesc = false;
        mIsVgaResource = false;
    }

    private void onListSwitched(int i, int j)
    {
        notifyOnCountChanged(i, j);
    }

    public boolean delete(int i)
    {
        while (mList == null || i >= mList.getCount() || mList.delete(i) == null) 
        {
            return false;
        }
        return true;
    }

    public boolean delete(MediaItem mediaitem)
    {
        if (mList == null)
        {
            return false;
        } else
        {
            return mList.deleteMediaItem(mediaitem);
        }
    }

    public int getCount()
    {
        if (mList != null)
        {
            return mList.getCount();
        } else
        {
            return 0;
        }
    }

    public MediaItem getItem(int i)
    {
        if (mList != null)
        {
            return (MediaItem)mList.get(i);
        } else
        {
            return null;
        }
    }

    public Object getObjectProp(int i, Property property, Object obj)
    {
        MediaItem mediaitem = getItem(i);
        if (mediaitem != null)
        {
            if (property.equals(Property.PROP_TITLE))
            {
                return mediaitem.title;
            }
            if (property.equals(Property.PROP_URI))
            {
                return mediaitem.uri;
            }
            if (property.equals(Property.PROP_SIZE))
            {
                return Long.valueOf(mediaitem.size);
            }
            if (property.equals(Property.PROP_ID))
            {
                return Long.valueOf(mediaitem._id);
            }
            if (property.equals(Property.PROP_ISDIR))
            {
                return Boolean.valueOf(mediaitem.isDir);
            }
        }
        return super.getObjectProp(i, property, obj);
    }

    public Property[] getSortCapability()
    {
        return new Property[0];
    }

    public Property getSortProperty()
    {
        return mSortProperty;
    }

    public Property[] getSupportedProperties()
    {
        return SUPPORTED_PROPERTIES;
    }

    public boolean hasMore()
    {
        while (mExpectCount == -1 || mExpectCount >= getTotalCount()) 
        {
            return false;
        }
        return true;
    }

    protected boolean isCountArriveExpect(int i)
    {
        while (mExpectCount == -1 || i < mExpectCount) 
        {
            return false;
        }
        return true;
    }

    protected void loadMore(int i)
    {
        boolean flag = hasMore();
        if (i == -1)
        {
            mExpectCount = -1;
        } else
        {
            mExpectCount = i + mExpectCount;
        }
        if (flag)
        {
            mList.invalide();
        }
    }

    public com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask makeDownloadTask(int i)
    {
        com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask downloadtask = new com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask();
        MediaItem mediaitem = getItem(i);
        if (mediaitem == null)
        {
            return null;
        }
        long l;
        Uri uri;
        String s;
        if (mediaitem.videoOrImage)
        {
            l = 1L;
        } else
        {
            l = 3L;
        }
        downloadtask.mediaClass = l;
        downloadtask.dms_uuid = RemoteDBMgr.instance().getCurrentServer();
        downloadtask.mediaId = getLongProp(i, Property.PROP_ID, -1L);
        downloadtask.title = getStringProp(i, Property.PROP_TITLE, null);
        uri = (Uri)getObjectProp(i, Property.PROP_URI, null);
        if (uri != null)
        {
            s = uri.toString();
        } else
        {
            s = null;
        }
        downloadtask.uri = s;
        downloadtask.fileSize = getLongProp(i, Property.PROP_SIZE, 0L);
        downloadtask.item_uuid = getRemoteItemUUID(i);
        downloadtask.videoOrImage = mediaitem.videoOrImage;
        return downloadtask;
    }

    protected abstract boolean onBuildList(List list, com.arcsoft.util.tool.DoubleBufferList.Options options);

    protected abstract void onDestoryList(List list);

    protected void onDisable()
    {
        mList.stop();
    }

    protected void onEnable()
    {
        mList.start();
    }

    protected void onInit()
    {
        mList = new DoubleBufferList(mDoubleBufferCallback);
        mList.init();
    }

    protected void onUninit()
    {
        mList.unInit();
        mList = null;
    }

    public void quickIndexSort(List list)
    {
    }

    protected void refresh()
    {
        if (mList != null)
        {
            mList.clear();
            mList.invalide();
        }
    }

    protected void setResourceType(boolean flag)
    {
        mIsVgaResource = flag;
    }

    protected void sort(Property property, boolean flag)
    {
        mSortProperty = property;
        mIsDesc = flag;
        refresh();
    }

    static 
    {
        Property aproperty[] = new Property[3];
        aproperty[0] = Property.PROP_URI;
        aproperty[1] = Property.PROP_TITLE;
        aproperty[2] = Property.PROP_SIZE;
        SUPPORTED_PROPERTIES = aproperty;
    }

}
