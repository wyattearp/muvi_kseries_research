// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusListView

class this._cls0 extends Handler
{

    final MediaPlusListView this$0;

    public void handleMessage(Message message)
    {
        super.handleMessage(message);
        switch (message.what)
        {
        default:
            return;

        case 1537: 
            updownloadStart(message.obj);
            return;

        case 1538: 
            updownloadProgress(message.arg1, message.obj);
            return;

        case 1539: 
            updownloadFinish(message.arg1, message.obj);
            return;

        case 1540: 
            updownloadExist();
            break;
        }
    }

    ()
    {
        this$0 = MediaPlusListView.this;
        super();
    }
}
