// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferTimerTask

class EasyTransferTimer
{
    public static interface IOnEasyTransferTimerListener
    {

        public abstract void onTimer(List list, int i, String s, long l);
    }

    public static class Request
    {

        long index;
        int retryCount;
        int retryPeriod;
        String serverudn;
        int startHour;
        int startMinute;

        public Request()
        {
            index = -1L;
            serverudn = null;
            startHour = 0;
            startMinute = 0;
            retryPeriod = 0x1d4c0;
            retryCount = 5;
        }
    }

    private static class RetryTimerTask extends TimerTask
    {

        private TimerImpl Impl;
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
            TimerImpl timerimpl = Impl;
            timerimpl;
            JVM INSTR monitorenter ;
            EasyTransferTimerTask easytransfertimertask = Impl.getTask(serverudn, index);
            if (easytransfertimertask == null)
            {
                break MISSING_BLOCK_LABEL_159;
            }
            if (EasyTransferTimer.mListener != null)
            {
                Timer timer = new Timer();
                timer.index = index;
                timer.serverudn = serverudn;
                timer.serverState = 5;
                timer.retryCount = easytransfertimertask.retryCount;
                timer.retryCurrent = easytransfertimertask.retryCurrent;
                ArrayList arraylist = new ArrayList();
                arraylist.add(timer);
                EasyTransferTimer.mListener.onTimer(arraylist, 5, serverudn, index);
            }
            timerimpl;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            timerimpl;
            JVM INSTR monitorexit ;
            throw exception;
        }


/*
        static TimerImpl access$202(RetryTimerTask retrytimertask, TimerImpl timerimpl)
        {
            retrytimertask.Impl = timerimpl;
            return timerimpl;
        }

*/

