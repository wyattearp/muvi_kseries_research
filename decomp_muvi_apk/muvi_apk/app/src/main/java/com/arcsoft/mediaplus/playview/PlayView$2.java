// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayView, PlayActivity

class this._cls0 extends Handler
{

    final PlayView this$0;

    public void handleMessage(Message message)
    {
        switch (message.what)
        {
        default:
            return;

        case 1280: 
            hideControlView();
            ((PlayActivity)mContext).hideControlBar();
            return;

        case 1281: 
            showControlView();
            break;
        }
        ((PlayActivity)mContext).showControlBar();
    }

    y(Looper looper)
    {
        this$0 = PlayView.this;
        super(looper);
    }
}
