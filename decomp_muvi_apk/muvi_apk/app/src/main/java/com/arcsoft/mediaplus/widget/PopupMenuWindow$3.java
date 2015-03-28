// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.widget;


// Referenced classes of package com.arcsoft.mediaplus.widget:
//            PopupMenuWindow

class this._cls0
    implements android.widget.issListener
{

    final PopupMenuWindow this$0;

    public void onDismiss()
    {
        setFocusable(false);
    }

    ()
    {
        this$0 = PopupMenuWindow.this;
        super();
    }
}
