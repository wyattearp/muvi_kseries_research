// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;


// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferDriver

class val.tableid
    implements Runnable
{

    final EasyTransferDriver this$0;
    final String val$serverudn;
    final long val$tableid;

    public void run()
    {
        if (mFinished || EasyTransferDriver.this == null)
        {
            return;
        }
        synchronized (EasyTransferDriver.this)
        {
            if (!mRemoteDBMgr.start(val$serverudn, val$tableid))
            {
                EasyTransferDriver.access$100(EasyTransferDriver.this, ine.Action.BROWSE, false, val$serverudn, val$tableid);
                EasyTransferDriver.access$200(EasyTransferDriver.this, ine.Action.BROWSE, val$serverudn, val$tableid, false);
            }
        }
        return;
        exception;
        easytransferdriver;
        JVM INSTR monitorexit ;
        throw exception;
    }

    ine.Action()
    {
        this$0 = final_easytransferdriver;
        val$serverudn = s;
        val$tableid = J.this;
        super();
    }
}
