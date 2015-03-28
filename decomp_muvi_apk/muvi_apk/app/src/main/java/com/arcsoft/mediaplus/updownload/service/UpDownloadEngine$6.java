// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.service;

import android.net.NetworkInfo;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.updownload.service:
//            UpDownloadEngine

class this._cls0
    implements com.arcsoft.util.os.ectivityChangeListener
{

    final UpDownloadEngine this$0;

    public void OnConnectivityChanged(com.arcsoft.util.os.StateInfo stateinfo)
    {
        if (stateinfo.networkInfo == null || stateinfo.networkInfo.getType() != 1)
        {
            return;
        }
        if (stateinfo.networkInfo.isConnected())
        {
            Log.i("UpDownloadEngine", "OnConnectivityChanged connect");
            UpDownloadEngine.access$2002(UpDownloadEngine.this, true);
            UpDownloadEngine.access$1900(UpDownloadEngine.this).removeMessages(2);
            UpDownloadEngine.access$1900(UpDownloadEngine.this).sendEmptyMessage(2);
            return;
        } else
        {
            Log.i("UpDownloadEngine", "OnConnectivityChanged disconnect");
            UpDownloadEngine.access$2002(UpDownloadEngine.this, false);
            UpDownloadEngine.access$1900(UpDownloadEngine.this).removeMessages(1);
            UpDownloadEngine.access$1900(UpDownloadEngine.this).sendEmptyMessage(1);
            return;
        }
    }

    rviceHandler()
    {
        this$0 = UpDownloadEngine.this;
        super();
    }
}
