// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.content.ContentResolver;
import java.util.List;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            ImageItem, VideoItem, AudioItem, MixDataSource, 
//            MediaItem, Property

protected static class mContentResolver extends lder
{
    class MixMediaItem extends MediaItem
    {

        int index;
        MediaItem item;
        final MixDataSource.MixItemBuilder this$0;

        MixMediaItem()
        {
            this$0 = MixDataSource.MixItemBuilder.this;
            super();
        }
    }


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
            return mBuilder.getObjectPropStatic(mediaitem, property, obj);
        }
        if (mediaitem instanceof VideoItem)
        {
            return mBuilder.getObjectPropStatic(mediaitem, property, obj);
        }
        if (mediaitem instanceof AudioItem)
        {
            return mBuilder.getObjectPropStatic(contentresolver, mediaitem, property, obj);
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
        MixMediaItem mixmediaitem1 = (MixMediaItem.item)mMixItemList.get(mixmediaitem.index);
        mixmediaitem1.index;
        JVM INSTR tableswitch 0 2: default 64
    //                   0 74
    //                   1 147
    //                   2 174;
           goto _L3 _L4 _L5 _L6
_L3:
        throw new UnsupportedOperationException("Unsupported Media Type!!!");
_L4:
        r r = (new mBuilder(mContentResolver)).setId(mixmediaitem1.setId).open();
_L8:
        if (r != null)
        {
            if (r.moveToFirst())
            {
                mixmediaitem.item = r.buildItem();
            }
            r.close();
        }
_L2:
        return getObjectPropStatic(mContentResolver, mixmediaitem.item, property, obj);
_L5:
        r = (new mBuilder(mContentResolver)).setId(mixmediaitem1.setId).open();
        continue; /* Loop/switch isn't completed */
_L6:
        r = (new mBuilder(mContentResolver)).setId(mixmediaitem1.setId).open();
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

    public MixMediaItem.this._cls0(ContentResolver contentresolver, List list)
    {
        mMixItemList = list;
        mContentResolver = contentresolver;
    }
}
