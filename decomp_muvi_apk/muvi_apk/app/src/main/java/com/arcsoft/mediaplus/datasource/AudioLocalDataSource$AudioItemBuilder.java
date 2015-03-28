// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import com.arcsoft.util.tool.EmptyCursor;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            Property, AudioItem, MediaItem, AudioLocalDataSource

protected static class mContentResolver extends mContentResolver
{

    private static final String COLUMN_GENRE = "genre";
    private static final Property SORT_CAPBILITIES[];
    private static Property SUPPORTED_PROPERTIES[];
    protected final String COLUMNS[] = {
        "_id", "_data", "title", "album", "artist", "duration", "_size", "composer", "mime_type"
    };
    private final int COLUMN_ALBUM = 3;
    private final int COLUMN_ARTIST = 4;
    private final int COLUMN_COMPOSER = 7;
    private final int COLUMN_DURATION = 5;
    private final int COLUMN_ID = 0;
    private final int COLUMN_MIME_TYPE = 8;
    private final int COLUMN_SIZE = 6;
    private final int COLUMN_TITLE = 2;
    private final int COLUMN_URI = 1;
    private final ContentResolver mContentResolver;
    protected Cursor mCursor;
    private Long mId;
    private boolean mIsDesc;
    private Property mSortProperty;

    private static String getGenre(ContentResolver contentresolver, AudioItem audioitem)
    {
        Cursor cursor = contentresolver.query(Uri.parse((new StringBuilder()).append(android.provider.T_URI).append("/").append(audioitem._id).append("/genres").toString()), new String[] {
            "name"
        }, null, null, null);
        String s = null;
        if (cursor != null)
        {
            boolean flag = cursor.moveToFirst();
            s = null;
            if (flag)
            {
                s = cursor.getString(0);
            }
        }
        if (cursor != null)
        {
            cursor.close();
        }
        return s;
    }

    private static long getModifiedTime(ContentResolver contentresolver, AudioItem audioitem)
    {
        Cursor cursor = contentresolver.query(Uri.parse((new StringBuilder()).append(android.provider.T_URI).append("/").append(audioitem._id).toString()), new String[] {
            "date_modified"
        }, null, null, null);
        long l = 0L;
        if (cursor != null && cursor.moveToFirst())
        {
            l = cursor.getLong(0);
        }
        if (cursor != null)
        {
            cursor.close();
        }
        return l;
    }

