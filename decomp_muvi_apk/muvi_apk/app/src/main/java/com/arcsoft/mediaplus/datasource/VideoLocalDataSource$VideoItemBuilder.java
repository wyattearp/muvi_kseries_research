// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import com.arcsoft.util.tool.EmptyCursor;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            Property, VideoItem, MediaItem, VideoLocalDataSource

public static class mContentResolver extends mContentResolver
{
    protected static class LocalVideoItem extends VideoItem
    {

        protected boolean isWidthHeightValid;

        protected LocalVideoItem()
        {
            isWidthHeightValid = false;
        }
    }


    public static final String COLUMNS[] = {
        "_id", "_data", "title", "date_added", "date_modified", "datetaken", "duration", "_size", "mime_type"
    };
    public static final int COLUMN_DATE_ADDED = 3;
    public static final int COLUMN_DATE_MODIFIED = 4;
    public static final int COLUMN_DATE_TAKEN = 5;
    public static final int COLUMN_DURATION = 6;
    public static final int COLUMN_ID = 0;
    public static final int COLUMN_MIME_TYPE = 8;
    public static final int COLUMN_SIZE = 7;
    public static final int COLUMN_TITLE = 2;
    public static final int COLUMN_URI = 1;
    private static final Property SORT_CAPBILITIES[];
    private static Property SUPPORTED_PROPERTIES[];
    private final ContentResolver mContentResolver;
    protected Cursor mCursor;
    private Long mId;
    private boolean mIsDesc;
    private Property mSortProperty;

    public static void fillVideoItem(Cursor cursor, MediaItem mediaitem)
    {
        if (cursor == null || mediaitem == null)
        {
            return;
        } else
        {
            VideoItem videoitem = (VideoItem)mediaitem;
            videoitem._id = cursor.getLong(0);
            videoitem.title = cursor.getString(2);
            videoitem.uri = Uri.parse((new StringBuilder()).append("file://").append(cursor.getString(1)).toString());
            videoitem.addedTime = cursor.getLong(3);
            videoitem.modifiedTime = cursor.getLong(4);
            videoitem.takenTime = cursor.getLong(5);
            videoitem.duration = cursor.getLong(6);
            videoitem.size = cursor.getLong(7);
            videoitem.mime_type = cursor.getString(8);
            videoitem.videoOrImage = true;
            return;
        }
    }

