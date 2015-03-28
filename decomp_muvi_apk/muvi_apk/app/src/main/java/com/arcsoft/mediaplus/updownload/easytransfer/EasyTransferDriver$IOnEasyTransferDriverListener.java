// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;

import java.util.List;

// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferDriver

public static interface 
{

    public abstract int onCancel(List list, String s, long l);

    public abstract boolean onDownload(List list, String s, long l);

    public abstract boolean onIsUpDownloadIdle();
}
