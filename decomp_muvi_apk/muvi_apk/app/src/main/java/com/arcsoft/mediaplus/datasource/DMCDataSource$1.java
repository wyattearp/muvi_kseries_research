// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;


// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            DMCDataSource, Property

class this._cls0
    implements taChangeListener
{

    final DMCDataSource this$0;

    public void onCountChanged(int i, int j)
    {
        notifyOnCountChanged(i, j);
    }

    public void onDataChanged(int i, Property property)
    {
        notifyOnDataChanged(i, property);
    }

    taChangeListener()
    {
        this$0 = DMCDataSource.this;
        super();
    }
}
