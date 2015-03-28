// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.service;

import com.arcsoft.util.debug.Log;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package com.arcsoft.mediaplus.updownload.service:
//            UpDownloadEngine

private class <init> extends Thread
{

    AtomicBoolean bExit;
    final UpDownloadEngine this$0;

    public void run()
    {
        Log.i("UpDownloadEngine", "ServiceThread run");
        if (!bExit.get()) goto _L2; else goto _L1
_L1:
        UpDownloadEngine.access$2402(UpDownloadEngine.this, 0);
        return;
_L2:
        UpDownloadEngine.access$2300(UpDownloadEngine.this);
        if (!bExit.get())
        {
            if ((1 & UpDownloadEngine.access$2400(UpDownloadEngine.this)) > 0)
            {
                UpDownloadEngine.access$2500(UpDownloadEngine.this);
                UpDownloadEngine.access$2402(UpDownloadEngine.this, -2 & UpDownloadEngine.access$2400(UpDownloadEngine.this));
            }
            if (!bExit.get())
            {
                if ((2 & UpDownloadEngine.access$2400(UpDownloadEngine.this)) > 0)
                {
                    UpDownloadEngine.access$2600(UpDownloadEngine.this);
                    UpDownloadEngine.access$2402(UpDownloadEngine.this, -3 & UpDownloadEngine.access$2400(UpDownloadEngine.this));
                }
                if (!bExit.get())
                {
                    if ((4 & UpDownloadEngine.access$2400(UpDownloadEngine.this)) > 0)
                    {
                        UpDownloadEngine.access$1000(UpDownloadEngine.this);
                        UpDownloadEngine.access$2402(UpDownloadEngine.this, -5 & UpDownloadEngine.access$2400(UpDownloadEngine.this));
                    }
                    bExit.set(true);
                }
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    private ()
    {
        this$0 = UpDownloadEngine.this;
        super();
        bExit = new AtomicBoolean(false);
    }

    bExit(bExit bexit)
    {
        this();
    }
}
