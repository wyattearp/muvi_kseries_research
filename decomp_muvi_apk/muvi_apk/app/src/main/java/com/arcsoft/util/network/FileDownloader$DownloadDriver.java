// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.network;

import android.os.Process;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.tool.SafeBuffer;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.arcsoft.util.network:
//            FileDownloader

private class <init> extends Thread
{

    private final AtomicInteger mStatus;
    final FileDownloader this$0;

    private boolean isResumed()
    {
        return mStatus.get() == 1;
    }

    private void pauseDriver()
    {
label0:
        {
            synchronized (mStatus)
            {
                if (mStatus.get() != 2)
                {
                    break label0;
                }
            }
            return;
        }
        mStatus.set(0);
        atomicinteger;
        JVM INSTR monitorexit ;
        return;
        exception;
        atomicinteger;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void requestQuit()
    {
        mStatus.set(2);
        FileDownloader.access$2200(FileDownloader.this, FileDownloader.access$2300(FileDownloader.this));
        FileDownloader.access$2200(FileDownloader.this, FileDownloader.access$2400(FileDownloader.this));
        FileDownloader.access$2200(FileDownloader.this, this);
    }

    private void resumeDriver()
    {
        synchronized (mStatus)
        {
            if (mStatus.get() == 0)
            {
                mStatus.set(1);
                FileDownloader.access$2200(FileDownloader.this, this);
            }
        }
        return;
        exception;
        atomicinteger;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void run()
    {
        Process.setThreadPriority(10);
_L3:
        if (mStatus.get() == 2)
        {
            break; /* Loop/switch isn't completed */
        }
        this;
        JVM INSTR monitorenter ;
        if (mStatus.get() == 1)
        {
            break MISSING_BLOCK_LABEL_47;
        }
        FileDownloader.access$2500(FileDownloader.this, this);
        this;
        JVM INSTR monitorexit ;
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        this;
        JVM INSTR monitorexit ;
        java.util.ArrayList arraylist = FileDownloader.access$2300(FileDownloader.this);
        arraylist;
        JVM INSTR monitorenter ;
          = FileDownloader.access$2600(FileDownloader.this);
        if ( != null)
        {
            break MISSING_BLOCK_LABEL_97;
        }
        FileDownloader.access$2500(FileDownloader.this, FileDownloader.access$2300(FileDownloader.this));
        arraylist;
        JVM INSTR monitorexit ;
        continue; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception1;
        arraylist;
        JVM INSTR monitorexit ;
         1 = FileDownloader.access$2400(FileDownloader.this);
        1;
        JVM INSTR monitorenter ;
         2 = ()s._mth700(FileDownloader.access$2400(FileDownloader.this)).get();
        if (2 != null)
        {
            break MISSING_BLOCK_LABEL_305;
        }
        2 = ()s._mth800(FileDownloader.access$2400(FileDownloader.this)).get();
        if (2 != null)
        {
            break MISSING_BLOCK_LABEL_185;
        }
        FileDownloader.access$2500(FileDownloader.this, FileDownloader.access$2400(FileDownloader.this));
        1;
        JVM INSTR monitorexit ;
        continue; /* Loop/switch isn't completed */
        Exception exception2;
        exception2;
        1;
        JVM INSTR monitorexit ;
        throw exception2;
        .access._mth2002(2, false);
_L1:
        1;
        JVM INSTR monitorexit ;
        if (FileDownloader.access$2700(FileDownloader.this, 2))
        {
            continue; /* Loop/switch isn't completed */
        }
        try
        {
            String s = getParsedFilePath(2.parentdir, 2.url);
            if ((new File(s)).exists())
            {
                Log.i("FileDownloader", (new StringBuilder()).append("DownloadThread file exist: ").append(2.index).toString());
                .access._mth2802(2, s);
                FileDownloader.access$2900(FileDownloader.this, 2);
                continue; /* Loop/switch isn't completed */
            }
        }
        catch (Exception exception3)
        {
            FileDownloader.access$3000(FileDownloader.this, 2, 4);
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_315;
        .access._mth2002(2, true);
          goto _L1
        .startDownload(2);
        if (true) goto _L3; else goto _L2
_L2:
    }





    private ()
    {
        this$0 = FileDownloader.this;
        super();
        mStatus = new AtomicInteger();
        mStatus.set(1);
    }

    mStatus(mStatus mstatus)
    {
        this();
    }
}
