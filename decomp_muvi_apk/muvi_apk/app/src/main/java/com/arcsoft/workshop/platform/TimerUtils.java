// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.platform;

import java.util.ArrayList;

// Referenced classes of package com.arcsoft.workshop.platform:
//            MPTimer

public class TimerUtils
{
    public class ARCTimer extends MPTimer
    {

        final TimerUtils this$0;

        public void TimerCallback(int i, int j)
        {
            if (i != 0)
            {
                ARCTimerCallback(i, j);
            }
        }

        public ARCTimer()
        {
            this$0 = TimerUtils.this;
            super();
        }
    }


    private ArrayList mARCTimerManager;

    public TimerUtils()
    {
        mARCTimerManager = new ArrayList();
    }

    public native void ARCTimerCallback(int i, int j);

    public int ARCTimerCancel(ARCTimer arctimer)
    {
        mARCTimerManager.remove(arctimer);
        return arctimer.TimerCancel();
    }

    public ARCTimer ARCTimerCreate()
    {
        ARCTimer arctimer = new ARCTimer();
        mARCTimerManager.add(arctimer);
        return arctimer;
    }

    public int ARCTimerDestroy(ARCTimer arctimer)
    {
        return 0;
    }

    public void ARCTimerManagerPauseAll()
    {
        if (mARCTimerManager != null && !mARCTimerManager.isEmpty())
        {
            int i = mARCTimerManager.size();
            for (int j = 0; j < i; j++)
            {
                ARCTimer arctimer = (ARCTimer)mARCTimerManager.get(j);
                if (arctimer != null)
                {
                    arctimer.removeMessages(10);
                    arctimer.sendEmptyMessage(11);
                }
            }

        }
    }

    public void ARCTimerManagerResumeAll()
    {
        if (mARCTimerManager != null && !mARCTimerManager.isEmpty())
        {
            int i = mARCTimerManager.size();
            for (int j = 0; j < i; j++)
            {
                ARCTimer arctimer = (ARCTimer)mARCTimerManager.get(j);
                if (arctimer != null)
                {
                    arctimer.sendEmptyMessage(12);
                }
            }

        }
    }

    public int ARCTimerSet(ARCTimer arctimer, int i, int j, int k)
    {
        return arctimer.TimerSet(i, j, k);
    }

    public int ARCTimerSetEx(ARCTimer arctimer, int i, boolean flag, int j, int k)
    {
        return arctimer.TimerSetEx(i, flag, j, k);
    }
}
