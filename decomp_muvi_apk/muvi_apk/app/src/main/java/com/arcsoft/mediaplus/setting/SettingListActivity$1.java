// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.view.View;
import android.widget.AdapterView;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            SettingListActivity

class this._cls0
    implements android.widget.kListener
{

    final SettingListActivity this$0;

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        if (SettingListActivity.access$000(SettingListActivity.this) == 1)
        {
            SettingListActivity.access$100(SettingListActivity.this);
            return;
        } else
        {
            SettingListActivity.access$200(SettingListActivity.this);
            return;
        }
    }

    _cls9()
    {
        this$0 = SettingListActivity.this;
        super();
    }
}
