// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.tool;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.arcsoft.util.tool:
//            CachePathManager

class this._cls1 extends Handler
{

    final sendEmptyMessageDelayed this$1;

    public void handleMessage(Message message)
    {
        synchronized (this._cls1.this)
        {
            notify();
        }
        sendEmptyMessageDelayed(0, 60000L);
        return;
        exception;
        _lcls1;
        JVM INSTR monitorexit ;
        throw exception;
    }

    ()
    {
        this$1 = this._cls1.this;
        super();
    }
}
