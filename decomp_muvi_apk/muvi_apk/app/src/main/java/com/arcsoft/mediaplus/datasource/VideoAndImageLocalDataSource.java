// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.util.tool.DoubleBufferList;
import com.arcsoft.util.tool.EmptyCursor;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsLocalDataSource, MediaItem, DatePriorityComparator, Property, 
//            ImageItem

public class VideoAndImageLocalDataSource extends AbsLocalDataSource
    implements DataSourceFactory.IProduct
{
    protected class VideoAndImageItemBuilder extends AbsLocalDataSource.AbsItemBuilder
    {

        boolean mCurrentCursorImage;
        final VideoAndImageLocalDataSource this$0;

        public MediaItem buildItem()
        {
            if (mCurrentCursorImage)
            {
                ImageItem imageitem = new ImageItem();
                ImageLocalDataSource.ImageItemBuilder.fillImageItem(mImageCursor, imageitem);
                return imageitem;
            } else
            {
                VideoLocalDataSource.VideoItemBuilder.LocalVideoItem localvideoitem = new VideoLocalDataSource.VideoItemBuilder.LocalVideoItem();
                VideoLocalDataSource.VideoItemBuilder.fillVideoItem(mVideoCursor, localvideoitem);
                return localvideoitem;
            }
        }

        public int getCount()
        {
            if (mVideoCursor == null || mImageCursor == null)
            {
                return 0;
            } else
            {
                return mVideoCursor.getCount() + mImageCursor.getCount();
            }
        }

        public Object getObjectProp(MediaItem mediaitem, Property property, Object obj)
            throws IllegalArgumentException
        {
            if (mediaitem.mime_type.contains("video"))
            {
                obj = VideoLocalDataSource.VideoItemBuilder.getObjectPropStatic(mediaitem, property, obj);
            } else
            if (mediaitem.mime_type.contains("image"))
            {
                return ImageLocalDataSource.ImageItemBuilder.getObjectPropStatic(mediaitem, property, obj);
            }
            return obj;
        }

        protected void onClose()
        {
            if (mVideoCursor != null)
            {
                mVideoCursor.close();
                mVideoCursor = null;
            }
            if (mImageCursor != null)
            {
                mImageCursor.close();
                mImageCursor = null;
            }
        }

        protected boolean onMove(int i, int j)
        {
            if (mVideoCursor != null && mImageCursor != null)
            {
                mCurrentCursorImage = mImageCursor.moveToPosition(j);
                boolean flag = mCurrentCursorImage;
                boolean flag1 = false;
                if (!flag)
                {
                    int k = j - mImageCursor.getCount();
                    flag1 = mVideoCursor.moveToPosition(k);
                }
                if (mCurrentCursorImage || flag1)
                {
                    return true;
                }
            }
            return false;
        }

        protected void onOpen()
        {
            onClose();
            mVideoCursor = queryDownloadVideo();
            mImageCursor = queryDownloadImage();
        }

        Cursor queryDownloadImage()
        {
            return VideoAndImageLocalDataSource.queryDownloadImageCursor(mContentResolver);
        }

        Cursor queryDownloadVideo()
        {
            return VideoAndImageLocalDataSource.queryDownloadVideoCursor(mContentResolver);
        }

        public VideoAndImageItemBuilder setId(long l)
        {
            mId = Long.valueOf(l);
            return this;
        }

        public VideoAndImageItemBuilder setSortOrder(Property property, boolean flag)
        {
            mSortProperty = property;
            mIsDesc = flag;
            return this;
        }

        protected VideoAndImageItemBuilder()
        {
            this$0 = VideoAndImageLocalDataSource.this;
            super();
            mCurrentCursorImage = false;
        }
    }


    private static final Uri IMAGE_URI;
    private static final String TAG = "VideoAndImageLocalDataSource";
    private static final Uri VIDEO_URI;
    Long mId;
    Cursor mImageCursor;
    private final VideoAndImageItemBuilder mItemBuilder = new VideoAndImageItemBuilder();
    Cursor mVideoCursor;

    public VideoAndImageLocalDataSource(ContentResolver contentresolver)
    {
        super(contentresolver);
        mVideoCursor = null;
        mImageCursor = null;
        mId = null;
        if (contentresolver != null);
    }

    public static Cursor queryDownloadImageCursor(ContentResolver contentresolver)
    {
        Cursor cursor;
        if (contentresolver == null)
        {
            cursor = null;
        } else
        {
            String as[] = ImageLocalDataSource.ImageItemBuilder.COLUMNS;
            String s = (new StringBuilder()).append("_data like '").append(OEMConfig.DOWNLOAD_PATH).append("%'").toString();
            cursor = contentresolver.query(IMAGE_URI, as, s, null, "date_added desc");
            if (cursor == null)
            {
                return EmptyCursor.EMPTY_CURSOR;
            }
        }
        return cursor;
    }

    public static Cursor queryDownloadVideoCursor(ContentResolver contentresolver)
    {
        Cursor cursor;
        if (contentresolver == null)
        {
            cursor = null;
        } else
        {
            String as[] = VideoLocalDataSource.VideoItemBuilder.COLUMNS;
            String s = (new StringBuilder()).append("_data like '").append(OEMConfig.DOWNLOAD_PATH).append("%'").toString();
            cursor = contentresolver.query(VIDEO_URI, as, s, null, "date_added desc");
            if (cursor == null)
            {
                return EmptyCursor.EMPTY_CURSOR;
            }
        }
        return cursor;
    }

    public void create()
    {
        super.init();
    }

    public void destory()
    {
        super.unInit();
    }

    public Object getObjectProp(int i, Property property, Object obj)
    {
        MediaItem mediaitem;
        Object obj1;
        try
        {
            mediaitem = (MediaItem)mList.get(i);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            return super.getObjectProp(i, property, obj);
        }
        if (mediaitem == null)
        {
            return null;
        }
        obj1 = mItemBuilder.getObjectProp(mediaitem, property, obj);
        return obj1;
    }

    protected AbsLocalDataSource.ItemBuilder openItemBuilder()
    {
        return mItemBuilder.setSortOrder(mSortProperty, mIsDesc).open();
    }

    public void quickIndexSort(List list)
    {
        if (list == null)
        {
            return;
        }
        try
        {
            Collections.sort(list, new DatePriorityComparator());
            return;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    protected void registerContentObserver(ContentObserver contentobserver)
    {
        mContentResolver.registerContentObserver(IMAGE_URI, true, contentobserver);
        mContentResolver.registerContentObserver(VIDEO_URI, true, contentobserver);
    }

    protected void unRegisterContentObserver(ContentObserver contentobserver)
    {
        mContentResolver.unregisterContentObserver(contentobserver);
    }

    static 
    {
        IMAGE_URI = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        VIDEO_URI = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
    }
}
