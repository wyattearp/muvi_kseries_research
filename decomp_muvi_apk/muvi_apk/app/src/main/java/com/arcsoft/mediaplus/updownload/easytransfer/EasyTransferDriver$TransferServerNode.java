// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;


// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferDriver

private class <init>
{

    boolean addtodownloadqueue;
    int current;
    boolean enableAllow;
    boolean enablePlugIn;
    int recordDay;
    String servername;
    int serverstate;
    String serverudn;
    int succuss;
    long tableid;
    final EasyTransferDriver this$0;
    int total;

    private Y()
    {
        this$0 = EasyTransferDriver.this;
        super();
        recordDay = 0;
        enableAllow = false;
        enablePlugIn = false;
        total = 0;
        current = 0;
        succuss = 0;
        addtodownloadqueue = false;
    }

    addtodownloadqueue(addtodownloadqueue addtodownloadqueue1)
    {
        this();
    }
}
