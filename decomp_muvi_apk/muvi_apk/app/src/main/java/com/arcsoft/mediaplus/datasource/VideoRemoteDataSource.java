// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.tool.DoubleBufferList;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsRemoteDataSource, Property, MediaItem, VideoItem

public class VideoRemoteDataSource extends AbsRemoteDataSource
    implements DataSourceFactory.IProduct
{
    public static class RemoteVideoItem extends VideoItem
    {

        String albumFile;
        String albumUrl;
        String channelName;
        String channelNr;
        int copyCount;
        long resumePoint;

        public String toString()
        {
            return (new StringBuilder()).append("Audio").append(title).append(" - ").append(duration).append("s").toString();
        }

        public RemoteVideoItem()
        {
            albumUrl = null;
            albumFile = null;
            resumePoint = 0L;
            copyCount = 0;
            channelName = null;
            channelNr = null;
        }
    }


    public static String PROJECTIONS[] = {
        "_ID", "TITLE", "URL", "DATE", "DURATION", "ALBUM_URL", "SIZE", "VGAURL", "RESUME_POINT", "COPY_COUNT", 
        "CHANNEL_NAME"
    };
    private static final Property SORT_CAPBILITIES[];

    public VideoRemoteDataSource(Context context, RemoteDBMgr remotedbmgr)
    {
        super(context, remotedbmgr);
    }

    public static MediaItem createMediaItems(Cursor cursor)
    {
        if (cursor == null)
        {
            return null;
        } else
        {
            RemoteVideoItem remotevideoitem = new RemoteVideoItem();
            remotevideoitem._id = cursor.getLong(0);
            remotevideoitem.title = cursor.getString(1);
            remotevideoitem.uri = Uri.parse(cursor.getString(2));
            remotevideoitem.datestring = cursor.getString(3);
            remotevideoitem.duration = 1000 * cursor.getInt(4);
            remotevideoitem.albumUrl = cursor.getString(5);
            remotevideoitem.size = cursor.getLong(6);
            remotevideoitem.vgaUri = Uri.parse(cursor.getString(7).replaceAll("&amp;", "&"));
            remotevideoitem.resumePoint = 1000L * cursor.getLong(8);
            remotevideoitem.copyCount = cursor.getInt(9);
            remotevideoitem.channelName = cursor.getString(10);
            remotevideoitem.videoOrImage = true;
            return remotevideoitem;
        }
    }

    public static Object getObjectProp(MediaItem mediaitem, Property property, Object obj, boolean flag)
    {
        RemoteVideoItem remotevideoitem;
        for (remotevideoitem = (RemoteVideoItem)mediaitem; property.equals(Property.PROP_WIDTH) || property.equals(Property.PROP_HEIGHT);)
        {
            return obj;
        }

        if (property.equals(Property.PROP_ADDED_TIME) || property.equals(Property.PROP_MODIFIED_TIME) || property.equals(Property.PROP_TAKEN_TIME))
        {
            return remotevideoitem.datestring;
        }
        if (property.equals(Property.PROP_THUMBNAIL_FILEPATH) || property.equals(Property.PROP_IMAGE_FILEPATH))
        {
            return remotevideoitem.albumFile;
        }
        if (property.equals(Property.PROP_DURATION))
        {
            return Long.valueOf(remotevideoitem.duration);
        }
        if (property.equals(Property.PROP_MIMETYPE))
        {
            return "video/*";
        }
        if (property.equals(Property.PROP_URI))
        {
            if (flag && remotevideoitem.vgaUri != null && remotevideoitem.vgaUri.getPath().length() != 0)
            {
                Log.e("AbsRemoteDataSource", (new StringBuilder()).append("VideoRemoteDataSource: vgaUri = ").append(remotevideoitem.vgaUri).toString());
                if (remotevideoitem.albumUrl != null && remotevideoitem.vgaUri.toString().equals(remotevideoitem.albumUrl.toString()))
                {
                    Log.e("AbsRemoteDataSource", (new StringBuilder()).append("VideoRemoteDataSource 0: Uri = ").append(mediaitem.uri).toString());
                    return mediaitem.uri;
                } else
                {
                    return remotevideoitem.vgaUri;
                }
            } else
            {
                Log.e("AbsRemoteDataSource", (new StringBuilder()).append("VideoRemoteDataSource: Uri = ").append(mediaitem.uri).toString());
                return mediaitem.uri;
            }
        }
        if (property.equals(Property.PROP_RESUMEPOINT))
        {
            return Long.valueOf(remotevideoitem.resumePoint);
        }
        if (property.equals(Property.PROP_COPYCOUNT))
        {
            return Integer.valueOf(remotevideoitem.copyCount);
        }
        if (property.equals(Property.PROP_CHANNELNAME))
        {
            return remotevideoitem.channelName;
        } else
        {
            return null;
        }
    }

    private String getSortOrder()
    {
        return getSortOrder(mSortProperty, mIsDesc);
    }

    public static String getSortOrder(Property property, boolean flag)
    {
        String s;
        if (property == null)
        {
            s = null;
        } else
        {
            if (!property.isBelongsTo(SORT_CAPBILITIES))
            {
                return null;
            }
            if (property.equals(Property.PROP_ADDED_TIME) || property.equals(Property.PROP_MODIFIED_TIME) || property.equals(Property.PROP_TAKEN_TIME))
            {
                s = "DATE";
            } else
            if (property.equals(Property.PROP_SIZE))
            {
                s = "SIZE";
            } else
            if (property.equals(Property.PROP_TITLE))
            {
                s = "lower(TITLE)";
            } else
            if (property.equals(Property.PROP_DURATION))
            {
                s = "DURATION";
            } else
            {
                return null;
            }
            if (flag)
            {
                return (new StringBuilder()).append(s).append(" DESC").toString();
            }
        }
        return s;
    }

    public static String getVideoDownloadUrl(MediaItem mediaitem, boolean flag)
    {
        RemoteVideoItem remotevideoitem = (RemoteVideoItem)mediaitem;
        if (remotevideoitem == null || remotevideoitem.albumFile != null)
        {
            return null;
        } else
        {
            return remotevideoitem.albumUrl;
        }
    }

    public static boolean updateRemoteVideoDownloadPath(MediaItem mediaitem, String s, boolean flag)
    {
        RemoteVideoItem remotevideoitem;
        for (remotevideoitem = (RemoteVideoItem)mediaitem; remotevideoitem == null || (remotevideoitem.albumFile == null || s != null) && (remotevideoitem.albumFile != null || s == null);)
        {
            return false;
        }

        remotevideoitem.albumFile = s;
        return true;
    }

    protected void OnDBDataUpdated(String s, com.arcsoft.mediaplus.datasource.db.RemoteDBMgr.UpdateInfo updateinfo)
    {
        if (updateinfo == null || updateinfo.videoadded)
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
        return createMediaItems(cursor);
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
        return getVideoDownloadUrl(mediaitem, flag);
    }

    public Object getObjectProp(int i, Property property, Object obj)
    {
        Object obj1 = getObjectProp((MediaItem)mList.get(i), property, obj, mIsVgaResource);
        if (obj1 != null)
        {
            return obj1;
        } else
        {
            return super.getObjectProp(i, property, obj);
        }
    }

    public Property[] getSortCapability()
    {
        return SORT_CAPBILITIES;
    }

    protected boolean onDownloadError(MediaItem mediaitem, boolean flag)
    {
        return false;
    }

    protected Cursor openCursor()
    {
        return RemoteDBMgr.instance().queryVideo(PROJECTIONS, null, null, null, null, getSortOrder(), null);
    }

    protected boolean updateDownloadedPath(MediaItem mediaitem, String s, boolean flag)
    {
        return updateDownloadedPath(mediaitem, s, flag);
    }

    static 
    {
        Property aproperty[] = new Property[4];
        aproperty[0] = Property.PROP_TITLE;
        aproperty[1] = Property.PROP_MODIFIED_TIME;
        aproperty[2] = Property.PROP_SIZE;
        aproperty[3] = Property.PROP_DURATION;
        SORT_CAPBILITIES = aproperty;
    }
}
