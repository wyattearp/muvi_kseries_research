// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            SettingListActivity

class this._cls0
    implements com.arcsoft.adk.atv.tatusListener
{

    final SettingListActivity this$0;

    public void OnDestroyObject(String s, int i)
    {
    }

    public void OnDigaBrowseRecordTasks(String s, com.arcsoft.adk.atv.ordTasks ordtasks, int i)
    {
    }

    public void OnDigaCreateRecordSchedule(String s, com.arcsoft.adk.atv.ordSchedule ordschedule, int i)
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

    public void onServerAdded(com.arcsoft.adk.atv.ctivity._cls7 _pcls7)
    {
    }

    public void onServerMetaChanged(com.arcsoft.adk.atv.ctivity._cls7 _pcls7)
    {
        Log.e(TAG, "settingList onServerMetaChanged");
        if (_pcls7 != null)
        {
            if (_pcls7._DeviceIcon != null);
        }
    }

    public void onServerRemoved(com.arcsoft.adk.atv.ctivity._cls7 _pcls7)
    {
        Log.e(TAG, "mServerStatusListener onServerRemoved");
    }

    e()
    {
        this$0 = SettingListActivity.this;
        super();
    }
}