    public static Object getObjectPropStatic(ContentResolver contentresolver, MediaItem mediaitem, Property property, Object obj)
        throws IllegalArgumentException
    {
        AudioItem audioitem = (AudioItem)mediaitem;
        if (!property.equals(Property.PROP_ALBUM)) goto _L2; else goto _L1
_L1:
        String s = audioitem.album;
_L4:
        return s;
_L2:
        if (property.equals(Property.PROP_ARTIST))
        {
            return audioitem.artist;
        }
        if (property.equals(Property.PROP_DURATION))
        {
            return Long.valueOf(audioitem.duration);
        }
        if (property.equals(Property.PROP_COMPOSER))
        {
            return audioitem.composer;
        }
        if (property.equals(Property.PROP_GENRE))
        {
            return getGenre(contentresolver, audioitem);
        }
        if (property.equals(Property.PROP_MIMETYPE))
        {
            return audioitem.mime_type;
        }
        if (!property.equals(Property.PROP_THUMBNAIL_FILEPATH))
        {
            break; /* Loop/switch isn't completed */
        }
        Uri uri1 = audioitem.uri;
        s = null;
        if (uri1 != null)
        {
            return audioitem.uri.getPath();
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (property.equals(Property.PROP_IMAGE_FILEPATH))
        {
            Uri uri = audioitem.uri;
            s = null;
            if (uri != null)
            {
                return audioitem.uri.getPath();
            }
        } else
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
            if (property.equals(Property.PROP_MODIFIED_TIME))
            {
                return Long.valueOf(getModifiedTime(contentresolver, audioitem));
            } else
            {
                throw new IllegalArgumentException("Input property is not supported");
            }
        }
        if (true) goto _L4; else goto _L5
_L5:
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
            if (mSortProperty.equals(Property.PROP_ADDED_TIME))
            {
                s = "date_added";
            } else
            if (mSortProperty.equals(Property.PROP_MODIFIED_TIME))
            {
                s = "date_modified";
            } else
            if (mSortProperty.equals(Property.PROP_SIZE))
            {
                s = "_size";
            } else
            if (mSortProperty.equals(Property.PROP_TITLE))
            {
                s = "lower(title)";
            } else
            if (mSortProperty.equals(Property.PROP_DURATION))
            {
                s = "duration";
            } else
            if (mSortProperty.equals(Property.PROP_ALBUM))
            {
                s = "album_key";
            } else
            if (mSortProperty.equals(Property.PROP_ARTIST))
            {
                s = "artist_key";
            } else
            if (mSortProperty.equals(Property.PROP_GENRE))
            {
                s = "genre is null,lower(genre)";
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

    public MediaItem buildItem()
    {
        MediaItem mediaitem = createItem();
        fillItem(mediaitem);
        return mediaitem;
    }

    protected MediaItem createItem()
    {
        return new AudioItem();
    }

    protected void fillItem(MediaItem mediaitem)
    {
        AudioItem audioitem = (AudioItem)mediaitem;
        audioitem._id = mCursor.getLong(0);
        audioitem.title = mCursor.getString(2);
        audioitem.uri = Uri.parse((new StringBuilder()).append("file://").append(mCursor.getString(1)).toString());
        audioitem.album = mCursor.getString(3);
        audioitem.artist = mCursor.getString(4);
        audioitem.duration = mCursor.getLong(5);
        audioitem.size = mCursor.getLong(6);
        audioitem.composer = mCursor.getString(7);
        audioitem.mime_type = mCursor.getString(8);
    }

    public int getCount()
    {
        return mCursor.getCount();
    }

    public Object getObjectProp(MediaItem mediaitem, Property property, Object obj)
        throws IllegalArgumentException
    {
        return getObjectPropStatic(mContentResolver, mediaitem, property, obj);
    }

    String[] getQueryColumns()
    {
        if (mSortProperty != null && mSortProperty.equals(Property.PROP_GENRE))
        {
            String as[] = new String[1 + COLUMNS.length];
            for (int i = 0; i < COLUMNS.length; i++)
            {
                as[i] = COLUMNS[i];
            }

            as[COLUMNS.length] = "(select distinct audio_genres.name from audio_genres_map,audio_genres where audio_genres_map.audio_id=audio._id and  audio_genres_map.genre_id=audio_genres._id ) as genre";
            return as;
        } else
        {
            return COLUMNS;
        }
    }

    public Property[] getSortCapability()
    {
        return SORT_CAPBILITIES;
    }

    public Property[] getSupportedProperties()
    {
        return SUPPORTED_PROPERTIES;
    }

    String getWhere()
    {
        return null;
    }

    String[] getWhereArgs()
    {
        return null;
    }

    protected void onClose()
    {
        mCursor.close();
    }

    protected boolean onMove(int i, int j)
    {
        return mCursor.moveToPosition(j);
    }

    protected void onOpen()
    {
        String as[] = getQueryColumns();
        String s = getWhere();
        String as1[];
        if (mId != null)
        {
            if (s != null)
            {
                s = (new StringBuilder()).append("_id=").append(mId).append(" AND (").append(s).append(")").toString();
            } else
            {
                s = (new StringBuilder()).append("_id=").append(mId).toString();
            }
        }
        as1 = getWhereArgs();
        mCursor = mContentResolver.query(AudioLocalDataSource.access$000(), as, s, as1, getSortOrder());
        if (mCursor == null)
        {
            mCursor = EmptyCursor.EMPTY_CURSOR;
        }
    }

    public mCursor setId(long l)
    {
        mId = Long.valueOf(l);
        return this;
    }

    public mId setSortOrder(Property property, boolean flag)
    {
        mSortProperty = property;
        mIsDesc = flag;
        return this;
    }

    static 
    {
        Property aproperty[] = new Property[8];
        aproperty[0] = Property.PROP_TITLE;
        aproperty[1] = Property.PROP_ALBUM;
        aproperty[2] = Property.PROP_ARTIST;
        aproperty[3] = Property.PROP_SIZE;
        aproperty[4] = Property.PROP_DURATION;
        aproperty[5] = Property.PROP_GENRE;
        aproperty[6] = Property.PROP_ADDED_TIME;
        aproperty[7] = Property.PROP_MODIFIED_TIME;
        SORT_CAPBILITIES = aproperty;
        Property aproperty1[] = new Property[8];
        aproperty1[0] = Property.PROP_URI;
        aproperty1[1] = Property.PROP_TITLE;
        aproperty1[2] = Property.PROP_ALBUM;
        aproperty1[3] = Property.PROP_ARTIST;
        aproperty1[4] = Property.PROP_SIZE;
        aproperty1[5] = Property.PROP_ADDED_TIME;
        aproperty1[6] = Property.PROP_MODIFIED_TIME;
        aproperty1[7] = Property.PROP_DURATION;
        SUPPORTED_PROPERTIES = aproperty1;
    }

    public (ContentResolver contentresolver)
    {
        mIsDesc = false;
        mId = null;
        mContentResolver = contentresolver;
    }
}