    public static Object getObjectPropStatic(MediaItem mediaitem, Property property, Object obj)
        throws IllegalArgumentException
    {
        VideoItem videoitem = (VideoItem)mediaitem;
        if (!property.equals(Property.PROP_WIDTH)) goto _L2; else goto _L1
_L1:
        Integer integer = Integer.valueOf(getVideoItemWidth(videoitem));
_L4:
        return integer;
_L2:
        if (property.equals(Property.PROP_HEIGHT))
        {
            return Integer.valueOf(getVideoItemHeight(videoitem));
        }
        if (property.equals(Property.PROP_ADDED_TIME))
        {
            return Long.valueOf(videoitem.addedTime);
        }
        if (property.equals(Property.PROP_MODIFIED_TIME))
        {
            return Long.valueOf(videoitem.modifiedTime);
        }
        if (property.equals(Property.PROP_TAKEN_TIME))
        {
            return Long.valueOf(videoitem.takenTime);
        }
        if (property.equals(Property.PROP_DURATION))
        {
            return Long.valueOf(videoitem.duration);
        }
        if (property.equals(Property.PROP_MIMETYPE))
        {
            return videoitem.mime_type;
        }
        if (!property.equals(Property.PROP_THUMBNAIL_FILEPATH))
        {
            break; /* Loop/switch isn't completed */
        }
        Uri uri1 = videoitem.uri;
        integer = null;
        if (uri1 != null)
        {
            return videoitem.uri.getPath();
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (property.equals(Property.PROP_IMAGE_FILEPATH))
        {
            Uri uri = videoitem.uri;
            integer = null;
            if (uri != null)
            {
                return videoitem.uri.getPath();
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
            if (mSortProperty.equals(Property.PROP_TAKEN_TIME))
            {
                s = "datetaken";
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

    private static int getVideoItemHeight(VideoItem videoitem)
    {
        validVideoResolution(videoitem);
        return videoitem.height;
    }

    private static int getVideoItemWidth(VideoItem videoitem)
    {
        validVideoResolution(videoitem);
        return videoitem.width;
    }

    private static void validVideoResolution(VideoItem videoitem)
    {
        LocalVideoItem localvideoitem = (LocalVideoItem)videoitem;
        if (!localvideoitem.isWidthHeightValid)
        {
            Bitmap bitmap = VideoLocalDataSource.access$100(localvideoitem.uri.getPath());
            if (bitmap != null)
            {
                localvideoitem.width = bitmap.getWidth();
                localvideoitem.height = bitmap.getHeight();
            }
            localvideoitem.isWidthHeightValid = true;
        }
    }

    public MediaItem buildItem()
    {
        MediaItem mediaitem = createItem();
        fillVideoItem(mCursor, mediaitem);
        return mediaitem;
    }

    public volatile void close()
    {
        super.e();
    }

    protected MediaItem createItem()
    {
        return new LocalVideoItem();
    }

    public int getCount()
    {
        return mCursor.getCount();
    }

    public Object getObjectProp(MediaItem mediaitem, Property property, Object obj)
        throws IllegalArgumentException
    {
        return getObjectPropStatic(mediaitem, property, obj);
    }

    public volatile int getPosition()
    {
        return super.osition();
    }

    String[] getQueryColumns()
    {
        return COLUMNS;
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

    public volatile boolean isAfterLast()
    {
        return super.terLast();
    }

    public volatile boolean isBeforeFirst()
    {
        return super.foreFirst();
    }

    public volatile boolean isFirst()
    {
        return super.rst();
    }

    public volatile boolean isLast()
    {
        return super.st();
    }

    public volatile boolean isOpened()
    {
        return super.ened();
    }

    public volatile boolean move(int i)
    {
        return super.(i);
    }

    public volatile boolean moveToFirst()
    {
        return super.ToFirst();
    }

    public volatile boolean moveToLast()
    {
        return super.ToLast();
    }

    public volatile boolean moveToNext()
    {
        return super.ToNext();
    }

    public volatile boolean moveToPosition(int i)
    {
        return super.ToPosition(i);
    }

    public volatile boolean moveToPrevious()
    {
        return super.ToPrevious();
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
        mCursor = mContentResolver.query(VideoLocalDataSource.access$000(), as, s, as1, getSortOrder());
        if (mCursor == null)
        {
            mCursor = EmptyCursor.EMPTY_CURSOR;
        }
    }

    public volatile mCursor open()
    {
        return super.();
    }

    public  setId(long l)
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
        Property aproperty[] = new Property[6];
        aproperty[0] = Property.PROP_TITLE;
        aproperty[1] = Property.PROP_ADDED_TIME;
        aproperty[2] = Property.PROP_MODIFIED_TIME;
        aproperty[3] = Property.PROP_TAKEN_TIME;
        aproperty[4] = Property.PROP_SIZE;
        aproperty[5] = Property.PROP_DURATION;
        SORT_CAPBILITIES = aproperty;
        Property aproperty1[] = new Property[7];
        aproperty1[0] = Property.PROP_URI;
        aproperty1[1] = Property.PROP_TITLE;
        aproperty1[2] = Property.PROP_SIZE;
        aproperty1[3] = Property.PROP_ADDED_TIME;
        aproperty1[4] = Property.PROP_MODIFIED_TIME;
        aproperty1[5] = Property.PROP_TAKEN_TIME;
        aproperty1[6] = Property.PROP_DURATION;
        SUPPORTED_PROPERTIES = aproperty1;
    }

    public LocalVideoItem.isWidthHeightValid(ContentResolver contentresolver)
    {
        mIsDesc = false;
        mId = null;
        mContentResolver = contentresolver;
    }
}
