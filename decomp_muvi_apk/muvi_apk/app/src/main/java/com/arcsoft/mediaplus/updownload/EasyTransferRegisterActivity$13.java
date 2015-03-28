// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.arcsoft.mediaplus.setting.EasyTransferSettingActivity;
import com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine;

// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            EasyTransferRegisterActivity

class this._cls0
    implements stItemClickListener
{

    final EasyTransferRegisterActivity this$0;

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        com.arcsoft.mediaplus.updownload.easytransfer.stener stener;
        if (EasyTransferRegisterActivity.access$200(EasyTransferRegisterActivity.this) != null)
        {
            if ((stener = EasyTransferRegisterActivity.access$200(EasyTransferRegisterActivity.this).getEasyTransfer(l)) != null)
            {
                if (stener.rState == 1)
                {
                    Intent intent = new Intent(EasyTransferRegisterActivity.this, com/arcsoft/mediaplus/setting/EasyTransferSettingActivity);
                    intent.putExtra("easytransfer", "edit");
                    intent.putExtra("server_udn", stener.st.erudn);
                    intent.putExtra("server_name", stener.st.ername);
                    intent.putExtra("recordday", stener.st.rdDay);
                    intent.putExtra("hour", stener.st.tHour);
                    intent.putExtra("minute", stener.st.tMinute);
                    intent.putExtra("allow", stener.st.leAllow);
                    intent.putExtra("plugin", stener.st.lePlugIn);
                    ((Activity)EasyTransferRegisterActivity.access$300(EasyTransferRegisterActivity.this)).startActivityForResult(intent, 1);
                    return;
                }
                if (stener.rState == 4)
                {
                    Toast.makeText(EasyTransferRegisterActivity.access$300(EasyTransferRegisterActivity.this), 0x7f0b0108, 1).show();
                    return;
                } else
                {
                    adapterview.setTag(stener.st.erudn);
                    showDialog(4);
                    return;
                }
            }
        }
    }

    public boolean onItemLongClick(AdapterView adapterview, View view, int i, long l)
    {
        com.arcsoft.mediaplus.updownload.easytransfer.erudn erudn;
        if (EasyTransferRegisterActivity.access$200(EasyTransferRegisterActivity.this) != null)
        {
            if ((erudn = EasyTransferRegisterActivity.access$200(EasyTransferRegisterActivity.this).getEasyTransfer(l)) != null && erudn.rState == 1)
            {
                adapterview.setTag(erudn.st.erudn);
                showDialog(1);
                return true;
            }
        }
        return true;
    }

    .Request()
    {
        this$0 = EasyTransferRegisterActivity.this;
        super();
    }
}
