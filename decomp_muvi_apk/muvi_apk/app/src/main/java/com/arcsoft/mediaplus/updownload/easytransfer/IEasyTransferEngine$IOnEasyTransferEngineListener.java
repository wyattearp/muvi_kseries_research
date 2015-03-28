// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;


// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            IEasyTransferEngine

public static interface 
{

    public abstract void onEasyTransferFinish(String s, long l);

    public abstract void onEasyTransferStart(String s, long l, int i);

    public abstract void onEasyTransfering(String s, long l, int i, int j);
}
