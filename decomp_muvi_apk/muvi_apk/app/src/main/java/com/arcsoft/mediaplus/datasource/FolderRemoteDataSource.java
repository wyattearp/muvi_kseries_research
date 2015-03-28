// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.content.Context;
import android.database.Cursor;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsRemoteDataSource, MediaItem

public class FolderRemoteDataSource extends AbsRemoteDataSource
    implements DataSourceFactory.IProduct
{

    private static String PROJECTIONS[] = {
        "_ID", "TITLE"
    };

    FolderRemoteDataSource(Context context, RemoteDBMgr remotedbmgr)
    {
        super(context, remotedbmgr);
    }

    protected void OnDBDataUpdated(String s, com.arcsoft.mediaplus.datasource.db.RemoteDBMgr.UpdateInfo updateinfo)
    {
        if (updateinfo == null || updateinfo.folderadded)
        {
            super.OnDBDataUpdated(s, updateinfo);
        }
    }

    public void create()
    {
        super.init();
    }

    protected MediaItem createMediaItem(Cursor cursor)
    {
        if (cursor == null)
        {
            return null;
        } else
        {
            MediaItem mediaitem = new MediaItem();
            mediaitem._id = cursor.getLong(0);
            mediaitem.title = cursor.getString(1);
            mediaitem.isDir = true;
            return mediaitem;
        }
    }

    public void destory()
    {
        super.unInit();
    }

    protected void destoryItem(MediaItem mediaitem)
    {
    }

    protected String getDownloadUrlIfNeed(MediaItem mediaitem, boolean flag)
    {
        return null;
    }

    protected boolean onDownloadError(MediaItem mediaitem, boolean flag)
    {
        return false;
    }

    protected Cursor openCursor()
    {
        return RemoteDBMgr.instance().queryFolder(PROJECTIONS, null);
    }

    protected boolean updateDownloadedPath(MediaItem mediaitem, String s, boolean flag)
    {
        return false;
    }

}
