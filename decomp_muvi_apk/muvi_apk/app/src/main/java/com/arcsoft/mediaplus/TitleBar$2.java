// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.view.View;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus:
//            TitleBar, ViewManager, MediaPlusActivity

class this._cls0
    implements android.view.kListener
{

    final TitleBar this$0;

    public void onClick(View view)
    {
        switch (ViewManager.getViewStatus())
        {
        default:
            return;

        case 0: // '\0'
        case 1: // '\001'
            Log.e("FENG", "FENG mTitleRightBtn click");
            ((MediaPlusActivity)TitleBar.access$000(TitleBar.this)).startSettingActivity();
            return;

        case 2: // '\002'
            ((MediaPlusActivity)TitleBar.access$000(TitleBar.this)).refreshMarkView();
            break;
        }
    }

    tivity()
    {
        this$0 = TitleBar.this;
        super();
    }
}
