// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.view.View;
import com.arcsoft.mediaplus.picture.ui.RemoteListViewGL;

// Referenced classes of package com.arcsoft.mediaplus:
//            ControlBar, ListViewManager

class this._cls0
    implements android.view.istener
{

    final ControlBar this$0;

    public void onClick(View view)
    {
        com.arcsoft.mediaplus.listview.IItemListView iitemlistview = ControlBar.access$100(ControlBar.this).getCurrentListView();
        if (iitemlistview instanceof RemoteListViewGL)
        {
            ((RemoteListViewGL)iitemlistview).cancelAll();
        }
    }

    moteListViewGL()
    {
        this$0 = ControlBar.this;
        super();
    }
}
