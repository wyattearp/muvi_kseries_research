// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.tool;

import android.os.Handler;
import android.os.Message;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.util.debug.Log;
import java.io.File;
import java.io.FilenameFilter;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package com.arcsoft.util.tool:
//            CachePathManager

private class mHandler extends Thread
{

    Handler mHandler;
    AtomicBoolean quitFlag;
    final CachePathManager this$0;

    private boolean deleteRecurisive(String s)
    {
        File file;
        for (file = new File(s); !file.exists() || file.isFile();)
        {
            return false;
        }

        File afile[] = file.listFiles();
        int i = afile.length;
        int j = 0;
        while (j < i) 
        {
            File file1 = afile[j];
            if (file1.isDirectory())
            {
                deleteRecurisive(file1.getAbsolutePath());
            } else
            if (!file1.delete())
            {
                Log.e("CacheManager", (new StringBuilder()).append(file1.getAbsolutePath()).append(" can't deleted!").toString());
            }
            j++;
        }
        if (!file.delete())
        {
            Log.e("CacheManager", (new StringBuilder()).append(file.getAbsolutePath()).append(" can't delete!").toString());
            return false;
        } else
        {
            return true;
        }
    }

    public void quit()
    {
        this;
        JVM INSTR monitorenter ;
        notify();
        this;
        JVM INSTR monitorexit ;
        mHandler.removeMessages(0);
        quitFlag.set(true);
        Exception exception;
        try
        {
            join(2000L);
            return;
        }
        catch (Exception exception1)
        {
            return;
        }
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void run()
    {
        File file = new File(OEMConfig.CACHE_BASE_PATH);
_L2:
        if (quitFlag.get())
        {
            break; /* Loop/switch isn't completed */
        }
        File afile[] = file.listFiles(new FilenameFilter() {

            final CachePathManager.ClearCacheThread this$1;

            public boolean accept(File file2, String s)
            {
                return s != null && s.endsWith("_delete");
            }

            
            {
                this$1 = CachePathManager.ClearCacheThread.this;
                super();
            }
        });
        this;
        JVM INSTR monitorenter ;
        if (afile == null)
        {
            break MISSING_BLOCK_LABEL_49;
        }
        int k = afile.length;
        if (k > 0)
        {
            break MISSING_BLOCK_LABEL_74;
        }
        Log.e("CleatThread", "Wait for task");
        wait();
        this;
        JVM INSTR monitorexit ;
        continue; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        this;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        CachePathManager.access$100(CachePathManager.this, true);
        int i = afile.length;
        for (int j = 0; j < i; j++)
        {
            File file1 = afile[j];
            Log.e("CleatThread", (new StringBuilder()).append("Delete Cache directory : ").append(file1.getAbsolutePath()).toString());
            deleteRecurisive(file1.getAbsolutePath());
        }

        CachePathManager.access$100(CachePathManager.this, false);
        if (true) goto _L2; else goto _L1
_L1:
    }

    _cls2.this._cls1()
    {
        this$0 = CachePathManager.this;
        super();
        quitFlag = new AtomicBoolean(false);
        mHandler = new Handler() {

            final CachePathManager.ClearCacheThread this$1;

            public void handleMessage(Message message)
            {
                synchronized (CachePathManager.ClearCacheThread.this)
                {
                    notify();
                }
                sendEmptyMessageDelayed(0, 60000L);
                return;
                exception;
                clearcachethread;
                JVM INSTR monitorexit ;
                throw exception;
            }

            
            {
                this$1 = CachePathManager.ClearCacheThread.this;
                super();
            }
        };
        mHandler.sendEmptyMessageDelayed(0, 60000L);
    }
}
