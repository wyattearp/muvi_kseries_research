// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEVideoStreamActivity

class this._cls0 extends BroadcastReceiver
{

    final AEEVideoStreamActivity this$0;

    public void onReceive(Context context, Intent intent)
    {
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("intent = ").append(intent.getAction()).toString());
        AEEVideoStreamActivity.access$400(AEEVideoStreamActivity.this, 12, 0x10000029, -1, String.valueOf(AEEVideoStreamActivity.access$300(AEEVideoStreamActivity.this)), 0L);
    }

    ()
    {
        this$0 = AEEVideoStreamActivity.this;
        super();
    }
}
