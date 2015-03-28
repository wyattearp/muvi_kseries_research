// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import com.arcsoft.mediaplus.widget.PopupMenuWindow;
import com.arcsoft.workshop.OnCommandListener;

// Referenced classes of package com.arcsoft.workshop.ui:
//            UIActionBar

class this._cls0
    implements com.arcsoft.mediaplus.widget.w.OnMenuClickedListener
{

    final UIActionBar this$0;

    public void onClicked(int i)
    {
        if (i != 0) goto _L2; else goto _L1
_L1:
        UIActionBar.access$000(UIActionBar.this).onCommand(38, null, null);
_L4:
        if (UIActionBar.access$900(UIActionBar.this) != null)
        {
            UIActionBar.access$900(UIActionBar.this).hidePopopMenuWindow();
        }
        return;
_L2:
        if (i == 1)
        {
            UIActionBar.access$000(UIActionBar.this).onCommand(39, null, null);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    Window()
    {
        this$0 = UIActionBar.this;
        super();
    }
}
