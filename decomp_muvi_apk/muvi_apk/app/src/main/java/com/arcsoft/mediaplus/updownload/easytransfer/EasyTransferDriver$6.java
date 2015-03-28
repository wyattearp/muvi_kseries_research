// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;

import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferDriver

class transmittedBegined
    implements com.arcsoft.mediaplus.datasource.db.Listener
{

    final EasyTransferDriver this$0;
    boolean transmittedBegined;

    public void OnDBDataMounted(String s)
    {
    }

    public void OnDBDataTransmittedBegin(String s)
    {
        Log.w("EasyTransferDriver", (new StringBuilder()).append("OnDBDataTransmittedBegin() =").append(s).toString());
        if (s == null)
        {
            return;
        }
        if (mTransferQueue != null)
        {
            ansferServerNode ansferservernode = mTransferQueue.first();
            if (ansferservernode != null && s.equalsIgnoreCase(ansferservernode.serverudn))
            {
                for (Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((OnEasyTransferEngineListener)iterator.next()).onEasyTransferStart(s, ansferservernode.tableid, ansferservernode.serverstate)) { }
            } else
            if (mRemoteDBMgr != null)
            {
                if (s.equalsIgnoreCase(syTransferDBMgr.access._mth1900(mRemoteDBMgr)))
                {
                    EasyTransferDriver.access$100(EasyTransferDriver.this, ine.Action.BROWSE, false, s, syTransferDBMgr.access._mth2000(mRemoteDBMgr));
                    EasyTransferDriver.access$200(EasyTransferDriver.this, ine.Action.BROWSE, s, syTransferDBMgr.access._mth2000(mRemoteDBMgr), false);
                    return;
                } else
                {
                    EasyTransferDriver.access$100(EasyTransferDriver.this, ine.Action.BROWSE, false, s, -1L);
                    EasyTransferDriver.access$200(EasyTransferDriver.this, ine.Action.BROWSE, s, -1L, false);
                    return;
                }
            }
        } else
        if (mRemoteDBMgr != null)
        {
            EasyTransferDriver.access$100(EasyTransferDriver.this, ine.Action.BROWSE, false, s, -1L);
            return;
        }
        transmittedBegined = true;
    }

    public void OnDBDataTransmittedFinish(String s)
    {
        Log.w("EasyTransferDriver", (new StringBuilder()).append("OnDBDataTransmittedFinish() =").append(s).toString());
        if (!transmittedBegined)
        {
            return;
        }
        if (mTransferQueue == null) goto _L2; else goto _L1
_L1:
        ansferServerNode ansferservernode = mTransferQueue.first();
        if (ansferservernode == null || !s.equalsIgnoreCase(ansferservernode.serverudn)) goto _L4; else goto _L3
_L3:
        EasyTransferDriver.access$100(EasyTransferDriver.this, ine.Action.BROWSE, true, ansferservernode.serverudn, ansferservernode.tableid);
        EasyTransferDriver.access$200(EasyTransferDriver.this, ine.Action.BUILD, ansferservernode.serverudn, ansferservernode.tableid, false);
_L6:
        transmittedBegined = false;
        return;
_L4:
        if (mRemoteDBMgr != null)
        {
            EasyTransferDriver.access$100(EasyTransferDriver.this, ine.Action.BROWSE, false, null, -1L);
            if (s.equalsIgnoreCase(syTransferDBMgr.access._mth1900(mRemoteDBMgr)))
            {
                EasyTransferDriver.access$200(EasyTransferDriver.this, ine.Action.BROWSE, null, syTransferDBMgr.access._mth2000(mRemoteDBMgr), false);
            } else
            {
                EasyTransferDriver.access$200(EasyTransferDriver.this, ine.Action.BROWSE, s, -1L, false);
            }
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (mRemoteDBMgr != null)
        {
            EasyTransferDriver.access$100(EasyTransferDriver.this, ine.Action.BROWSE, false, null, -1L);
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public void OnDBDataUnMounted(String s)
    {
    }

    public void OnDBDataUpdated(String s, com.arcsoft.mediaplus.datasource.db.o o)
    {
    }

    ine.Action()
    {
        this$0 = EasyTransferDriver.this;
        super();
        transmittedBegined = false;
    }
}
