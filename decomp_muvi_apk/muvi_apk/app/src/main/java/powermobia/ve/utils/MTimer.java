// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.ve.utils;

import java.util.Timer;
import java.util.TimerTask;

public class MTimer
{
    private class MTimerTask extends TimerTask
    {

        final MTimer this$0;
        long timerProc;
        long userData;

        public void run()
        {
            nativeTimerCallback(timerProc, userData);
        }

        public MTimerTask(long l, long l1)
        {
            this$0 = MTimer.this;
            super();
            timerProc = 0L;
            userData = 0L;
            timerProc = l;
            userData = l1;
        }
    }


    private static Timer timer = null;
    private MTimerTask timerTask;

    public MTimer()
    {
        timerTask = null;
    }

    public static int create()
    {
        if (timer == null)
        {
            timer = new Timer();
        }
        return 0;
    }

    public static int destroy()
    {
        if (timer != null)
        {
            timer.cancel();
            timer = null;
        }
        return 0;
    }

    private native void nativeTimerCallback(long l, long l1);

    public int cancel()
    {
        if (timerTask != null)
        {
            timerTask.cancel();
            timerTask = null;
        }
        return 0;
    }

    public int set(int i, long l, long l1)
    {
        if (timer == null)
        {
            return -1;
        }
        if (timerTask != null)
        {
            timerTask.cancel();
            timerTask = null;
        }
        timerTask = new MTimerTask(l, l1);
        timer.schedule(timerTask, i);
        return 0;
    }

    public int setEx(int i, boolean flag, long l, long l1)
    {
        if (timer == null)
        {
            return -1;
        }
        if (timerTask != null)
        {
            timerTask.cancel();
            timerTask = null;
        }
        timerTask = new MTimerTask(l, l1);
        if (flag)
        {
            timer.schedule(timerTask, i, i);
        } else
        {
            timer.schedule(timerTask, i);
        }
        return 0;
    }


}
