// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import com.arcsoft.mediaplus.datasource.Property;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            DmcRemotePrefetcher, DmcPlayingDataProvider

class this._cls0
    implements com.arcsoft.mediaplus.datasource.geListener
{

    final DmcRemotePrefetcher this$0;

    public void onCountChanged(int i, int j)
    {
        if (i != 0)
        {
            prefetch(DmcRemotePrefetcher.access$000(DmcRemotePrefetcher.this).getCurrentIndex());
        }
    }

    public void onDataChanged(int i, Property property)
    {
        if (property == Property.PROP_THUMBNAIL_FILEPATH)
        {
            int j = DmcRemotePrefetcher.access$000(DmcRemotePrefetcher.this).getListIndex(i);
            Log.d("DmcDecoder", (new StringBuilder()).append("onDataChanged:  index = ").append(i).append(" listIndex = ").append(j).toString());
            if (DmcRemotePrefetcher.access$100(DmcRemotePrefetcher.this) != null && -1 != j)
            {
                DmcRemotePrefetcher.access$100(DmcRemotePrefetcher.this).prefetchDone(j);
            }
        }
    }

    refetchDone()
    {
        this$0 = DmcRemotePrefetcher.this;
        super();
    }
}
