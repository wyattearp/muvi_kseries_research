// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;


// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferTimer

public static class retryCount
{

    long index;
    int retryCount;
    int retryPeriod;
    String serverudn;
    int startHour;
    int startMinute;

    public ()
    {
        index = -1L;
        serverudn = null;
        startHour = 0;
        startMinute = 0;
        retryPeriod = 0x1d4c0;
        retryCount = 5;
    }
}
