// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.content.DialogInterface;

// Referenced classes of package com.arcsoft.mediaplus:
//            RemoteListView

class this._cls0
    implements android.content.nClickListener
{

    final RemoteListView this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        addDownloadByTask(RemoteListView.access$100(RemoteListView.this));
        RemoteListView.access$000(RemoteListView.this);
    }

    Listener()
    {
        this$0 = RemoteListView.this;
        super();
    }
}
