// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util;

import android.app.Application;
import android.os.HandlerThread;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Iterator;

public class LooperManager
{

    public static final int LOOPER_TYPE_BACKDATA = 1;
    public static final int LOOPER_TYPE_COUNT = 2;
    public static final int LOOPER_TYPE_UI;
    private static LooperManager sInstance = null;
    private Application mApplication;
    private ArrayList mLooperPool;
    private ArrayList mThreadPool;

    private LooperManager(Application application)
    {
        mApplication = null;
        mLooperPool = new ArrayList();
        mThreadPool = new ArrayList();
        mApplication = application;
    }

    private void init()
    {
        this;
        JVM INSTR monitorenter ;
        mLooperPool.add(Looper.myLooper());
        int i = 1;
_L2:
        if (i >= 2)
        {
            break; /* Loop/switch isn't completed */
        }
        mLooperPool.add(null);
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static void initSingleton(Application application)
    {
        if (sInstance != null)
        {
            throw new IllegalStateException("Already initialized.");
        } else
        {
            sInstance = new LooperManager(application);
            sInstance.init();
            return;
        }
    }

    public static LooperManager instance()
    {
        if (sInstance == null)
        {
            throw new IllegalStateException("Uninitialized.");
        } else
        {
            return sInstance;
        }
    }

    private void uninit()
    {
        this;
        JVM INSTR monitorenter ;
        for (Iterator iterator = mThreadPool.iterator(); iterator.hasNext(); ((HandlerThread)iterator.next()).quit()) { }
        break MISSING_BLOCK_LABEL_40;
        Exception exception;
        exception;
        throw exception;
        mThreadPool.clear();
        mLooperPool.clear();
        this;
        JVM INSTR monitorexit ;
    }

    public static void uninitSingleton()
    {
        if (sInstance == null)
        {
            throw new IllegalStateException("Not initialized.");
        } else
        {
            sInstance.uninit();
            sInstance = null;
            return;
        }
    }

    public Looper getLooper(int i)
    {
        this;
        JVM INSTR monitorenter ;
        if (i >= 0 && i < 2)
        {
            break MISSING_BLOCK_LABEL_26;
        }
        throw new IllegalArgumentException("invalid looper type");
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        Looper looper = (Looper)mLooperPool.get(i);
        if (looper != null)
        {
            break MISSING_BLOCK_LABEL_101;
        }
        HandlerThread handlerthread = new HandlerThread((new StringBuilder()).append("LooperManager:").append(i).toString());
        handlerthread.start();
        mThreadPool.add(handlerthread);
        looper = handlerthread.getLooper();
        mLooperPool.set(i, looper);
        this;
        JVM INSTR monitorexit ;
        return looper;
    }

}
