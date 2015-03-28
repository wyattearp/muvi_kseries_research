// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import android.content.DialogInterface;
import android.widget.CheckBox;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            RenderDevSelector

class this._cls0
    implements android.content.ickListener
{

    final RenderDevSelector this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        RenderDevSelector.access$102(RenderDevSelector.this, true);
        RenderDevSelector.access$200(RenderDevSelector.this).setChecked(false);
        RenderDevSelector.access$1100(RenderDevSelector.this);
    }

    r()
    {
        this$0 = RenderDevSelector.this;
        super();
    }
}
