// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.network;

import java.util.Comparator;

// Referenced classes of package com.arcsoft.util.network:
//            FileDownloader

class this._cls0
    implements Comparator
{

    final FileDownloader this$0;

    public int compare(wnloadRequest wnloadrequest, wnloadRequest wnloadrequest1)
    {
        return wnloadrequest.requestcode != wnloadrequest1.requestcode || !wnloadrequest.url.equals(wnloadrequest1.url) ? 1 : 0;
    }

    public volatile int compare(Object obj, Object obj1)
    {
        return compare((wnloadRequest)obj, (wnloadRequest)obj1);
    }

    wnloadRequest()
    {
        this$0 = FileDownloader.this;
        super();
    }
}
