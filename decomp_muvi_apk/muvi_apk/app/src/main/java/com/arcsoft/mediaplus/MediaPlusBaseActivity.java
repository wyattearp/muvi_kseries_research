// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;

public class MediaPlusBaseActivity extends Activity
{

    private final BroadcastReceiver mStorageStateReceiver = new BroadcastReceiver() {

        final MediaPlusBaseActivity this$0;

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

            
            {
                this$0 = MediaPlusBaseActivity.this;
                super();
            }
    };
    boolean mbResume;

    public MediaPlusBaseActivity()
    {
        mbResume = false;
    }

    public static boolean isSDCardAvailable()
    {
        String s = Environment.getExternalStorageState();
        return "mounted".equals(s) || "mounted_ro".equals(s);
    }

    protected void SDCardEject()
    {
    }

    protected void SDCardMount()
    {
    }

    protected void SDCardScanFinsh()
    {
    }

    protected void SDCardScanStart()
    {
    }

    protected void SDCardUnmount()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        registerReceives();
    }

    protected void onDestroy()
    {
        super.onDestroy();
        unRegisterReceives();
    }

    protected void onPause()
    {
        super.onPause();
        mbResume = false;
    }

    protected void onResume()
    {
        super.onResume();
        mbResume = true;
    }

    protected void registerReceives()
    {
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentfilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentfilter.addAction("android.intent.action.MEDIA_SCANNER_STARTED");
        intentfilter.addAction("android.intent.action.MEDIA_SCANNER_FINISHED");
        intentfilter.addAction("android.intent.action.MEDIA_SHARED");
        intentfilter.addAction("android.intent.action.MEDIA_REMOVED");
        intentfilter.addAction("android.intent.action.MEDIA_BAD_REMOVAL");
        intentfilter.addAction("android.intent.action.MEDIA_EJECT");
        intentfilter.addAction("android.intent.action.MEDIA_UNMOUNTABLE");
        intentfilter.addDataScheme("file");
        if (mStorageStateReceiver != null)
        {
            registerReceiver(mStorageStateReceiver, intentfilter);
        }
    }

    protected void unRegisterReceives()
    {
        if (mStorageStateReceiver != null)
        {
            unregisterReceiver(mStorageStateReceiver);
        }
    }
}
