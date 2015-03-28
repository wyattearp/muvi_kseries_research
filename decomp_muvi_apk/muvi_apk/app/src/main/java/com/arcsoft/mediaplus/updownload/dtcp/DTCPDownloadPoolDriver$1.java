// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.dtcp;

import android.widget.Toast;
import com.arcsoft.adk.atv.dtcp.IDtcpSinkListener;
import com.arcsoft.util.debug.Log;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

// Referenced classes of package com.arcsoft.mediaplus.updownload.dtcp:
//            DTCPDownloadPoolDriver

class this._cls0
    implements IDtcpSinkListener
{

    final DTCPDownloadPoolDriver this$0;

    public void OnDtcpSinkAkeEnd(int i)
    {
        Log.d("DTCPDownloadPoolDriver", "OnDtcpSinkAkeEnd");
        if (i != 0)
        {
            Toast.makeText(DTCPDownloadPoolDriver.access$000(DTCPDownloadPoolDriver.this), (new StringBuilder()).append("ake error").append(i).toString(), 0).show();
            DTCPDownloadPoolDriver.access$200(DTCPDownloadPoolDriver.this, DTCPDownloadPoolDriver.access$100(DTCPDownloadPoolDriver.this), 820);
            return;
        }
        Toast.makeText(DTCPDownloadPoolDriver.access$300(DTCPDownloadPoolDriver.this), "ake ok", 0).show();
        DTCPDownloadPoolDriver.access$400(DTCPDownloadPoolDriver.this).writeLock().lock();
        CPDownloadTask cpdownloadtask = new CPDownloadTask(DTCPDownloadPoolDriver.this, DTCPDownloadPoolDriver.access$100(DTCPDownloadPoolDriver.this), DTCPDownloadPoolDriver.access$500(DTCPDownloadPoolDriver.this));
        mThreadPool.execute(cpdownloadtask);
        DTCPDownloadPoolDriver.access$600(DTCPDownloadPoolDriver.this).writeLock().unlock();
        return;
        RejectedExecutionException rejectedexecutionexception;
        rejectedexecutionexception;
        Log.e("DTCPDownloadPoolDriver", "RejectedExecutionException at discretion of RejectedExecutionHandler, if the task cannot be accepted for execution");
        DTCPDownloadPoolDriver.access$600(DTCPDownloadPoolDriver.this).writeLock().unlock();
        return;
        NullPointerException nullpointerexception;
        nullpointerexception;
        Log.e("DTCPDownloadPoolDriver", "NullPointerException - request is null");
        DTCPDownloadPoolDriver.access$600(DTCPDownloadPoolDriver.this).writeLock().unlock();
        return;
        Exception exception;
        exception;
        DTCPDownloadPoolDriver.access$600(DTCPDownloadPoolDriver.this).writeLock().unlock();
        throw exception;
    }

    CPDownloadTask()
    {
        this$0 = DTCPDownloadPoolDriver.this;
        super();
    }
}
