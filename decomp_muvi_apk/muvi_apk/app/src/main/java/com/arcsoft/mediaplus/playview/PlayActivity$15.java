// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.content.res.Resources;
import android.widget.Toast;
import com.arcsoft.mediaplus.ListViewManager;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.tool.FileDelete;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayActivity

class this._cls0
    implements com.arcsoft.adk.atv.erverStatusListener
{

    final PlayActivity this$0;

    public void OnDestroyObject(String s, int i)
    {
        Log.e("PlayActivity", (new StringBuilder()).append("testP OnDestroyObeject updateid = ").append(s).append(" errorcode = ").append(i).toString());
        if (i == 0)
        {
            String s1 = ListViewManager.getObjId(s);
            int j = RemoteDBMgr.instance().delteRemoteItem(s1);
            Log.e("PlayActivity", (new StringBuilder()).append("testP OnDestroyObject del suc = ").append(j).toString());
        } else
        {
            PlayActivity.access$302(PlayActivity.this, true);
        }
        ListViewManager.clearUpdateItems();
        if (PlayActivity.access$2700(PlayActivity.this) != null)
        {
            PlayActivity.access$2700(PlayActivity.this).stopDelete();
        }
    }

    public void OnDigaBrowseRecordTasks(String s, com.arcsoft.adk.atv.aOnRecordTasks aonrecordtasks, int i)
    {
    }

    public void OnDigaCreateRecordSchedule(String s, com.arcsoft.adk.atv.aOnRecordSchedule aonrecordschedule, int i)
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

    public void onServerAdded(com.arcsoft.adk.atv.Desc desc)
    {
    }

    public void onServerMetaChanged(com.arcsoft.adk.atv.Desc desc)
    {
    }

    public void onServerRemoved(com.arcsoft.adk.atv.Desc desc)
    {
        String s = Settings.instance().getDefaultDMSUDN();
        if (desc.m_strUuid.equals(s))
        {
            String s1 = getResources().getString(0x7f0b0018);
            Object aobj[] = new Object[1];
            aobj[0] = desc.m_strFriendlyName;
            String s2 = String.format(s1, aobj);
            Toast.makeText(PlayActivity.this, s2, 0).show();
            if (!isLocalContent())
            {
                finish();
                return;
            }
        }
    }

    hedule()
    {
        this$0 = PlayActivity.this;
        super();
    }
}
