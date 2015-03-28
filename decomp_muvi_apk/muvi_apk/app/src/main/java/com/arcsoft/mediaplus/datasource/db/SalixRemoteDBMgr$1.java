// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;

import android.net.NetworkInfo;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            SalixRemoteDBMgr

class this._cls0
    implements com.arcsoft.util.os.ectivityChangeListener
{

    final SalixRemoteDBMgr this$0;

    public void OnConnectivityChanged(com.arcsoft.util.os.StateInfo stateinfo)
    {
        if (stateinfo.networkInfo == null || stateinfo.networkInfo.getType() != 1)
        {
            return;
        } else
        {
            Log.e("FENG", (new StringBuilder()).append("SalixRemoteBDMgr OnConnectivityChanged info.networkInfo.isConnected() = ").append(stateinfo.networkInfo.isConnected()).toString());
            return;
        }
    }

    stener()
    {
        this$0 = SalixRemoteDBMgr.this;
        super();
    }
}
