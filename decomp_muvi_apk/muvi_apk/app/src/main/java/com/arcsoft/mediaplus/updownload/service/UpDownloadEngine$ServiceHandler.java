// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.service;

import android.os.Handler;
import android.os.Message;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.mediaplus.updownload.DownloadPoolDriver;
import com.arcsoft.mediaplus.updownload.UploadPoolDriver;
import com.arcsoft.mediaplus.updownload.dtcp.DTCPDownloadPoolDriver;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.arcsoft.mediaplus.updownload.service:
//            UpDownloadEngine

private class <init> extends Handler
{

    final UpDownloadEngine this$0;

    public void handleMessage(Message message)
    {
        if (message.what != 0) goto _L2; else goto _L1
_L1:
        ((Runnable)message.obj).run();
_L4:
        return;
_L2:
        if (message.what != 1)
        {
            break MISSING_BLOCK_LABEL_155;
        }
        if (!UpDownloadEngine.access$1300(UpDownloadEngine.this))
        {
            String s = Settings.instance().getDefaultDMSUDN();
            UpDownloadEngine.access$2800(UpDownloadEngine.this).abortTask(s);
            UpDownloadEngine.access$1800(UpDownloadEngine.this).abortTask(s);
            UpDownloadEngine.access$2900(UpDownloadEngine.this).abortTask(s);
        }
        ArrayList arraylist2 = UpDownloadEngine.access$300(UpDownloadEngine.this);
        arraylist2;
        JVM INSTR monitorenter ;
        for (Iterator iterator2 = UpDownloadEngine.access$300(UpDownloadEngine.this).iterator(); iterator2.hasNext(); ((stener)iterator2.next()).onUpDownloadFinish(null, null, null, 819)) { }
        break MISSING_BLOCK_LABEL_151;
        Exception exception2;
        exception2;
        arraylist2;
        JVM INSTR monitorexit ;
        throw exception2;
        arraylist2;
        JVM INSTR monitorexit ;
        return;
        if (message.what == 2)
        {
            UpDownloadEngine.access$3000(UpDownloadEngine.this);
            return;
        }
        if (message.what != 3)
        {
            continue; /* Loop/switch isn't completed */
        }
        UpDownloadEngine.access$3100(UpDownloadEngine.this);
        UpDownloadEngine.access$3200(UpDownloadEngine.this, false, -1, true);
        ArrayList arraylist1 = UpDownloadEngine.access$300(UpDownloadEngine.this);
        arraylist1;
        JVM INSTR monitorenter ;
        for (Iterator iterator1 = UpDownloadEngine.access$300(UpDownloadEngine.this).iterator(); iterator1.hasNext(); ((stener)iterator1.next()).onUpDownloadFinish(null, null, null, 817)) { }
        break MISSING_BLOCK_LABEL_263;
        Exception exception1;
        exception1;
        arraylist1;
        JVM INSTR monitorexit ;
        throw exception1;
        arraylist1;
        JVM INSTR monitorexit ;
        return;
        if (message.what != 4) goto _L4; else goto _L3
_L3:
        UpDownloadEngine.access$3100(UpDownloadEngine.this);
        abortAllTask();
        UpDownloadEngine.access$3200(UpDownloadEngine.this, true, 1, false);
        ArrayList arraylist = UpDownloadEngine.access$300(UpDownloadEngine.this);
        arraylist;
        JVM INSTR monitorenter ;
        for (Iterator iterator = UpDownloadEngine.access$300(UpDownloadEngine.this).iterator(); iterator.hasNext(); ((stener)iterator.next()).onUpDownloadFinish(null, null, null, 819)) { }
        break MISSING_BLOCK_LABEL_367;
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
        arraylist;
        JVM INSTR monitorexit ;
    }

    void release()
    {
        removeMessages(0);
        removeMessages(1);
        removeMessages(2);
        removeMessages(3);
        removeMessages(4);
    }

    private stener()
    {
        this$0 = UpDownloadEngine.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
