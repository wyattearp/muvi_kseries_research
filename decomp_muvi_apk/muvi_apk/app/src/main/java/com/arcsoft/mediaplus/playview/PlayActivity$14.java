// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayActivity

class this._cls0 extends BroadcastReceiver
{

    final PlayActivity this$0;

    public void onReceive(Context context, Intent intent)
    {
        Log.v("PlayActivity", "onReceive() clear cache action");
        if (PlayActivity.this != null && !isFinishing())
        {
            finish();
        }
    }

    ()
    {
        this$0 = PlayActivity.this;
        super();
    }
}
