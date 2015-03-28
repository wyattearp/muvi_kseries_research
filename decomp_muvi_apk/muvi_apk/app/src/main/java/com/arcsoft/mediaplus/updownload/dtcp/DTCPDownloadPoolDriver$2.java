// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.dtcp;

import android.widget.Toast;
import com.arcsoft.adk.atv.dtcp.DtcpSinkMove;
import com.arcsoft.adk.atv.dtcp.IDtcpSinkMoveListener;
import com.arcsoft.util.debug.Log;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

// Referenced classes of package com.arcsoft.mediaplus.updownload.dtcp:
//            DTCPDownloadPoolDriver

class this._cls0
    implements IDtcpSinkMoveListener
{

    final DTCPDownloadPoolDriver this$0;

    public void OnDtcpSinkMoveAkeEnd(int i)
    {
        Log.d("DTCPDownloadPoolDriver", "OnDtcpSinkMoveAkeEnd");
        if (i != 0)
        {
            Toast.makeText(DTCPDownloadPoolDriver.access$1300(DTCPDownloadPoolDriver.this), (new StringBuilder()).append("ake error").append(i).toString(), 0).show();
            DTCPDownloadPoolDriver.access$1400(DTCPDownloadPoolDriver.this, DTCPDownloadPoolDriver.access$1000(DTCPDownloadPoolDriver.this), 820);
            return;
        }
        Toast.makeText(DTCPDownloadPoolDriver.access$1500(DTCPDownloadPoolDriver.this), "ake ok", 0).show();
        DTCPDownloadPoolDriver.access$1600(DTCPDownloadPoolDriver.this).writeLock().lock();
        CPDownloadTask cpdownloadtask = new CPDownloadTask(DTCPDownloadPoolDriver.this, DTCPDownloadPoolDriver.access$1000(DTCPDownloadPoolDriver.this), DTCPDownloadPoolDriver.access$1700(DTCPDownloadPoolDriver.this));
        mThreadPool.execute(cpdownloadtask);
        DTCPDownloadPoolDriver.access$1800(DTCPDownloadPoolDriver.this).writeLock().unlock();
        return;
        RejectedExecutionException rejectedexecutionexception;
        rejectedexecutionexception;
        Log.e("DTCPDownloadPoolDriver", "RejectedExecutionException at discretion of RejectedExecutionHandler, if the task cannot be accepted for execution");
        DTCPDownloadPoolDriver.access$1800(DTCPDownloadPoolDriver.this).writeLock().unlock();
        return;
        NullPointerException nullpointerexception;
        nullpointerexception;
        Log.e("DTCPDownloadPoolDriver", "NullPointerException - request is null");
        DTCPDownloadPoolDriver.access$1800(DTCPDownloadPoolDriver.this).writeLock().unlock();
        return;
        Exception exception;
        exception;
        DTCPDownloadPoolDriver.access$1800(DTCPDownloadPoolDriver.this).writeLock().unlock();
        throw exception;
    }

    public void OnDtcpSinkMoveEnd(int i)
    {
        if (i != 0)
        {
            Toast.makeText(DTCPDownloadPoolDriver.access$700(DTCPDownloadPoolDriver.this), (new StringBuilder()).append("move fail : ").append(i).toString(), 0).show();
        } else
        {
            Toast.makeText(DTCPDownloadPoolDriver.access$800(DTCPDownloadPoolDriver.this), (new StringBuilder()).append("move fail : ").append(i).toString(), 0).show();
        }
        DTCPDownloadPoolDriver.access$900(DTCPDownloadPoolDriver.this).writeLock().lock();
        CPMoveDownloadRequest.access._mth1100(DTCPDownloadPoolDriver.access$1000(DTCPDownloadPoolDriver.this)).CloseStream();
        DTCPDownloadPoolDriver.access$1200(DTCPDownloadPoolDriver.this).writeLock().unlock();
    }

    CPMoveDownloadRequest()
    {
        this$0 = DTCPDownloadPoolDriver.this;
        super();
    }
}
