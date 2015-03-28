// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;

import android.net.NetworkInfo;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferDriver

class this._cls0
    implements com.arcsoft.util.os.tivityChangeListener
{

    final EasyTransferDriver this$0;

    public void OnConnectivityChanged(com.arcsoft.util.os.ateInfo ateinfo)
    {
        while (ateinfo.networkInfo == null || ateinfo.networkInfo.getType() != 1 || mFinished || EasyTransferDriver.access$1500(EasyTransferDriver.this) == null) 
        {
            return;
        }
        if (ateinfo.networkInfo.isConnected())
        {
            Log.i("EasyTransferDriver", "OnConnectivityChanged connect");
            EasyTransferDriver.access$1102(EasyTransferDriver.this, true);
            EasyTransferDriver.access$1500(EasyTransferDriver.this).removeMessages(3);
            EasyTransferDriver.access$1500(EasyTransferDriver.this).sendEmptyMessage(3);
            return;
        } else
        {
            Log.i("EasyTransferDriver", "OnConnectivityChanged disconnect");
            EasyTransferDriver.access$1102(EasyTransferDriver.this, false);
            EasyTransferDriver.access$1500(EasyTransferDriver.this).removeMessages(3);
            EasyTransferDriver.access$1500(EasyTransferDriver.this).sendEmptyMessage(3);
            return;
        }
    }

    iverHandler()
    {
        this$0 = EasyTransferDriver.this;
        super();
    }
}
