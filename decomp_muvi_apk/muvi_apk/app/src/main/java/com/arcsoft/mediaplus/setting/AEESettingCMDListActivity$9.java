// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import com.arcsoft.util.debug.Log;
import com.arcsoft.videostream.aee.AEESocketClient;
import java.io.IOException;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            AEESettingCMDListActivity

class this._cls0
    implements com.arcsoft.videostream.aee.figListener
{

    final AEESettingCMDListActivity this$0;

    public void onRequestConfigFinished(String s)
    {
        Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("onRequestConfigFinished configStr = ").append(s).toString());
        try
        {
            AEESettingCMDListActivity.access$1402(AEESettingCMDListActivity.this, AEESocketClient.getInstanceClient());
            AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).stopRequestConfig();
            AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).setOnRequestRespondsListener(AEESettingCMDListActivity.access$2900(AEESettingCMDListActivity.this));
            AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).startRespondParams(-1);
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
            AEESettingCMDListActivity.access$3000(AEESettingCMDListActivity.this);
        }
        if (s != null)
        {
            AEESettingCMDListActivity.access$900(AEESettingCMDListActivity.this, 0x10000027, null);
            AEESettingCMDListActivity.access$000(AEESettingCMDListActivity.this, 0x1100007, 0, null, -1, -1);
            return;
        } else
        {
            AEESettingCMDListActivity.access$000(AEESettingCMDListActivity.this, 0x1100005, 0, null, 0, -1);
            AEESettingCMDListActivity.access$000(AEESettingCMDListActivity.this, 0x1100003, 0, null, 0x7f0b0160, -1);
            finish();
            return;
        }
    }

    gListener()
    {
        this$0 = AEESettingCMDListActivity.this;
        super();
    }
}
