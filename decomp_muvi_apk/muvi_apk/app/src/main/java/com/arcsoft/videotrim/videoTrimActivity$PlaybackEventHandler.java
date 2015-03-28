// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

// Referenced classes of package com.arcsoft.videotrim:
//            videoTrimActivity, PlayBackModule

private class this._cls0 extends Handler
{

    final videoTrimActivity this$0;

    public void handleMessage(Message message)
    {
        if (videoTrimActivity.access$500(videoTrimActivity.this) == null || message == null)
        {
            return;
        }
        message.what;
        JVM INSTR tableswitch 4097 4100: default 48
    //                   4097 54
    //                   4098 107
    //                   4099 138
    //                   4100 93;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        super.handleMessage(message);
        return;
_L2:
        videoTrimActivity.access$500(videoTrimActivity.this).OnReady(false);
        videoTrimActivity.access$500(videoTrimActivity.this).EnableDisplay(true);
        videoTrimActivity.access$500(videoTrimActivity.this).SeekTo(0);
        continue; /* Loop/switch isn't completed */
_L5:
        videoTrimActivity.access$500(videoTrimActivity.this).OnPaused();
        continue; /* Loop/switch isn't completed */
_L3:
        setPlayerPlay(false);
        videoTrimActivity.access$500(videoTrimActivity.this).SeekTo(0);
        videoTrimActivity.access$600(videoTrimActivity.this, 0);
        continue; /* Loop/switch isn't completed */
_L4:
        videoTrimActivity.access$500(videoTrimActivity.this).OnPlaying();
        int i = videoTrimActivity.access$500(videoTrimActivity.this).getCurrentTime();
        videoTrimActivity.access$600(videoTrimActivity.this, i);
        if (true) goto _L1; else goto _L6
_L6:
    }

    public (Looper looper)
    {
        this$0 = videoTrimActivity.this;
        super(looper);
    }
}
