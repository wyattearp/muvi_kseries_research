// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            SettingListActivity, DigitalMediaAdapter

class this._cls0 extends Handler
{

    final SettingListActivity this$0;

    public void handleMessage(Message message)
    {
        super.handleMessage(message);
        message.what;
        JVM INSTR tableswitch 769 774: default 48
    //                   769 49
    //                   770 60
    //                   771 48
    //                   772 48
    //                   773 115
    //                   774 143;
           goto _L1 _L2 _L3 _L1 _L1 _L4 _L5
_L1:
        return;
_L2:
        ((DigitalMediaAdapter)message.obj).notifyDataSetChanged();
        return;
_L3:
        if (!SettingListActivity.access$400(SettingListActivity.this))
        {
            int i;
            if (((DigitalMediaAdapter)message.obj).getType() == 1)
            {
                i = 0x7f0b0058;
            } else
            {
                i = 0x7f0b0057;
            }
            Toast.makeText(SettingListActivity.this, i, 0).show();
            SettingListActivity.access$402(SettingListActivity.this, true);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (SettingListActivity.access$300(SettingListActivity.this) != null)
        {
            SettingListActivity.access$300(SettingListActivity.this).dmsOffLine((String)message.obj);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (SettingListActivity.access$300(SettingListActivity.this) != null)
        {
            SettingListActivity.access$300(SettingListActivity.this).dmsOnLine((String)message.obj);
            return;
        }
        if (true) goto _L1; else goto _L6
_L6:
    }

    _cls9()
    {
        this$0 = SettingListActivity.this;
        super();
    }
}
