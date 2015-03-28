// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import java.io.IOException;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEConnectActivity, AEESocketClient

class this._cls0 extends Handler
{

    final AEEConnectActivity this$0;

    public void handleMessage(Message message)
    {
        message.what;
        JVM INSTR tableswitch 0 8: default 56
    //                   0 62
    //                   1 76
    //                   2 56
    //                   3 56
    //                   4 86
    //                   5 56
    //                   6 56
    //                   7 56
    //                   8 159;
           goto _L1 _L2 _L3 _L1 _L1 _L4 _L1 _L1 _L1 _L5
_L1:
        super.handleMessage(message);
_L11:
        return;
_L2:
        AEEConnectActivity.access$600(AEEConnectActivity.this, message.arg1);
          goto _L1
_L3:
        AEEConnectActivity.access$300(AEEConnectActivity.this);
          goto _L1
_L4:
        int k;
        int l;
        k = message.arg1;
        l = 0;
        k;
        JVM INSTR lookupswitch 2: default 124
    //                   -17: 152
    //                   1: 145;
           goto _L6 _L7 _L8
_L6:
        break; /* Loop/switch isn't completed */
_L7:
        break MISSING_BLOCK_LABEL_152;
_L9:
        if (l != 0)
        {
            Toast.makeText(AEEConnectActivity.this, l, 0).show();
        }
          goto _L1
_L8:
        l = 0x7f0b018e;
          goto _L9
        l = 0x7f0b0198;
          goto _L9
_L5:
        int i;
        int j;
        i = message.arg1;
        j = message.arg2;
        if (i == -1 && j == -1) goto _L11; else goto _L10
_L10:
        AEEConnectActivity.access$702(AEEConnectActivity.this, AEESocketClient.getInstanceClient());
        if (AEEConnectActivity.access$700(AEEConnectActivity.this) != null && AEEConnectActivity.access$700(AEEConnectActivity.this).isConnected())
        {
label0:
            {
                if (i != -1)
                {
                    break label0;
                }
                try
                {
                    AEEConnectActivity.access$700(AEEConnectActivity.this).sendCommand(j, null);
                    AEEConnectActivity.access$700(AEEConnectActivity.this).startRespondParams(j);
                }
                catch (IOException ioexception)
                {
                    ioexception.printStackTrace();
                }
            }
        }
          goto _L1
        AEEConnectActivity.access$700(AEEConnectActivity.this).sendCommand(i, null);
        AEEConnectActivity.access$700(AEEConnectActivity.this).startRespondParams(i);
        AEEConnectActivity.access$700(AEEConnectActivity.this).setNextCMD(j);
          goto _L1
    }

    ()
    {
        this$0 = AEEConnectActivity.this;
        super();
    }
}
