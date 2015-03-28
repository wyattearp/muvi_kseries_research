// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import com.arcsoft.adk.image.ThumbEngine;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            PictureDataSource

class this._cls0
    implements com.arcsoft.adk.image.ineListener
{

    final PictureDataSource this$0;

    public void onThumbReady(ThumbEngine thumbengine, int i)
    {
        notifyOnDataChanged(i, PictureDataSource.PROP_BITMAP);
    }

    ()
    {
        this$0 = PictureDataSource.this;
        super();
    }
}
