// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;

import java.util.Set;

// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            ChannelDataMgr

class val.channelIDs
    implements Runnable
{

    final val.channelIDs this$1;
    final Set val$channelIDs;
    final String val$serverudn;

    public void run()
    {
        Listener alistener[] = ChannelDataMgr.access$200(_fld0);
        if (alistener != null)
        {
            int i = alistener.length;
            for (int j = 0; j < i; j++)
            {
                alistener[j].OnChannelDataTransmittedBegin(val$serverudn, val$channelIDs);
            }

        }
    }

    Listener()
    {
        this$1 = final_listener;
        val$serverudn = s;
        val$channelIDs = Set.this;
        super();
    }
}
