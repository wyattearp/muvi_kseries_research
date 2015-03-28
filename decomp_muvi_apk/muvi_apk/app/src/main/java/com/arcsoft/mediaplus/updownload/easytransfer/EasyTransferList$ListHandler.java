// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferList

private class this._cls0 extends Handler
{

    final EasyTransferList this$0;

    public void handleMessage(Message message)
    {
        boolean flag = true;
        message.what;
        JVM INSTR tableswitch 0 2: default 32
    //                   0 33
    //                   1 105
    //                   2 181;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L2:
        EasyTransferList easytransferlist1 = EasyTransferList.this;
        boolean flag1 = EasyTransferList.access$100(EasyTransferList.this);
        boolean flag2 = false;
        if (!flag1)
        {
            flag2 = flag;
        }
        EasyTransferList.access$002(easytransferlist1, flag2);
        if (!EasyTransferList.access$000(EasyTransferList.this))
        {
            sendEmptyMessageDelayed(flag, 500L);
            return;
        }
        if (!hasMessages(2))
        {
            sendEmptyMessageDelayed(2, 500L);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (!EasyTransferList.access$000(EasyTransferList.this))
        {
            EasyTransferList easytransferlist = EasyTransferList.this;
            if (EasyTransferList.access$200(EasyTransferList.this))
            {
                flag = false;
            }
            EasyTransferList.access$002(easytransferlist, flag);
            if (!EasyTransferList.access$000(EasyTransferList.this))
            {
                EasyTransferList.access$302(EasyTransferList.this, 0);
                return;
            }
            if (!hasMessages(2))
            {
                sendEmptyMessageDelayed(2, 500L);
                return;
            }
        }
        if (true) goto _L1; else goto _L4
_L4:
        EasyTransferList.access$400(EasyTransferList.this);
        EasyTransferList.access$302(EasyTransferList.this, 0);
        EasyTransferList.access$002(EasyTransferList.this, flag);
        return;
    }

    public void release()
    {
        removeMessages(0);
        removeMessages(1);
        removeMessages(2);
    }

    public (Looper looper)
    {
        this$0 = EasyTransferList.this;
        super(looper);
    }
}
