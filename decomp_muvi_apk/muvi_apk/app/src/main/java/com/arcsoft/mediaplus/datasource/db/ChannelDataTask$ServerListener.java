// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;

import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.util.debug.Log;
import java.util.List;

// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            ChannelDataTask

private class <init>
    implements com.arcsoft.adk.atv.ener
{

    final ChannelDataTask this$0;

    public void OnDestroyObject(String s, int i)
    {
    }

    public void OnDigaBrowseRecordTasks(String s, com.arcsoft.adk.atv..ServerListener serverlistener, int i)
    {
        int j;
        Log.v("ChannelTask", (new StringBuilder()).append("OnDigaBrowseRecordTasks errorcode = ").append(i).append(" serverUdn=").append(ChannelDataTask.access$1100(ChannelDataTask.this)).append(" mDigaAction=").append(mDigaAction).toString());
        j = mDigaAction;
        if (ChannelDataTask.access$1100(ChannelDataTask.this) != null) goto _L2; else goto _L1
_L1:
        mDigaAction = 0;
_L8:
        if ((4 & mDigaAction) == 0) goto _L4; else goto _L3
_L3:
        dater dater = dater.access._mth600(ChannelDataTask.access$1000(ChannelDataTask.this), mCreateRecordScheduleObjID);
        Log.v("ChannelTask", (new StringBuilder()).append("play: step 3 / 3 channelData = ").append(dater).toString());
        ChannelDataTask channeldatatask;
        if (dater != null)
        {
            requestChannelItemData(dater.nelid, dater.nelid, ((com.arcsoft.adk.atv.em)serverlistener.m_TaskItem.get(0)).m_strRecordedCDSObjectID);
        } else
        {
            mDigaAction = 0;
        }
_L6:
        if (mDigaAction == 0)
        {
            synchronized (ChannelDataTask.access$1300(ChannelDataTask.this))
            {
                ChannelDataTask.access$1300(ChannelDataTask.this).OnDigaActionFinish(j, 7);
            }
        }
        return;
_L2:
        if (i != 0 || serverlistener.m_TaskItem == null)
        {
            channeldatatask = ChannelDataTask.this;
            channeldatatask.mDigaAction = 1 & channeldatatask.mDigaAction;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if ((1 & mDigaAction) != 0)
        {
            ChannelDataTask.access$1200(ChannelDataTask.this);
        }
        if (true) goto _L6; else goto _L5
_L5:
        exception;
        pdatelistener;
        JVM INSTR monitorexit ;
        throw exception;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public void OnDigaCreateRecordSchedule(String s, com.arcsoft.adk.atv.le le, int i)
    {
        int j;
        Log.v("ChannelTask", (new StringBuilder()).append("OnDigaCreateRecordSchedule errorcode = ").append(i).append(" serverUdn=").append(ChannelDataTask.access$1100(ChannelDataTask.this)).append(" mNextCreateRecordScheduleObjID=").append(mNextCreateRecordScheduleObjID).append(" mDigaAction=").append(mDigaAction).toString());
        j = mDigaAction;
        if (ChannelDataTask.access$1100(ChannelDataTask.this) != null) goto _L2; else goto _L1
_L1:
        mDigaAction = 0;
_L8:
        if (i == 0)
        {
            Settings.instance().setRecordScheduleID(le.m_strRecordScheduleID);
        }
        if ((4 & mDigaAction) == 0) goto _L4; else goto _L3
_L3:
        DLNA.instance().getServerManager().digaBrowseRecordTasks(ChannelDataTask.access$1100(ChannelDataTask.this), le.m_strRecordScheduleID, "*:*", 0, 1, null);
_L6:
        if (mDigaAction == 0)
        {
            synchronized (ChannelDataTask.access$1300(ChannelDataTask.this))
            {
                ChannelDataTask.access$1300(ChannelDataTask.this).OnDigaActionFinish(j, 6);
            }
        }
        return;
_L2:
        if (i != 0)
        {
            ChannelDataTask channeldatatask = ChannelDataTask.this;
            channeldatatask.mDigaAction = 1 & channeldatatask.mDigaAction;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if ((8 & mDigaAction) != 0)
        {
            mCreateRecordScheduleObjID = mNextCreateRecordScheduleObjID;
            DLNA.instance().getServerManager().digaEnableRecordSchedule(ChannelDataTask.access$1100(ChannelDataTask.this), le.m_strRecordScheduleID);
        } else
        if ((1 & mDigaAction) != 0)
        {
            ChannelDataTask.access$1200(ChannelDataTask.this);
        }
        if (true) goto _L6; else goto _L5
_L5:
        exception;
        pdatelistener;
        JVM INSTR monitorexit ;
        throw exception;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public void OnDigaDeleteRecordSchedule(String s, int i)
    {
        int j;
        Log.v("ChannelTask", (new StringBuilder()).append("OnDigaDeleteRecordSchedule errorcode = ").append(i).append(" serverUdn=").append(ChannelDataTask.access$1100(ChannelDataTask.this)).append(" mDigaAction=").append(mDigaAction).toString());
        j = mDigaAction;
        ChannelDataTask channeldatatask = ChannelDataTask.this;
        channeldatatask.mDigaAction = -4 & channeldatatask.mDigaAction;
        if (ChannelDataTask.access$1100(ChannelDataTask.this) == null)
        {
            mDigaAction = 0;
        }
        if (i == 0 || i == 703) goto _L2; else goto _L1
_L1:
        boolean flag = false;
        if (i != 704) goto _L3; else goto _L2
_L2:
        Settings.instance().setRecordScheduleID("");
        flag = true;
        if (i != 0 || (j & 2) == 0) goto _L5; else goto _L4
_L4:
        ChannelDataTask channeldatatask1 = ChannelDataTask.this;
        channeldatatask1.mDigaAction = 2 | channeldatatask1.mDigaAction;
        requestChannelData();
_L3:
        if (mDigaAction != 0) goto _L7; else goto _L6
_L6:
        pdateListener pdatelistener = ChannelDataTask.access$1300(ChannelDataTask.this);
        pdatelistener;
        JVM INSTR monitorenter ;
        pdateListener pdatelistener1 = ChannelDataTask.access$1300(ChannelDataTask.this);
        int k = 0;
        if (!flag)
        {
            k = 4;
        }
        pdatelistener1.OnDigaActionFinish(j, k);
        pdatelistener;
        JVM INSTR monitorexit ;
_L7:
        return;
_L5:
        if ((4 & mDigaAction) != 0)
        {
            DLNA.instance().getServerManager().digaCreateRecordSchedule(ChannelDataTask.access$1100(ChannelDataTask.this), "", mCreateRecordScheduleObjID);
        }
        if (true) goto _L3; else goto _L8
_L8:
        Exception exception;
        exception;
        pdatelistener;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void OnDigaDisableRecordSchedule(String s, int i)
    {
        Log.v("ChannelTask", (new StringBuilder()).append("OnDigaDisableRecordSchedule errorcode = ").append(i).append(" serverUdn=").append(ChannelDataTask.access$1100(ChannelDataTask.this)).append(" mDigaAction=").append(mDigaAction).toString());
        int j = mDigaAction;
        if (ChannelDataTask.access$1100(ChannelDataTask.this) == null)
        {
            mDigaAction = 0;
        }
        if (i == 0 && (8 & mDigaAction) != 0)
        {
            dater dater1 = dater.access._mth600(ChannelDataTask.access$1000(ChannelDataTask.this), mCreateRecordScheduleObjID);
            Log.v("ChannelTask", (new StringBuilder()).append("switch: step 2 / 4 data = ").append(dater1).toString());
            dater dater;
            if (dater1 != null)
            {
                requestChannelItemData(dater1.nelid, dater1.nelid, dater1.rVideoItemObjId);
            } else
            {
                mDigaAction = 0;
            }
        }
        if ((1 & mDigaAction) != 0)
        {
            ChannelDataTask.access$1200(ChannelDataTask.this);
        }
        if (i != 0 && (8 & mDigaAction) != 0)
        {
            dater = dater.access._mth600(ChannelDataTask.access$1000(ChannelDataTask.this), mNextCreateRecordScheduleObjID);
            Log.v("ChannelTask", (new StringBuilder()).append("switch: step 2 / 4 retry delete/create data = ").append(dater).toString());
            if (dater != null)
            {
                mDigaAction = 4;
                ChannelDataTask.access$1400(ChannelDataTask.this, dater.nelid, dater.nelid);
            }
        }
        if (mDigaAction == 0)
        {
            synchronized (ChannelDataTask.access$1300(ChannelDataTask.this))
            {
                ChannelDataTask.access$1300(ChannelDataTask.this).OnDigaActionFinish(j, 8);
            }
        }
        return;
        exception;
        pdatelistener;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void OnDigaEnableRecordSchedule(String s, int i)
    {
        pdateListener pdatelistener;
        int k;
        Log.v("ChannelTask", (new StringBuilder()).append("OnDigaEnableRecordSchedule errorcode = ").append(i).append(" serverUdn=").append(ChannelDataTask.access$1100(ChannelDataTask.this)).append(" mDigaAction=").append(mDigaAction).toString());
        int j = mDigaAction;
        pdateListener pdatelistener1;
        if (ChannelDataTask.access$1100(ChannelDataTask.this) == null)
        {
            mDigaAction = 0;
        } else
        {
            ChannelDataTask channeldatatask = ChannelDataTask.this;
            channeldatatask.mDigaAction = 1 & channeldatatask.mDigaAction;
        }
        if (i != 0 && ChannelDataTask.access$1100(ChannelDataTask.this) != null && (j & 8) != 0)
        {
            dater dater = dater.access._mth600(ChannelDataTask.access$1000(ChannelDataTask.this), mNextCreateRecordScheduleObjID);
            Log.v("ChannelTask", (new StringBuilder()).append("switch: step 2 / 4 retry delete/create data = ").append(dater).toString());
            if (dater != null)
            {
                mDigaAction = 4;
                ChannelDataTask.access$1400(ChannelDataTask.this, dater.nelid, dater.nelid);
            }
        }
        if ((1 & mDigaAction) != 0)
        {
            ChannelDataTask.access$1200(ChannelDataTask.this);
        }
        if (mDigaAction != 0) goto _L2; else goto _L1
_L1:
        pdatelistener = ChannelDataTask.access$1300(ChannelDataTask.this);
        pdatelistener;
        JVM INSTR monitorenter ;
        pdatelistener1 = ChannelDataTask.access$1300(ChannelDataTask.this);
        if (ChannelDataTask.access$1100(ChannelDataTask.this) == null) goto _L4; else goto _L3
_L3:
        k = 0;
        if (i == 0) goto _L5; else goto _L4
_L5:
        pdatelistener1.OnDigaActionFinish(j, k);
        pdatelistener;
        JVM INSTR monitorexit ;
_L2:
        return;
        Exception exception;
        exception;
        pdatelistener;
        JVM INSTR monitorexit ;
        throw exception;
_L4:
        k = 9;
        if (true) goto _L5; else goto _L6
_L6:
    }

    public void OnDigaXP9eGetContainerIds(String s, String s1, int i)
    {
    }

    public void onGetSearchCapabilities(String s, String s1, int i)
    {
    }

    public void onGetSortCapabilities(String s, String s1, int i)
    {
    }

    public void onServerAdded(com.arcsoft.adk.atv..ServerListener serverlistener)
    {
    }

    public void onServerMetaChanged(com.arcsoft.adk.atv..ServerListener serverlistener)
    {
    }

    public void onServerRemoved(com.arcsoft.adk.atv..ServerListener serverlistener)
    {
    }

    private pdateListener()
    {
        this$0 = ChannelDataTask.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
