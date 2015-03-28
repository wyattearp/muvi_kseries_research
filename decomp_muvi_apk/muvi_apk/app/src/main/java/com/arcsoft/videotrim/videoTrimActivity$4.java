// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim;

import android.os.Handler;

// Referenced classes of package com.arcsoft.videotrim:
//            videoTrimActivity, PlayBackModule

class val.value
    implements Runnable
{

    final videoTrimActivity this$0;
    final int val$value;

    public void run()
    {
        videoTrimActivity.access$800(videoTrimActivity.this, true);
        videoTrimActivity.access$500(videoTrimActivity.this).SeekTo(val$value);
        if (videoTrimActivity.access$900(videoTrimActivity.this) != null)
        {
            videoTrimActivity.access$900(videoTrimActivity.this).sendEmptyMessage(1);
        }
        videoTrimActivity.access$800(videoTrimActivity.this, false);
    }

    ()
    {
        this$0 = final_videotrimactivity;
        val$value = I.this;
        super();
    }
}
