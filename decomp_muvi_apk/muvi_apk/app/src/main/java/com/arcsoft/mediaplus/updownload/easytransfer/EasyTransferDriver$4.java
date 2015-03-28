// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;

import com.arcsoft.util.debug.Log;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferDriver, EasyTransferTimer

class this._cls0
    implements EasyTransferTimerListener
{

    final EasyTransferDriver this$0;

    public void onTimer(final List list, final int serverState, String s, long l)
    {
        Log.w("EasyTransferDriver", (new StringBuilder()).append("onTimer() id:").append(l).append(", state:").append(serverState).toString());
        if ((serverState == 2 || serverState == 3) && mTimer != null)
        {
            mTimer.startTimer();
        }
        if (EasyTransferDriver.access$1500(EasyTransferDriver.this) == null || list.size() < 1)
        {
            return;
        } else
        {
            EasyTransferDriver.access$1500(EasyTransferDriver.this).post(new Runnable() {

                final EasyTransferDriver._cls4 this$1;
                final List val$list;
                final int val$serverState;

                public void run()
                {
                    if (mFinished || this$0 == null)
                    {
                        return;
                    }
                    EasyTransferDriver easytransferdriver = this$0;
                    easytransferdriver;
                    JVM INSTR monitorenter ;
                    EasyTransferDriver.TransferServerNode transferservernode;
                    Iterator iterator;
                    Log.i("EasyTransferDriver", (new StringBuilder()).append("before queue size =").append(mTransferQueue.getCount()).toString());
                    transferservernode = mTransferQueue.first();
                    iterator = list.iterator();
_L9:
                    if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
                    String s1;
                    long l1;
                    EasyTransferTimer.Timer timer = (EasyTransferTimer.Timer)iterator.next();
                    s1 = timer.serverudn;
                    l1 = timer.index;
                    boolean flag = true;
                    EasyTransferDriver.TransferServerNode transferservernode1 = mTransferQueue.find(s1, l1);
                    if (transferservernode1 == null) goto _L4; else goto _L3
_L3:
                    Log.w("EasyTransferDriver", (new StringBuilder()).append("onTimer() find state:").append(transferservernode1.serverstate).toString());
                    if (transferservernode1.serverstate != 2 && transferservernode1.serverstate != 3) goto _L6; else goto _L5
_L5:
                    int i;
                    Log.i("EasyTransferDriver", (new StringBuilder()).append("auto/manual easy-transfer had waited, ignore :").append(l1).toString());
                    i = serverState;
                    flag = false;
                    if (i != 5) goto _L4; else goto _L7
_L7:
                    Log.w("EasyTransferDriver", (new StringBuilder()).append("why retry timer comes on?! ").append(l1).toString());
_L4:
                    if (!flag)
                    {
                        continue; /* Loop/switch isn't completed */
                    }
                    mTransferQueue.add(s1, l1, serverState, false);
                    continue; /* Loop/switch isn't completed */
                    Exception exception;
                    exception;
                    easytransferdriver;
                    JVM INSTR monitorexit ;
                    throw exception;
_L6:
                    if (transferservernode1.serverstate != 5)
                    {
                        continue; /* Loop/switch isn't completed */
                    }
                    if (serverState != 2 && transferservernode1.serverstate != 3)
                    {
                        break MISSING_BLOCK_LABEL_398;
                    }
                    if (transferservernode == null)
                    {
                        continue; /* Loop/switch isn't completed */
                    }
                    if (transferservernode.tableid != l1)
                    {
                        Log.i("EasyTransferDriver", (new StringBuilder()).append("retry waited, remove :").append(l1).toString());
                        mTransferQueue.remove(s1, l1);
                    }
                    continue; /* Loop/switch isn't completed */
                    Log.w("EasyTransferDriver", (new StringBuilder()).append("why two retry timer exist?! ").append(l1).toString());
                    flag = false;
                    if (true) goto _L4; else goto _L2
_L2:
                    Log.i("EasyTransferDriver", (new StringBuilder()).append("after queue size =").append(mTransferQueue.getCount()).toString());
                    if (mIdleListener != null)
                    {
                        Log.i("EasyTransferDriver", (new StringBuilder()).append("is idle =").append(mIdleListener.onIsUpDownloadIdle()).toString());
                    }
                    if (mIdleListener == null || !mIdleListener.onIsUpDownloadIdle() || transferservernode != null)
                    {
                        break MISSING_BLOCK_LABEL_573;
                    }
                    EasyTransferDriver.access$200(this$0, EasyTransferStateMachine.Action.BROWSE, null, -1L, false);
                    easytransferdriver;
                    JVM INSTR monitorexit ;
                    return;
                    if (true) goto _L9; else goto _L8
_L8:
                }

            
            {
                this$1 = EasyTransferDriver._cls4.this;
                list = list1;
                serverState = i;
                super();
            }
            });
            return;
        }
    }

    _cls1.val.serverState()
    {
        this$0 = EasyTransferDriver.this;
        super();
    }
}
