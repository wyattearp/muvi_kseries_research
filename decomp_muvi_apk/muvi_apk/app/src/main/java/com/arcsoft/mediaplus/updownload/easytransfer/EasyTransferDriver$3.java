// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;

import android.app.Application;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferDriver, EasyTransferList, EasyTransferTimer

class this._cls0
    implements ine.IOnActionListener
{

    final EasyTransferDriver this$0;

    public boolean onDoAction(ine.Action action, Object obj)
    {
        ateMachineData atemachinedata;
        int i;
        boolean flag;
        if (mFinished || EasyTransferDriver.this == null)
        {
            return false;
        }
        atemachinedata = (ateMachineData)obj;
        i = ..SwitchMap.com.arcsoft.mediaplus.updownload.easytransfer.EasyTransferStateMachine.Action[action.ordinal()];
        flag = false;
        i;
        JVM INSTR tableswitch 1 5: default 72
    //                   1 75
    //                   2 87
    //                   3 110
    //                   4 116
    //                   5 223;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return flag;
_L2:
        flag = EasyTransferDriver.access$300(EasyTransferDriver.this);
        continue; /* Loop/switch isn't completed */
_L3:
        flag = mList.build(atemachinedata.serverudn, atemachinedata.tableid);
        continue; /* Loop/switch isn't completed */
_L4:
        flag = true;
        continue; /* Loop/switch isn't completed */
_L5:
        EasyTransferTimer easytransfertimer1 = mTimer;
        flag = false;
        if (easytransfertimer1 != null)
        {
            String s1 = atemachinedata.serverudn;
            flag = false;
            if (s1 != null)
            {
                mTransferQueue.remove(null, atemachinedata.tableid);
                mTimer.cancel(atemachinedata.serverudn, atemachinedata.tableid);
                EasyTransferDriver.access$400(EasyTransferDriver.this, atemachinedata.serverudn, atemachinedata.tableid, true);
                mList.cancel(atemachinedata.serverudn, atemachinedata.tableid);
                flag = true;
            }
        }
        continue; /* Loop/switch isn't completed */
_L6:
        EasyTransferTimer easytransfertimer = mTimer;
        flag = false;
        if (easytransfertimer != null)
        {
            String s = atemachinedata.serverudn;
            flag = false;
            if (s != null)
            {
                mTransferQueue.remove(null, atemachinedata.tableid);
                mTimer.delete(atemachinedata.serverudn, atemachinedata.tableid);
                EasyTransferDriver.access$400(EasyTransferDriver.this, atemachinedata.serverudn, atemachinedata.tableid, true);
                mList.cancel(atemachinedata.serverudn, atemachinedata.tableid);
                flag = true;
            }
        }
        if (true) goto _L1; else goto _L7
_L7:
    }

    public void onDoActionResult(ine.Action action, ine.State state, ine.State state1, Object obj)
    {
        if (!mFinished && EasyTransferDriver.this != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        ateMachineData atemachinedata = (ateMachineData)obj;
        switch (..SwitchMap.com.arcsoft.mediaplus.updownload.easytransfer.EasyTransferStateMachine.Action[action.ordinal()])
        {
        default:
            return;

        case 2: // '\002'
        case 3: // '\003'
            break;

        case 1: // '\001'
            EasyTransferDriver.access$500(EasyTransferDriver.this, atemachinedata.serverudn, atemachinedata.tableid);
            return;

        case 4: // '\004'
            if (state == ine.State.COMPLETED || state == ine.State.FAULT)
            {
                EasyTransferDriver.access$100(EasyTransferDriver.this, ine.Action.CANCEL, true, atemachinedata.serverudn, atemachinedata.tableid);
                return;
            }
            if (state1 == ine.State.CANCELLING)
            {
                EasyTransferDriver.access$600(EasyTransferDriver.this, atemachinedata.tableid, 4L);
                return;
            }
            break;

        case 5: // '\005'
            if (state == ine.State.COMPLETED || state == ine.State.FAULT)
            {
                EasyTransferDriver.access$100(EasyTransferDriver.this, ine.Action.DELETE, true, atemachinedata.serverudn, atemachinedata.tableid);
                return;
            }
            continue; /* Loop/switch isn't completed */
        }
        if (true) goto _L1; else goto _L3
_L3:
        if (state1 != ine.State.DELETING) goto _L1; else goto _L4
_L4:
    }

    public void onFinishActionResult(ine.Action action, ine.State state, ine.State state1, Object obj)
    {
        if (!mFinished && EasyTransferDriver.this != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        ateMachineData atemachinedata = (ateMachineData)obj;
        switch (..SwitchMap.com.arcsoft.mediaplus.updownload.easytransfer.EasyTransferStateMachine.Action[action.ordinal()])
        {
        default:
            return;

        case 1: // '\001'
            if (state1 != ine.State.FAULT)
            {
                EasyTransferDriver.access$400(EasyTransferDriver.this, atemachinedata.serverudn, atemachinedata.tableid, false);
                return;
            } else
            {
                EasyTransferDriver.access$400(EasyTransferDriver.this, atemachinedata.serverudn, atemachinedata.tableid, true);
                mTransferQueue.remove(atemachinedata.serverudn, atemachinedata.tableid);
                EasyTransferDriver.access$600(EasyTransferDriver.this, atemachinedata.tableid, 1L);
                ansferServerNode ansferservernode4 = mTransferQueue.find(null, atemachinedata.tableid);
                EasyTransferDriver.access$700(EasyTransferDriver.this, mApp.getApplicationContext(), ansferservernode4);
                return;
            }

        case 2: // '\002'
            if (state1 == ine.State.FAULT)
            {
                mTransferQueue.remove(atemachinedata.serverudn, atemachinedata.tableid);
                mList.clear();
                EasyTransferDriver.access$600(EasyTransferDriver.this, atemachinedata.tableid, 1L);
                ansferServerNode ansferservernode3 = mTransferQueue.find(null, atemachinedata.tableid);
                EasyTransferDriver.access$700(EasyTransferDriver.this, mApp.getApplicationContext(), ansferservernode3);
                return;
            }
            break;

        case 3: // '\003'
            EasyTransferDriver.access$600(EasyTransferDriver.this, atemachinedata.tableid, 1L);
            ansferServerNode ansferservernode2 = mTransferQueue.find(null, atemachinedata.tableid);
            EasyTransferDriver.access$700(EasyTransferDriver.this, mApp.getApplicationContext(), ansferservernode2);
            if (state1 == ine.State.COMPLETED)
            {
                EasyTransferDriver.access$600(EasyTransferDriver.this, atemachinedata.tableid, 1L);
                mTransferQueue.remove(atemachinedata.serverudn, atemachinedata.tableid);
                mList.clear();
                if (ansferservernode2 != null && ansferservernode2.total != ansferservernode2.succuss && ansferservernode2.serverstate != 3)
                {
                    Log.i("EasyTransferDriver", (new StringBuilder()).append("retry timer =").append(atemachinedata.tableid).toString());
                    mTimer.retryTimer(atemachinedata.serverudn, atemachinedata.tableid);
                    return;
                }
            }
            break;

        case 4: // '\004'
            mTransferQueue.remove(atemachinedata.serverudn, atemachinedata.tableid);
            mList.clear();
            EasyTransferDriver.access$600(EasyTransferDriver.this, atemachinedata.tableid, 1L);
            ansferServerNode ansferservernode1 = mTransferQueue.find(null, atemachinedata.tableid);
            EasyTransferDriver.access$700(EasyTransferDriver.this, mApp.getApplicationContext(), ansferservernode1);
            return;

        case 5: // '\005'
            mTransferQueue.remove(atemachinedata.serverudn, atemachinedata.tableid);
            mList.clear();
            EasyTransferDriver.access$800(EasyTransferDriver.this, atemachinedata.tableid);
            ansferServerNode ansferservernode = mTransferQueue.find(null, atemachinedata.tableid);
            EasyTransferDriver.access$700(EasyTransferDriver.this, mApp.getApplicationContext(), ansferservernode);
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    ine.State()
    {
        this$0 = EasyTransferDriver.this;
        super();
    }
}
