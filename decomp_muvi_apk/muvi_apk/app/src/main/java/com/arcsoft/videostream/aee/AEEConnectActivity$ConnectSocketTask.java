// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.os.AsyncTask;
import android.os.Handler;
import com.arcsoft.util.debug.Log;
import com.arcsoft.videostream.rtsp.RtspUtils;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEConnectActivity, AEESocketClient

public class this._cls0 extends AsyncTask
{

    final AEEConnectActivity this$0;

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((String[])aobj);
    }

    protected transient Void doInBackground(String as[])
    {
        Log.e("AEEConnectActivity", (new StringBuilder()).append("ConnectSocketTask RtspUtils.isAmbar() = ").append(RtspUtils.isAmbar()).toString());
        if (RtspUtils.isAmbar())
        {
            boolean _tmp = AEEConnectActivity.access$800(AEEConnectActivity.this);
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
            break MISSING_BLOCK_LABEL_105;
        }
        AEEConnectActivity.access$702(AEEConnectActivity.this, AEESocketClient.getInstanceClient());
        if (AEEConnectActivity.access$700(AEEConnectActivity.this) != null && AEEConnectActivity.access$700(AEEConnectActivity.this).isConnected())
        {
            AEEConnectActivity.access$902(AEEConnectActivity.this, true);
            if (AEEConnectActivity.access$1000(AEEConnectActivity.this) != null)
            {
                AEEConnectActivity.access$1000(AEEConnectActivity.this).removeMessages(1);
                AEEConnectActivity.access$1000(AEEConnectActivity.this).sendEmptyMessage(1);
                return;
            }
            break MISSING_BLOCK_LABEL_105;
        }
        try
        {
            switchTo(2);
            return;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        switchTo(2);
    }

    public ()
    {
        this$0 = AEEConnectActivity.this;
        super();
    }
}
