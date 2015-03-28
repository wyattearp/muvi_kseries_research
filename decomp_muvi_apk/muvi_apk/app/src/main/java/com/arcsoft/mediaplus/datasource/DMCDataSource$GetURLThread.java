// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import com.arcsoft.util.debug.DebugUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.tool.SafeBuffer;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            DMCDataSource

private class <init> extends Thread
{

    private static final int STATUS_DOING = 1;
    private static final int STATUS_NONE = 0;
    private static final int STATUS_QUIT = 2;
     mRequest;
    private final AtomicInteger mStatus;
    final DMCDataSource this$0;

    private void requestCancel()
    {
        this;
        JVM INSTR monitorenter ;
        if (mRequest != null)
        {
            mRequest.mCancelFlag = true;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void requestQuit()
    {
        requestCancel();
        mStatus.set(2);
        DMCDataSource.access$500(DMCDataSource.this, DMCDataSource.access$400(DMCDataSource.this));
        DMCDataSource.access$500(DMCDataSource.this, this);
    }

    public void run()
    {
_L2:
        if (mStatus.get() == 2)
        {
            break; /* Loop/switch isn't completed */
        }
        this;
        JVM INSTR monitorenter ;
        if (mStatus.get() == 1)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        DMCDataSource.access$600(DMCDataSource.this, this);
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
        SafeBuffer safebuffer = DMCDataSource.access$400(DMCDataSource.this);
        safebuffer;
        JVM INSTR monitorenter ;
          = ()DMCDataSource.access$400(DMCDataSource.this).get();
        if ( != null)
        {
            break MISSING_BLOCK_LABEL_98;
        }
        DMCDataSource.access$600(DMCDataSource.this, DMCDataSource.access$400(DMCDataSource.this));
        safebuffer;
        JVM INSTR monitorexit ;
        continue; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        safebuffer;
        JVM INSTR monitorexit ;
        throw exception1;
        safebuffer;
        JVM INSTR monitorexit ;
        this;
        JVM INSTR monitorenter ;
        mRequest = ;
        this;
        JVM INSTR monitorexit ;
         1;
        long l = System.currentTimeMillis();
        1 = DMCDataSource.access$700(DMCDataSource.this, );
        long l1 = System.currentTimeMillis();
        if (l1 - l > 50L)
        {
            Log.w("DMSDataSource", (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append("getResourceInfos cost: ").append(l1 - l).toString());
        }
        this;
        JVM INSTR monitorenter ;
        mRequest = null;
        this;
        JVM INSTR monitorexit ;
        Exception exception2;
        Exception exception3;
        if (1 != null)
        {
            if (.listener != null)
            {
                .listener.onGetPlayURL(1.resource._strUri, 1.metadata, .userdata);
            }
        } else
        if (.listener != null)
        {
            .listener.onGetPlayURLError(.userdata);
        }
        continue; /* Loop/switch isn't completed */
        exception2;
        this;
        JVM INSTR monitorexit ;
        throw exception2;
        exception3;
        this;
        JVM INSTR monitorexit ;
        throw exception3;
        if (true) goto _L2; else goto _L1
_L1:
    }



    private ()
    {
        this$0 = DMCDataSource.this;
        super();
        mRequest = null;
        mStatus = new AtomicInteger(0);
        mStatus.set(1);
    }

    mStatus(mStatus mstatus)
    {
        this();
    }
}
