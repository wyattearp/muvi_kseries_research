// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import com.arcsoft.mediaplus.datasource.Property;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            DmcPlayback, DmcPlayingListAdapter

class this._cls0
    implements com.arcsoft.mediaplus.datasource.DataChangeListener
{

    final DmcPlayback this$0;

    public void onCountChanged(int i, int j)
    {
        Log.v("zdf", (new StringBuilder()).append("**** [DmcPlayback] onCountChanged: oldCount ").append(j).append(" newCount ").append(i).toString());
        if (DmcPlayback.access$100(DmcPlayback.this) != null)
        {
            DmcPlayback.access$100(DmcPlayback.this).notifyDataSetChanged();
        }
    }

    public void onDataChanged(int i, Property property)
    {
    }

    y()
    {
        this$0 = DmcPlayback.this;
        super();
    }
}
