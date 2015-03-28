// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// Referenced classes of package com.arcsoft.videotrim.Utils:
//            SDCardManager, UtilFunc

class this._cls0 extends BroadcastReceiver
{

    final SDCardManager this$0;

    public void onReceive(Context context, Intent intent)
    {
        UtilFunc.Log("SDCardManager", (new StringBuilder()).append("onReceive--->action: ").append(intent.getAction()).toString());
        SDCardManager.access$000(SDCardManager.this, intent);
    }

    ()
    {
        this$0 = SDCardManager.this;
        super();
    }
}
