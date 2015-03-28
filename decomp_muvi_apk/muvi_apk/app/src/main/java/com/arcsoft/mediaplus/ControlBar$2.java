// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.app.Activity;
import android.view.View;
import com.arcsoft.mediaplus.dmc.RenderDevSelector;

// Referenced classes of package com.arcsoft.mediaplus:
//            ControlBar, ListViewManager

class this._cls0
    implements android.view.istener
{

    final ControlBar this$0;

    public void onClick(View view)
    {
        if (mDevSelector == null)
        {
            mDevSelector = new RenderDevSelector((Activity)ControlBar.access$000(ControlBar.this));
        }
        mDevSelector.setDataSourceFilter(ControlBar.access$100(ControlBar.this).getPlayDataSourceFilter());
        RenderDevSelector.CURRENT_PROVIDER_TYPE = com.arcsoft.mediaplus.dmc.IDER_TYPE.TYPE_FROM_MULTI_VIEW;
        boolean flag;
        if (getCurrentViewType() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        RenderDevSelector.CURRENT_PROVIDER_FROM_LOCAL = flag;
        mDevSelector.start();
    }

    PROVIDER_TYPE()
    {
        this$0 = ControlBar.this;
        super();
    }
}
