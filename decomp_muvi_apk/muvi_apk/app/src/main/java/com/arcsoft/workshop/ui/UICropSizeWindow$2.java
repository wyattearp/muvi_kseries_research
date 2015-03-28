// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.PopupWindow;

// Referenced classes of package com.arcsoft.workshop.ui:
//            UICropSizeWindow

class this._cls0 extends Handler
{

    final UICropSizeWindow this$0;

    public void handleMessage(Message message)
    {
        message.what;
        JVM INSTR tableswitch 0 1: default 28
    //                   0 29
    //                   1 63;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        if (UICropSizeWindow.access$100(UICropSizeWindow.this) != null && UICropSizeWindow.access$100(UICropSizeWindow.this).isShowing())
        {
            UICropSizeWindow.access$100(UICropSizeWindow.this).dismiss();
            return;
        }
          goto _L1
_L3:
        UICropSizeWindow.access$200(UICropSizeWindow.this).removeMessages(1);
        if (mBaseView.getHeight() != 0)
        {
            showPopupMenuWindow();
            return;
        } else
        {
            UICropSizeWindow.access$200(UICropSizeWindow.this).sendEmptyMessageDelayed(1, 200L);
            return;
        }
    }

    ()
    {
        this$0 = UICropSizeWindow.this;
        super();
    }
}
