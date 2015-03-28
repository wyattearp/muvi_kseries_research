// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.os.Handler;
import android.os.Message;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.tool.DoubleBufferList;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsLocalDataSource

class this._cls0 extends Handler
{

    final AbsLocalDataSource this$0;

    public void handleMessage(Message message)
    {
        if (mList != null)
        {
            Log.e("MediaSee", "Local files updated while mediastore scan operation");
            mList.invalide();
        }
        if (hasMessages(0))
        {
            removeMessages(0);
            sendEmptyMessageDelayed(0, 5000L);
        }
    }

    ()
    {
        this$0 = AbsLocalDataSource.this;
        super();
    }
}
