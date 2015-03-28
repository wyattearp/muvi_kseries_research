// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.List;

class EasyTransferList
{
    public static interface IOnTransferListListener
    {

        public abstract boolean onBuildList(List list, Options options, String s, long l);

        public abstract void onDestroyList(List list, String s, long l);

        public abstract boolean onTransferList(List list, Options options, String s, long l);
    }

    private class ListHandler extends Handler
    {

        final EasyTransferList this$0;

        public void handleMessage(Message message)
        {
            boolean flag = true;
            message.what;
            JVM INSTR tableswitch 0 2: default 32
        //                       0 33
        //                       1 105
        //                       2 181;
               goto _L1 _L2 _L3 _L4
_L1:
            return;
_L2:
            EasyTransferList easytransferlist1 = EasyTransferList.this;
            boolean flag1 = buildList();
            boolean flag2 = false;
            if (!flag1)
            {
                flag2 = flag;
            }
            easytransferlist1.mCanBuilt = flag2;
            if (!mCanBuilt)
            {
                sendEmptyMessageDelayed(flag, 500L);
                return;
            }
            if (!hasMessages(2))
            {
                sendEmptyMessageDelayed(2, 500L);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L3:
            if (!mCanBuilt)
            {
                EasyTransferList easytransferlist = EasyTransferList.this;
                if (transferList())
                {
                    flag = false;
                }
                easytransferlist.mCanBuilt = flag;
                if (!mCanBuilt)
                {
                    mState = 0;
                    return;
                }
                if (!hasMessages(2))
                {
                    sendEmptyMessageDelayed(2, 500L);
                    return;
                }
            }
            if (true) goto _L1; else goto _L4
_L4:
            destroyList();
            mState = 0;
            mCanBuilt = flag;
            return;
        }

        public void release()
        {
            removeMessages(0);
            removeMessages(1);
            removeMessages(2);
        }

        public ListHandler(Looper looper)
        {
            this$0 = EasyTransferList.this;
            super(looper);
        }
    }

    public static class Options
    {

        public boolean cancelled;

        public Options()
        {
            cancelled = false;
        }
    }


    private final int MSG_BUILD_LIST = 0;
    private final int MSG_DESTROY_LIST = 2;
    private final int MSG_TRANSFER_LIST = 1;
    private final int STATE_DOING = 1;
    private final int STATE_IDLE = 0;
    private final String TAG = "EasyTransferList";
    private boolean mCanBuilt;
    private HandlerThread mHandlerThread;
    private ArrayList mList;
    private ListHandler mListHandler;
    private IOnTransferListListener mListener;
    private Options mOptions;
    private String mServerudn;
    private int mState;
    private long mTableId;

    EasyTransferList()
    {
        mHandlerThread = null;
        mListHandler = null;
        mList = new ArrayList();
        mOptions = new Options();
        mListener = null;
        mCanBuilt = true;
        mState = 0;
        mServerudn = null;
        mTableId = -1L;
    }

    private boolean buildList()
    {
        synchronized (mList)
        {
            mList.clear();
        }
        if (mListener != null)
        {
            return mListener.onBuildList(mList, mOptions, mServerudn, mTableId);
        } else
        {
            return false;
        }
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void destroyList()
    {
        if (mListener != null)
        {
            mListener.onDestroyList(mList, mServerudn, mTableId);
        }
        clear();
    }

    private boolean transferList()
    {
        if (mListener != null)
        {
            return mListener.onTransferList(mList, mOptions, mServerudn, mTableId);
        } else
        {
            return false;
        }
    }

    public boolean build(String s, long l)
    {
        boolean flag = false;
        this;
        JVM INSTR monitorenter ;
        if (mState == 0) goto _L2; else goto _L1
_L1:
        Log.w("EasyTransferList", (new StringBuilder()).append("List is building. state: ").append(mState).toString());
_L4:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        mListHandler.removeMessages(0);
        mServerudn = s;
        mTableId = l;
        mOptions.cancelled = false;
        mState = 1;
        mListHandler.sendEmptyMessageDelayed(0, 200L);
        flag = true;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public void cancel()
    {
        this;
        JVM INSTR monitorenter ;
        if (!mListHandler.hasMessages(2))
        {
            mOptions.cancelled = true;
            mState = 1;
            mListHandler.sendEmptyMessage(2);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void cancel(String s, long l)
    {
        this;
        JVM INSTR monitorenter ;
        if (!mCanBuilt) goto _L2; else goto _L1
_L1:
        Log.w("EasyTransferList", "List is not built");
_L5:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (l == mTableId && s.equalsIgnoreCase(mServerudn)) goto _L4; else goto _L3
_L3:
        Log.w("EasyTransferList", (new StringBuilder()).append("Current List[").append(mServerudn).append("] not match with cancelling list:").append(s).toString());
          goto _L5
        Exception exception;
        exception;
        throw exception;
_L4:
        if (mListHandler.hasMessages(2)) goto _L5; else goto _L6
_L6:
        mOptions.cancelled = true;
        mState = 1;
        mListHandler.sendEmptyMessageDelayed(2, 200L);
          goto _L5
    }

    public void clear()
    {
        this;
        JVM INSTR monitorenter ;
        synchronized (mList)
        {
            mList.clear();
        }
        mOptions.cancelled = false;
        mCanBuilt = true;
        mState = 0;
        mServerudn = null;
        mTableId = -1L;
        this;
        JVM INSTR monitorexit ;
        return;
        exception1;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void init(IOnTransferListListener iontransferlistlistener)
    {
        mListener = iontransferlistlistener;
        mHandlerThread = new HandlerThread("EasyTransferList");
        mHandlerThread.setPriority(10);
        mHandlerThread.start();
        mListHandler = new ListHandler(mHandlerThread.getLooper());
    }

    public void release()
    {
        this;
        JVM INSTR monitorenter ;
        mOptions.cancelled = true;
        destroyList();
        mListener = null;
        if (mHandlerThread != null)
        {
            mHandlerThread.quit();
            mHandlerThread = null;
        }
        if (mListHandler != null)
        {
            mListHandler.release();
            mListHandler = null;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }



/*
    static boolean access$002(EasyTransferList easytransferlist, boolean flag)
    {
        easytransferlist.mCanBuilt = flag;
        return flag;
    }

*/




/*
    static int access$302(EasyTransferList easytransferlist, int i)
    {
        easytransferlist.mState = i;
        return i;
    }

*/

}
