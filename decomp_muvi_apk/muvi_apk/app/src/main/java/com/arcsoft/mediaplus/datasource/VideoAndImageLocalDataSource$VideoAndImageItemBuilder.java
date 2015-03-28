// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.database.Cursor;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            ImageItem, VideoAndImageLocalDataSource, MediaItem, Property

protected class mCurrentCursorImage extends mCurrentCursorImage
{

    boolean mCurrentCursorImage;
    final VideoAndImageLocalDataSource this$0;

    public MediaItem buildItem()
    {
        if (mCurrentCursorImage)
        {
            ImageItem imageitem = new ImageItem();
            mCurrentCursorImage(mImageCursor, imageitem);
            return imageitem;
        } else
        {
            mCurrentCursorImage mcurrentcursorimage = new init>();
            init>(mVideoCursor, mcurrentcursorimage);
            return mcurrentcursorimage;
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
            obj = tic(mediaitem, property, obj);
        } else
        if (mediaitem.mime_type.contains("image"))
        {
            return tic(mediaitem, property, obj);
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

    public queryDownloadImage setId(long l)
    {
        mId = Long.valueOf(l);
        return this;
    }

    public queryDownloadImage setSortOrder(Property property, boolean flag)
    {
        mSortProperty = property;
        mIsDesc = flag;
        return this;
    }

    protected ()
    {
        this$0 = VideoAndImageLocalDataSource.this;
        super();
        mCurrentCursorImage = false;
    }
}
