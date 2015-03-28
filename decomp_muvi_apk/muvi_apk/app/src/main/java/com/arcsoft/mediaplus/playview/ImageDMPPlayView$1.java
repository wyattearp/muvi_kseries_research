// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.os.Handler;
import android.os.Message;
import android.view.View;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            ImageDMPPlayView, DataSourcePlayList

class this._cls0 extends Handler
{

    final ImageDMPPlayView this$0;

    public void handleMessage(Message message)
    {
        message.what;
        JVM INSTR tableswitch 1000 1000: default 24
    //                   1000 30;
           goto _L1 _L2
_L1:
        super.handleMessage(message);
        return;
_L2:
        if (mRootView != null)
        {
            mRootView.setKeepScreenOn(true);
        }
        if (mPlayList.getNextIndex(false) == -1)
        {
            ImageDMPPlayView.access$002(ImageDMPPlayView.this, false);
            stopSlideShow();
        }
        if (ImageDMPPlayView.access$000(ImageDMPPlayView.this))
        {
            slideNext();
            ImageDMPPlayView.access$100(ImageDMPPlayView.this).removeMessages(1000);
            ImageDMPPlayView.access$100(ImageDMPPlayView.this).sendEmptyMessageDelayed(1000, ImageDMPPlayView.access$200(ImageDMPPlayView.this));
        } else
        {
            ImageDMPPlayView.access$100(ImageDMPPlayView.this).removeMessages(1000);
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    ()
    {
        this$0 = ImageDMPPlayView.this;
        super();
    }
}
