// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.service.util;

import android.os.Handler;
import android.os.Message;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.service.util:
//            DLNAService

class this._cls0 extends Handler
{

    final DLNAService this$0;

    public void handleMessage(Message message)
    {
        if (mToken != null)
        {
            Log.w("DLNA Service", "Release Token in service");
            DLNA.instance().releaseUserToken(mToken);
            mToken = null;
        }
    }

    ()
    {
        this$0 = DLNAService.this;
        super();
    }
}
