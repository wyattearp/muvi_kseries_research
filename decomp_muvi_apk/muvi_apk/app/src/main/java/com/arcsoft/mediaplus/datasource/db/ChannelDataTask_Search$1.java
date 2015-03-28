// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;


// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            ChannelDataTask_Search

class this._cls0
    implements Runnable
{

    final ChannelDataTask_Search this$0;

    public void run()
    {
        try
        {
            join(3000L);
            return;
        }
        catch (InterruptedException interruptedexception)
        {
            interruptedexception.printStackTrace();
        }
    }

    ()
    {
        this$0 = ChannelDataTask_Search.this;
        super();
    }
}
