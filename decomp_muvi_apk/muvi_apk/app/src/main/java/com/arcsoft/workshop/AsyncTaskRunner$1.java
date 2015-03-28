// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop;

import android.os.Handler;
import android.os.Message;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.workshop:
//            AsyncTaskRunner, WorkShop

class this._cls0
    implements android.os.kRunner._cls1
{

    final AsyncTaskRunner this$0;

    public boolean handleMessage(Message message)
    {
        message.what;
        JVM INSTR tableswitch 1 1: default 24
    //                   1 26;
           goto _L1 _L2
_L1:
        return false;
_L2:
        Log.v("EditorTools", "In mHandlerAysnTask");
        if (AsyncTaskRunner.access$000(AsyncTaskRunner.this).isFinishing())
        {
            Log.v("WorkShop", "dostep when isFinishing");
            return false;
        }
        if (!AsyncTaskRunner.access$000(AsyncTaskRunner.this).getSaveStart() && AsyncTaskRunner.access$000(AsyncTaskRunner.this).isNeedDoAsyncTask())
        {
            int i = AsyncTaskRunner.access$100(AsyncTaskRunner.this);
            if (i == 0x80002)
            {
                Log.v("EditorTools", "In mHandlerAysnTask MERR_NO_MORE");
            }
            if (i == 0)
            {
                mHandlerAysnTask.sendEmptyMessageDelayed(1, 10L);
            }
            if (i != 0 && i != 0x80002)
            {
                mHandlerAysnTask.removeMessages(1);
                return false;
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    ()
    {
        this$0 = AsyncTaskRunner.this;
        super();
    }
}
