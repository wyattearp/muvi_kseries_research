// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.arcsoft.videotrim.Utils.UtilFunc;

// Referenced classes of package com.arcsoft.videotrim:
//            TrimSaveOper

private class this._cls0 extends Handler
{

    final TrimSaveOper this$0;

    public void handleMessage(Message message)
    {
        Bundle bundle = message.getData();
        if (bundle != null)
        {
            long l = bundle.getLong("filechange_eventid");
            String s = bundle.getString("filechange_item_name");
            if (1L == l || 2L == l)
            {
                UtilFunc.Log("VideoSplite", (new StringBuilder()).append("FileObserverEventHandler--- path: ").append(s).append(", m_bFileChangeNotifeid: ").append(TrimSaveOper.access$000(TrimSaveOper.this)).toString());
                if (TrimSaveOper.access$100(TrimSaveOper.this) != null && !TrimSaveOper.access$000(TrimSaveOper.this))
                {
                    TrimSaveOper.access$002(TrimSaveOper.this, true);
                    TrimSaveOper.access$200(TrimSaveOper.this, 5);
                    return;
                }
            }
        }
    }

    public A(Looper looper)
    {
        this$0 = TrimSaveOper.this;
        super(looper);
    }
}
