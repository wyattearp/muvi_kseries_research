// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;


// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            RemoteDBMgr, DataTask

class this._cls0
    implements com.arcsoft.adk.atv.IServerStatusListener
{

    final RemoteDBMgr this$0;

    public void OnDestroyObject(String s, int i)
    {
    }

    public void OnDigaBrowseRecordTasks(String s, com.arcsoft.adk.atv.ataOnRecordTasks ataonrecordtasks, int i)
    {
    }

    public void OnDigaCreateRecordSchedule(String s, com.arcsoft.adk.atv.ataOnRecordSchedule ataonrecordschedule, int i)
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

    public void onServerAdded(com.arcsoft.adk.atv.erDesc erdesc)
    {
        synchronized (RemoteDBMgr.this)
        {
            if (mServerUDN != null && mServerUDN.equals(erdesc.m_strUuid))
            {
                startDBData(mDataTask.getCurrentFolder(), true);
            }
        }
        return;
        exception;
        remotedbmgr;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void onServerMetaChanged(com.arcsoft.adk.atv.erDesc erdesc)
    {
    }

    public void onServerRemoved(com.arcsoft.adk.atv.erDesc erdesc)
    {
        synchronized (RemoteDBMgr.this)
        {
            if (mServerUDN != null && mServerUDN.equals(erdesc.m_strUuid))
            {
                stopDBData();
            }
        }
        return;
        exception;
        remotedbmgr;
        JVM INSTR monitorexit ;
        throw exception;
    }

    ule()
    {
        this$0 = RemoteDBMgr.this;
        super();
    }
}
