// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.service;

import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.arcsoft.mediaplus.updownload.service:
//            UpDownloadEngine

class this._cls0
    implements com.arcsoft.mediaplus.updownload.ploadListener
{

    final UpDownloadEngine this$0;

    public void onUploadFinished(com.arcsoft.mediaplus.updownload.loadResult loadresult)
    {
        ArrayList arraylist;
        Log.i("UpDownloadEngine", (new StringBuilder()).append("onUploadFinished id=").append(loadresult.tableid).append(" ,result=").append(loadresult.errorcode).toString());
        UpDownloadEngine.access$1200(UpDownloadEngine.this, loadresult);
        if (loadresult.errorcode != 819 && loadresult.errorcode != 817)
        {
            UpDownloadEngine.access$1000(UpDownloadEngine.this);
            Exception exception;
            Iterator iterator;
            if (loadresult.errorcode == 1015)
            {
                UpDownloadEngine.access$900(UpDownloadEngine.this, 1, 0);
            } else
            {
                UpDownloadEngine.access$900(UpDownloadEngine.this, 1, 1);
            }
        } else
        {
            UpDownloadEngine.access$900(UpDownloadEngine.this, 1, 2);
        }
        arraylist = UpDownloadEngine.access$300(UpDownloadEngine.this);
        arraylist;
        JVM INSTR monitorenter ;
        for (iterator = UpDownloadEngine.access$300(UpDownloadEngine.this).iterator(); iterator.hasNext(); ((nUpDownloadListener)iterator.next()).onUpDownloadFinish(loadresult.request.dms_uuid, loadresult.request.uri, loadresult, loadresult.errorcode)) { }
        break MISSING_BLOCK_LABEL_195;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
        arraylist;
        JVM INSTR monitorexit ;
    }

    public void onUploadProgress(com.arcsoft.mediaplus.updownload.loadRequest loadrequest, long l)
    {
        Log.i("UpDownloadEngine", (new StringBuilder()).append("onUploadProgress id=").append(l).toString());
        mCount = System.currentTimeMillis() - tick;
        if (mCount <= 500L)
        {
            break MISSING_BLOCK_LABEL_161;
        }
        ArrayList arraylist = UpDownloadEngine.access$300(UpDownloadEngine.this);
        arraylist;
        JVM INSTR monitorenter ;
        for (Iterator iterator = UpDownloadEngine.access$300(UpDownloadEngine.this).iterator(); iterator.hasNext(); ((nUpDownloadListener)iterator.next()).onProgress(loadrequest.dms_uuid, loadrequest.uri, loadrequest.uploadSize, loadrequest.fileSize)) { }
        break MISSING_BLOCK_LABEL_133;
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
        arraylist;
        JVM INSTR monitorexit ;
        UpDownloadEngine.access$600(UpDownloadEngine.this, loadrequest.uploadSize, loadrequest.fileSize);
        tick = System.currentTimeMillis();
    }

    public void onUploadStarted(com.arcsoft.mediaplus.updownload.loadRequest loadrequest, long l)
    {
        Log.i("UpDownloadEngine", (new StringBuilder()).append("onUploadStarted id=").append(l).toString());
        UpDownloadEngine.access$1100(UpDownloadEngine.this, loadrequest);
        ArrayList arraylist = UpDownloadEngine.access$300(UpDownloadEngine.this);
        arraylist;
        JVM INSTR monitorenter ;
        for (Iterator iterator = UpDownloadEngine.access$300(UpDownloadEngine.this).iterator(); iterator.hasNext(); ((nUpDownloadListener)iterator.next()).onUpDownloadStart(loadrequest.dms_uuid, loadrequest.uri)) { }
        break MISSING_BLOCK_LABEL_101;
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
        arraylist;
        JVM INSTR monitorexit ;
        UpDownloadEngine.access$400(UpDownloadEngine.this, 1, loadrequest.title, loadrequest.uploadSize, loadrequest.fileSize);
        return;
    }

    est()
    {
        this$0 = UpDownloadEngine.this;
        super();
    }
}
