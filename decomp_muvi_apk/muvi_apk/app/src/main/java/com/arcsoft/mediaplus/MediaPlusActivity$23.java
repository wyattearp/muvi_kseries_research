// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import com.arcsoft.mediaplus.listview.LoadingDialog;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity

class this._cls0 extends Handler
{

    final MediaPlusActivity this$0;

    public void handleMessage(Message message)
    {
        message.what;
        JVM INSTR tableswitch 4096 4098: default 32
    //                   4096 33
    //                   4097 136
    //                   4098 177;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L2:
        removeMessages(4096);
        if (MediaPlusActivity.access$2200(MediaPlusActivity.this) != null && !MediaPlusActivity.access$2700(MediaPlusActivity.this))
        {
            String s3 = getString(0x7f0b0120);
            String s4 = (new StringBuilder()).append(s3).append("(").append(MediaPlusActivity.access$200(MediaPlusActivity.this)).append("/").append(MediaPlusActivity.access$100(MediaPlusActivity.this)).append(")").toString();
            MediaPlusActivity.access$3700(MediaPlusActivity.this).setText(s4);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        removeMessages(4096);
        if (MediaPlusActivity.access$2200(MediaPlusActivity.this) != null)
        {
            String s2 = getString(0x7f0b0182);
            MediaPlusActivity.access$3700(MediaPlusActivity.this).setText(s2);
            return;
        }
        if (true) goto _L1; else goto _L4
_L4:
        removeMessages(4098);
        if (MediaPlusActivity.access$3800(MediaPlusActivity.this) != null)
        {
            String s = getString(0x7f0b0120);
            String s1 = (new StringBuilder()).append(s).append("(").append(MediaPlusActivity.access$200(MediaPlusActivity.this)).append("/").append(MediaPlusActivity.access$100(MediaPlusActivity.this)).append(")").toString();
            MediaPlusActivity.access$3800(MediaPlusActivity.this).setText(s1);
            return;
        }
        if (true) goto _L1; else goto _L5
_L5:
    }

    g()
    {
        this$0 = MediaPlusActivity.this;
        super();
    }
}
