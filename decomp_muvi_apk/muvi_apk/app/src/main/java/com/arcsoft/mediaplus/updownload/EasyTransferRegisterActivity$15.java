// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.arcsoft.mediaplus.service.util.DLNAService;
import com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            EasyTransferRegisterActivity, EasyTransferRegisterListView

class this._cls0
    implements ServiceConnection
{

    final EasyTransferRegisterActivity this$0;

    public void onServiceConnected(ComponentName componentname, IBinder ibinder)
    {
        Log.i("", "DLNAService connected");
        EasyTransferRegisterActivity.access$502(EasyTransferRegisterActivity.this, ((com.arcsoft.mediaplus.service.util.is._cls0)ibinder).ce());
        EasyTransferRegisterActivity.access$202(EasyTransferRegisterActivity.this, EasyTransferRegisterActivity.access$500(EasyTransferRegisterActivity.this).getUpDownloadEngine());
        if (EasyTransferRegisterActivity.access$200(EasyTransferRegisterActivity.this) != null)
        {
            EasyTransferRegisterActivity.access$000(EasyTransferRegisterActivity.this).init(EasyTransferRegisterActivity.access$600(EasyTransferRegisterActivity.this));
            EasyTransferRegisterActivity.access$000(EasyTransferRegisterActivity.this).setEasyTransferEngine(EasyTransferRegisterActivity.access$200(EasyTransferRegisterActivity.this));
            EasyTransferRegisterActivity.access$200(EasyTransferRegisterActivity.this).registerListener(mLisener);
            EasyTransferRegisterActivity.access$200(EasyTransferRegisterActivity.this).start();
        }
        EasyTransferRegisterActivity.access$400(EasyTransferRegisterActivity.this);
    }

    public void onServiceDisconnected(ComponentName componentname)
    {
        Log.i("", "DLNAService disconnected");
        EasyTransferRegisterActivity.access$502(EasyTransferRegisterActivity.this, null);
    }

    ()
    {
        this$0 = EasyTransferRegisterActivity.this;
        super();
    }
}
