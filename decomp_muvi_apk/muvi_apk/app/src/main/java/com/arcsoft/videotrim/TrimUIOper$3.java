// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.arcsoft.videotrim:
//            TrimUIOper

class this._cls0 extends Handler
{

    final TrimUIOper this$0;

    public void handleMessage(Message message)
    {
        message.what;
        JVM INSTR tableswitch 1 1: default 24
    //                   1 25;
           goto _L1 _L2
_L1:
        return;
_L2:
        if (TrimUIOper.access$3000(TrimUIOper.this))
        {
            int i = message.arg1;
            TrimUIOper.access$3100(TrimUIOper.this, i);
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    ()
    {
        this$0 = TrimUIOper.this;
        super();
    }
}
