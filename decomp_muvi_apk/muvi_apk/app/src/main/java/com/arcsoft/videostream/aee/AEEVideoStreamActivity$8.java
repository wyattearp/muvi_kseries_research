// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.net.NetworkInfo;
import android.os.Handler;
import com.arcsoft.util.debug.Log;
import java.io.IOException;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEVideoStreamActivity, AEESocketClient

class this._cls0
    implements com.arcsoft.util.os.tyChangeListener
{

    final AEEVideoStreamActivity this$0;

    public void OnConnectivityChanged(com.arcsoft.util.os.nfo nfo)
    {
        if (nfo.networkInfo != null && nfo.networkInfo.getType() == 1)
        {
label0:
            {
                Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("OnConnectivityChanged  ---------- info.networkInfo.isConnected() = ").append(nfo.networkInfo.isConnected()).toString());
                AEEVideoStreamActivity.access$2502(AEEVideoStreamActivity.this, nfo.networkInfo.isConnected());
                AEEVideoStreamActivity aeevideostreamactivity = AEEVideoStreamActivity.this;
                boolean flag = AEEVideoStreamActivity.access$2500(AEEVideoStreamActivity.this);
                int i = 0;
                byte byte0;
                if (!flag)
                {
                    i = 1;
                }
                if (AEEVideoStreamActivity.access$2500(AEEVideoStreamActivity.this))
                {
                    byte0 = -1;
                } else
                {
                    byte0 = 11;
                }
                aeevideostreamactivity.switchTo(i, byte0);
                try
                {
                    mSocketClient = AEESocketClient.getInstanceClient();
                    if (!AEEVideoStreamActivity.access$2500(AEEVideoStreamActivity.this))
                    {
                        continue; /* Loop/switch isn't completed */
                    }
                    if (AEEVideoStreamActivity.access$1800(AEEVideoStreamActivity.this) != 0x10000066)
                    {
                        if (mSocketClient == null || !mSocketClient.isConnected())
                        {
                            break MISSING_BLOCK_LABEL_281;
                        }
                        if (AEEVideoStreamActivity.access$1800(AEEVideoStreamActivity.this) == -1)
                        {
                            if (mSocketClient.getCurTokenId() > 0)
                            {
                                AEEVideoStreamActivity.access$1602(AEEVideoStreamActivity.this, true);
                                AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x1000002b, 0x10000033, 0L);
                                return;
                            }
                            break label0;
                        }
                    }
                }
                catch (IOException ioexception)
                {
                    ioexception.printStackTrace();
                    return;
                }
            }
        }
_L2:
        return;
        synchronized (AEEVideoStreamActivity.access$600(AEEVideoStreamActivity.this))
        {
            AEEVideoStreamActivity.access$600(AEEVideoStreamActivity.this).removeMessages(7);
            AEEVideoStreamActivity.access$600(AEEVideoStreamActivity.this).sendEmptyMessage(7);
        }
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
        if (AEEVideoStreamActivity.access$3700(AEEVideoStreamActivity.this) != null)
        {
            AEEVideoStreamActivity.access$3700(AEEVideoStreamActivity.this).cancel(true);
            AEEVideoStreamActivity.access$3702(AEEVideoStreamActivity.this, null);
        }
        AEEVideoStreamActivity.access$3702(AEEVideoStreamActivity.this, new nnectSocketTask(AEEVideoStreamActivity.this));
        AEEVideoStreamActivity.access$3700(AEEVideoStreamActivity.this).execute(new String[0]);
        return;
        if (mSocketClient == null || !mSocketClient.isConnected()) goto _L2; else goto _L1
_L1:
        mSocketClient.close();
        mSocketClient = null;
        return;
    }

    nnectSocketTask()
    {
        this$0 = AEEVideoStreamActivity.this;
        super();
    }
}
