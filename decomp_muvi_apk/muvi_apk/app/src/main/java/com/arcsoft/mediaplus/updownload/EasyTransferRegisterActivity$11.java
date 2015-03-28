// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;

import android.content.DialogInterface;
import com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine;

// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            EasyTransferRegisterActivity, EasyTransferRegisterListView

class this._cls0
    implements android.content.isterActivity._cls11
{

    final EasyTransferRegisterActivity this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        if (EasyTransferRegisterActivity.access$200(EasyTransferRegisterActivity.this) != null)
        {
            String s = (String)EasyTransferRegisterActivity.access$000(EasyTransferRegisterActivity.this).getTag();
            EasyTransferRegisterActivity.access$200(EasyTransferRegisterActivity.this).cancelEasyTransfer(s);
            if (EasyTransferRegisterActivity.access$000(EasyTransferRegisterActivity.this) != null)
            {
                EasyTransferRegisterActivity.access$000(EasyTransferRegisterActivity.this).invalidateViews();
            }
        }
    }

    ()
    {
        this$0 = EasyTransferRegisterActivity.this;
        super();
    }
}
