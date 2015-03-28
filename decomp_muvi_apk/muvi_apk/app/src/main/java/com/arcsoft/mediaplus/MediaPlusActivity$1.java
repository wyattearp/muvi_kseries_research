// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity

class this._cls0
    implements com.arcsoft.util.tool.eleteList
{

    final MediaPlusActivity this$0;

    public void delete(com.arcsoft.util.tool.a a)
    {
        if (mUpDownloadEngine != null && a != null)
        {
            mUpDownloadEngine.deleteDownloadedRecode(a.filePath);
        }
    }

    a()
    {
        this$0 = MediaPlusActivity.this;
        super();
    }
}
