// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.updownload.service:
//            UpDownloadEngine

class this._cls0 extends BroadcastReceiver
{

    final UpDownloadEngine this$0;

    public void onReceive(Context context, Intent intent)
    {
        String s = intent.getAction();
        if (s.equals("android.intent.action.MEDIA_MOUNTED"))
        {
            Log.i("UpDownloadEngine", (new StringBuilder()).append("SD card mounted =").append(s).toString());
            UpDownloadEngine.access$1900(UpDownloadEngine.this).removeMessages(2);
            UpDownloadEngine.access$1900(UpDownloadEngine.this).sendEmptyMessage(2);
        } else
        if (s.equals("android.intent.action.MEDIA_UNMOUNTED") || s.equals("android.intent.action.MEDIA_BAD_REMOVAL") || s.equals("android.intent.action.MEDIA_EJECT"))
        {
            Log.i("UpDownloadEngine", (new StringBuilder()).append("SD card unmounted =").append(s).toString());
            UpDownloadEngine.access$1900(UpDownloadEngine.this).removeMessages(4);
            UpDownloadEngine.access$1900(UpDownloadEngine.this).sendEmptyMessage(4);
            return;
        }
    }

    rviceHandler()
    {
        this$0 = UpDownloadEngine.this;
        super();
    }
}
