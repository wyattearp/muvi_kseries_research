// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;

import java.util.Map;

// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            RemoteDBMgr, DataTask

class val.serverudn
    implements Runnable
{

    final tifyChanges this$1;
    final String val$serverudn;

    public void run()
    {
        Map map = cess._mth100(this._cls1.this);
        map;
        JVM INSTR monitorenter ;
        val.serverudn serverudn1 = (this._cls1)cess._mth100(this._cls1.this).get(val$serverudn);
        if (serverudn1 != null)
        {
            break MISSING_BLOCK_LABEL_67;
        }
        serverudn1 = new it>(_fld0);
        cess._mth100(this._cls1.this).put(val$serverudn, serverudn1);
        serverudn1.eorootfolderadded = "AV".equals(mDataTask.getCurrentFolder());
        serverudn1.eoadded = true;
        serverudn1.geadded = true;
        serverudn1.ioadded = true;
        serverudn1.deradded = true;
        map;
        JVM INSTR monitorexit ;
        tifyChanges();
        return;
        Exception exception;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
    }

    ()
    {
        this$1 = final_;
        val$serverudn = String.this;
        super();
    }
}
