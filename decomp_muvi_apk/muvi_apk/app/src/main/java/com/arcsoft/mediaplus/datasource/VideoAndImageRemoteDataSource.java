// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.content.Context;
import android.database.Cursor;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.util.network.FileDownloader;
import java.io.File;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsRemoteDataSource, ImageRemoteDataSource, VideoRemoteDataSource, MediaItem, 
//            RemotePriorityComparator, Property

public class VideoAndImageRemoteDataSource extends AbsRemoteDataSource
    implements DataSourceFactory.IProduct
{

    public VideoAndImageRemoteDataSource(Context context, RemoteDBMgr remotedbmgr)
    {
        super(context, remotedbmgr);
    }

    private Cursor getRemoteImageCursor()
    {
        return RemoteDBMgr.instance().queryImage(ImageRemoteDataSource.PROJECTIONS, null, null, null, null, orderById(), null);
    }

    private Cursor getRemoteVideoCursor()
    {
        return RemoteDBMgr.instance().queryVideo(VideoRemoteDataSource.PROJECTIONS, null, null, null, null, orderById(), null);
    }

    boolean buildMediaList(Cursor cursor, List list, com.arcsoft.util.tool.DoubleBufferList.Options options, boolean flag)
    {
        int i;
        long l;
        boolean flag1;
        if (cursor == null)
        {
            return true;
        }
        i = 0;
        l = System.currentTimeMillis();
        flag1 = false;
_L13:
        if (!cursor.moveToNext()) goto _L2; else goto _L1
_L1:
        boolean flag2 = options.mIsCanceled;
        if (!flag2) goto _L3; else goto _L2
_L2:
        cursor.close();
_L11:
        MediaItem mediaitem;
        int j;
        boolean flag3;
        String s;
        boolean flag4;
        String s1;
        String s2;
        return !options.mIsCanceled;
_L3:
        mediaitem = createMediaItem(cursor, flag);
        if (flag1) goto _L5; else goto _L4
_L4:
        j = 0;
_L10:
        if (j >= 2) goto _L7; else goto _L6
_L6:
        if (!options.mIsCanceled) goto _L8; else goto _L7
_L7:
        if (System.currentTimeMillis() - l > 1000L)
        {
            flag1 = true;
        }
_L5:
        list.add(mediaitem);
        i++;
        continue; /* Loop/switch isn't completed */
_L8:
        if (j == 1)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        s = getDownloadUrlIfNeed(mediaitem, flag3);
        if (j == 1)
        {
            flag4 = true;
        } else
        {
            flag4 = false;
        }
        s1 = getDownloadPath(flag4);
        s2 = FileDownloader.instance().getParsedFilePath(s1, s);
        if ((new File(s2)).exists())
        {
            boolean flag5;
            if (j == 1)
            {
                flag5 = true;
            } else
            {
                flag5 = false;
            }
            Exception exception;
            Exception exception1;
            try
            {
                updateDownloadedPath(mediaitem, s2, flag5);
            }
            catch (Exception exception2) { }
            finally
            {
                cursor.close();
            }
        }
        j++;
        if (true) goto _L10; else goto _L9
_L9:
        exception1;
        exception1.printStackTrace();
        cursor.close();
          goto _L11
        throw exception;
        if (true) goto _L13; else goto _L12
_L12:
    }

    public void create()
    {
        super.init();
    }

    protected MediaItem createMediaItem(Cursor cursor)
    {
        return null;
    }

    MediaItem createMediaItem(Cursor cursor, boolean flag)
    {
        if (flag)
        {
            return VideoRemoteDataSource.createMediaItems(cursor);
        } else
        {
            return ImageRemoteDataSource.createMediaItems(cursor);
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
        if (mediaitem == null)
        {
            return null;
        }
        if (mediaitem.videoOrImage)
        {
            return VideoRemoteDataSource.getVideoDownloadUrl(mediaitem, flag);
        } else
        {
            return ImageRemoteDataSource.getImageDownloadUrl(mediaitem, flag);
        }
    }

    public Object getObjectProp(int i, Property property, Object obj)
    {
        MediaItem mediaitem = getItem(i);
        if (mediaitem == null)
        {
            return obj;
        }
        Object obj1;
        if (mediaitem.videoOrImage)
        {
            obj1 = VideoRemoteDataSource.getObjectProp(mediaitem, property, obj, mIsVgaResource);
        } else
        {
            obj1 = ImageRemoteDataSource.getObjectProp(mediaitem, property, obj, mIsVgaResource);
        }
        if (obj1 != null)
        {
            return obj1;
        } else
        {
            return super.getObjectProp(i, property, obj);
        }
    }

    protected boolean onBuildList(List list, com.arcsoft.util.tool.DoubleBufferList.Options options)
    {
        boolean flag = true;
        mLastPrefetchStart = -1;
        for (mLastPrefetchEnd = -1; options.mIsCanceled || !buildMediaList(getRemoteImageCursor(), list, options, false) || !buildMediaList(getRemoteVideoCursor(), list, options, flag);)
        {
            return false;
        }

        quickIndexSort(list);
        if (options.mIsCanceled)
        {
            flag = false;
        }
        return flag;
    }

    protected boolean onDownloadError(MediaItem mediaitem, boolean flag)
    {
        while (mediaitem == null || mediaitem.videoOrImage) 
        {
            return false;
        }
        return ImageRemoteDataSource.onImageDownloadError(mediaitem, flag);
    }

    protected Cursor openCursor()
    {
        return null;
    }

    String orderById()
    {
        return "_ID";
    }

    public void quickIndexSort(List list)
    {
        if (list == null)
        {
            return;
        }
        try
        {
            Collections.sort(list, new RemotePriorityComparator());
            return;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    protected boolean updateDownloadedPath(MediaItem mediaitem, String s, boolean flag)
    {
        if (mediaitem == null || s == null)
        {
            return false;
        }
        if (mediaitem.videoOrImage)
        {
            return VideoRemoteDataSource.updateRemoteVideoDownloadPath(mediaitem, s, flag);
        } else
        {
            return ImageRemoteDataSource.updateRemoteImageDownloadPath(mediaitem, s, flag);
        }
    }
}
