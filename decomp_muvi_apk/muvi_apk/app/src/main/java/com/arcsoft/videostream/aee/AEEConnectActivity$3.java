// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.net.NetworkInfo;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEConnectActivity

class this._cls0
    implements com.arcsoft.util.os.tivityChangeListener
{

    final AEEConnectActivity this$0;

    public void OnConnectivityChanged(com.arcsoft.util.os.ateInfo ateinfo)
    {
        if (ateinfo.networkInfo != null && ateinfo.networkInfo.getType() == 1)
        {
            Log.e("AEEConnectActivity", (new StringBuilder()).append("OnConnectivityChanged  ---------- info.networkInfo.isConnected() = ").append(ateinfo.networkInfo.isConnected()).toString());
            isWifiConnected = ateinfo.networkInfo.isConnected();
            if (!isWifiConnected)
            {
                AEEConnectActivity.access$200(AEEConnectActivity.this);
                return;
            }
        }
    }

    angeListener()
    {
        this$0 = AEEConnectActivity.this;
        super();
    }
}
