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
        removeMessages(0);
        if (message.what == 0 && TrimUIOper.access$2500(TrimUIOper.this) != null)
        {
            TrimUIOper.access$2500(TrimUIOper.this).notifySeekToValue(message.arg1);
        }
    }

    imUIOperListener()
    {
        this$0 = TrimUIOper.this;
        super();
    }
}
