// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.os;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

// Referenced classes of package com.arcsoft.util.os:
//            BatteryTool

class <init> extends BroadcastReceiver
{

    final BatteryTool this$0;

    public void onReceive(Context context, Intent intent)
    {
        tteryInfo tteryinfo = new tteryInfo(BatteryTool.this);
        tteryinfo.present = intent.getBooleanExtra("present", true);
        tteryinfo.maxlevel = intent.getIntExtra("scale", 0);
        tteryinfo.level = intent.getIntExtra("level", 0);
        tteryinfo.status = intent.getIntExtra("status", 1);
        tteryinfo.pluged = intent.getIntExtra("plugged", 2);
        tteryinfo.temperature = intent.getIntExtra("temperature", 0);
        tteryinfo.voltage = intent.getIntExtra("voltage", 5);
        if (BatteryTool.access$000(BatteryTool.this) == null)
        {
            BatteryTool.access$002(BatteryTool.this, new tifyHandler(BatteryTool.this, null));
        }
        if (BatteryTool.access$200(BatteryTool.this) == null || tteryinfo.level != BatteryTool.access$200(BatteryTool.this).level)
        {
            BatteryTool.access$000(BatteryTool.this).sendEmptyMessage(3);
        }
        if (BatteryTool.access$200(BatteryTool.this) == null || tteryinfo.status != BatteryTool.access$200(BatteryTool.this).status)
        {
            BatteryTool.access$000(BatteryTool.this).sendEmptyMessage(1);
        }
        if (BatteryTool.access$200(BatteryTool.this) == null || tteryinfo.pluged != BatteryTool.access$200(BatteryTool.this).pluged)
        {
            BatteryTool.access$000(BatteryTool.this).sendEmptyMessage(2);
        }
        BatteryTool.access$202(BatteryTool.this, tteryinfo);
    }

    tifyHandler()
    {
        this$0 = BatteryTool.this;
        super();
    }
}
