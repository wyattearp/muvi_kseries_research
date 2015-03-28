// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.TextView;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            SettingsActivity, Settings

class val.curViewId
    implements android.widget.lickListener
{

    final SettingsActivity this$0;
    final int val$curViewId;

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        val$curViewId;
        JVM INSTR tableswitch 2131296495 2131296495: default 24
    //                   2131296495 45;
           goto _L1 _L2
_L1:
        if (SettingsActivity.access$200(SettingsActivity.this) != null)
        {
            SettingsActivity.access$200(SettingsActivity.this).dismiss();
        }
        return;
_L2:
        SettingsActivity.access$002(SettingsActivity.this, i);
        SettingsActivity.access$400(SettingsActivity.this).setText(SettingsActivity.access$300(SettingsActivity.this)[i]);
        Settings.instance().setDefaultContentAccess(SettingsActivity.access$000(SettingsActivity.this));
        if (true) goto _L1; else goto _L3
_L3:
    }

    ()
    {
        this$0 = final_settingsactivity;
        val$curViewId = I.this;
        super();
    }
}
