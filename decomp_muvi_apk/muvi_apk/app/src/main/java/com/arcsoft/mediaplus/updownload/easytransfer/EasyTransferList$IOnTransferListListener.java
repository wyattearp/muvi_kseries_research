// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;

import java.util.List;

// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferList

public static interface 
{

    public abstract boolean onBuildList(List list,  , String s, long l);

    public abstract void onDestroyList(List list, String s, long l);

    public abstract boolean onTransferList(List list,  , String s, long l);
}
