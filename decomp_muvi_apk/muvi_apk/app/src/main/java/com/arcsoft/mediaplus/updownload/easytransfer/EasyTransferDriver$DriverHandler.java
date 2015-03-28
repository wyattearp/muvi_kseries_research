// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferDriver

private class <init> extends Handler
{

    final EasyTransferDriver this$0;

    public void handleMessage(Message message)
    {
        if (!mFinished) goto _L2; else goto _L1
_L1:
        return;
_L2:
        switch (message.what)
        {
        default:
            return;

        case 2: // '\002'
            if (!EasyTransferDriver.access$900(EasyTransferDriver.this))
            {
                if (EasyTransferDriver.access$1000(EasyTransferDriver.this))
                {
                    stop();
                    return;
                }
            } else
            {
                start();
                return;
            }
            break;

        case 3: // '\003'
            if (!EasyTransferDriver.access$1100(EasyTransferDriver.this))
            {
                stop();
                return;
            } else
            {
                start();
                return;
            }

        case 4: // '\004'
            String s = (String)message.obj;
            cancelEasyTransfer(s);
            return;

        case 0: // '\0'
        case 1: // '\001'
            break;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    public void release()
    {
        removeMessages(0);
        removeMessages(1);
        removeMessages(2);
        removeMessages(3);
        removeMessages(4);
    }

    private ()
    {
        this$0 = EasyTransferDriver.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
