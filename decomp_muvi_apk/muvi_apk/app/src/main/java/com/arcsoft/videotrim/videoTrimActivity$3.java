// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.arcsoft.videotrim:
//            videoTrimActivity, TrimUIOper

class this._cls0 extends Handler
{

    final videoTrimActivity this$0;

    public void handleMessage(Message message)
    {
        message.what;
        JVM INSTR tableswitch 0 1: default 28
    //                   0 29
    //                   1 42;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        removeMessages(0);
        showDeletingWaitDialog();
        return;
_L3:
        removeMessages(0);
        removeMessages(1);
        cancelDeletingWaitDialog();
        if (videoTrimActivity.access$700(videoTrimActivity.this) != null && videoTrimActivity.access$700(videoTrimActivity.this).getTouchPlayerStatus())
        {
            setPlayerPlay(true);
            videoTrimActivity.access$700(videoTrimActivity.this).setTouchPlayerStatus(false);
            return;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    ()
    {
        this$0 = videoTrimActivity.this;
        super();
    }
}
