// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.content.DialogInterface;
import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;

// Referenced classes of package com.arcsoft.mediaplus:
//            DownloadFacade

class this._cls0
    implements android.content.nClickListener
{

    final DownloadFacade this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        if (mUpDownloadEngine != null)
        {
            mUpDownloadEngine.unregisterUpDownloadListener(DownloadFacade.this);
        }
    }

    e.UpDownloadEngine()
    {
        this$0 = DownloadFacade.this;
        super();
    }
}
