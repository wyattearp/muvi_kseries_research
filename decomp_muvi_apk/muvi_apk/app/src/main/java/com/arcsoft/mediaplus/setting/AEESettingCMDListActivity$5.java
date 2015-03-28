// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            AEESettingCMDListActivity

class val.data
    implements android.widget.ner
{

    final AEESettingCMDListActivity this$0;
    final com.arcsoft.videostream.aee._cls5.this._cls0 val$data;

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        AEESettingCMDListActivity.access$502(AEESettingCMDListActivity.this, i);
        Log.i("zdf", (new StringBuilder()).append("showChoiceCmdView, sendHandleWithTime, data.name = ").append(val$data.ame).append(", data.options[").append(i).append("] = ").append(val$data.ptions[i]).append(", mCurCmd = ").append(AEESettingCMDListActivity.access$300(AEESettingCMDListActivity.this)).append(", mCurVal = ").append(AEESettingCMDListActivity.access$500(AEESettingCMDListActivity.this)).toString());
        AEESettingCMDListActivity.access$000(AEESettingCMDListActivity.this, 0x1100002, 0, (new StringBuilder()).append(val$data.ame).append(";").append(val$data.ptions[i]).toString(), AEESettingCMDListActivity.access$300(AEESettingCMDListActivity.this), AEESettingCMDListActivity.access$500(AEESettingCMDListActivity.this));
        AEESettingCMDListActivity.access$702(AEESettingCMDListActivity.this, val$data.ptions[i]);
        if (AEESettingCMDListActivity.access$600(AEESettingCMDListActivity.this) != null)
        {
            AEESettingCMDListActivity.access$600(AEESettingCMDListActivity.this).dismiss();
        }
        AEESettingCMDListActivity.access$000(AEESettingCMDListActivity.this, 0x1100005, 0, null, 1, -1);
    }

    ()
    {
        this$0 = final_aeesettingcmdlistactivity;
        val$data = com.arcsoft.videostream.aee._cls5.val.data.this;
        super();
    }
}
