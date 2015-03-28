// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.content.DialogInterface;
import android.os.Handler;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEVideoStreamActivity

class this._cls0
    implements android.content.stener
{

    final AEEVideoStreamActivity this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        synchronized (AEEVideoStreamActivity.access$600(AEEVideoStreamActivity.this))
        {
            AEEVideoStreamActivity.access$600(AEEVideoStreamActivity.this).removeMessages(7);
            AEEVideoStreamActivity.access$600(AEEVideoStreamActivity.this).sendEmptyMessage(7);
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
        this$0 = AEEVideoStreamActivity.this;
        super();
    }
}
