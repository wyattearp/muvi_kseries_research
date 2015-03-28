// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.os;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.util.os:
//            ScreenTool

class  extends BroadcastReceiver
{

    final ScreenTool this$0;

    public void onReceive(Context context, Intent intent)
    {
        if (ScreenTool.access$000(ScreenTool.this) == null)
        {
            ScreenTool.access$002(ScreenTool.this, new tifyHandler(ScreenTool.this, null));
        }
        if ("android.intent.action.SCREEN_OFF".equalsIgnoreCase(intent.getAction()))
        {
            Log.i("ScreenTool", "android.intent.action.SCREEN_OFF");
            ScreenTool.access$202(ScreenTool.this, true);
            ScreenTool.access$000(ScreenTool.this).sendEmptyMessage(1);
        } else
        if ("android.intent.action.SCREEN_ON".equalsIgnoreCase(intent.getAction()))
        {
            Log.i("ScreenTool", "android.intent.action.SCREEN_ON");
            ScreenTool.access$202(ScreenTool.this, false);
            ScreenTool.access$000(ScreenTool.this).sendEmptyMessage(2);
            return;
        }
    }

    tifyHandler()
    {
        this$0 = ScreenTool.this;
        super();
    }
}
