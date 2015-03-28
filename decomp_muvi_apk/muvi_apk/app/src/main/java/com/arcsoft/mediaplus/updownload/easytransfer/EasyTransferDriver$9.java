// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;

import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferDriver

class this._cls0
    implements com.arcsoft.adk.atv.StatusListener
{

    final EasyTransferDriver this$0;

    public void OnDestroyObject(String s, int i)
    {
    }

    public void OnDigaBrowseRecordTasks(String s, com.arcsoft.adk.atv.cordTasks cordtasks, int i)
    {
    }

    public void OnDigaCreateRecordSchedule(String s, com.arcsoft.adk.atv.cordSchedule cordschedule, int i)
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

    public void onServerAdded(com.arcsoft.adk.atv.er._cls9 _pcls9)
    {
    }

    public void onServerMetaChanged(com.arcsoft.adk.atv.er._cls9 _pcls9)
    {
    }

    public void onServerRemoved(com.arcsoft.adk.atv.er._cls9 _pcls9)
    {
        while (mFinished || EasyTransferDriver.access$1500(EasyTransferDriver.this) == null || _pcls9.m_strUuid == null || mTransferQueue.find(_pcls9.m_strUuid, -1L) == null) 
        {
            return;
        }
        Log.i("EasyTransferDriver", (new StringBuilder()).append("onServerRemoved:").append(_pcls9.m_strUuid).toString());
        EasyTransferDriver.access$1500(EasyTransferDriver.this).removeMessages(4);
        android.os.Message message = EasyTransferDriver.access$1500(EasyTransferDriver.this).obtainMessage(4, _pcls9.m_strUuid);
        EasyTransferDriver.access$1500(EasyTransferDriver.this).sendMessage(message);
    }

    iverHandler()
    {
        this$0 = EasyTransferDriver.this;
        super();
    }
}
