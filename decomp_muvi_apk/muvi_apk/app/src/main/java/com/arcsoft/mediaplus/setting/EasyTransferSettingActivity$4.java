// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.view.View;
import android.widget.Toast;
import com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            EasyTransferSettingActivity

class this._cls0
    implements android.view.ettingActivity._cls4
{

    final EasyTransferSettingActivity this$0;

    public void onClick(View view)
    {
        if (!EasyTransferSettingActivity.access$600(EasyTransferSettingActivity.this) && EasyTransferSettingActivity.access$400(EasyTransferSettingActivity.this).getEasyTransfer(EasyTransferSettingActivity.access$700(EasyTransferSettingActivity.this)) != null)
        {
            Toast.makeText(EasyTransferSettingActivity.access$800(EasyTransferSettingActivity.this), 0x7f0b0102, 1).show();
            return;
        }
        EasyTransferSettingActivity.access$900(EasyTransferSettingActivity.this);
        if (EasyTransferSettingActivity.access$1000(EasyTransferSettingActivity.this))
        {
            showDialog(104);
            return;
        } else
        {
            setResult(-1);
            finish();
            return;
        }
    }

    ngine()
    {
        this$0 = EasyTransferSettingActivity.this;
        super();
    }
}
