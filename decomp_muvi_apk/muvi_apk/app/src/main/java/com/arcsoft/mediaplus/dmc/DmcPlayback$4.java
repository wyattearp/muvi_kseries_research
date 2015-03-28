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
        if (mHandler != null)
        {
            mHandler.sendEmptyMessage(9);
        }
    }

    aybackHandler()
    {
        this$0 = DmcPlayback.this;
        super();
    }
}
