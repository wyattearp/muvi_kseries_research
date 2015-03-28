// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import android.content.DialogInterface;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            DmcPlayback

class this._cls0
    implements android.content.e.OnClickListener
{

    final DmcPlayback this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        stopPlayer();
        playFromIndex(DmcPlayback.access$200(DmcPlayback.this));
        DmcPlayback.access$800(DmcPlayback.this);
    }

    istener()
    {
        this$0 = DmcPlayback.this;
        super();
    }
}
