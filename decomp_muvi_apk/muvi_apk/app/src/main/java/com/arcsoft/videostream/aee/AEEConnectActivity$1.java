// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEConnectActivity

class this._cls0
    implements android.view.nectActivity._cls1
{

    final AEEConnectActivity this$0;

    public void onClick(View view)
    {
        boolean flag = true;
        view.getId();
        JVM INSTR tableswitch 2131296637 2131296642: default 44
    //                   2131296637 45
    //                   2131296638 62
    //                   2131296639 44
    //                   2131296640 44
    //                   2131296641 99
    //                   2131296642 148;
           goto _L1 _L2 _L3 _L1 _L1 _L4 _L5
_L1:
        return;
_L2:
        startActivity(new Intent("android.settings.WIFI_SETTINGS"));
        return;
_L3:
        if (AEEConnectActivity.access$000(AEEConnectActivity.this) != flag)
        {
            if (AEEConnectActivity.access$100(AEEConnectActivity.this))
            {
                AEEConnectActivity.access$200(AEEConnectActivity.this);
                return;
            } else
            {
                AEEConnectActivity.access$300(AEEConnectActivity.this);
                return;
            }
        }
          goto _L1
_L4:
        AEEConnectActivity.access$102(AEEConnectActivity.this, AEEConnectActivity.access$400(AEEConnectActivity.this).isChecked());
        CheckBox checkbox = AEEConnectActivity.access$500(AEEConnectActivity.this);
        if (AEEConnectActivity.access$100(AEEConnectActivity.this))
        {
            flag = false;
        }
        checkbox.setChecked(flag);
        return;
_L5:
        AEEConnectActivity aeeconnectactivity = AEEConnectActivity.this;
        if (AEEConnectActivity.access$500(AEEConnectActivity.this).isChecked())
        {
            flag = false;
        }
        AEEConnectActivity.access$102(aeeconnectactivity, flag);
        AEEConnectActivity.access$400(AEEConnectActivity.this).setChecked(AEEConnectActivity.access$100(AEEConnectActivity.this));
        return;
    }

    ()
    {
        this$0 = AEEConnectActivity.this;
        super();
    }
}
