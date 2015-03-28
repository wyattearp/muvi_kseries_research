// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.os;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import com.arcsoft.util.SystemUtils;

// Referenced classes of package com.arcsoft.util.os:
//            NetworkTool

class <init> extends BroadcastReceiver
{

    final NetworkTool this$0;

    public void onReceive(Context context, Intent intent)
    {
        final tworkStateInfo tmpinfo;
        String s;
        tmpinfo = new tworkStateInfo(NetworkTool.this);
        s = intent.getAction();
        if (!s.equals("android.net.wifi.WIFI_STATE_CHANGED")) goto _L2; else goto _L1
_L1:
        if (SystemUtils.getSDKVersion() > 10 || intent.getIntExtra("wifi_state", 1) != 1) goto _L4; else goto _L3
_L3:
        tmpinfo.noconnectivity = intent.getBooleanExtra("noConnectivity", false);
        tmpinfo.failover = intent.getBooleanExtra("isFailover", false);
        tmpinfo.networkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getNetworkInfo(1);
_L6:
        if (tmpinfo.networkInfo == null || !tmpinfo.networkInfo.getState().equals(android.net.ate.CONNECTING))
        {
            break; /* Loop/switch isn't completed */
        }
_L4:
        return;
_L2:
        if (s.equals("android.net.wifi.STATE_CHANGE"))
        {
            tmpinfo.noconnectivity = intent.getBooleanExtra("noConnectivity", false);
            tmpinfo.failover = intent.getBooleanExtra("isFailover", false);
            tmpinfo.networkInfo = (NetworkInfo)intent.getParcelableExtra("networkInfo");
        }
        if (true) goto _L6; else goto _L5
_L5:
        if (NetworkTool.access$000(NetworkTool.this) == null)
        {
            NetworkTool.access$002(NetworkTool.this, new Handler());
        }
        NetworkTool.access$000(NetworkTool.this).post(new Runnable() {

            final NetworkTool._cls1 this$1;
            final NetworkTool.NetworkStateInfo val$tmpinfo;

            public void run()
            {
                if (NetworkTool.access$100(this$0) != null)
                {
                    NetworkTool.access$100(this$0).OnConnectivityChanged(tmpinfo);
                }
            }

            
            {
                this$1 = NetworkTool._cls1.this;
                tmpinfo = networkstateinfo;
                super();
            }
        });
        return;
    }

    _cls1.val.tmpinfo()
    {
        this$0 = NetworkTool.this;
        super();
    }
}
