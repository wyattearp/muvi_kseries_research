// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;


// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            MatrixDataSource

class this._cls0
    implements uildListener
{

    final MatrixDataSource this$0;

    public void onDataBuiltBegin()
    {
        NotifyOnDataBuiltBegin();
    }

    public void onDataBuiltFinish()
    {
        NotifyOnDataBuiltFinish();
    }

    uildListener()
    {
        this$0 = MatrixDataSource.this;
        super();
    }
}
