// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.content.DialogInterface;
import android.os.Handler;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEVideoStreamFullScreenActivity

class this._cls0
    implements android.content.nActivity._cls5
{

    final AEEVideoStreamFullScreenActivity this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        synchronized (AEEVideoStreamFullScreenActivity.access$900(AEEVideoStreamFullScreenActivity.this))
        {
            AEEVideoStreamFullScreenActivity.access$900(AEEVideoStreamFullScreenActivity.this).removeMessages(7);
            AEEVideoStreamFullScreenActivity.access$900(AEEVideoStreamFullScreenActivity.this).sendEmptyMessage(7);
        }
        dialoginterface.dismiss();
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    ()
    {
        this$0 = AEEVideoStreamFullScreenActivity.this;
        super();
    }
}
