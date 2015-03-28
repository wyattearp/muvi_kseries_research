// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.content.ContentResolver;
import android.database.ContentObserver;
import com.arcsoft.util.tool.DoubleBufferList;
import java.util.List;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsLocalDataSource, MediaItem, Property, ImageItem, 
//            VideoItem, AudioItem

public class MixDataSource extends AbsLocalDataSource
{
    public static class MixItem
    {

        public long id;
        public int type;

        public MixItem()
        {
        }

        public MixItem(int i, long l)
        {
            type = i;
            id = l;
        }
    }

    protected static class MixItemBuilder extends AbsLocalDataSource.AbsItemBuilder
    {

        private final ContentResolver mContentResolver;
        private final List mMixItemList;

        private MediaItem createItem()
        {
            return new MixMediaItem();
        }

        private void fillItem(MediaItem mediaitem)
        {
            ((MixMediaItem)mediaitem).index = getPosition();
        }

        public static Object getObjectPropStatic(ContentResolver contentresolver, MediaItem mediaitem, Property property, Object obj)
            throws IllegalArgumentException
        {
            if (mediaitem instanceof ImageItem)
            {
                return ImageLocalDataSource.ImageItemBuilder.getObjectPropStatic(mediaitem, property, obj);
            }
            if (mediaitem instanceof VideoItem)
            {
                return VideoLocalDataSource.VideoItemBuilder.getObjectPropStatic(mediaitem, property, obj);
            }
            if (mediaitem instanceof AudioItem)
            {
                return AudioLocalDataSource.AudioItemBuilder.getObjectPropStatic(contentresolver, mediaitem, property, obj);
            } else
            {
                throw new IllegalArgumentException("Invalid input MediaItem");
            }
        }

        public MediaItem buildItem()
        {
            MediaItem mediaitem = createItem();
            fillItem(mediaitem);
            return mediaitem;
        }

        public int getCount()
        {
            return mMixItemList.size();
        }

        public Object getObjectProp(MediaItem mediaitem, Property property, Object obj)
            throws IllegalArgumentException
        {
            MixMediaItem mixmediaitem = (MixMediaItem)mediaitem;
            if (mixmediaitem.item != null) goto _L2; else goto _L1
_L1:
            MixItem mixitem = (MixItem)mMixItemList.get(mixmediaitem.index);
            mixitem.type;
            JVM INSTR tableswitch 0 2: default 64
        //                       0 74
        //                       1 147
        //                       2 174;
               goto _L3 _L4 _L5 _L6
_L3:
            throw new UnsupportedOperationException("Unsupported Media Type!!!");
_L4:
            AbsLocalDataSource.ItemBuilder itembuilder = (new ImageLocalDataSource.ImageItemBuilder(mContentResolver)).setId(mixitem.id).open();
_L8:
            if (itembuilder != null)
            {
                if (itembuilder.moveToFirst())
                {
                    mixmediaitem.item = itembuilder.buildItem();
                }
                itembuilder.close();
            }
_L2:
            return getObjectPropStatic(mContentResolver, mixmediaitem.item, property, obj);
_L5:
            itembuilder = (new VideoLocalDataSource.VideoItemBuilder(mContentResolver)).setId(mixitem.id).open();
            continue; /* Loop/switch isn't completed */
_L6:
            itembuilder = (new AudioLocalDataSource.AudioItemBuilder(mContentResolver)).setId(mixitem.id).open();
            if (true) goto _L8; else goto _L7
_L7:
        }

        protected void onClose()
        {
        }

        protected boolean onMove(int i, int j)
        {
            return true;
        }

        protected void onOpen()
        {
        }

        public MixItemBuilder(ContentResolver contentresolver, List list)
        {
            mMixItemList = list;
            mContentResolver = contentresolver;
        }
    }

    class MixItemBuilder.MixMediaItem extends MediaItem
    {

        int index;
        MediaItem item;
        final MixItemBuilder this$0;

        MixItemBuilder.MixMediaItem()
        {
            this$0 = MixItemBuilder.this;
            super();
        }
    }


    public static final int MEDIATYPE_AUDIO = 2;
    public static final int MEDIATYPE_IMAGE = 0;
    public static final int MEDIATYPE_VIDEO = 1;
    private static final String TAG = "MixDataSource";
    private final MixItemBuilder mItemBuilder;
    private final List mMixItemList;

    public MixDataSource(ContentResolver contentresolver, List list)
    {
        super(contentresolver);
        mMixItemList = list;
        mItemBuilder = new MixItemBuilder(mContentResolver, mMixItemList);
    }

    public Object getObjectProp(int i, Property property, Object obj)
    {
        Object obj1;
        try
        {
            MediaItem mediaitem = (MediaItem)mList.get(i);
            obj1 = mItemBuilder.getObjectProp(mediaitem, property, obj);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            return super.getObjectProp(i, property, obj);
        }
        return obj1;
    }

    public void init()
    {
        super.init();
    }

    protected AbsLocalDataSource.ItemBuilder openItemBuilder()
    {
        return mItemBuilder.open();
    }

    protected void registerContentObserver(ContentObserver contentobserver)
    {
    }

    public void unInit()
    {
        super.unInit();
    }

    protected void unRegisterContentObserver(ContentObserver contentobserver)
    {
    }
}
