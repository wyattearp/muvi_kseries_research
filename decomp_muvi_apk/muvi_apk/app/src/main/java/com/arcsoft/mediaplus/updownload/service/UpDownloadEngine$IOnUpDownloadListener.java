// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.service;


// Referenced classes of package com.arcsoft.mediaplus.updownload.service:
//            UpDownloadEngine

public static interface 
{

    public abstract void onProgress(String s, String s1, long l, long l1);

    public abstract void onUpDownloadFinish(String s, String s1, Object obj, int i);

    public abstract void onUpDownloadStart(String s, String s1);
}
