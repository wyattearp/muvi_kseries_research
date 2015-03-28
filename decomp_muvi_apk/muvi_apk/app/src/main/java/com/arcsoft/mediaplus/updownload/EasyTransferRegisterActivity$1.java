// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;

import android.os.Message;

// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            EasyTransferRegisterActivity, EasyTransferRegisterListView

class this._cls0
    implements android.os.ferRegisterActivity._cls1
{

    final EasyTransferRegisterActivity this$0;

    public boolean handleMessage(Message message)
    {
        if (EasyTransferRegisterActivity.access$000(EasyTransferRegisterActivity.this) != null)
        {
            EasyTransferRegisterActivity.access$000(EasyTransferRegisterActivity.this).invalidateViews();
        }
        return true;
    }

    ()
    {
        this$0 = EasyTransferRegisterActivity.this;
        super();
    }
}
