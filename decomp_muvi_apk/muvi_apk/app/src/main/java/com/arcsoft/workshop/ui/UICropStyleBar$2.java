// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import android.view.View;

// Referenced classes of package com.arcsoft.workshop.ui:
//            UICropStyleBar, UICropSizeWindow

class this._cls0
    implements android.view.ner
{

    final UICropStyleBar this$0;

    public void onClick(View view)
    {
        view.getId();
        JVM INSTR tableswitch 2131296652 2131296653: default 28
    //                   2131296652 29
    //                   2131296653 64;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        if (!mPopWindow.isShowing())
        {
            mPopWindow.showPopupMenuWindow();
            return;
        } else
        {
            mPopWindow.hide();
            return;
        }
_L3:
        process(7, true);
        if (mPopWindow != null)
        {
            mPopWindow.retsetMode();
            mPopWindow.hide();
            return;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    ()
    {
        this$0 = UICropStyleBar.this;
        super();
    }
}
