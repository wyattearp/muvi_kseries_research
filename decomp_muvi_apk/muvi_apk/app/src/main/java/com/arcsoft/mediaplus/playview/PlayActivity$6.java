// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.content.DialogInterface;
import android.os.Handler;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayActivity

class this._cls0
    implements android.content..OnClickListener
{

    final PlayActivity this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        synchronized (PlayActivity.access$200(PlayActivity.this))
        {
            PlayActivity.access$200(PlayActivity.this).removeMessages(2);
            PlayActivity.access$200(PlayActivity.this).sendEmptyMessage(2);
        }
        dialoginterface.dismiss();
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    r()
    {
        this$0 = PlayActivity.this;
        super();
    }
}
