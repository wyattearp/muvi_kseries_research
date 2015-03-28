// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.view.View;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            AEESettingCMDListActivity

class val.data
    implements android.view.tingCMDAdapter._cls1
{

    final is._cls0 this$1;
    final com.arcsoft.videostream.aee.this._cls0 val$data;
    final int val$index;

    public void onClick(View view)
    {
        AEESettingCMDListActivity.access$000(_fld0, 0x1100005, 0, null, 1, -1);
        AEESettingCMDListActivity.access$302(_fld0, val$index);
        AEESettingCMDListActivity.access$502(_fld0, 1 - val$data.data);
        String s = val$data.data[AEESettingCMDListActivity.access$500(_fld0)];
        AEESettingCMDListActivity.access$000(_fld0, 0x1100002, 0, (new StringBuilder()).append(val$data.data).append(";").append(s).toString(), AEESettingCMDListActivity.access$300(_fld0), AEESettingCMDListActivity.access$500(_fld0));
    }

    Q()
    {
        this$1 = final_q;
        val$index = i;
        val$data = com.arcsoft.videostream.aee._cls1.val.data.this;
        super();
    }
}
