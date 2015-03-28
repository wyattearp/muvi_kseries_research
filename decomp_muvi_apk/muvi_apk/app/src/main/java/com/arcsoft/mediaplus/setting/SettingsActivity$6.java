// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            SettingsActivity, Settings

class this._cls0 extends Handler
{

    final SettingsActivity this$0;

    public void handleMessage(Message message)
    {
        message.what;
        JVM INSTR tableswitch 0 1: default 28
    //                   0 29
    //                   1 99;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        if (SettingsActivity.access$600(SettingsActivity.this) != null)
        {
            SettingsActivity.access$600(SettingsActivity.this).setText((String)message.obj);
        }
        if (SettingsActivity.access$700(SettingsActivity.this) != null)
        {
            TextView textview = SettingsActivity.access$700(SettingsActivity.this);
            boolean flag;
            if (0L != SettingsActivity.access$500(SettingsActivity.this))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            textview.setEnabled(flag);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (SettingsActivity.access$800(SettingsActivity.this) != null)
        {
            SettingsActivity.access$800(SettingsActivity.this).setText(Settings.instance().getDefaultDMSName());
            return;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    ()
    {
        this$0 = SettingsActivity.this;
        super();
    }
}
