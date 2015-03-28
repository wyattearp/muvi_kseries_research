// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.net.NetworkInfo;
import android.os.Handler;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEVideoStreamFullScreenActivity, AEESocketClient

class this._cls0
    implements com.arcsoft.util.os.stener
{

    final AEEVideoStreamFullScreenActivity this$0;

    public void OnConnectivityChanged(com.arcsoft.util.os.ivity._cls8 _pcls8)
    {
        if (_pcls8.kInfo == null || _pcls8.kInfo.getType() != 1)
        {
            return;
        }
        AEEVideoStreamFullScreenActivity.access$2402(AEEVideoStreamFullScreenActivity.this, _pcls8.kInfo.isConnected());
        Exception exception;
        mSocketClient = AEESocketClient.getInstanceClient();
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("OnConnectivityChanged isWifiConnected = ").append(AEEVideoStreamFullScreenActivity.access$2400(AEEVideoStreamFullScreenActivity.this)).toString());
        if (!AEEVideoStreamFullScreenActivity.access$2400(AEEVideoStreamFullScreenActivity.this))
        {
            break MISSING_BLOCK_LABEL_396;
        }
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("OnConnectivityChanged bStreamConnectDone = ").append(AEEVideoStreamFullScreenActivity.access$3500(AEEVideoStreamFullScreenActivity.this)).append(", mCurConnectParams = ").append(AEEVideoStreamFullScreenActivity.access$1200(AEEVideoStreamFullScreenActivity.this)).toString());
        if (!AEEVideoStreamFullScreenActivity.access$3500(AEEVideoStreamFullScreenActivity.this))
        {
            break MISSING_BLOCK_LABEL_219;
        }
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("OnConnectivityChanged mCurExecuteStatus = ").append(AEEVideoStreamFullScreenActivity.access$1800(AEEVideoStreamFullScreenActivity.this)).toString());
        if (AEEVideoStreamFullScreenActivity.access$1800(AEEVideoStreamFullScreenActivity.this) == 8)
        {
            switchTo(8, -1);
            return;
        }
        try
        {
            switchTo(2, -1);
            showSurfaceView(0);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception)
        {
            exception.printStackTrace();
            return;
        }
        if (AEEVideoStreamFullScreenActivity.access$1200(AEEVideoStreamFullScreenActivity.this) == 18 || AEEVideoStreamFullScreenActivity.access$1200(AEEVideoStreamFullScreenActivity.this) == 20)
        {
            switchTo(1, AEEVideoStreamFullScreenActivity.access$1200(AEEVideoStreamFullScreenActivity.this));
            return;
        }
        switchTo(0, -1);
        if (mSocketClient.isConnected())
        {
            synchronized (AEEVideoStreamFullScreenActivity.access$900(AEEVideoStreamFullScreenActivity.this))
            {
                AEEVideoStreamFullScreenActivity.access$900(AEEVideoStreamFullScreenActivity.this).removeMessages(7);
                AEEVideoStreamFullScreenActivity.access$900(AEEVideoStreamFullScreenActivity.this).sendEmptyMessage(7);
            }
            return;
        }
        break MISSING_BLOCK_LABEL_330;
        exception1;
        handler;
        JVM INSTR monitorexit ;
        throw exception1;
        if (AEEVideoStreamFullScreenActivity.access$3600(AEEVideoStreamFullScreenActivity.this) != null)
        {
            AEEVideoStreamFullScreenActivity.access$3600(AEEVideoStreamFullScreenActivity.this).cancel(true);
            AEEVideoStreamFullScreenActivity.access$3602(AEEVideoStreamFullScreenActivity.this, null);
        }
        AEEVideoStreamFullScreenActivity.access$3602(AEEVideoStreamFullScreenActivity.this, new nnectSocketTask(AEEVideoStreamFullScreenActivity.this));
        AEEVideoStreamFullScreenActivity.access$3600(AEEVideoStreamFullScreenActivity.this).execute(new String[0]);
        return;
        if (mSocketClient != null && mSocketClient.isConnected())
        {
            mSocketClient.close();
            mSocketClient = null;
        }
        switchTo(1, 11);
        return;
    }

    nnectSocketTask()
    {
        this$0 = AEEVideoStreamFullScreenActivity.this;
        super();
    }
}
