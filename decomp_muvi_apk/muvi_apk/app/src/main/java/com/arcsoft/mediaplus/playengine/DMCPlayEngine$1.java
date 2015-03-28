// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayEngine

class val.udn
    implements Runnable
{

    final DMCPlayEngine this$0;
    final String val$udn;

    public void run()
    {
        if (DMCPlayEngine.access$000(DMCPlayEngine.this) != null)
        {
            DMCPlayEngine.access$000(DMCPlayEngine.this).onMediaSeek(0, val$udn);
        }
    }

    ener()
    {
        this$0 = final_dmcplayengine;
        val$udn = String.this;
        super();
    }
}
