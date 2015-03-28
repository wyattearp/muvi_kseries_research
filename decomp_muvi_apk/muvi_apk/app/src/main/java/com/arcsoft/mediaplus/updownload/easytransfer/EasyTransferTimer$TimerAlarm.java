// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Timer;

// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferTimerTask, EasyTransferTimer

private static final class mRetryTimer
{

    boolean finished;
    Application mApp;
    mStartSender mImpl;
    ArrayList mRetryTask;
    Timer mRetryTimer;
    private eceiver mStartReceiver;
    private PendingIntent mStartSender;

    private void recycle()
    {
        reset();
        if (mStartReceiver != null)
        {
            mApp.getApplicationContext().unregisterReceiver(mStartReceiver);
            eceiver.access._mth302(mStartReceiver, null);
        }
        mApp = null;
        mImpl = null;
        mRetryTask.clear();
        mRetryTimer.cancel();
    }

    public void cancelRetryTimer(Context context, String s, long l)
    {
        if (!finished) goto _L2; else goto _L1
_L1:
        return;
_L2:
        ask ask;
        Iterator iterator = mRetryTask.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                continue; /* Loop/switch isn't completed */
            }
            ask = (ask)iterator.next();
        } while (ask.index != l);
        break; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L3
_L3:
        ask.cancelflag = true;
        ask.access._mth202(ask, null);
        ask.cancel();
        mRetryTask.remove(ask);
        return;
    }

    public boolean cancelRetryTimerIfUpdate(String s, long l, int i, int j)
    {
        if (!finished) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(11, i);
        calendar.set(12, j);
        Iterator iterator = mRetryTask.iterator();
        ask ask;
        do
        {
            if (!iterator.hasNext())
            {
                continue; /* Loop/switch isn't completed */
            }
            ask = (ask)iterator.next();
        } while (ask.index != l);
        if (ask.when >= calendar.getTimeInMillis())
        {
            return true;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    public void cancelTimer(Context context)
    {
        if (context == null)
        {
            return;
        } else
        {
            ((AlarmManager)context.getSystemService("alarm")).cancel(mStartSender);
            return;
        }
    }

    public boolean existRetryTimer(String s, long l)
    {
        for (Iterator iterator = mRetryTask.iterator(); iterator.hasNext();)
        {
            if (((ask)iterator.next()).index == l)
            {
                return true;
            }
        }

        return false;
    }

    protected void finalize()
        throws Throwable
    {
        recycle();
        super.finalize();
    }

    public void reset()
    {
        ask ask;
        for (Iterator iterator = mRetryTask.iterator(); iterator.hasNext(); ask.cancel())
        {
            ask = (ask)iterator.next();
            ask.cancelflag = true;
            ask.access._mth202(ask, null);
        }

        if (mApp != null)
        {
            cancelTimer(mApp.getApplicationContext());
        }
    }

    public boolean retryTimer(Context context, String s, long l, long l1)
    {
        if (finished)
        {
            return false;
        } else
        {
            ask ask = new ask(null);
            ask.access._mth202(ask, mImpl);
            ask.cancelflag = false;
            ask.when = l1 + System.currentTimeMillis();
            ask.serverudn = s;
            ask.index = l;
            mRetryTask.add(ask);
            mRetryTimer.schedule(ask, l1);
            return true;
        }
    }

    public boolean startTimer(Context context)
    {
        Log.i("EasyTransferTimer", "startTimer()");
        if (finished || context == null)
        {
            Log.w("EasyTransferTimer", (new StringBuilder()).append("finished:").append(finished).toString());
            return false;
        }
        if (mStartReceiver == null)
        {
            mStartReceiver = new eceiver(null);
            eceiver.access._mth302(mStartReceiver, mImpl);
            context.registerReceiver(mStartReceiver, new IntentFilter("com.arcsoft.mediasee.easytransfer.ALARM_ALERT"));
        }
        AlarmManager alarmmanager = (AlarmManager)context.getSystemService("alarm");
        EasyTransferTimerTask easytransfertimertask = mImpl.inimum();
        if (easytransfertimertask != null)
        {
            Intent intent = new Intent("com.arcsoft.mediasee.easytransfer.ALARM_ALERT");
            intent.putExtra("index", easytransfertimertask.index);
            intent.putExtra("serverudn", easytransfertimertask.serverudn);
            intent.putExtra("when", easytransfertimertask.getWhen());
            intent.putExtra("auto", true);
            mStartSender = PendingIntent.getBroadcast(context, 0, intent, 0x8000000);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(easytransfertimertask.getWhen());
            alarmmanager.set(0, calendar.getTimeInMillis(), mStartSender);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean startTimer(Context context, String s, long l)
    {
        if (finished || context == null)
        {
            return false;
        }
        if (mStartReceiver == null)
        {
            mStartReceiver = new eceiver(null);
            eceiver.access._mth302(mStartReceiver, mImpl);
            context.registerReceiver(mStartReceiver, new IntentFilter("com.arcsoft.mediasee.easytransfer.ALARM_ALERT"));
        }
        AlarmManager alarmmanager = (AlarmManager)context.getSystemService("alarm");
        Intent intent = new Intent("com.arcsoft.mediasee.easytransfer.ALARM_ALERT");
        intent.putExtra("index", l);
        intent.putExtra("serverudn", s);
        intent.putExtra("when", 0);
        intent.putExtra("auto", false);
        mStartSender = PendingIntent.getBroadcast(context, 0, intent, 0x8000000);
        alarmmanager.set(0, System.currentTimeMillis(), mStartSender);
        return true;
    }


    eceiver(Application application, eceiver eceiver)
    {
        mStartReceiver = null;
        finished = false;
        mImpl = null;
        mApp = null;
        mStartSender = null;
        mRetryTimer = null;
        mRetryTask = new ArrayList();
        mApp = application;
        mImpl = eceiver;
        mRetryTimer = new Timer();
    }
}
