// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import com.arcsoft.mediaplus.datasource.Property;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            DataSourcePlayList

class this._cls0
    implements com.arcsoft.mediaplus.datasource.ngeListener
{

    final DataSourcePlayList this$0;

    public void onCountChanged(int i, int j)
    {
        DataSourcePlayList.access$000(DataSourcePlayList.this);
        DataSourcePlayList.access$100(DataSourcePlayList.this);
    }

    public void onDataChanged(int i, Property property)
    {
    }

    hangeListener()
    {
        this$0 = DataSourcePlayList.this;
        super();
    }
}
