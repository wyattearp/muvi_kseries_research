// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.platform;


// Referenced classes of package com.arcsoft.workshop.platform:
//            MPTimer, TimerUtils

public class this._cls0 extends MPTimer
{

    final TimerUtils this$0;

    public void TimerCallback(int i, int j)
    {
        if (i != 0)
        {
            ARCTimerCallback(i, j);
        }
    }

    public ()
    {
        this$0 = TimerUtils.this;
        super();
    }
}
