// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.content.Intent;
import android.view.View;
import com.arcsoft.mediaplus.dmc.DmcUtils;
import com.arcsoft.videostream.rtsp.ChangeRtspDlg;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            SettingsActivity, SettingListActivity, About

class this._cls0
    implements android.view.r
{

    final SettingsActivity this$0;

    public void onClick(View view)
    {
        switch (view.getId())
        {
        case 2131296511: 
        default:
            return;

        case 2131296504: 
            Intent intent2 = new Intent(getApplicationContext(), com/arcsoft/mediaplus/setting/SettingListActivity);
            intent2.putExtra("settingtype", 0);
            startActivityForResult(intent2, 0);
            return;

        case 2131296494: 
            Intent intent1 = new Intent(getApplicationContext(), com/arcsoft/mediaplus/setting/SettingListActivity);
            intent1.putExtra("settingtype", 1);
            startActivityForResult(intent1, 1);
            return;

        case 2131296509: 
            startActivity(new Intent("android.settings.WIFI_SETTINGS"));
            return;

        case 2131296510: 
            Intent intent = new Intent(getApplicationContext(), com/arcsoft/mediaplus/setting/About);
            startActivity(intent);
            return;

        case 2131296503: 
            dealWithClearCacheClicked();
            return;

        case 2131296507: 
            updateSupportLivingView();
            updateSupportLivingViewItem();
            toastSupportLivingViewOk();
            return;

        case 2131296262: 
            finish();
            return;

        case 2131296506: 
            (new ChangeRtspDlg(SettingsActivity.this)).showDialog();
            return;

        case 2131296499: 
            DmcUtils.resetDefaultRender(SettingsActivity.this);
            return;

        case 2131296495: 
            SettingsActivity.access$100(SettingsActivity.this, 0x7f0900ef, 0x7f0b006a, SettingsActivity.access$000(SettingsActivity.this));
            return;
        }
    }

    ()
    {
        this$0 = SettingsActivity.this;
        super();
    }
}
