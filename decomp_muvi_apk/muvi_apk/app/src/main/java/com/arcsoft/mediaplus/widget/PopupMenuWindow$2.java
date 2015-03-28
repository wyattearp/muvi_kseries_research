// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.widget;

import android.view.MotionEvent;
import android.view.View;

// Referenced classes of package com.arcsoft.mediaplus.widget:
//            PopupMenuWindow

class this._cls0
    implements android.view.er
{

    final PopupMenuWindow this$0;

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        setFocusable(true);
        return false;
    }

    ()
    {
        this$0 = PopupMenuWindow.this;
        super();
    }
}
