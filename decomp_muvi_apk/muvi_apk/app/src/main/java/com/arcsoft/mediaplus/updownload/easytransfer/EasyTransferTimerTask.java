// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;


class EasyTransferTimerTask
{

    long index;
    final Object lock = new Object();
    int retryCount;
    int retryCurrent;
    int retryPeriod;
    String serverudn;
    int startHour;
    int startMinute;
    long startWhen;

    public EasyTransferTimerTask()
    {
        index = -1L;
        serverudn = null;
        startHour = 0;
        startMinute = 0;
        startWhen = 0L;
        retryPeriod = 0;
        retryCount = 0;
        retryCurrent = 0;
    }

    public boolean canRetry()
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        Exception exception;
        boolean flag;
        if (1 + retryCurrent < retryCount)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj;
        JVM INSTR monitorexit ;
        return flag;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean cancel()
    {
        synchronized (lock)
        {
            startWhen = 0L;
            retryCurrent = 0;
        }
        return true;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getHour()
    {
        int i;
        synchronized (lock)
        {
            i = startHour;
        }
        return i;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getMinute()
    {
        int i;
        synchronized (lock)
        {
            i = startMinute;
        }
        return i;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public long getWhen()
    {
        long l;
        synchronized (lock)
        {
            l = startWhen;
        }
        return l;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean resetRetry()
    {
        synchronized (lock)
        {
            retryCurrent = 0;
        }
        return true;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean retryCurrent()
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        Exception exception;
        boolean flag;
        int i;
        if (1 + retryCurrent < retryCount)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_44;
        }
        i = 1 + retryCurrent;
_L1:
        retryCurrent = i;
        obj;
        JVM INSTR monitorexit ;
        return flag;
        i = retryCurrent;
          goto _L1
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int retryPeriod()
    {
        int i;
        synchronized (lock)
        {
            i = retryPeriod;
        }
        return i;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean setWhen(int i, int j)
    {
        synchronized (lock)
        {
            startHour = i;
            startMinute = j;
            startWhen = 0L;
        }
        return true;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean setWhen(long l)
    {
        synchronized (lock)
        {
            startWhen = l;
        }
        return true;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }
}
