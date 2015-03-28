// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.ve.utils;

import java.util.TimerTask;

// Referenced classes of package powermobia.ve.utils:
//            MTimer

private class userData extends TimerTask
{

    final MTimer this$0;
    long timerProc;
    long userData;

    public void run()
    {
        MTimer.access$0(MTimer.this, timerProc, userData);
    }

    public (long l, long l1)
    {
        this$0 = MTimer.this;
        super();
        timerProc = 0L;
        userData = 0L;
        timerProc = l;
        userData = l1;
    }
}
