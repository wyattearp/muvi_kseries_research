// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;

import android.content.DialogInterface;

// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            EasyTransferRegisterActivity

class this._cls0
    implements android.content.
{

    final EasyTransferRegisterActivity this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        if (i == 0)
        {
            showDialog(2);
            return;
        } else
        {
            showDialog(3);
            return;
        }
    }

    ()
    {
        this$0 = EasyTransferRegisterActivity.this;
        super();
    }
}
