// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.os.AsyncTask;
import android.os.Handler;
import com.arcsoft.util.debug.Log;
import com.arcsoft.videostream.rtsp.RtspUtils;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEVideoStreamFullScreenActivity, AEESocketClient

public class this._cls0 extends AsyncTask
{

    final AEEVideoStreamFullScreenActivity this$0;

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((String[])aobj);
    }

    protected transient Void doInBackground(String as[])
    {
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("ConnectSocketTask RtspUtils.isAmbar() = ").append(RtspUtils.isAmbar()).toString());
        if (RtspUtils.isAmbar())
        {
            boolean _tmp = AEEVideoStreamFullScreenActivity.access$3700(AEEVideoStreamFullScreenActivity.this);
        }
        return null;
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((Void)obj);
    }

    protected void onPostExecute(Void void1)
    {
        if (!RtspUtils.isAmbar())
        {
            break MISSING_BLOCK_LABEL_110;
        }
        mSocketClient = AEESocketClient.getInstanceClient();
        if (mSocketClient != null && mSocketClient.isConnected())
        {
            synchronized (AEEVideoStreamFullScreenActivity.access$900(AEEVideoStreamFullScreenActivity.this))
            {
                AEEVideoStreamFullScreenActivity.access$900(AEEVideoStreamFullScreenActivity.this).removeMessages(7);
                AEEVideoStreamFullScreenActivity.access$900(AEEVideoStreamFullScreenActivity.this).sendEmptyMessage(7);
            }
            return;
        }
        break MISSING_BLOCK_LABEL_100;
        exception1;
        handler;
        JVM INSTR monitorexit ;
        try
        {
            throw exception1;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        switchTo(1, 12);
        return;
        switchTo(1, 12);
    }

    public ()
    {
        this$0 = AEEVideoStreamFullScreenActivity.this;
        super();
    }
}
