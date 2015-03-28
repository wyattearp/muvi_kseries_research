// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferTimer, EasyTransferTimerTask

private static class <init> extends BroadcastReceiver
{

    private rListener.onTimer Impl;

    public void onReceive(Context context, Intent intent)
    {
        Log.i("EasyTransferTimer", "onReceive()");
        if (Impl == null)
        {
            Log.w("EasyTransferTimer", "impl had release");
            return;
        }
        <init> <init>1 = Impl;
        <init>1;
        JVM INSTR monitorenter ;
        long l;
        String s;
        l = intent.getLongExtra("index", -1L);
        s = intent.getStringExtra("serverudn");
        if (l >= 0L && s != null)
        {
            break MISSING_BLOCK_LABEL_76;
        }
        <init>1;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        <init>1;
        JVM INSTR monitorexit ;
        throw exception;
        boolean flag = intent.getBooleanExtra("auto", true);
        EasyTransferTimerTask aeasytransfertimertask[];
        aeasytransfertimertask = null;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_106;
        }
        aeasytransfertimertask = Impl.py(s, l);
        ArrayList arraylist;
        if (EasyTransferTimer.access$600() == null)
        {
            break MISSING_BLOCK_LABEL_342;
        }
        arraylist = new ArrayList();
        if (!flag) goto _L2; else goto _L1
_L1:
        byte byte0 = 2;
        if (aeasytransfertimertask == null) goto _L4; else goto _L3
_L3:
        int i = 0;
_L5:
        if (i >= aeasytransfertimertask.length)
        {
            break; /* Loop/switch isn't completed */
        }
        Impl.Impl(aeasytransfertimertask[i].serverudn, aeasytransfertimertask[i].index);
        <init> <init>3 = new Impl();
        <init>3.Impl = aeasytransfertimertask[i].index;
        <init>3.Impl = aeasytransfertimertask[i].serverudn;
        <init>3.Impl = 2;
        arraylist.add(<init>3);
        Log.i("onReceiver", (new StringBuilder()).append("auto timer id =").append(aeasytransfertimertask[i].index).toString());
        i++;
        if (true) goto _L5; else goto _L4
_L2:
        byte0 = 3;
        <init> <init>2 = new Impl();
        <init>2.Impl = l;
        <init>2.Impl = s;
        <init>2.Impl = 3;
        arraylist.add(<init>2);
        Log.i("onReceiver", (new StringBuilder()).append("manual timer id =").append(l).toString());
_L4:
        EasyTransferTimer.access$600().onTimer(arraylist, byte0, s, l);
        <init>1;
        JVM INSTR monitorexit ;
    }


/*
    static rListener access$302(rListener rlistener, rListener rlistener1)
    {
        rlistener.Impl = rlistener1;
        return rlistener1;
    }

*/

    private Impl()
    {
        Impl = null;
    }

    Impl(Impl impl)
    {
        this();
    }
}
