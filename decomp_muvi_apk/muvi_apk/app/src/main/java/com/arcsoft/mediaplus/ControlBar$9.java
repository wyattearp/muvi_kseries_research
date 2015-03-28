// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.view.View;

// Referenced classes of package com.arcsoft.mediaplus:
//            ControlBar

class this._cls0
    implements android.view.istener
{

    final ControlBar this$0;

    public void onClick(View view)
    {
        if (System.currentTimeMillis() - ControlBar.access$200(ControlBar.this) <= 1000L)
        {
            return;
        }
        if (1 != getCurrentViewType()) goto _L2; else goto _L1
_L1:
        ControlBar.access$300(ControlBar.this);
        ControlBar.access$400(ControlBar.this);
_L4:
        ControlBar.access$202(ControlBar.this, System.currentTimeMillis());
        return;
_L2:
        if (getCurrentViewType() == 0)
        {
            mRemoteBtnClickType = 1;
            preDownload(0x7f0b0180);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    ()
    {
        this$0 = ControlBar.this;
        super();
    }
}
