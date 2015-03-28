// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;

import java.util.Map;

// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            RemoteDBMgr

class val.videorootfolderadded
    implements Runnable
{

    final sume this$1;
    final boolean val$audioadded;
    final boolean val$folderadded;
    final boolean val$imageadded;
    final String val$serverudn;
    final boolean val$videoadded;
    final boolean val$videorootfolderadded;

    public void run()
    {
        Map map = cess._mth100(this._cls1.this);
        map;
        JVM INSTR monitorenter ;
        val.videorootfolderadded videorootfolderadded1 = (this._cls1)cess._mth100(this._cls1.this).get(val$serverudn);
        if (videorootfolderadded1 != null)
        {
            break MISSING_BLOCK_LABEL_67;
        }
        videorootfolderadded1 = new it>(_fld0);
        cess._mth100(this._cls1.this).put(val$serverudn, videorootfolderadded1);
        videorootfolderadded1.ioadded = videorootfolderadded1.ioadded | val$audioadded;
        videorootfolderadded1.eoadded = videorootfolderadded1.eoadded | val$videoadded;
        videorootfolderadded1.geadded = videorootfolderadded1.geadded | val$imageadded;
        videorootfolderadded1.deradded = videorootfolderadded1.deradded | val$folderadded;
        videorootfolderadded1.eorootfolderadded = videorootfolderadded1.eorootfolderadded | val$videorootfolderadded;
        sume();
        map;
        JVM INSTR monitorexit ;
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
        val$serverudn = s;
        val$audioadded = flag;
        val$videoadded = flag1;
        val$imageadded = flag2;
        val$folderadded = flag3;
        val$videorootfolderadded = Z.this;
        super();
    }
}
