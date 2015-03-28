// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.os.Handler;
import android.os.Message;
import com.arcsoft.adk.image.PhotoViewer;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            ImageDMPPlayView

class this._cls0 extends Handler
{

    final ImageDMPPlayView this$0;

    public void handleMessage(Message message)
    {
        switch (message.what)
        {
        default:
            return;

        case 1: // '\001'
            ImageDMPPlayView.access$300(ImageDMPPlayView.this, ImageDMPPlayView.access$800(ImageDMPPlayView.this).getCurrentIndex(), true);
            break;
        }
    }

    ()
    {
        this$0 = ImageDMPPlayView.this;
        super();
    }
}
