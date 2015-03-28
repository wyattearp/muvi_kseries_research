// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.os.AsyncTask;
import com.arcsoft.videostream.rtsp.RtspUtils;
import java.io.IOException;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEESocketClient, AEEVideoStreamActivity

public class this._cls0 extends AsyncTask
{

    final AEEVideoStreamActivity this$0;

    protected transient Boolean doInBackground(Void avoid[])
    {
        Boolean boolean1;
        boolean1 = Boolean.valueOf(false);
        if (!RtspUtils.isAmbar())
        {
            break MISSING_BLOCK_LABEL_113;
        }
        mSocketClient = AEESocketClient.getInstanceClient();
        if (mSocketClient == null || !mSocketClient.isConnected())
        {
            break MISSING_BLOCK_LABEL_84;
        }
        mSocketClient.sendCommandSuc(0x10000001, null);
        mSocketClient.startRespondParams(0x10000001);
        mSocketClient.setNextCMD(0x1000002b);
        return boolean1;
        try
        {
            switchTo(1, 12);
        }
        catch (IOException ioexception)
        {
            switchTo(1, 12);
            ioexception.printStackTrace();
            return boolean1;
        }
        return boolean1;
        switchTo(2, -1);
        showSurfaceView(0);
        return boolean1;
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((Void[])aobj);
    }

    protected void onPostExecute(Boolean boolean1)
    {
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((Boolean)obj);
    }

    public ()
    {
        this$0 = AEEVideoStreamActivity.this;
        super();
    }
}
