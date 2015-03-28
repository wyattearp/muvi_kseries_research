// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.platform;

import android.os.Handler;
import android.os.Message;

public abstract class MPTimer extends Handler
{

    public static final int PROCESS_TIMER = 10;
    public static final int PROCESS_TIME_PAUSE = 11;
    public static final int PROCESS_TIME_RESUME = 12;
    private boolean m_bRepeat;
    private int m_dwTimeElapsed;
    private int m_pUserData;
    private int m_pfnTimerProc;

    public MPTimer()
    {
    }

    public abstract void TimerCallback(int i, int j);

    public int TimerCancel()
    {
        removeMessages(10);
        removeMessages(11);
        removeMessages(12);
        return 0;
    }

    public int TimerSet(int i, int j, int k)
    {
        m_dwTimeElapsed = i;
        m_pUserData = k;
        m_pfnTimerProc = j;
        m_bRepeat = false;
        removeMessages(10);
        sendMessageDelayed(obtainMessage(10), m_dwTimeElapsed);
        return 0;
    }

    public int TimerSetEx(int i, boolean flag, int j, int k)
    {
        m_dwTimeElapsed = i;
        m_pUserData = k;
        m_pfnTimerProc = j;
        m_bRepeat = flag;
        removeMessages(10);
        sendMessageDelayed(obtainMessage(10), m_dwTimeElapsed);
        return 0;
    }

    public void handleMessage(Message message)
    {
        super.handleMessage(message);
        message.what;
        JVM INSTR tableswitch 10 12: default 36
    //                   10 37
    //                   11 82
    //                   12 89;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L2:
        Message message1 = obtainMessage(10);
        if (m_pfnTimerProc != 0)
        {
            TimerCallback(m_pfnTimerProc, m_pUserData);
        }
        if (m_bRepeat)
        {
            sendMessageDelayed(message1, m_dwTimeElapsed);
            return;
        }
          goto _L1
_L3:
        removeMessages(10);
        return;
_L4:
        sendMessageDelayed(obtainMessage(10), m_dwTimeElapsed);
        return;
    }
}
