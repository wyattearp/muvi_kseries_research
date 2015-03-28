// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;


// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            ChannelDataMgr

class this._cls0
    implements com.arcsoft.adk.atv.rverStatusListener
{

    final ChannelDataMgr this$0;

    public void OnDestroyObject(String s, int i)
    {
    }

    public void OnDigaBrowseRecordTasks(String s, com.arcsoft.adk.atv.OnRecordTasks onrecordtasks, int i)
    {
    }

    public void OnDigaCreateRecordSchedule(String s, com.arcsoft.adk.atv.OnRecordSchedule onrecordschedule, int i)
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
    }

    public void onGetSearchCapabilities(String s, String s1, int i)
    {
    }

    public void onGetSortCapabilities(String s, String s1, int i)
    {
    }

    public void onServerAdded(com.arcsoft.adk.atv.esc esc)
    {
        synchronized (ChannelDataMgr.this)
        {
            if (ChannelDataMgr.access$500(ChannelDataMgr.this) != null && ChannelDataMgr.access$500(ChannelDataMgr.this).equals(esc.m_strUuid))
            {
                ChannelDataMgr.access$600(ChannelDataMgr.this);
            }
        }
        return;
        exception;
        channeldatamgr;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void onServerMetaChanged(com.arcsoft.adk.atv.esc esc)
    {
    }

    public void onServerRemoved(com.arcsoft.adk.atv.esc esc)
    {
        synchronized (ChannelDataMgr.this)
        {
            if (ChannelDataMgr.access$500(ChannelDataMgr.this) != null && ChannelDataMgr.access$500(ChannelDataMgr.this).equals(esc.m_strUuid))
            {
                ChannelDataMgr.access$700(ChannelDataMgr.this);
            }
        }
        return;
        exception;
        channeldatamgr;
        JVM INSTR monitorexit ;
        throw exception;
    }

    ()
    {
        this$0 = ChannelDataMgr.this;
        super();
    }
}
