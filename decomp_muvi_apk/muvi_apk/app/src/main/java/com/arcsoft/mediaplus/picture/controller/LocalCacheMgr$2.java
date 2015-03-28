// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Process;
import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.datasource.Property;
import com.arcsoft.mediaplus.picture.data.LocalCacheProvider;

// Referenced classes of package com.arcsoft.mediaplus.picture.controller:
//            LocalCacheMgr

class this._cls0 extends Thread
{

    final LocalCacheMgr this$0;

    public void run()
    {
        int i;
        int j;
        Intent intent = new Intent("com.arcsoft.picture.sync_cache_begin");
        LocalCacheMgr.access$000(LocalCacheMgr.this).sendBroadcast(intent);
        Process.setThreadPriority(10);
        i = mDataSource.getCount();
        j = 0;
_L3:
        if (j >= i || LocalCacheMgr.access$100(LocalCacheMgr.this))
        {
            LocalCacheMgr.access$400(LocalCacheMgr.this).removeInvalidFiles();
            LocalCacheMgr.access$102(LocalCacheMgr.this, false);
            Intent intent1 = new Intent("com.arcsoft.picture.sync_cache_end");
            LocalCacheMgr.access$000(LocalCacheMgr.this).sendBroadcast(intent1);
            return;
        }
        if (!LocalCacheMgr.access$200(LocalCacheMgr.this))
        {
            break MISSING_BLOCK_LABEL_133;
        }
        Object obj = LocalCacheMgr.access$300(LocalCacheMgr.this);
        obj;
        JVM INSTR monitorenter ;
        LocalCacheMgr.access$300(LocalCacheMgr.this).wait();
_L1:
        Exception exception;
        InterruptedException interruptedexception;
        if (j < mDataSource.getCount())
        {
            MediaItem mediaitem = mDataSource.getItem(j);
            if (!LocalCacheMgr.access$400(LocalCacheMgr.this).isThumbCached(mediaitem))
            {
                if (LocalCacheMgr.access$500(LocalCacheMgr.this))
                {
                    mediaitem.strDecodePath = mDataSource.getStringProp(j, Property.PROP_THUMBNAIL_FILEPATH, null);
                }
                Bitmap bitmap = LocalCacheMgr.access$400(LocalCacheMgr.this).createThumbnail(mediaitem, 128, 128);
                if (bitmap != null)
                {
                    android.opengl.ture ture = LocalCacheMgr.access$400(LocalCacheMgr.this).getETC1Texture(bitmap);
                    LocalCacheMgr.access$400(LocalCacheMgr.this).saveETCToDatabase(mediaitem, ture);
                    bitmap.recycle();
                }
            }
        }
        j++;
        continue; /* Loop/switch isn't completed */
        interruptedexception;
        interruptedexception.printStackTrace();
          goto _L1
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        if (true) goto _L3; else goto _L2
_L2:
    }

    (String s)
    {
        this$0 = LocalCacheMgr.this;
        super(s);
    }
}
