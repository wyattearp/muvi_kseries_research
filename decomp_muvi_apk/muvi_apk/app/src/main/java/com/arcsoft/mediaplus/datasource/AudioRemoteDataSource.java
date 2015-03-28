// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.util.tool.DoubleBufferList;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsRemoteDataSource, Property, MediaItem, AudioItem

public class AudioRemoteDataSource extends AbsRemoteDataSource
    implements DataSourceFactory.IProduct
{
    private class RemoteAudioItem extends AudioItem
    {

        String albumFile;
        String albumUrl;
        String datestring;
        final AudioRemoteDataSource this$0;

        public String toString()
        {
            return (new StringBuilder()).append("Audio").append(title).append(" - ").append(duration).append("s").toString();
        }

        private RemoteAudioItem()
        {
            this$0 = AudioRemoteDataSource.this;
            super();
            albumUrl = null;
            albumFile = null;
            datestring = null;
        }

    }


    private static String PROJECTIONS[] = {
        "_ID", "TITLE", "DURATION", "ARTIST", "ALBUM", "GENRE", "ALBUM_URL", "URL", "SIZE", "DATE"
    };
    private static final Property SORT_CAPBILITIES[];

    AudioRemoteDataSource(Context context, RemoteDBMgr remotedbmgr)
    {
        super(context, remotedbmgr);
    }

    private String getSortOrder()
    {
        String s;
        if (mSortProperty == null)
        {
            s = null;
        } else
        {
            if (!mSortProperty.isBelongsTo(SORT_CAPBILITIES))
            {
                return null;
            }
            if (mSortProperty.equals(Property.PROP_SIZE))
            {
                s = "SIZE";
            } else
            if (mSortProperty.equals(Property.PROP_TITLE))
            {
                s = "lower(TITLE)";
            } else
            if (mSortProperty.equals(Property.PROP_DURATION))
            {
                s = "DURATION";
            } else
            if (mSortProperty.equals(Property.PROP_ALBUM))
            {
                s = "ALBUM";
            } else
            if (mSortProperty.equals(Property.PROP_ARTIST))
            {
                s = "ARTIST";
            } else
            if (mSortProperty.equals(Property.PROP_GENRE))
            {
                s = "GENRE is null,lower(GENRE)";
            } else
            if (mSortProperty.equals(Property.PROP_ADDED_TIME) || mSortProperty.equals(Property.PROP_MODIFIED_TIME) || mSortProperty.equals(Property.PROP_TAKEN_TIME))
            {
                s = "DATE";
            } else
            {
                return null;
            }
            if (mIsDesc)
            {
                return (new StringBuilder()).append(s).append(" DESC").toString();
            }
        }
        return s;
    }

    protected void OnDBDataUpdated(String s, com.arcsoft.mediaplus.datasource.db.RemoteDBMgr.UpdateInfo updateinfo)
    {
        if (updateinfo == null || updateinfo.audioadded)
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
            RemoteAudioItem remoteaudioitem = new RemoteAudioItem();
            remoteaudioitem._id = cursor.getLong(0);
            remoteaudioitem.title = cursor.getString(1);
            remoteaudioitem.duration = 1000 * cursor.getInt(2);
            remoteaudioitem.artist = cursor.getString(3);
            remoteaudioitem.album = cursor.getString(4);
            remoteaudioitem.genre = cursor.getString(5);
            remoteaudioitem.albumUrl = cursor.getString(6);
            remoteaudioitem.uri = Uri.parse(cursor.getString(7));
            remoteaudioitem.size = cursor.getLong(8);
            remoteaudioitem.datestring = cursor.getString(9);
            return remoteaudioitem;
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
        RemoteAudioItem remoteaudioitem = (RemoteAudioItem)mediaitem;
        if (remoteaudioitem == null || remoteaudioitem.albumFile != null)
        {
            return null;
        } else
        {
            return remoteaudioitem.albumUrl;
        }
    }

    public Object getObjectProp(int i, Property property, Object obj)
    {
        RemoteAudioItem remoteaudioitem = (RemoteAudioItem)mList.get(i);
        if (property.equals(Property.PROP_ALBUM))
        {
            return remoteaudioitem.album;
        }
        if (property.equals(Property.PROP_ARTIST))
        {
            return remoteaudioitem.artist;
        }
        if (property.equals(Property.PROP_COMPOSER))
        {
            return remoteaudioitem.composer;
        }
        if (property.equals(Property.PROP_GENRE))
        {
            return remoteaudioitem.genre;
        }
        if (property.equals(Property.PROP_DURATION))
        {
            return Long.valueOf(remoteaudioitem.duration);
        }
        if (property.equals(Property.PROP_MODIFIED_TIME))
        {
            return remoteaudioitem.datestring;
        }
        if (property.equals(Property.PROP_MIMETYPE))
        {
            return "audio/*";
        }
        if (property.equals(Property.PROP_THUMBNAIL_FILEPATH) || property.equals(Property.PROP_IMAGE_FILEPATH))
        {
            return remoteaudioitem.albumFile;
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
        return RemoteDBMgr.instance().queryAudio(PROJECTIONS, null, null, null, null, getSortOrder(), null);
    }

    protected boolean updateDownloadedPath(MediaItem mediaitem, String s, boolean flag)
    {
        RemoteAudioItem remoteaudioitem;
        for (remoteaudioitem = (RemoteAudioItem)mediaitem; remoteaudioitem == null || (remoteaudioitem.albumFile == null || s != null) && (remoteaudioitem.albumFile != null || s == null);)
        {
            return false;
        }

        remoteaudioitem.albumFile = s;
        return true;
    }

    static 
    {
        Property aproperty[] = new Property[7];
        aproperty[0] = Property.PROP_TITLE;
        aproperty[1] = Property.PROP_ALBUM;
        aproperty[2] = Property.PROP_ARTIST;
        aproperty[3] = Property.PROP_SIZE;
        aproperty[4] = Property.PROP_DURATION;
        aproperty[5] = Property.PROP_GENRE;
        aproperty[6] = Property.PROP_MODIFIED_TIME;
        SORT_CAPBILITIES = aproperty;
    }
}
