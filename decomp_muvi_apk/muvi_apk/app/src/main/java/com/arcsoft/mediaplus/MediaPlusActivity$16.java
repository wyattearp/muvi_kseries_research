// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity

class this._cls0 extends BroadcastReceiver
{

    final MediaPlusActivity this$0;

    public void onReceive(Context context, Intent intent)
    {
        if (MediaPlusActivity.this != null && !isFinishing())
        {
            finish();
        }
    }

    ()
    {
        this$0 = MediaPlusActivity.this;
        super();
    }
}
