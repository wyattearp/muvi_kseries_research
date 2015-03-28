// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;

import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            DataTask

private class <init>
    implements com.arcsoft.adk.atv.tusListener
{

    final DataTask this$0;

    public void OnDestroyObject(String s, int i)
    {
    }

    public void OnDigaBrowseRecordTasks(String s, com.arcsoft.adk.atv.dTasks dtasks, int i)
    {
    }

    public void OnDigaCreateRecordSchedule(String s, com.arcsoft.adk.atv.dSchedule dschedule, int i)
    {
    }

    public void OnDigaDeleteRecordSchedule(String s, int i)
    {
    }

    public void OnDigaDisableRecordSchedule(String s, int i)
    {
    }

    public void OnDigaEnableRecordSchedule(String s, int i)
    {
    }

    public void OnDigaXP9eGetContainerIds(String s, String s1, int i)
    {
        Log.v("DataTask", (new StringBuilder()).append("OnDigaXP9eGetContainerIds errorcode = ").append(i).toString());
        if (i == 0)
        {
            DataTask.access$1602(DataTask.this, s1);
            boolean flag = dater.access._mth1800(DataTask.access$1400(DataTask.this), s);
            resumeTask();
            if (!flag && DataTask.access$900(DataTask.this) != null)
            {
                synchronized (DataTask.access$900(DataTask.this))
                {
                    DataTask.access$900(DataTask.this).OnDataTransmittedFinish(DataTask.access$1100(DataTask.this), -1);
                }
                return;
            }
        } else
        {
            Log.w("DataTask", (new StringBuilder()).append("OnDigaXP9eGetContainerIds errorcode = ").append(i).toString());
        }
        break MISSING_BLOCK_LABEL_142;
        exception;
        stener;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void onGetSearchCapabilities(String s, String s1, int i)
    {
    }

    public void onGetSortCapabilities(String s, String s1, int i)
    {
        dater.access._mth1700(DataTask.access$1400(DataTask.this), s);
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

    private stener()
    {
        this$0 = DataTask.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
