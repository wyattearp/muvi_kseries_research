// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.platform;

import android.os.HandlerThread;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.arcsoft.platform:
//            ITimer, ITimerCallback, HandlerTimer

public class MPTimer
    implements ITimer, ITimerCallback
{

    static String TAG = "MPTimer";
    static final int V_HANDLER = 0;
    static final int V_THREAD = 1;
    private static ArrayList mTimerList = new ArrayList();
    static HandlerThread s_thread = null;
    HandlerTimer m_iTimer;

    public MPTimer(int i)
    {
        if (i == 0)
        {
            m_iTimer = new HandlerTimer(this);
            mTimerList.add(m_iTimer);
            return;
        }
        if (1 == i)
        {
            if (s_thread == null)
            {
                s_thread = new HandlerThread("VThreadTimer");
                s_thread.start();
            }
            m_iTimer = new HandlerTimer(this, s_thread.getLooper());
            mTimerList.add(m_iTimer);
            return;
        } else
        {
            Log.e(TAG, "FAILED create timer because no version specified");
            return;
        }
    }

    public static void pause()
    {
        if (!mTimerList.isEmpty())
        {
            Log.d(TAG, (new StringBuilder()).append("count = ").append(mTimerList.size()).toString());
            Iterator iterator = ((ArrayList)mTimerList.clone()).iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                HandlerTimer handlertimer = (HandlerTimer)iterator.next();
                if (handlertimer != null)
                {
                    handlertimer.removeMessages(0xfffe0000);
                }
            } while (true);
        }
    }

    public static void resume()
    {
        if (!mTimerList.isEmpty())
        {
            Log.d(TAG, (new StringBuilder()).append("count = ").append(mTimerList.size()).toString());
            Iterator iterator = ((ArrayList)mTimerList.clone()).iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                HandlerTimer handlertimer = (HandlerTimer)iterator.next();
                if (handlertimer != null)
                {
                    handlertimer.sendEmptyMessage(0xfffe0000);
                }
            } while (true);
        }
    }

    public static void slowDownDLNATimer(boolean flag)
    {
        HandlerTimer.slowDown(flag);
    }

    native void TimerCallback(int i, int j);

    public int TimerCancel()
    {
        if (m_iTimer == null)
        {
            Log.e(TAG, "FAILED TimerCancel");
            return -1;
        } else
        {
            mTimerList.remove(m_iTimer);
            return m_iTimer.TimerCancel();
        }
    }

    public int TimerSet(int i, int j, int k)
    {
        if (m_iTimer == null)
        {
            Log.e(TAG, "FAILED TimerSet");
            return -1;
        } else
        {
            return m_iTimer.TimerSet(i, j, k);
        }
    }

    public int TimerSetEx(int i, boolean flag, int j, int k)
    {
        if (m_iTimer == null)
        {
            Log.e(TAG, "FAILED TimerSetEx");
            return -1;
        } else
        {
            return m_iTimer.TimerSetEx(i, flag, j, k);
        }
    }

    public void doTimerCallback(int i, int j)
    {
        TimerCallback(i, j);
    }

}
