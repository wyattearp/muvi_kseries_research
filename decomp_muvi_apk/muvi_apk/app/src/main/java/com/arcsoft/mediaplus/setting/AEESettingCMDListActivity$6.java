// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            AEESettingCMDListActivity

class this._cls0
    implements android.widget.r
{

    final AEESettingCMDListActivity this$0;

    public void onDismiss()
    {
        Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("onDismiss mCurVal = ").append(AEESettingCMDListActivity.access$500(AEESettingCMDListActivity.this)).append(" mCurCmd = ").append(AEESettingCMDListActivity.access$300(AEESettingCMDListActivity.this)).toString());
        AEESettingCMDListActivity.access$602(AEESettingCMDListActivity.this, null);
        AEESettingCMDListActivity.access$302(AEESettingCMDListActivity.this, -1);
        AEESettingCMDListActivity.access$502(AEESettingCMDListActivity.this, -1);
    }

    ()
    {
        this$0 = AEESettingCMDListActivity.this;
        super();
    }
}
