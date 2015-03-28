// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferDriver

class this._cls0 extends BroadcastReceiver
{

    final EasyTransferDriver this$0;

    public void onReceive(Context context, Intent intent)
    {
        String s = intent.getAction();
        if (!mFinished && EasyTransferDriver.access$1500(EasyTransferDriver.this) != null)
        {
            if (s.equals("android.intent.action.MEDIA_MOUNTED"))
            {
                Log.i("EasyTransferDriver", (new StringBuilder()).append("SD card mounted =").append(s).toString());
                EasyTransferDriver.access$902(EasyTransferDriver.this, true);
                EasyTransferDriver.access$1500(EasyTransferDriver.this).removeMessages(2);
                EasyTransferDriver.access$1500(EasyTransferDriver.this).sendEmptyMessage(2);
                return;
            }
            if (s.equals("android.intent.action.MEDIA_UNMOUNTED") || s.equals("android.intent.action.MEDIA_BAD_REMOVAL") || s.equals("android.intent.action.MEDIA_EJECT"))
            {
                Log.i("EasyTransferDriver", (new StringBuilder()).append("SD card unmounted =").append(s).toString());
                EasyTransferDriver.access$902(EasyTransferDriver.this, false);
                EasyTransferDriver.access$1500(EasyTransferDriver.this).removeMessages(2);
                EasyTransferDriver.access$1500(EasyTransferDriver.this).sendEmptyMessage(2);
                return;
            }
        }
    }

    iverHandler()
    {
        this$0 = EasyTransferDriver.this;
        super();
    }
}
