// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import java.util.Comparator;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            RemoteDateStringItem, MediaItem

public class RemotePriorityComparator
    implements Comparator
{

    public RemotePriorityComparator()
    {
    }

    public int compare(MediaItem mediaitem, MediaItem mediaitem1)
    {
        RemoteDateStringItem remotedatestringitem = (RemoteDateStringItem)mediaitem;
        RemoteDateStringItem remotedatestringitem1 = (RemoteDateStringItem)mediaitem1;
        return remotedatestringitem._id <= remotedatestringitem1._id ? -1 : 1;
    }

    public volatile int compare(Object obj, Object obj1)
    {
        return compare((MediaItem)obj, (MediaItem)obj1);
    }
}
