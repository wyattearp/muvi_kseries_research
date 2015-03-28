// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.service;

import android.content.Context;
import android.content.Intent;
import com.arcsoft.mediaplus.updownload.easytransfer.EasyTransferDriver;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.arcsoft.mediaplus.updownload.service:
//            UpDownloadEngine

class this._cls0
    implements com.arcsoft.mediaplus.updownload.adListener
{

    final UpDownloadEngine this$0;

    public void onDownloadFinished(com.arcsoft.mediaplus.updownload.dResult dresult)
    {
        ArrayList arraylist;
        Log.i("UpDownloadEngine", (new StringBuilder()).append("onDownloadFinished id=").append(dresult.tableid).append(" ,result=").append(dresult.errorcode).toString());
        UpDownloadEngine.access$700(UpDownloadEngine.this, dresult);
        Exception exception;
        Iterator iterator;
        if (dresult.errorcode != 816 && dresult.errorcode != 818 && dresult.errorcode != 817 && dresult.errorcode != 819)
        {
            if (dresult.errorcode == 911)
            {
                Intent intent = new Intent("files_download_finished");
                UpDownloadEngine.access$800(UpDownloadEngine.this).sendBroadcast(intent);
                UpDownloadEngine.access$900(UpDownloadEngine.this, 0, 0);
            } else
            {
                UpDownloadEngine.access$900(UpDownloadEngine.this, 0, 1);
            }
        } else
        {
            UpDownloadEngine.access$900(UpDownloadEngine.this, 0, 2);
        }
        arraylist = UpDownloadEngine.access$300(UpDownloadEngine.this);
        arraylist;
        JVM INSTR monitorenter ;
        for (iterator = UpDownloadEngine.access$300(UpDownloadEngine.this).iterator(); iterator.hasNext(); ((nUpDownloadListener)iterator.next()).onUpDownloadFinish(dresult.request.dms_uuid, dresult.request.uri, dresult, dresult.errorcode)) { }
        break MISSING_BLOCK_LABEL_230;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
        arraylist;
        JVM INSTR monitorexit ;
        UpDownloadEngine.access$500(UpDownloadEngine.this).onDownloadFinished(dresult);
        if (dresult.errorcode != 819 && dresult.errorcode != 817)
        {
            UpDownloadEngine.access$1000(UpDownloadEngine.this);
        }
        return;
    }

    public void onDownloadProgress(com.arcsoft.mediaplus.updownload.dRequest drequest, long l)
    {
        mCount = System.currentTimeMillis() - tick;
        if (mCount <= 1400L)
        {
            break MISSING_BLOCK_LABEL_190;
        }
        Log.d("UpDownloadEngine", (new StringBuilder()).append("tick = ").append(mCount).toString());
        UpDownloadEngine.access$102(UpDownloadEngine.this, (int)(100F * ((float)drequest.downloadSize / (float)drequest.fileSize)));
        ArrayList arraylist = UpDownloadEngine.access$300(UpDownloadEngine.this);
        arraylist;
        JVM INSTR monitorenter ;
        for (Iterator iterator = UpDownloadEngine.access$300(UpDownloadEngine.this).iterator(); iterator.hasNext(); ((nUpDownloadListener)iterator.next()).onProgress(drequest.dms_uuid, drequest.uri, drequest.downloadSize, drequest.fileSize)) { }
        break MISSING_BLOCK_LABEL_162;
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
        arraylist;
        JVM INSTR monitorexit ;
        UpDownloadEngine.access$600(UpDownloadEngine.this, drequest.downloadSize, drequest.fileSize);
        tick = System.currentTimeMillis();
    }

    public void onDownloadStarted(com.arcsoft.mediaplus.updownload.dRequest drequest, long l)
    {
        Log.i("UpDownloadEngine", (new StringBuilder()).append("onDownloadStarted id=").append(l).toString());
        UpDownloadEngine.access$102(UpDownloadEngine.this, 0);
        UpDownloadEngine.access$200(UpDownloadEngine.this, drequest);
        ArrayList arraylist = UpDownloadEngine.access$300(UpDownloadEngine.this);
        arraylist;
        JVM INSTR monitorenter ;
        for (Iterator iterator = UpDownloadEngine.access$300(UpDownloadEngine.this).iterator(); iterator.hasNext(); ((nUpDownloadListener)iterator.next()).onUpDownloadStart(drequest.dms_uuid, drequest.uri)) { }
        break MISSING_BLOCK_LABEL_110;
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
        arraylist;
        JVM INSTR monitorexit ;
        UpDownloadEngine.access$400(UpDownloadEngine.this, 0, drequest.title, drequest.downloadSize, drequest.fileSize);
        UpDownloadEngine.access$500(UpDownloadEngine.this).onDownloadStarted(drequest, l);
        tick = System.currentTimeMillis();
        return;
    }

    ()
    {
        this$0 = UpDownloadEngine.this;
        super();
    }
}
