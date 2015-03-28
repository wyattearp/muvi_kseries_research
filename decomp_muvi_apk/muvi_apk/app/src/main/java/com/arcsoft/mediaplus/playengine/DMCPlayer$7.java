// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayer

class this._cls0 extends Handler
{

    final DMCPlayer this$0;

    public void handleMessage(Message message)
    {
        ((Runnable)message.obj).run();
    }

    ()
    {
        this$0 = DMCPlayer.this;
        super();
    }
}
