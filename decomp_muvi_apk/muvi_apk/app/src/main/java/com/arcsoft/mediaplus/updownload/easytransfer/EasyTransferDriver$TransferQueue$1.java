// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;

import java.util.Comparator;

// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferDriver

class this._cls1
    implements Comparator
{

    final de this$1;

    public int compare(de de, de de1)
    {
        return !de.serverudn.equalsIgnoreCase(de1.serverudn) ? -1 : 0;
    }

    public volatile int compare(Object obj, Object obj1)
    {
        return compare((de)obj, (de)obj1);
    }

    de()
    {
        this$1 = this._cls1.this;
        super();
    }
}
