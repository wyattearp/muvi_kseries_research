// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.view.View;
import android.widget.SeekBar;

// Referenced classes of package com.arcsoft.mediaplus:
//            TitleBar, MediaPlusActivity

class this._cls0
    implements android.view.kListener
{

    final TitleBar this$0;

    public void onClick(View view)
    {
        if (TitleBar.access$200(TitleBar.this).getProgress() != 0)
        {
            ((MediaPlusActivity)TitleBar.access$000(TitleBar.this)).switchView(0);
            TitleBar.access$200(TitleBar.this).setProgress(0);
            return;
        } else
        {
            ((MediaPlusActivity)TitleBar.access$000(TitleBar.this)).switchView(1);
            TitleBar.access$200(TitleBar.this).setProgress(1);
            return;
        }
    }

    tivity()
    {
        this$0 = TitleBar.this;
        super();
    }
}
