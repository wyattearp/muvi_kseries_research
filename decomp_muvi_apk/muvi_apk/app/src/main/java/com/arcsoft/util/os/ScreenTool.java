// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.os;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import com.arcsoft.Recyclable;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;

public class ScreenTool
    implements Recyclable
{
    public static interface IOnScreenStatusChangeListener
    {

        public abstract void OnScreenStatusTurnOff();

        public abstract void OnScreenStatusTurnOn();
    }

    private class NotifyHandler extends Handler
    {

        final ScreenTool this$0;

        public void handleMessage(Message message)
        {
            IOnScreenStatusChangeListener aionscreenstatuschangelistener[] = getListenerListCopy();
            if (aionscreenstatuschangelistener != null && aionscreenstatuschangelistener.length >= 1) goto _L2; else goto _L1
_L1:
            return;
_L2:
            int i;
            int j;
            switch (message.what)
            {
            default:
                return;

            case 1: // '\001'
                int k = aionscreenstatuschangelistener.length;
                int l = 0;
                while (l < k) 
                {
                    aionscreenstatuschangelistener[l].OnScreenStatusTurnOff();
                    l++;
                }
                break;

            case 2: // '\002'
                i = aionscreenstatuschangelistener.length;
                j = 0;
                break; /* Loop/switch isn't completed */
            }
            if (true) goto _L1; else goto _L3
_L3:
            while (j < i) 
            {
                aionscreenstatuschangelistener[j].OnScreenStatusTurnOn();
                j++;
            }
            if (true) goto _L1; else goto _L4
_L4:
        }

        private NotifyHandler()
        {
            this$0 = ScreenTool.this;
            super();
        }

    }


    private static final int MSG_SCREEN_TURN_OFF = 1;
    private static final int MSG_SCREEN_TURN_ON = 2;
    private static ScreenTool mInstance = null;
    private Context mContext;
    private Handler mHandler;
    private boolean mIsScreenOff;
    private ArrayList mListeners;
    private BroadcastReceiver mScreenReceiver;

    public ScreenTool(Context context)
    {
        mIsScreenOff = false;
        mContext = null;
        mScreenReceiver = null;
        mHandler = null;
        mListeners = new ArrayList();
        mContext = context;
    }

    private IOnScreenStatusChangeListener[] getListenerListCopy()
    {
        ArrayList arraylist = mListeners;
        arraylist;
        JVM INSTR monitorenter ;
        int i = mListeners.size();
        IOnScreenStatusChangeListener aionscreenstatuschangelistener[];
        aionscreenstatuschangelistener = null;
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_48;
        }
        IOnScreenStatusChangeListener aionscreenstatuschangelistener1[] = new IOnScreenStatusChangeListener[mListeners.size()];
        aionscreenstatuschangelistener = (IOnScreenStatusChangeListener[])mListeners.toArray(aionscreenstatuschangelistener1);
        arraylist;
        JVM INSTR monitorexit ;
        return aionscreenstatuschangelistener;
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static void initSingleton(Application application)
    {
        if (mInstance != null)
        {
            throw new IllegalStateException("Already initialized.");
        } else
        {
            mInstance = new ScreenTool(application);
            return;
        }
    }

    public static ScreenTool instance()
    {
        if (mInstance == null)
        {
            throw new IllegalStateException("Uninitialized.");
        } else
        {
            return mInstance;
        }
    }

    private void registerScreenReceiver(Context context)
    {
        if (mScreenReceiver != null)
        {
            return;
        } else
        {
            mScreenReceiver = new BroadcastReceiver() {

                final ScreenTool this$0;

                public void onReceive(Context context1, Intent intent)
                {
                    if (mHandler == null)
                    {
                        mHandler = new NotifyHandler();
                    }
                    if ("android.intent.action.SCREEN_OFF".equalsIgnoreCase(intent.getAction()))
                    {
                        Log.i("ScreenTool", "android.intent.action.SCREEN_OFF");
                        mIsScreenOff = true;
                        mHandler.sendEmptyMessage(1);
                    } else
                    if ("android.intent.action.SCREEN_ON".equalsIgnoreCase(intent.getAction()))
                    {
                        Log.i("ScreenTool", "android.intent.action.SCREEN_ON");
                        mIsScreenOff = false;
                        mHandler.sendEmptyMessage(2);
                        return;
                    }
                }

            
            {
                this$0 = ScreenTool.this;
                super();
            }
            };
            IntentFilter intentfilter = new IntentFilter("android.intent.action.SCREEN_OFF");
            intentfilter.addAction("android.intent.action.SCREEN_ON");
            context.registerReceiver(mScreenReceiver, intentfilter);
            return;
        }
    }

    public static void uninitSingleton()
    {
        if (mInstance == null)
        {
            throw new IllegalStateException("Not initialized.");
        } else
        {
            mInstance.recycle();
            mInstance = null;
            return;
        }
    }

    private void unregisterScreenReceiver(Context context)
    {
        if (mScreenReceiver == null)
        {
            return;
        } else
        {
            context.unregisterReceiver(mScreenReceiver);
            mScreenReceiver = null;
            return;
        }
    }

    public boolean isScreenOff()
    {
        return mIsScreenOff;
    }

    public void recycle()
    {
        unregisterScreenReceiver(mContext);
        mListeners.clear();
        mContext = null;
        mHandler = null;
    }

    public void registerOnScreenStatusChangeListener(IOnScreenStatusChangeListener ionscreenstatuschangelistener)
    {
        ArrayList arraylist = mListeners;
        arraylist;
        JVM INSTR monitorenter ;
        if (ionscreenstatuschangelistener == null)
        {
            break MISSING_BLOCK_LABEL_47;
        }
        getListenerListCopy();
        if (!mListeners.contains(ionscreenstatuschangelistener))
        {
            break MISSING_BLOCK_LABEL_30;
        }
        arraylist;
        JVM INSTR monitorexit ;
        return;
        registerScreenReceiver(mContext);
        mListeners.add(ionscreenstatuschangelistener);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void unregisterOnScreenStatusChangeListener(IOnScreenStatusChangeListener ionscreenstatuschangelistener)
    {
        ArrayList arraylist = mListeners;
        arraylist;
        JVM INSTR monitorenter ;
        if (ionscreenstatuschangelistener == null)
        {
            break MISSING_BLOCK_LABEL_39;
        }
        mListeners.remove(this);
        if (mListeners.size() < 1)
        {
            unregisterScreenReceiver(mContext);
        }
        arraylist;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }




/*
    static Handler access$002(ScreenTool screentool, Handler handler)
    {
        screentool.mHandler = handler;
        return handler;
    }

*/


/*
    static boolean access$202(ScreenTool screentool, boolean flag)
    {
        screentool.mIsScreenOff = flag;
        return flag;
    }

*/

}
