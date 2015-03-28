// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.platform;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

// Referenced classes of package com.arcsoft.platform:
//            ITimer, ITimerCallback

public class HandlerTimer extends Handler
    implements ITimer
{

    private static boolean SLOW_DOWN_FLAG = false;
    private static final int SLOW_DOWN_INTERVAL = 10000;
    private boolean m_bRepeat;
    private int m_dwTimeElapsed;
    ITimerCallback m_iTimerCallback;
    private int m_pUserData;
    private int m_pfnTimerProc;

    public HandlerTimer(ITimerCallback itimercallback)
    {
        m_iTimerCallback = itimercallback;
    }

    public HandlerTimer(ITimerCallback itimercallback, Looper looper)
    {
        super(looper);
        m_iTimerCallback = itimercallback;
    }

    public static void slowDown(boolean flag)
    {
        SLOW_DOWN_FLAG = flag;
    }

    public int TimerCancel()
    {
        m_bRepeat = false;
        removeMessages(0xfffe0000);
        return 0;
    }

    public int TimerSet(int i, int j, int k)
    {
        m_dwTimeElapsed = i;
        m_pUserData = k;
        m_pfnTimerProc = j;
        m_bRepeat = false;
        removeMessages(0xfffe0000);
        long l = m_dwTimeElapsed;
        if (SLOW_DOWN_FLAG && m_dwTimeElapsed < 10000)
        {
            l = 10000L;
        }
        sendEmptyMessageDelayed(0xfffe0000, l);
        return 0;
    }

    public int TimerSetEx(int i, boolean flag, int j, int k)
    {
        m_dwTimeElapsed = i;
        m_pUserData = k;
        m_pfnTimerProc = j;
        m_bRepeat = flag;
        if (!hasMessages(0xfffe0000))
        {
            long l = m_dwTimeElapsed;
            if (SLOW_DOWN_FLAG && m_dwTimeElapsed < 10000)
            {
                l = 10000L;
            }
            sendEmptyMessageDelayed(0xfffe0000, l);
        }
        return 0;
    }

    public void handleMessage(Message message)
    {
        if (message.what == 0xfffe0000)
        {
            timerProc();
        }
    }

    public void timerProc()
    {
        long l = System.currentTimeMillis();
        m_iTimerCallback.doTimerCallback(m_pfnTimerProc, m_pUserData);
        long _tmp = System.currentTimeMillis() - l;
        if (m_bRepeat)
        {
            long l1 = m_dwTimeElapsed;
            if (SLOW_DOWN_FLAG && m_dwTimeElapsed < 10000)
            {
                l1 = 10000L;
            }
            sendEmptyMessageDelayed(0xfffe0000, l1);
        }
    }

    static 
    {
        SLOW_DOWN_FLAG = false;
    }
}
