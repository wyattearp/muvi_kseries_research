// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.os.Handler;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            SettingsActivity

class this._cls0
    implements com.arcsoft.adk.atv.erStatusListener
{

    final SettingsActivity this$0;

    public void OnDestroyObject(String s, int i)
    {
    }

    public void OnDigaBrowseRecordTasks(String s, com.arcsoft.adk.atv.RecordTasks recordtasks, int i)
    {
    }

    public void OnDigaCreateRecordSchedule(String s, com.arcsoft.adk.atv.RecordSchedule recordschedule, int i)
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

    public void onServerAdded(com.arcsoft.adk.atv.c c)
    {
        if (mHandler != null)
        {
            mHandler.removeMessages(1);
            mHandler.sendEmptyMessage(1);
        }
    }

    public void onServerMetaChanged(com.arcsoft.adk.atv.c c)
    {
        Log.e("SettingsActivity", "onServerMetaChanged");
    }

    public void onServerRemoved(com.arcsoft.adk.atv.c c)
    {
        if (mHandler != null)
        {
            mHandler.removeMessages(1);
            mHandler.sendEmptyMessage(1);
        }
    }

    dule()
    {
        this$0 = SettingsActivity.this;
        super();
    }
}
