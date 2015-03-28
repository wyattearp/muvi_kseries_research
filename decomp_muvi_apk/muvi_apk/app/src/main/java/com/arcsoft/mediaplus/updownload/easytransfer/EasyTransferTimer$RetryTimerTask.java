// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;

import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferTimer, EasyTransferTimerTask

private static class <init> extends TimerTask
{

    private index Impl;
    boolean cancelflag;
    long index;
    String serverudn;
    long when;

    public void run()
    {
        Log.i("EasyTransferTimer", "onRetryTimer()");
        if (cancelflag || Impl == null)
        {
            Log.w("EasyTransferTimer", "impl had release");
            return;
        }
        <init> <init>1 = Impl;
        <init>1;
        JVM INSTR monitorenter ;
        EasyTransferTimerTask easytransfertimertask = Impl.sk(serverudn, index);
        if (easytransfertimertask == null)
        {
            break MISSING_BLOCK_LABEL_159;
        }
        if (EasyTransferTimer.access$600() != null)
        {
            <init> <init>2 = new index();
            <init>2.index = index;
            <init>2. = serverudn;
            <init>2.te = 5;
            <init>2.t = easytransfertimertask.retryCount;
            <init>2.ent = easytransfertimertask.retryCurrent;
            ArrayList arraylist = new ArrayList();
            arraylist.add(<init>2);
            EasyTransferTimer.access$600().onTimer(arraylist, 5, serverudn, index);
        }
        <init>1;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        <init>1;
        JVM INSTR monitorexit ;
        throw exception;
    }


/*
    static TimerListener access$202(TimerListener timerlistener, TimerListener timerlistener1)
    {
        timerlistener.Impl = timerlistener1;
        return timerlistener1;
    }

*/

    private Impl()
    {
        cancelflag = false;
        when = 0L;
        serverudn = null;
        index = -1L;
        Impl = null;
    }

    Impl(Impl impl)
    {
        this();
    }
}
