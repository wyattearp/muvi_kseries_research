// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.collage;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.arcsoft.mediaplus.collage:
//            Collage

class this._cls0 extends Handler
{

    final Collage this$0;

    public void handleMessage(Message message)
    {
        switch (message.what)
        {
        default:
            return;

        case 0: // '\0'
            Collage.access$1000(Collage.this, message.arg1);
            break;
        }
    }

    ()
    {
        this$0 = Collage.this;
        super();
    }
}
