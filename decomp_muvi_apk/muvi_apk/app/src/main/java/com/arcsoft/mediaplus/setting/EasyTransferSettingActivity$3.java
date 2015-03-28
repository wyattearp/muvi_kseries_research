// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.arcsoft.mediaplus.service.util.DLNAService;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            EasyTransferSettingActivity

class this._cls0
    implements ServiceConnection
{

    final EasyTransferSettingActivity this$0;

    public void onServiceConnected(ComponentName componentname, IBinder ibinder)
    {
        Log.i("", "DLNAService connected");
        EasyTransferSettingActivity.access$302(EasyTransferSettingActivity.this, ((com.arcsoft.mediaplus.service.util._cls0)ibinder).vice());
        EasyTransferSettingActivity.access$402(EasyTransferSettingActivity.this, EasyTransferSettingActivity.access$300(EasyTransferSettingActivity.this).getUpDownloadEngine());
        EasyTransferSettingActivity.access$500(EasyTransferSettingActivity.this, true);
    }

    public void onServiceDisconnected(ComponentName componentname)
    {
        Log.i("", "DLNAService disconnected");
        EasyTransferSettingActivity.access$302(EasyTransferSettingActivity.this, null);
    }

    A()
    {
        this$0 = EasyTransferSettingActivity.this;
        super();
    }
}
