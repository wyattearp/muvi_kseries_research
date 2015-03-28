// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;


// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            ChannelDataMgr

class val.itemid
    implements Runnable
{

    final val.itemid this$1;
    final int val$channelID;
    final long val$itemid;
    final String val$serverudn;

    public void run()
    {
        Listener alistener[] = ChannelDataMgr.access$200(_fld0);
        if (alistener != null)
        {
            int i = alistener.length;
            for (int j = 0; j < i; j++)
            {
                alistener[j].OnChannelItemRefresh(val$serverudn, val$channelID, val$itemid);
            }

        }
    }

    Listener()
    {
        this$1 = final_listener;
        val$serverudn = s;
        val$channelID = i;
        val$itemid = J.this;
        super();
    }
}
