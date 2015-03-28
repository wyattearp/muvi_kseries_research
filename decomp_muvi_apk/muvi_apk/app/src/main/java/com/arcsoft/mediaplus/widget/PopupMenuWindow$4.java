// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.widget;

import android.os.Handler;
import android.os.Message;
import android.widget.PopupWindow;

// Referenced classes of package com.arcsoft.mediaplus.widget:
//            PopupMenuWindow

class this._cls0 extends Handler
{

    final PopupMenuWindow this$0;

    public void handleMessage(Message message)
    {
        while (message.what != 0 || PopupMenuWindow.access$100(PopupMenuWindow.this) == null || !PopupMenuWindow.access$100(PopupMenuWindow.this).isShowing()) 
        {
            return;
        }
        PopupMenuWindow.access$100(PopupMenuWindow.this).dismiss();
    }

    ()
    {
        this$0 = PopupMenuWindow.this;
        super();
    }
}
