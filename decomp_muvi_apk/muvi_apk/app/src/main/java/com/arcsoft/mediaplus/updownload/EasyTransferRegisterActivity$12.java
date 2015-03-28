// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import com.arcsoft.mediaplus.setting.EasyTransferSettingActivity;
import com.arcsoft.mediaplus.setting.Settings;

// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            EasyTransferRegisterActivity

class this._cls0
    implements android.view.RegisterActivity._cls12
{

    final EasyTransferRegisterActivity this$0;

    public void onClick(View view)
    {
        if (EasyTransferRegisterActivity.access$200(EasyTransferRegisterActivity.this) == null || Settings.instance().getDefaultDMSUDN() == null)
        {
            return;
        } else
        {
            Intent intent = new Intent(EasyTransferRegisterActivity.this, com/arcsoft/mediaplus/setting/EasyTransferSettingActivity);
            intent.putExtra("easytransfer", "register");
            intent.putExtra("server_udn", Settings.instance().getDefaultDMSUDN());
            intent.putExtra("server_name", Settings.instance().getDefaultDMSName());
            ((Activity)EasyTransferRegisterActivity.access$300(EasyTransferRegisterActivity.this)).startActivityForResult(intent, 1);
            return;
        }
    }

    ()
    {
        this$0 = EasyTransferRegisterActivity.this;
        super();
    }
}
