// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.content.res.Resources;
import android.view.View;
import android.widget.AdapterView;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            AEESettingCMDListActivity

class this._cls0
    implements android.widget.ner
{

    final AEESettingCMDListActivity this$0;

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("onItemClick position = ").append(i).append(" isClickable = ").append(view.isClickable()).toString());
        if (AEESettingCMDListActivity.access$100(AEESettingCMDListActivity.this) != null && i < AEESettingCMDListActivity.access$100(AEESettingCMDListActivity.this).length) goto _L2; else goto _L1
_L1:
        return;
_L2:
        AEESettingCMDListActivity.access$100(AEESettingCMDListActivity.this)[i];
        JVM INSTR tableswitch 33 35: default 96
    //                   33 187
    //                   34 121
    //                   35 154;
           goto _L3 _L4 _L5 _L6
_L3:
        if (!view.isClickable())
        {
            AEESettingCMDListActivity.access$302(AEESettingCMDListActivity.this, i);
            AEESettingCMDListActivity.access$400(AEESettingCMDListActivity.this, i);
            return;
        }
          goto _L7
_L5:
        AEESettingCMDListActivity.access$200(AEESettingCMDListActivity.this, i, getResources().getString(0x7f0b01a5), getResources().getString(0x7f0b01a6));
        return;
_L6:
        AEESettingCMDListActivity.access$200(AEESettingCMDListActivity.this, i, getResources().getString(0x7f0b01a5), getResources().getString(0x7f0b01a7));
        return;
_L4:
        AEESettingCMDListActivity.access$200(AEESettingCMDListActivity.this, i, getResources().getString(0x7f0b01a5), getResources().getString(0x7f0b01a8));
        if (true) goto _L3; else goto _L7
_L7:
        if (true) goto _L1; else goto _L8
_L8:
    }

    ()
    {
        this$0 = AEESettingCMDListActivity.this;
        super();
    }
}
