// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import java.util.Comparator;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            MediaItem

public class DatePriorityComparator
    implements Comparator
{

    public DatePriorityComparator()
    {
    }

    public int compare(MediaItem mediaitem, MediaItem mediaitem1)
    {
        return mediaitem.addedTime >= mediaitem1.addedTime ? -1 : 1;
    }

    public volatile int compare(Object obj, Object obj1)
    {
        return compare((MediaItem)obj, (MediaItem)obj1);
    }
}
