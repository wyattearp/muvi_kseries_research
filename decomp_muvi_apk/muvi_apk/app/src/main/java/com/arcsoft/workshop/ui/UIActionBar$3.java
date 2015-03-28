// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;


// Referenced classes of package com.arcsoft.workshop.ui:
//            UICallBack, UIActionBar, UIMiniatureLineView

class this._cls0
    implements UICallBack
{

    final UIActionBar this$0;

    public void callback()
    {
        if (UIActionBar.access$100(UIActionBar.this) != null)
        {
            UIActionBar.access$100(UIActionBar.this).setVisibility(4);
        }
    }

    eView()
    {
        this$0 = UIActionBar.this;
        super();
    }
}
