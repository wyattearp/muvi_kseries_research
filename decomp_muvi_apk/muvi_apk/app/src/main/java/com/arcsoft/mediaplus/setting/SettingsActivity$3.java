// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.view.View;
import android.widget.PopupWindow;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            SettingsActivity

class this._cls0
    implements android.view.r
{

    final SettingsActivity this$0;

    public void onClick(View view)
    {
        if (SettingsActivity.access$200(SettingsActivity.this) != null)
        {
            SettingsActivity.access$200(SettingsActivity.this).dismiss();
        }
    }

    ()
    {
        this$0 = SettingsActivity.this;
        super();
    }
}
