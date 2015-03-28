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

class orkStateInfo
    implements Runnable
{

    final val.tmpinfo this$1;
    final orkStateInfo val$tmpinfo;

    public void run()
    {
        if (NetworkTool.access$100(_fld0) != null)
        {
            NetworkTool.access$100(_fld0).OnConnectivityChanged(val$tmpinfo);
        }
    }

    orkStateInfo()
    {
        this$1 = final_orkstateinfo;
        val$tmpinfo = orkStateInfo.this;
        super();
    }

    // Unreferenced inner class com/arcsoft/util/os/NetworkTool$1

/* anonymous class */
    class NetworkTool._cls1 extends BroadcastReceiver
    {

        final NetworkTool this$0;

        public void onReceive(Context context, Intent intent)
        {
            NetworkTool.NetworkStateInfo networkstateinfo;
            String s;
            networkstateinfo = new NetworkTool.NetworkStateInfo(NetworkTool.this);
            s = intent.getAction();
            if (!s.equals("android.net.wifi.WIFI_STATE_CHANGED")) goto _L2; else goto _L1
_L1:
            if (SystemUtils.getSDKVersion() > 10 || intent.getIntExtra("wifi_state", 1) != 1) goto _L4; else goto _L3
_L3:
            networkstateinfo.noconnectivity = intent.getBooleanExtra("noConnectivity", false);
            networkstateinfo.failover = intent.getBooleanExtra("isFailover", false);
            networkstateinfo.networkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getNetworkInfo(1);
_L6:
            if (networkstateinfo.networkInfo == null || !networkstateinfo.networkInfo.getState().equals(android.net.NetworkInfo.State.CONNECTING))
            {
                break; /* Loop/switch isn't completed */
            }
_L4:
            return;
_L2:
            if (s.equals("android.net.wifi.STATE_CHANGE"))
            {
                networkstateinfo.noconnectivity = intent.getBooleanExtra("noConnectivity", false);
                networkstateinfo.failover = intent.getBooleanExtra("isFailover", false);
                networkstateinfo.networkInfo = (NetworkInfo)intent.getParcelableExtra("networkInfo");
            }
            if (true) goto _L6; else goto _L5
_L5:
            if (NetworkTool.access$000(NetworkTool.this) == null)
            {
                NetworkTool.access$002(NetworkTool.this, new Handler());
            }
            NetworkTool.access$000(NetworkTool.this).post(networkstateinfo. new NetworkTool._cls1._cls1());
            return;
        }

            
            {
                this$0 = NetworkTool.this;
                super();
            }
    }

}
