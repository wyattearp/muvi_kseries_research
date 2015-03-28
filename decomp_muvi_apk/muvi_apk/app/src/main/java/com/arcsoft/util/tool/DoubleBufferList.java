// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.tool;

import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.arcsoft.mediaplus.ViewManager;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.util.debug.DebugUtils;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DoubleBufferList
{
    private class BackGroundHandler extends Handler
    {

        final DoubleBufferList this$0;

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 0 0: default 24
        //                       0 30;
               goto _L1 _L2
_L1:
            super.handleMessage(message);
            return;
_L2:
            if (!isDirty())
            {
                Log.w("DoubleBuffList", (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append("build list while the list isn't dirty.").toString());
            }
            mCallback.onDestoryList(getBackGroundList());
            setClear();
            if (mCallback.onBuildList(getBackGroundList(), mBuildOptions))
            {
                setBuildState(2);
                mForeGroundHandler.sendEmptyMessage(1);
                return;
            }
            Log.w("DoubleBuffList", (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append("build list failed or canceled. ").append("IsCanceled=").append(mBuildOptions.mIsCanceled).toString());
            setDirty();
            setBuildState(0);
            if (true) goto _L1; else goto _L3
_L3:
        }

        public BackGroundHandler(Looper looper)
        {
            this$0 = DoubleBufferList.this;
            super(looper);
        }
    }

    private class ForeGroundHandler extends Handler
    {

        final DoubleBufferList this$0;

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 1: default 24
        //                       1 30;
               goto _L1 _L2
_L1:
            super.handleMessage(message);
            return;
_L2:
            switchList();
            setBuildState(0);
            synchronized (DoubleBufferList.this)
            {
                if (isDirty() && !mBuildOptions.mIsCanceled)
                {
                    buildList(false);
                }
            }
            if (true) goto _L1; else goto _L3
_L3:
            exception;
            doublebufferlist;
            JVM INSTR monitorexit ;
            throw exception;
        }

        private ForeGroundHandler()
        {
            this$0 = DoubleBufferList.this;
            super();
        }

    }

    public static interface ICallback
    {

        public abstract boolean onBuildList(List list, Options options);

        public abstract void onDestoryList(List list);

        public abstract void onListSwitched(int i, int j);
    }

    public static class Options
    {

        public boolean mIsCanceled;

        public Options()
        {
            mIsCanceled = false;
        }
    }


    private static final int BUILD_STATE_IDLE = 0;
    private static final int BUILD_STATE_TO_BUILD = 1;
    private static final int BUILD_STATE_TO_SWITCH = 2;
    private static int CLASS_TAG = 0;
    private static final int MSG_BACK_BUILD_LIST = 0;
    private static final int MSG_FORE_SWITCH_LIST = 1;
    private static final String TAG = "DoubleBuffList";
    private int OBJ_TAG;
    private boolean bIsDirtyList;
    private BackGroundHandler mBackGroundHandler;
    private HandlerThread mBackGroundThread;
    private Options mBuildOptions;
    private Integer mBuildState;
    private ICallback mCallback;
    private Handler mForeGroundHandler;
    private Boolean mIsDirtyList;
    private boolean mIsFirstList;
    private boolean mIsInited;
    private boolean mIsInternalThread;
    private boolean mIsStopped;
    private boolean mIsThreadSeted;
    protected List mList;
    private List mListBuff[];

    public DoubleBufferList(ICallback icallback)
    {
        OBJ_TAG = 0;
        mIsInited = false;
        mIsThreadSeted = false;
        mIsInternalThread = false;
        mIsDirtyList = Boolean.valueOf(false);
        bIsDirtyList = false;
        mBuildState = Integer.valueOf(0);
        mCallback = null;
        mIsStopped = true;
        CLASS_TAG = 1 + CLASS_TAG;
        OBJ_TAG = CLASS_TAG;
        mCallback = icallback;
    }

    private boolean buildList(boolean flag)
    {
        boolean flag1 = false;
        this;
        JVM INSTR monitorenter ;
        if (getBuildState() == 0) goto _L2; else goto _L1
_L1:
        Log.w("DoubleBuffList", (new StringBuilder()).append("List is building. state: ").append(getBuildState()).toString());
_L4:
        this;
        JVM INSTR monitorexit ;
        return flag1;
_L2:
        mBuildOptions = new Options();
        mBuildOptions.mIsCanceled = false;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_110;
        }
        if (!isDirty())
        {
            Log.w("DoubleBuffList", "build list while the list isn't dirty.");
        }
        setClear();
        mCallback.onBuildList(getBackGroundList(), mBuildOptions);
        switchList();
        break MISSING_BLOCK_LABEL_160;
        Log.w("DoubleBuffList", (new StringBuilder()).append(OBJ_TAG).append(", To Build").toString());
        setBuildState(1);
        mBackGroundHandler.sendEmptyMessage(0);
        break MISSING_BLOCK_LABEL_160;
        Exception exception;
        exception;
        throw exception;
        flag1 = true;
        if (true) goto _L4; else goto _L3
_L3:
    }

    private List getBackGroundList()
    {
        if (mIsFirstList)
        {
            return mListBuff[1];
        } else
        {
            return mListBuff[0];
        }
    }

    private int getBuildState()
    {
        int i;
        synchronized (mBuildState)
        {
            i = mBuildState.intValue();
        }
        return i;
        exception;
        integer;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private List getForeGroundList()
    {
        return mList;
    }

    private void releaseHandlerThread()
    {
        if (!mIsThreadSeted)
        {
            Log.w("DoubleBuffList", "The thread already be released");
            return;
        }
        abortBuilding();
        mBackGroundHandler = null;
        if (mIsInternalThread)
        {
            mBackGroundThread.quit();
            long l = System.currentTimeMillis();
            int i = 0;
            while (mBackGroundThread.isAlive() && i < 100) 
            {
                try
                {
                    Thread.sleep(10L);
                }
                catch (InterruptedException interruptedexception)
                {
                    interruptedexception.printStackTrace();
                }
                i++;
            }
            long l1 = System.currentTimeMillis();
            if (mBackGroundThread.isAlive())
            {
                Log.w("DoubleBuffList", (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append("slow exit thread. cost: ").append(l1 - l).toString());
            }
        }
        mBackGroundThread = null;
        mForeGroundHandler = null;
        mIsInternalThread = false;
        mIsThreadSeted = false;
    }

    private void safeSleep(long l)
    {
        try
        {
            Thread.sleep(l);
            return;
        }
        catch (InterruptedException interruptedexception)
        {
            interruptedexception.printStackTrace();
        }
    }

    private void setBuildState(int i)
    {
        if (!mIsInited)
        {
            Log.e("DoubleBuffList", "Set state while not init");
            (new Exception()).printStackTrace();
        }
        synchronized (mBuildState)
        {
            mBuildState = Integer.valueOf(i);
        }
        return;
        exception;
        integer;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void setClear()
    {
        synchronized (mIsDirtyList)
        {
            bIsDirtyList = false;
        }
        return;
        exception;
        boolean1;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void setDirty()
    {
        synchronized (mIsDirtyList)
        {
            bIsDirtyList = true;
        }
        return;
        exception;
        boolean1;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void switchList()
    {
        boolean flag = true;
        List list = mList;
        int i;
        i = 0;
        if (list == null)
        {
            break MISSING_BLOCK_LABEL_25;
        }
        i = mList.size();
        Exception exception;
        List list1;
        if (mIsFirstList)
        {
            flag = false;
        }
        mIsFirstList = flag;
        if (!mIsFirstList)
        {
            break MISSING_BLOCK_LABEL_93;
        }
        list1 = mListBuff[0];
_L2:
        mList = list1;
        if (mList != null && mCallback != null)
        {
            mCallback.onListSwitched(mList.size(), i);
            return;
        }
        break MISSING_BLOCK_LABEL_109;
        list1 = mListBuff[1];
        if (true) goto _L2; else goto _L1
_L1:
        exception;
        exception.printStackTrace();
    }

    public void abortBuilding()
    {
        this;
        JVM INSTR monitorenter ;
        int i = getBuildState();
        if (i != 0) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        long l;
        long l1;
        long l2;
        l = Thread.currentThread().getId();
        l1 = mForeGroundHandler.getLooper().getThread().getId();
        l2 = mBackGroundHandler.getLooper().getThread().getId();
        if (l != l1)
        {
            break; /* Loop/switch isn't completed */
        }
        mBuildOptions.mIsCanceled = true;
_L7:
        getBuildState();
        JVM INSTR tableswitch 0 2: default 96
    //                   0 11
    //                   1 96
    //                   2 128;
           goto _L3 _L4 _L3 _L5
_L4:
        continue; /* Loop/switch isn't completed */
_L3:
        safeSleep(10L);
        if (ViewManager.getViewStatus() != 1) goto _L7; else goto _L6
_L6:
        Log.d("DoubleBufferList", "local stop abortBuilding !!");
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        throw exception;
_L5:
        mCallback.onDestoryList(getBackGroundList());
        mForeGroundHandler.removeMessages(1);
        setDirty();
        setBuildState(0);
        if (true) goto _L1; else goto _L8
_L8:
        if (l != l2) goto _L10; else goto _L9
_L9:
        throw new IllegalThreadStateException("Mustn't abort building in background thread.");
_L10:
        mBuildOptions.mIsCanceled = true;
_L14:
        getBuildState();
        JVM INSTR tableswitch 0 0: default 208
    //                   0 11;
           goto _L11 _L12
_L12:
        if (true) goto _L1; else goto _L13
_L13:
_L11:
        safeSleep(10L);
          goto _L14
    }

    public void clear()
    {
        this;
        JVM INSTR monitorenter ;
        int i = mList.size();
        mList.clear();
        mCallback.onListSwitched(mList.size(), i);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public Object delete(int i)
    {
        if (getForeGroundList() == null)
        {
            return null;
        } else
        {
            return getForeGroundList().remove(i);
        }
    }

    public boolean deleteMediaItem(MediaItem mediaitem)
    {
        List list = getForeGroundList();
        if (list == null)
        {
            return false;
        }
        for (Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            Object obj = iterator.next();
            if (mediaitem.uri.equals(((MediaItem)obj).uri))
            {
                list.remove(obj);
                return true;
            }
        }

        return false;
    }

    protected void finalize()
        throws Throwable
    {
        mCallback = null;
        super.finalize();
    }

    public Object get(int i)
    {
        if (i >= 0 && i < mList.size())
        {
            return mList.get(i);
        } else
        {
            return null;
        }
    }

    public int getCount()
    {
        return mList.size();
    }

    public boolean init()
    {
        if (mIsInited)
        {
            Log.w("DoubleBuffList", "List has already inited");
            return false;
        }
        mListBuff = new List[2];
        mListBuff[0] = new ArrayList();
        mListBuff[1] = new ArrayList();
        mIsFirstList = true;
        mList = mListBuff[0];
        if (!mIsThreadSeted)
        {
            HandlerThread handlerthread = new HandlerThread("DoubleBuffList");
            handlerthread.setPriority(10);
            handlerthread.start();
            if (!setHandlerThread(handlerthread))
            {
                Log.e("DoubleBuffList", "internal thread set failed!");
            }
            mIsInternalThread = true;
        }
        Log.d("DoubleBuffList", (new StringBuilder()).append(OBJ_TAG).append(" Init").toString());
        mIsInited = true;
        return true;
    }

    public void invalide()
    {
        this;
        JVM INSTR monitorenter ;
        setDirty();
        if (!mIsStopped)
        {
            buildList(false);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isDirty()
    {
        boolean flag;
        synchronized (mIsDirtyList)
        {
            flag = bIsDirtyList;
        }
        return flag;
        exception;
        boolean1;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean setHandlerThread(HandlerThread handlerthread)
    {
        if (mIsThreadSeted)
        {
            Log.w("DoubleBuffList", "The thread already be set");
            return false;
        }
        if (mIsInited)
        {
            Log.w("DoubleBuffList", "Should call this function before init");
            return false;
        }
        if (handlerthread == null || !handlerthread.isAlive())
        {
            Log.w("DoubleBuffList", "Thread is null or isn't alive");
            return false;
        } else
        {
            mBackGroundThread = handlerthread;
            mBackGroundHandler = new BackGroundHandler(mBackGroundThread.getLooper());
            mForeGroundHandler = new ForeGroundHandler();
            mIsThreadSeted = true;
            return true;
        }
    }

    public void start()
    {
        this;
        JVM INSTR monitorenter ;
        Log.d("DoubleBuffList", DebugUtils.currentTraceInfo());
        if (mIsStopped) goto _L2; else goto _L1
_L1:
        Log.w("DoubleBuffList", (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append("has already started").toString());
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        mIsStopped = false;
        if (isDirty())
        {
            buildList(false);
        }
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public void stop()
    {
        this;
        JVM INSTR monitorenter ;
        if (!mIsStopped) goto _L2; else goto _L1
_L1:
        Log.w("DoubleBuffList", (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append("has already stopped").toString());
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        mIsStopped = true;
        abortBuilding();
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public void unInit()
    {
        if (!mIsInited)
        {
            Log.w("DoubleBuffList", "List has already unInited");
            return;
        } else
        {
            releaseHandlerThread();
            setBuildState(0);
            mCallback.onDestoryList(mListBuff[0]);
            mCallback.onDestoryList(mListBuff[1]);
            mListBuff[0] = null;
            mListBuff[1] = null;
            mListBuff = null;
            mIsDirtyList = Boolean.valueOf(true);
            bIsDirtyList = true;
            Log.d("DoubleBuffList", (new StringBuilder()).append(OBJ_TAG).append(" Uninit").toString());
            mIsInited = false;
            return;
        }
    }

    static 
    {
        CLASS_TAG = 0;
    }









}
