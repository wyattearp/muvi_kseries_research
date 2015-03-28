// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.network;

import com.arcsoft.util.tool.SafeBuffer;

// Referenced classes of package com.arcsoft.util.network:
//            FileDownloader

private class <init>
{

    private final SafeBuffer mNormalQueue;
    private final SafeBuffer mUrgentQueue;
    final FileDownloader this$0;



    private ()
    {
        this$0 = FileDownloader.this;
        super();
        mUrgentQueue = new SafeBuffer();
        mNormalQueue = new SafeBuffer();
    }

    mNormalQueue(mNormalQueue mnormalqueue)
    {
        this();
    }
}
