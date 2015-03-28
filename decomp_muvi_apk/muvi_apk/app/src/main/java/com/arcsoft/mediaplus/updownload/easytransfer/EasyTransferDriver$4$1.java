// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;

import com.arcsoft.util.debug.Log;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferDriver, EasyTransferTimer

class val.serverState
    implements Runnable
{

    final e.Action this$1;
    final List val$list;
    final int val$serverState;

    public void run()
    {
        if (mFinished || _fld0 == null)
        {
            return;
        }
        EasyTransferDriver easytransferdriver = _fld0;
        easytransferdriver;
        JVM INSTR monitorenter ;
        sferServerNode sferservernode;
        Iterator iterator;
        Log.i("EasyTransferDriver", (new StringBuilder()).append("before queue size =").append(mTransferQueue.getCount()).toString());
        sferservernode = mTransferQueue.first();
        iterator = val$list.iterator();
_L9:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        String s;
        long l;
          = ()iterator.next();
        s = .serverudn;
        l = .index;
        boolean flag = true;
        sferServerNode sferservernode1 = mTransferQueue.find(s, l);
        if (sferservernode1 == null) goto _L4; else goto _L3
_L3:
        Log.w("EasyTransferDriver", (new StringBuilder()).append("onTimer() find state:").append(sferservernode1.serverstate).toString());
        if (sferservernode1.serverstate != 2 && sferservernode1.serverstate != 3) goto _L6; else goto _L5
_L5:
        int i;
        Log.i("EasyTransferDriver", (new StringBuilder()).append("auto/manual easy-transfer had waited, ignore :").append(l).toString());
        i = val$serverState;
        flag = false;
        if (i != 5) goto _L4; else goto _L7
_L7:
        Log.w("EasyTransferDriver", (new StringBuilder()).append("why retry timer comes on?! ").append(l).toString());
_L4:
        if (!flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        mTransferQueue.add(s, l, val$serverState, false);
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        easytransferdriver;
        JVM INSTR monitorexit ;
        throw exception;
_L6:
        if (sferservernode1.serverstate != 5)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (val$serverState != 2 && sferservernode1.serverstate != 3)
        {
            break MISSING_BLOCK_LABEL_398;
        }
        if (sferservernode == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (sferservernode.tableid != l)
        {
            Log.i("EasyTransferDriver", (new StringBuilder()).append("retry waited, remove :").append(l).toString());
            mTransferQueue.remove(s, l);
        }
        continue; /* Loop/switch isn't completed */
        Log.w("EasyTransferDriver", (new StringBuilder()).append("why two retry timer exist?! ").append(l).toString());
        flag = false;
        if (true) goto _L4; else goto _L2
_L2:
        Log.i("EasyTransferDriver", (new StringBuilder()).append("after queue size =").append(mTransferQueue.getCount()).toString());
        if (mIdleListener != null)
        {
            Log.i("EasyTransferDriver", (new StringBuilder()).append("is idle =").append(mIdleListener.onIsUpDownloadIdle()).toString());
        }
        if (mIdleListener == null || !mIdleListener.onIsUpDownloadIdle() || sferservernode != null)
        {
            break MISSING_BLOCK_LABEL_573;
        }
        EasyTransferDriver.access$200(_fld0, e.Action.BROWSE, null, -1L, false);
        easytransferdriver;
        JVM INSTR monitorexit ;
        return;
        if (true) goto _L9; else goto _L8
_L8:
    }

    is._cls0()
    {
        this$1 = final__pcls0;
        val$list = list1;
        val$serverState = I.this;
        super();
    }

    // Unreferenced inner class com/arcsoft/mediaplus/updownload/easytransfer/EasyTransferDriver$4

/* anonymous class */
    class EasyTransferDriver._cls4
        implements EasyTransferTimer.IOnEasyTransferTimerListener
    {

        final EasyTransferDriver this$0;

        public void onTimer(final List list, int i, String s, long l)
        {
            Log.w("EasyTransferDriver", (new StringBuilder()).append("onTimer() id:").append(l).append(", state:").append(i).toString());
            if ((i == 2 || i == 3) && mTimer != null)
            {
                mTimer.startTimer();
            }
            if (EasyTransferDriver.access$1500(EasyTransferDriver.this) == null || list.size() < 1)
            {
                return;
            } else
            {
                EasyTransferDriver.access$1500(EasyTransferDriver.this).post(i. new EasyTransferDriver._cls4._cls1());
                return;
            }
        }

            
            {
                this$0 = EasyTransferDriver.this;
                super();
            }
    }

}
