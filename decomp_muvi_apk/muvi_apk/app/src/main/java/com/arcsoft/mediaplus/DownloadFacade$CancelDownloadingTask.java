// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.os.AsyncTask;
import android.os.Handler;
import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;

// Referenced classes of package com.arcsoft.mediaplus:
//            DownloadFacade

class this._cls0 extends AsyncTask
{

    final DownloadFacade this$0;

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((Void[])aobj);
    }

    protected transient Void doInBackground(Void avoid[])
    {
        if (mNeedToDownloadTasks != null)
        {
            mHandler.sendEmptyMessage(5);
            if (mUpDownloadEngine != null)
            {
                mUpDownloadEngine.cancelDownloadTasks(mNeedToDownloadTasks);
                return null;
            }
        }
        return null;
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((Void)obj);
    }

    protected void onPostExecute(Void void1)
    {
        super.onPostExecute(void1);
        mHandler.sendEmptyMessage(6);
    }

    ()
    {
        this$0 = DownloadFacade.this;
        super();
    }
}
