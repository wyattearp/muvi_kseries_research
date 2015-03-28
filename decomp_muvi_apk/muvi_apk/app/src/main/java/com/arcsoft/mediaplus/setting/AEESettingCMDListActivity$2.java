// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.net.NetworkInfo;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            AEESettingCMDListActivity

class this._cls0
    implements com.arcsoft.util.os.hangeListener
{

    final AEESettingCMDListActivity this$0;

    public void OnConnectivityChanged(com.arcsoft.util.os. )
    {
        while (.networkInfo == null || .networkInfo.getType() != 1 || .networkInfo.isConnected()) 
        {
            return;
        }
        AEESettingCMDListActivity.access$000(AEESettingCMDListActivity.this, 0x1100003, 0, null, 0x7f0b0059, -1);
        finish();
    }

    ner()
    {
        this$0 = AEESettingCMDListActivity.this;
        super();
    }
}
