// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.content.DialogInterface;
import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity

class this._cls0
    implements android.content.ckListener
{

    final MediaPlusActivity this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        if (mUpDownloadEngine != null)
        {
            mUpDownloadEngine.cancelAllTask();
        }
        dialoginterface.dismiss();
        setResult(MediaPlusActivity.access$3500(MediaPlusActivity.this));
        finish();
    }

    DownloadEngine()
    {
        this$0 = MediaPlusActivity.this;
        super();
    }
}
