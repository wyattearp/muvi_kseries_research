// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

// Referenced classes of package com.arcsoft.videotrim:
//            SaveDialog, TrimSaveOper

class this._cls0 extends Handler
{

    final SaveDialog this$0;

    public void handleMessage(Message message)
    {
        message.what;
        JVM INSTR tableswitch 0 8: default 56
    //                   0 62
    //                   1 159
    //                   2 343
    //                   3 291
    //                   4 317
    //                   5 481
    //                   6 507
    //                   7 533
    //                   8 559;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
        super.handleMessage(message);
        return;
_L2:
        removeMessages(0);
        if (SaveDialog.access$000(SaveDialog.this) != null && SaveDialog.access$100(SaveDialog.this) != null)
        {
            SaveDialog.access$300(SaveDialog.this).setText((new StringBuilder()).append(SaveDialog.access$200(SaveDialog.this).getResources().getString(0x7f0b01c3)).append("0%").toString());
            SaveDialog.access$400(SaveDialog.this).setProgress(0);
            SaveDialog.access$500(SaveDialog.this).sendEmptyMessageDelayed(1, 50L);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        removeMessages(1);
        if (!SaveDialog.access$600(SaveDialog.this) && SaveDialog.access$000(SaveDialog.this) != null && SaveDialog.access$100(SaveDialog.this) != null)
        {
            int i = SaveDialog.access$000(SaveDialog.this).getCurrentPercent();
            SaveDialog.access$300(SaveDialog.this).setText((new StringBuilder()).append(SaveDialog.access$200(SaveDialog.this).getResources().getString(0x7f0b01c3)).append(i).append("%").toString());
            SaveDialog.access$400(SaveDialog.this).setProgress(i);
            if (i < 100)
            {
                SaveDialog.access$500(SaveDialog.this).sendEmptyMessageDelayed(1, 50L);
            }
        }
        continue; /* Loop/switch isn't completed */
_L5:
        SaveDialog.access$700(SaveDialog.this);
        Toast.makeText(SaveDialog.access$200(SaveDialog.this), 0x7f0b01c9, 0).show();
        continue; /* Loop/switch isn't completed */
_L6:
        SaveDialog.access$700(SaveDialog.this);
        Toast.makeText(SaveDialog.access$200(SaveDialog.this), 0x7f0b01c4, 0).show();
        continue; /* Loop/switch isn't completed */
_L4:
        if (SaveDialog.access$000(SaveDialog.this) != null && SaveDialog.access$100(SaveDialog.this) != null)
        {
            SaveDialog.access$300(SaveDialog.this).setText((new StringBuilder()).append(SaveDialog.access$200(SaveDialog.this).getResources().getString(0x7f0b01c3)).append(100).append("%").toString());
            SaveDialog.access$400(SaveDialog.this).setProgress(100);
        }
        if (SaveDialog.access$800(SaveDialog.this) != null)
        {
            Message message1 = SaveDialog.access$800(SaveDialog.this).obtainMessage(0);
            message1.obj = SaveDialog.access$900(SaveDialog.this);
            SaveDialog.access$800(SaveDialog.this).sendMessageDelayed(message1, 1000L);
        }
        SaveDialog.access$700(SaveDialog.this);
        continue; /* Loop/switch isn't completed */
_L7:
        SaveDialog.access$700(SaveDialog.this);
        Toast.makeText(SaveDialog.access$200(SaveDialog.this), 0x7f0b01c6, 0).show();
        continue; /* Loop/switch isn't completed */
_L8:
        SaveDialog.access$700(SaveDialog.this);
        Toast.makeText(SaveDialog.access$200(SaveDialog.this), 0x7f0b01c7, 0).show();
        continue; /* Loop/switch isn't completed */
_L9:
        SaveDialog.access$700(SaveDialog.this);
        Toast.makeText(SaveDialog.access$200(SaveDialog.this), 0x7f0b01c8, 0).show();
        continue; /* Loop/switch isn't completed */
_L10:
        SaveDialog.access$700(SaveDialog.this);
        if (true) goto _L1; else goto _L11
_L11:
    }

    ()
    {
        this$0 = SaveDialog.this;
        super();
    }
}
