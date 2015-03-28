// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop;

import android.net.Uri;

// Referenced classes of package com.arcsoft.workshop:
//            SaveThreadForShare

private class <init>
{

    boolean bNoSdCard;
    boolean needShare;
    final SaveThreadForShare this$0;
    Uri uri;
    int what;

    private ()
    {
        this$0 = SaveThreadForShare.this;
        super();
        uri = null;
        needShare = false;
        bNoSdCard = false;
        what = 0;
    }

    what(what what1)
    {
        this();
    }
}
