// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.arcsoft.workshop.ui:
//            UICropBar, UISaveDialog

class this._cls1 extends Handler
{

    final chToEdit this$1;

    public void handleMessage(Message message)
    {
        if (message.what == 0)
        {
            UICropBar.access$200(_fld0).dismiss();
            UICropBar.access$400(_fld0).chToEdit();
        }
    }

    ()
    {
        this$1 = this._cls1.this;
        super();
    }
}
