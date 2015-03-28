// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            ChannelDataMgr

class val.channelIDs
    implements Runnable
{

    final sume this$1;
    final Set val$channelIDs;
    final String val$serverudn;

    public void run()
    {
        Map map = cess._mth300(this._cls1.this);
        map;
        JVM INSTR monitorenter ;
        val.channelIDs channelids = (this._cls1)cess._mth300(this._cls1.this).get(val$serverudn);
        if (channelids != null)
        {
            break MISSING_BLOCK_LABEL_49;
        }
        channelids = new it>(_fld0);
        int i;
        for (Iterator iterator = val$channelIDs.iterator(); iterator.hasNext(); channelids.ateChannels.add(Integer.valueOf(i)))
        {
            i = ((Integer)iterator.next()).intValue();
        }

        break MISSING_BLOCK_LABEL_106;
        Exception exception;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
        cess._mth300(this._cls1.this).put(val$serverudn, channelids);
        sume();
        map;
        JVM INSTR monitorexit ;
    }

    ()
    {
        this$1 = final_;
        val$serverudn = s;
        val$channelIDs = Set.this;
        super();
    }
}
