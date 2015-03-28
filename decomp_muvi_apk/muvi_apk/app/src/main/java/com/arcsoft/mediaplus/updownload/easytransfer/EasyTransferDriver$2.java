// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;


// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferDriver

class val.forced
    implements Runnable
{

    final EasyTransferDriver this$0;
    final boolean val$forced;
    final String val$udn;

    public void run()
    {
        if (mFinished || EasyTransferDriver.this == null)
        {
            return;
        }
        synchronized (EasyTransferDriver.this)
        {
            mRemoteDBMgr.stop(val$udn, val$forced);
        }
        return;
        exception;
        easytransferdriver;
        JVM INSTR monitorexit ;
        throw exception;
    }

    syTransferDBMgr()
    {
        this$0 = final_easytransferdriver;
        val$udn = s;
        val$forced = Z.this;
        super();
    }
}
