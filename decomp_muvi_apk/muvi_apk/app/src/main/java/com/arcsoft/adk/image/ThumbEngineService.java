// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.image;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.arcsoft.util.tool.SafeBuffer;
import java.util.Comparator;

// Referenced classes of package com.arcsoft.adk.image:
//            ThumbEngine

public class ThumbEngineService extends Service
{
    static class AsyncTask
    {

        int id;
        Runnable runnable;

        AsyncTask()
        {
        }
    }

    class EngineBinder extends Binder
    {

        final ThumbEngineService this$0;

        public ThumbEngineService getService()
        {
            return ThumbEngineService.this;
        }

        EngineBinder()
        {
            this$0 = ThumbEngineService.this;
            super();
        }
    }

    class TaskThread extends Thread
    {

        public static final int TASK_ASYNCTASK = 0;
        public static final int TASK_DOSTEP = 1;
        private ThumbEngine mEngine;
        private boolean mStop;
        private Comparator mTaskComparator;
        private SafeBuffer mTasks;
        private boolean mTasksFlag[] = {
            0, 0
        };
        final ThumbEngineService this$0;

        void pauseTask()
        {
            synchronized (mTasksFlag)
            {
                mTasksFlag[1] = false;
            }
            return;
            exception;
            aflag;
            JVM INSTR monitorexit ;
            throw exception;
        }

        void postAsyncTask(AsyncTask asynctask, boolean flag)
        {
            if (asynctask == null)
            {
                return;
            }
            if (flag)
            {
                mTasks.removeSame(asynctask, mTaskComparator);
            }
            mTasks.append(asynctask);
            synchronized (mTasksFlag)
            {
                mTasksFlag[0] = true;
                mTasksFlag.notify();
            }
            return;
            exception;
            aflag;
            JVM INSTR monitorexit ;
            throw exception;
        }

        void quit()
        {
            synchronized (mTasksFlag)
            {
                mStop = true;
                mTasksFlag.notify();
            }
            try
            {
                join();
                return;
            }
            catch (Exception exception)
            {
                return;
            }
            exception1;
            aflag;
            JVM INSTR monitorexit ;
            throw exception1;
        }

        void resumeTask()
        {
            synchronized (mTasksFlag)
            {
                mTasksFlag[1] = true;
                mTasksFlag.notify();
            }
            return;
            exception;
            aflag;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void run()
        {
_L2:
label0:
            {
                synchronized (mTasksFlag)
                {
                    if (!mStop)
                    {
                        break label0;
                    }
                }
                return;
            }
            boolean flag = mTasksFlag[0];
            boolean flag1;
            flag1 = false;
            if (!flag)
            {
                break MISSING_BLOCK_LABEL_41;
            }
            flag1 = true;
            mTasksFlag[0] = false;
            boolean flag2 = mTasksFlag[1];
            boolean flag3;
            flag3 = false;
            if (flag2)
            {
                flag3 = true;
            }
            if (flag3 || flag1)
            {
                break MISSING_BLOCK_LABEL_87;
            }
            try
            {
                mTasksFlag.wait();
            }
            catch (Exception exception2) { }
            aflag;
            JVM INSTR monitorexit ;
            continue; /* Loop/switch isn't completed */
            exception;
            aflag;
            JVM INSTR monitorexit ;
            throw exception;
            aflag;
            JVM INSTR monitorexit ;
            do
            {
                AsyncTask asynctask = (AsyncTask)mTasks.get();
                if (asynctask == null)
                {
                    break;
                }
                asynctask.runnable.run();
            } while (true);
            if (flag3 && mEngine.doStep() == 0x80002)
            {
                try
                {
                    Thread.sleep(20L);
                }
                catch (Exception exception1) { }
            }
            if (true) goto _L2; else goto _L1
_L1:
        }

        TaskThread(ThumbEngine thumbengine)
        {
            this$0 = ThumbEngineService.this;
            super();
            mStop = false;
            mEngine = null;
            mTasks = new SafeBuffer();
            mTaskComparator = new _cls1();
            mEngine = thumbengine;
        }
    }


    private final Binder mBinder = new EngineBinder();
    private TaskThread mTaskThread;

    public ThumbEngineService()
    {
        mTaskThread = null;
    }

    public IBinder onBind(Intent intent)
    {
        return mBinder;
    }

    public void onCreate()
    {
    }

    public void onDestroy()
    {
        stop();
    }

    public int onStartCommand(Intent intent, int i, int j)
    {
        return 1;
    }

    void pause()
    {
        if (mTaskThread != null)
        {
            mTaskThread.pauseTask();
        }
    }

    void postAsyncTask(AsyncTask asynctask, boolean flag)
    {
        if (mTaskThread != null)
        {
            mTaskThread.postAsyncTask(asynctask, flag);
        }
    }

    void resume()
    {
        if (mTaskThread != null)
        {
            mTaskThread.resumeTask();
        }
    }

    void start(ThumbEngine thumbengine)
    {
        stop();
        mTaskThread = new TaskThread(thumbengine);
        mTaskThread.start();
    }

    void stop()
    {
        if (mTaskThread != null)
        {
            mTaskThread.quit();
            mTaskThread = null;
        }
    }

    // Unreferenced inner class com/arcsoft/adk/image/ThumbEngineService$TaskThread$1

/* anonymous class */
    class TaskThread._cls1
        implements Comparator
    {

        final TaskThread this$1;

        public int compare(AsyncTask asynctask, AsyncTask asynctask1)
        {
            return asynctask.id - asynctask1.id;
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((AsyncTask)obj, (AsyncTask)obj1);
        }

            
            {
                this$1 = TaskThread.this;
                super();
            }
    }

}
