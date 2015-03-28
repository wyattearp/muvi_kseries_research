// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.tool;

import android.app.Application;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Iterator;

public class PlayerMandatoryManager
{
    public static interface IOnPlayerMandatoryManagerCallback
    {

        public abstract void disable();

        public abstract void enable();
    }


    private static PlayerMandatoryManager sInstance = null;
    private Application mApp;
    private ArrayList mCaller;
    private Handler mHandler;

    public PlayerMandatoryManager(Application application)
    {
        mApp = null;
        mCaller = new ArrayList();
        mHandler = new Handler();
        mApp = application;
    }

    public static void initSingleton(Application application)
    {
        com/arcsoft/util/tool/PlayerMandatoryManager;
        JVM INSTR monitorenter ;
        if (sInstance != null)
        {
            throw new IllegalStateException("Has call initSingleton PlayerMandatoryManager");
        }
        break MISSING_BLOCK_LABEL_25;
        Exception exception;
        exception;
        com/arcsoft/util/tool/PlayerMandatoryManager;
        JVM INSTR monitorexit ;
        throw exception;
        sInstance = new PlayerMandatoryManager(application);
        com/arcsoft/util/tool/PlayerMandatoryManager;
        JVM INSTR monitorexit ;
    }

    public static PlayerMandatoryManager instance()
    {
        com/arcsoft/util/tool/PlayerMandatoryManager;
        JVM INSTR monitorenter ;
        if (sInstance == null)
        {
            throw new IllegalStateException("Has not call initSingleton PlayerMandatoryManager");
        }
        break MISSING_BLOCK_LABEL_25;
        Exception exception;
        exception;
        com/arcsoft/util/tool/PlayerMandatoryManager;
        JVM INSTR monitorexit ;
        throw exception;
        PlayerMandatoryManager playermandatorymanager = sInstance;
        com/arcsoft/util/tool/PlayerMandatoryManager;
        JVM INSTR monitorexit ;
        return playermandatorymanager;
    }

    public static void uninitSingleton()
    {
        com/arcsoft/util/tool/PlayerMandatoryManager;
        JVM INSTR monitorenter ;
        if (sInstance == null)
        {
            throw new IllegalStateException("Has not call initSingleton PlayerMandatoryManager");
        }
        break MISSING_BLOCK_LABEL_25;
        Exception exception;
        exception;
        com/arcsoft/util/tool/PlayerMandatoryManager;
        JVM INSTR monitorexit ;
        throw exception;
        sInstance = null;
        com/arcsoft/util/tool/PlayerMandatoryManager;
        JVM INSTR monitorexit ;
    }

    public void CallDisable(IOnPlayerMandatoryManagerCallback ionplayermandatorymanagercallback, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        Iterator iterator = mCaller.iterator();
_L4:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        final IOnPlayerMandatoryManagerCallback c = (IOnPlayerMandatoryManagerCallback)iterator.next();
          goto _L3
_L5:
        mHandler.post(new Runnable() {

            final PlayerMandatoryManager this$0;
            final IOnPlayerMandatoryManagerCallback val$c;

            public void run()
            {
                c.disable();
            }

            
            {
                this$0 = PlayerMandatoryManager.this;
                c = ionplayermandatorymanagercallback;
                super();
            }
        });
          goto _L4
        Exception exception;
        exception;
        throw exception;
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
_L3:
        if (flag && c == ionplayermandatorymanagercallback || !flag && c != ionplayermandatorymanagercallback) goto _L4; else goto _L5
    }

    public void CallEnable(IOnPlayerMandatoryManagerCallback ionplayermandatorymanagercallback)
    {
        this;
        JVM INSTR monitorenter ;
        Iterator iterator = mCaller.iterator();
_L2:
        final IOnPlayerMandatoryManagerCallback c;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_62;
            }
            c = (IOnPlayerMandatoryManagerCallback)iterator.next();
        } while (c != ionplayermandatorymanagercallback);
        mHandler.post(new Runnable() {

            final PlayerMandatoryManager this$0;
            final IOnPlayerMandatoryManagerCallback val$c;

            public void run()
            {
                c.enable();
            }

            
            {
                this$0 = PlayerMandatoryManager.this;
                c = ionplayermandatorymanagercallback;
                super();
            }
        });
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
        this;
        JVM INSTR monitorexit ;
    }

    public void RegisterCallback(IOnPlayerMandatoryManagerCallback ionplayermandatorymanagercallback)
    {
        if (ionplayermandatorymanagercallback == null)
        {
            throw new NullPointerException();
        }
        if (!mCaller.contains(ionplayermandatorymanagercallback))
        {
            mCaller.add(ionplayermandatorymanagercallback);
        }
    }

    public void UnregisterCallback(IOnPlayerMandatoryManagerCallback ionplayermandatorymanagercallback)
    {
        if (ionplayermandatorymanagercallback == null)
        {
            throw new NullPointerException();
        }
        if (mCaller.contains(ionplayermandatorymanagercallback))
        {
            mCaller.remove(ionplayermandatorymanagercallback);
        }
    }

}
