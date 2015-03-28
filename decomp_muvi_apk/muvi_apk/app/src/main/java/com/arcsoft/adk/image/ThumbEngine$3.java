// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.image;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.arcsoft.adk.image:
//            ThumbEngine

class this._cls0 extends Handler
{

    final ThumbEngine this$0;

    public void handleMessage(Message message)
    {
        super.handleMessage(message);
        message.what;
        JVM INSTR tableswitch 10948608 10948608: default 28
    //                   10948608 29;
           goto _L1 _L2
_L1:
        return;
_L2:
        int i = message.arg1;
        if (ThumbEngine.access$1800(ThumbEngine.this) != null)
        {
            ThumbEngine.access$1800(ThumbEngine.this).onThumbReady(ThumbEngine.this, i);
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    umbEngineListener()
    {
        this$0 = ThumbEngine.this;
        super();
    }
}
