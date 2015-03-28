// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;


// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferTimer

public static class serverState
{

    long index;
    long retryCount;
    int retryCurrent;
    int serverState;
    String serverudn;

    public ()
    {
        index = -1L;
        serverudn = null;
        retryCount = 0L;
        retryCurrent = 0;
        serverState = 0;
    }
}
