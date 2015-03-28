// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.view.View;
import android.widget.PopupWindow;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            AEESettingCMDListActivity

class this._cls0
    implements android.view.MDListActivity._cls4
{

    final AEESettingCMDListActivity this$0;

    public void onClick(View view)
    {
        AEESettingCMDListActivity.access$502(AEESettingCMDListActivity.this, -1);
        if (AEESettingCMDListActivity.access$600(AEESettingCMDListActivity.this) != null)
        {
            AEESettingCMDListActivity.access$600(AEESettingCMDListActivity.this).dismiss();
        }
    }

    ()
    {
        this$0 = AEESettingCMDListActivity.this;
        super();
    }
}
