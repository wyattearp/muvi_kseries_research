// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.os.Handler;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity

class this._cls0
    implements com.arcsoft.util.os.canListener
{

    final MediaPlusActivity this$0;

    public void onMediaScanFinished()
    {
        mHandler.removeMessages(770);
    }

    public void onMediaScanStarted()
    {
        mHandler.sendEmptyMessage(770);
    }

    nListener()
    {
        this$0 = MediaPlusActivity.this;
        super();
    }
}
