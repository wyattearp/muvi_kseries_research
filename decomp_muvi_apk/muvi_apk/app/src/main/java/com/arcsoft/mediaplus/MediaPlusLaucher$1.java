// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusLaucher

class this._cls0 extends Handler
{

    final MediaPlusLaucher this$0;

    public void handleMessage(Message message)
    {
        switch (message.what)
        {
        default:
            return;

        case 1: // '\001'
            MediaPlusLaucher.access$000(MediaPlusLaucher.this);
            break;
        }
    }

    ()
    {
        this$0 = MediaPlusLaucher.this;
        super();
    }
}
