// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.tool;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.arcsoft.util.tool:
//            DoubleBufferList

private class <init> extends Handler
{

    final DoubleBufferList this$0;

    public void handleMessage(Message message)
    {
        message.what;
        JVM INSTR tableswitch 1 1: default 24
    //                   1 30;
           goto _L1 _L2
_L1:
        super.handleMessage(message);
        return;
_L2:
        DoubleBufferList.access$800(DoubleBufferList.this);
        DoubleBufferList.access$500(DoubleBufferList.this, 0);
        synchronized (DoubleBufferList.this)
        {
            if (isDirty() && !DoubleBufferList.access$400(DoubleBufferList.this).d)
            {
                DoubleBufferList.access$900(DoubleBufferList.this, false);
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
        exception;
        doublebufferlist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private ()
    {
        this$0 = DoubleBufferList.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
