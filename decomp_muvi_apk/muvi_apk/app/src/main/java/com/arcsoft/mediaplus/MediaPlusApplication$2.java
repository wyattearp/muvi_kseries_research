// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusApplication

class this._cls0 extends BroadcastReceiver
{

    final MediaPlusApplication this$0;

    public void onReceive(Context context, Intent intent)
    {
        String s = intent.getAction();
        if (TextUtils.equals("android.intent.action.MEDIA_EJECT", s))
        {
            SDCardEject();
        } else
        {
            if (TextUtils.equals("android.intent.action.MEDIA_UNMOUNTED", s))
            {
                SDCardUnmount();
                return;
            }
            if (TextUtils.equals("android.intent.action.MEDIA_SCANNER_STARTED", s))
            {
                SDCardScanStart();
                return;
            }
            if (TextUtils.equals("android.intent.action.MEDIA_SCANNER_FINISHED", s))
            {
                SDCardScanFinsh();
                return;
            }
            if (TextUtils.equals("android.intent.action.MEDIA_MOUNTED", s))
            {
                SDCardMount();
                return;
            }
        }
    }

    ()
    {
        this$0 = MediaPlusApplication.this;
        super();
    }
}
