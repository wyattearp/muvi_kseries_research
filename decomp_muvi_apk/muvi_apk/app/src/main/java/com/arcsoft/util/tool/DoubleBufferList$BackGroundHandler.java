// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.tool;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.arcsoft.util.debug.DebugUtils;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.util.tool:
//            DoubleBufferList

private class this._cls0 extends Handler
{

    final DoubleBufferList this$0;

    public void handleMessage(Message message)
    {
        message.what;
        JVM INSTR tableswitch 0 0: default 24
    //                   0 30;
           goto _L1 _L2
_L1:
        super.handleMessage(message);
        return;
_L2:
        if (!isDirty())
        {
            Log.w("DoubleBuffList", (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append("build list while the list isn't dirty.").toString());
        }
        DoubleBufferList.access$200(DoubleBufferList.this).yList(DoubleBufferList.access$100(DoubleBufferList.this));
        DoubleBufferList.access$300(DoubleBufferList.this);
        if (DoubleBufferList.access$200(DoubleBufferList.this).ist(DoubleBufferList.access$100(DoubleBufferList.this), DoubleBufferList.access$400(DoubleBufferList.this)))
        {
            DoubleBufferList.access$500(DoubleBufferList.this, 2);
            DoubleBufferList.access$600(DoubleBufferList.this).sendEmptyMessage(1);
            return;
        }
        Log.w("DoubleBuffList", (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append("build list failed or canceled. ").append("IsCanceled=").append(DoubleBufferList.access$400(DoubleBufferList.this).d).toString());
        DoubleBufferList.access$700(DoubleBufferList.this);
        DoubleBufferList.access$500(DoubleBufferList.this, 0);
        if (true) goto _L1; else goto _L3
_L3:
    }

    public (Looper looper)
    {
        this$0 = DoubleBufferList.this;
        super(looper);
    }
}
