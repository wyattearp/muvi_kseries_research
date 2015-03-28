// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.database.Cursor;
import android.net.Uri;
import com.arcsoft.mediaplus.datasource.db.SalixRemoteDBMgr;
import com.arcsoft.util.tool.DoubleBufferList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsListDataSource, SalixRemoteMediaItem, Property, MediaItem

public class SalixRemoteDataSource extends AbsListDataSource
    implements DataSourceFactory.IProduct, com.arcsoft.mediaplus.datasource.db.SalixRemoteDBMgr.IOnSalixDBUpdaterListener
{

    private static String PROJECTIONS[] = {
        "_ID", "name", "url", "time_code", "size", "is_video"
    };

    public SalixRemoteDataSource()
    {
    }

    private boolean buildMediaList(List list, com.arcsoft.util.tool.DoubleBufferList.Options options)
    {
        Cursor cursor = openCursor();
        if (cursor != null)
        {
            int i = 0;
            System.currentTimeMillis();
            while (cursor.moveToNext() && !options.mIsCanceled) 
            {
                list.add(createMediaItem(cursor));
                i++;
            }
            cursor.close();
            quickIndexSort(list);
            if (options.mIsCanceled)
            {
                return false;
            }
        }
        return true;
    }

    public void OnDBDataMounted()
    {
        refreshList();
    }

    public void OnDBDataTransmittedBegin()
    {
        NotifyOnDataBuiltBegin();
    }

    public void OnDBDataTransmittedFinish()
    {
        NotifyOnDataBuiltFinish();
    }

    public void OnDBDataUnMounted()
    {
        if (mList != null)
        {
            mList.clear();
        }
        refreshList();
    }

    public void OnDBDataUpdated()
    {
        refreshList();
    }

    public void create()
    {
        init();
    }

    protected MediaItem createMediaItem(Cursor cursor)
    {
        SalixRemoteMediaItem salixremotemediaitem = new SalixRemoteMediaItem();
        salixremotemediaitem._id = cursor.getInt(cursor.getColumnIndex("_ID"));
        boolean flag;
        if (cursor.getInt(cursor.getColumnIndex("is_video")) == 0)
        {
            flag = false;
        } else
        {
            flag = true;
        }
        salixremotemediaitem.videoOrImage = flag;
        salixremotemediaitem.addedTime = cursor.getInt(cursor.getColumnIndex("time_code"));
        salixremotemediaitem.title = cursor.getString(cursor.getColumnIndex("name"));
        salixremotemediaitem.size = cursor.getLong(cursor.getColumnIndex("size"));
        salixremotemediaitem.downloadUrl = cursor.getString(cursor.getColumnIndex("url"));
        salixremotemediaitem.uri = Uri.parse(salixremotemediaitem.downloadUrl);
        return salixremotemediaitem;
    }

    public void destory()
    {
        unInit();
    }

    public Object getObjectProp(int i, Property property, Object obj)
    {
        SalixRemoteMediaItem salixremotemediaitem = (SalixRemoteMediaItem)mList.get(i);
        if (property.equals(Property.PROP_TITLE))
        {
            return salixremotemediaitem.title;
        }
        if (property.equals(Property.PROP_ADDED_TIME))
        {
            return Long.valueOf(salixremotemediaitem.addedTime);
        }
        if (property.equals(Property.PROP_SIZE))
        {
            return Long.valueOf(salixremotemediaitem.size);
        }
        if (property.equals(Property.PROP_VIDEO_OR_IMAGE))
        {
            return Boolean.valueOf(salixremotemediaitem.videoOrImage);
        }
        if (property.equals(Property.PROP_DOWNLOAD_URL))
        {
            return salixremotemediaitem.downloadUrl;
        } else
        {
            return super.getObjectProp(i, property, obj);
        }
    }

    protected boolean onBuildList(List list, com.arcsoft.util.tool.DoubleBufferList.Options options)
    {
        while (options.mIsCanceled || !buildMediaList(list, options) || options.mIsCanceled) 
        {
            return false;
        }
        return true;
    }

    protected void onDestoryList(List list)
    {
        if (list == null)
        {
            return;
        }
        for (Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            MediaItem _tmp = (MediaItem)iterator.next();
        }

        list.clear();
    }

    protected void onInit()
    {
        super.onInit();
        SalixRemoteDBMgr.instance().registerOnDataUpdateListener(this);
    }

    protected Cursor openCursor()
    {
        return SalixRemoteDBMgr.instance().queryVideoAndImageCursor(PROJECTIONS, null, null, null, null, "time_code desc", null);
    }

    protected void refreshList()
    {
        mList.abortBuilding();
        mList.invalide();
    }

    protected void unInit()
    {
        super.unInit();
        SalixRemoteDBMgr.instance().unregisterOnDataUpdateListener(this);
    }

}
