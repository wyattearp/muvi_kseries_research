// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;

import android.os.Handler;

// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            EasyTransferRegisterActivity

class this._cls0
    implements com.arcsoft.mediaplus.updownload.easytransfer.ferEngineListener
{

    final EasyTransferRegisterActivity this$0;

    public void onEasyTransferFinish(String s, long l)
    {
        if (mHandler != null && !mHandler.hasMessages(0))
        {
            mHandler.sendEmptyMessage(0);
        }
    }

    public void onEasyTransferStart(String s, long l, int i)
    {
        if (mHandler != null && !mHandler.hasMessages(0))
        {
            mHandler.sendEmptyMessage(0);
        }
    }

    public void onEasyTransfering(String s, long l, int i, int j)
    {
    }

    .IOnEasyTransferEngineListener()
    {
        this$0 = EasyTransferRegisterActivity.this;
        super();
    }
}
