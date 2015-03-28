// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;

import android.view.View;
import android.widget.AdapterView;

// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            EasyTransferRegisterListView

class istItemClickListener
    implements android.widget.
{

    final EasyTransferRegisterListView this$0;
    final istItemClickListener val$itemListener;

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        ewHolder ewholder = (ewHolder)view.getTag();
        if (val$itemListener != null)
        {
            val$itemListener.onItemClick(adapterview, view, i, ewholder.tableid);
        }
    }

    istItemClickListener()
    {
        this$0 = final_easytransferregisterlistview;
        val$itemListener = istItemClickListener.this;
        super();
    }
}