        private RetryTimerTask()
        {
            cancelflag = false;
            when = 0L;
            serverudn = null;
            index = -1L;
            Impl = null;
        }

    }

    private static class StartAlarmReceiver extends BroadcastReceiver
    {

        private TimerImpl Impl;

        public void onReceive(Context context, Intent intent)
        {
            Log.i("EasyTransferTimer", "onReceive()");
            if (Impl == null)
            {
                Log.w("EasyTransferTimer", "impl had release");
                return;
            }
            TimerImpl timerimpl = Impl;
            timerimpl;
            JVM INSTR monitorenter ;
            long l;
            String s;
            l = intent.getLongExtra("index", -1L);
            s = intent.getStringExtra("serverudn");
            if (l >= 0L && s != null)
            {
                break MISSING_BLOCK_LABEL_76;
            }
            timerimpl;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            timerimpl;
            JVM INSTR monitorexit ;
            throw exception;
            boolean flag = intent.getBooleanExtra("auto", true);
            EasyTransferTimerTask aeasytransfertimertask[];
            aeasytransfertimertask = null;
            if (!flag)
            {
                break MISSING_BLOCK_LABEL_106;
            }
            aeasytransfertimertask = Impl.getTaskCopy(s, l);
            ArrayList arraylist;
            if (EasyTransferTimer.mListener == null)
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
            Impl.execute(aeasytransfertimertask[i].serverudn, aeasytransfertimertask[i].index);
            Timer timer1 = new Timer();
            timer1.index = aeasytransfertimertask[i].index;
            timer1.serverudn = aeasytransfertimertask[i].serverudn;
            timer1.serverState = 2;
            arraylist.add(timer1);
            Log.i("onReceiver", (new StringBuilder()).append("auto timer id =").append(aeasytransfertimertask[i].index).toString());
            i++;
            if (true) goto _L5; else goto _L4
_L2:
            byte0 = 3;
            Timer timer = new Timer();
            timer.index = l;
            timer.serverudn = s;
            timer.serverState = 3;
            arraylist.add(timer);
            Log.i("onReceiver", (new StringBuilder()).append("manual timer id =").append(l).toString());
_L4:
            EasyTransferTimer.mListener.onTimer(arraylist, byte0, s, l);
            timerimpl;
            JVM INSTR monitorexit ;
        }


/*
        static TimerImpl access$302(StartAlarmReceiver startalarmreceiver, TimerImpl timerimpl)
        {
            startalarmreceiver.Impl = timerimpl;
            return timerimpl;
        }

*/

        private StartAlarmReceiver()
        {
            Impl = null;
        }

    }

    public static class Timer
    {

        long index;
        long retryCount;
        int retryCurrent;
        int serverState;
        String serverudn;

        public Timer()
        {
            index = -1L;
            serverudn = null;
            retryCount = 0L;
            retryCurrent = 0;
            serverState = 0;
        }
    }

    private static final class TimerAlarm
    {

        boolean finished;
        Application mApp;
        TimerImpl mImpl;
        ArrayList mRetryTask;
        java.util.Timer mRetryTimer;
        private StartAlarmReceiver mStartReceiver;
        private PendingIntent mStartSender;

        private void recycle()
        {
            reset();
            if (mStartReceiver != null)
            {
                mApp.getApplicationContext().unregisterReceiver(mStartReceiver);
                mStartReceiver.Impl = null;
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
            RetryTimerTask retrytimertask;
            Iterator iterator = mRetryTask.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    continue; /* Loop/switch isn't completed */
                }
                retrytimertask = (RetryTimerTask)iterator.next();
            } while (retrytimertask.index != l);
            break; /* Loop/switch isn't completed */
            if (true) goto _L1; else goto _L3
_L3:
            retrytimertask.cancelflag = true;
            retrytimertask.Impl = null;
            retrytimertask.cancel();
            mRetryTask.remove(retrytimertask);
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
            RetryTimerTask retrytimertask;
            do
            {
                if (!iterator.hasNext())
                {
                    continue; /* Loop/switch isn't completed */
                }
                retrytimertask = (RetryTimerTask)iterator.next();
            } while (retrytimertask.index != l);
            if (retrytimertask.when >= calendar.getTimeInMillis())
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
                if (((RetryTimerTask)iterator.next()).index == l)
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
            RetryTimerTask retrytimertask;
            for (Iterator iterator = mRetryTask.iterator(); iterator.hasNext(); retrytimertask.cancel())
            {
                retrytimertask = (RetryTimerTask)iterator.next();
                retrytimertask.cancelflag = true;
                retrytimertask.Impl = null;
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
                RetryTimerTask retrytimertask = new RetryTimerTask();
                retrytimertask.Impl = mImpl;
                retrytimertask.cancelflag = false;
                retrytimertask.when = l1 + System.currentTimeMillis();
                retrytimertask.serverudn = s;
                retrytimertask.index = l;
                mRetryTask.add(retrytimertask);
                mRetryTimer.schedule(retrytimertask, l1);
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
                mStartReceiver = new StartAlarmReceiver();
                mStartReceiver.Impl = mImpl;
                context.registerReceiver(mStartReceiver, new IntentFilter("com.arcsoft.mediasee.easytransfer.ALARM_ALERT"));
            }
            AlarmManager alarmmanager = (AlarmManager)context.getSystemService("alarm");
            EasyTransferTimerTask easytransfertimertask = mImpl.minimum();
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
                mStartReceiver = new StartAlarmReceiver();
                mStartReceiver.Impl = mImpl;
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


        TimerAlarm(Application application, TimerImpl timerimpl)
        {
            mStartReceiver = null;
            finished = false;
            mImpl = null;
            mApp = null;
            mStartSender = null;
            mRetryTimer = null;
            mRetryTask = new ArrayList();
            mApp = application;
            mImpl = timerimpl;
            mRetryTimer = new java.util.Timer();
        }
    }

    private static final class TimerImpl
    {

        private int DEFAULT_LIST_SIZE;
        private int mSize;
        private EasyTransferTimerTask mTasks[];

        private void addSort()
        {
            int i = -1 + mSize;
            EasyTransferTimerTask easytransfertimertask = mTasks[i];
            int j;
            for (j = i - 1; j >= 0 && (easytransfertimertask.startHour < mTasks[j].startHour || easytransfertimertask.startHour == mTasks[j].startHour && easytransfertimertask.startMinute < mTasks[j].startMinute); j--) { }
            if (j < i - 1)
            {
                EasyTransferTimerTask aeasytransfertimertask[] = mTasks;
                System.arraycopy(aeasytransfertimertask, j + 1, aeasytransfertimertask, j + 2, -2 + (mSize - j));
                mTasks[j + 1] = easytransfertimertask;
            }
        }

        private boolean calculateWhen(EasyTransferTimerTask easytransfertimertask)
        {
            Calendar calendar;
            long l;
            int i;
            int j;
            int k;
            int i1;
            calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            l = easytransfertimertask.getWhen();
            i = easytransfertimertask.getHour();
            j = easytransfertimertask.getMinute();
            k = calendar.get(11);
            i1 = calendar.get(12);
            if (l != 0L) goto _L2; else goto _L1
_L1:
            boolean flag;
            if (i < k || i == k && j < i1)
            {
                flag = true;
            } else
            {
                calendar.set(11, i);
                calendar.set(12, j);
                calendar.set(13, 0);
                calendar.set(14, 0);
                easytransfertimertask.setWhen(calendar.getTimeInMillis());
                flag = false;
            }
_L6:
            if (flag)
            {
                calendar.add(6, 1);
                calendar.set(11, i);
                calendar.set(12, j);
                calendar.set(13, 0);
                calendar.set(14, 0);
                easytransfertimertask.setWhen(calendar.getTimeInMillis());
                return false;
            } else
            {
                return true;
            }
_L2:
            if (i < k) goto _L4; else goto _L3
_L3:
            flag = false;
            if (i != k) goto _L6; else goto _L5
_L5:
            flag = false;
            if (j >= i1) goto _L6; else goto _L4
_L4:
            flag = true;
              goto _L6
        }

        private int getDaysBetween(Calendar calendar, Calendar calendar1)
        {
            if (calendar.get(1) != calendar1.get(1))
            {
                return -1;
            } else
            {
                return calendar.get(6) - calendar1.get(6);
            }
        }

        private void remove(int i)
        {
            if (i < -1 + mSize)
            {
                System.arraycopy(mTasks, i + 1, mTasks, i, -1 + (mSize - i));
            }
            mTasks[-1 + mSize] = null;
        }

        public boolean cancel(String s, long l)
        {
            int i = getTaskPos(s, l);
            if (i > -1)
            {
                return mTasks[i].cancel();
            } else
            {
                return false;
            }
        }

        public boolean delete(String s, long l)
        {
            int i = getTaskPos(s, l);
            if (i > -1)
            {
                remove(i);
                mSize = -1 + mSize;
                return true;
            } else
            {
                return false;
            }
        }

        public boolean execute(String s, long l)
        {
            int i = getTaskPos(s, l);
            if (i > -1)
            {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.add(6, 1);
                calendar.set(11, mTasks[i].startHour);
                calendar.set(12, mTasks[i].startMinute);
                calendar.set(13, 0);
                calendar.set(14, 0);
                mTasks[i].setWhen(calendar.getTimeInMillis());
                mTasks[i].resetRetry();
                return true;
            } else
            {
                return false;
            }
        }

        public EasyTransferTimerTask getTask(String s, long l)
        {
            for (int i = 0; i < mSize; i++)
            {
                if (mTasks[i].index == l && mTasks[i].serverudn.equalsIgnoreCase(s))
                {
                    return mTasks[i];
                }
            }

            return null;
        }

        public EasyTransferTimerTask[] getTaskCopy(String s, long l)
        {
            int i = getTaskPos(s, l);
            if (i == -1)
            {
                return null;
            }
            EasyTransferTimerTask easytransfertimertask = mTasks[i];
            int j = easytransfertimertask.getHour();
            int k = easytransfertimertask.getMinute();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(easytransfertimertask.getWhen());
            Calendar calendar1 = Calendar.getInstance();
            int i1 = 0;
            EasyTransferTimerTask aeasytransfertimertask[] = new EasyTransferTimerTask[mSize];
            for (int j1 = 0; j1 < mSize; j1++)
            {
                if (mTasks[j1].getHour() != j || mTasks[j1].getMinute() != k)
                {
                    continue;
                }
                calendar1.setTimeInMillis(mTasks[j1].getWhen());
                if (getDaysBetween(calendar, calendar1) == 0)
                {
                    int k1 = i1 + 1;
                    aeasytransfertimertask[i1] = mTasks[j1];
                    i1 = k1;
                }
            }

            if (i1 == 0)
            {
                return null;
            } else
            {
                EasyTransferTimerTask aeasytransfertimertask1[] = new EasyTransferTimerTask[i1];
                System.arraycopy(aeasytransfertimertask, 0, aeasytransfertimertask1, 0, i1);
                return aeasytransfertimertask1;
            }
        }

        public int getTaskPos(String s, long l)
        {
            for (int i = 0; i < mSize; i++)
            {
                if (mTasks[i].index == l && mTasks[i].serverudn.equalsIgnoreCase(s))
                {
                    return i;
                }
            }

            return -1;
        }

        public boolean insert(EasyTransferTimerTask easytransfertimertask)
        {
            if (mSize == DEFAULT_LIST_SIZE)
            {
                mSize = mSize + DEFAULT_LIST_SIZE / 2;
                EasyTransferTimerTask aeasytransfertimertask1[] = new EasyTransferTimerTask[mSize];
                System.arraycopy(mTasks, 0, aeasytransfertimertask1, 0, mTasks.length);
                mTasks = aeasytransfertimertask1;
            }
            EasyTransferTimerTask aeasytransfertimertask[] = mTasks;
            int i = mSize;
            mSize = i + 1;
            aeasytransfertimertask[i] = easytransfertimertask;
            addSort();
            return true;
        }

        public boolean isEmpty()
        {
            return mSize > 0;
        }

        public EasyTransferTimerTask minimum()
        {
            if (mSize >= 1) goto _L2; else goto _L1
_L1:
            EasyTransferTimerTask easytransfertimertask = null;
_L8:
            return easytransfertimertask;
_L2:
            int i;
            easytransfertimertask = null;
            i = 0;
_L4:
            if (i >= mSize)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (calculateWhen(mTasks[i]))
            {
                break; /* Loop/switch isn't completed */
            }
_L5:
            i++;
            if (true) goto _L4; else goto _L3
_L3:
            if (easytransfertimertask == null)
            {
                easytransfertimertask = mTasks[i];
            } else
            if (easytransfertimertask.getWhen() > mTasks[i].getWhen())
            {
                easytransfertimertask = mTasks[i];
            }
              goto _L5
            if (true) goto _L4; else goto _L6
_L6:
            if (easytransfertimertask != null) goto _L8; else goto _L7
_L7:
            return mTasks[0];
        }

        public void reset()
        {
            for (int i = 0; i < mSize; i++)
            {
                mTasks[i].cancel();
            }

        }

        public long retry(String s, long l)
        {
            int i = getTaskPos(s, l);
            if (i > -1 && mTasks[i].canRetry())
            {
                if (System.currentTimeMillis() + (long)mTasks[i].retryPeriod() < mTasks[i].getWhen())
                {
                    mTasks[i].retryCurrent();
                    return (long)mTasks[i].retryPeriod();
                }
                Log.w("EasyTransferTimer", "retry-time will exceed start-time of next day, ignore");
            }
            Log.w("EasyTransferTimer", "retry had finished, return");
            return -1L;
        }

        public boolean setWhen(String s, long l, int i, int j)
        {
            int k = getTaskPos(s, l);
            if (k > -1)
            {
                mTasks[k].setWhen(i, j);
                return true;
            } else
            {
                return false;
            }
        }

        private TimerImpl()
        {
            DEFAULT_LIST_SIZE = 50;
            mSize = 0;
            mTasks = new EasyTransferTimerTask[25];
        }

    }


    private static IOnEasyTransferTimerListener mListener = null;
    private final String TAG = "EasyTransferTimer";
    private Application mApp;
    private TimerAlarm mTimerAlarm;
    private TimerImpl mTimerImpl;

    EasyTransferTimer()
    {
        mApp = null;
        mTimerImpl = null;
        mTimerAlarm = null;
    }

    public boolean cancel(String s, long l)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        synchronized (mTimerImpl)
        {
            flag = mTimerImpl.cancel(s, l);
            mTimerAlarm.cancelRetryTimer(null, s, l);
            mTimerAlarm.startTimer(mApp.getApplicationContext());
        }
        this;
        JVM INSTR monitorexit ;
        return flag;
        exception1;
        timerimpl;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean delete(String s, long l)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        synchronized (mTimerImpl)
        {
            flag = mTimerImpl.delete(s, l);
            mTimerAlarm.cancelRetryTimer(null, s, l);
            mTimerAlarm.startTimer(mApp.getApplicationContext());
        }
        this;
        JVM INSTR monitorexit ;
        return flag;
        exception1;
        timerimpl;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean execute(String s, long l)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        synchronized (mTimerImpl)
        {
            flag = mTimerImpl.execute(s, l);
            mTimerAlarm.cancelRetryTimer(null, s, l);
            mTimerAlarm.startTimer(mApp.getApplicationContext(), s, l);
        }
        this;
        JVM INSTR monitorexit ;
        return flag;
        exception1;
        timerimpl;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean init(Application application)
    {
        mApp = application;
        mTimerImpl = new TimerImpl();
        mTimerAlarm = new TimerAlarm(application, mTimerImpl);
        return true;
    }

    public boolean register(Request request)
    {
        if (request == null)
        {
            Log.e("EasyTransferTimer", "register request is null!");
            return false;
        }
        synchronized (mTimerImpl)
        {
            EasyTransferTimerTask easytransfertimertask = new EasyTransferTimerTask();
            easytransfertimertask.index = request.index;
            easytransfertimertask.serverudn = request.serverudn;
            easytransfertimertask.retryCount = request.retryCount;
            easytransfertimertask.retryPeriod = request.retryPeriod;
            easytransfertimertask.startHour = request.startHour;
            easytransfertimertask.startMinute = request.startMinute;
            mTimerImpl.insert(easytransfertimertask);
        }
        synchronized (mTimerAlarm)
        {
            mTimerAlarm.startTimer(mApp.getApplicationContext());
        }
        return true;
        exception;
        timerimpl;
        JVM INSTR monitorexit ;
        throw exception;
        exception1;
        timeralarm;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    public boolean register(ArrayList arraylist)
    {
        if (arraylist == null)
        {
            Log.e("EasyTransferTimer", "register request list is null!");
            return false;
        }
        TimerImpl timerimpl = mTimerImpl;
        timerimpl;
        JVM INSTR monitorenter ;
        EasyTransferTimerTask easytransfertimertask;
        for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); mTimerImpl.insert(easytransfertimertask))
        {
            Request request = (Request)iterator.next();
            easytransfertimertask = new EasyTransferTimerTask();
            easytransfertimertask.index = request.index;
            easytransfertimertask.serverudn = request.serverudn;
            easytransfertimertask.retryCount = request.retryCount;
            easytransfertimertask.retryPeriod = request.retryPeriod;
            easytransfertimertask.startHour = request.startHour;
            easytransfertimertask.startMinute = request.startMinute;
        }

        break MISSING_BLOCK_LABEL_136;
        Exception exception;
        exception;
        timerimpl;
        JVM INSTR monitorexit ;
        throw exception;
        timerimpl;
        JVM INSTR monitorexit ;
        synchronized (mTimerAlarm)
        {
            mTimerAlarm.startTimer(mApp.getApplicationContext());
        }
        return true;
        exception1;
        timeralarm;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    public void registerTimerListener(IOnEasyTransferTimerListener ioneasytransfertimerlistener)
    {
        if (ioneasytransfertimerlistener == null)
        {
            return;
        } else
        {
            mListener = ioneasytransfertimerlistener;
            return;
        }
    }

    public boolean retryTimer(String s, long l)
    {
        this;
        JVM INSTR monitorenter ;
        TimerImpl timerimpl = mTimerImpl;
        timerimpl;
        JVM INSTR monitorenter ;
        long l1;
        mTimerAlarm.cancelRetryTimer(null, s, l);
        l1 = mTimerImpl.retry(s, l);
        if (l1 <= 0L) goto _L2; else goto _L1
_L1:
        mTimerAlarm.retryTimer(null, s, l, l1);
        boolean flag = true;
        timerimpl;
        JVM INSTR monitorexit ;
_L4:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        timerimpl;
        JVM INSTR monitorexit ;
        flag = false;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception1;
        exception1;
        timerimpl;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void start()
    {
        if (mTimerAlarm != null)
        {
            mTimerAlarm.startTimer(mApp);
        }
    }

    public boolean startTimer()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        synchronized (mTimerAlarm)
        {
            flag = mTimerAlarm.startTimer(mApp.getApplicationContext());
        }
        this;
        JVM INSTR monitorexit ;
        return flag;
        exception1;
        timeralarm;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void stop()
    {
        if (mTimerImpl != null)
        {
            mTimerImpl.reset();
        }
        if (mTimerAlarm != null)
        {
            mTimerAlarm.reset();
        }
    }

    public void uninit()
    {
        this;
        JVM INSTR monitorenter ;
        mApp = null;
        mListener = null;
        if (mTimerImpl != null)
        {
            mTimerImpl.reset();
        }
        if (mTimerAlarm != null)
        {
            mTimerAlarm.recycle();
        }
        mTimerImpl = null;
        mTimerAlarm = null;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void unregisterTimerListener()
    {
        mListener = null;
    }

    public boolean update(String s, long l, int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        synchronized (mTimerImpl)
        {
            flag = mTimerImpl.setWhen(s, l, i, j);
            if (mTimerAlarm.cancelRetryTimerIfUpdate(s, l, i, j))
            {
                mTimerAlarm.cancelRetryTimer(null, s, l);
            }
            mTimerAlarm.startTimer(mApp.getApplicationContext());
        }
        this;
        JVM INSTR monitorexit ;
        return flag;
        exception1;
        timerimpl;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }


}
