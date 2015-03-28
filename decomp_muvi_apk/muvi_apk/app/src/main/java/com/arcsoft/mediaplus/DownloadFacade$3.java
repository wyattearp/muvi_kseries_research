// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.content.DialogInterface;
import android.os.Handler;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus:
//            DownloadFacade

class this._cls0
    implements android.content.nClickListener
{

    final DownloadFacade this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        mOverageTaskCount = downloadAll(mNeedToDownloadTasks);
        Log.i("zdf", (new StringBuilder()).append("*** [DownloadFacade] showDownloadConfirmDlg, mOverageTaskCount = ").append(mOverageTaskCount).toString());
        if (mOverageTaskCount > 0)
        {
            mHandler.sendEmptyMessage(3);
        } else
        if (mOverageTaskCount == 0)
        {
            toastDownloadFailedIfNeeded();
            return;
        }
    }

    Listener()
    {
        this$0 = DownloadFacade.this;
        super();
    }
}
