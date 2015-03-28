// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.os.Handler;
import android.os.Message;
import android.view.View;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity

class this._cls0
    implements android.view.
{

    final MediaPlusActivity this$0;

    public void onClick(View view)
    {
        Integer integer = (Integer)view.getTag();
        switchDevicesUninit(integer.intValue());
        synchronized (mHandler)
        {
            mHandler.removeMessages(773);
            Message message = mHandler.obtainMessage(773);
            message.obj = integer;
            mHandler.sendMessageDelayed(message, 50L);
        }
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    ()
    {
        this$0 = MediaPlusActivity.this;
        super();
    }
}
