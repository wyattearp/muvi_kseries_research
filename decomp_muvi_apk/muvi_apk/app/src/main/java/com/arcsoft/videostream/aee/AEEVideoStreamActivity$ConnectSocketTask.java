// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.os.AsyncTask;
import android.os.Handler;
import com.arcsoft.util.debug.Log;
import com.arcsoft.videostream.rtsp.RtspUtils;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEVideoStreamActivity, AEESocketClient

public class this._cls0 extends AsyncTask
{

    final AEEVideoStreamActivity this$0;

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((String[])aobj);
    }

    protected transient Void doInBackground(String as[])
    {
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("ConnectSocketTask RtspUtils.isAmbar() = ").append(RtspUtils.isAmbar()).toString());
        if (RtspUtils.isAmbar())
        {
            boolean _tmp = AEEVideoStreamActivity.access$3800(AEEVideoStreamActivity.this);
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
            break MISSING_BLOCK_LABEL_121;
        }
        mSocketClient = AEESocketClient.getInstanceClient();
        if (mSocketClient != null && mSocketClient.isConnected() && AEEVideoStreamActivity.access$1800(AEEVideoStreamActivity.this) == -1)
        {
            synchronized (AEEVideoStreamActivity.access$600(AEEVideoStreamActivity.this))
            {
                AEEVideoStreamActivity.access$600(AEEVideoStreamActivity.this).removeMessages(7);
                AEEVideoStreamActivity.access$600(AEEVideoStreamActivity.this).sendEmptyMessage(7);
            }
            return;
        }
        break MISSING_BLOCK_LABEL_111;
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
        this$0 = AEEVideoStreamActivity.this;
        super();
    }
}
