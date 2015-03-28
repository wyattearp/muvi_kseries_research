// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;


// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            RemoteDBMgr

class val.serverudn
    implements Runnable
{

    final val.serverudn this$1;
    final String val$serverudn;

    public void run()
    {
        ner aner[] = RemoteDBMgr.access$000(_fld0);
        if (aner != null)
        {
            int i = aner.length;
            for (int j = 0; j < i; j++)
            {
                aner[j].OnDBDataTransmittedBegin(val$serverudn);
            }

        }
    }

    ner()
    {
        this$1 = final_ner;
        val$serverudn = String.this;
        super();
    }
}
