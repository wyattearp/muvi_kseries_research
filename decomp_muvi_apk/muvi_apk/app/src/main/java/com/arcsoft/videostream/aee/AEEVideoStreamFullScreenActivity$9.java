// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEVideoStreamFullScreenActivity

class this._cls0 extends BroadcastReceiver
{

    final AEEVideoStreamFullScreenActivity this$0;

    public void onReceive(Context context, Intent intent)
    {
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("intent = ").append(intent.getAction()).toString());
        AEEVideoStreamFullScreenActivity.access$3400(AEEVideoStreamFullScreenActivity.this, 11, 0x10000029, -1, String.valueOf(AEEVideoStreamFullScreenActivity.access$3200(AEEVideoStreamFullScreenActivity.this)), 0L);
    }

    ()
    {
        this$0 = AEEVideoStreamFullScreenActivity.this;
        super();
    }
}
