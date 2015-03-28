// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;

import com.arcsoft.util.debug.Log;
import java.util.Calendar;

// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferTimerTask, EasyTransferTimer

private static final class <init>
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

    private ()
    {
        DEFAULT_LIST_SIZE = 50;
        mSize = 0;
        mTasks = new EasyTransferTimerTask[25];
    }

    mTasks(mTasks mtasks)
    {
        this();
    }
}
