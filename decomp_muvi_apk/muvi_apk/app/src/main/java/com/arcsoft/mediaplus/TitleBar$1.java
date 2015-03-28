// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.view.View;

// Referenced classes of package com.arcsoft.mediaplus:
//            TitleBar, ViewManager, MediaPlusActivity

class this._cls0
    implements android.view.kListener
{

    final TitleBar this$0;

    public void onClick(View view)
    {
        if (ViewManager.getViewStatus() == 0)
        {
            ((MediaPlusActivity)TitleBar.access$000(TitleBar.this)).refreshRemoteView();
            return;
        } else
        {
            ((MediaPlusActivity)TitleBar.access$000(TitleBar.this)).onBackPressed();
            return;
        }
    }

    tivity()
    {
        this$0 = TitleBar.this;
        super();
    }
}
