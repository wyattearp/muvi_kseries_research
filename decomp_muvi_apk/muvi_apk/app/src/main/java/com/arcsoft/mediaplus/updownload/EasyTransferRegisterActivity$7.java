// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;

import android.content.DialogInterface;
import android.widget.Toast;
import com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine;

// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            EasyTransferRegisterActivity, EasyTransferRegisterListView

class this._cls0
    implements android.content.
{

    final EasyTransferRegisterActivity this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
label0:
        {
            if (EasyTransferRegisterActivity.access$200(EasyTransferRegisterActivity.this) != null)
            {
                String s = (String)EasyTransferRegisterActivity.access$000(EasyTransferRegisterActivity.this).getTag();
                if (EasyTransferRegisterActivity.access$200(EasyTransferRegisterActivity.this).getServerState(s) != 1)
                {
                    break label0;
                }
                EasyTransferRegisterActivity.access$200(EasyTransferRegisterActivity.this).executeEasyTransfer(s);
                if (EasyTransferRegisterActivity.access$000(EasyTransferRegisterActivity.this) != null)
                {
                    EasyTransferRegisterActivity.access$000(EasyTransferRegisterActivity.this).invalidateViews();
                }
            }
            return;
        }
        Toast.makeText(EasyTransferRegisterActivity.access$300(EasyTransferRegisterActivity.this), 0x7f0b0105, 1).show();
    }

    e()
    {
        this$0 = EasyTransferRegisterActivity.this;
        super();
    }
}
