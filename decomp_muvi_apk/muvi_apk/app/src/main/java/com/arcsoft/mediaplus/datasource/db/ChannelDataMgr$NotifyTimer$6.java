// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;


// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            ChannelDataMgr

class val.fserverudn
    implements Runnable
{

    final val.fserverudn this$1;
    final boolean val$fmount;
    final String val$fserverudn;

    public void run()
    {
        ChannelDataMgr.access$400(_fld0, val$fmount, val$fserverudn);
    }

    ()
    {
        this$1 = final_;
        val$fmount = flag;
        val$fserverudn = String.this;
        super();
    }
}
