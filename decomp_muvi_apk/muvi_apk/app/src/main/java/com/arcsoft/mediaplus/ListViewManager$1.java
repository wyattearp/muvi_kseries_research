// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.view.View;
import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.mediaplus.listview.IItemListView;
import java.util.HashMap;

// Referenced classes of package com.arcsoft.mediaplus:
//            ListViewManager, MediaPlusActivity

class val.view
    implements com.arcsoft.mediaplus.listview.tOpListener
{

    final ListViewManager this$0;
    final View val$view;

    public void OnItemClick(int i, long l)
    {
        com.arcsoft.adk.atv.c c = ((IItemListView)mListViews.get(Integer.valueOf(ListViewManager.access$000(ListViewManager.this)))).getDataSource().getRemoteItemDesc(i);
        ((MediaPlusActivity)ListViewManager.access$100(ListViewManager.this)).doItemSelect(i, c, l);
    }

    public void OnItemLongPress(int i)
    {
        val$view.setTag(Integer.valueOf(i));
    }

    urce()
    {
        this$0 = final_listviewmanager;
        val$view = View.this;
        super();
    }
}
