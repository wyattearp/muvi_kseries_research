// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.content.DialogInterface;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayActivity

class this._cls0
    implements android.content..OnDismissListener
{

    final PlayActivity this$0;

    public void onDismiss(DialogInterface dialoginterface)
    {
        resetHideControlTimerEx();
    }

    ner()
    {
        this$0 = PlayActivity.this;
        super();
    }
}
