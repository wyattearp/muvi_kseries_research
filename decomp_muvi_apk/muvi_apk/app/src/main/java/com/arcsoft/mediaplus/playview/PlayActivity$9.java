// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.view.View;
import android.widget.Toast;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.RenderManager;
import com.arcsoft.mediaplus.setting.Settings;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayActivity, DataSourcePlayList

class this._cls0
    implements android.view.tener
{

    final PlayActivity this$0;

    public void onClick(View view)
    {
        String s = Settings.instance().getDefaultDMRUDN();
        if (!DLNA.instance().getRenderManager().isRenderOnline(s))
        {
            Toast.makeText(PlayActivity.this, 0x7f0b0058, 1).show();
        } else
        if (mPlayList.getCurrentIndex() >= 0)
        {
            switchView();
            return;
        }
    }

    ist()
    {
        this$0 = PlayActivity.this;
        super();
    }
}
