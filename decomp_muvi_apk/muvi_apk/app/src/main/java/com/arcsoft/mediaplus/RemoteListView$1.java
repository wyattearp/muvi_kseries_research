// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.view.View;
import android.widget.AdapterView;

// Referenced classes of package com.arcsoft.mediaplus:
//            RemoteListView, ViewManager

class this._cls0
    implements android.widget.mClickListener
{

    final RemoteListView this$0;

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        if (!isGridView)
        {
            break MISSING_BLOCK_LABEL_102;
        }
        if (ViewManager.getViewStatus() != 2) goto _L2; else goto _L1
_L1:
        updateSelector(view, i);
_L4:
        return;
_L2:
        if (ViewManager.getViewStatus() == 4)
        {
            if (checkalreadyDownloaded(i))
            {
                alreadyDownloaded(i);
                return;
            } else
            {
                addUpdownload(false, i);
                RemoteListView.access$000(RemoteListView.this);
                return;
            }
        }
        if (ViewManager.getViewStatus() != 0 || mOpListener == null) goto _L4; else goto _L3
_L3:
        mOpListener.OnItemClick(i, 0L);
        return;
        if (checkalreadyDownloaded(i))
        {
            alreadyDownloaded(i);
            return;
        } else
        {
            addUpdownload(false, i);
            RemoteListView.access$000(RemoteListView.this);
            return;
        }
    }

    tView.IListOpListener()
    {
        this$0 = RemoteListView.this;
        super();
    }
}
